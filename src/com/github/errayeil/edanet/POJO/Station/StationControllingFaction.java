package com.github.errayeil.edanet.POJO.Station;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class StationControllingFaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 6582160446320797410L;

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
