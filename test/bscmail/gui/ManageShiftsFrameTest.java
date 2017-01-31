/*
 * Copyright © 2014-2017 its authors.  See the file "AUTHORS" for details.
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

import bscmail.Application;
import bscmail.ApplicationInfo;
import org.junit.*;

/**
 * Unit tests for {@link ManageShiftsFrame}.
 *
 * @author Wayne Miller
 */
public class ManageShiftsFrameTest {

    /**
     * Tests that
     * {@link ManageShiftsFrame#ManageShiftsFrame(bscmail.Application)} throws a
     * {@link NullPointerException} when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicationIsNull() {
        Application application = null;

        ManageShiftsFrame frame = new ManageShiftsFrame(application);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that
     * {@link ManageShiftsFrame#ManageShiftsFrame(bscmail.Application)} does not
     * throw an exception when application is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenApplicationIsNotNull() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz");
        Application application = new Application(applicationInfo);

        ManageShiftsFrame frame = new ManageShiftsFrame(application);
    }    // constructorDoesNotThrowExceptionWhenApplicationIsNotNull()

}    // ManageShiftsFrameTest
