package com.github.errayeil.edanet.Net;

import com.github.errayeil.edanet.EDSMURLS;
import com.github.errayeil.edanet.POJO.System.EDSMSystem;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDSMCache {

    /**
     *
     */
    private QueryBuilder builder;

    /**
     * System names recently searched.
     */
    private List< String > recentlySearched;

    /**
     * The absolute path of files storing json data
     * from searches on EDSM.
     */
    private Map< String, String > fileCaches;

    /**
     * If storing retrieved json from searches is enabled.
     */
    private boolean fileCacheEnabled = true;

    /**
     * The maximum system names stored in the recently searched
     * list.
     */
    private int maxMemoryCacheSize = 50;

    /**
     * The maximum files allowed on disk for serialized objects.
     */
    private int maxFileCacheSize = 50;

    /**
     *
     */
    public EDSMCache() {
        this(true);
    }

    /**
     *
     * @param enableFileCache
     */
    public EDSMCache(boolean enableFileCache) {
        fileCacheEnabled = enableFileCache;
        builder = new QueryBuilder();
        recentlySearched = new ArrayList<>(  );
        fileCaches = new ConcurrentHashMap<>();
    }

    /**
     *
     * @param enable
     */
    public void enableFileCache(boolean enable) {
        fileCacheEnabled = enable;

    }

    /**
     *
     * @param max
     */
    public void setMaxMemoryCacheSize(final int max) {
        maxMemoryCacheSize = max;
    }

    /**
     * Serializes the specified EDSMSystem into a file. Should be quicker to deserialize than
     * pulling the data again on EDSM, and will save bandwidth. <br>
     * This method performs under a separate thread, so this can run in the background. <br>
     * If the 'EDA' or 'Cache' folders are not created this will return and do nothing. <br>
     * If the .srl file cannot be created, an IOException is thrown to report the error. <br>
     * If the flag fileCacheEnabled is set to false, this will return and do nothing.
     * @param system The system to serialize.
     */
    public void serializeSystem( final EDSMSystem system ) {
        if (!fileCacheEnabled) {
            return;
        }

        validateUpdateFileCaches(system.name);

        Runnable serializeRun = ( ) -> {

            File edaFolder = new File( System.getProperty( "user.home" ) + File.separator +
                    "Documents" + File.separator + "EDA" ); //TODO merge all EDA related pathing to a separate utils lib

            File srlFolder = new File(edaFolder.getAbsolutePath() + File.separator + "Cache");

            File systemFile = new File( srlFolder.getAbsolutePath( ) + File.separator + system.name + ".srl" );

            if (!edaFolder.exists()) {
                return;
            }

            if (!srlFolder.exists()) {
                return;
            }

            if ( !systemFile.exists( ) ) {

                try {
                    boolean created = systemFile.createNewFile( );

                    if (!created)
                        throw new IOException( "The .srl file for: " + system.name + "could not be created." );

                } catch ( IOException e ) {
                    e.printStackTrace( );
                    //TODO logging
                }
            }

            try ( ObjectOutputStream outStream = new ObjectOutputStream( new FileOutputStream( systemFile ) ) ) {
                outStream.writeObject( system );
            } catch ( IOException e ) {
                e.printStackTrace( );
                //TODO logging
            }

            fileCaches.put( system.name, systemFile.getAbsolutePath() );
        };

        Thread thread = new Thread( serializeRun );
        thread.setName( "System-serializer-" + system.name );
        thread.start( );
    }

    /**
     * Deserializes the EDSMSystem object for the specified system. This will return an empty EDSMSystem
     * object with the name set to "Deserialization failed" if this process fails. Failure can be due to
     * a file not existing for the specified system name.
     * @param systemName The name of the system we want to retrieve the object for.
     * @return A deserialized EDSMSystem object.
     */
    public EDSMSystem deserializeSystem( String systemName)  {
        EDSMSystem system = new EDSMSystem();
        system.name = "Deserialization failed";

        File edaFolder = new File (System.getProperty( "user.home" ) + File.separator +
                "Documents" + File.separator + "EDA"); //TODO merge all EDA related pathing to a separate utils lib
        File systemFile = new File( fileCaches.get( systemName ) );

        if (!edaFolder.exists()) {
            return system;
        }

        if (!systemFile.exists()) {
            return system;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(systemFile))) {
            system = ( EDSMSystem ) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return system;
    }

    /**
     * Validates the system exists. If this has been previously searched and added to the cache,
     * the cache will be checked. System names stored in the cache have already been validated.
     * If it is not contained in the cache, a query will be sent to EDSM to validate if it exists.
     *
     * @return True if it is a valid system, false otherwise.
     */
    public boolean validateSystemName(String systemName) {

//        if (getContent != null && !getContent.equals( "{}" ) && !getContent.equals( "[]" )) {
//            return true;
//        }

        return checkCache( systemName );
    }

    /**
     * Updates the cache with the specified system name. If the cache is currently holding the maximum
     * entries, the first entry will be removed for every addition.
     * @param systemName
     */
    public void updateCache(String systemName) {
        if (recentlySearched.size() == maxMemoryCacheSize) {
            recentlySearched.remove( 0 );
        }

        if (!recentlySearched.contains( systemName ))
            recentlySearched.add( systemName );
    }

    /*
     * Checks if the system name is in the cache.
     * @return
     */
    public boolean checkCache(String systemName) {
        return recentlySearched.contains( systemName );
    }

    /**
     *
     */
    private void validateUpdateFileCaches(String systemName) {
        if (fileCaches.size() == maxFileCacheSize) {
           Set<String> cacheKeys = fileCaches.keySet();

           for (String s : cacheKeys) {
               fileCaches.remove( s );
           }
        }
    }

    /**
     *
     * @param get
     * @return
     * @throws IOException
     */
    private String readContent( HttpGet get )  {
        String content = "";

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute( get );
            HttpEntity entity = response.getEntity();

            try ( response; BufferedInputStream bis = new BufferedInputStream( entity.getContent( ) );
                  ByteArrayOutputStream buf = new ByteArrayOutputStream( ) ) {

                int result = 0;
                while ( ( result = bis.read( ) ) != -1 ) {
                    buf.write( ( byte ) result );
                }

                if ( buf.size( ) != 0 ) {
                    content = buf.toString( StandardCharsets.UTF_8 );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
