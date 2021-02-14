package com.github.errayeil.edanet.POJO.System;

import java.io.Serial;
import java.io.Serializable;

/**
 * Used when getting a list of systems in a specified radius or size (if cube search) of
 * a reference system.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class BasicSystem implements Serializable {

    @Serial
    private static final long serialVersionUID = -3223917284955807737L;

    /**
     *
     */
    public String name;

    /**
     *
     */
    public long bodyCount;

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
    public double distance;

    /**
     *
     */
    public boolean coordsLocked;

    /**
     *
     */
    public SystemCoordinates coords;

    /**
     *
     */
    public SystemPrimaryStar primaryStar;

    /**
     * Returns a string representation of the object. In general, the {@code toString} method returns a string that
     * "textually represents" this object. The result should be a concise but informative representation that is easy
     * for a person to read. It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object} returns a string consisting of the name of the class of
     * which the object is an instance, the at-sign character `{@code @}', and the unsigned hexadecimal representation
     * of the hash code of the object. In other words, this method returns a string equal to the value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString( ) {
        return "{\"BasicSystem\":{"
                + "\"name\":\"" + name + "\""
                + ", \"bodyCount\":\"" + bodyCount + "\""
                + ", \"id\":\"" + id + "\""
                + ", \"id64\":\"" + id64 + "\""
                + ", \"distance\":\"" + distance + "\""
                + ", \"coordsLocked\":\"" + coordsLocked + "\""
                + ", \"coords\":" + coords
                + ", \"primaryStar\":" + primaryStar
                + "}}";
    }
}
