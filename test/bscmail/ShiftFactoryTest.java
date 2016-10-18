/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Shift.Factory}.
 *
 * @author Wayne Miller
 */
public class ShiftFactoryTest {

    /**
     * Variable used to hold the shift description used in testing.
     */
    private String description;

    /**
     * Variable used to hold the shift "is angel shift" status used in testing.
     */
    private boolean isAngelShift;

    /**
     * Variable used to hold the volunteer used in testing.
     */
    private Volunteer volunteer;
    
    /**
     * Variable used to hold the read-writable properties used in testing.
     */
    private Map<String, Object> properties;

    /**
     * Variable used to hold the read-writable factory being tested.
     */
    private Shift.Factory factory;

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Shift.ShiftFactory");
        System.out.println("==================");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass(

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void beforeTest() {
        description = "Foo";
        isAngelShift = false;
        volunteer = new Volunteer("Foo Bar", "foo@bar", true);
        properties = null;
        factory = null;
    }    // beforeTest()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void afterTest() {
        description = null;
        volunteer = null;
        properties = null;
        factory = null;
    }    // afterTest()

    /*
     * Unit tests
     */
    
    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} throws a
     * {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructReadWritablePropertiesNull() {
        System.out.println("constructReadWritable - properties is null");

        factory = Shift.getShiftFactory();
        properties = null;
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesNull()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties is an empty map.
     */
    @Test
    public void testConstructReadWritablePropertiesEmptyNoException() {
        System.out.println("constructReadWritable - properties empty, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesEmptyNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has only the description.
     */
    @Test
    public void testConstructReadWritableDescriptionOnlyNoException() {
        System.out.println("constructReadWritable - properties has description only, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableDescriptionOnlyNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has only the "is angel shift" status.
     */
    @Test
    public void testConstructReadWritableIsAngelShiftOnlyNoException() {
        System.out.println("constructReadWritable - properties has isAngelShift only, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("isAngelShift", isAngelShift);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableIsAngelShiftOnlyNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when description is null.
     */
    @Test
    public void testConstructReadWritableDescriptionIsNullNoException() {
        System.out.println("constructReadWritable - description is null, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", null);
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", volunteer);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableDescriptionIsNullNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when isAngelShift is null.
     */
    @Test
    public void testConstructReadWritableIsAngelShiftIsNullNoException() {
        System.out.println("constructReadWritable - isAngelShift is null, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", null);
        properties.put("volunteer", volunteer);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableIsAngelShiftIsNullNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when volunteer is null.
     */
    @Test
    public void testConstructReadWritableVolunteerIsNullNoException() {
        System.out.println("constructReadWritable - volunteer is null, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", null);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableIsAngelShiftIsNullNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has all the properties the factory
     * needs.
     */
    @Test
    public void testConstructReadWritableNoException() {
        System.out.println("constructReadWritable - no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", volunteer);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has all the properties the factory
     * needs plus extraneous properties.
     */
    @Test
    public void testConstructReadWritablePropertiesExtraneousNoException() {
        System.out.println("constructReadWritable - properties has extraneous properties, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", volunteer);
        properties.put("foo", "bar");
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesExtraneousNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when the description and volunteer values are not the
     * correct objects.
     */
    @Test
    public void testConstructReadWritableDescriptionVolunteerWrongObjectsNoException() {
        System.out.println("constructReadWritable - description and volunteer values are not correct objects, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", new Volunteer("description", "", false));
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", "foo");
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableDescriptionVolunteerWrongObjectsNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when the isAngelShift value is not a Boolean.
     */
    @Test
    public void testConstructReadWritableIsAngelShiftWrongObjectNoException() {
        System.out.println("constructReadWritable - isAngelShift value is not Boolean, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", "true");
        properties.put("volunteer", volunteer);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableIsAngelShiftWrongObjectNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns null
     * when properties is an empty map.
     */
    @Test
    public void testConstructReadWritablePropertiesEmpty() {
        System.out.println("constructReadWritable - properties empty");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        
        Shift expected = null;
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesEmptyNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns null
     * when properties has only the description.
     */
    @Test
    public void testConstructReadWritableDescriptionOnly() {
        System.out.println("constructReadWritable - properties has description only");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        
        Shift expected = null;
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableDescriptionOnly()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when properties has only the "is angel shift" status.
     */
    @Test
    public void testConstructReadWritableIsAngelShiftOnly() {
        System.out.println("constructReadWritable - properties has isAngelShift only");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("isAngelShift", isAngelShift);
        
        Shift expected = new Shift("", isAngelShift);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableIsAngelShiftOnly()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when description is null.
     */
    @Test
    public void testConstructReadWritableDescriptionIsNull() {
        System.out.println("constructReadWritable - description is null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", null);
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", volunteer);
        
        Shift expected = new Shift("", isAngelShift);
        expected.setVolunteer(volunteer);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableDescriptionIsNull()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns null
     * when isAngelShift is null.
     */
    @Test
    public void testConstructReadWritableIsAngelShiftIsNull() {
        System.out.println("constructReadWritable - isAngelShift is null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", null);
        properties.put("volunteer", volunteer);
        
        Shift expected = null;
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableIsAngelShiftIsNull()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when volunteer is null.
     */
    @Test
    public void testConstructReadWritableVolunteerIsNull() {
        System.out.println("constructReadWritable - volunteer is null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", null);
        
        Shift expected = new Shift(description, isAngelShift);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableIsAngelShiftIsNull()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when properties has all the properties the factory needs.
     */
    @Test
    public void testConstructReadWritable() {
        System.out.println("constructReadWritable");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", volunteer);
        
        Shift expected = new Shift(description, isAngelShift);
        expected.setVolunteer(volunteer);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritable()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when properties has all the properties the factory needs
     * plus extraneous properties.
     */
    @Test
    public void testConstructReadWritablePropertiesExtraneous() {
        System.out.println("constructReadWritable - properties has extraneous properties");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", volunteer);
        properties.put("foo", "bar");
        
        Shift expected = new Shift(description, isAngelShift);
        expected.setVolunteer(volunteer);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesExtraneous()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when the description and volunteer values are not the
     * correct objects.
     */
    @Test
    public void testConstructReadWritableDescriptionVolunteerWrongObjects() {
        System.out.println("constructReadWritable - description and volunteer values are not correct objects");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", new Volunteer(description, "", false));
        properties.put("isAngelShift", isAngelShift);
        properties.put("volunteer", "foo");
        
        Shift expected = new Shift(description, isAngelShift);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableDescriptionVolunteerWrongObjects()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns null
     * when the isAngelShift value is not a Boolean.
     */
    @Test
    public void testConstructReadWritableIsAngelShiftWrongObject() {
        System.out.println("constructReadWritable - isAngelShift value is not Boolean");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("isAngelShift", 1);
        properties.put("volunteer", volunteer);
        
        Shift expected = null;
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableIsAngelShiftWrongObject()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns
     * the correct value with properties constructed from
     * {@link Shift#getReadWritableProperties()}.
     */
    @Test
    public void testConstructReadWritableReflexive() {
        System.out.println("constructReadWritable - reflexivity");

        factory = Shift.getShiftFactory();
        isAngelShift = !isAngelShift;
        Shift shift = new Shift(description, isAngelShift);
        shift.setVolunteer(volunteer);
        properties = shift.getReadWritableProperties();

        Shift expected = shift;
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableReflexive()

}    // ShiftFactoryTest
