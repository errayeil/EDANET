package com.github.errayeil.edanet.Net;

import com.github.errayeil.edanet.POJO.System.BasicSystem;
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
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Provides the functionality to query via GET to EDSM to receive information on
 * specific systems and their information in Elite:Dangerous.
 *
 * TODO: Review possible optimization opportunities
 * TODO: Possibly make this event based.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAQuery {

    /**
     * Builds queries to tell EDSM what information
     * we are attempting to retrieve.
     */
    private QueryBuilder builder;

    /**
     * A cache to serialize previous searched systems
     * for better lookup times and bandwidth reduction.
     */
    private EDSMCache cache;

    /**
     * The system we will be querying.
     */
    private String targetSystem;

    /**
     * The radius around the targetSystem if we
     * are searching for systems in a radius.
     * Maximum value cannot be greater than 100.
     */
    private String targetRadius;

    /**
     * The size of a cube around the targetSystem if we
     * are searching for systems in a cube.
     * Maximum value cannot be greater than 200.
     */
    private String targetSize;

    /**
     * Constructor.
     */
    public EDAQuery( ) {
        builder = new QueryBuilder( );
        cache = new EDSMCache( true );
    }

    /**
     * Sets the targetSystem for future queries.
     * This will validate the system actually exists before
     * setting the targetSystem.
     *
     * @param systemName The system we will query on in the future.
     * @returns True or false if the target system is valid.
     */
    public boolean setTargetSystem(String systemName) {
        if ( validateSystem( systemName )) {
            targetSystem = systemName;
            return true;
        }

        return false;
    }

    /**
     * Sets the radius for future sphere searches.
     * If the value specified is greater than 100 it will
     * be set to 100 to avoid errors.
     *
     * @param radius The radius around the targetSystem
     */
    public void setTargetRadius(int radius) {
        if (radius > 100) {
            targetRadius = "100";
        } else {
            targetRadius = String.valueOf( radius );
        }
    }

    /**
     * Sets the size for future cube searches.
     * If the value specified is greater than 200 it
     * will be set to 200 to avoid errors.
     *
     * @param size The size of a cube around the targetSystem.
     */
    public void setTargetSize(int size) {
        if (size > 200) {
            targetSize = "200";
        } else {
            targetSize = String.valueOf(  size );
        }
    }


    /**
     * Returns the searched system as a POJO, but with minimal information. No body info, stations, etc. Use
     * getExtendedSystemInfo() to get if you want all the information on a system.
     *
     * @return An EDSMSystem instance with minimal information.
     */
    public EDSMSystem getBasicSystemInfo( ) {
        String systemContent = "";

        boolean existsInCache = cache.isCached( targetSystem );

        if ( existsInCache ) {
            return cache.deserializeSystem( targetSystem );
        }

        builder.setTargetSystem( targetSystem );

        try {
            systemContent = readGetContent( builder.buildForSystem( ) );
        } catch ( URISyntaxException e ) {
            e.printStackTrace( );
        }


        return EDSMParser.parseSystemJson( systemContent );
    }

    /**
     * Gets all the information on a given system EDSM can provide. Depending on the amount of information, this make
     * take a few seconds. Tests average around 7 seconds. This can probably be reduced but for now is acceptable.
     * I will return to this after I have finished everything else and optimized the rest of the library.
     * <p></p>
     * EDSMSystems created from getExtendedSystemInfo() can end up quite large. If converted to json they get upwards
     * to 20k lines. Too many of these in memory will affect performance on some machines. This should mainly be used
     * for "information" windows where the point is allowing all this information accessible by the user.
     * <p></p>
     * There could be some data I am not recording. EDSM uses a similar structure for all their objects, however
     * I have noticed some variables just do not show up in the json structure for certain objects. For example,
     * Planets and Stars use the same structure but star-related variables such as isScoopable do not show up if
     * the object is a Planet. This goes vice versa.
     * I am still doing testing and adding as I come across but if anyone finds anything missing let me know.
     *
     * @return An EDSMSystem with all the information EDSM has on the system searched for.
     */
    public EDSMSystem getExtendedSystemInfo() {
        boolean existsInCache = cache.isCached( targetSystem );

        if ( existsInCache ) {
            return cache.deserializeSystem( targetSystem );
        }

        builder.setTargetSystem( targetSystem );

        EDSMSystem system = null;

        //TODO: Determine if threading the below code will be beneficial. Probably use another cyclic barrier.
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

        /*
         * I use a cyclic barrier to prevent the method from returning. The method will return
         * once all parties report await() to the barrier.
         */
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
     * Uses the target system as the reference system to search for all systems in
     * the specified target radius.
     */
    public List< BasicSystem > searchForSystemsInRadius( ) throws URISyntaxException {
        builder.setTargetSystem( targetSystem );
        builder.setTargetRadius( targetRadius );

        String systemsContent = readGetContent( builder.buildForSystemsInRadius() );
        return EDSMParser.parseSystemsJson( systemsContent );
    }

    /**
     *
     */
    public List< BasicSystem> searchForSystemsInCube(  ) throws URISyntaxException {
        builder.setTargetSystem( targetSystem );
        builder.setTargetSize( targetSize );

        String systemsContent = readGetContent( builder.buildForSystemsInCube());
        return EDSMParser.parseSystemsJson( systemsContent );
    }

    /**
     * Validates the system exists by doing a quick and small query
     * to EDSM with just the system name. Preventing a system name that
     * doesn't exist from going through prevents errors.
     */
    private boolean validateSystem(String systemName) {
        builder.setTargetSystem( systemName );

        String content = null;
        try {
            content = readGetContent( builder.buildForSystemName() );
        } catch ( URISyntaxException e ) {
            e.printStackTrace( );
        }

        if (content != null && !content.equals( "{}" ) && !content.equals( "[]" )) {
            System.out.println( "Valid system" );
            return true;
        }

        return false;
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
