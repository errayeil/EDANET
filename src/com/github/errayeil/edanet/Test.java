package com.github.errayeil.edanet;


import com.github.errayeil.edanet.Net.EDSMQuery;
import com.github.errayeil.edanet.POJO.System.EDSMSystem;

import java.util.Scanner;

public class Test {

    public Test( ) {
    }

    public static void main( String[] args) throws Exception {
        EDSMQuery newEDSMQuery = new EDSMQuery();
        Scanner scanner = new Scanner(System.in);

        EDSMSystem EDSMSystem = newEDSMQuery.searchForSystem( scanner.nextLine() );

    }


}
