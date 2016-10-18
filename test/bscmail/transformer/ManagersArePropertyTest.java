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
 * Unit tests for {@link ManagersAreProperty}.
 * 
 * @author Wayne Miller
 */
public class ManagersArePropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new ManagersAreProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManagersAreProperty");
        System.out.println("===================");
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
     * {@link ManagersAreProperty#ManagersAreProperty()}
     * does not throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new ManagersAreProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link ManagersAreProperty#getProperty(Event)}
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
        
        String expected = "House Managers for this event are";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyHasBoth()
    
    /**
     * Tests that {@link ManagersAreProperty#getProperty(Event)}
     * returns the correct value for an event with a manager but no assistant
     * manager.
     */
    @Test
    public void testGetPropertyHasManagerOnly() {
        System.out.println("getProperty - has manager but not assistant manager");
        Event event = new Event();
        event.setManager(new Manager("Manny Ager", "manny@ager", "555-MANNY"));
        EventProperty eventProperty = getEventProperty();
        
        String expected = "House Manager for this event is";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyHasManagerOnly()
    
    /**
     * Tests that {@link ManagersAreProperty#getProperty(Event)}
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
     * Tests that {@link ManagersAreProperty#getProperty(Event)}
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
    
}    // ManagersArePropertyTest
