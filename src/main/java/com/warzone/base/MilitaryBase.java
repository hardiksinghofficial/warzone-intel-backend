package com.warzone.base;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "military_bases")
public class MilitaryBase implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    @Column(nullable=false,length=200) private String name;
    @Column(name="country_code",nullable=false,length=3) private String countryCode;
    @Column(name="host_country",length=3) private String hostCountry;
    @Column(nullable=false) private Double lat;
    @Column(nullable=false) private Double lng;
    @Column(name="base_type",nullable=false,length=30) private String baseType;
    private Integer personnel=0;
    @Column(name="is_overseas") private Boolean isOverseas=false;
    @Column(length=50) private String alliance;
    @Column(columnDefinition="TEXT") private String description;
    @Column(name="updated_at") private LocalDateTime updatedAt=LocalDateTime.now();

    public UUID getId(){return id;} public void setId(UUID v){this.id=v;}
    public String getName(){return name;} public void setName(String v){this.name=v;}
    public String getCountryCode(){return countryCode;} public void setCountryCode(String v){this.countryCode=v;}
    public String getHostCountry(){return hostCountry;} public void setHostCountry(String v){this.hostCountry=v;}
    public Double getLat(){return lat;} public void setLat(Double v){this.lat=v;}
    public Double getLng(){return lng;} public void setLng(Double v){this.lng=v;}
    public String getBaseType(){return baseType;} public void setBaseType(String v){this.baseType=v;}
    public Integer getPersonnel(){return personnel;} public void setPersonnel(Integer v){this.personnel=v;}
    public Boolean getIsOverseas(){return isOverseas;} public void setIsOverseas(Boolean v){this.isOverseas=v;}
    public String getAlliance(){return alliance;} public void setAlliance(String v){this.alliance=v;}
    public String getDescription(){return description;} public void setDescription(String v){this.description=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){this.updatedAt=v;}
}
