package com.warzone.military;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MilitaryService {
    private final MilitaryRepository repo;
    public MilitaryService(MilitaryRepository repo) { this.repo = repo; }

    @Cacheable("military")
    public List<MilitaryPower> getAll() { return repo.findAllByOrderByRankAsc(); }

    public Optional<MilitaryPower> getByCountry(String code) { return repo.findByCountryCode(code.toUpperCase()); }

    public List<MilitaryPower> getTopN(int n) {
        List<MilitaryPower> all = repo.findAllByOrderByRankAsc();
        return all.subList(0, Math.min(n, all.size()));
    }

    public Map<String,MilitaryPower> compare(String a, String b) {
        Map<String,MilitaryPower> r = new LinkedHashMap<>();
        repo.findByCountryCode(a.toUpperCase()).ifPresent(m -> r.put(a, m));
        repo.findByCountryCode(b.toUpperCase()).ifPresent(m -> r.put(b, m));
        return r;
    }
}
