/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.gui;

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
     * Returns the manage volunteer panel to be tested.
     * 
     * @return the manage volunteer panel to be tested
     */
    @Override
    protected ManageVolunteerPanel getPanel() {
        return new ManageVolunteerPanel();
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
        return new Volunteer("foo", "bar", true);
    }    // getElement()
    
    /**
     * Tests that {@link ManageVolunteerPanel#ManageVolunteerPanel()} does not throw an
     * exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        ManageVolunteerPanel panel = new ManageVolunteerPanel();
    }    // testConstructorNoException()
    
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
        Volunteer expected = new Volunteer("", "", false);
        Volunteer received = panel.createElement();
        assertEquals(expected, received);
    }    // testCreateElementNotLoaded()
    
}    // ManageVolunteerPanelTest
