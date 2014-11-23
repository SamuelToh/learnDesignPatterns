package interfaces.entities;

import classes.exceptions.DuplicateRouteException;
import classes.exceptions.NoSuchRouteException;

/**
 * A modified composite pattern.
 * 
 * A composite pattern would normally consists of nodes and leaf, in this application's context
 * the graph is only compose of nodes so the composition pattern will not have leaf entity like
 * traditional composite pattern do.
 * 
 *
 */
public interface IRailwayEntity {

    char getStationName();
    
    void addOutboundStation(final IRailwayEntity railEntity, 
            final int distance) 
            throws DuplicateRouteException;

    int getDistanceToDestination(final IRailwayEntity destinationStation) 
            throws NoSuchRouteException;
    
    char[] getAllTransversibleLocation();
}
