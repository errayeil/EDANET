package com.github.errayeil.edanet.POJO.Station;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class State implements Serializable {

    @Serial
    private static final long serialVersionUID = 742829621473081860L;

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
