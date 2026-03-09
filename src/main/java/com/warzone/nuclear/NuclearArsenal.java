package com.warzone.nuclear;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "nuclear_arsenal")
public class NuclearArsenal implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    @Column(name="country_code",unique=true,nullable=false,length=3) private String countryCode;
    @Column(name="country_name",nullable=false,length=100) private String countryName;
    @Column(name="flag_emoji",length=10) private String flagEmoji;
    @Column(name="total_warheads") private Integer totalWarheads=0;
    private Integer deployed=0; private Integer reserve=0; private Integer retired=0;
    @Column(name="icbm_count") private Integer icbmCount=0;
    @Column(name="slbm_count") private Integer slbmCount=0;
    @Column(name="strategic_bombers") private Integer strategicBombers=0;
    @Column(name="max_range_km") private Integer maxRangeKm=0;
    @Column(name="first_test_year") private Integer firstTestYear;
    @Column(name="last_test_year") private Integer lastTestYear;
    @Column(length=50) private String policy;
    @Column(length=30) private String status;
    @Column(columnDefinition="numeric(9,6)") private Double lat;
    @Column(columnDefinition="numeric(9,6)") private Double lng;
    @Column(name="updated_at") private LocalDateTime updatedAt=LocalDateTime.now();

    public UUID getId(){return id;} public void setId(UUID v){this.id=v;}
    public String getCountryCode(){return countryCode;} public void setCountryCode(String v){this.countryCode=v;}
    public String getCountryName(){return countryName;} public void setCountryName(String v){this.countryName=v;}
    public String getFlagEmoji(){return flagEmoji;} public void setFlagEmoji(String v){this.flagEmoji=v;}
    public Integer getTotalWarheads(){return totalWarheads;} public void setTotalWarheads(Integer v){this.totalWarheads=v;}
    public Integer getDeployed(){return deployed;} public void setDeployed(Integer v){this.deployed=v;}
    public Integer getReserve(){return reserve;} public void setReserve(Integer v){this.reserve=v;}
    public Integer getRetired(){return retired;} public void setRetired(Integer v){this.retired=v;}
    public Integer getIcbmCount(){return icbmCount;} public void setIcbmCount(Integer v){this.icbmCount=v;}
    public Integer getSlbmCount(){return slbmCount;} public void setSlbmCount(Integer v){this.slbmCount=v;}
    public Integer getStrategicBombers(){return strategicBombers;} public void setStrategicBombers(Integer v){this.strategicBombers=v;}
    public Integer getMaxRangeKm(){return maxRangeKm;} public void setMaxRangeKm(Integer v){this.maxRangeKm=v;}
    public Integer getFirstTestYear(){return firstTestYear;} public void setFirstTestYear(Integer v){this.firstTestYear=v;}
    public Integer getLastTestYear(){return lastTestYear;} public void setLastTestYear(Integer v){this.lastTestYear=v;}
    public String getPolicy(){return policy;} public void setPolicy(String v){this.policy=v;}
    public String getStatus(){return status;} public void setStatus(String v){this.status=v;}
    public Double getLat(){return lat;} public void setLat(Double v){this.lat=v;}
    public Double getLng(){return lng;} public void setLng(Double v){this.lng=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){this.updatedAt=v;}
}
