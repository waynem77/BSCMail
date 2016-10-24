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
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EmailAddressesProperty}.
 * 
 * @author Wayne Miller
 */
public class EmailAddressesPropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new EmailAddressesProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("EmailAddressesProperty");
        System.out.println("======================");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass()
    
    /**
     * Tests that
     * {@link EmailAddressesProperty#EmailAddressesProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new EmailAddressesProperty();
    }    // testConstructorNoException()

    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with  all shifts filled.
     */
    @Test
    public void testGetPropertyAllShiftsFilled() {
        System.out.println("getProperty - all shifts filled");
        Event event = new Event();
        String name = "Manny Ager";
        String email = "manny@ager";
        String phone = "555-MANNY";
        String expected = email;

        name = "Lou Tenant";
        email = "lou@tenant";
        phone = "555-LOU";
        expected += ", " + email;
        
        Shift shift = new Shift("Setup", false);
        name = "Bar Volunteer";
        email = "bar@volunteer";
        shift.setVolunteer(new Volunteer(name, email));
        event.addShift(shift);
        expected += ", " + email;
        
        shift = new Shift("Door", false);
        name = "Baz Volunteer";
        email = "baz@volunteer";
        shift.setVolunteer(new Volunteer(name, email));
        event.addShift(shift);
        expected += ", " + email;
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyAllShiftsFilled()
}    // EmailAddressesPropertyTest
