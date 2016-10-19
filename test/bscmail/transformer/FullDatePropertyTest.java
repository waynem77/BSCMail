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
import java.util.Calendar;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link FullDateProperty}.
 * 
 * @author Wayne Miller
 */
public class FullDatePropertyTest  extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new FullDateProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("FullDateProperty");
        System.out.println("================");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass()
    
    /**
     * Tests that {@link FullDateProperty#FullDateProperty()} does not throw an
     * exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new FullDateProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link FullDateProperty#getProperty(Event)} returns
     * the correct value for an event with a date.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Event event = new Event();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.NOVEMBER, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        EventProperty eventProperty = getEventProperty();
        
        String expected = "Friday November 28";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoEvent()
    
    /**
     * Tests that {@link FullDateProperty#getProperty(Event)} does not throw an
     * exception for an event with no date.
     */
    @Test
    public void testGetPropertyNoDateNoException() {
        System.out.println("getProperty - no date, no exception");
        Event event = new Event();
        Date date = null;
        event.setDate(date);
        EventProperty eventProperty = getEventProperty();
        
        eventProperty.getProperty(event);
    }    // testGetPropertyNoDateNoException()
    
    /**
     * Tests that {@link FullDateProperty#getProperty(Event)} returns
     * the correct value for an event with no date.
     */
    @Test
    public void testGetPropertyNoDate() {
        System.out.println("getProperty - no date");
        Event event = new Event();
        Date date = null;
        event.setDate(date);
        EventProperty eventProperty = getEventProperty();
        
        String expected = "";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoDate()

}    // FullDatePropertyTest
