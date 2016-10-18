/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.gui;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageShiftsFrame}.
 * 
 * @author Wayne Miller
 */
public class ManageShiftsFrameTest {
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManageShiftsFrame");
        System.out.println("=================");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass()
    
    /**
     * Tests that {@link ManageShiftsFrame#ManageShiftsFrame()} does not throw
     * an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        ManageShiftsFrame frame = new ManageShiftsFrame();
    }    // testConstructorNoException()
}    // ManageShiftsFrameTest
