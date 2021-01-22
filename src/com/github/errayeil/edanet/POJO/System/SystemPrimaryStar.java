package com.github.errayeil.edanet.POJO.System;

import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemPrimaryStar implements Serializable {
    
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
        return "SystemPrimaryStar{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", isScoopable=" + isScoopable +
                '}';
    }
}
