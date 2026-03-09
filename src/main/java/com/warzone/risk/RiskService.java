package com.warzone.risk;

import com.warzone.alert.AlertService;
import com.warzone.conflict.ConflictRepository;
import com.warzone.nuclear.NuclearRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RiskService {

    private static final Logger log = LoggerFactory.getLogger(RiskService.class);
    private final RiskRepository riskRepo;
    private final ConflictRepository conflictRepo;
    private final NuclearRepository nuclearRepo;
    private final AlertService alerts;

    public RiskService(RiskRepository riskRepo, ConflictRepository conflictRepo,
                       NuclearRepository nuclearRepo, AlertService alerts) {
        this.riskRepo=riskRepo; this.conflictRepo=conflictRepo;
        this.nuclearRepo=nuclearRepo; this.alerts=alerts;
    }

    @Cacheable("risk")
    public Optional<RiskScore> getCurrent() { return riskRepo.findTopByOrderByCalculatedAtDesc(); }

    public List<RiskScore> getHistory() {
        return riskRepo.findAll(PageRequest.of(0,30,Sort.by(Sort.Direction.DESC,"calculatedAt"))).getContent();
    }

    @Scheduled(fixedDelayString="${app.risk.calc-interval:300000}", initialDelay=15000)
    @CacheEvict(value={"risk","dashboard"}, allEntries=true)
    public void calculate() {
        log.info("Calculating WW3 risk...");
        long crit=conflictRepo.countBySeverity("CRITICAL");
        long high=conflictRepo.countBySeverity("HIGH");
        long med=conflictRepo.countBySeverity("MEDIUM");
        long wars=conflictRepo.countActiveWars();

        double cf=Math.min((crit*10+high*5+med*2+wars*3)/50.0*100,100);
        int nukes=nuclearRepo.sumTotalWarheads();
        int dep=nuclearRepo.sumDeployed();
        double nf=Math.min(dep*0.01+nukes*0.001,100);
        if(crit>0) nf=Math.min(nf+20,100);
        double tf=Math.min(wars*8.0,100);
        double df=Math.min(crit*15+high*5,100);
        double ov=Math.min(cf*0.4+nf*0.3+tf*0.15+df*0.15,100);

        String lvl=ov>=80?"CRITICAL":ov>=60?"HIGH":ov>=40?"ELEVATED":ov>=20?"GUARDED":"LOW";
        String threat=crit>0?"Nuclear nations in active conflict":wars>3?"Multiple simultaneous conflicts":"Regional tensions elevated";

        RiskScore s=new RiskScore();
        s.setOverallScore(bd(ov)); s.setConflictScore(bd(cf)); s.setNuclearScore(bd(nf));
        s.setTroopScore(bd(tf)); s.setDiplomaticScore(bd(df));
        s.setLevel(lvl); s.setTopThreat(threat); s.setCalculatedAt(LocalDateTime.now());

        riskRepo.save(s);
        alerts.broadcastRisk(s);
        log.info("Risk: {} ({})", s.getOverallScore(), lvl);
    }

    private BigDecimal bd(double v) { return BigDecimal.valueOf(v).setScale(2, RoundingMode.HALF_UP); }
}
