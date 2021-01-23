package com.github.errayeil.edanet.POJO.Station;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class State {
    
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
