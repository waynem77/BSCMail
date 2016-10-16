/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;

/**
 * Returns the phone number of an event's manager.
 * 
 * @author Wayne Miller
 */
public class ManagerPhoneProperty implements EventProperty {

    /**
     * Returns the phone number of an event's manager.
     * 
     * @param event the event; may not be null
     * @return the phone number of {@code event}'s manager
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        Manager manager = event.getManager();
        return (manager == null) ? "" : manager.getPhone();
    }    // getProperty()

}    // ManagerPhoneProperty
