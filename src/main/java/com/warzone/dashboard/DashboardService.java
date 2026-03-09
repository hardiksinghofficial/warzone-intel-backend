package com.warzone.dashboard;

import com.warzone.base.BaseService;
import com.warzone.conflict.ConflictService;
import com.warzone.military.MilitaryService;
import com.warzone.nuclear.NuclearService;
import com.warzone.risk.RiskService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DashboardService {
    private final ConflictService cs;
    private final MilitaryService ms;
    private final NuclearService ns;
    private final BaseService bs;
    private final RiskService rs;

    public DashboardService(ConflictService cs, MilitaryService ms, NuclearService ns, BaseService bs, RiskService rs) {
        this.cs=cs; this.ms=ms; this.ns=ns; this.bs=bs; this.rs=rs;
    }

    @Cacheable("dashboard")
    public DashboardResponse get() {
        DashboardResponse r = new DashboardResponse();
        r.setConflicts(cs.getAll());
        r.setTopMilitary(ms.getTopN(10));
        r.setNuclearNations(ns.getAll());
        r.setBases(bs.getAll());
        r.setRiskScore(rs.getCurrent().orElse(null));

        Map<String,Object> stats = new LinkedHashMap<>();
        stats.put("totalConflicts", cs.getAll().size());
        stats.put("activeWars", cs.countActiveWars());
        stats.put("nuclearStats", ns.getGlobalStats());
        stats.put("totalBases", bs.getAll().size());
        r.setStats(stats);
        return r;
    }
}
