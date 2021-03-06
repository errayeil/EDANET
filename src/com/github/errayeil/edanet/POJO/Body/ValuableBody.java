package com.github.errayeil.edanet.POJO.Body;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class ValuableBody implements Serializable {

    @Serial
    private static final long serialVersionUID = 5571161284822838031L;
    
    /**
     * Celestial body EDSM id.
     */
    public long bodyId;
    
    /**
     * Celestial body name.
     */
    public String bodyName;
    
    /**
     * Distance from system arrival point
     */
    public long distance;
    
    /**
     * Maximum scan value.
     */
    public long valueMax;

    @Override
    public String toString( ) {
        return "{\"ValuableBody\":{"
                + "\"bodyId\":\"" + bodyId + "\""
                + ", \"bodyName\":\"" + bodyName + "\""
                + ", \"distance\":\"" + distance + "\""
                + ", \"valueMax\":\"" + valueMax + "\""
                + "}}";
    }
}
