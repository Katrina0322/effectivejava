package com.oneapm.instrument;

/**
 * filename: InstrumentException
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 3:01 PM
 */
public class InstrumentException extends Exception {

    private static final long serialVersionUID = 7891672495740281077L;

    public InstrumentException() {
    }

    public InstrumentException(Throwable cause) {
        super(cause);
    }

    public InstrumentException(String message) {
        super(message);
    }

    public InstrumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
