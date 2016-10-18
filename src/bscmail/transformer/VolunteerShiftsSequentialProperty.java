/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.transformer;

import bscmail.*;
import java.util.*;

/**
 * Returns all the shifts of an event, in order, separated by newlines.  Each
 * shift is accompanied by the name of the volunteer assigned to it, or "open"
 * if no volunteer is assigned to it.
 * 
 * @author Wayne Miller
 */
public class VolunteerShiftsSequentialProperty implements EventProperty {

    /**
     * Returns all the shifts of an event and the names of the volunteers
     * assigned to them, separated by newlines.
     * 
     * @param event the event; may not be null
     * @return all the shifts of {@code event} and the names of the volunteers
     * assigned to them, separated by newlines
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        
        String formattedString = "";
        List<Shift> shifts = event.getShifts();
        for (Shift shift : shifts) {
            if (! formattedString.isEmpty()) {
                formattedString += "\n";
            }    // if
            formattedString += shift.getDescription() + ": ";
            Volunteer volunteer = shift.getVolunteer();
            if (volunteer != null) {
                formattedString += volunteer.getName();
            } else {    // if
                formattedString += "(open)";
            }    // else
        }    // for
        return formattedString;
    }    // getProperty()

}    // VolunteerShiftsSequentialProperty
