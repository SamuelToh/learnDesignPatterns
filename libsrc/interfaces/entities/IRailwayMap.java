package interfaces.entities;

import classes.entities.RailwayStation;

/**
 * Adapter pattern.
 * 
 * Typedef (C++ terminology) java.util.Hashmap into a RailwayMap object that is relevant to
 * this program.
 */
public interface IRailwayMap { 
    boolean hasStation(final char stationName);
    
    int totalStations();
    
    void insertRailEntity(IRailwayEntity entity);
    
    RailwayStation getStation(final char stationName);
}