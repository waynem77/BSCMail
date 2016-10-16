/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;

/**
 * Returns the general price of an event.
 * 
 * @author Wayne Miller
 */
public class GeneralPriceProperty implements EventProperty {

    /**
     * Returns the general price of an event.
     * 
     * @param event the event; may not be null
     * @return the general price of {@code event}
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        return event.getGeneralPrice();
    }    // getProperty()

}    // GeneralPriceProperty
