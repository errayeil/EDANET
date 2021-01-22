package com.github.errayeil.edanet.Net;

import com.github.errayeil.edanet.EDSMURLS;
import com.github.errayeil.edanet.POJO.System.EDSMSystem;
import com.github.errayeil.edanet.POJO.System.SystemCoordinates;
import com.github.errayeil.edanet.POJO.System.SystemStation;
import com.github.errayeil.edanet.Parsers.EDSMParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 *
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDSMQuery {

    /**
     *
     */
    private QueryBuilder builder;

    /**
     *
     */
    public EDSMQuery() {
         builder = new QueryBuilder( );
    }

    /**
     *
     * @param systemName
     */
    public EDSMSystem searchForSystem( final String systemName) throws URISyntaxException, IOException {
        if (systemName.isEmpty() || systemName.isBlank())
            throw new IllegalArgumentException( "System name specified is empty." );
        builder.setBuilderURI( EDSMURLS.edsm_system_url );

        builder.addBuilderParameter( "systemName", systemName );
        builder.addBuilderParameter( "showInformation", "1" );
        builder.addBuilderParameter( "showId", "1" );
        builder.addBuilderParameter( "showCoordinates", "1" );
        builder.addBuilderParameter( "showPermit", "1" );
        builder.addBuilderParameter( "showPrimaryStar", "1" );

        String systemContent = readContent( builder.build() );
        EDSMSystem system = EDSMParser.parseSystemJson( systemContent );

        builder.clear();
        builder.setBuilderURI( EDSMURLS.edsm_bodies_url );
        builder.addBuilderParameter( "systemName", system.name );

        String systemBodiesContent = readContent( builder.build() );
        system.bodies = EDSMParser.parseBodiesJson( systemBodiesContent );

        builder.clear();
        builder.setBuilderURI( EDSMURLS.edsm_stations_url );
        builder.addBuilderParameter( "systemName", system.name);

        String stationsContent = readContent( builder.build() );
        system.stations = EDSMParser.parseStationsJson( stationsContent );

        builder.clear();;
        builder.setBuilderURI( EDSMURLS.edsm_factions_url );
        builder.addBuilderParameter( "systemName", system.name );

        String factionsContent = readContent( builder.build() );
        system.factions = EDSMParser.parseStationFactionJson( factionsContent );
        system.controllingFaction = EDSMParser.parseSystemFactionJson( factionsContent );

        builder.clear();
        builder.setBuilderURI( EDSMURLS.edsm_estimated_value_url );
        builder.addBuilderParameter( "systemName", system.name );

        String value = readContent( builder.build() );
        system.scanValues = EDSMParser.parseSystemScanValuesJson( value );

        builder.clear();
        builder.setBuilderURI( EDSMURLS.edsm_traffic_url );
        builder.addBuilderParameter( "systemName", system.name );

        String trafficContent = readContent( builder.build() );
        system.traffic = EDSMParser.parseSystemTrafficJson( trafficContent );

        builder.clear();
        builder.setBuilderURI( EDSMURLS.edsm_deaths_url );
        builder.addBuilderParameter( "systemName", system.name );

        String deathsContent = readContent( builder.build() );
        system.deaths = EDSMParser.parseSystemDeathsJson( deathsContent );

        for ( SystemStation station : system.stations) {
            builder.clear();
            builder.setBuilderURI( EDSMURLS.edsm_shipyard_url );
            builder.addBuilderParameter( "marketId", String.valueOf( station.marketId ) );

            String shipyardContent = readContent( builder.build( ) );
            station.shipyard = EDSMParser.parseStationShipyardJson( shipyardContent );
        }

        return system;
    }

    /**
     *
     * @param referenceSystem
     * @param radius
     * throws IllegalArgumentException Thrown if the reference system is empty or the radius is greater than 100.
     */
    public void searchForSystemsInRadius(final String referenceSystem, final int radius) throws URISyntaxException {
        if (referenceSystem.isEmpty() || referenceSystem.isBlank())
            throw new IllegalArgumentException( "Specified reference system is empty." );
        if (radius > 100)
            throw new IllegalArgumentException( "Radius cannot be greater than 100." );
        builder.setBuilderURI( EDSMURLS.edsm_sphere_systems_url );
    }

    /**
     *
     * @param coordinates
     * @param radius
     * throws IllegalArgumentException Thrown if the reference coordinates are empty or the radius is greater than 100.
     */
    public void searchForSystemsInRadius( final SystemCoordinates coordinates, final int radius) throws URISyntaxException {
        if (coordinates == null)
            throw new IllegalArgumentException( "Specified coordinates are null." );

        if (radius > 100)
            throw new IllegalArgumentException( "Radius cannot be greater than 100." );
        builder.setBuilderURI( EDSMURLS.edsm_sphere_systems_url );
    }

    /**
     *
     * @param referenceSystem
     * @param size
     * throws IllegalArgumentException Thrown if the reference system is empty or the cube size is greater than 200.
     */
    public void searchForSystemsInCube(final String referenceSystem, final int size) throws URISyntaxException {
        if (referenceSystem.isEmpty() || referenceSystem.isBlank())
            throw new IllegalArgumentException( "Specified reference system is empty." );

        if (size > 200)
            throw new IllegalArgumentException( "Cube size cannot be greater than 200." );
        builder.setBuilderURI( EDSMURLS.edsm_cube_systems_url );
    }

    /**
     *
     * @param coordinates
     * @param size
     * throws IllegalArgumentException Thrown if the reference coordinates are empty or the size is greater than 200.
     */
    public void searchForSystemsInCube(final SystemCoordinates coordinates, final int size) throws URISyntaxException {
        if (coordinates == null)
            throw new IllegalArgumentException( "Specified coordinates are null." );

        if (size > 200)
            throw new IllegalArgumentException( "Cube size cannot be greater than 200." );
        builder.setBuilderURI( EDSMURLS.edsm_cube_systems_url );
    }

    /**
     *
     * @param get
     * @return
     */
    private String readContent( HttpGet get ) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute( get );
        HttpEntity entity = response.getEntity();

        String content = "";

        try ( response; BufferedInputStream bis = new BufferedInputStream( entity.getContent( ) );
              ByteArrayOutputStream buf = new ByteArrayOutputStream( ) ) {

            int result = 0;
            while ( ( result = bis.read( ) ) != -1 ) {
                buf.write( ( byte ) result );
            }

            if ( buf.size( ) != 0 ) {
                content = buf.toString( StandardCharsets.UTF_8 );
            }
        }

        EntityUtils.consume(entity);

        return content;
    }
}
