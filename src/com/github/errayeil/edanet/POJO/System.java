package com.github.errayeil.edanet.POJO;

import java.awt.SystemTray;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class System {
    
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
    public SystemCoordinates coordinates;
    
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
}
