package interfaces;


import interfaces.entities.IRailwayMap;
import classes.exceptions.DuplicateRouteException;
import classes.exceptions.OrphanStationException;

public interface IGraphDataProcessor {

    IRailwayMap processRailwayGraph(final String graphInput)
                throws DuplicateRouteException, OrphanStationException;
    
}
