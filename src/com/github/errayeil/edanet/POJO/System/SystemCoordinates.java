package com.github.errayeil.edanet.POJO.System;

import java.io.Serial;
import java.io.Serializable;

/**
 * Holds the x, y, and z coordinates of the system.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemCoordinates implements Serializable {

    @Serial
    private static final long serialVersionUID = -6076111260389024792L;

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
