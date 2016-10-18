/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
