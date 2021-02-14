package com.github.errayeil.edanet;


import com.github.errayeil.edanet.Net.EDAQuery;
import com.github.errayeil.edanet.POJO.System.BasicSystem;
import com.github.errayeil.edanet.POJO.System.EDSMSystem;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Test {

    public Test( ) {
        test4();
    }

    public static void main( String[] args) throws Exception {
        new Test();
    }

    /**
     * Time test to see the difference between pulling from EDSM
     * and deserializing a system from disk.
     */
    public void test4() {
        long startTime = System.nanoTime();
        String[] tests = {"sol", "sol"};
        EDAQuery query = new EDAQuery();

        long netStartTime = System.nanoTime();

        query.setTargetSystem( tests[0] );
        query.getExtendedSystemInfo();

        long netEndTime = System.nanoTime();
        double elapsedNetTime = (double) TimeUnit.SECONDS.convert( netEndTime - netStartTime, TimeUnit.NANOSECONDS );

        long serialStartTime = System.nanoTime();

        query.setTargetSystem( tests[1] );
        query.getExtendedSystemInfo();

        long serialEndTime = System.nanoTime();
        double elapsedSerialTime = (double) TimeUnit.SECONDS.convert( serialEndTime - serialStartTime, TimeUnit.NANOSECONDS );

        long endTime = System.nanoTime();
        double elapsedTime = (double) TimeUnit.SECONDS.convert( endTime - startTime, TimeUnit.NANOSECONDS );

        System.out.println( "Seconds for EDSM pull: " + elapsedNetTime );
        System.out.println( "Seconds for disk pull: " + elapsedSerialTime);
        System.out.println( "Seconds total: " + elapsedTime );

    }

    public void test3() {
        EDAQuery query = new EDAQuery();

        query.setTargetSystem( "Sirius" );

        EDSMSystem system = query.getExtendedSystemInfo();
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
