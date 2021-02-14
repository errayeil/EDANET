package com.github.errayeil.edanet.POJO.Station;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents an outfitting item found in a stations market.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class OutfittingItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 5332900087394553503L;

    /**
     * EDSM id for market outfitting item.
     */
    public String id;

    /**
     * Name of market outfitting item.
     */
    public String name;

    @Override
    public String toString( ) {
        return "{\"OutfittingItem\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + "}}";
    }
}
