package com.github.errayeil.edanet.POJO.Body;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class ValuableBody {
    
    /**
     *
     */
    public long bodyId;
    
    /**
     *
     */
    public String bodyName;
    
    /**
     *
     */
    public long distance;
    
    /**
     *
     */
    public long valueMax;

    @Override
    public String toString( ) {
        return "ValuableBody{" +
                "bodyId=" + bodyId +
                ", bodyName='" + bodyName + '\'' +
                ", distance=" + distance +
                ", valueMax=" + valueMax +
                '}';
    }
}
