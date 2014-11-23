package TestsGraphDataProcessor;

import static org.junit.Assert.*;
import interfaces.IGraphDataProcessor;
import interfaces.entities.IRailwayMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.GraphDataProcessor;
import classes.entities.RailwayStation;
import classes.exceptions.DuplicateRouteException;
import classes.exceptions.NoSuchStationException;
import classes.exceptions.OrphanStationException;

/**
 * Test the GraphDataProcessor class.
 * @author Samuel Toh
 *
 */
public class TGraphDataProcessor {
    
    private final String GOOD_GRAPH_INPUT = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    private IGraphDataProcessor graphDataProcessor;
  

    @Before
    public void setUp() throws Exception {
        graphDataProcessor = new GraphDataProcessor();
    }
    
    @After
    public void tearDown() throws Exception {
        graphDataProcessor = null;
    }
    
    @Test
    public void testNoExceptionParsingValidGraph() 
            throws DuplicateRouteException, OrphanStationException {
        graphDataProcessor.processRailwayGraph(GOOD_GRAPH_INPUT);
    }
    
    @Test
    public void testAllStationsAreStoredForValidGraph() 
        throws DuplicateRouteException, OrphanStationException {
        IRailwayMap trainMap = graphDataProcessor.processRailwayGraph(GOOD_GRAPH_INPUT);
        assertEquals(5, trainMap.totalStations());
        assertEquals(true, trainMap.hasStation('A'));
        assertEquals(true, trainMap.hasStation('B'));
        assertEquals(true, trainMap.hasStation('C'));
        assertEquals(true, trainMap.hasStation('D'));
        assertEquals(true, trainMap.hasStation('E'));
    }
    
    @Test
    public void testAllLinkagesAreParsedForValidGraph() 
        throws DuplicateRouteException, OrphanStationException, NoSuchStationException {
        IRailwayMap trainMap = graphDataProcessor.processRailwayGraph(GOOD_GRAPH_INPUT);
        
        RailwayStation stationA = trainMap.getStation('A');
        assertEquals(3, stationA.getAllTransversibleLocation().length);
        
        RailwayStation stationB = trainMap.getStation('B');
        assertEquals(1, stationB.getAllTransversibleLocation().length);
        
        RailwayStation stationC = trainMap.getStation('C');
        assertEquals(2, stationC.getAllTransversibleLocation().length);
        
        RailwayStation stationD = trainMap.getStation('D');
        assertEquals(2, stationD.getAllTransversibleLocation().length);
        
        RailwayStation stationE = trainMap.getStation('E');
        assertEquals(1, stationE.getAllTransversibleLocation().length);        
    }

}
