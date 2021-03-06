package classes.algorithms;

import java.util.ArrayList;
import java.util.List;

import classes.entities.RailwayStation;
import classes.exceptions.NoSuchRouteException;
import classes.exceptions.NoSuchStationException;
import interfaces.entities.IRailwayMap;

public class TripWithinStopsAnalzyer extends TripAnalyzer {

    public TripWithinStopsAnalzyer(IRailwayMap trainMap) {
        super(trainMap);
    }

    @Override
    public String search(SearchParameter searchParam)
            throws NoSuchStationException, NoSuchRouteException {
        
        // Throws NoSuchStationexception if 1 of the to or from station don't exists
        this.checkStationExists(searchParam.fromRailwayEntityName, searchParam.toRailwayEntityName);
               
        List<String> possibleTrips = new ArrayList<String>();
        RailwayStation currStation = trainMap.getStation(searchParam.fromRailwayEntityName);
        RailwayStation toStation = trainMap.getStation(searchParam.toRailwayEntityName);
        String transversedStation = new String("" + currStation.getStationName()); // Start location
        int effortUsed = 0;

        transverseStation(currStation,
                          /*toStation=*/toStation,
                          /*maxSteps=*/searchParam.effortValue,
                          /*effortUsed=*/effortUsed,
                          /*transversedStation=*/transversedStation,
                          possibleTrips);
        
        return Integer.toString(possibleTrips.size());
    }
    
    private void transverseStation(RailwayStation currStation,
                                   final RailwayStation toStation,
                                   final int maxSteps,
                                   int effortUsed,
                                   String transversedStation,
                                   List<String> possibleTripsWithinMaxStep) {
       
        int effortSnapshot = effortUsed;
        String transversedStatioSnapshot = transversedStation;
        
        // Only search further if the effort used is less than or equals to the specified max steps
        for (char nextStationName : currStation.getAllTransversibleLocation()) {
            RailwayStation nextStation = this.trainMap.getStation(nextStationName);
                
            // Not yet reach destination and the effort spent so far is still within max limit, travel deeper
            effortUsed += /* increment step=*/1;
            
            if (effortUsed <= maxSteps) {
                // Still within search space. 
                transversedStation += nextStationName;
                
                if (nextStation.equals(toStation)) {
                    // Reach destination
                    possibleTripsWithinMaxStep.add(transversedStation.toString());
                }
                
                // Visit the next node
                transverseStation(nextStation, 
                        toStation,
                        maxSteps,
                        effortUsed,
                        transversedStation,
                        possibleTripsWithinMaxStep);
            }
            else {
                // Gone beyond searchspace. Exit
                return;
            }
            
            transversedStation = transversedStatioSnapshot;
            effortUsed = effortSnapshot; // restore previous effort value
        } // end for each transversible station
    }
   
}
