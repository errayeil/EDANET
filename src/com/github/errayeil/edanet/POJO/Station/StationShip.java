package com.github.errayeil.edanet.POJO.Station;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class StationShip {

    public long id;

    public String name;

    @Override
    public String toString( ) {
        return "StationShip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
