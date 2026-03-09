package com.warzone.risk;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "risk_snapshots")
public class RiskScore implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    @Column(name="overall_score",nullable=false) private BigDecimal overallScore=BigDecimal.ZERO;
    @Column(name="conflict_score") private BigDecimal conflictScore=BigDecimal.ZERO;
    @Column(name="nuclear_score") private BigDecimal nuclearScore=BigDecimal.ZERO;
    @Column(name="troop_score") private BigDecimal troopScore=BigDecimal.ZERO;
    @Column(name="diplomatic_score") private BigDecimal diplomaticScore=BigDecimal.ZERO;
    @Column(nullable=false,length=20) private String level="LOW";
    @Column(name="top_threat",length=200) private String topThreat;
    @Column(name="calculated_at") private LocalDateTime calculatedAt=LocalDateTime.now();

    public UUID getId(){return id;} public void setId(UUID v){this.id=v;}
    public BigDecimal getOverallScore(){return overallScore;} public void setOverallScore(BigDecimal v){this.overallScore=v;}
    public BigDecimal getConflictScore(){return conflictScore;} public void setConflictScore(BigDecimal v){this.conflictScore=v;}
    public BigDecimal getNuclearScore(){return nuclearScore;} public void setNuclearScore(BigDecimal v){this.nuclearScore=v;}
    public BigDecimal getTroopScore(){return troopScore;} public void setTroopScore(BigDecimal v){this.troopScore=v;}
    public BigDecimal getDiplomaticScore(){return diplomaticScore;} public void setDiplomaticScore(BigDecimal v){this.diplomaticScore=v;}
    public String getLevel(){return level;} public void setLevel(String v){this.level=v;}
    public String getTopThreat(){return topThreat;} public void setTopThreat(String v){this.topThreat=v;}
    public LocalDateTime getCalculatedAt(){return calculatedAt;} public void setCalculatedAt(LocalDateTime v){this.calculatedAt=v;}
}
