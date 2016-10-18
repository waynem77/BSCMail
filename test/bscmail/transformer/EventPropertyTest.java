/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
