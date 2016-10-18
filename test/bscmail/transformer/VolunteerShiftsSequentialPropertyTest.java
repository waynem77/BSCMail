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
 * Unit tests for {@link VolunteerShiftsSequentialProperty}.
 * 
 * @author Wayne Miller
 */
public class VolunteerShiftsSequentialPropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new VolunteerShiftsSequentialProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("VolunteerShiftsSequentialProperty");
        System.out.println("=================================");
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
     * {@link VolunteerShiftsSequentialProperty#VolunteerShiftsSequentialProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new VolunteerShiftsSequentialProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link VolunteerShiftsSequentialProperty#getProperty(Event)}
     * returns the correct value for an event with no shifts.
     */
    @Test
    public void testGetPropertyNoShifts() {
        System.out.println("getProperty - no shifts");
        Event event = new Event();
        EventProperty eventProperty = getEventProperty();
        
        String expected = "";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoShifts()
    
    /**
     * Tests that {@link VolunteerShiftsSequentialProperty#getProperty(Event)}
     * returns the correct value for an event with no filled shifts.
     */
    @Test
    public void testGetPropertyNoFilledShifts() {
        System.out.println("getProperty - no shifts filled");
        Event event = new Event();
        
        String description = "Setup";
        boolean isAngelShift = false;
        Shift shift = new Shift(description, isAngelShift);
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        event.addShift(shift);
        String expected = "Setup: (open)";
        expected += "\nDoor: (open)";
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoFilledShifts()
    
    /**
     * Tests that {@link VolunteerShiftsSequentialProperty#getProperty(Event)}
     * returns the correct value for an event with one filled
     * shift.
     */
    @Test
    public void testGetPropertyOneFilledShift() {
        System.out.println("getProperty - one filled shift");
        Event event = new Event();
        Shift shift = new Shift("Setup", false);
        String name = "Bar Volunteer";
        String email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        String expected = "Setup: Bar Volunteer";
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyOneFilledShift()
    
    /**
     * Tests that {@link VolunteerShiftsSequentialProperty#getProperty(Event)}
     * returns the correct value for an event with all shifts
     * filled.
     */
    @Test
    public void testGetPropertyAllShiftsFilled() {
        System.out.println("getProperty - all shifts filled");
        Event event = new Event();
        
        Shift shift = new Shift("Setup", false);
        shift.setVolunteer(new Volunteer("Bar Volunteer", "bar@volunteer", true));
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        shift.setVolunteer(new Volunteer("Baz Volunteer", "baz@volunteer", true));
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String expected = "Setup: Bar Volunteer";
        expected += "\nDoor: Baz Volunteer";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyAllShiftsFilled()
    
    /**
     * Tests that {@link VolunteerShiftsSequentialProperty#getProperty(Event)}
     * returns the correct value for an event with some shifts filled.
     */
    @Test
    public void testGetPropertySomeShiftsFilled() {
        System.out.println("getProperty - some shifts filled");
        Event event = new Event();
        
        Shift shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        String name = "Bar Volunteer";
        String email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Door", false);
        event.addShift(shift);
        
        shift = new Shift("Cleanup", false);
        name = "Baz Volunteer";
        email = "baz@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Angel", true);
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String expected = "Setup: (open)";
        expected += "\nDoor: Bar Volunteer";
        expected += "\nDoor: (open)";
        expected += "\nCleanup: Baz Volunteer";
        expected += "\nAngel: (open)";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertySomeShiftsFilled()
}    // VolunteerShiftsSequentialPropertyTest
