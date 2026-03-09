package com.warzone.conflict;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "conflicts")
public class Conflict implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 300) private String title;
    @Column(columnDefinition = "TEXT") private String description;
    @Column(name = "country_a", nullable = false, length = 100) private String countryA;
    @Column(name = "country_b", length = 100) private String countryB;
    @Column(nullable = false) private Double lat;
    @Column(nullable = false) private Double lng;
    @Column(length = 20) private String severity = "LOW";
    @Column(length = 30) private String status = "TENSION";
    @Column(name = "conflict_type", length = 50) private String conflictType;
    private Integer casualties = 0;
    private Integer displaced = 0;
    @Column(name = "started_at") private LocalDate startedAt;
    @Column(length = 50) private String source;
    @Column(name = "source_url", columnDefinition = "TEXT") private String sourceUrl;
    @Column(name = "external_id", unique = true) private String externalId;
    @Column(name = "fetched_at") private LocalDateTime fetchedAt;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();

    public UUID getId() { return id; } public void setId(UUID v) { this.id = v; }
    public String getTitle() { return title; } public void setTitle(String v) { this.title = v; }
    public String getDescription() { return description; } public void setDescription(String v) { this.description = v; }
    public String getCountryA() { return countryA; } public void setCountryA(String v) { this.countryA = v; }
    public String getCountryB() { return countryB; } public void setCountryB(String v) { this.countryB = v; }
    public Double getLat() { return lat; } public void setLat(Double v) { this.lat = v; }
    public Double getLng() { return lng; } public void setLng(Double v) { this.lng = v; }
    public String getSeverity() { return severity; } public void setSeverity(String v) { this.severity = v; }
    public String getStatus() { return status; } public void setStatus(String v) { this.status = v; }
    public String getConflictType() { return conflictType; } public void setConflictType(String v) { this.conflictType = v; }
    public Integer getCasualties() { return casualties; } public void setCasualties(Integer v) { this.casualties = v; }
    public Integer getDisplaced() { return displaced; } public void setDisplaced(Integer v) { this.displaced = v; }
    public LocalDate getStartedAt() { return startedAt; } public void setStartedAt(LocalDate v) { this.startedAt = v; }
    public String getSource() { return source; } public void setSource(String v) { this.source = v; }
    public String getSourceUrl() { return sourceUrl; } public void setSourceUrl(String v) { this.sourceUrl = v; }
    public String getExternalId() { return externalId; } public void setExternalId(String v) { this.externalId = v; }
    public LocalDateTime getFetchedAt() { return fetchedAt; } public void setFetchedAt(LocalDateTime v) { this.fetchedAt = v; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
}
