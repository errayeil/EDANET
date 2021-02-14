package com.github.errayeil.edanet.POJO.Station;

import jdk.jfr.Label;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class StationMarket implements Serializable {

    @Serial
    private static final long serialVersionUID = 1192824307328472378L;

    /**
     * EDSM id for the station market.
     */
    public long id;

    /**
     * EDSM id64 for the station market.
     */
    public long id64;

    /**
     * The system name the station resides in.
     */
    public String systemName;

    /**
     * The market id for the station. Used to gather station
     * market, outfitting, and shipyard info.
     */
    public long marketId;

    /**
     * EDSM station id.
     */
    public long stationId;

    /**
     * Name of the station.
     */
    public String stationName;

    /**
     * URL to the station page on EDSM.
     */
    public String url;

    /**
     * Commodities the station market sells.
     */
    public MarketCommodity[] commodities;

    @Override
    public String toString( ) {
        return "{\"StationMarket\":{"
                + "\"id\":\"" + id + "\""
                + ", \"id64\":\"" + id64 + "\""
                + ", \"systemName\":\"" + systemName + "\""
                + ", \"marketId\":\"" + marketId + "\""
                + ", \"stationId\":\"" + stationId + "\""
                + ", \"stationName\":\"" + stationName + "\""
                + ", \"url\":\"" + url + "\""
                + ", \"commodities\":" + Arrays.toString( commodities )
                + "}}";
    }
}
