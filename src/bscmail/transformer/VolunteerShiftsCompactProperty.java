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
