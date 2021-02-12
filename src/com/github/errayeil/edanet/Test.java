package com.github.errayeil.edanet;


import com.github.errayeil.edanet.Net.EDSMQuery;
import com.github.errayeil.edanet.POJO.System.EDSMSystem;

public class Test {

    public Test( ) {
    }

    public static void main( String[] args) throws Exception {
        EDSMQuery newEDSMQuery = new EDSMQuery();
        EDSMSystem EDSMSystem = newEDSMQuery.getBasicSystemInfo( "sol" );

        System.out.println( EDSMSystem.toString() );
    }


}
