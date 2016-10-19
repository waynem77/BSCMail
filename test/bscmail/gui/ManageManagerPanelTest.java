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

import bscmail.Manager;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageManagerPanel}.
 * 
 * @author Wayne Miller
 */
public class ManageManagerPanelTest extends ManageElementPanelTest<Manager> {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManageManagerPanel");
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
     * Returns the manage manager panel to be tested.
     * 
     * @return the manage manager panel to be tested
     */
    @Override
    protected ManageManagerPanel getPanel() {
        return new ManageManagerPanel();
    }    // getPanel()

    /**
     * Returns an invalid manager to use in testing.
     * 
     * @return an invalid manager to use in testing
     */
    @Override
    protected Manager getInvalidElement() {
        return null;
    }    // gerInvalidElement()

    /**
     * Returns a valid manager to use in testing.
     * 
     * @return a valid manager to use in testing
     */
    @Override
    protected Manager getElement() {
        return new Manager("foo", "bar", "baz");
    }    // getElement()
    
    /**
     * Tests that {@link ManageManagerPanel#ManageManagerPanel()} does not throw an
     * exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        ManageManagerPanel panel = new ManageManagerPanel();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link ManageManagerPanel#createElement()} does not throw an
     * exception when the panel is not loaded.
     */
    @Test
    public void testCreateElementNotLoadedNoException() {
        System.out.println("createElement - not loadeed, no exception");
        
        ManageManagerPanel panel = getPanel();
        panel.loadElement(null);
        panel.createElement();
    }    // testCreateElementNotLoadedNoException()
    
    /**
     * Tests that {@link ManageManagerPanel#createElement()} does not return null
     * when the panel is not loaded.
     */
    @Test
    public void testCreateElementNotLoadedNotNull() {
        System.out.println("createElement - not loadeed, not null");
        
        ManageManagerPanel panel = getPanel();
        panel.loadElement(null);
        Manager received = panel.createElement();
        assertNotNull(received);
    }    // testCreateElementNotLoadedNotNull()
    
    /**
     * Tests that {@link ManageManagerPanel#createElement()} returns an "empty"
     * manager when the panel is not loaded.
     */
    @Test
    public void testCreateElementNotLoaded() {
        System.out.println("createElement - not loadeed");
        
        ManageManagerPanel panel = getPanel();
        panel.loadElement(null);
        Manager expected = new Manager("", "", "");
        Manager received = panel.createElement();
        assertEquals(expected, received);
    }    // testCreateElementNotLoaded()
    
}    // ManageManagerPanelTest
