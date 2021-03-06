package com.github.errayeil.edanet.POJO.System;

import com.github.errayeil.edanet.POJO.Station.State;
import com.github.errayeil.edanet.POJO.Station.StationControllingFaction;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * Holds the data of a systems faction.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemFaction implements Serializable {

    @Serial
    private static final long serialVersionUID = -988970414516458586L;

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
    public Map<String, Double> influenceHistory;
    
    /**
     *
     */
    public Map<String, String> stateHistory;
    
    /**
     *
     */
    public State[] recoveringStates;
    
    /**
     *
     */
    public State[] pendingStates;

    /**
     *
     */
    public StationControllingFaction controllingFaction;


    @Override
    public String toString( ) {
        return "{\"SystemFaction\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"allegiance\":\"" + allegiance + "\""
                + ", \"government\":\"" + government + "\""
                + ", \"influence\":\"" + influence + "\""
                + ", \"state\":\"" + state + "\""
                + ", \"isPlayer\":\"" + isPlayer + "\""
                + ", \"influenceHistory\":" + influenceHistory
                + ", \"stateHistory\":" + stateHistory
                + ", \"recoveringStates\":" + Arrays.toString( recoveringStates )
                + ", \"pendingStates\":" + Arrays.toString( pendingStates )
                + ", \"controllingFaction\":" + controllingFaction
                + "}}";
    }
}
