/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
