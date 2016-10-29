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

package bscmail.gui;

import bscmail.Event;
import bscmail.transformer.Transformer;
import java.io.*;
import bscmail.Application;
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
