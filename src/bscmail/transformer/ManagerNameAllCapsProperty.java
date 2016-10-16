/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;

/**
 * Returns the name of an event's manager, formatted in all caps.
 * 
 * @author Wayne Miller
 */
public class ManagerNameAllCapsProperty implements EventProperty {

    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        Manager manager = event.getManager();
        String name = (manager == null) ? "" : manager.getName();
        return name.toUpperCase();
    }    // getProperty()

}    // ManagerNameAllCapsProperty
