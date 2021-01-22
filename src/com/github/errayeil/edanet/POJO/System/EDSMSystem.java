package com.github.errayeil.edanet.POJO.System;

import com.github.errayeil.edanet.POJO.Body.CelestialBody;

import java.util.Arrays;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDSMSystem {
    
    /**
     *
     */
    public long id;

    /**
     *
     */
    public long id64;
    
    /**
     *
     */
    public String name;

    /**
     *
     */
    public boolean coordsLocked;

    /**
     *
     */
    public boolean requirePermit;

    /**
     *
     */
    public String permitName;

    /**
     *
     */
    public SystemControllingFaction controllingFaction;

    /**
     *
     */
    public SystemCoordinates coords;
    
    /**
     *
     */
    public SystemInformation information;
    
    /**
     *
     */
    public SystemPrimaryStar primaryStar;
    
    /**
     *
     */
    public CelestialBody[] bodies;
    
    /**
     *
     */
    public SystemStation[] stations;
    
    /**
     *
     */
    public SystemFaction[] factions;
    
    /**
     *
     */
    public SystemScanValues scanValues;
    
    /**
     *
     */
    public SystemTraffic traffic;
    
    /**
     *
     */
    public SystemDeaths deaths;

    /**
     *
     */
    @Override
    public String toString( ) {
        return "EDSMSystem{" +
                "id=" + id +
                ", id64=" + id64 +
                ", name='" + name + '\'' +
                ", coordsLocked=" + coordsLocked +
                ", requirePermit=" + requirePermit +
                ", permitName='" + permitName + '\'' +
                ", controllingFaction=" + controllingFaction +
                ", coords=" + coords +
                ", information=" + information +
                ", primaryStar=" + primaryStar +
                ", bodies=" + Arrays.toString( bodies ) +
                ", stations=" + Arrays.toString( stations ) +
                ", factions=" + Arrays.toString( factions ) +
                ", scanValues=" + scanValues +
                ", traffic=" + traffic +
                ", deaths=" + deaths +
                '}';
    }
}
