package com.warzone.nuclear;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class NuclearService {
    private final NuclearRepository repo;
    public NuclearService(NuclearRepository repo) { this.repo = repo; }

    @Cacheable("nuclear")
    public List<NuclearArsenal> getAll() { return repo.findAllByOrderByTotalWarheadsDesc(); }
    public Optional<NuclearArsenal> getByCountry(String code) { return repo.findByCountryCode(code.toUpperCase()); }

    public Map<String,Object> getGlobalStats() {
        Map<String,Object> s = new LinkedHashMap<>();
        s.put("totalWarheads", repo.sumTotalWarheads());
        s.put("totalDeployed", repo.sumDeployed());
        s.put("nuclearNations", repo.count());
        return s;
    }
}
