package com.github.errayeil.edanet.POJO.Station;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a purchasable ship in a station shipyard.
 * TODO: Use this data to get more ship information from a secondary source.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class ShipyardShip implements Serializable {

    @Serial
    private static final long serialVersionUID = -3103427207739021422L;

    /**
     * EDSM id for the ship.
     */
    public long id;

    /**
     * The name of the ship.
     */
    public String name;

    @Override
    public String toString( ) {
        return "{\"StationShip\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + "}}";
    }
}
