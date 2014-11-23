package classes.algorithms;

import java.util.ArrayList;
import java.util.List;

import classes.entities.RailwayStation;
import classes.exceptions.NoSuchRouteException;
import classes.exceptions.NoSuchStationException;
import interfaces.entities.IRailwayMap;

public class TripsLessThanDistance extends TripAnalyzer {

    public TripsLessThanDistance(IRailwayMap trainMap) {
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
                          /*maxDistance=*/searchParam.effortValue,
                          /*effortUsed=*/effortUsed,
                          /*transversedStation=*/transversedStation,
                          possibleTrips);
        
        return Integer.toString(possibleTrips.size());
    }

    // begins with infinitely search space
    private void transverseStation(RailwayStation currStation,
            final RailwayStation toStation,
            final int maxDistance,
            int distanceTraveled,
            String transversedStation,
            List<String> possibleTrips) throws NoSuchRouteException {

        int effortSnapshot = distanceTraveled;
        String transversedStatioSnapshot = transversedStation;

        // Only search further if the effort used is less than or equals to the specified max steps
        for (char nextStationName : currStation.getAllTransversibleLocation()) {
            RailwayStation nextStation = this.trainMap.getStation(nextStationName);

            // Not yet reach destination and the effort spent so far is still within max limit, travel deeper
            distanceTraveled += /* increment dist=*/currStation.getDistanceToDestination(nextStation);

            if (distanceTraveled < maxDistance) {
                // Still within search space. 
                transversedStation += nextStationName;

                if (nextStation.equals(toStation)) {
                    possibleTrips.add(transversedStation);
                }
              
                // Visit the next node
                transverseStation(nextStation, 
                                  toStation,
                                  maxDistance,
                                  distanceTraveled,
                                  transversedStation,
                                  possibleTrips);
             }
             else {
                 // Gone beyond searchspace. Exit
                 return;
             }

             transversedStation = transversedStatioSnapshot;
             distanceTraveled = effortSnapshot; // restore previous effort value
         } // end for each transversible station
    }
}
