package com.github.errayeil.edanet;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;

public class Test {

    public Test( ) {
    }

    public static void main( String[] args) throws Exception {
//        CloseableHttpClient client = HttpClients.createDefault();
//        URIBuilder builder = new URIBuilder("https://www.edsm.net/api-v1/system");
//        builder.setParameter( "systemName", "sol" );
//        builder.setParameter( "showInformation", "1" );
//        builder.setParameter( "showId", "1" );
//        builder.setParameter( "showCoordinates", "1" );
//        builder.setParameter( "showPermit", "1");
//        builder.setParameter( "showPrimaryStar", "1" );
//        HttpGet httpGet = new HttpGet(builder.build());
//
//        CloseableHttpResponse response = client.execute(httpGet);
//
//        try {
//            HttpEntity entity = response.getEntity();
//            InputStream in = entity.getContent();
//            BufferedInputStream bis = new BufferedInputStream(in);
//            ByteArrayOutputStream buf = new ByteArrayOutputStream();
//
//            int result = bis.read();
//            while(result != -1) {
//                buf.write((byte) result);
//                result = bis.read();
//            }
//
//            System.out.println( buf.toString( StandardCharsets.UTF_8 ) );
//            EntityUtils.consume(entity);
//        } finally {
//            response.close();
//        }
    }


}
