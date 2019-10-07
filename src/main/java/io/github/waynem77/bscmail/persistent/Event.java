/*
 * Copyright © 2014-2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.persistent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Represents a BSC event. For the purposes of this class, BSC events have the
 * following properties and components;
 * <ul>
 *   <li>a date,</li>
 *   <li>a list of event properties that may or may not be filled,</li>
 *   <li>a house manager</li>
 *   <li>an assistant house manager, and</li>
 *   <li>a list of volunteer shifts that may or may not be filled.</li>
 * </ul>
 *
 * @author Wayne Miller
 */
public class Event {
    
    /**
     * The date of the event.  If no date is set for the event, this property is
     * null.
     */
    private Date date;

    /**
     * The event properties assigned to the event.
     */
    private final List<EventProperty> eventProperties;

    /**
     * The volunteer shifts assigned to the event.
     */
    private final List<Shift> shifts;
    
    /**
     * Constructs a new event.
     */
    public Event() {
        shifts = new ArrayList<>();
        eventProperties = new ArrayList<>();
        assertInvariant();
    }    // Event()

    /**
     * Returns true if the event has a date set.
     *
     * @return true if the event has a date set; false otherwise
     */
    public boolean hasDate() {
        assertInvariant();
        return (date != null);
    }    // public boolean hasManager()

    /**
     * Returns the date of the event. If the event has no date, this method
     * returns null.
     *
     * @return the date of the event
     */
    public Date getDate() {
        assertInvariant();
        return date;
    }    // getDate()

    /**
     * Sets the date of the event. If the parameter is null, the date will be
     * unset.
     *
     * @param date the date of the event
     */
    public void setDate(Date date) {
        assertInvariant();
        this.date = date;
        assertInvariant();
    }    // setDate()

    /**
     * Adds a Event Properties to the event.
     *
     * @param eventProperty the event property to add to the event; may not be null
     * @throws NullPointerException if {@code eventProperty} is null
     */
    public void addEventProperty(EventProperty eventProperty) {
        assertInvariant();
        if (eventProperty == null) {
            throw new NullPointerException();
        }    // if
        eventProperties.add(eventProperty);
        assertInvariant();
    }    // addEventProperty()

    /**
     * Returns an unmodifiable list of the event properties associated with the
     * event. The event properties are ordered sequentially in the order they
     * were added.
     *
     * @return an unmodifiable list of the event properties associated with the
     * event
     */
    public List<EventProperty> getEventProperties() {
        assertInvariant();
        return Collections.unmodifiableList(eventProperties);
    }    // getEventProperties()

    /**
     * Adds a volunteer shift to the event.
     *
     * @param shift the shift to add to the event; may not be null
     * @throws NullPointerException if {@code shift} is null
     */
    public void addShift(Shift shift) {
        assertInvariant();
        if (shift == null) {
            throw new NullPointerException();
        }    // if
        shifts.add(shift);
        assertInvariant();
    }    // addShift()

    /**
     * Returns an unmodifiable list of the volunteer shifts associated with the
     * event. Although the list may not be modified, volunteers may be assigned
     * to the shifts via the elements of this list. The shifts are ordered
     * sequentially in the order they were added.
     *
     * @return an unmodifiable list of the volunteer shifts associated with the
     * event
     */
    public List<Shift> getShifts() {
        assertInvariant();
        return Collections.unmodifiableList(shifts);
    }    // getShifts()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (eventProperties != null);
        assert (! eventProperties.contains(null));
        assert (shifts != null);
        assert (! shifts.contains(null));
    }    // assertInvariant()
}    // Event
