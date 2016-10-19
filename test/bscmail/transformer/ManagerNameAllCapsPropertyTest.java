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
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManagerNameAllCapsProperty}.
 * 
 * @author Wayne Miller
 */
public class ManagerNameAllCapsPropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new ManagerNameAllCapsProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManagerNameAllCapsProperty");
        System.out.println("==========================");
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
     * {@link ManagerNameAllCapsProperty#ManagerNameAllCapsProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new ManagerNameAllCapsProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link ManagerNameAllCapsProperty#getProperty(bscmail.Event)}
     * returns the correct value for an event with no manager.
     */
    @Test
    public void testGetPropertyNoManager() {
        System.out.println("getProperty - no manager");
        Event event = new Event();
        EventProperty eventProperty = getEventProperty();
        
        String expected = "";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoManager()
    
    /**
     * Tests that {@link ManagerNameAllCapsProperty#getProperty(bscmail.Event)}
     * returns the correct value.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Event event = new Event();
        String name = "Foo Bar";
        String email = "Foo@bar";
        String phone = "1234";
        Manager manager = new Manager(name, email, phone);
        event.setManager(manager);
        EventProperty eventProperty = getEventProperty();
        
        String expected = "FOO BAR";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetProperty()
    
}    // ManagerNameAllCapsPropertyTest
