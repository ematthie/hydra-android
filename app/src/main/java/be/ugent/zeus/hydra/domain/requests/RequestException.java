package be.ugent.zeus.hydra.domain.requests;

/**
 * Exception thrown by a {@link Request} when something goes wrong while producing the data.
 *
 * @author Niko Strijbol
 */
public class RequestException extends Exception {

    public RequestException() {
        super();
    }

    public RequestException(Throwable cause) {
        super(cause);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestException(String message) {
        super(message);
    }
}