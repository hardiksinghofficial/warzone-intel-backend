package com.warzone.military;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "military_power")
public class MilitaryPower implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @Column(name="country_code",unique=true,nullable=false,length=3) private String countryCode;
    @Column(name="country_name",nullable=false,length=100) private String countryName;
    @Column(name="flag_emoji",length=10) private String flagEmoji;
    @Column(nullable=false) private Integer rank;
    @Column(name="power_index",nullable=false) private BigDecimal powerIndex;
    @Column(name="active_personnel") private Integer activePersonnel=0;
    @Column(name="reserve_personnel") private Integer reservePersonnel=0;
    private Integer paramilitary=0;
    private Integer tanks=0;
    @Column(name="armored_vehicles") private Integer armoredVehicles=0;
    private Integer artillery=0;
    @Column(name="rocket_launchers") private Integer rocketLaunchers=0;
    @Column(name="total_aircraft") private Integer totalAircraft=0;
    @Column(name="fighter_jets") private Integer fighterJets=0;
    @Column(name="attack_helicopters") private Integer attackHelicopters=0;
    @Column(name="transport_aircraft") private Integer transportAircraft=0;
    @Column(name="naval_vessels") private Integer navalVessels=0;
    @Column(name="aircraft_carriers") private Integer aircraftCarriers=0;
    private Integer submarines=0;
    private Integer destroyers=0;
    private Integer frigates=0;
    @Column(name="defense_budget") private BigDecimal defenseBudget=BigDecimal.ZERO;
    @Column(name="oil_production") private Integer oilProduction=0;
    private Integer airports=0;
    private Integer ports=0;
    @Column(name="nuclear_capable") private Boolean nuclearCapable=false;
    @Column(length=200) private String alliances;
    private Double lat; private Double lng;
    @Column(name="updated_at") private LocalDateTime updatedAt=LocalDateTime.now();

    public UUID getId(){return id;} public void setId(UUID v){this.id=v;}
    public String getCountryCode(){return countryCode;} public void setCountryCode(String v){this.countryCode=v;}
    public String getCountryName(){return countryName;} public void setCountryName(String v){this.countryName=v;}
    public String getFlagEmoji(){return flagEmoji;} public void setFlagEmoji(String v){this.flagEmoji=v;}
    public Integer getRank(){return rank;} public void setRank(Integer v){this.rank=v;}
    public BigDecimal getPowerIndex(){return powerIndex;} public void setPowerIndex(BigDecimal v){this.powerIndex=v;}
    public Integer getActivePersonnel(){return activePersonnel;} public void setActivePersonnel(Integer v){this.activePersonnel=v;}
    public Integer getReservePersonnel(){return reservePersonnel;} public void setReservePersonnel(Integer v){this.reservePersonnel=v;}
    public Integer getParamilitary(){return paramilitary;} public void setParamilitary(Integer v){this.paramilitary=v;}
    public Integer getTanks(){return tanks;} public void setTanks(Integer v){this.tanks=v;}
    public Integer getArmoredVehicles(){return armoredVehicles;} public void setArmoredVehicles(Integer v){this.armoredVehicles=v;}
    public Integer getArtillery(){return artillery;} public void setArtillery(Integer v){this.artillery=v;}
    public Integer getRocketLaunchers(){return rocketLaunchers;} public void setRocketLaunchers(Integer v){this.rocketLaunchers=v;}
    public Integer getTotalAircraft(){return totalAircraft;} public void setTotalAircraft(Integer v){this.totalAircraft=v;}
    public Integer getFighterJets(){return fighterJets;} public void setFighterJets(Integer v){this.fighterJets=v;}
    public Integer getAttackHelicopters(){return attackHelicopters;} public void setAttackHelicopters(Integer v){this.attackHelicopters=v;}
    public Integer getTransportAircraft(){return transportAircraft;} public void setTransportAircraft(Integer v){this.transportAircraft=v;}
    public Integer getNavalVessels(){return navalVessels;} public void setNavalVessels(Integer v){this.navalVessels=v;}
    public Integer getAircraftCarriers(){return aircraftCarriers;} public void setAircraftCarriers(Integer v){this.aircraftCarriers=v;}
    public Integer getSubmarines(){return submarines;} public void setSubmarines(Integer v){this.submarines=v;}
    public Integer getDestroyers(){return destroyers;} public void setDestroyers(Integer v){this.destroyers=v;}
    public Integer getFrigates(){return frigates;} public void setFrigates(Integer v){this.frigates=v;}
    public BigDecimal getDefenseBudget(){return defenseBudget;} public void setDefenseBudget(BigDecimal v){this.defenseBudget=v;}
    public Integer getOilProduction(){return oilProduction;} public void setOilProduction(Integer v){this.oilProduction=v;}
    public Integer getAirports(){return airports;} public void setAirports(Integer v){this.airports=v;}
    public Integer getPorts(){return ports;} public void setPorts(Integer v){this.ports=v;}
    public Boolean getNuclearCapable(){return nuclearCapable;} public void setNuclearCapable(Boolean v){this.nuclearCapable=v;}
    public String getAlliances(){return alliances;} public void setAlliances(String v){this.alliances=v;}
    public Double getLat(){return lat;} public void setLat(Double v){this.lat=v;}
    public Double getLng(){return lng;} public void setLng(Double v){this.lng=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){this.updatedAt=v;}
}
