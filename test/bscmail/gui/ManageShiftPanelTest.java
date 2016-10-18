/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
