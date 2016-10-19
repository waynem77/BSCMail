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
 * Abstract base class for unit tests for implementations of
 * {@link EventProperty}.
 * 
 * @author Wayne Miller
 */
@Ignore
public abstract class EventPropertyTest {

    /**
     * Returns the event property to be tested.  Implementations must ensure
     * that this method does not return null.
     * 
     * @return the event property to be tested
     */
    protected abstract EventProperty getEventProperty();

    /**
     * Tests that {@link EventProperty#getProperty(Event)} throws a 
     * {@link NullPointerException} when the event is null.
     */
    @Test(expected = NullPointerException.class)
    public void testGetPropertyNull() {
        System.out.println("(EventProperty) getProperty - null");
        EventProperty eventProperty = getEventProperty();
        Event event = null;
        eventProperty.getProperty(event);
    }    // testGetPropertyNull()

    /**
     * Tests that {@link EventProperty#getProperty(Event)} does not throw an 
     * exception when the event is not null.
     */
    @Test
    public void testGetPropertyNoException() {
        System.out.println("(EventProperty) getProperty - not null. no exception");
        EventProperty eventProperty = getEventProperty();
        Event event = new Event();
        eventProperty.getProperty(event);
    }    // testGetPropertyNull()
}    // EventPropertyTest
