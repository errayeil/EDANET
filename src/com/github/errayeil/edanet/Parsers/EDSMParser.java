package com.github.errayeil.edanet.Parsers;

import com.github.errayeil.edanet.POJO.Body.CelestialBody;
import com.github.errayeil.edanet.POJO.Body.CelestialBodyDiscovery;
import com.github.errayeil.edanet.POJO.Body.CelestialBodyRing;
import com.github.errayeil.edanet.POJO.Body.ValuableBody;
import com.github.errayeil.edanet.POJO.Station.*;
import com.github.errayeil.edanet.POJO.System.*;
import com.google.gson.*;

import java.util.*;
import java.util.List;

public class EDSMParser {

    private EDSMParser() {}

    /**
     * Parses the system information from the system content.
     *
     * @param systemContent
     */
    public static EDSMSystem parseSystemJson( String systemContent) {
        EDSMSystem EDSMSystem = new EDSMSystem();

        JsonElement element = JsonParser.parseString( systemContent );
        JsonObject object = element.getAsJsonObject();
        Gson gson = new Gson();

        EDSMSystem.name = object.get( "name" ).getAsString();
        EDSMSystem.id = object.get( "id" ).getAsLong();
        EDSMSystem.id64 = object.get( "id64" ).getAsLong();
        EDSMSystem.coordsLocked = object.get( "coordsLocked" ).getAsBoolean();
        EDSMSystem.requirePermit = object.get( "requirePermit" ).getAsBoolean();
        EDSMSystem.permitName = object.get( "permitName" ).getAsString();
        EDSMSystem.coords = gson.fromJson( object.getAsJsonObject( "coords" ), SystemCoordinates.class );
        EDSMSystem.information = gson.fromJson( object.getAsJsonObject( "information" ), SystemInformation.class );
        EDSMSystem.primaryStar = gson.fromJson( object.getAsJsonObject( "primaryStar" ), SystemPrimaryStar.class );

        return EDSMSystem;
    }

    /**
     * Parses the celestial bodies of the specified system in the bodies content.
     * @param bodiesContent
     * @return
     */
    public static CelestialBody[] parseBodiesJson( String bodiesContent) {
        List<CelestialBody> bodies = new ArrayList<>(  );

        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonElement parentElement = gson.fromJson( bodiesContent, JsonElement.class);
        JsonArray array = parentElement.getAsJsonObject().getAsJsonArray( "bodies" );

        for (JsonElement bodyElement : array) {
            CelestialBody body = new CelestialBody();
            JsonObject object = bodyElement.getAsJsonObject();

            double min = Double.MIN_EXPONENT;

            body.id = object.get( "id" ).getAsLong();
            body.id64 = object.get( "id64" ).getAsLong();
            body.name = object.get( "name" ).getAsString();
            body.type = object.get( "type" ).getAsString();
            body.subType = object.get( "subType" ).getAsString();
            body.distanceToArrival = object.get( "distanceToArrival" ).getAsLong();
            body.isMainStar = object.has( "isMainStar" ) && object.get( "isMainStar" ).getAsBoolean( );
            body.isLandable = object.has( "isLandable" ) && object.get( "isLandable" ).getAsBoolean() ;
            body.gravity = object.has( "gravity" )  ? object.get( "gravity" ).getAsDouble() : min;
            body.earthMasses = object.has("earthMasses")? object.get( "earthMasses" ).getAsDouble() : min;
            body.radius = object.has( "radius" ) ? object.get( "radius" ).getAsDouble() : min;
            body.volcanismType = object.has( "volcanismType" ) ? object.get( "volcanismType" ).getAsString() : null;
            body.atmosphereType = object.has( "atmosphereType" ) ? object.get("atmosphereType" ).getAsString() : null;
            body.terraformingState = object.has( "terraformingState" ) && !object.get( "terraformingState" ).isJsonNull() ? object.get( "terraformingState" ).getAsString() : null;
            body.spectralClass = object.has( "spectralClass" ) ? object.get( "spectralClass" ).getAsString() : null;
            body.isScoopable = object.has( "isScoopable" ) ? object.get( "isScoopable" ).getAsBoolean() : false;
            body.age = object.has( "age" ) ? object.get( "age" ).getAsLong() : Long.MIN_VALUE;
            body.luminosity = object.has( "luminosity" ) ? object.get( "luminosity" ).getAsString() : null;
            body.absoluteMagnitude = object.has( "absoluteMagnitude" ) ? object.get( "absoluteMagnitude" ).getAsDouble() : min;
            body.solarMasses = object.has( "solarMasses" ) ? object.get( "solarMasses" ).getAsDouble() : min;
            body.solarRadius = object.has( "solarRadius" ) ? object.get( "solarRadius" ).getAsDouble() : min;
            body.surfaceTemperature = object.has( "surfaceTemperature" ) ? object.get( "surfaceTemperature" ).getAsLong() : Long.MIN_VALUE;
            body.orbitalPeriod = object.has( "orbitalPeriod" ) && !object.get( "orbitalPeriod" ).isJsonNull() ? object.get( "orbitalPeriod" ).getAsDouble() : min;
            body.semiMajorAxis = object.has( "semiMajorAxis" ) && !object.get( "semiMajorAxis" ).isJsonNull() ? object.get( "semiMajorAxis" ).getAsDouble() : min;
            body.orbitalEccentricity = object.has( "orbitalEccentricity" ) && !object.get( "orbitalEccentricity" ).isJsonNull() ? object.get( "orbitalEccentricity" ).getAsDouble() : min;
            body.ageOfPeriapsis = object.has( "ageOfPeriapsis" ) ? object.get( "ageOfPeriapsis" ).getAsDouble() : min;
            body.rotationalPeriod = object.has( "rotationalPeriod" ) ? object.get( "rotationalPeriod" ).getAsDouble() : min;
            body.rotationalPeriodTidallyLocked = object.has( "rotaionalPeriodTidallyLocked" ) && object.get( "rotationalPeriodTidallyLocked" ).getAsBoolean( );
            body.axialTilt = object.has( "axialTilt" ) && !object.get( "axialTilt" ).isJsonNull() ? object.get( "axialTilt" ).getAsDouble() : min;
            body.discovery = object.has( "discovery" ) && !object.get( "discovery" ).isJsonNull() ? gson.fromJson( object.get( "discovery" ).getAsJsonObject(), CelestialBodyDiscovery.class ) : null;
            body.updateTime = object.has( "updateTime" ) ? object.get( "updateTime" ).getAsString() : null;

            if (object.has( "atmosphereComposition" ) && !object.get( "atmosphereComposition" ).isJsonNull()) {
                Map<String, Double> atmoComp = new HashMap<>();

                JsonObject compObject = object.getAsJsonObject( "atmosphereComposition" );

                Set< Map.Entry< String, JsonElement > > set = compObject.entrySet();

                for ( Map.Entry< String, JsonElement > entry : set ) {
                    atmoComp.put( entry.getKey( ), entry.getValue( ).getAsDouble( ) );
                }

                body.atmosphereComposition = atmoComp;
            }

            if (object.has( "solidComposition" ) && !object.get( "solidComposition" ).isJsonNull()) {
                Map<String, Double> comps = new HashMap<>();

                JsonObject compObject = object.getAsJsonObject( "solidComposition" );

               Set< Map.Entry< String, JsonElement > > set = compObject.entrySet();

                for ( Map.Entry< String, JsonElement > entry : set ) {
                    comps.put( entry.getKey( ), entry.getValue( ).getAsDouble( ) );
                }

                body.solidComposition = comps;
             }

            if (object.has( "parents" ) && !object.get( "parents" ).isJsonNull()) {
                JsonArray parentsArray = object.getAsJsonArray( "parents" );
                Map<String, Integer> parents = new HashMap<>();

                parents.put( "Star", 0 );
                parents.put( "Planet", 0 );

                for (JsonElement parentEle : parentsArray) {
                    String str = parentEle.toString();
                    JsonObject obj = parentEle.getAsJsonObject();

                    if (str.contains( "Star" )) {
                        int value = obj.get( "Star" ).isJsonNull() ? obj.get( "Star" ).getAsInt() : 0;
                        parents.put( "Star", parents.get( "Star" ) + value );
                    } else if (str.contains( "Planet" )) {
                        int value = obj.get( "Planet" ).isJsonNull() ? obj.get( "Planet" ).getAsInt() : 0;
                        parents.put( "Planet", parents.get( "Planet" ) + value);
                    }
                }
            }

            if (object.has( "materials" ) && !object.get( "materials" ).isJsonNull()) {
                Map<String, Double> materials = new HashMap<>();

                JsonObject materialObjet = object.getAsJsonObject( "materials" );

                Set< Map.Entry< String, JsonElement > > set = materialObjet.entrySet();

                for ( Map.Entry< String, JsonElement > entry : set ) {
                    materials.put( entry.getKey( ), entry.getValue( ).getAsDouble( ) );
                }

                body.materials = materials;
            }

            if (object.has( "rings" )) {
                JsonArray ringsArray = object.getAsJsonArray( "rings" );
                List< CelestialBodyRing > rings = new ArrayList<>(  );

                for (JsonElement ringsEle : ringsArray) {
                    CelestialBodyRing ring = new CelestialBodyRing();
                    JsonObject ringObject = ringsEle.getAsJsonObject( );

                    ring.innerRadius = ringObject.get( "innerRadius" ).getAsLong();
                    ring.name = ringObject.get( "name" ).getAsString();
                    ring.type = ringObject.get( "type" ).getAsString();
                    ring.outerRadius = ringObject.get( "outerRadius" ).getAsLong();
                    ring.mass = ringObject.get( "mass" ).getAsLong();

                    rings.add( ring );
                }

                body.rings = rings.toArray( new CelestialBodyRing[rings.size()] );
            } else {
                body.rings = null;
            }

            bodies.add( body );
        }

        return bodies.toArray( new CelestialBody[bodies.size()] );
    }

    /**
     * Parses all the stations in the system specified in the station content.
     * @param stationContent
     * @return
     */
    public static SystemStation[] parseStationsJson(String stationContent) {
        List<SystemStation> stations = new ArrayList<>();

        JsonElement element = new GsonBuilder().setLenient().create().fromJson( stationContent, JsonElement.class );
        JsonArray stationsArray = element.getAsJsonObject( ).getAsJsonArray( "stations");

        for (JsonElement stationElement : stationsArray) {
            SystemStation station = new SystemStation();
            JsonObject object = stationElement.getAsJsonObject();

            station.allegiance = object.get( "allegiance" ).getAsString();
            station.distanceToArrival = object.get( "distanceToArrival" ).getAsLong();
            station.id = object.get( "id" ).getAsLong();
            station.marketId = object.get( "marketId" ).getAsLong();
            station.economy = object.get("economy").getAsString();
            station.government = object.get( "government" ).getAsString();
            station.haveMarket = object.get( "haveMarket" ).getAsBoolean();
            station.haveShipyard = object.get( "haveShipyard" ).getAsBoolean();
            station.type = object.get( "type" ).getAsString();
            station.name = object.get( "name" ).getAsString();

            StationControllingFaction faction = new StationControllingFaction();
            JsonObject obj = object.getAsJsonObject( "controllingFaction" );

            faction.name = obj.get( "name" ).getAsString();
            faction.id = obj.has( "id" ) && !obj.get( "id" ).isJsonNull() ? obj.get( "id" ).getAsLong() : -1;

            station.stationControllingFaction = faction;
            stations.add(station);
        }

        return stations.toArray( new SystemStation[stations.size()] );
    }

    /**
     * Parses all the factions in the system from the faction content.
     * @param factionContent
     * @return
     */
    public static SystemFaction[] parseStationFactionJson( String factionContent) {
        List<SystemFaction> factions = new ArrayList<>(  );

        JsonElement element = JsonParser.parseString( factionContent );
        JsonArray array = element.getAsJsonObject().getAsJsonArray( "factions" );

        for (JsonElement factionsElement : array) {
            SystemFaction faction = new SystemFaction();
            JsonObject obj = factionsElement.getAsJsonObject();

            faction.id = obj.get( "id" ).getAsLong();
            faction.name = obj.get( "name" ).getAsString();
            faction.allegiance = obj.get( "allegiance" ).getAsString();
            faction.government = obj.get( "government" ).getAsString();
            faction.influence = obj.get( "influence" ).getAsDouble();
            faction.isPlayer = obj.get( "isPlayer" ).getAsBoolean();
            faction.state = obj.has( "state" ) && !obj.get( "state" ).isJsonNull() ? obj.get( "state" ).getAsString() : null;
            //TODO state history
            //TODO influence history
            //TODO recovering states

            if (obj.has( "controllingFaction" ) && !obj.get( "controllingFaction" ).isJsonNull()) {
                JsonObject objF = obj.get( "controllingFaction" ).getAsJsonObject();
                StationControllingFaction ctrl = new StationControllingFaction();

                ctrl.id = objF.get( "id" ).getAsLong();
                ctrl.name = objF.get( "name" ).getAsString();
                faction.controllingFaction = ctrl;
            }

            if (obj.has("pendingStates") && !obj.get( "pendingStates" ).isJsonNull()) {
                JsonArray stateArray = obj.getAsJsonArray( "pendingStates" );
                List< State > list = new ArrayList<>(  );

                for (JsonElement ele : stateArray) {
                    JsonObject state = ele.getAsJsonObject();
                    State st = new State();

                    st.state = state.get( "state" ).getAsString();
                    st.trend = state.get( "trend" ).getAsInt();
                    list.add( st );
                }
            }
        }

        return factions.toArray( new SystemFaction[factions.size()] );
    }

    /**
     * Parses the controlling faction of the system in the faction content String.
     * @param factionContent
     * @return
     */
    public static SystemControllingFaction parseSystemFactionJson( String factionContent) {
        SystemControllingFaction faction = new SystemControllingFaction();

        JsonElement element = JsonParser.parseString( factionContent );
        JsonObject obj = element.getAsJsonObject();
        JsonObject ctr = obj.getAsJsonObject( "controllingFaction" );

        faction.allegiance = ctr.get( "allegiance" ).getAsString();
        faction.id = ctr.get( "id" ).getAsLong();
        faction.name = ctr.get( "name" ).getAsString();
        faction.government = ctr.get( "government" ).getAsString();

        return faction;
    }

    /**
     * Parses the estimated scan value for the system in the scan value content.
     * @param scanValueContent
     * @return
     */
    public static SystemScanValues parseSystemScanValuesJson( String scanValueContent ) {
        SystemScanValues values = new SystemScanValues();
        List< ValuableBody > bodies = new ArrayList<>(  );

        JsonElement element = JsonParser.parseString( scanValueContent );
        JsonObject object = element.getAsJsonObject();
        JsonArray valuables = object.getAsJsonArray( "valuableBodies" );

        values.id = object.get( "id" ).getAsLong();
        values.id64 = object.get( "id64" ).getAsLong();
        values.name = object.get( "name" ).getAsString();
        values.url = object.get( "url" ).getAsString();
        values.estimatedValue = object.get( "estimatedValue" ).getAsLong();
        values.estimatedValueMapped = object.has( "estimatedValue" ) && object.get( "estimatedValue" ).isJsonNull() ? object.get( "estimatedValueMax").getAsLong() : -1;

        for (JsonElement ele : valuables) {
            ValuableBody body = new ValuableBody();
            JsonObject obj = ele.getAsJsonObject();

            body.bodyId = obj.get( "bodyId" ).getAsLong();
            body.bodyName = obj.get( "bodyName" ).getAsString();
            body.valueMax = obj.get( "valueMax" ).getAsLong();
            body.distance = obj.get( "distance" ).getAsLong();
            bodies.add(body);
        }

        values.valuableBodies = bodies.toArray( new ValuableBody[bodies.size()] );

        return values;
    }

    /**
     *
     * @param systemTrafficContent
     * @return
     */
    public static SystemTraffic parseSystemTrafficJson( String systemTrafficContent ) {
        SystemTraffic systemTraffic = new SystemTraffic();
        Map<String, Integer> breakdownMap = new HashMap<>();

        JsonElement element = JsonParser.parseString( systemTrafficContent );
        JsonObject object = element.getAsJsonObject();

        systemTraffic.id = object.get( "id" ).getAsLong();
        systemTraffic.id64 = object.get( "id64" ).getAsLong();
        systemTraffic.name = object.get( "name" ).getAsString();

        JsonObject trafficObject = object.get( "traffic" ).getAsJsonObject();

        systemTraffic.total = trafficObject.get( "total" ).getAsInt();
        systemTraffic.week = trafficObject.get( "week" ).getAsInt();
        systemTraffic.day = trafficObject.get( "day" ).getAsInt();

        JsonObject breakdownObject = element.getAsJsonObject().getAsJsonObject( "breakdown" );

        Set<String> keys = breakdownObject.keySet();

        for ( String key : keys ) {
            breakdownMap.put( key, breakdownObject.get( key ).getAsInt( ) );
        }

        systemTraffic.breakdown = breakdownMap;

        return systemTraffic;
    }

    /**
     *
     * @param systemDeathsContent
     * @return
     */
    public static SystemDeaths parseSystemDeathsJson( String systemDeathsContent ) {
        SystemDeaths deaths = new SystemDeaths();

        JsonElement element = JsonParser.parseString( systemDeathsContent );
        JsonObject object = element.getAsJsonObject();

        deaths.id = object.get( "id" ).getAsLong();
        deaths.id64 = object.get( "id64" ).getAsLong();
        deaths.name = object.get( "name" ).getAsString();

        JsonObject deathsObject = object.getAsJsonObject( "deaths" );

        Set<String> keys = deathsObject.keySet();

        for (String key : keys) {
            if (key.equals( "total" ))
                deaths.totalDeaths = deathsObject.get( key ).getAsInt();

            if (key.equals( "week" ))
                deaths.weeklyDeaths = deathsObject.get( key ).getAsInt();

            if (key.equals( "day" ))
                deaths.dailyDeaths = deathsObject.get( key ).getAsInt();
        }

        return deaths;
    }

    /**
     *
     * @param shipyardContent
     * @return
     */
    public static StationShipyard parseStationShipyardJson( String shipyardContent) {
        StationShipyard shipyard = new StationShipyard();
        List< StationShip > ships = new ArrayList<>();

        JsonObject object = JsonParser.parseString( shipyardContent ).getAsJsonObject();

        shipyard.id = object.get( "id" ).getAsLong();
        shipyard.id64 = object.get( "id64" ).getAsLong();
        shipyard.systemName = object.get( "name" ).getAsString();
        shipyard.marketId = object.get( "marketId" ).getAsLong();
        shipyard.stationId = object.get( "sId" ).getAsLong();
        shipyard.stationName = object.get( "sName" ).getAsString();
        shipyard.url = object.has( "url" ) && !object.get( "url" ).isJsonNull() ? object.get( "url" ).getAsString() : "";

        JsonArray array = object.getAsJsonArray( "ships" );

        for (JsonElement element : array) {
            JsonObject obj = element.getAsJsonObject();
            StationShip ship = new StationShip();

            ship.id = obj.get( "id" ).getAsLong();
            ship.name = obj.get( "name" ).getAsString();
            ships.add( ship );
        }

        shipyard.ships = ships.toArray( ships.toArray( new StationShip[ ships.size() ] ) );

        return shipyard;
    }

    /**
     *
     * @param marketContent
     * @return
     */
    public static StationMarket parseStationMarketJson( String marketContent) {
        StationMarket market = new StationMarket();

        return market;
    }
}