package com.github.errayeil.edanet.POJO.System;

import com.github.errayeil.edanet.POJO.Station.StationControllingFaction;
import com.github.errayeil.edanet.POJO.Station.StationMarket;
import com.github.errayeil.edanet.POJO.Station.StationOutfitting;
import com.github.errayeil.edanet.POJO.Station.StationShipyard;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SystemStation {
    
    /**
     *
     */
    public long id;

    /**
     *
     */
    public long marketId;
    
    /**
     *
     */
    public String name;
    
    /**
     *
     */
    public String type;
    
    /**
     *
     */
    public long distanceToArrival;
    
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
    public String economy;
    
    /**
     *
     */
    public boolean haveMarket;
    
    /**
     *
     */
    public boolean haveShipyard;
    
    /**
     *
     */
    public StationControllingFaction stationControllingFaction;

    /**
     *
     */
    public StationShipyard shipyard;

    /**
     *
     */
    public StationMarket market;

    /**
     *
     * @return
     */
    public StationOutfitting outfitting;

    @Override
    public String toString( ) {
        return "{\"SystemStation\":{"
                + "\"id\":\"" + id + "\""
                + ", \"marketId\":\"" + marketId + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"type\":\"" + type + "\""
                + ", \"distanceToArrival\":\"" + distanceToArrival + "\""
                + ", \"allegiance\":\"" + allegiance + "\""
                + ", \"government\":\"" + government + "\""
                + ", \"economy\":\"" + economy + "\""
                + ", \"haveMarket\":\"" + haveMarket + "\""
                + ", \"haveShipyard\":\"" + haveShipyard + "\""
                + ", \"stationControllingFaction\":" + stationControllingFaction
                + ", \"shipyard\":" + shipyard
                + ", \"market\":" + market
                + ", \"outfitting\":" + outfitting
                + "}}";
    }
}
