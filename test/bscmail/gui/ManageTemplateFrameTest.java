/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.gui;

import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageTemplateFrame}.
 * 
 * @author Wayne Miller
 */
public class ManageTemplateFrameTest {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManageTemplateFrame");
        System.out.println("===================");
    }    // setUpClass()

    /*
     * Unit tests
     */
    
    /**
     * Tests that {@link ManageTemplateFrame#ManageTemplateFrame(Reader)} throws
     * a {@link NullPointerException} when the reader is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorReaderNull() throws IOException {
        System.out.println("constructor - reader is null");
        
        Reader reader = null;
        ManageTemplateFrame frame = new ManageTemplateFrame(reader);
    }    // testConstructorReaderNull()
    
    /**
     * Tests that {@link ManageTemplateFrame#ManageTemplateFrame(Reader)} does
     * not throw an exception when the reader is not null.
     */
    @Test
    public void testConstructorNoException() throws IOException {
        System.out.println("constructor - no exception");
        
        String text = "Unit test the class.\nSample text, perhaps, can be\nin a haiku form.";
        Reader reader = new StringReader(text);
        ManageTemplateFrame frame = new ManageTemplateFrame(reader);
    }    // testConstructorReaderNull()
    
}    // ManageTemplateFrameTest
