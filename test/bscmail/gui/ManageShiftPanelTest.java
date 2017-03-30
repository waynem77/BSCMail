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
import java.util.Collections;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageShiftPanel}.
 *
 * @author Wayne Miller
 */
public class ManageShiftPanelTest extends ManageElementPanelTest<Shift> {

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
     * Returns the manage shift panel to be tested.
     *
     * @return the manage shift panel to be tested
     */
    @Override
    protected ManageShiftPanel getPanel() {
        Application application = getTestApplication();
        return new ManageShiftPanel(application);
    }    // getPanel()

    /**
     * Returns an invalid shift to use in testing.
     *
     * @return an invalid shift to use in testing
     */
    @Override
    protected Shift getInvalidElement() {
        return null;
    }    // gerInvalidElement()

    /**
     * Returns a valid shift to use in testing.
     *
     * @return a valid shift to use in testing
     */
    @Override
    protected Shift getElement() {
        return new Shift("foo", Collections.<Role>emptyList(), false, false, false);
    }    // getElement()

    /*
     * Unit tests
     */

    /**
     * Tests that {@link ManageShiftPanel#ManageShiftPanel(bscmail.Application)}
     * throws a {@link NullPointerException} when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicationIsNull() {
        Application application = null;

        ManageShiftPanel panel = new ManageShiftPanel(application);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that {@link ManageShiftPanel#ManageShiftPanel(bscmail.Application)}
     * does not throw an exception when application is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenApplicationIsNotNull() {
        Application application = getTestApplication();

        ManageShiftPanel panel = new ManageShiftPanel(application);
    }    // constructorDoesNotThrowExceptionWhenApplicationIsNotNull()

    /**
     * Tests that {@link ManageShiftPanel#createElement()} does not throw an
     * exception when the panel has not been loaded.
     */
    @Test
    public void createElementDoesNotThrowExceptionWhenPanelHasNotBeenLoaded() {
        ManageShiftPanel panel = getPanel();
        panel.loadElement(null);

        panel.createElement();
    }    // createElementDoesNotThrowExceptionWhenPanelHasNotBeenLoaded()

    /**
     * Tests that {@link ManageShiftPanel#createElement()} does not return null
     * when the panel has not been loaded.
     */
    @Test
    public void createElementDoesNotReturnNullWhenPanelHasNotBeenLoaded() {
        ManageShiftPanel panel = getPanel();
        panel.loadElement(null);

        Shift received = panel.createElement();

        assertNotNull(received);
    }    // createElementDoesNotReturnNullWhenPanelHasNotBeenLoaded()

    /**
     * Tests that {@link ManageShiftPanel#createElement()} returns an "empty"
     * shift when the panel has not been loaded.
     */
    @Test
    public void createElementReturnsEmptyElementWhenPanelHasNotBeenLoaded() {
        ManageShiftPanel panel = getPanel();
        panel.loadElement(null);

        Shift received = panel.createElement();

        Shift expected = new Shift("", Collections.<Role>emptyList(), false, false, false);
        assertEquals(expected, received);
    }    // createElementReturnsEmptyElementWhenPanelHasNotBeenLoaded()

}    // ManageShiftPanelTest
