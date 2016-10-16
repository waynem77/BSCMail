/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;

/**
 * Returns the discount price of an event.
 * 
 * @author Wayne Miller
 */
public class DiscountPriceProperty implements EventProperty {

    /**
     * Returns the discount price of an event.
     * 
     * @param event the event; may not be null
     * @return the discount price of {@code event}
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        return event.getDiscountPrice();
    }    // getProperty()

}    // DiscountPriceProperty
