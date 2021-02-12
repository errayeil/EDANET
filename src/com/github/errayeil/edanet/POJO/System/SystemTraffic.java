package com.github.errayeil.edanet.POJO.System;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemTraffic implements Serializable {
    
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
    public int total;

    /**
     *
     */
    public int week;

    /**
     *
     */
    public int day;
    
    /**
     *
     */
    public Map<String, Integer> breakdown;

    @Override
    public String toString( ) {
        return "{\"SystemTraffic\":{"
                + "\"id\":\"" + id + "\""
                + ", \"id64\":\"" + id64 + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"total\":\"" + total + "\""
                + ", \"week\":\"" + week + "\""
                + ", \"day\":\"" + day + "\""
                + ", \"breakdown\":" + breakdown
                + "}}";
    }
}
