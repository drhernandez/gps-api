package com.tesis.exceptions;

public class ParseArgsException extends Exception {

    public ParseArgsException(String message) {
        super(message);
    }

    public ParseArgsException(String message, Throwable cause) {
        super(message, cause);
    }
}
