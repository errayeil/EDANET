package com.github.errayeil.edanet.Net;

import com.github.errayeil.edanet.EDSMURLS;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Aids in building URI's for requesting content from edsm.net.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class QueryBuilder {

    public static String PARA_ENABLE = "1";

    public static String PARA_DISABLE = "0";

    public static String PARA_NAME = "systemName";

    public static String PARA_INFO = "showInformation";

    public static String PARA_ID = "showId";

    public static String PARA_COORD = "showCoordinates";

    public static String PARA_PERMIT = "showPermit";

    public static String PARA_STAR = "showPrimaryStar";

    public static String PARA_MARKET = "marketId";

    /**
     *
     */
    private String targetSystem;

    /**
     *
     */
    private String targetMarketId;


    /**
     *
     */
    public QueryBuilder()  {

    }

    /**
     *
     * @param systemName
     */
    public void setTargetSystem(String systemName) {
        targetSystem = systemName;
    }

    /**
     *
     * @param marketId
     */
    public void setTargetMarketId(String marketId) {
        targetMarketId = marketId;
    }

    /**
     *
     */
   public HttpGet buildForSystem() throws URISyntaxException {
       URIBuilder builder = new URIBuilder( EDSMURLS.edsm_system_url );

       builder.setParameter( PARA_NAME, targetSystem );
       builder.setParameter( PARA_INFO, PARA_ENABLE );
       builder.setParameter( PARA_ID, PARA_ENABLE );
       builder.setParameter( PARA_COORD, PARA_ENABLE );
       builder.setParameter( PARA_PERMIT, PARA_ENABLE );
       builder.setParameter( PARA_STAR, PARA_ENABLE );

       return new HttpGet( builder.build() );
   }

    /**
     *
     * @return
     * @throws URISyntaxException
     */
   public HttpGet buildForSystemBodies() throws URISyntaxException {
       URIBuilder builder = new URIBuilder(EDSMURLS.edsm_bodies_url);

       builder.setParameter( PARA_NAME, targetSystem );

       return new HttpGet( builder.build() );
   }

    /**
     *
     * @return
     * @throws URISyntaxException
     */
   public HttpGet buildForSystemStations() throws URISyntaxException {
       URIBuilder builder = new URIBuilder(EDSMURLS.edsm_stations_url);

       builder.setParameter( PARA_NAME, targetSystem );

       return new HttpGet( builder.build() );
   }

    /**
     *
     * @return
     * @throws URISyntaxException
     */
   public HttpGet buildForSystemFactions() throws URISyntaxException {
       URIBuilder builder = new URIBuilder(EDSMURLS.edsm_factions_url);

       builder.setParameter( PARA_NAME, targetSystem );

       return new HttpGet( builder.build() );
   }

    /**
     *
     * @return
     * @throws URISyntaxException
     */
   public HttpGet buildForSystemScanValues() throws URISyntaxException {
       URIBuilder builder = new URIBuilder(EDSMURLS.edsm_estimated_value_url);

       builder.setParameter( PARA_NAME, targetSystem );

       return new HttpGet( builder.build() );
   }

    /**
     *
     * @return
     * @throws URISyntaxException
     */
   public HttpGet buildForSystemTraffic() throws URISyntaxException {
       URIBuilder builder = new URIBuilder( EDSMURLS.edsm_traffic_url );

       builder.setParameter( PARA_NAME, targetSystem );

       return new HttpGet( builder.build() );
   }

    /**
     *
     * @return
     * @throws URISyntaxException
     */
   public HttpGet buildForSystemDeaths()  {
       HttpGet get = null;
       URIBuilder builder = null;

      try {
         builder = new URIBuilder(EDSMURLS.edsm_deaths_url );

          builder.setParameter( PARA_NAME, targetSystem );
      } catch ( URISyntaxException e ) {
          e.printStackTrace( );
      }

       try {
           get = new HttpGet( builder.build());
       } catch ( URISyntaxException e ) {
           e.printStackTrace( );
       }

       return get;
   }

    /**
     *
     * @return
     * @throws URISyntaxException
     */
   public HttpGet buildForStationMarket() throws URISyntaxException {
       URIBuilder builder = new URIBuilder( EDSMURLS.edsm_market_url );

       builder.setParameter( PARA_MARKET, targetMarketId );

       return new HttpGet( builder.build() );
   }

    /**
     *
     * @return
     * @throws URISyntaxException
     */
   public HttpGet buildForMarketShipyard() throws URISyntaxException {
       URIBuilder builder = new URIBuilder(EDSMURLS.edsm_shipyard_url);

       builder.setParameter( PARA_MARKET, targetMarketId );

       return new HttpGet( builder.build() );
   }

    /**
     *
     * @return
     * @throws URISyntaxException
     */
   public HttpGet buildForMarketOutfitting() throws URISyntaxException {
       URIBuilder builder = new URIBuilder(EDSMURLS.edsm_outfitting_url);

       builder.setParameter( PARA_MARKET, targetMarketId );

       return new HttpGet( builder.build() );
   }



}
