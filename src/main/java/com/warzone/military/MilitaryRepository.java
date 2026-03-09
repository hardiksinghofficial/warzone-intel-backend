package com.warzone.military;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface MilitaryRepository extends JpaRepository<MilitaryPower, UUID> {
    Optional<MilitaryPower> findByCountryCode(String code);
    List<MilitaryPower> findAllByOrderByRankAsc();
}
