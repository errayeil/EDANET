package com.github.errayeil.edanet.POJO.System;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemDeaths implements Serializable {
    
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
