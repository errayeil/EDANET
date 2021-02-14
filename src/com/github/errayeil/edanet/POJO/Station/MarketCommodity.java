package com.github.errayeil.edanet.POJO.Station;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class MarketCommodity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1888766370617586160L;

    /**
     *
     */
    public String id;

    /**
     *
     */
    public String name;

    /**
     *
     */
    public long buyPrice;

    /**
     *
     */
    public long stock;

    /**
     *
     */
    public long sellPrice;

    /**
     *
     */
    public long demand;


    @Override
    public String toString( ) {
        return "{\"MarketCommodity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"buyPrice\":\"" + buyPrice + "\""
                + ", \"stock\":\"" + stock + "\""
                + ", \"sellPrice\":\"" + sellPrice + "\""
                + ", \"demand\":\"" + demand + "\""
                + ", \"stockBracket\":\"" + stockBracket + "\""
                + "}}";
    }

    /**
     *
     */
    public long stockBracket;
}
