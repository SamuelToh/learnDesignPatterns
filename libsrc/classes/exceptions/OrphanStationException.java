package classes.exceptions;

/**
 * Exception raised when a given input graph value consists of a station that is not
 * accessible by other station.
 * <br><br>
 * e.g. AB3, BC2, XY4 - XY4 are the orphan stations where they are not accessible from the
 *                      head node.
 * 
 */
@SuppressWarnings("serial")
public class OrphanStationException extends Exception {
    private static final String ERROR_MESSAGE =
            "Graph input contains orphan station (not accessible by other stations).";
    
    public OrphanStationException() {
        super(ERROR_MESSAGE);
    }
    
    public OrphanStationException(final Throwable cause) {
        super(ERROR_MESSAGE, cause);
    }
}
