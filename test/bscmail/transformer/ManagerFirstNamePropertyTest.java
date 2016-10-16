/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManagerFirstNameProperty}.
 * 
 * @author Wayne Miller
 */
public class ManagerFirstNamePropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new ManagerFirstNameProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManagerFirstNameProperty");
        System.out.println("========================");
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
     * {@link ManagerFirstNameProperty#ManagerFirstNameProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new ManagerFirstNameProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link ManagerFirstNameProperty#getProperty(bscmail.Event)}
     * returns the correct value for an event with no manager.
     */
    @Test
    public void testGetPropertyNoManager() {
        System.out.println("getProperty - no manager");
        Event event = new Event();
        EventProperty eventProperty = getEventProperty();
        
        String expected = "";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyNoManager()
    
    /**
     * Tests that {@link ManagerFirstNameProperty#getProperty(bscmail.Event)}
     * returns the correct value for a manager with one name.
     */
    @Test
    public void testGetPropertyOneName() {
        System.out.println("getProperty - one name");
        Event event = new Event();
        String name = "Foo";
        String email = "Foo@bar";
        String phone = "1234";
        Manager manager = new Manager(name, email, phone);
        event.setManager(manager);
        EventProperty eventProperty = getEventProperty();
        
        String expected = "Foo";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyOneName()
    
    /**
     * Tests that {@link ManagerFirstNameProperty#getProperty(bscmail.Event)}
     * returns the correct value for a manager with three names.
     */
    @Test
    public void testGetPropertyThreeNames() {
        System.out.println("getProperty - three names");
        Event event = new Event();
        String name = "Foo Baz Bar";
        String email = "Foo@bar";
        String phone = "1234";
        Manager manager = new Manager(name, email, phone);
        event.setManager(manager);
        EventProperty eventProperty = getEventProperty();
        
        String expected = "Foo";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyThreeNames()
    
    /**
     * Tests that {@link ManagerFirstNameProperty#getProperty(bscmail.Event)}
     * returns the correct value for a manager with two names.
     */
    @Test
    public void testGetPropertyTwoNames() {
        System.out.println("getProperty - two names");
        Event event = new Event();
        String name = "Foo Bar";
        String email = "Foo@bar";
        String phone = "1234";
        Manager manager = new Manager(name, email, phone);
        event.setManager(manager);
        EventProperty eventProperty = getEventProperty();
        
        String expected = "Foo";
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetPropertyTwoNames()
    
}    // ManagerFirstNamePropertyTest
