package com.github.errayeil.edanet.POJO.System;

import java.io.Serializable;

/**
 * @author Steven Frizell
 * @verison HIP 2
 * @since HIP 2
 */
public class SystemCoordinates implements Serializable {
    
    /**
     * The x coordinate of the system.
     */
    public double x;
    
    /**
     * The y coordinate of the system.
     */
    public double y;
    
    /**
     * The z coordinate of the system.
     */
    public double z;

    @Override
    public String toString( ) {
        return "{\"SystemCoordinates\":{"
                + "\"x\":\"" + x + "\""
                + ", \"y\":\"" + y + "\""
                + ", \"z\":\"" + z + "\""
                + "}}";
    }
}
