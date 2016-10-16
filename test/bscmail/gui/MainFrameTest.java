/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.gui;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link MainFrame}.
 * 
 * @author Wayne Miller
 */
public class MainFrameTest {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("MainFrame");
        System.out.println("=========");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass()
    
    /**
     * Tests that {@link MainFrame#MainFrame()} does not throw
     * an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        MainFrame frame = new MainFrame();
    }    // testConstructorNoException()
}    // MainFrameTest
