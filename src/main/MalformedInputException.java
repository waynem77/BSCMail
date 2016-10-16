/*
 * Copyright © 2014 Wayne Miller
 */

package main;

/**
 * Indicates that an XML file is malformed.
 * 
 * @author Wayne Miller
 */
public class MalformedInputException extends RuntimeException {

    /**
     * Creates a new instance of <code>MalformedInputException</code> without detail message.
     */
    public MalformedInputException() {
    }    // MalformedInputException()

    /**
     * Constructs an instance of <code>MalformedInputException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public MalformedInputException(String msg) {
        super(msg);
    }    // MalformedInputException(String msg)
}    // MalformedInputException
