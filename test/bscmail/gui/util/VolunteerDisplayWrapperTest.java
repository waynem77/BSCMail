/*
 * Copyright © 2017 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui.util;

import bscmail.Volunteer;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link VolunteerDisplayWrapper}.
 *
 * @author Wayne Miller
 */
public class VolunteerDisplayWrapperTest {

    /* constructor */

    /**
     * Tests that
     * {@link VolunteerDisplayWrapper#VolunteerDisplayWrapper(bscmail.Volunteer)}
     * does not throw an exception when volunteer is null.
     */
    @Test
    public void constructorDoesNotThrowNullWhenVolunteerIsNull() {
        Volunteer volunteer = null;

        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);
    }    // constructorDoesNotThrowNullWhenVolunteerIsNull()

    /**
     * Tests
     * {@link VolunteerDisplayWrapper#VolunteerDisplayWrapper(bscmail.Volunteer)}
     * does not throw an exception when volunteer is not null.
     */
    @Test
    public void constructorDoesNotThrowNullWhenVolunteerIsNotNull() {
        Volunteer volunteer = new Volunteer("foo", "bar", "baz", "smurf");

        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);
    }    // constructorDoesNotThrowNullWhenVolunteerIsNotNull()

    /* getVolunteer() */

    /**
     * Tests that {@link VolunteerDisplayWrapper#getVolunteer()} does not throw
     * an exception when volunteer is null.
     */
    @Test
    public void getVolunteerDoesNotThrowNullWhenVolunteerIsNull() {
        Volunteer volunteer = null;
        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);

        wrapper.getVolunteer();
    }    // getVolunteerDoesNotThrowNullWhenVolunteerIsNull()

    /**
     * Tests that {@link VolunteerDisplayWrapper#getVolunteer()} returns the
     * correct value when volunteer is null.
     */
    @Test
    public void getVolunteerReturnsCorrectValueWhenVolunteerIsNull() {
        Volunteer volunteer = null;
        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);

        Volunteer received = wrapper.getVolunteer();

        Volunteer expected = volunteer;
        assertEquals(expected, received);
    }    // getVolunteerDoesNotThrowNullWhenVolunteerIsNull()

    /**
     * Tests that {@link VolunteerDisplayWrapper#getVolunteer()} does not throw
     * an exception when volunteer is not null.
     */
    @Test
    public void getVolunteerDoesNotThrowNullWhenVolunteerIsNotNull() {
        Volunteer volunteer = new Volunteer("foo", "bar", "baz", "smurf");
        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);

        wrapper.getVolunteer();
    }    // getVolunteerDoesNotThrowNullWhenVolunteerIsNotNull()

    /**
     * Tests that {@link VolunteerDisplayWrapper#getVolunteer()} returns the
     * correct value when volunteer is not null.
     */
    @Test
    public void getVolunteerReturnsCorrectValueWhenVolunteerIsNotNull() {
        Volunteer volunteer = new Volunteer("foo", "bar", "baz", "smurf");
        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);

        Volunteer received = wrapper.getVolunteer();

        Volunteer expected = volunteer;
        assertEquals(expected, received);
    }    // getVolunteerDoesNotThrowNullWhenVolunteerIsNotNull()

    /* toString() */

    /**
     * Tests that {@link VolunteerDisplayWrapper#toString()} does not throw an
     * exception when volunteer is null.
     */
    @Test
    public void toStringDoesNotThrowNullWhenVolunteerIsNull() {
        Volunteer volunteer = null;
        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);

        wrapper.toString();
    }    // toStringDoesNotThrowNullWhenVolunteerIsNull()

    /**
     * Tests that {@link VolunteerDisplayWrapper#toString()} returns the correct
     * value when volunteer is null.
     */
    @Test
    public void toStringReturnsCorrectValueWhenVolunteerIsNull() {
        Volunteer volunteer = null;
        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);

        String received = wrapper.toString();

        String expected = "(open)";
        assertEquals(expected, received);
    }    // toStringDoesNotThrowNullWhenVolunteerIsNull()

    /**
     * Tests that {@link VolunteerDisplayWrapper#toString()} does not throw
     * an exception when volunteer is not null.
     */
    @Test
    public void toStringDoesNotThrowNullWhenVolunteerIsNotNull() {
        Volunteer volunteer = new Volunteer("foo", "bar", "baz", "smurf");
        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);

        wrapper.toString();
    }    // toStringDoesNotThrowNullWhenVolunteerIsNotNull()

    /**
     * Tests that {@link VolunteerDisplayWrapper#toString()} returns the
     * correct value when volunteer is not null.
     */
    @Test
    public void toStringReturnsCorrectValueWhenVolunteerIsNotNull() {
        Volunteer volunteer = new Volunteer("foo", "bar", "baz", "smurf");
        VolunteerDisplayWrapper wrapper = new VolunteerDisplayWrapper(volunteer);

        String received = wrapper.toString();

        String expected = volunteer.getName();
        assertEquals(expected, received);
    }    // toStringDoesNotThrowNullWhenVolunteerIsNotNull()

}    // VolunteerDisplayWrapperTest
