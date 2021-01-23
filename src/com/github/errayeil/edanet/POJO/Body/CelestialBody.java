package com.github.errayeil.edanet.POJO.Body;

import java.util.Arrays;
import java.util.Map;

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
    public long id64;

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
    public String spectralClass;
    
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
    public Map<String, Double>  atmosphereComposition;

    /**
     *
     */
    public Map<String, Double> solidComposition;

    /**
     *
     */
    public Map<String, Integer> parents;

    /**
     *
     */
    public Map<String, Double> materials;

    /**
     *
     */
    public CelestialBodyDiscovery discovery;
    
    /**
     *
     */
    public CelestialBodyRing[] rings;

    /**
     *
     */
    public String updateTime;

    /**
     * @return
     */
    @Override
    public String toString( ) {
        return "{\"CelestialBody\":{"
                + "\"id\":\"" + id + "\""
                + ", \"id64\":\"" + id64 + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"type\":\"" + type + "\""
                + ", \"subType\":\"" + subType + "\""
                + ", \"distanceToArrival\":\"" + distanceToArrival + "\""
                + ", \"isMainStar\":\"" + isMainStar + "\""
                + ", \"isLandable\":\"" + isLandable + "\""
                + ", \"gravity\":\"" + gravity + "\""
                + ", \"earthMasses\":\"" + earthMasses + "\""
                + ", \"radius\":\"" + radius + "\""
                + ", \"volcanismType\":\"" + volcanismType + "\""
                + ", \"atmosphereType\":\"" + atmosphereType + "\""
                + ", \"terraformingState\":\"" + terraformingState + "\""
                + ", \"spectralClass\":\"" + spectralClass + "\""
                + ", \"isScoopable\":\"" + isScoopable + "\""
                + ", \"age\":\"" + age + "\""
                + ", \"luminosity\":\"" + luminosity + "\""
                + ", \"absoluteMagnitude\":\"" + absoluteMagnitude + "\""
                + ", \"solarMasses\":\"" + solarMasses + "\""
                + ", \"solarRadius\":\"" + solarRadius + "\""
                + ", \"surfaceTemperature\":\"" + surfaceTemperature + "\""
                + ", \"orbitalPeriod\":\"" + orbitalPeriod + "\""
                + ", \"semiMajorAxis\":\"" + semiMajorAxis + "\""
                + ", \"orbitalEccentricity\":\"" + orbitalEccentricity + "\""
                + ", \"orbitalInclination\":\"" + orbitalInclination + "\""
                + ", \"ageOfPeriapsis\":\"" + ageOfPeriapsis + "\""
                + ", \"rotationalPeriod\":\"" + rotationalPeriod + "\""
                + ", \"rotationalPeriodTidallyLocked\":\"" + rotationalPeriodTidallyLocked + "\""
                + ", \"axialTilt\":\"" + axialTilt + "\""
                + ", \"atmosphereComposition\":" + atmosphereComposition
                + ", \"solidComposition\":" + solidComposition
                + ", \"parents\":" + parents
                + ", \"materials\":" + materials
                + ", \"discovery\":" + discovery
                + ", \"rings\":" + Arrays.toString( rings )
                + ", \"updateTime\":\"" + updateTime + "\""
                + "}}";
    }
}
