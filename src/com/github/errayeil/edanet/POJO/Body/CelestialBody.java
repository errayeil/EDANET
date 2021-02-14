package com.github.errayeil.edanet.POJO.Body;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class CelestialBody implements Serializable {


    @Serial
    private static final long serialVersionUID = -2695952292765667560L;

    /**
     * The EDSM id for a celestial body.
     */
    public long id;

    /**
     * The EDSM id64 for a celestial body.
     */
    public long id64;

    /**
     * The name of the celestial body.
     */
    public String name;
    
    /**
     * The type of the celestial body.
     * TODO: Figure out if I can find a static list of types.
     */
    public String type;
    
    /**
     * The subType of celestial body.
     * TODO: Figure out if I can find a static list of subtypes.
     */
    public String subType;
    
    /**
     * Distance of arrival from start point of system. I think.
     */
    public long distanceToArrival;
    
    /**
     * If the star is the primary star or not. <br>
     * Note: If the CelestialBody is a planet this will be false or null.
     */
    public boolean isMainStar;
    
    /**
     * If the planet is landable.
     * This will be false or null if its a star.
     */
    public boolean isLandable;
    
    /**
     * The gravity of the body.
     */
    public double gravity;
    
    /**
     * The earthmasses of the body.
     */
    public double earthMasses;
    
    /**
     * The size or radius of the body.
     */
    public double radius;
    
    /**
     * Volcanismtype of the body.
     */
    public String volcanismType;
    
    /**
     * Atmosphere type of the body.
     */
    public String atmosphereType;
    
    /**
     * If the body is terraformable.
     */
    public String terraformingState;

    /**
     * Spectral class of the body.
     * Whatever that means.
     */
    public String spectralClass;
    
    /**
     * If the star is scoopable for fuel.
     */
    public boolean isScoopable;
    
    /**
     * The galatic age of the body.
     */
    public long age;
    
    /**
     * Brightness of the body.
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
