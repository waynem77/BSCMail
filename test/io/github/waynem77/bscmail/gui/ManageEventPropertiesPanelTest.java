/*
 * Copyright © 2016-2019 its authors.  See the file "AUTHORS" for details.
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

import io.github.waynem77.bscmail.persistent.EventProperty;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageEventPropertiesPanel}.
 *
 * @author Wayne Miller
 */
public class ManageEventPropertiesPanelTest extends ManageElementPanelTest<EventProperty> {

    /*
     * Protected class methods.
     */

    /**
     * Returns the manage event properties panel to be tested.
     *
     * @return the manage event properties panel to be tested
     */
    @Override
    protected ManageEventPropertiesPanel getPanel() {
        return new ManageEventPropertiesPanel();
    }    // getPanel()

    /**
     * Returns an invalid event property to use in testing.
     *
     * @return an invalid event property to use in testing
     */
    @Override
    protected EventProperty getInvalidElement() {
        return null;
    }    // gerInvalidElement()

    /**
     * Returns a valid event property to use in testing.
     *
     * @return a valid event property to use in testing
     */
    @Override
    protected EventProperty getElement() {
        return new EventProperty("Foo", "Bar");
    }    // getElement()

    /*
     * Unit tests
     */

    /**
     * Tests that {@link ManageEventPropertiesPanel#ManageEventPropertiesPanel()}
     * does not throw an exception.
     */
    @Test
    public void constructorDoesNotThrowException() {
        ManageEventPropertiesPanel panel = new ManageEventPropertiesPanel();
    }    // constructorDoesNotThrowException()

    /**
     * Tests that {@link ManageEventPropertiesPanel#createElement()} does not
     * throw an exception when the panel is not loaded.
     */
    @Test
    public void createElementDoesNotThrowExceptionWhenPanelIsNotLoaded() {
        EventProperty eventProperty = null;
        ManageEventPropertiesPanel panel = getPanel();
        panel.loadElement(eventProperty);

        panel.createElement();
    }    // createElementDoesNotThrowExceptionWhenPanelIsNotLoaded()

    /**
     * Tests that {@link ManageEventPropertiesPanel#createElement()} does not
     * return null when the panel is not loaded.
     */
    @Test
    public void createElementDoesNotReturnNullWhenPanelIsNotLoaded() {
        EventProperty eventProperty = null;
        ManageEventPropertiesPanel panel = getPanel();
        panel.loadElement(eventProperty);

        EventProperty received = panel.createElement();

        assertNotNull(received);
    }    // createElementDoesNotReturnNullWhenPanelIsNotLoaded()

    /**
     * Tests that {@link ManageEventPropertiesPanel#createElement()} returns an
     * "empty" event property when the panel is not loaded.
     */
    @Test
    public void createElementReturnsCorrectValueWhenPanelIsNotLoaded() {
        EventProperty eventProperty = null;
        ManageEventPropertiesPanel panel = getPanel();
        panel.loadElement(eventProperty);

        EventProperty received = panel.createElement();

        EventProperty expected = new EventProperty("", "");
        assertEquals(expected, received);
    }    // createElementReturnsCorrectValueWhenPanelIsNotLoaded()

}    // ManageEventPropertiesPanelTest
