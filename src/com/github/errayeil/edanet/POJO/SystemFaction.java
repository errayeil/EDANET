package com.github.errayeil.edanet.POJO;

import java.util.Map;

/**
 * @author Steven Frizell
 * @verision HIP 2
 * @since HIP 2
 */
public class SystemFaction {
    
    /**
     *
     */
    public long id;
    
    /**
     *
     */
    public String name;
    
    /**
     *
     */
    public String allegiance;
    
    /**
     *
     */
    public String government;
    
    /**
     *
     */
    public double influence;
    
    /**
     *
     */
    public String state;
    
    /**
     *
     */
    public boolean isPlayer;
    
    /**
     *
     */
    public Map<Long, Double> influenceHistory;
    
    /**
     *
     */
    public Map<Long, String> stateHistory;
    
    /**
     *
     */
    public State[] recoveringStates;
    
    /**
     *
     */
    public State[] pendingStates;
}
