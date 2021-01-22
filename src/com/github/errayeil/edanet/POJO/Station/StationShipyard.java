package com.github.errayeil.edanet.POJO.Station;

import java.util.Arrays;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class StationShipyard {

    public long id;

    public long id64;

    public String systemName;

    public long marketId;

    public long stationId;

    public String stationName;

    public String url;

    public StationShip[] ships;

    @Override
    public String toString( ) {
        return "StationShipyard{" +
                "id=" + id +
                ", id64=" + id64 +
                ", systemName='" + systemName + '\'' +
                ", marketId=" + marketId +
                ", stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", url='" + url + '\'' +
                ", ships=" + Arrays.toString( ships ) +
                '}';
    }
}
