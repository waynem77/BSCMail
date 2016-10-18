/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.transformer;

import bscmail.*;

/**
 * Returns the student/senior price of an event.
 * 
 * @author Wayne Miller
 */
public class StudentPriceProperty implements EventProperty {

    /**
     * Returns the student/senior price of an event.
     * 
     * @param event the event; may not be null
     * @return the student/senior price of {@code event}
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        return event.getStudentPrice();
    }    // getProperty()

}    // StudentPriceProperty
