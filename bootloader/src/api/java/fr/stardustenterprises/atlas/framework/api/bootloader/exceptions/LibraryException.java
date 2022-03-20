package fr.stardustenterprises.atlas.framework.api.bootloader.exceptions;

import fr.stardustenterprises.atlas.framework.api.bootloader.library.ILibrary;

/**
 * Thrown to indicate an error linked to {@link ILibrary Library} loading.
 *
 * @author xtrm
 * @since 0.0.1
 */
public class LibraryException extends UnsupportedOperationException {
    /**
     * Constructs a LibraryException with no detail message.
     */
    public LibraryException() {
    }

    /**
     * Constructs a LibraryException with the specified
     * detail message.
     *
     * @param message the detail message
     */
    public LibraryException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * <p>Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link Throwable#getCause()} method).  (A {@code null} value
     *                is permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link Throwable#getCause()} method). (A {@code null} value
     *              is permitted, and indicates that the cause is nonexistent
     *              or unknown.)
     */
    public LibraryException(Throwable cause) {
        super(cause);
    }
}
