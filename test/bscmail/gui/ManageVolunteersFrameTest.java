/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.gui;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageVolunteersFrame}.
 * 
 * @author Wayne Miller
 */
public class ManageVolunteersFrameTest {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManageVolunteersFrame");
        System.out.println("=====================");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass()
    
    /**
     * Tests that {@link ManageVolunteersFrame#ManageVolunteersFrame()} does not
     * throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        ManageVolunteersFrame frame = new ManageVolunteersFrame();
    }    // testConstructorNoException()
}    // ManageVolunteersFrameTest
