package com.xj.exceptions;

/**
 * Created by sheeran on 2017/6/14.
 * Talk is cheap show me code.
 */
public class DaoException extends Exception{
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
