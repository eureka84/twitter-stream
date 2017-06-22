package org.test.twitter.exceptions;

/**
 * @author asciarra
 */
public class StatusRetrievalException extends RuntimeException {
    private static final long serialVersionUID = 2675654030518131410L;

    public StatusRetrievalException(Throwable cause) {
        super(cause);
    }

}
