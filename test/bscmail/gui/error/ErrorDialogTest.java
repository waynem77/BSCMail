/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
        String expected = main.Application.getApplicationName();
        String received = dialog.getTitle();
        assertTrue(received.contains(expected));
    }    // testTitleHasApplicationName()

}    // ErrorDialogTest
