package com.github.errayeil.edanet.POJO.Station;

public class OutfittingItem {

    public String id;

    public String name;

    @Override
    public String toString( ) {
        return "{\"OutfittingItem\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + "}}";
    }
}
