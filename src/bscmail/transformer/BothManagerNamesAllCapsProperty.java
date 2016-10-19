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
 * Returns the names of an event's manager and assistant manager, formatted in
 * all caps.
 * 
 * If the event has both a manager and an assistant manager, this property
 * returns a string like
 * <blockquote>MANAGER and ASSISTANT MANAGER</blockquote>
 * If the event has a manager but no assistant manager, this property returns a
 * string like
 * <blockquote>MANAGER</blockquote>
 * If the event has no manager, this property returns an empty string
 * (regardless of whether or not the event has an assistant manager).
 * 
 * @author Wayne Miller
 * @since 2.1.2
 */
public class BothManagerNamesAllCapsProperty implements EventProperty {

    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        Manager manager = event.getManager();
        if (manager == null) {
            return "";
        }    // if
        
        String returnValue = manager.getName().toUpperCase();
        Manager assistantManager = event.getAssistantManager();
        if (assistantManager != null) {
            returnValue += " and " + assistantManager.getName().toUpperCase();
        }    // if
        return returnValue;
    }    // getProperty()

}    // BothManagerNamesAllCapsProperty
