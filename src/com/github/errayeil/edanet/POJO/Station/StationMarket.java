package com.github.errayeil.edanet.POJO.Station;

import jdk.jfr.Label;

import java.io.Serializable;
import java.util.Arrays;

public class StationMarket implements Serializable {

    public long id;

    public long id64;

    public String systemName;

    public long marketId;

    public long stationId;

    public String stationName;

    public String url;

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
