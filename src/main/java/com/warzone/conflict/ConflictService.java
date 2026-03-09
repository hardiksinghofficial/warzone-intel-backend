package com.warzone.conflict;

import com.warzone.alert.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConflictService {

    private static final Logger log = LoggerFactory.getLogger(ConflictService.class);
    private final ConflictRepository repo;
    private final GdeltClient gdelt;
    private final AlertService alerts;

    public ConflictService(ConflictRepository repo, GdeltClient gdelt, AlertService alerts) {
        this.repo = repo; this.gdelt = gdelt; this.alerts = alerts;
    }

    @Cacheable("conflicts")
    public List<Conflict> getAll() { return repo.findAllByOrderBySeverityDesc(); }

    public Optional<Conflict> getById(UUID id) { return repo.findById(id); }
    public List<Conflict> getActive() { return repo.findByStatusOrderBySeverityDesc("ACTIVE_WAR"); }
    public List<Conflict> getByCountry(String code) { return repo.findByCountry(code.toUpperCase()); }
    public long countActiveWars() { return repo.countActiveWars(); }

    @Scheduled(fixedDelayString = "${app.gdelt.poll-interval:900000}", initialDelay = 10000)
    @CacheEvict(value = {"conflicts", "dashboard"}, allEntries = true)
    public void pollGdelt() {
        log.info("Polling GDELT...");
        try {
            List<Conflict> events = gdelt.fetchLatestConflicts();
            int saved = 0;
            for (Conflict e : events) {
                if (e.getExternalId() != null && !repo.existsByExternalId(e.getExternalId())) {
                    repo.save(e);
                    alerts.broadcastConflict(e);
                    saved++;
                }
            }
            if (saved > 0) log.info("Saved {} new GDELT events", saved);
        } catch (Exception e) {
            log.warn("GDELT poll failed: {}", e.getMessage());
        }
    }
}
