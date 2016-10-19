/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * BSCMail is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BSCMail is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BSCMail.  If not, see <http://www.gnu.org/licenses/>.
 */

package bscmail.transformer;

import bscmail.*;
import java.util.*;

/**
 * Returns the email addresses of the manager, assistant manager, and volunteers
 * assigned to an event, separated by commas. Duplicate email addresses are
 * merged.
 *
 * @author Wayne Miller
 */
public class EmailAddressesProperty implements EventProperty {

    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        Set<String> emails = new LinkedHashSet<>();
        if (event.hasManager()) {
            Manager manager = event.getManager();
            String email = manager.getEmail();
            emails.add(email);
        }    // if
        if (event.hasAssistantManager()) {
            Manager manager = event.getAssistantManager();
            String email = manager.getEmail();
            emails.add(email);
        }    // if
        for (Shift shift : event.getShifts()) {
            assert (shift != null);
            Volunteer volunteer = shift.getVolunteer();
            if (volunteer != null) {
                emails.add(volunteer.getEmail());
            }    // if
        }    // for
        
        final String DELIMITER = ", ";
        String addressList = "";
        for (String email : emails) {
            if (!addressList.isEmpty()) {
                addressList += DELIMITER;
            }    // if
            addressList += email;
        }    // for
        return addressList;
    }    // getProperty()

}    // EmailAddressesProperty
