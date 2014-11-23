package interfaces;

import interfaces.entities.IRailwayMap;
import classes.algorithms.SearchParameter;
import classes.exceptions.NoSuchRouteException;
import classes.exceptions.NoSuchStationException;

/*
 * Strategy pattern
 */
public interface ITrainMapAnalyzer {
    
    void setTrainMap(IRailwayMap trainMap);
    
    String search(final SearchParameter searchParam) 
            throws NoSuchStationException, NoSuchRouteException;

}
