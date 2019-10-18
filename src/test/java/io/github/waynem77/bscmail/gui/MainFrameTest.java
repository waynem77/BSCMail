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

package io.github.waynem77.bscmail.gui;

import io.github.waynem77.bscmail.help.HelpDisplay;
import io.github.waynem77.bscmail.iolayer.IOLayer;
import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.ApplicationInfo;
import io.github.waynem77.bscmail.persistent.EmailServerProperties;
import io.github.waynem77.bscmail.persistent.EmailTemplate;
import io.github.waynem77.bscmail.persistent.EventProperty;
import io.github.waynem77.bscmail.persistent.Role;
import io.github.waynem77.bscmail.persistent.Shift;
import io.github.waynem77.bscmail.iolayer.TestIOLayer;
import io.github.waynem77.bscmail.persistent.Volunteer;
import org.junit.*;

/**
 * Unit tests for {@link MainFrame}.
 *
 * @author Wayne Miller
 */
public class MainFrameTest {

    /**
     * Returns an application that can be used in tests.
     */
    private Application getTestApplication()  {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new HelpDisplay(){ @Override public void displayHelp() {} };
        return Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // getTestApplication()

    /**
     * Tests that {@link MainFrame#MainFrame(bscmail.Application)} throws a
     * {@link NullPointerException} when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicationIsNull() {
        Application application = null;

        MainFrame frame = new MainFrame(application);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that {@link MainFrame#MainFrame(bscmail.Application)} does not
     * throw an exception when application is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenApplicationIsNotNull() {
        Application application = getTestApplication();
        MainFrame frame = new MainFrame(application);
    }    // constructorDoesNotThrowExceptionWhenApplicationIsNotNull()

}    // MainFrameTest
