package classes.entities;

import java.util.HashMap;

import interfaces.entities.IRailwayEntity;
import classes.exceptions.DuplicateRouteException;
import classes.exceptions.NoSuchRouteException;

public class RailwayStation implements IRailwayEntity{

    private char stationName;
    
    private HashMap<IRailwayEntity, Integer> outboundStations;
    
    public RailwayStation(final char stationName) {
        this.stationName = stationName;
        outboundStations = new HashMap<IRailwayEntity, Integer>();
    }

    public char getStationName() {
        return stationName;
    }
   
    public void addOutboundStation(final IRailwayEntity railEntity, final int distance) 
            throws DuplicateRouteException {
        
        if (outboundStations.containsKey(railEntity)) {
            // already has a similar route defined
            throw new DuplicateRouteException(this.getStationName(), railEntity.getStationName());
        }
        
        outboundStations.put(railEntity, distance);
    }

    public int getDistanceToDestination(final IRailwayEntity destination) throws NoSuchRouteException {
        int distanceToDestination = 0; // Default to 0 - traveling to station is impossible 
        
        if (outboundStations.containsKey(destination)) {
            distanceToDestination = outboundStations.get(destination);
        }
        else 
        {
            String routeDetail = this.stationName + "-" + destination;
            throw new NoSuchRouteException(routeDetail);
        }
        
        return distanceToDestination;
    }
    
  
    public char[] getAllTransversibleLocation() {
        char[] transversibleStationNames = new char[outboundStations.size()];
        
        int index = 0;
        for(IRailwayEntity railwayEntity : outboundStations.keySet()) {
            transversibleStationNames[index] = railwayEntity.getStationName();
            index++;
        }
        
        return transversibleStationNames;
    }
}
