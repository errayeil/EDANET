package com.github.errayeil.edanet.POJO.Station;

import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class State implements Serializable {
    
    /**
     *
     */
    public String state;
    
    /**
     *
     */
    public long trend;

    @Override
    public String toString( ) {
        return "{\"State\":{"
                + "\"state\":\"" + state + "\""
                + ", \"trend\":\"" + trend + "\""
                + "}}";
    }
}
