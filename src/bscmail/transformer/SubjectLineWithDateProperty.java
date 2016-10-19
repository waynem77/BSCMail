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
