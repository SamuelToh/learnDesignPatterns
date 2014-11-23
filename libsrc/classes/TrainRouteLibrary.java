package classes;

import classes.algorithms.SearchParameter;
import classes.algorithms.TripWithinStopsAnalzyer;
import classes.algorithms.TripDistanceAnalyzer;
import classes.algorithms.TripShortestDistanceAnalzyer;
import classes.algorithms.TripWithExactStopAnalzyer;
import classes.algorithms.TripsLessThanDistance;
import classes.entities.RailwayMap;
import classes.exceptions.DuplicateRouteException;
import classes.exceptions.NoSuchRouteException;
import classes.exceptions.NoSuchStationException;
import classes.exceptions.OrphanStationException;
import interfaces.ITrainMapAnalyzer;
import interfaces.ITrainRouteLibrary;
import interfaces.entities.IRailwayMap;


public class TrainRouteLibrary implements ITrainRouteLibrary {
    
    GraphDataProcessor dataProcessor;
    IRailwayMap trainMap;
    
    /* Search algorithms */
    ITrainMapAnalyzer algorithm;
    SearchParameter parameters;
    
    public TrainRouteLibrary() {
        dataProcessor = new GraphDataProcessor();
        trainMap = new RailwayMap();
    }

    @Override
    public void loadTrainMap(String graphValue) 
            throws DuplicateRouteException, OrphanStationException {
        trainMap = dataProcessor.processRailwayGraph(graphValue);
    }

    @Override
    public String computeRouteDistance(final String tripInput) throws NoSuchStationException, NoSuchRouteException {
        parameters = new SearchParameter();
        parameters.setSearchValue(tripInput);
        
        algorithm = new TripDistanceAnalyzer(trainMap);
        return algorithm.search(parameters);
    }

    // Solutions with exact N stops 
    @Override
    public String findAllRoutesWithNStops(final char fromStation, 
                                        final char toStation,
                                        final int stops) throws NoSuchStationException, NoSuchRouteException {
        parameters = new SearchParameter();
        parameters.setEffortValue(stops);
        parameters.setFromRailwayEntityName(fromStation);
        parameters.setToRailwayEntityName(toStation);
        
        algorithm = new TripWithExactStopAnalzyer(trainMap);
        return algorithm.search(parameters);
    }
    
    @Override
    public String findAllRoutesWithinNStops(char fromStation, char toStation,
            int stops) throws NoSuchStationException, NoSuchRouteException {
        parameters = new SearchParameter();
        parameters.setEffortValue(stops);
        parameters.setFromRailwayEntityName(fromStation);
        parameters.setToRailwayEntityName(toStation);
        
        algorithm = new TripWithinStopsAnalzyer(trainMap);
        return algorithm.search(parameters);
    }

    @Override
    public String findShortestRoute(final char fromStation, 
                                  final char toStation) throws NoSuchStationException, NoSuchRouteException {
        parameters = new SearchParameter();
        parameters.setFromRailwayEntityName(fromStation);
        parameters.setToRailwayEntityName(toStation);
        
        algorithm = new TripShortestDistanceAnalzyer(trainMap);
        return algorithm.search(parameters);
    }

    @Override
    public String findAllRoutesLessThanDistance(final char fromStation, 
                              final char toStation,
                              final int maxDistance) throws NoSuchStationException, NoSuchRouteException {
        parameters = new SearchParameter();
        parameters.setFromRailwayEntityName(fromStation);
        parameters.setToRailwayEntityName(toStation);
        parameters.setEffortValue(maxDistance);

        algorithm = new TripsLessThanDistance(trainMap);
        return algorithm.search(parameters);
    }

}
