/*
 * Copyright © 2014 Wayne Miller
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
