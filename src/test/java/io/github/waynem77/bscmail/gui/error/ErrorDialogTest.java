/*
 * Copyright © 2014-2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.gui.error;

import java.awt.Frame;
import javax.swing.JFrame;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ErrorDialog}.
 *
 * @author Wayne Miller
 */
public class ErrorDialogTest {

    /* constructors */

    /**
     * Tests that
     * {@link ErrorDialog#ErrorDialog(java.awt.Frame, java.lang.String, java.lang.String, java.lang.Throwable)}
     * does not throw an exception when owner is null.
     */
    @Test
    public void fourArgConstructorDoesNotThrowExceptionWhenOwnerIsNull() {
        Frame owner = null;
        String title = "Foo";
        String message = "Bar";
        Throwable cause = new RuntimeException();

        ErrorDialog dialog = new ErrorDialog(owner, title, message, cause);
    }    // testConstructorFrameStringThrowableOwnerNullNoException()

    /**
     * Tests that
     * {@link ErrorDialog#ErrorDialog(java.awt.Frame, java.lang.String, java.lang.String, java.lang.Throwable)}
     * does not throw an exception when title is null.
     */
    @Test
    public void fourArgConstructorDoesNotThrowExceptionWhenTitleIsNull() {
        Frame owner = new JFrame();
        String title = null;
        String message = "Bar";
        Throwable cause = new RuntimeException();

        ErrorDialog dialog = new ErrorDialog(owner, title, message, cause);
    }    // fourArgConstructorDoesNotThrowExceptionWhenTitleIsNull()

    /**
     * Tests that
     * {@link ErrorDialog#ErrorDialog(java.awt.Frame, java.lang.String, java.lang.String, java.lang.Throwable)}
     * does not throw an exception when message is null.
     */
    @Test
    public void fourArgConstructorDoesNotThrowExceptionWhenMessageIsNull() {
        Frame owner = new JFrame();
        String title = "Foo";
        String message = null;
        Throwable cause = new RuntimeException();

        ErrorDialog dialog = new ErrorDialog(owner, title, message, cause);
    }    // fourArgConstructorDoesNotThrowExceptionWhenMessageIsNull()

    /**
     * Tests that
     * {@link ErrorDialog#ErrorDialog(java.awt.Frame, java.lang.String, java.lang.String, java.lang.Throwable)}
     * does not throw an exception when cause is null.
     */
    @Test
    public void fourArgConstructorDoesNotThrowExceptionWhenCauseIsNull() {
        Frame owner = new JFrame();
        String title = "Foo";
        String message = "Bar";
        Throwable cause = null;

        ErrorDialog dialog = new ErrorDialog(owner, title, message, cause);
    }    // fourArgConstructorDoesNotThrowExceptionWhenCauseIsNull()

    /**
     * Tests that
     * {@link ErrorDialog#ErrorDialog(java.awt.Frame, java.lang.String, java.lang.String)}
     * does not throw an exception when owner is null.
     */
    @Test
    public void threeArgConstructorDoesNotThrowExceptionWhenOwnerIsNull() {
        Frame owner = null;
        String title = "Foo";
        String message = "Bar";

        ErrorDialog dialog = new ErrorDialog(owner, title, message);
    }    // fourArgConstructorDoesNotThrowExceptionWhenMessageIsNull()

    /**
     * Tests that
     * {@link ErrorDialog#ErrorDialog(java.awt.Frame, java.lang.String, java.lang.String)}
     * does not throw an exception when title is null.
     */
    @Test
    public void threeArgConstructorDoesNotThrowExceptionWhenTitleIsNull() {
        Frame owner = new JFrame();
        String title = null;
        String message = "Bar";

        ErrorDialog dialog = new ErrorDialog(owner, title, message);
    }    // fourArgConstructorDoesNotThrowExceptionWhenTitleIsNull()

    /**
     * Tests that
     * {@link ErrorDialog#ErrorDialog(java.awt.Frame, java.lang.String, java.lang.String)}
     * does not throw an exception when message is null.
     */
    @Test
    public void threeArgConstructorDoesNotThrowExceptionWhenMessageIsNull() {
        Frame owner = new JFrame();
        String title = "Foo";
        String message = null;

        ErrorDialog dialog = new ErrorDialog(owner, title, message);
    }    // fourArgConstructorDoesNotThrowExceptionWhenMessageIsNull()

}    // ErrorDialogTest
