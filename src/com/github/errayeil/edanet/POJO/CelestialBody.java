package com.github.errayeil.edanet.POJO;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class CelestialBody {
    
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
    public String type;
    
    /**
     *
     */
    public String subType;
    
    /**
     *
     */
    public long distanceToArrival;
    
    /**
     *
     */
    public boolean isMainStar;
    
    /**
     *
     */
    public boolean isLandable;
    
    /**
     *
     */
    public double gravity;
    
    /**
     *
     */
    public double earthMasses;
    
    /**
     *
     */
    public double radius;
    
    /**
     *
     */
    public String volcanismType;
    
    /**
     *
     */
    public String atmosphereType;
    
    /**
     *
     */
    public String terraformingState;
    
    /**
     *
     */
    public boolean isScoopable;
    
    /**
     *
     */
    public long age;
    
    /**
     *
     */
    public String luminosity;
    
    /**
     *
     */
    public double absoluteMagnitude;
    
    /**
     *
     */
    public double solarMasses;
    
    /**
     *
     */
    public double solarRadius;
    
    /**
     *
     */
    public long surfaceTemperature;
    
    /**
     *
     */
    public double orbitalPeriod;
    
    /**
     *
     */
    public double semiMajorAxis;
    
    /**
     *
     */
    public double orbitalEccentricity;
    
    /**
     *
     */
    public double orbitalInclination;
    
    /**
     *
     */
    public double ageOfPeriapsis;
    
    /**
     *
     */
    public double rotationalPeriod;
    
    /**
     *
     */
    public boolean rotationalPeriodTidallyLocked;
    
    /**
     *
     */
    public double axialTilt;
    
    /**
     *
     */
    public CelestialBodyRing[] rings;
}
