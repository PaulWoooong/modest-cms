package cz.koroptev.mcms.util;

/**
 * Generic exception thrown from application.
 */
public class ScmException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public ScmException() {
	super();
    }

    /**
     * @param message
     * @param cause
     */
    public ScmException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     */
    public ScmException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public ScmException(Throwable cause) {
	super(cause);
    }

}
