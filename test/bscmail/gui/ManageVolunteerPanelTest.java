/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
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
import bscmail.Volunteer;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageVolunteerPanel}.
 * 
 * @author Wayne Miller
 */
public class ManageVolunteerPanelTest extends ManageElementPanelTest<Volunteer> {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManageVolunteerPanel");
        System.out.println("================");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass(

    /**
     * Returns an application object to be used in testing.
     *
     * @return Returns an application object to be used in testing
     */
    private Application getApplication() {
        return new Application();
    }    // getApplication()

    /**
     * Returns the manage volunteer panel to be tested.
     * 
     * @return the manage volunteer panel to be tested
     */
    @Override
    protected ManageVolunteerPanel getPanel() {
        return new ManageVolunteerPanel(getApplication());
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
        return new Volunteer("foo", "bar", "baz", "smurf");
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
        Application application = getApplication();
        assert (application != null);

        ManageVolunteerPanel panel = new ManageVolunteerPanel(application);
    }    // constructorDoesNotThrowExceptionWhenApplicationIsNotNull()
    
    /**
     * Tests that {@link ManageVolunteerPanel#createElement()} does not throw an
     * exception when the panel is not loaded.
     */
    @Test
    public void testCreateElementNotLoadedNoException() {
        System.out.println("createElement - not loadeed, no exception");
        
        ManageVolunteerPanel panel = getPanel();
        panel.loadElement(null);
        panel.createElement();
    }    // testCreateElementNotLoadedNoException()
    
    /**
     * Tests that {@link ManageVolunteerPanel#createElement()} does not return null
     * when the panel is not loaded.
     */
    @Test
    public void testCreateElementNotLoadedNotNull() {
        System.out.println("createElement - not loadeed, not null");
        
        ManageVolunteerPanel panel = getPanel();
        panel.loadElement(null);
        Volunteer received = panel.createElement();
        assertNotNull(received);
    }    // testCreateElementNotLoadedNotNull()
    
    /**
     * Tests that {@link ManageVolunteerPanel#createElement()} returns an "empty"
     * volunteer when the panel is not loaded.
     */
    @Test
    public void testCreateElementNotLoaded() {
        System.out.println("createElement - not loadeed");
        
        ManageVolunteerPanel panel = getPanel();
        panel.loadElement(null);
        Volunteer expected = new Volunteer("", "", "", "");
        Volunteer received = panel.createElement();
        assertEquals(expected, received);
    }    // testCreateElementNotLoaded()
    
}    // ManageVolunteerPanelTest
