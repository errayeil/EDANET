package com.github.errayeil.edanet.POJO.Body;

import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class CelestialBodyRing implements Serializable {
    
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
    public long mass;
    
    /**
     *
     */
    public long innerRadius;
    
    /**
     *
     */
    public long outerRadius;

    @Override
    public String toString( ) {
        return "{\"CelestialBodyRing\":{"
                + "\"name\":\"" + name + "\""
                + ", \"type\":\"" + type + "\""
                + ", \"mass\":\"" + mass + "\""
                + ", \"innerRadius\":\"" + innerRadius + "\""
                + ", \"outerRadius\":\"" + outerRadius + "\""
                + "}}";
    }
}
