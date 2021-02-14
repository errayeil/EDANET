package com.github.errayeil.edanet.POJO.System;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents the primary star of the system, usually the one players see when exiting super cruise.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemPrimaryStar implements Serializable {

    @Serial
    private static final long serialVersionUID = 8305846823565072204L;

    /**
     *
     */
    public String type;
    
    /**
     *
     */
    public String name;
    
    /**
     *
     */
    public boolean isScoopable;

    @Override
    public String toString( ) {
        return "{\"SystemPrimaryStar\":{"
                + "\"type\":\"" + type + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"isScoopable\":\"" + isScoopable + "\""
                + "}}";
    }
}
