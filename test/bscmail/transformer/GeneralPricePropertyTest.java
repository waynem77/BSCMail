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
 * Unit tests for {@link GeneralPriceProperty}.
 * 
 * @author Wayne Miller
 */
public class GeneralPricePropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new GeneralPriceProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("GeneralPriceProperty");
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
     * {@link GeneralPriceProperty#GeneralPriceProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new GeneralPriceProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link GeneralPriceProperty#getProperty(bscmail.Event)}
     * returns the correct value.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Event event = new Event();
        String generalPrice = "Foo";
        event.setGeneralPrice(generalPrice);
        EventProperty eventProperty = getEventProperty();
        
        String expected = generalPrice;
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetProperty()

}    // GeneralPricePropertyTest
