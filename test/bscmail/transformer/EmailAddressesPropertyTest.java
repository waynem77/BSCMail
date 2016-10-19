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
 * Unit tests for {@link EmailAddressesProperty}.
 * 
 * @author Wayne Miller
 */
public class EmailAddressesPropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new EmailAddressesProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("EmailAddressesProperty");
        System.out.println("======================");
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
     * {@link EmailAddressesProperty#EmailAddressesProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new EmailAddressesProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)}
     * returns the correct value for an event with no manager or volunteers.
     */
    @Test
    public void testGetPropertyNoManagerNoShifts() {
        System.out.println("getProperty - no manager, no shifts");
        Event event = new Event();
        EventProperty eventProperty = getEventProperty();
        
        String expected = "";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoManagerNoShifts()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)}
     * returns the correct value for an event with a manager but no shifts.
     */
    @Test
    public void testGetPropertyNoShifts() {
        System.out.println("getProperty - no shifts");
        Event event = new Event();
        String name = "Foo Manager";
        String email = "foo@manager";
        String phone = "1234";
        Manager manager = new Manager(name, email, phone);
        event.setManager(manager);
        String expected = email;
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoShifts()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)}
     * returns the correct value for an event with no manager and no shifts
     * filled.
     */
    @Test
    public void testGetPropertyNoManagerNoFilledShifts() {
        System.out.println("getProperty - no manager, no shifts filled");
        Event event = new Event();
        
        Shift shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        event.addShift(shift);

        String expected = "";
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoManagerNoFilledShifts()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with no manager, no assistant manager, and one
     * filled shift.
     */
    @Test
    public void testGetPropertyNoManagerNoAssistantOneFilledShift() {
        System.out.println("getProperty - no manager, no assistant manager, one filled shift");
        Event event = new Event();
        Shift shift = new Shift("Setup", false);
        String name = "Bar Volunteer";
        String email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        String expected = email;
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoManagerNoAssistantOneFilledShift()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)}
     * returns the correct value for an event with no manager and no assistant
     * manager.
     */
    @Test
    public void testGetPropertyNoManagerNoAssistantTwoFilledShifts() {
        System.out.println("getProperty - no manager, no assistant manager, two filled shifts");
        Event event = new Event();
        Shift shift = new Shift("Setup", false);
        String name = "Bar Volunteer";
        String email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        String expected = email;
        
        shift = new Shift("Door", false);
        name = "Baz Volunteer";
        email = "baz@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoManagerNoAssistantTwoFilledShifts()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with a manager, no assistant manager, and all
     * shifts filled.
     */
    @Test
    public void testGetPropertyNoAssitantAllShiftsFilled() {
        System.out.println("getProperty - no assistant manager, all shifts filled");
        Event event = new Event();
        String name = "Foo Manager";
        String email = "foo@manager";
        String phone = "1234";
        event.setManager(new Manager(name, email, phone));
        String expected = email;
        
        Shift shift = new Shift("Setup", false);
        name = "Bar Volunteer";
        email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;
        
        shift = new Shift("Door", false);
        name = "Baz Volunteer";
        email = "baz@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoAssitantAllShiftsFilled()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with a manager, no assistant manager, and no
     * shifts filled.
     */
    @Test
    public void testGetPropertyNoAssistantNoShiftsFilled() {
        System.out.println("getProperty - no assistant manager, no shifts filled");
        Event event = new Event();
        String name = "Foo Manager";
        String email = "foo@manager";
        String phone = "1234";
        event.setManager(new Manager(name, email, phone));
        String expected = email;
        
        Shift shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoAssistantNoShiftsFilled()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with a manager, no assistant manager, and some
     * shifts filled.
     */
    @Test
    public void testGetPropertyNoAssistantSomeShiftsFilled() {
        System.out.println("getProperty - some shifts filled");
        Event event = new Event();
        String name = "Foo Manager";
        String email = "foo@manager";
        String phone = "1234";
        event.setManager(new Manager(name, email, phone));
        String expected = email;
        
        Shift shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        name = "Bar Volunteer";
        email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;

        shift = new Shift("Door", false);
        event.addShift(shift);
        
        shift = new Shift("Cleanup", false);
        name = "Baz Volunteer";
        email = "baz@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;

        shift = new Shift("Angel", true);
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoAssistantSomeShiftsFilled()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with a manager, an assistant manager, and all
     * shifts filled.
     */
    @Test
    public void testGetPropertyAllShiftsFilled() {
        System.out.println("getProperty - all shifts filled");
        Event event = new Event();
        String name = "Manny Ager";
        String email = "manny@ager";
        String phone = "555-MANNY";
        event.setManager(new Manager(name, email, phone));
        String expected = email;

        name = "Lou Tenant";
        email = "lou@tenant";
        phone = "555-LOU";
        event.setAssistantManager(new Manager(name, email, phone));
        expected += ", " + email;
        
        Shift shift = new Shift("Setup", false);
        name = "Bar Volunteer";
        email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;
        
        shift = new Shift("Door", false);
        name = "Baz Volunteer";
        email = "baz@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyAllShiftsFilled()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with a manager, an assistant manager, and no
     * shifts filled.
     */
    @Test
    public void testGetPropertyNoShiftsFilled() {
        System.out.println("getProperty - no shifts filled");
        Event event = new Event();
        String name = "Manny Ager";
        String email = "manny@ager";
        String phone = "555-MANNY";
        event.setManager(new Manager(name, email, phone));
        String expected = email;

        name = "Lou Tenant";
        email = "lou@tenant";
        phone = "555-LOU";
        event.setAssistantManager(new Manager(name, email, phone));
        expected += ", " + email;
        
        Shift shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoShiftsFilled()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with a manager, an assistant manager, and some
     * shifts filled.
     */
    @Test
    public void testGetPropertySomeShiftsFilled() {
        System.out.println("getProperty - some shifts filled");
        Event event = new Event();
        String name = "Manny Ager";
        String email = "manny@ager";
        String phone = "555-MANNY";
        event.setManager(new Manager(name, email, phone));
        String expected = email;

        name = "Lou Tenant";
        email = "lou@tenant";
        phone = "555-LOU";
        event.setAssistantManager(new Manager(name, email, phone));
        expected += ", " + email;
        
        Shift shift = new Shift("Setup", false);
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        name = "Bar Volunteer";
        email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;

        shift = new Shift("Door", false);
        event.addShift(shift);
        
        shift = new Shift("Cleanup", false);
        name = "Baz Volunteer";
        email = "baz@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;

        shift = new Shift("Angel", true);
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertySomeShiftsFilled()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with a manager and assistant manager with
     * identical email addresses.
     */
    @Test
    public void testGetPropertyManagerAndAssistantDuplicate() {
        System.out.println("getProperty - manager and assistant manager have identical emails");
        Event event = new Event();
        String name = "Manny Ager";
        String email = "manny@ager";
        String phone = "555-MANNY";
        event.setManager(new Manager(name, email, phone));
        String expected = email;

        name = "Lou Tenant";
        phone = "555-LOU";
        event.setAssistantManager(new Manager(name, email, phone));
        
        Shift shift = new Shift("Setup", false);
        name = "Bar Volunteer";
        email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;
        
        shift = new Shift("Door", false);
        name = "Baz Volunteer";
        email = "baz@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyManagerAndAssistantDuplicate()
    
    /**
     * Tests that {@link EmailAddressesProperty#getProperty(Event)} returns the
     * correct value for an event with some duplicate email addresses.
     */
    @Test
    public void testGetPropertySomeDuplicates() {
        System.out.println("getProperty - some duplicate emails");
        Event event = new Event();
        String name = "Manny Ager";
        String email = "manny@ager";
        String phone = "555-MANNY";
        event.setManager(new Manager(name, email, phone));
        String expected = email;

        name = "Lou Tenant";
        email = "lou@tenant";
        phone = "555-LOU";
        event.setAssistantManager(new Manager(name, email, phone));
        expected += ", " + email;
        
        Shift shift = new Shift("Setup", false);
        name = "Bar Volunteer";
        email = "bar@volunteer";
        boolean canAngel = true;
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        expected += ", " + email;
        
        shift = new Shift("Door", false);
        name = "Baz Volunteer";
        email = "manny@ager";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        name = "Quux Volunteer";
        email = "bar@volunteer";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        
        shift = new Shift("Door", false);
        name = "Quuux Volunteer";
        email = "lou@tenant";
        shift.setVolunteer(new Volunteer(name, email, canAngel));
        event.addShift(shift);
        
        EventProperty eventProperty = getEventProperty();
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertySomeDuplicates()
    
}    // EmailAddressesPropertyTest
