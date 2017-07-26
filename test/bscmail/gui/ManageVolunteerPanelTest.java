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
import bscmail.EmailTemplate;
import bscmail.EventProperty;
import bscmail.Role;
import bscmail.Shift;
import bscmail.TestIOLayer;
import bscmail.Volunteer;
import bscmail.help.HelpDisplay;
import iolayer.IOLayer;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageVolunteerPanel}.
 *
 * @author Wayne Miller
 */
public class ManageVolunteerPanelTest extends ManageElementPanelTest<Volunteer> {

    /**
     * Returns an application that can be used in tests.
     */
    private Application getTestApplication()  {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new HelpDisplay(){ @Override public void displayHelp() {} };
        return new Application(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // getTestApplication()

    /**
     * Returns the manage volunteer panel to be tested.
     *
     * @return the manage volunteer panel to be tested
     */
    @Override
    protected ManageVolunteerPanel getPanel() {
        Application application = getTestApplication();
        return new ManageVolunteerPanel(application);
    }    // getPanel()

    /**
     * Returns an invalid volunteer to use in testing.
     *
     * @return an invalid volunteer to use in testing
     */
    @Override
    protected Volunteer getInvalidElement() {
        return null;
    }    // gerInvalidElement()

    /**
     * Returns a valid volunteer to use in testing.
     *
     * @return a valid volunteer to use in testing
     */
    @Override
    protected Volunteer getElement() {
        return new Volunteer("foo", "bar", "baz", "smurf", true);
    }    // getElement()

    /*
     * Unit tests
     */

    /**
     * Tests that
     * {@link ManageVolunteerPanel#ManageVolunteerPanel(bscmail.Application)}
     * throws a {@link NullPointerException} if application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicationIsNull() {
        Application application = null;

        ManageVolunteerPanel panel = new ManageVolunteerPanel(application);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that
     * {@link ManageVolunteerPanel#ManageVolunteerPanel(bscmail.Application)}
     * does not throw an exception when application is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenApplicationIsNotNull() {
        Application application = getTestApplication();
        assert (application != null);

        ManageVolunteerPanel panel = new ManageVolunteerPanel(application);
    }    // constructorDoesNotThrowExceptionWhenApplicationIsNotNull()

    /**
     * Tests that {@link ManageVolunteerPanel#createElement()} does not throw an
     * exception when the panel is not loaded.
     */
    @Test
    public void createElementDoesNotThrowExceptionWhenPanelHasNotBeenLoaded() {
        ManageVolunteerPanel panel = getPanel();
        panel.loadElement(null);

        panel.createElement();
    }    // createElementDoesNotThrowExceptionWhenPanelHasNotBeenLoaded()

    /**
     * Tests that {@link ManageVolunteerPanel#createElement()} does not return null
     * when the panel is not loaded.
     */
    @Test
    public void createElementDoesNotReturnNullWhenPanelHasNotBeenLoaded() {
        ManageVolunteerPanel panel = getPanel();
        panel.loadElement(null);

        Volunteer received = panel.createElement();

        assertNotNull(received);
    }    // createElementDoesNotReturnNullWhenPanelHasNotBeenLoaded()

    /**
     * Tests that {@link ManageVolunteerPanel#createElement()} returns an "empty"
     * volunteer when the panel is not loaded.
     */
    @Test
    public void createElementReturnsEmptyElementWhenPanelHasNotBeenLoaded() {
        ManageVolunteerPanel panel = getPanel();
        panel.loadElement(null);

        Volunteer received = panel.createElement();

        Volunteer expected = new Volunteer("", "", "", "", true);
        assertEquals(expected, received);
    }    // createElementReturnsEmptyElementWhenPanelHasNotBeenLoaded()

}    // ManageVolunteerPanelTest
