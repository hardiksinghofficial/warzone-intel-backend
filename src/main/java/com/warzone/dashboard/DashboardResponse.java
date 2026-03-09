package com.warzone.dashboard;

import com.warzone.base.MilitaryBase;
import com.warzone.conflict.Conflict;
import com.warzone.military.MilitaryPower;
import com.warzone.nuclear.NuclearArsenal;
import com.warzone.risk.RiskScore;
import java.io.Serializable;
import java.util.*;

public class DashboardResponse implements Serializable {
    private List<Conflict> conflicts;
    private List<MilitaryPower> topMilitary;
    private List<NuclearArsenal> nuclearNations;
    private List<MilitaryBase> bases;
    private RiskScore riskScore;
    private Map<String,Object> stats;

    public List<Conflict> getConflicts(){return conflicts;} public void setConflicts(List<Conflict> v){this.conflicts=v;}
    public List<MilitaryPower> getTopMilitary(){return topMilitary;} public void setTopMilitary(List<MilitaryPower> v){this.topMilitary=v;}
    public List<NuclearArsenal> getNuclearNations(){return nuclearNations;} public void setNuclearNations(List<NuclearArsenal> v){this.nuclearNations=v;}
    public List<MilitaryBase> getBases(){return bases;} public void setBases(List<MilitaryBase> v){this.bases=v;}
    public RiskScore getRiskScore(){return riskScore;} public void setRiskScore(RiskScore v){this.riskScore=v;}
    public Map<String,Object> getStats(){return stats;} public void setStats(Map<String,Object> v){this.stats=v;}
}
