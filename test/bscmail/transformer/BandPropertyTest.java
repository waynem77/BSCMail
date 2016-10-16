/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link BandProperty}.
 * 
 * @author Wayne Miller
 */
public class BandPropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new BandProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("BandProperty");
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
     * {@link BandProperty#BandProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new BandProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link BandProperty#getProperty(bscmail.Event)}
     * returns the correct value.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Event event = new Event();
        String band = "Foo";
        event.setBand(band);;
        EventProperty eventProperty = getEventProperty();
        
        String expected = band;
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetProperty()

}    // BandPropertyTest
