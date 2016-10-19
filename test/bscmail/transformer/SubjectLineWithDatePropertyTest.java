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
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link SubjectLineWithDateProperty}.
 * 
 * @author Wayne Miller
 */
public class SubjectLineWithDatePropertyTest  extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new SubjectLineWithDateProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("SubjectLineWithDateProperty");
        System.out.println("===========================");
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
     * {@link SubjectLineWithDateProperty#SubjectLineWithDateProperty()} does
     * not throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new SubjectLineWithDateProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link ManagerFirstNameProperty#getProperty(Event)} returns
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
        
        String expected = "BSC Volunteer Schedule for Friday November 28";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoEvent()

}    // SubjectLineWithDatePropertyTest
