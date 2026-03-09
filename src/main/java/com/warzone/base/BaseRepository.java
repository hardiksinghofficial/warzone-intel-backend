package com.warzone.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface BaseRepository extends JpaRepository<MilitaryBase, UUID> {
    List<MilitaryBase> findByCountryCode(String code);
    List<MilitaryBase> findByBaseType(String type);
    List<MilitaryBase> findByIsOverseasTrue();
}
