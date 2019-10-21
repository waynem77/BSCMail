/*
 * Copyright © 2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ApplicationInitializationException}
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class ApplicationInitializationExceptionTest {

    /**
     * Tests that
     * {@link ApplicationInitializationException#ApplicationInitializationException(Throwable)}
     * does not throw an exception when cause is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenCauseIsNull() {
        Throwable cause = null;

        ApplicationInitializationException exception = new ApplicationInitializationException(cause);
    }    // constructorDoesNotThrowExceptionWhenCauseIsNull()

    /**
     * Tests that
     * {@link ApplicationInitializationException#ApplicationInitializationException(Throwable)}
     * does not throw an exception when cause is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenCauseIsNotNull() {
        Throwable cause = new Exception("foo");

        ApplicationInitializationException exception = new ApplicationInitializationException(cause);
    }    // constructorDoesNotThrowExceptionWhenCauseIsNotNull()

    /**
     * Tests that
     * {@link ApplicationInitializationException#ApplicationInitializationException(Throwable)}
     * correctly sets the cause.
     */
    @Test
    public void constructorSetsCause() {
        Throwable cause = new Exception("foo");
        ApplicationInitializationException exception = new ApplicationInitializationException(cause);

        Throwable received = exception.getCause();

        Throwable expected = cause;
        assertEquals(expected, received);
    }    // constructorSetsCause()

}    // ApplicationInitializationExceptionTest
