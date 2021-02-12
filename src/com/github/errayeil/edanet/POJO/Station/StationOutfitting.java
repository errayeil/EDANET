package com.github.errayeil.edanet.POJO.Station;

import java.io.Serializable;
import java.util.Arrays;

public class StationOutfitting implements Serializable {

    public long id;

    public long id64;

    public String name;

    public long marketId;

    public long stationId;

    public String stationName;

    public String url;

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
