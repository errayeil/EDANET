package com.github.errayeil.edanet;


import com.github.errayeil.edanet.Net.EDAQuery;
import com.github.errayeil.edanet.POJO.System.BasicSystem;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Test {

    public Test( ) throws URISyntaxException {
        test2();
    }

    public static void main( String[] args) throws Exception {
        new Test();
    }

    public void test2() throws URISyntaxException {
        EDAQuery query = new EDAQuery( );

        query.setTargetSystem( "sol" );
        query.setTargetRadius( 100 );
        List< BasicSystem> systems = query.searchForSystemsInRadius( );

        for (BasicSystem system : systems) {
            System.out.println( system.name );
        }

        System.out.println( "Systems found: " + systems.size() );
    }

    public void test1() {
        String[] tests = {"sol", "Sirius", "Wolf 359", "Duamata", "Luhman 16",
                "Barnard's Star", "Alpha Centauri", "Ross 154", "Epsilon Eridani", "WISE 1506+7027",
                "Ross 128", "EZ Aquarii", "61 Cygni", "Procyon", "Groombridge 34", "Kruger 60"};
        EDAQuery newEDSMQuery = new EDAQuery();

        for (String system : tests) {
            long start = System.nanoTime();

            boolean valid = newEDSMQuery.setTargetSystem( system );

            if (valid) {
                newEDSMQuery.getExtendedSystemInfo( );

                double elapsed = (double) TimeUnit.SECONDS.convert( System.nanoTime() - start, TimeUnit.NANOSECONDS );
                System.out.println("Completed in: " + elapsed + " seconds");
            } else {
                System.out.println( "Skipped: " + system );
            }
        }

        System.out.println( "Tests completed." );
    }


}
