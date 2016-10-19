/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
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
