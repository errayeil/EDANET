package com.github.errayeil.edanet.POJO.System;

import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemControllingFaction implements Serializable {

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
    public String allegiance;

    /**
     *
     */
    public String government;


    @Override
    public String toString( ) {
        return "{\"SystemControllingFaction\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"allegiance\":\"" + allegiance + "\""
                + ", \"government\":\"" + government + "\""
                + "}}";
    }
}
