package com.github.errayeil.edanet.POJO.System;

import java.util.Map;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemDeaths {
    
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
    
    /**
     *
     */
    public Map<String, Long> deaths;

    @Override
    public String toString( ) {
        return "SystemDeaths{" +
                "id=" + id +
                ", id64=" + id64 +
                ", name='" + name + '\'' +
                ", deaths=" + deaths +
                '}';
    }
}
