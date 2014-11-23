package classes.entities;

import interfaces.entities.IRailwayMap;
import interfaces.entities.IRailwayEntity;

import java.util.HashMap;

/**
 * Adapter pattern.
 * 
 * Typedef RailwayMap as Hashmap<Character, IRailwayEntity> 
 */
@SuppressWarnings("serial")
public class RailwayMap extends HashMap<Character, IRailwayEntity> implements IRailwayMap {

    @Override
    public boolean hasStation(final char stationName) {
        return this.containsKey(stationName);
    }

    @Override
    public RailwayStation getStation(final char stationName) {
        
        if (!hasStation(stationName) || !(this.get(stationName) instanceof RailwayStation)) {
            return null;
        }
        
        return (RailwayStation) this.get(stationName);
    }

    @Override
    public int totalStations() {
        return this.size();
    }

    @Override
    public void insertRailEntity(IRailwayEntity entity) {
        if (entity != null) {
            this.put(entity.getStationName(), entity);
        }
    }
}