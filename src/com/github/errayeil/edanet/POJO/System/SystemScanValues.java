package com.github.errayeil.edanet.POJO.System;

import com.github.errayeil.edanet.POJO.Body.ValuableBody;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemScanValues implements Serializable {

    @Serial
    private static final long serialVersionUID = -7322261638535939760L;

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
    public String url;
    
    /**
     *
     */
    public long estimatedValue;
    
    /**
     *
     */
    public long estimatedValueMapped;
    
    /**
     *
     */
    public ValuableBody[] valuableBodies;

    @Override
    public String toString( ) {
        return "{\"SystemScanValues\":{"
                + "\"id\":\"" + id + "\""
                + ", \"id64\":\"" + id64 + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"url\":\"" + url + "\""
                + ", \"estimatedValue\":\"" + estimatedValue + "\""
                + ", \"estimatedValueMapped\":\"" + estimatedValueMapped + "\""
                + ", \"valuableBodies\":" + Arrays.toString( valuableBodies )
                + "}}";
    }
}
