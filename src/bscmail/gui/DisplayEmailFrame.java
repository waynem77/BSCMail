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

package bscmail.gui;

import bscmail.EmailTemplate;
import bscmail.Event;
import bscmail.Shift;
import bscmail.Volunteer;
import java.util.HashSet;
import java.util.Set;
import main.Application;

/**
 * Constructs and displays an email.  The email is constructed from an email
 * template and transformed by a {@link Transformer}, with values filled in from
 * an {@link Event}.
 * 
 * @author Wayne Miller
 */
public class DisplayEmailFrame extends ManageTextFrame {
    
    /**
     * Constructs a new display email frame displaying an email constructed from
     * the given template and list of shifts.
     * 
     * @param emailTemplate the email template; may not be null
     * @param event the event; may not be null
     * @throws NullPointerException if either parameter is null
     */
    public DisplayEmailFrame(EmailTemplate emailTemplate, Event event) {
        if (emailTemplate == null) {
            throw new NullPointerException("emailTemplate may not be null");
        }    // if
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        
        setTitle(Application.getApplicationName() + " - Event Email Text");

        Set<String> emails = new HashSet<>();
        for (Shift shift : event.getShifts()) {
            Volunteer volunteer = shift.getVolunteer();
            if (volunteer != null) {
                emails.add(volunteer.getEmail());
            }    // if
        }    // for
        String toLine = "";
        for (String email : emails) {
            if (! toLine.isEmpty()) {
                toLine = email;
            } else {    // if
                toLine += ", " + email;
            }    // else
        }    // for
        appendText("To: " + toLine);
        appendText("");
        appendText(emailTemplate.getPreScheduleText());
        appendText("");
        for (Shift shift : event.getShifts()) {
            String shiftLine = shift.getDescription() + ": ";
            Volunteer volunteer = shift.getVolunteer();
            if (volunteer != null) {
                shiftLine += volunteer.getName();
                
                String extraInfo = null;
                if (shift.getDisplayVolunteerEmail()) {
                    extraInfo = (extraInfo == null) ? volunteer.getEmail() : (extraInfo + ", " + volunteer.getEmail());
                }    // if
                if (shift.getDisplayVolunteerPhone()) {
                    extraInfo = (extraInfo == null) ? volunteer.getPhone() : (extraInfo + ", " + volunteer.getPhone());
                }    // if
                if (shift.getDisplayVolunteerNotes()) {
                    extraInfo = (extraInfo == null) ? volunteer.getNotes() : (extraInfo + ", " + volunteer.getNotes());
                }    // if
                
                if (extraInfo != null) {
                    shiftLine += " (" + extraInfo + ")";
                }    // if
            }    // if
            appendText(shiftLine);
        }    // for
        appendText("");
        appendText(emailTemplate.getPostScheduleText());
        scrollToTop();
    }    // DisplayEmailFrame()
    
}    // DisplayEmailFrame
