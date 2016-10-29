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

package bscmail.gui.error;

import java.awt.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ErrorDialog}.
 *
 * @author Wayne Miller
 */
public class ErrorDialogTest {

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ErrorDialog");
        System.out.println("===========");
    }    // setUpClass()

    /*
     * Unit tests
     */

    /* constructors */
    /**
     * Tests that {@link ErrorDialog#ErrorDialog(Frame, String, Throwable)} does
     * not throw an exception when owner is null.
     */
    @Test
    public void testConstructorFrameStringThrowableOwnerNullNoException() {
        System.out.println("constructor(Frame, String, Throwable) - owner is null, no exception");

        Frame owner = null;
        String message = "Foo";
        Throwable cause = new RuntimeException();
        ErrorDialog dialog = new ErrorDialog(owner, message, cause);
    }    // testConstructorFrameStringThrowableOwnerNullNoException()

    /**
     * Tests that {@link ErrorDialog#ErrorDialog(Frame, String, Throwable)} does
     * not throw an exception when message is null.
     */
    @Test
    public void testConstructorFrameStringThrowableMessageNullNoException() {
        System.out.println("constructor(Frame, String, Throwable) - message is null, no exception");

        Frame owner = new Frame();
        String message = null;
        Throwable cause = new RuntimeException();
        ErrorDialog dialog = new ErrorDialog(owner, message, cause);
    }    // testConstructorFrameStringThrowableMessageNullNoException()

    /**
     * Tests that {@link ErrorDialog#ErrorDialog(Frame, String, Throwable)} does
     * not throw an exception when cause is null.
     */
    @Test
    public void testConstructorFrameStringThrowableCauseNullNoException() {
        System.out.println("constructor(Frame, String, Throwable) - cause is null, no exception");

        Frame owner = new Frame();
        String message = "Foo";
        Throwable cause = null;
        ErrorDialog dialog = new ErrorDialog(owner, message, cause);
    }    // testConstructorFrameStringThrowableCauseNullNoException()

    /**
     * Tests that {@link ErrorDialog#ErrorDialog(Frame, String, Throwable)} does
     * not throw an exception when no parameter is null.
     */
    @Test
    public void testConstructorFrameStringThrowableNoException() {
        System.out.println("constructor(Frame, String, Throwable) - no exception");

        Frame owner = new Frame();
        String message = "Foo";
        Throwable cause = new RuntimeException();
        ErrorDialog dialog = new ErrorDialog(owner, message, cause);
    }    // testConstructorFrameStringThrowableNoException()

    /**
     * Tests that {@link ErrorDialog#ErrorDialog(Frame, String)} does not throw
     * an exception when owner is null.
     */
    @Test
    public void testConstructorFrameStringOwnerNullNoException() {
        System.out.println("constructor(Frame, String) - owner is null, no exception");

        Frame owner = null;
        String message = "Foo";
        ErrorDialog dialog = new ErrorDialog(owner, message);
    }    // testConstructorFrameStringOwnerNullNoException()

    /**
     * Tests that {@link ErrorDialog#ErrorDialog(Frame, String)} does not throw
     * an exception when message is null.
     */
    @Test
    public void testConstructorFrameStringMessageNullNoException() {
        System.out.println("constructor(Frame, String) - message is null, no exception");

        Frame owner = new Frame();
        String message = null;
        ErrorDialog dialog = new ErrorDialog(owner, message);
    }    // testConstructorFrameStringMessageNullNoException()

    /**
     * Tests that {@link ErrorDialog#ErrorDialog(Frame, String)} does not throw
     * an exception when no parameter is null.
     */
    @Test
    public void testConstructorFrameStringNoException() {
        System.out.println("constructor(Frame, String) - no exception");

        Frame owner = new Frame();
        String message = "Foo";
        ErrorDialog dialog = new ErrorDialog(owner, message);
    }    // testConstructorFrameStringNoException()

    /* modality */
    /**
     * Tests that {@link ErrorDialog} is modal by default when created with an
     * owner.
     */
    @Test
    public void testIsModalHasOwner() {
        System.out.println("getModalityType - has owner");

        Frame owner = new Frame();
        String message = "Foo";
        ErrorDialog dialog = new ErrorDialog(owner, message);
        Dialog.ModalityType expected = Dialog.DEFAULT_MODALITY_TYPE;
        Dialog.ModalityType received = dialog.getModalityType();
        assertEquals(expected, received);
    }    // testIsModalHasOwner()

    /**
     * Tests that {@link ErrorDialog} is modal by default when created without
     * an owner.
     */
    @Test
    public void testIsModalNoOwner() {
        System.out.println("getModalityType - has no owner");

        Frame owner = null;
        String message = "Foo";
        ErrorDialog dialog = new ErrorDialog(owner, message);
        Dialog.ModalityType expected = Dialog.DEFAULT_MODALITY_TYPE;
        Dialog.ModalityType received = dialog.getModalityType();
        assertEquals(expected, received);
    }    // testIsModalNoOwner()

    /* title */
    /**
     * Tests that the title of {@link ErrorDialog} contains the application name
     * by default.
     */
    @Test
    public void testTitleHasApplicationName() {
        System.out.println("getTitle - contains application name");

        Frame owner = null;
        String message = "Foo";
        ErrorDialog dialog = new ErrorDialog(owner, message);
        String expected = bscmail.Application.getApplicationName();
        String received = dialog.getTitle();
        assertTrue(received.contains(expected));
    }    // testTitleHasApplicationName()

}    // ErrorDialogTest
