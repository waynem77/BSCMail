/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package bscmail.gui;

import bscmail.Shift;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageShiftPanel}.
 * 
 * @author Wayne Miller
 */
public class ManageShiftPanelTest extends ManageElementPanelTest<Shift> {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManageShiftPanel");
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
     * Returns the manage shift panel to be tested.
     * 
     * @return the manage shift panel to be tested
     */
    @Override
    protected ManageShiftPanel getPanel() {
        return new ManageShiftPanel();
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
        return new Shift("foo", true);
    }    // getElement()
    
    /**
     * Tests that {@link ManageShiftPanel#ManageShiftPanel()} does not throw an
     * exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        ManageShiftPanel panel = new ManageShiftPanel();
    }    // testConstructorNoException()
    
    /**
     * Tests that {@link ManageShiftPanel#createElement()} does not throw an
     * exception when the panel is not loaded.
     */
    @Test
    public void testCreateElementNotLoadedNoException() {
        System.out.println("createElement - not loadeed, no exception");
        
        ManageShiftPanel panel = getPanel();
        panel.loadElement(null);
        panel.createElement();
    }    // testCreateElementNotLoadedNoException()
    
    /**
     * Tests that {@link ManageShiftPanel#createElement()} does not return null
     * when the panel is not loaded.
     */
    @Test
    public void testCreateElementNotLoadedNotNull() {
        System.out.println("createElement - not loadeed, not null");
        
        ManageShiftPanel panel = getPanel();
        panel.loadElement(null);
        Shift received = panel.createElement();
        assertNotNull(received);
    }    // testCreateElementNotLoadedNotNull()
    
    /**
     * Tests that {@link ManageShiftPanel#createElement()} returns an "empty"
     * shift when the panel is not loaded.
     */
    @Test
    public void testCreateElementNotLoaded() {
        System.out.println("createElement - not loadeed");
        
        ManageShiftPanel panel = getPanel();
        panel.loadElement(null);
        Shift expected = new Shift("", false);
        Shift received = panel.createElement();
        assertEquals(expected, received);
    }    // testCreateElementNotLoaded()
    
}    // ManageShiftPanelTest
