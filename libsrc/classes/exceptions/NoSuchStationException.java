package classes.exceptions;
/**
 * 
 * Exception raised when a route is not reachable from a node to another.
 * 
 * @author Samuel Toh
 */
@SuppressWarnings("serial")
public class NoSuchStationException extends Exception {
    private static final String ERROR_MESSAGE =
            "Station (%c) does not exist.";
    
    public NoSuchStationException(final char notFoundStation) {
        super(String.format(ERROR_MESSAGE, notFoundStation));
    }
    
    public NoSuchStationException(final char notFoundStation, final Throwable cause) {
        super(String.format(ERROR_MESSAGE, notFoundStation), cause);
    }
}