package classes.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import interfaces.entities.IRailwayMap;
import classes.entities.RailwayStation;
import classes.exceptions.NoSuchRouteException;
import classes.exceptions.NoSuchStationException;

public class TripDistanceAnalyzer extends TripAnalyzer {

    private static final int REGEX_GROUP_START_INDEX = 1;
    private static final int ROUTE_SET_FROM_INDEX = 0;
    private static final int ROUTE_SET_TO_INDEX = 1;
    private static final int TRIP_INPUT_FROM_STATION_INDEX = 0;
    private static final int TRIP_INPUT_TO_STATION_INDEX = 2;
    private static final String CAPTURE_ROUTE_PATTERN = "(?=([a-zA-Z]-[a-zA-Z]))";
    private Pattern compiledRoutePattern = null;
    
    public TripDistanceAnalyzer(IRailwayMap trainMap) {
        super(trainMap);
        compiledRoutePattern = Pattern.compile(CAPTURE_ROUTE_PATTERN);
    }
    
    private List<Character[]> getRouteSets(String searchValue) {
        Matcher matcher = compiledRoutePattern.matcher(searchValue);
        List<Character[]> routeSets = new ArrayList<Character[]>();

        while(matcher.find()) {
            Character[] routeSet = new Character[/*From and To*/2];
            String route = matcher.group(REGEX_GROUP_START_INDEX); 
            routeSet[ROUTE_SET_FROM_INDEX] = route.charAt(TRIP_INPUT_FROM_STATION_INDEX);
            routeSet[ROUTE_SET_TO_INDEX] = route.charAt(TRIP_INPUT_TO_STATION_INDEX);
            
            routeSets.add(routeSet);
        }
        
        return routeSets;
    }
    
    @Override
    public String search(SearchParameter searchParam) throws NoSuchStationException, NoSuchRouteException {
        int distance = 0;
        List<Character[]> routeSets = getRouteSets(searchParam.getSearchValue());
        
        for(Character[] routeSet : routeSets) {
            char fromDestination = routeSet[ROUTE_SET_FROM_INDEX];
            char toDestination = routeSet[ROUTE_SET_TO_INDEX];
            
            RailwayStation fromStation = this.trainMap.getStation(fromDestination);
            RailwayStation toStation = this.trainMap.getStation(toDestination);
            
            distance += fromStation.getDistanceToDestination(toStation);
        }
        
        return Integer.toString(distance);
    }

}
