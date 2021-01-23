package com.github.errayeil.edanet.POJO.System;

import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemInformation implements Serializable {

    public String allegiance;
    
    public String government;
    
    public String faction;
    
    public String factionState;
    
    public String security;
    
    public String economy;
    
    public long population;

    @Override
    public String toString( ) {
        return "{\"SystemInformation\":{"
                + "\"allegiance\":\"" + allegiance + "\""
                + ", \"government\":\"" + government + "\""
                + ", \"faction\":\"" + faction + "\""
                + ", \"factionState\":\"" + factionState + "\""
                + ", \"security\":\"" + security + "\""
                + ", \"economy\":\"" + economy + "\""
                + ", \"population\":\"" + population + "\""
                + "}}";
    }
}
