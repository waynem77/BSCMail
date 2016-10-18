/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.transformer;

import bscmail.*;


/**
 * Returns an email subject line containing the event's date.
 * 
 * @author Wayne Miller
 */
public class SubjectLineWithDateProperty implements EventProperty {

    /**
     * Returns an email subject line containing an event's date.  If the event
     * does not have a date set, the return value of this method is undefined.
     * 
     * @param event the event; may not be null
     * @return an email subject line containing the date of {@code event}
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if

        String subject = "BSC Volunteer Schedule for ";
        
        EventProperty dateFormatter = new FullDateProperty();
        String date = dateFormatter.getProperty(event);
        if (date != null) {
            subject += date;
        }    // if
        
        return subject;
    }    // getProperty()

}    // SubjectLineWithDateProperty
