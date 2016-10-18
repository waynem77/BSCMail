/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.gui;

import bscmail.Event;
import bscmail.transformer.Transformer;
import java.io.*;
import main.Application;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link DisplayEmailFrame}.
 * 
 * @author Wayne Miller
 */
public class DisplayEmailFrameTest {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("DisplayEmailFrame");
        System.out.println("=================");
    }    // setUpClass()

    /*
     * Unit tests
     */
    
    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(Reader, Transformer)} throws a
     * {@link NullPointerException} when the reader is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorReaderNull() throws IOException {
        System.out.println("constructor - reader is null");
        
        Reader reader = null;
        Transformer transformer = Application.getTransformer();
        Event event = new Event();
        DisplayEmailFrame frame = new DisplayEmailFrame(reader, transformer, event);
    }    // testConstructorReaderNull()
    
    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(Reader, Transformer)} throws a
     * {@link NullPointerException} when the transformer is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorTransformerNull() throws IOException {
        System.out.println("constructor - transformer is null");
        
        String text = "Unit test the class.\nSample text, perhaps, can be\nin a haiku form.";
        Reader reader = new StringReader(text);
        Transformer transformer = null;
        Event event = new Event();
        DisplayEmailFrame frame = new DisplayEmailFrame(reader, transformer, event);
    }    // testConstructorTransformerNull()
    
    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(Reader, Transformer)} throws a
     * {@link NullPointerException} when the event is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorEventNull() throws IOException {
        System.out.println("constructor - event is null");
        
        String text = "Unit test the class.\nSample text, perhaps, can be\nin a haiku form.";
        Reader reader = new StringReader(text);
        Transformer transformer = Application.getTransformer();
        Event event = null;
        DisplayEmailFrame frame = new DisplayEmailFrame(reader, transformer, event);
    }    // testConstructorEventNull()
    
    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(Reader, Transformer)} does not
     * throw an exception when no parameter is null.
     */
    @Test
    public void testConstructorNoException() throws IOException {
        System.out.println("constructor - no exception");
        
        String text = "Unit test the class.\nSample text, perhaps, can be\nin a haiku form.";
        Reader reader = new StringReader(text);
        Transformer transformer = Application.getTransformer();
        Event event = new Event();
        DisplayEmailFrame frame = new DisplayEmailFrame(reader, transformer, event);
    }    // testConstructorNoException()

}    // DisplayEmailFrameTest
