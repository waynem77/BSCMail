/*
 * Copyright © 2014 Wayne Miller
 */
package bscmail.transformer;

import bscmail.*;

/**
 * Returns the first name of an event's manager.
 * 
 * @author Wayne Miller
 */
public class ManagerFirstNameProperty implements EventProperty {

    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        Manager manager = event.getManager();
        String name = (manager == null) ? "" : manager.getName();
        String[] names = name.split("\\s+");
        assert (names != null);
        assert (names.length > 0);
        return names[0];
    }    // getProperty()

}    // ManagerFirstNameProperty
