package com.github.errayeil.edanet.POJO.System;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * Holds the total, daily, and weekly deaths that have occurred in the system.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemDeaths implements Serializable {

    @Serial
    private static final long serialVersionUID = -5566510785777243024L;

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
    public int totalDeaths;

    /**
     *
     */
    public int weeklyDeaths;

    /**
     *
     */
    public int dailyDeaths;


    @Override
    public String toString( ) {
        return "{\"SystemDeaths\":{"
                + "\"id\":\"" + id + "\""
                + ", \"id64\":\"" + id64 + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"totalDeaths\":\"" + totalDeaths + "\""
                + ", \"weeklyDeaths\":\"" + weeklyDeaths + "\""
                + ", \"dailyDeaths\":\"" + dailyDeaths + "\""
                + "}}";
    }
}
