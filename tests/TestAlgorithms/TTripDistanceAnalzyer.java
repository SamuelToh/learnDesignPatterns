package TestAlgorithms;

import static org.junit.Assert.*;
import interfaces.IGraphDataProcessor;
import interfaces.ITrainMapAnalyzer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.GraphDataProcessor;
import classes.algorithms.SearchParameter;
import classes.algorithms.TripDistanceAnalyzer;
import classes.exceptions.NoSuchRouteException;
import classes.exceptions.NoSuchStationException;

public class TTripDistanceAnalzyer {

    private final String GOOD_GRAPH_INPUT = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    private IGraphDataProcessor graphDataProcessor;
    private SearchParameter parameters;
    private ITrainMapAnalyzer algorithm;
  

    @Before
    public void setUp() throws Exception {
        graphDataProcessor = new GraphDataProcessor();
        
        parameters = new SearchParameter();
        parameters.setSearchValue("A-E-B-C-D");
        
        algorithm = new TripDistanceAnalyzer(graphDataProcessor.processRailwayGraph(GOOD_GRAPH_INPUT));
    }
    
    @After
    public void tearDown() throws Exception {
        graphDataProcessor = null;
        algorithm = null;
        parameters = null;
    }
    
  
    @Test
    public void testCalculateDistance() throws NoSuchStationException, NoSuchRouteException {
        String result = algorithm.search(parameters);
        assertEquals("22", result);
    }
    
}
