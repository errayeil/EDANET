package com.github.errayeil.edanet.POJO.System;

import java.io.Serial;
import java.io.Serializable;

/**
 * Holds misc. info on a system.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemInformation implements Serializable {

    @Serial
    private static final long serialVersionUID = -4869298515231687787L;

    /**
     *
     */
    public String allegiance;

    /**
     *
     */
    public String government;

    /**
     *
     */
    public String faction;

    /**
     *
     */
    public String factionState;

    /**
     *
     */
    public String security;

    /**
     *
     */
    public String economy;

    /**
     *
     */
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
