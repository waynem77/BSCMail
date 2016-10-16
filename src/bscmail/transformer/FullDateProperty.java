/*
 * Copyright © 2015 Wayne Miller
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
