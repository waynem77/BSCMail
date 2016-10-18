/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
