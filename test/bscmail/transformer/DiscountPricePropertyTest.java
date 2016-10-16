/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link DiscountPriceProperty}.
 * 
 * @author Wayne Miller
 */
public class DiscountPricePropertyTest extends EventPropertyTest {

    @Override
    protected EventProperty getEventProperty() {
        return new DiscountPriceProperty();
    }    // getEventProperty()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("DiscountPriceProperty");
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
     * {@link DiscountPriceProperty#DiscountPriceProperty()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        EventProperty eventProperty = new DiscountPriceProperty();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link DiscountPriceProperty#getProperty(bscmail.Event)}
     * returns the correct value.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Event event = new Event();
        String discountPrice = "Foo";
        event.setDiscountPrice(discountPrice);
        EventProperty eventProperty = getEventProperty();
        
        String expected = discountPrice;
        String received = eventProperty.getProperty(event);
        assertEquals(expected, received);
    }    // testGetProperty()

}    // DiscountPricePropertyTest
