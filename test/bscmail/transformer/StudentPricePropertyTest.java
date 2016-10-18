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
 * Unit tests for {@link StudentPriceProperty}.
 * 
 * @author Wayne Miller
 */
public class StudentPricePropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new StudentPriceProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("StudentPriceProperty");
        System.out.println("============");
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
     * {@link StudentPriceProperty#StudentPriceProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new StudentPriceProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link StudentPriceProperty#getProperty(bscmail.Event)}
     * returns the correct value.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Event event = new Event();
        String studentPrice = "Foo";
        event.setStudentPrice(studentPrice);
        EventProperty eventProperty = getEventProperty();
        
        String expected = studentPrice;
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetProperty()

}    // StudentPricePropertyTest
