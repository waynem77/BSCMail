/*
 * Copyright © 2014 Wayne Miller
 */
package bscmail.transformer;

import bscmail.*;
import java.util.*;

/**
 * Returns all the shifts of an event and the names of their volunteers in
 * condensed form. Consecutive shifts with the same description are combined,
 * with a comma-delimited list of volunteers. If a shift has no volunteer
 * assigned to it, "open" is shown.
 *
 * @author Wayne Miller
 */
public class VolunteerShiftsCompactProperty implements EventProperty {

    /**
     * Returns all the shifts of an event and the names of their volunteers in
     * condensed form.
     *
     * @param event the event; may not be null
     * @return all the shifts of {@code event} and the names of their volunteers
     * in condensed form.
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if

        String formattedString = "";
        String previousShift = null;
        List<Shift> shifts = event.getShifts();
        for (Shift shift : shifts) {
            assert (shift != null);
            if (shift.getDescription().equals(previousShift)) {
                formattedString += ", ";
            } else {    // if
                if (!formattedString.isEmpty()) {
                    formattedString += "\n";
                }    // if
                formattedString += shift.getDescription() + ": ";
            }    // else
            previousShift = shift.getDescription();
            
            Volunteer volunteer = shift.getVolunteer();
            if (volunteer != null) {
                formattedString += volunteer.getName();
            } else {    // if
                formattedString += "(open)";
            }    // else
        }    // for
        return formattedString;
    }    // getProperty()

}    // VolunteerShiftsCompactProperty
