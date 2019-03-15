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

package bscmail.gui;

import bscmail.Event;
import bscmail.Application;
import bscmail.ApplicationInfo;
import bscmail.EmailServerProperties;
import bscmail.EmailTemplate;
import bscmail.EventProperty;
import bscmail.Role;
import bscmail.Shift;
import bscmail.TestIOLayer;
import bscmail.Volunteer;
import bscmail.help.HelpDisplay;
import iolayer.IOLayer;
import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link DisplayEmailFrame}.
 *
 * @author Wayne Miller
 */
public class DisplayEmailFrameTest {

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
        return new Application(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // getTestApplication()

    /*
     * Unit tests
     */

    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(bscmail.Application, bscmail.Event)}
     * throws a {@link NullPointerException} when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicationIsNull() throws IOException {
        Application application = null;
        Event event = new Event();

        DisplayEmailFrame frame = new DisplayEmailFrame(application, event);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(bscmail.Application, bscmail.Event)}
     * throws a {@link NullPointerException} when event is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenEventIsNull() throws IOException {
        Application application = getTestApplication();
        Event event = null;

        DisplayEmailFrame frame = new DisplayEmailFrame(application, event);
    }    // constructorThrowsExceptionWhenEventIsNull()

    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(bscmail.Application, bscmail.Event)}
     * does not throw an exception when no parameter is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNoParamIsNull() throws IOException {
        Application application = getTestApplication();
        Event event = new Event();

        DisplayEmailFrame frame = new DisplayEmailFrame(application, event);
    }    // constructorDoesNotThrowExceptionWhenNoParamIsNull()

}    // DisplayEmailFrameTest
