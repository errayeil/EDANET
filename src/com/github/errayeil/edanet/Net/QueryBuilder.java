package com.github.errayeil.edanet.Net;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

/**
 * Aids in building URI's for requesting content from edsm.net.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class QueryBuilder {

    /**
     * The URIBuilder url;
     */
    private String queryURL;

    /**
     * The URIBuilder.
     */
    private URIBuilder builder;


    /**
     * Constructs a new builder from the specified url.
     * @param url
     */
    public QueryBuilder()  {

    }

    /**
     *
     * @param url
     */
    public void setBuilderURI(String url) throws URISyntaxException {
        queryURL = url;
        builder = new URIBuilder(url);
    }

    /**
     * Adds a parameter to the specified builder.
     *
     * @param key
     * @param value
     */
    public void addBuilderParameter(final String key, final String value) {
        builder.setParameter( key, value );
    }

    /**
     * Builds the query and returns an HttpGet instance for use
     * with CloseableHttpClient.
     * @return
     */
    public HttpGet build() throws URISyntaxException {
        return new HttpGet( builder.build() );
    }

    /**
     * Clears the builder parameters.
     */
    public void clear() {
        builder.clearParameters();
    }

    /**
     * Sets the builder url to new url.
     * @param url
     */
    public void setQueryURL(final String url) throws URISyntaxException {
        builder = new URIBuilder( url );
    }


}
