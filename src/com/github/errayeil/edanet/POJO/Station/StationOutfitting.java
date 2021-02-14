package com.github.errayeil.edanet.POJO.Station;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents a stations outfitting market.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class StationOutfitting implements Serializable {

    @Serial
    private static final long serialVersionUID = 67274554688570782L;

    /**
     * EDSM id for the outfitting market
     */
    public long id;

    /**
     * EDSM id64 for the outfitting market.
     */
    public long id64;

    /**
     * Name of outfitting.
     */
    public String name;

    /**
     * Market id for the station outfitting.
     */
    public long marketId;

    /**
     * Station id
     */
    public long stationId;

    /**
     * Station name
     */
    public String stationName;

    /**
     * Url to the station page on EDSM.
     */
    public String url;

    /**
     * Outfitting items for sale
     */
    public OutfittingItem[] items;

    @Override
    public String toString( ) {
        return "{\"StationOutfitting\":{"
                + "\"id\":\"" + id + "\""
                + ", \"id64\":\"" + id64 + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"marketId\":\"" + marketId + "\""
                + ", \"stationId\":\"" + stationId + "\""
                + ", \"stationName\":\"" + stationName + "\""
                + ", \"url\":\"" + url + "\""
                + ", \"items\":" + Arrays.toString( items )
                + "}}";
    }
}
