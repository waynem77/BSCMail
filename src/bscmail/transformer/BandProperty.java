/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;

/**
 * Returns the band scheduled for an event.
 * 
 * @author Wayne Miller
 */
public class BandProperty implements EventProperty {

    /**
     * Returns the band scheduled for an event.
     * 
     * @param event the event; may not be null
     * @return the band scheduled for {@code event}
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        return event.getBand();
    }    // getProperty()

}    // BandProperty
