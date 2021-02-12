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
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
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
    private EDSMCache cache;

    /**
     *
     */
    public EDSMQuery( ) {
        builder = new QueryBuilder( );
        cache = new EDSMCache( true );
    }


    /**
     * Returns the searched system as a POJO, but with minimal information. No body info, stations, etc. Use
     * getAllInfoFor if you want all the information on a system.
     *
     * @param systemName
     *
     * @return
     */
    public EDSMSystem getBasicSystemInfo( final String systemName ) {
        if ( systemName.isEmpty( ) || systemName.isBlank( ) )
            throw new IllegalArgumentException( "System name specified is empty." );

        String systemContent = "";

        boolean existsInCache = cache.validateSystemName( systemName );

        if ( existsInCache ) {
            return cache.deserializeSystem( systemName );
        }

        builder.setTargetSystem( systemName );

        try {
            systemContent = readGetContent( builder.buildForSystem( ) );
        } catch ( URISyntaxException e ) {
            e.printStackTrace( );
        }


        return EDSMParser.parseSystemJson( systemContent );
    }

    /**
     * Gets all the information on a given system EDSM can provide. Depending on the amount of information, this make
     * take a few seconds.`
     *
     * @param systemName
     */
    public EDSMSystem getExtendedSystemInfo( final String systemName ) {
        if ( systemName.isEmpty( ) || systemName.isBlank( ) )
            throw new IllegalArgumentException( "System name specified is empty." );

        boolean existsInCache = cache.validateSystemName( systemName );

        if ( existsInCache ) {
            return cache.deserializeSystem( systemName );
        }

        builder.setTargetSystem( systemName );

        EDSMSystem system = null;

        try {
            String systemContent = readGetContent( builder.buildForSystem( ) );
            system = EDSMParser.parseSystemJson( systemContent );

            String systemBodiesContent = readGetContent( builder.buildForSystemBodies( ) );
            system.bodies = EDSMParser.parseBodiesJson( systemBodiesContent );

            String stationsContent = readGetContent( builder.buildForSystemStations( ) );
            system.stations = EDSMParser.parseStationsJson( stationsContent );

            String factionsContent = readGetContent( builder.buildForSystemFactions( ) );
            system.factions = EDSMParser.parseStationFactionJson( factionsContent );
            system.controllingFaction = EDSMParser.parseSystemFactionJson( factionsContent );

            String value = readGetContent( builder.buildForSystemScanValues( ) );
            system.scanValues = EDSMParser.parseSystemScanValuesJson( value );

            String trafficContent = readGetContent( builder.buildForSystemTraffic( ) );
            system.traffic = EDSMParser.parseSystemTrafficJson( trafficContent );

            String deathsContent = readGetContent( builder.buildForSystemDeaths( ) );
            system.deaths = EDSMParser.parseSystemDeathsJson( deathsContent );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (system == null) {
            return new EDSMSystem();
        }

        CyclicBarrier barrier = new CyclicBarrier( system.stations.length + 1 );

        for ( SystemStation station : system.stations ) {

            Runnable run = ( ) -> {
                String shipyardContent = "";
                String outfittingContent = "";
                String marketContent = "";

                try {
                    builder.setTargetMarketId( String.valueOf( station.marketId ) );

                    marketContent = readGetContent( builder.buildForStationMarket( ) );
                    shipyardContent = readGetContent( builder.buildForMarketShipyard( ) );
                    outfittingContent = readGetContent( builder.buildForMarketOutfitting( ) );
                } catch ( URISyntaxException e ) {
                    e.printStackTrace( );
                }

                station.shipyard = EDSMParser.parseStationShipyardJson( shipyardContent );
                station.outfitting = EDSMParser.parseStationOutfittingJson( outfittingContent );
                station.market = EDSMParser.parseStationMarketJson( marketContent );

                try {
                    System.out.println( "Awaiting" );
                    barrier.await( );
                } catch ( InterruptedException | BrokenBarrierException e ) {
                    e.printStackTrace( );
                }
            };

            Thread thread = new Thread( run );
            thread.setName( "Station-thread-" + station.name );
            thread.start( );
        }


        try {
            System.out.println( "Awaiting before return." );
            barrier.await( );
        } catch ( InterruptedException | BrokenBarrierException e ) {
            e.printStackTrace( );
        }
        return system;
    }

    /**
     * @param referenceSystem
     * @param radius          throws IllegalArgumentException Thrown if the reference system is empty or the radius is
     *                        greater than 100.
     */
    public void searchForSystemsInRadius( final String referenceSystem, final int radius ) throws URISyntaxException {
        if ( referenceSystem.isEmpty( ) || referenceSystem.isBlank( ) )
            throw new IllegalArgumentException( "Specified reference system is empty." );
        if ( radius > 100 )
            throw new IllegalArgumentException( "Radius cannot be greater than 100." );


    }

    /**
     * @param coordinates
     * @param radius      throws IllegalArgumentException Thrown if the reference coordinates are empty or the radius is
     *                    greater than 100.
     */
    public void searchForSystemsInRadius( final SystemCoordinates coordinates, final int radius ) throws URISyntaxException {
        if ( coordinates == null )
            throw new IllegalArgumentException( "Specified coordinates are null." );

        if ( radius > 100 )
            throw new IllegalArgumentException( "Radius cannot be greater than 100." );

    }

    /**
     * @param referenceSystem
     * @param size            throws IllegalArgumentException Thrown if the reference system is empty or the cube size
     *                        is greater than 200.
     */
    public void searchForSystemsInCube( final String referenceSystem, final int size ) throws URISyntaxException {
        if ( referenceSystem.isEmpty( ) || referenceSystem.isBlank( ) )
            throw new IllegalArgumentException( "Specified reference system is empty." );

        if ( size > 200 )
            throw new IllegalArgumentException( "Cube size cannot be greater than 200." );

    }

    /**
     * @param coordinates
     * @param size        throws IllegalArgumentException Thrown if the reference coordinates are empty or the size is
     *                    greater than 200.
     */
    public void searchForSystemsInCube( final SystemCoordinates coordinates, final int size ) throws URISyntaxException {
        if ( coordinates == null )
            throw new IllegalArgumentException( "Specified coordinates are null." );

        if ( size > 200 )
            throw new IllegalArgumentException( "Cube size cannot be greater than 200." );

    }

    /**
     * Acquires an input stream from the HttpGet and reads the content.
     *
     * @param get
     *
     * @return
     */
    private String readGetContent( HttpGet get ) {
        String content = "";

        try {
            CloseableHttpClient client = HttpClients.createDefault( );
            CloseableHttpResponse response = client.execute( get );
            HttpEntity entity = response.getEntity( );

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

            EntityUtils.consume( entity );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
