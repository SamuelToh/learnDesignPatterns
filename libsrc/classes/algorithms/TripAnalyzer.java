package classes.algorithms;

import classes.exceptions.NoSuchStationException;
import interfaces.ITrainMapAnalyzer;
import interfaces.entities.IRailwayMap;

public abstract class TripAnalyzer implements ITrainMapAnalyzer {
    protected IRailwayMap trainMap = null;
    
    protected TripAnalyzer(IRailwayMap trainMap) {
        setTrainMap(trainMap);
    }
    
    public void setTrainMap(IRailwayMap trainMap) {
        this.trainMap = trainMap;
    }
    
    protected void checkStationExists(char fromStationName, char toStationName) throws NoSuchStationException {
        if (!trainMap.hasStation(fromStationName)) {
            throw new NoSuchStationException(fromStationName);
        }
        if (!trainMap.hasStation(toStationName)) {
            throw new NoSuchStationException(toStationName);
        }
    }
    
}
