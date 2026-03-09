package com.warzone.base;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BaseService {
    private final BaseRepository repo;
    public BaseService(BaseRepository repo) { this.repo = repo; }

    @Cacheable("bases") public List<MilitaryBase> getAll() { return repo.findAll(); }
    public List<MilitaryBase> getByCountry(String code) { return repo.findByCountryCode(code.toUpperCase()); }
    public List<MilitaryBase> getByType(String type) { return repo.findByBaseType(type.toUpperCase()); }
    public List<MilitaryBase> getOverseas() { return repo.findByIsOverseasTrue(); }
}
