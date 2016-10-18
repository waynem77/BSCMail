/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
