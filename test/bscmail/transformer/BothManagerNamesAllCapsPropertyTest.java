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
 * Unit tests for {@link BothManagerNamesAllCapsProperty}.
 * 
 * @author Wayne Miller
 */
public class BothManagerNamesAllCapsPropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new BothManagerNamesAllCapsProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("BothManagerNamesAllCapsProperty");
        System.out.println("===============================");
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
     * {@link BothManagerNamesAllCapsProperty#BothManagerNamesAllCapsProperty()}
     * does not throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new BothManagerNamesAllCapsProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link BothManagerNamesAllCapsProperty#getProperty(Event)}
     * returns the correct value for an event with both a manager and assistant
     * manager.
     */
    @Test
    public void testGetPropertyHasBoth() {
        System.out.println("getProperty - has manager and assistant manager");
        Event event = new Event();
        event.setManager(new Manager("Manny Ager", "manny@ager", "555-MANNY"));
        event.setAssistantManager(new Manager("Lou Tenant", "lou@tenant", "555-LOU"));
        EventProperty eventProperty = getEventProperty();
        
        String expected = "MANNY AGER and LOU TENANT";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyHasBoth()
    
    /**
     * Tests that {@link BothManagerNamesAllCapsProperty#getProperty(Event)}
     * returns the correct value for an event with a manager but no assistant
     * manager.
     */
    @Test
    public void testGetPropertyHasManagerOnly() {
        System.out.println("getProperty - has manager but not assistant manager");
        Event event = new Event();
        event.setManager(new Manager("Manny Ager", "manny@ager", "555-MANNY"));
        EventProperty eventProperty = getEventProperty();
        
        String expected = "MANNY AGER";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyHasManagerOnly()
    
    /**
     * Tests that {@link BothManagerNamesAllCapsProperty#getProperty(Event)}
     * returns the correct value for an event with an assistant manager but no
     * manager.
     */
    @Test
    public void testGetPropertyHasAssistantManagerOnly() {
        System.out.println("getProperty - has assistant manager but not manager");
        Event event = new Event();
        event.setAssistantManager(new Manager("Lou Tenant", "lou@tenant", "555-LOU"));
        EventProperty eventProperty = getEventProperty();
        
        String expected = "";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyHasAssistantManagerOnly()
    
    /**
     * Tests that {@link BothManagerNamesAllCapsProperty#getProperty(Event)}
     * returns the correct value for an event with neither a manager not an assistant manager.
     */
    @Test
    public void testGetPropertyHasNeither() {
        System.out.println("getProperty - has neither manager nor assistant manager");
        Event event = new Event();
        EventProperty eventProperty = getEventProperty();
        
        String expected = "";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyHasNeither()
    
}    // BothManagerNamesAllCapsPropertyTest
