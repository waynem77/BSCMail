/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link VolunteerShiftsCompactProperty}.
 * 
 * @author Wayne Miller
 */
public class VolunteerShiftsCompactPropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new VolunteerShiftsCompactProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("VolunteerShiftsCompactProperty");
        System.out.println("==============================");
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
     * {@link VolunteerShiftsCompactProperty#VolunteerShiftsCompactProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new VolunteerShiftsCompactProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link VolunteerShiftsCompactProperty#getProperty(Event)}
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
     * Tests that {@link VolunteerShiftsCompactProperty#getProperty(Event)}
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
     * Tests that {@link VolunteerShiftsCompactProperty#getProperty(Event)}
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
     * Tests that {@link VolunteerShiftsCompactProperty#getProperty(Event)}
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
     * Tests that {@link VolunteerShiftsCompactProperty#getProperty(Event)}
     * returns the correct value for an event with some shifts filled.
     */
    @Test
    public void testGetPropertySomeShiftsFilled() {
        System.out.println("getProperty - some shifts filled");
        Event event = new Event();
        
        Shift shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Door 1", false);
        String name = "Bar Volunteer";
        String email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Door 2", false);
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
        expected += "\nDoor 1: Bar Volunteer";
        expected += "\nDoor 2: (open)";
        expected += "\nCleanup: Baz Volunteer";
        expected += "\nAngel: (open)";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertySomeShiftsFilled()
    
    /**
     * Tests that {@link VolunteerShiftsCompactProperty#getProperty(Event)}
     * returns the correct value for an event with some identical consecutive
     * shifts, all filled.
     */
    @Test
    public void testGetPropertyConsecutiveFilled() {
        System.out.println("getProperty - identical consecutive shifts, filled");
        Event event = new Event();
        
        Shift shift = new Shift("Setup", false);
        String name = "Foo Setup";
        String email = "foo@string";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        
        shift = new Shift("Setup", false);
        name = "Bar Setup";
        email = "bar@string";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        name = "Foo Volunteer";
        email = "foobar@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Door", false);
        name = "Bar Volunteer";
        email = "bar@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Angel", true);
        name = "Foo Angel";
        email = "foo@angel";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Angel", true);
        name = "Bar Angel";
        email = "bar@angel";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Angel", true);
        name = "Baz Angel";
        email = "baz@angel";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Angel", true);
        name = "Qux Angel";
        email = "qux@angel";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Angel", true);
        name = "Quux Angel";
        email = "quux@angel";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String expected = "Setup: Foo Setup, Bar Setup";
        expected += "\nDoor: Foo Volunteer, Bar Volunteer";
        expected += "\nAngel: Foo Angel, Bar Angel, Baz Angel, Qux Angel, Quux Angel";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyConsecutiveFilled()
    
    /**
     * Tests that {@link VolunteerShiftsCompactProperty#getProperty(Event)}
     * returns the correct value for an event with some identical consecutive
     * shifts, none filled.
     */
    @Test
    public void testGetPropertyConsecutiveNotFilled() {
        System.out.println("getProperty - identical consecutive shifts, not filled");
        Event event = new Event();
        
        Shift shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        event.addShift(shift);

        shift = new Shift("Door", false);
        event.addShift(shift);

        shift = new Shift("Angel", true);
        event.addShift(shift);

        shift = new Shift("Angel", true);
        event.addShift(shift);

        shift = new Shift("Angel", true);
        event.addShift(shift);

        shift = new Shift("Angel", true);
        event.addShift(shift);

        shift = new Shift("Angel", true);
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String expected = "Setup: (open), (open)";
        expected += "\nDoor: (open), (open)";
        expected += "\nAngel: (open), (open), (open), (open), (open)";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyConsecutiveNotFilled()
    
    /**
     * Tests that {@link VolunteerShiftsCompactProperty#getProperty(Event)}
     * returns the correct value for an event with some identical consecutive
     * shifts, partially filled.
     */
    @Test
    public void testGetPropertyConsecutivePartiallyFilled() {
        System.out.println("getProperty - identical consecutive shifts, partially filled");
        Event event = new Event();
        
        Shift shift = new Shift("Setup", false);
        String name = "Foo Setup";
        String email = "foo@string";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        
        shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        event.addShift(shift);

        shift = new Shift("Door", false);
        name = "Bar Volunteer";
        email = "bar@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Angel", true);
        event.addShift(shift);

        shift = new Shift("Angel", true);
        name = "Bar Angel";
        email = "bar@angel";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Angel", true);
        name = "Baz Angel";
        email = "baz@angel";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);

        shift = new Shift("Angel", true);
        event.addShift(shift);

        shift = new Shift("Angel", true);
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String expected = "Setup: Foo Setup, (open)";
        expected += "\nDoor: (open), Bar Volunteer";
        expected += "\nAngel: (open), Bar Angel, Baz Angel, (open), (open)";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyConsecutivePartiallyFilled()
}    // VolunteerShiftsCompactPropertyTest
