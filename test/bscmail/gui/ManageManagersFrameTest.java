/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.gui;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageManagersFrame}.
 * 
 * @author Wayne Miller
 */
public class ManageManagersFrameTest {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManageManagersFrame");
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
     * Tests that {@link ManageManagersFrame#ManageManagersFrame()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        ManageManagersFrame frame = new ManageManagersFrame();
    }    // testConstructorNoException()
}    // ManageManagersFrameTest
