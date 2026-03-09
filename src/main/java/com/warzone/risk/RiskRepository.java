package com.warzone.risk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface RiskRepository extends JpaRepository<RiskScore, UUID> {
    Optional<RiskScore> findTopByOrderByCalculatedAtDesc();
}
