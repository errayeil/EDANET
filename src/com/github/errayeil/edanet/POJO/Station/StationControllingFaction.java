package com.github.errayeil.edanet.POJO.Station;

import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class StationControllingFaction implements Serializable {
    
    /**
     *
     */
    public long id;
    
    /**
     *
     */
    public String name;

    @Override
    public String toString( ) {
        return "{\"StationControllingFaction\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + "}}";
    }
}
