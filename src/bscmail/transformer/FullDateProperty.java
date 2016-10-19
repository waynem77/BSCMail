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

import bscmail.Event;
import java.text.*;
import java.util.Date;

/**
 * Returns a the date of an event, formatted like "Friday January 2".
 * 
 * @author Wayne Miller
 * @since 2.0.2
 */
public class FullDateProperty implements EventProperty {
    
    /**
     * Returns the date of an event, formatted like "Friday January 2". If the
     * event does not have a date set, this method returns an empty string.
     *
     * @param event the event; may not be null
     * @return the date of {@code event} in a long format
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    public String getProperty(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if

        String formattedDate = "";
        Date date = event.getDate();
        if (date != null) {
            DateFormat format = new SimpleDateFormat("EEEEE MMMMM d");
            formattedDate = format.format(date);
        }    // if
        
        return formattedDate;
    }    // getProperty()

}    // FullDateProperty
