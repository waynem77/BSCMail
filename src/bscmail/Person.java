/*
 * Copyright © 2014 Wayne Miller
 */
package bscmail;

/**
 * Represents a person affiliated with BSC.
 *
 * @author Wayne Miller
 */
public interface Person {

    /**
     * Returns the person's name.
     *
     * @return the person's name
     */
    public String getName();

    /**
     * Returns the person's email address.
     *
     * @return the person's email address
     */
    public String getEmail();

}    // Person