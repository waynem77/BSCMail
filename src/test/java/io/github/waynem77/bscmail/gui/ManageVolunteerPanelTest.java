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

import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.TestApplication;
import io.github.waynem77.bscmail.persistent.Volunteer;
import java.util.Arrays;
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
        return new TestApplication();
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
        return new Volunteer("foo", "bar", "baz", "smurf", true, Arrays.asList());
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

        Volunteer expected = new Volunteer("", "", "", "", false, Arrays.asList());
        assertEquals(expected, received);
    }    // createElementReturnsEmptyElementWhenPanelHasNotBeenLoaded()

}    // ManageVolunteerPanelTest
