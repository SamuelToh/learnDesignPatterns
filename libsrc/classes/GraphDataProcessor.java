package classes;

import interfaces.IGraphDataProcessor;
import interfaces.entities.IRailwayEntity;
import interfaces.entities.IRailwayMap;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;

import classes.entities.RailwayMap;
import classes.entities.RailwayStation;
import classes.exceptions.DuplicateRouteException;
import classes.exceptions.OrphanStationException;

public class GraphDataProcessor implements IGraphDataProcessor {

    // For each given graph values by the user.
    // The graph value must comply with the standard of having 
    // 1) an alphabet representing the fromStation
    // 2) another alphabet representing the toStation
    // 3) then followed by an integer value depicting the distance between the 2 stations
    // E.g. AB3 means that the distance to go from A to B is 3 
    private static final String VALID_ROUTE_PATTERN = "[a-zA-Z]{2}[0-9]+";
    private static final String GRAPH_INPUT_DELIMETER = ",";
    
    private static final int GRAPH_INPUT_FROM_STATION_INDEX = 0;
    private static final int GRAPH_INPUT_TARGET_STATION_INDEX = 1;
    private static final int STATION_TO_STATION_DISTANCE_INDEX = 2;
    
    private Pattern expectedRouteParttern = null;
    
    public GraphDataProcessor() {
        expectedRouteParttern = Pattern.compile(VALID_ROUTE_PATTERN);
    }
    
    private String[] preprocessGraphValue(String graphInput){
        graphInput = graphInput.replaceAll(" ", "");
        return graphInput.split(GRAPH_INPUT_DELIMETER);
    }
    
    @Override 
    public IRailwayMap processRailwayGraph(final String graphInput) 
            throws DuplicateRouteException, OrphanStationException {
        IRailwayMap trainMap = new RailwayMap();
        HashMap<Character, Integer> checkLinkageMap = new HashMap<Character, Integer>();
        String[] routes = preprocessGraphValue(graphInput);
        
        for (String route : routes) {
            if (isValidRouteSet(route)) {
                char fromStation = route.charAt(GRAPH_INPUT_FROM_STATION_INDEX);
                char toStation = route.charAt(GRAPH_INPUT_TARGET_STATION_INDEX);
                int distance = Integer.parseInt(route.substring(STATION_TO_STATION_DISTANCE_INDEX, 
                                                                route.length()));
                
                IRailwayEntity fromStationEntity = null;
                IRailwayEntity toStationEntity = null;
                
                // Create station if it hasn't been created before. 
                if (!trainMap.hasStation(fromStation)) {
                    fromStationEntity = new RailwayStation(fromStation);
                    trainMap.insertRailEntity(fromStationEntity);
                }
                
                if (!trainMap.hasStation(toStation)) {
                    toStationEntity = new RailwayStation(toStation);
                    trainMap.insertRailEntity(toStationEntity);
                }

                fromStationEntity = trainMap.getStation(fromStation);
                toStationEntity = trainMap.getStation(toStation);

                // Create linkage between both stations
                fromStationEntity.addOutboundStation(toStationEntity, distance); 
                
                // Mark the TO-station as accessible.  
                if (!checkLinkageMap.containsKey(toStation)) {
                    checkLinkageMap.put(toStation, 1);
                }
            }
        }
        
        // Validate the train map and ensure that it is not a broken graph
        // that is, every defined stations is accessible
        int orphanNodeCount = 0;
        for(Character stationName : checkLinkageMap.keySet()) {
            if (!checkLinkageMap.containsKey(stationName)) {
                // this station is defined but is not accessible from other location
                orphanNodeCount++;
            }
        }
        
        // Assumption #1 - The first node is assumed as it will never be accessible.
        if (orphanNodeCount > 1 ) {
            throw new OrphanStationException(); 
        }
        
        return trainMap;
    }
    
    private boolean isValidRouteSet(final String routeSet) {
        boolean isValid = false;
        
        Matcher match = expectedRouteParttern.matcher(routeSet); 
        if (match.matches()) {
            isValid = true;
        }

        return isValid;
    }

}
