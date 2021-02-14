package com.github.errayeil.edanet.POJO.Body;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class CelestialBodyDiscovery implements Serializable {

    @Serial
    private static final long serialVersionUID = 7301136701504233143L;

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
