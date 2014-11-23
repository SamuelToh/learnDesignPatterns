package classes.exceptions;

/**
 * Exception raised when a duplicated route is found from a given input graph value.
 * 
 */
@SuppressWarnings("serial")
public class DuplicateRouteException  extends Exception {
    private static final String ERROR_MESSAGE = 
            "Duplicate route defined for route set (%c-%c).";
    
    public DuplicateRouteException(final char fromStation, final char toStation) {
        super(String.format(ERROR_MESSAGE, fromStation, toStation));
    }

    public DuplicateRouteException(
            final char fromStation, final char toStation, final Throwable cause) {
        super(String.format(ERROR_MESSAGE, fromStation, toStation), cause);
    }
}
