/*
 * Copyright © 2016-2017 its authors.  See the file "AUTHORS" for details.
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
import bscmail.Shift;
import bscmail.TestIOLayer;
import iolayer.IOLayer;
import org.junit.*;

/**
 * Unit tests for {@link ManageEventPropertiesFrame}.
 *
 * @author Wayne Miller
 */
public class ManageEventPropertiesFrameTest {

    /**
     * Returns an application that can be used in tests.
     */
    private Application getTestApplication()  {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        return new Application(applicationInfo, shiftsIOLayer);
    }    // getTestApplication()

    /**
     * Tests that
     * {@link ManageEventPropertiesFrame#ManageEventPropertiesFrame(bscmail.Application)}
     * throws a {@link NullPointerException} when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicationIsNull() {
        Application application = null;

        ManageEventPropertiesFrame frame = new ManageEventPropertiesFrame(application);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that
     * {@link ManageEventPropertiesFrame#ManageEventPropertiesFrame(bscmail.Application)}
     * does not throw an exception when application is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenApplicationIsNotNull() {
        Application application = getTestApplication();
        ManageEventPropertiesFrame frame = new ManageEventPropertiesFrame(application);
    }    // constructorDoesNotThrowExceptionWhenApplicationIsNotNull()

}    // ManageEventPropertiesFrameTest
