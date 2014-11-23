package classes.algorithms;

import java.util.TreeMap;

import classes.entities.RailwayStation;
import classes.exceptions.NoSuchRouteException;
import classes.exceptions.NoSuchStationException;
import interfaces.entities.IRailwayMap;

public class TripShortestDistanceAnalzyer extends TripAnalyzer {

    public TripShortestDistanceAnalzyer(IRailwayMap trainMap) {
        super(trainMap);
    }

    @Override
    public String search(SearchParameter searchParam)
            throws NoSuchStationException, NoSuchRouteException {
        // Throws NoSuchStationexception if 1 of the to or from station don't exists
        this.checkStationExists(searchParam.fromRailwayEntityName, searchParam.toRailwayEntityName);
               
        RailwayStation currStation = trainMap.getStation(searchParam.fromRailwayEntityName);
        RailwayStation toStation = trainMap.getStation(searchParam.toRailwayEntityName);
        String transversedStation = new String("" + currStation.getStationName()); // Start location
        TreeMap<Integer,String> solution = new TreeMap<Integer, String>();
        
        // init with a best solution that has an infinite search space
        solution.put(Integer.MAX_VALUE, "");
        
        // Define the search space as almost infinite, maxEffort=Integer.MaxValue, 
        // it is ok to define such a huge search space as the program has already
        // ensure that every entity defined in the trainMap is accessible and there is no
        // broken link in it. Hence, it is possible to first find a trip then use the
        // effort taken for that trip as a bench mark and search backwards for shortest distance
        transverseStation(currStation,
                          /*prevStation=*/null,
                          /*toStation=*/toStation,
                          /*effortUsed=*/0,
                          transversedStation,
                          /*bestSolution container=*/solution);
        
        return Integer.toString(solution.firstKey());
    }

    // begins with infinitely search space
    private void transverseStation(RailwayStation currStation,
            RailwayStation prevStation,
            final RailwayStation toStation,
            int distanceTraveled,
            String transversedStation,
            TreeMap<Integer, String> bestSolution) throws NoSuchRouteException {

         int maxEffort = bestSolution.firstKey(); // define search space
         int effortSnapshot = distanceTraveled;
         String transversedStatioSnapshot = transversedStation;

         // Only search further if the effort used is less than or equals to the specified max steps
         for (char nextStationName : currStation.getAllTransversibleLocation()) {
             RailwayStation nextStation = this.trainMap.getStation(nextStationName);

             // Not yet reach destination and the effort spent so far is still within max limit, travel deeper
             distanceTraveled += /* increment dist=*/currStation.getDistanceToDestination(nextStation);

             if (distanceTraveled <= maxEffort) {
                 // Still within search space. 
                 transversedStation += nextStationName;
                 
                 // Detect cyclic relation 
                 if (prevStation != null && prevStation.equals(nextStation)) {
                     // break loop to avoid stackoverflow 
                     continue;
                 }

                 if (nextStation.equals(toStation)) {
                     // Reach a destination 
                     // Review the solution
                     int bestSolutionEffort = bestSolution.firstKey();
                         
                     if (distanceTraveled < bestSolutionEffort) {
                         // Found a better solution, replace with previous discovered solution.
                         bestSolution.remove(bestSolutionEffort);
                         bestSolution.put(distanceTraveled, transversedStation);
                         maxEffort = distanceTraveled; // redefine search space.
                     }
                 }   
               
                 // Visit the next node
                 transverseStation(nextStation, 
                                   currStation,
                                   toStation,
                                   distanceTraveled,
                                   transversedStation,
                                   bestSolution);
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
