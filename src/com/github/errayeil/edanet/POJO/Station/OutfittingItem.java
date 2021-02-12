package com.github.errayeil.edanet.POJO.Station;

import java.io.Serializable;

public class OutfittingItem implements Serializable {

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
