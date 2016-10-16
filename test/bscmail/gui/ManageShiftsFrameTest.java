/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.gui;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageShiftsFrame}.
 * 
 * @author Wayne Miller
 */
public class ManageShiftsFrameTest {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManageShiftsFrame");
        System.out.println("=================");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass()
    
    /**
     * Tests that {@link ManageShiftsFrame#ManageShiftsFrame()} does not throw
     * an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        ManageShiftsFrame frame = new ManageShiftsFrame();
    }    // testConstructorNoException()
}    // ManageShiftsFrameTest
