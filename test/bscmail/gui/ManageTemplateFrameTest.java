/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
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
