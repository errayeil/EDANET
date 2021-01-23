package com.github.errayeil.edanet.POJO.Body;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class CelestialBodyDiscovery {

    /**
     * Discovery commander.
     */
    public String commander;

    /**
     * Date of discovery.
     */
    public String date;

    @Override
    public String toString( ) {
        return "{\"CelestialBodyDiscovery\":{"
                + "\"commander\":\"" + commander + "\""
                + ", \"date\":\"" + date + "\""
                + "}}";
    }
}
