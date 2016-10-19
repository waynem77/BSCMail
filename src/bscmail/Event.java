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

package bscmail;

import java.util.*;

/**
 * Represents a BSC event. For the purposes of this class, BSC events have the
 * following properties and components;
 * <ul>
 *   <li>a date,</li>
 *   <li>a location,</li>
 *   <li>a band,</li>
 *   <li>instructors,</li>
 *   <li>prices,</li>
 *   <li>a house manager</li>
 *   <li>an assistant house manager, and</li>
 *   <li>a list of volunteer shifts that may or may not be filled.</li>
 * </ul>
 *
 * @author Wayne Miller
 */
public class Event {

    /**
     * Used to denote various string properties of an event.
     */
    private enum EventProperty {
        BAND,
        INSTRUCTORS,
        GENERAL_PRICE,
        STUDENT_PRICE,
        DISCOUNT_PRICE
    }    // EventProperty

    /**
     * The string properties of the event.
     */
    private final Map<EventProperty, String> stringProperties;
    
    /**
     * The date of the event.  If no date is set for the event, this property is
     * null.
     */
    private Date date;
    
    /**
     * The manager assigned to the event.  If no manager is assigned to the
     * event, this property is null.
     */
    private Manager manager;
    
    /**
     * The assistant manager assigned to the event. If no assistant manager is
     * assigned to the event, this property is null.
     */
    private Manager assistantManager;
    
    /**
     * The volunteer shifts assigned to the event.
     */
    private final List<Shift> shifts;
    
    /**
     * Constructs a new event.
     */
    public Event() {
        stringProperties = new EnumMap<>(EventProperty.class);
        shifts = new ArrayList<>();
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
     * Returns the band for the event. If the event has no band, this method
     * returns an empty string.
     *
     * @return the band for the event
     */
    public String getBand() {
        assertInvariant();
        return getProperty(EventProperty.BAND);
    }    // getBand()

    /**
     * Sets the band for the event. If the parameter is empty or null, the band
     * will be unset.
     *
     * @param band the band for the event
     */
    public void setBand(String band) {
        assertInvariant();
        stringProperties.put(EventProperty.BAND, band);
        assertInvariant();
    }    // setBand()

    /**
     * Returns the instructors for the event. If the event has no instructors,
     * this method returns an empty string.
     *
     * @return the instructors for the event
     */
    public String getInstructors() {
        assertInvariant();
        return getProperty(EventProperty.INSTRUCTORS);
    }    // getInstructors()

    /**
     * Sets the instructors for the event. If the parameter is empty or null,
     * the instructors will be unset.
     *
     * @param instructors the instructors for the event
     */
    public void setInstructors(String instructors) {
        assertInvariant();
        stringProperties.put(EventProperty.INSTRUCTORS, instructors);
        assertInvariant();
    }    // setInstructors()

    /**
     * Returns the general admission price of the event. If the event has no
     * general admission price, this method returns an empty string.
     *
     * @return the general admission price of the event
     */
    public String getGeneralPrice() {
        assertInvariant();
        return getProperty(EventProperty.GENERAL_PRICE);
    }    // getGeneralPrice()

    /**
     * Sets the general admission price of the event. If the parameter is empty
     * or null, the general admission price will be unset.
     *
     * @param price the general admission price of the event
     */
    public void setGeneralPrice(String price) {
        assertInvariant();
        stringProperties.put(EventProperty.GENERAL_PRICE, price);
        assertInvariant();
    }    // setGeneralPrice()

    /**
     * Returns the student/senior price of the event. If the event has no
     * student/senior price, this method returns an empty string.
     *
     * @return the student/senior price of the event
     */
    public String getStudentPrice() {
        assertInvariant();
        return getProperty(EventProperty.STUDENT_PRICE);
    }    // getStudentPrice()

    /**
     * Sets the student/senior price of the event. If the parameter is empty or
     * null, the student/senior price will be unset.
     *
     * @param price the student/senior price of the event
     */
    public void setStudentPrice(String price) {
        assertInvariant();
        stringProperties.put(EventProperty.STUDENT_PRICE, price);
        assertInvariant();
    }    // setStudentPrice()

    /**
     * Returns the discount price of the event. If the event has no discount
     * price, this method returns an empty string.
     *
     * @return the discount price of the event
     */
    public String getDiscountPrice() {
        assertInvariant();
        return getProperty(EventProperty.DISCOUNT_PRICE);
    }    // getDiscountPrice()

    /**
     * Sets the discount price of the event. If the parameter is empty or null,
     * the discount price will be unset.
     *
     * @param price the discount price of the event
     */
    public void setDiscountPrice(String price) {
        assertInvariant();
        stringProperties.put(EventProperty.DISCOUNT_PRICE, price);
        assertInvariant();
    }    // setDiscountPrice()

    /**
     * Returns true if the event has a house manager scheduled.
     *
     * @return true if the event has a house manager scheduled; false otherwise
     */
    public boolean hasManager() {
        assertInvariant();
        return (manager != null);
    }    // public boolean hasManager()

    /**
     * Returns the house manager scheduled for this event. If there is no house
     * manager scheduled (that is, if {@link #hasManager()} returns true), this
     * method returns null.
     *
     * @return the house manager scheduled for this event
     */
    public Manager getManager() {
        assertInvariant();
        return manager;
    }    // getManager()

    /**
     * Assigns a house manager to this event. If the parameter is null, this
     * method unassigns the current house manager from this event.
     *
     * @param manager the house manager to assign to the event
     */
    public void setManager(Manager manager) {
        assertInvariant();
        this.manager = manager;
        assertInvariant();
    }    // setManager()

    /**
     * Returns true if the event has a assistant house manager scheduled.
     *
     * @return true if the event has a assistant house manager scheduled; false
     * otherwise
     * @since 2.1.2
     */
    public boolean hasAssistantManager() {
        assertInvariant();
        return (assistantManager != null);
    }    // hasAssistantManager()

    /**
     * Returns the assistant house manager scheduled for this event. If there is
     * no house manager scheduled (that is, if {@link #hasAssistantManager()}
     * returns true), this method returns null.
     *
     * @return the assistant house manager scheduled for this event
     * @since 2.1.2
     */
    public Manager getAssistantManager() {
        assertInvariant();
        return assistantManager;
    }    // getAssistantManager()

    /**
     * Assigns a assistant house manager to this event. If the parameter is
     * null, this method unassigns the current assistant house manager from this
     * event.
     *
     * @param manager the assistant house manager to assign to the event
     * @since 2.1.2
     */
    public void setAssistantManager(Manager manager) {
        assertInvariant();
        this.assistantManager = manager;
        assertInvariant();
    }    // setAssistantManager()

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
     * Returns a string property of the event corresponding to the given key.
     * If the property is null, this method returns an empty string.
     * 
     * @param key the string property to return; may not be null
     * @return the string property, or an empty string if the string property is
     * null
     */
    private String getProperty(EventProperty key) {
        assert(key != null);
        String property = stringProperties.get(key);
        return (property == null) ? "" : property;
    }    // getProperty()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (stringProperties != null);
        assert (shifts != null);
        assert (! shifts.contains(null));
    }    // assertInvariant()
}    // Event
