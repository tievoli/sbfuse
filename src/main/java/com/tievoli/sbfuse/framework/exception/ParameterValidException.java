package com.tievoli.sbfuse.framework.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 参数检查异常.
 */
public class ParameterValidException extends RuntimeException {

    //参数静态校验错误信息
    private final List<ObjectError> allErrors;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ParameterValidException(String message, List<ObjectError> allErrors) {
        super(message);
        this.allErrors = allErrors;
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public ParameterValidException(String message, Throwable cause, List<ObjectError> allErrors) {
        super(message, cause);
        this.allErrors = allErrors;
    }

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public ParameterValidException(Throwable cause, List<ObjectError> allErrors) {
        super(cause);
        this.allErrors = allErrors;
    }

    //获取所有的AllErrors
    public List<ObjectError> getAllErrors() {
        return allErrors;
    }
}
