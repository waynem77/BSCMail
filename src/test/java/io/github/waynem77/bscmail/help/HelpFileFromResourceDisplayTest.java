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

package io.github.waynem77.bscmail.help;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link HelpFileFromResourceDisplay}
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class HelpFileFromResourceDisplayTest {

    /**
     * Tests that
     * {@link HelpFileFromResourceDisplay#HelpFileFromResourceDisplay(java.lang.String)}
     * throws a NullPointerException when pathname is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenPathnameIsNull() {
        String resource = null;

        HelpFileFromResourceDisplay display = new HelpFileFromResourceDisplay(resource);
    }    // constructorThrowsExceptionWhenPathnameIsNull()

    /**
     * Tests that
     * {@link HelpFileFromResourceDisplay#HelpFileFromResourceDisplay(java.lang.String)}
     * does not throw an exception when pathname is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenPathnameIsNotNull() {
        String resource = "foo";

        HelpFileFromResourceDisplay display = new HelpFileFromResourceDisplay(resource);
    }    // constructorDoesNotThrowExceptionWhenPathnameIsNotNull()

}
