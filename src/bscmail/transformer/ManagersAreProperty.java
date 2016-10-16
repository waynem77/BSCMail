/*
 * Copyright © 2015 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;

/**
 * Returns a subject and verb for the managers assigned to an event. If the
 * event has both a manager and an assistant manager, this property returns the
 * string "House Managers for this event are". If the event has a manager but no
 * assistant manager, this property returns the string "House Manager for this
 * event is". If the event has no manager, this property returns an empty string
 * (regardless of whether or not the event has an assistant manager).
 *
 * @author Wayne Miller
 * @since 2.1.2
 */
public class ManagersAreProperty implements EventProperty {

    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        Manager manager = event.getManager();
        if (manager == null) {
            return "";
        }    // if
        
        Manager assistantManager = event.getAssistantManager();
        
        String houseManagers = (assistantManager == null) ? "House Manager" : "House Managers";
        String verb = (assistantManager == null) ? "is" : "are";
        return houseManagers + " for this event " + verb;
    }    // getProperty()

}    // ManagersAreProperty
