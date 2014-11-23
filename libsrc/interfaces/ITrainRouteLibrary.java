package interfaces;

import classes.exceptions.DuplicateRouteException;
import classes.exceptions.NoSuchRouteException;
import classes.exceptions.NoSuchStationException;
import classes.exceptions.OrphanStationException;

public interface ITrainRouteLibrary {

    void loadTrainMap(String graphValue) throws DuplicateRouteException, OrphanStationException;
    
    String computeRouteDistance(final String tripInput) throws NoSuchStationException, NoSuchRouteException;
    
    String findAllRoutesWithinNStops(final char fromStation, 
            final char toStation, 
            final int distance) throws NoSuchStationException, NoSuchRouteException;
    
    String findAllRoutesWithNStops(final char fromStation, 
                                 final char toStation, 
                                 final int stops) throws NoSuchStationException, NoSuchRouteException;
    
    String findShortestRoute(final char fromStation, 
                           final char toStation) throws NoSuchStationException, NoSuchRouteException;
    
    String findAllRoutesLessThanDistance(final char fromStation, 
                       final char toStation, 
                       final int maxDistance) throws NoSuchStationException, NoSuchRouteException;
}
