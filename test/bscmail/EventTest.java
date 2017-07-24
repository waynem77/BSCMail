/*
 * Copyright © 2014-2017 its authors.  See the file "AUTHORS" for details.
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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Event}.
 *
 * @author Wayne Miller
 */
public class EventTest {

    /* constructor */

    /**
     * Tests that {@link Event#Event()} does not throw an exception.
     */
    @Test
    public void constructorDoesNotThrowException() {
        Event event = new Event();
    }    // constructorDoesNotThrowException()

    /* hasDate / getDate / setDate */

    /**
     * Tests that {@link Event#hasDate()} does not throw an exception.
     */
    @Test
    public void hasDateDoesNotThrowException() {
        Event event = new Event();

        event.hasDate();
    }    // hasDateDoesNotThrowException()

    /**
     * Tests that {@link Event#hasDate()} returns false when called before
     * {@link Event#setDate(Date)}.
     */
    @Test
    public void hasDateReturnsFalseWhenDateHasNotBeenSet() {
        Event event = new Event();

        boolean received = event.hasDate();

        boolean expected = false;
        assertEquals(expected, received);
    }    // hasDateReturnsFalseWhenDateHasNotBeenSet()

    /**
     * Tests that {@link Event#hasDate()} returns true when called after
     * {@link Event#setDate(Date)}.
     */
    @Test
    public void hasDateReturnsTrueWhenDateHasBeenSet() {
        Event event = new Event();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();

        event.setDate(date);
        boolean received = event.hasDate();

        boolean expected = true;
        assertEquals(expected, received);
    }    // hasDateReturnsTrueWhenDateHasBeenSet()

    /**
     * Tests that {@link Event#hasDate()} returns false when called after
     * {@link Event#setDate(Date)}, called with null.
     */
    @Test
    public void hasDateReturnsFalseWhenDateHasBeenSetToNull() {
        Event event = new Event();
        Calendar calendar = Calendar.getInstance();
        Date date = null;

        event.setDate(date);
        boolean received = event.hasDate();

        boolean expected = false;
        assertEquals(expected, received);
    }    // hasDateReturnsFalseWhenDateHasBeenSetToNull()

    /**
     * Tests that {@link Event#getDate()} does not throw an exception.
     */
    @Test
    public void getDateDoesNotThrowException() {
        Event event = new Event();

        event.getDate();
    }    // getDateDoesNotThrowException()

    /**
     * Tests that {@link Event#getDate()} returns null when called before
     * {@link Event#setDate(Date)}.
     */
    @Test
    public void getDateReturnsNullWhenDateHasNotBeenSet() {
        Event event = new Event();

        Date received = event.getDate();

        Date expected = null;
        assertEquals(expected, received);
    }    // getDateReturnsNullWhenDateHasNotBeenSet()

    /**
     * Tests that {@link Event#getDate()} returns the correct value when
     * called after {@link Event#setDate(Date)}.
     */
    @Test
    public void getDateReturnsValuePassedToSetDate() {
        Event event = new Event();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();

        event.setDate(date);
        Date received = event.getDate();

        Date expected = date;
        assertEquals(expected, received);
    }    // getDateReturnsValuePassedToSetDate()

    /**
     * Tests that {@link Event#getDate()} returns null when called after
     * {@link Event#setDate(Date)}, called with null.
     */
    @Test
    public void getDateReturnsNullWhenDateHasBeenSetToNull() {
        Event event = new Event();
        Date date = null;

        event.setDate(date);
        Date received = event.getDate();

        Date expected = null;
        assertEquals(expected, received);
    }    // testGetDateAfterSetDateNull()

    /**
     * Tests that {@link Event#setDate(Date)} does not throw an exception when
     * date is null.
     */
    @Test
    public void setDateDoesNotThrowAnExceptionWhenDateIsNull() {
        Event event = new Event();
        Date date = null;

        event.setDate(date);
    }    // setDateDoesNotThrowAnExceptionWhenDateIsNull()

    /**
     * Tests that {@link Event#setDate(Date)} does not throw an exception when
     * date is not null.
     */
    @Test
    public void setDateDoesNotThrowAnExceptionWhenDateIsNotNull() {
        Event event = new Event();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();

        event.setDate(date);
    }    // setDateDoesNotThrowAnExceptionWhenDateIsNotNull()

    /**
     * Tests that {@link Event#setDate(Date)} does not throw an exception when
     * called twice with different arguments.
     */
    @Test
    public void setDateDoesNotThrowAnExceptionWhenCalledTwiceWithDifferentArguments() {
        Event event = new Event();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        calendar.add(Calendar.DATE, 1);

        event.setDate(date);
    }    // setDateDoesNotThrowAnExceptionWhenCalledTwiceWithDifferentArguments()

    /**
     * Tests that {@link Event#setDate(Date)} unsets the existing date when
     * called with null.
     */
    @Test
    public void setDateUnsetsExistingDateWhenCalledWithNull() {
        Event event = new Event();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        date = null;

        event.setDate(date);

        boolean expectedFlag = false;
        boolean receivedFlag = event.hasDate();
        assertEquals(expectedFlag, receivedFlag);

        Date expectedDate = date;
        Date receivedDate = event.getDate();
        assertEquals(expectedDate, receivedDate);
    }    // setDateUnsetsExistingDateWhenCalledWithNull()

    /* addEventProperty / getEventProperties */

    /**
     * Tests that {@link Event#addEventProperty(EventProperty)} throws a
     * {@link NullPointerException} when eventProperty is null.
     */
    @Test(expected = NullPointerException.class)
    public void addEventPropertyThrowsExceptionWhenEventPropertyIsNull() {
        Event event = new Event();
        EventProperty eventProperty = null;

        event.addEventProperty(eventProperty);
    }    // addEventPropertyThrowsExceptionWhenEventPropertyIsNull()

    /**
     * Tests that {@link Event#addEventProperty(EventProperty)} does not throw
     * an exception when eventProperty is not null.
     */
    @Test
    public void addEvenetPropertyDoesNotThrowExceptionWhenEventPropertyIsNotNull() {
        Event event = new Event();
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        event.addEventProperty(eventProperty);
    }    // addEvenetPropertyDoesNotThrowExceptionWhenEventPropertyIsNotNull

    /**
     * Tests that {@link Event#getEventProperties()} does not throw an exception
     * when no eventProperties have been added.
     */
    @Test
    public void getEventPropertiesDoesNotThrowExceptionWhenThereAreNoEventProperties() {
        Event event = new Event();

        event.getEventProperties();
    }    // getEventPropertiesDoesNotThrowExceptionWhenThereAreNoEventProperties()

    /**
     * Tests that {@link Event#getEventProperties()} returns an empty list when
     * no eventProperties have been added.
     */
    @Test
    public void getEventPropertiesReturnsEmptyListWhenThereAreNoEventProperties() {
        Event event = new Event();

        List<EventProperty> received = event.getEventProperties();

        List<EventProperty> expected = Collections.emptyList();
        assertEquals(expected, received);
    }    // getEventPropertiesReturnsEmptyListWhenThereAreNoEventProperties()

    /**
     * Tests that {@link Event#getEventProperties()} does not throw an exception
     * after eventProperties have been added.
     */
    @Test
    public void getEventPropertiesDoesNotThrowExceptionWhenThereAreEventProperties() {
        Event event = new Event();
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        event.addEventProperty(eventProperty);

        event.getEventProperties();
    }    // getEventPropertiesDoesNotThrowExceptionWhenThereAreEventProperties()

    /**
     * Tests that {@link Event#getEventProperties()} returns the correct value
     * after eventProperties have been added.
     */
    @Test
    public void getEventPropertiesReturnsCorrectValueWhenThereAreEventProperties() {
        Event event = new Event();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("foo", "one"),
                new EventProperty("bar", "two"),
                new EventProperty("baz", "three"));
        for (EventProperty eventProperty : eventProperties) {
            event.addEventProperty(eventProperty);
        }    // for

        List<EventProperty> received = event.getEventProperties();

        List<EventProperty> expected = eventProperties;
        assertEquals(expected, received);
    }    // getEventPropertiesReturnsCorrectValueWhenThereAreEventProperties()

    /**
     * Tests that the list returned from {@link Event#getEventProperties()} is
     * unmodifiable.
     */
    @Test
    public void getEventPropertiesReturnsImmutableList() {
        Event event = new Event();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("foo", "one"),
                new EventProperty("bar", "two"),
                new EventProperty("baz", "three"));
        for (EventProperty eventProperty : eventProperties) {
            event.addEventProperty(eventProperty);
        }    // for

        List<EventProperty> list = event.getEventProperties();

        EventProperty eventProperty = new EventProperty("smurf", "four");
        try {
            list.add(eventProperty);
            fail("able to add eventProperties to return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.remove(0);
            fail("able to remove eventProperties from return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.set(0, eventProperty);
            fail("able to replace eventProperties in return value");
        } catch (RuntimeException e) {    // try
        }    // catch
    }    // getEventPropertiesReturnsImmutableList()

    /**
     * Tests that the elements of the list returned from
     * {@link Event#getEventProperties()} are modifiable.
     */
    @Test
    public void getEventPropertiesReturnsListWhoseElementsAreMutable() {
        Event event = new Event();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("foo", "one"),
                new EventProperty("bar", "two"),
                new EventProperty("baz", "three"));
        for (EventProperty eventProperty : eventProperties) {
            event.addEventProperty(eventProperty);
        }    // for

        List<EventProperty> list = event.getEventProperties();

        int index = 0;
        String value = "smurf";
        list.get(index).setValue(value);
    }    // getEventPropertiesReturnsListWhoseElementsAreMutable()

    /**
     * Tests that changes to the elements of the list returned from
     * {@link Event#getEventProperties()} do not affect the event.
     */
    @Test
    public void changesToReturnValueOfGetEventPropertiesDoNotAffectEvent() {
        Event event = new Event();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("foo", "one"),
                new EventProperty("bar", "two"),
                new EventProperty("baz", "three"));
        for (EventProperty eventProperty : eventProperties) {
            event.addEventProperty(eventProperty);
        }    // for

        List<EventProperty> list = event.getEventProperties();

        int index = 0;
        String value = "smurf";
        list.get(index).setValue(value);

        list = event.getEventProperties();
        String expected = value;
        String received = list.get(index).getValue();
        assertEquals(expected, received);
    }    // changesToReturnValueOfGetEventPropertiesDoNotAffectEvent()

    /* addShift / getShifts */

    /**
     * Tests that {@link Event#addShift(Shift)} throws a
     * {@link NullPointerException} when shift is null.
     */
    @Test(expected = NullPointerException.class)
    public void addShiftThrowsExceptionWhenShiftIsNull() {
        Event event = new Event();
        Shift shift = null;

        event.addShift(shift);
    }    // addShiftThrowsExceptionWhenShiftIsNull()

    /**
     * Tests that {@link Event#addShift(Shift)} does not throw an exception
     * when shift is not null.
     */
    @Test
    public void addShiftDoesNotThrowExceptionWhenShiftIsNotNull() {
        Event event = new Event();
        String description = "foo";
        List<Role> roles = new LinkedList<>();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        event.addShift(shift);
    }    // addShiftDoesNotThrowExceptionWhenShiftIsNotNull

    /**
     * Tests that {@link Event#getShifts()} does not throw an exception when no
     * shifts have been added.
     */
    @Test
    public void getShiftsDoesNotThrowExceptionWhenThereAreNoShifts() {
        Event event = new Event();

        event.getShifts();
    }    // getShiftsDoesNotThrowExceptionWhenThereAreNoShifts()

    /**
     * Tests that {@link Event#getShifts()} returns an empty list when no shifts
     * have been added.
     */
    @Test
    public void getShiftsReturnsEmptyListWhenThereAreNoShifts() {
        Event event = new Event();

        List<Shift> received = event.getShifts();

        List<Shift> expected = Collections.emptyList();
        assertEquals(expected, received);
    }    // getShiftsReturnsEmptyListWhenThereAreNoShifts()

    /**
     * Tests that {@link Event#getShifts()} does not throw an exception after
     * shifts have been added.
     */
    @Test
    public void getShiftsDoesNotThrowExceptionWhenThereAreShifts() {
        Event event = new Event();
        String description = "foo";
        List<Role> roles = new LinkedList<>();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        event.addShift(shift);

        event.getShifts();
    }    // getShiftsDoesNotThrowExceptionWhenThereAreShifts()

    /**
     * Tests that {@link Event#getShifts()} returns the correct value after
     * shifts have been added.
     */
    @Test
    public void getShiftsReturnsCorrectValueWhenThereAreShifts() {
        Event event = new Event();
        List<Shift> shifts = Arrays.asList(new Shift("foo", new LinkedList<Role>(), true, true, true),
                new Shift("bar", new LinkedList<Role>(), true, true, true),
                new Shift("baz", new LinkedList<Role>(), true, true, true));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for

        List<Shift> received = event.getShifts();

        List<Shift> expected = shifts;
        assertEquals(expected, received);
    }    // getShiftsReturnsCorrectValueWhenThereAreShifts()

    /**
     * Tests that the list returned from {@link Event#getShifts()} is
     * unmodifiable.
     */
    @Test
    public void getShiftsReturnsImmutableList() {
        Event event = new Event();
        List<Shift> shifts = Arrays.asList(new Shift("foo", new LinkedList<Role>(), true, true, true),
                new Shift("bar", new LinkedList<Role>(), true, true, true),
                new Shift("baz", new LinkedList<Role>(), true, true, true));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for

        List<Shift> list = event.getShifts();

        Shift shift = new Shift("smurf", new LinkedList<Role>(), true, true, true);
        try {
            list.add(shift);
            fail("able to add shifts to return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.remove(0);
            fail("able to remove shifts from return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.set(0, shift);
            fail("able to replace shifts in return value");
        } catch (RuntimeException e) {    // try
        }    // catch
    }    // getShiftsReturnsImmutableList()

    /**
     * Tests that the elements of the list returned from
     * {@link Event#getShifts()} are modifiable.
     */
    @Test
    public void getShiftsReturnsListWhoseElementsAreMutable() {
        Event event = new Event();
        List<Shift> shifts = Arrays.asList(new Shift("foo", new LinkedList<Role>(), true, true, true),
                new Shift("bar", new LinkedList<Role>(), true, true, true),
                new Shift("baz", new LinkedList<Role>(), true, true, true));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for

        List<Shift> list = event.getShifts();

        int index = 0;
        Volunteer volunteer = new Volunteer("smurf", "snork", "gummibear", "thundercat", true);
        list.get(index).setVolunteer(volunteer);
    }    // getShiftsReturnsListWhoseElementsAreMutable()

    /**
     * Tests that changes to the elements of the list returned from
     * {@link Event#getShifts()} do not affect the event.
     */
    @Test
    public void changesToReturnValueOfGetShiftsDoNotAffectEvent() {
        Event event = new Event();
        List<Shift> shifts = Arrays.asList(new Shift("foo", new LinkedList<Role>(), true, true, true),
                new Shift("bar", new LinkedList<Role>(), true, true, true),
                new Shift("baz", new LinkedList<Role>(), true, true, true));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for

        List<Shift> list = event.getShifts();

        int index = 0;
        Volunteer volunteer = new Volunteer("smurf", "snork", "gummibear", "thundercat", true);
        list.get(index).setVolunteer(volunteer);

        list = event.getShifts();
        Volunteer expected = volunteer;
        Volunteer received = list.get(index).getVolunteer();
        assertEquals(expected, received);
    }    // changesToReturnValueOfGetShiftsDoNotAffectEvent()

}    // EventTest
