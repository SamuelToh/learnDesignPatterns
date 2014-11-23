package classes.exceptions;

@SuppressWarnings("serial")
public class NoSuchRouteException extends Exception {
    private static final String ERROR_MESSAGE = "NO SUCH ROUTE, route %s don't exist.";
    
    public NoSuchRouteException(final String badRoute) {
        super(String.format(ERROR_MESSAGE, badRoute));
    }
    
    public NoSuchRouteException(final String badRoute, final Throwable cause) {
        super(String.format(ERROR_MESSAGE, badRoute, cause));
    }
}
