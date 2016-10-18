/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail;

import java.util.*;
import main.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Manager}
 * @author Wayne Miller
 */
public class ShiftTest extends ReadWritableTest {

    /**
     * Variable used to hold the description used in testing.
     */
    private String description;

    /**
     * Variable used to hold the "is angel" status used in testing.
     */
    private boolean isAngelShift;

    /**
     * Variable used to hold the volunteer used in testing.
     */
    private Volunteer volunteer;

    /**
     * Variable used to hold the shift being tested.
     */
    private Shift shift;
    
    /**
     * Returns a valid volunteer that cannot perform angel shifts.
     * @return a valid volunteer that cannot perform angel shifts
     */
    private Volunteer getVolunteer() {
        String name = "Foo Bar";
        String email = "foo@bar.baz";
        boolean isAngel = false;
        return new Volunteer(name, email, isAngel);
    }    // getVolunteer()
    
    /**
     * Returns a valid volunteer that can perform angel shifts.
     * @return a valid volunteer that can perform angel shifts
     */
    private Volunteer getAngel() {
        String name = "Foo Bar";
        String email = "foo@bar.baz";
        boolean isAngel = true;
        return new Volunteer(name, email, isAngel);
    }    // getAngel()
    
    /**
     * Returns the shift to be tested.
     * 
     * @return the shift to be tested
     */
    @Override
    protected Shift getReadWritable() {
        Shift shift = new Shift("foo", false);
        shift.setVolunteer(getVolunteer());
        return shift;
    }    // getReadWritable()
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Shift");
        System.out.println("=====");
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
    public void setUp() {
        description = "Door 10:15–11:15";
        isAngelShift = false;
        volunteer = null;
        shift = null;
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        description = null;
        isAngelShift = false;
        volunteer = null;
        shift = null;
    }    // tearDown()

    /* constructor */
    
    /**
     * Tests that {@link Shift#Shift(String, boolean)} does not throw an
     * exception when isAngelShift is true.
     */
    @Test
    public void testConstructorIsAngelShiftTrue() {
        System.out.println("constructor - isAngelShift is true");
        
        isAngelShift = true;
        shift = new Shift(description, isAngelShift);
    }    // testConstructorIsAngelShiftTrue()

    /**
     * Tests that {@link Shift#Shift(String, boolean)} does not throw an
     * exception when isAngel is false.
     */
    @Test
    public void testConstructorIsAngelShiftFalse() {
        System.out.println("constructor - isAngelShift is false");
        
        isAngelShift = false;
        shift = new Shift(description, isAngelShift);
    }    // testConstructorIsAngelShiftFalse()

    /**
     * Tests that {@link Shift#Shift(String, boolean)} throws a {@link NullPointerException}
     * when description is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorDescriptionNull() {
        System.out.println("constructor - description is null");
        
        description = null;
        shift = new Shift(description, isAngelShift);
    }    // testConstructorDescriptionNull()

    /* getDescription */
    
    /**
     * Tests that {@link Shift#getDescription()} does not throw an exception.
     */
    @Test
    public void testGetDescriptionNoException() {
        System.out.println("getDescription - no exception");

        shift = new Shift(description, isAngelShift);
        shift.getDescription();
    }    // testGetDescriptionNoException()
    
    /**
     * Tests that {@link Shift#getDescription()} returns the correct value.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");

        shift = new Shift(description, isAngelShift);
        String expected = description;
        String received = shift.getDescription();
        assertEquals(expected, received);
    }    // testGetDescription()
    
    /* isAngelShift */
    
    /**
     * Tests that {@link Shift#isAngelShift()} does not throw an exception when
     * isAngelShift is true.
     */
    @Test
    public void testIsAngelShiftTrueNoException() {
        System.out.println("isAngelShift - true, no exception");

        isAngelShift = true;
        shift = new Shift(description, isAngelShift);
        shift.isAngelShift();
    }    // testIsAngelShiftTrueNoException()
    
    /**
     * Tests that {@link Shift#isAngelShift()} returns the correct value when
     * isAngelShift is true.
     */
    @Test
    public void testIsAngelShiftTrue() {
        System.out.println("isAngelShift - true");

        isAngelShift = true;
        shift = new Shift(description, isAngelShift);
        boolean expected = isAngelShift;
        boolean received = shift.isAngelShift();
        assertEquals(expected, received);
    }    // testIsAngelShiftTrue()
    
    /**
     * Tests that {@link Shift#isAngelShift()} does not throw an exception when
     * isAngelShift is false.
     */
    @Test
    public void testIsAngelShiftFalseNoException() {
        System.out.println("isAngelShift - false, no exception");

        isAngelShift = false;
        shift = new Shift(description, isAngelShift);
        shift.isAngelShift();
    }    // testIsAngelShiftFalseNoException()
    
    /**
     * Tests that {@link Shift#isAngelShift()} returns the correct value when
     * isAngelShift is false.
     */
    @Test
    public void testIsAngelShiftFalse() {
        System.out.println("isAngelShift - false");

        isAngelShift = false;
        shift = new Shift(description, isAngelShift);
        boolean expected = isAngelShift;
        boolean received = shift.isAngelShift();
        assertEquals(expected, received);
    }    // testIsAngelShiftFalse()
    
    /* isOpen */
    
    /**
     * Tests that {@link Shift#isOpen()} returns true when called before
     * {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testIsOpenBeforeSetVolunteer() {
        System.out.println("isOpen - before setVolunteer");
        
        shift = new Shift(description, isAngelShift);
        boolean received = shift.isOpen();
        assertTrue(received);
    }    // testIsOpenBeforeSetVolunteer()
    
    /**
     * Tests that {@link Shift#isOpen()} returns false when called after
     * {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testIsOpenAfterSetVolunteer() {
        System.out.println("isOpen - after setVolunteer");
        
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
        boolean received = shift.isOpen();
        assertFalse(received);
    }    // testIsOpenAfterSetVolunteer()
    
    /**
     * Tests that {@link Shift#ifOpen()} returns true when called after
     * {@link Shift#unsetVolunteer()} is called with null.
     */
    @Test
    public void testIsOpenAfterUnsetVolunteer() {
        System.out.println("isOpen - after unsetVolunteer");
        
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
        volunteer = null;
        shift.setVolunteer(volunteer);
        boolean received = shift.isOpen();
        assertTrue(received);
    }    // testIsOpenAfterUnsetVolunteer()
    
    /* getVolunteer / setVolunteer */
    
    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception when
     * called before {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testGetVolunteerBeforeSetVolunteerNoException() {
        System.out.println("getVolunteer - before setVolunteer, no exception");
        
        shift = new Shift(description, isAngelShift);
        shift.getVolunteer();
    }    // testGetVolunteerBeforeSetVolunteerNoException()
    
    /**
     * Tests that {@link Shift#getVolunteer()} returns null when called before
     * {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testGetVolunteerBeforeSetVolunteer() {
        System.out.println("getVolunteer - before setVolunteer");
        
        shift = new Shift(description, isAngelShift);
        Volunteer expected = null;
        Volunteer received = shift.getVolunteer();
        assertEquals(expected, received);
    }    // testGetVolunteerBeforeSetVolunteer()
    
    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception
     * when called after {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testGetVolunteerAfterSetVolunteerNoException() {
        System.out.println("getVolunteer - after setVolunteer, no exception");
        
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
        shift.getVolunteer();
    }    // testGetVolunteerAfterSetVolunteerNoException()
    
    /**
     * Tests that {@link Shift#getVolunteer()} returns the correct value when
     * called after {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testGetVolunteerAfterSetVolunteer() {
        System.out.println("getVolunteer - after setVolunteer");
        
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
        Volunteer expected = volunteer;
        Volunteer received = shift.getVolunteer();
        assertEquals(expected, received);
    }    // testGetVolunteerAfterSetVolunteer()
    
    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception when
     * called after {@link Shift#unsetVolunteer()} called with null.
     */
    @Test
    public void testGetVolunteerAfterSetVolunteerNullNoException() {
        System.out.println("getVolunteer - after setVolunteer(null), no exception");
        
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
        volunteer = null;
        shift.setVolunteer(volunteer);
        shift.getVolunteer();
    }    // testGetVolunteerAfterSetVolunteerNullNoException()
    
    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception when
     * called after {@link Shift#unsetVolunteer()} called with null.
     */
    @Test
    public void testGetVolunteerAfterSetVolunteerNull() {
        System.out.println("getVolunteer - after setVolunteer(null)");
        
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
        volunteer = null;
        shift.setVolunteer(volunteer);
        Volunteer expected = volunteer;
        Volunteer received = shift.getVolunteer();
        assertEquals(expected, received);
    }    // testGetVolunteerAfterSetVolunteerNull()
    
    /**
     * Tests that {@link Shift#setVolunteer(Volunteer)} does not throw an
     * exception when volunteer is null.
     */
    @Test
    public void testSetVolunteerVolunteerNull() {
        System.out.println("setVolunteer - volunteer is null");
        
        shift = new Shift(description, isAngelShift);
        volunteer = null;
        shift.setVolunteer(volunteer);
    }    // testSetVolunteerVolunteerNull()
    
    /**
     * Tests that {@link Shift#setVolunteer(Volunteer)} does not throw an
     * exception when the shift is not an angel shift and the volunteer cannot
     * perform angel shifts.
     */
    @Test
    public void testSetVolunteerNotAngelCannotAngel() {
        System.out.println("setVolunteer - not angel, volunteer cannot angel");
        
        isAngelShift = false;
        shift = new Shift(description, isAngelShift);
        volunteer = getVolunteer();
        shift.setVolunteer(volunteer);
    }    // testSetVolunteerNotAngelCannotAngel()
    
    /**
     * Tests that {@link Shift#setVolunteer(Volunteer)} does not throw an
     * exception when the shift is not an angel shift and the volunteer can
     * perform angel shifts.
     */
    @Test
    public void testSetVolunteerNotAngelCanAngel() {
        System.out.println("setVolunteer - not angel, volunteer can angel");
        
        isAngelShift = false;
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
    }    // testSetVolunteerNotAngelCanAngel()
    
    /**
     * Tests that {@link Shift#setVolunteer(Volunteer)} throws an
     * {@link IllegalArgumentException} exception when the shift is an angel
     * shift and the volunteer cannot perform angel shifts.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetVolunteerIsAngelCannotAngel() {
        System.out.println("setVolunteer - is angel, volunteer cannot angel");
        
        isAngelShift = true;
        shift = new Shift(description, isAngelShift);
        volunteer = getVolunteer();
        shift.setVolunteer(volunteer);
    }    // testSetVolunteerIsAngelCannotAngel()
    
    /**
     * Tests that {@link Shift#setVolunteer(Volunteer)} does not throw an
     * exception when the shift is an angel shift and the volunteer can
     * perform angel shifts.
     */
    @Test
    public void testSetVolunteerIsAngelCanAngel() {
        System.out.println("setVolunteer - is angel, volunteer can angel");
        
        isAngelShift = true;
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
    }    // testSetVolunteerIsAngelCanAngel()
    
    /* getReadWritableProperties */
    
    /**
     * Tests that {@link Shift#getReadWritableProperties()} returns the
     * correct value for a shift with a volunteer.
     */
    @Test
    public void testGetReadWritablePropertiesVolunteer() {
        System.out.println("getReadWritableProperties - shift has volunteer");
        
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
        Map<String, Object> expected = new HashMap<>();
        expected.put("description", description);
        expected.put("isAngelShift", isAngelShift);
        expected.put("volunteer", volunteer);
        Map<String, Object> received = shift.getReadWritableProperties();
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesVolunteer()
    
    /**
     * Tests that {@link Shift#getReadWritableProperties()} returns the
     * correct value for a shift with no volunteer.
     */
    @Test
    public void testGetReadWritablePropertiesNoVolunteer() {
        System.out.println("getReadWritableProperties - shift does not have a volunteer");
        
        shift = new Shift(description, isAngelShift);
        Map<String, Object> expected = new HashMap<>();
        expected.put("description", description);
        expected.put("isAngelShift", isAngelShift);
        Map<String, Object> received = shift.getReadWritableProperties();
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesNoVolunteer()
    
    /**
     * Tests that the return value of {@link Shift#getReadWritableProperties()}
     * has the correct iteration order for a shift with a volunteer.
     */
    @Test
    public void testGetReadWritablePropertiesIterationOrderVolunteer() {
        System.out.println("getReadWritableProperties - iteration order, shift has volunteer");
        
        shift = new Shift(description, isAngelShift);
        volunteer = getAngel();
        shift.setVolunteer(volunteer);
        Map<String, Object> properties = shift.getReadWritableProperties();
        List<String> expected = Arrays.asList("description", "isAngelShift", "volunteer");
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesIterationOrderVolunteer()
    
    /**
     * Tests that the return value of {@link Shift#getReadWritableProperties()}
     * has the correct iteration order for a shift with no volunteer.
     */
    @Test
    public void testGetReadWritablePropertiesIterationOrderNoVolunteer() {
        System.out.println("getReadWritableProperties - iteration order, shift does not have a volunteer");
        
        shift = new Shift(description, isAngelShift);
        Map<String, Object> properties = shift.getReadWritableProperties();
        List<String> expected = Arrays.asList("description", "isAngelShift");
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesIterationOrderNoVolunteer()

    /* equals */
    
    /**
     * Tests that {@link Shift#equals(Object)} does not throw an exception
     * when the argument is null.
     */
    @Test
    public void testEqualsNullNoException() {
        System.out.println("equals - null, no exception");
        
        shift = new Shift(description, isAngelShift);
        Object obj = null;
        shift.equals(obj);
    }    // testEqualsNullNoException()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void testEqualsNull() {
        System.out.println("equals - null");
        
        shift = new Shift(description, isAngelShift);
        Object obj = null;
        boolean received = shift.equals(obj);
        assertFalse(received);
    }    // testEqualsNull()
    
    /**
     * Tests that {@link Shift#equals(Object)} does not throw an exception
     * when the argument is not a shift.
     */
    @Test
    public void testEqualsNotAShiftNoException() {
        System.out.println("equals - not a shift, no exception");
        
        shift = new Shift(description, isAngelShift);
        Object obj = 1;
        shift.equals(obj);
    }    // testEqualsNotAShiftNoException()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument is not a shift.
     */
    @Test
    public void testEqualsNotAShift() {
        System.out.println("equals - not a shift");
        
        shift = new Shift(description, isAngelShift);
        Object obj = 1;
        boolean received = shift.equals(obj);
        assertFalse(received);
    }    // testEqualsNotAShift()
    
    /**
     * Tests that {@link Shift#equals(Object)} does not throw an exception
     * when the argument is a shift.
     */
    @Test
    public void testEqualsShiftNoException() {
        System.out.println("equals - shift, no exception");
        
        shift = new Shift(description, isAngelShift);
        isAngelShift = !isAngelShift;
        Object obj = new Shift(description, isAngelShift);
        shift.equals(obj);
    }    // testEqualsShiftNoException()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument has a different description than the caller.
     */
    @Test
    public void testEqualsDifferentDescription() {
        System.out.println("equals - shift, different description");
        
        description = "Foo";
        shift = new Shift(description, isAngelShift);
        description = "Bar";
        Object obj = new Shift(description, isAngelShift);
        boolean expected = false;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentDescription()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument has a different "is angel shift" status than the caller.
     */
    @Test
    public void testEqualsDifferentIsAngelShift() {
        System.out.println("equals - shift, different isAngelShift");
        
        shift = new Shift(description, isAngelShift);
        isAngelShift = !isAngelShift;
        Object obj = new Shift(description, isAngelShift);
        boolean expected = false;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentIsAngelShift()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * caller has no volunteer assigned and the argument has a volunteer
     * assigned.
     */
    @Test
    public void testEqualsNoVolunteerArgHasVolunteer() {
        System.out.println("equals - no volunteer, argument has volunteer");
        
        shift = new Shift(description, isAngelShift);
        Shift obj = new Shift(description, isAngelShift);
        String name = "Foo";
        String email = "Bar";
        boolean canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        obj.setVolunteer(volunteer);
        boolean expected = false;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsNoVolunteerArgHasVolunteer()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * caller has a volunteer assigned and the argument has no volunteer
     * assigned.
     */
    @Test
    public void testEqualsVolunteerArgHasNoVolunteer() {
        System.out.println("equals - volunteer, argument has no volunteer");
        
        shift = new Shift(description, isAngelShift);
        String name = "Foo";
        String email = "Bar";
        boolean canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        shift.setVolunteer(volunteer);
        Object obj = new Shift(description, isAngelShift);
        boolean expected = false;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsVolunteerArgHasNoVolunteer()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * caller has a different volunteer assigned to it than the argument.
     */
    @Test
    public void testEqualsDifferentVolunteers() {
        System.out.println("equals - different volunteers");
        
        shift = new Shift(description, isAngelShift);
        String name = "Foo";
        String email = "Bar";
        boolean canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        shift.setVolunteer(volunteer);
        Shift obj = new Shift(description, isAngelShift);
        name = "Bar";
        email = "Foo";
        volunteer = new Volunteer(name, email, canAngel);
        obj.setVolunteer(volunteer);
        boolean expected = false;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentVolunteers()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns true when neither the
     * caller nor the argument have volunteers assigned to them.
     */
    @Test
    public void testEqualsNoVolunteers() {
        System.out.println("equals - no volunteers");
        
        shift = new Shift(description, isAngelShift);
        Object obj = new Shift(description, isAngelShift);
        boolean expected = true;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsNoVolunteers()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns true when the
     * caller has the same volunteer assigned to it as the argument.
     */
    @Test
    public void testEqualsSameVolunteer() {
        System.out.println("equals - same volunteers");
        
        shift = new Shift(description, isAngelShift);
        String name = "Foo";
        String email = "Bar";
        boolean canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        shift.setVolunteer(volunteer);
        Shift obj = new Shift(description, isAngelShift);
        volunteer = new Volunteer(name, email, canAngel);
        obj.setVolunteer(volunteer);
        boolean expected = true;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsSameVolunteer()
    
    /**
     * Tests that {@link Shift#equals(Object)} returns true when the
     * argument is the same object as the caller.
     */
    @Test
    public void testEqualsIdentical() {
        System.out.println("equals - shift, identical");
        
        shift = new Shift(description, isAngelShift);
        Object obj = shift;
        boolean expected = true;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsIdentical()
    
    /**
     * Tests that {@link Shift#hashCode()} does not throw an exception.
     */
    @Test
    public void testHashCodeNoException() {
        System.out.println("hashCode - no exception");
        
        shift = new Shift(description, isAngelShift);
        shift.hashCode();
    }    // testHashCodeNoException()
    
    /* hashCode */
    
    /**
     * Tests that {@link Shift#hashCode()} returns equal values for equal
     * shifts without volunteers.
     */
    @Test
    public void testHashCodeEqualNoVolunteers() {
        System.out.println("hashCode - equal shifts without volunteers");
        
        shift = new Shift(description, isAngelShift);
        Shift experimental = new Shift(description, isAngelShift);
        int expected = shift.hashCode();
        int received = experimental.hashCode();
        assertEquals(expected, received);
    }    // testHashCodeEqualNoVolunteers()
    
    /**
     * Tests that {@link Shift#hashCode()} returns equal values for equal
     * shifts with volunteers.
     */
    @Test
    public void testHashCodeEqualWithVolunteers() {
        System.out.println("hashCode - equal shifts with volunteers");
        
        shift = new Shift(description, isAngelShift);
        String name = "Foo";
        String email = "Bar";
        boolean canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        shift.setVolunteer(volunteer);
        Shift experimental = new Shift(description, isAngelShift);
        volunteer = new Volunteer(name, email, canAngel);
        experimental.setVolunteer(volunteer);
        int expected = shift.hashCode();
        int received = experimental.hashCode();
        assertEquals(expected, received);
    }    // testHashCodeEqualWithVolunteers()
    
    /* clone */
    
    /**
     * Tests that {@link Shift#clone()} does not throw an exception when the
     * shift does not have a volunteer.
     */
    @Test
    public void testCloneNoVolunteerNoException() {
        System.out.println("clone - no volunteer, no exception");
        
        shift = new Shift(description, isAngelShift);
        shift.clone();
    }    // testCloneNoVolunteerNoException()
    
    /**
     * Tests that {@link Shift#clone()} does not throw an exception when the
     * shift has a volunteer.
     */
    @Test
    public void testCloneVolunteerNoException() {
        System.out.println("clone - volunteer, no exception");
        
        shift = new Shift(description, isAngelShift);
        String name = "Foo";
        String email = "Bar";
        boolean canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        shift.setVolunteer(volunteer);
        shift.clone();
    }    // testCloneVolunteerNoException()
    
    /**
     * Tests that the return value of {@link Shift#clone()} is not null when the
     * shift does not have a volunteer.
     */
    @Test
    public void testCloneNoVolunteerNotNull() {
        System.out.println("clone - no volunteer, not null");
        
        shift = new Shift(description, isAngelShift);
        Shift received = shift.clone();
        assertNotNull(received);
    }    // testCloneNoVolunteerNotNull()
    
    /**
     * Tests that the return value of {@link Shift#clone()} is not null when the
     * shift has a volunteer.
     */
    @Test
    public void testCloneVolunteerNotNull() {
        System.out.println("clone - volunteer, not null");
        
        shift = new Shift(description, isAngelShift);
        String name = "Foo";
        String email = "Bar";
        boolean canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        shift.setVolunteer(volunteer);
        Shift received = shift.clone();
        assertNotNull(received);
    }    // testCloneVolunteerNotNull()
    
    /**
     * Tests that the return value of {@link Shift#clone()} is equal to the
     * argument when the shift does not have a volunteer.
     */
    @Test
    public void testCloneNoVolunteerEqual() {
        System.out.println("clone - no volunteer, equal");
        
        shift = new Shift(description, isAngelShift);
        Shift expected = shift;
        Shift received = shift.clone();
        assertEquals(expected, received);
    }    // testCloneNoVolunteerNoException()
    
    /**
     * Tests that the return value of {@link Shift#clone()} is equal to the
     * argument when the shift has a volunteer.
     */
    @Test
    public void testCloneVolunteerEqual() {
        System.out.println("clone - volunteer, equal");
        
        shift = new Shift(description, isAngelShift);
        String name = "Foo";
        String email = "Bar";
        boolean canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        shift.setVolunteer(volunteer);
        Shift expected = shift;
        Shift received = shift.clone();
        assertEquals(expected, received);
    }    // testCloneVolunteerNoException()
    
    /**
     * Tests that the return value of {@link Shift#clone()} is not identical to
     * the argument when the shift does not have a volunteer.
     */
    @Test
    public void testCloneNoVolunteerIdentity() {
        System.out.println("clone - no volunteer, not identical");
        
        shift = new Shift(description, isAngelShift);
        Shift received = shift.clone();
        assertFalse(shift == received);
    }    // testCloneNoVolunteerNoException()
    
    /**
     * Tests that the return value of {@link Shift#clone()} is not identical to
     * the argument when the shift has a volunteer.
     */
    @Test
    public void testCloneVolunteerIdentity() {
        System.out.println("clone - volunteer, not identical");
        
        shift = new Shift(description, isAngelShift);
        String name = "Foo";
        String email = "Bar";
        boolean canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        shift.setVolunteer(volunteer);
        Shift received = shift.clone();
        assertFalse(shift == received);
    }    // testCloneVolunteerNoException()
    
    /* toString */
    
    /**
     * Tests that {@link Shift#toString()} does not throw an exception.
     */
    @Test
    public void testToStringNoException() {
        System.out.println("toString - no exception");
        
        shift = new Shift(description, isAngelShift);
        shift.toString();
    }    // testToStringNoException()
    
    /**
     * Tests that the return value of {@link Shift#toString()} is not null.
     */
    @Test
    public void testToStringNotNull() {
        System.out.println("toString - not null");
        
        shift = new Shift(description, isAngelShift);
        String received = shift.toString();
        assertNotNull(received);
    }    // testToStringNotNull()
    
    /**
     * Tests that {@link Shift#toString()} returns the shift's description.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        shift = new Shift(description, isAngelShift);
        String expected = description;
        String received = shift.toString();
        assertEquals(expected, received);
    }    // testToString()
    
    /* getShiftFactory */
    
    /**
     * Tests that {@link Shift#getShiftFactory()} does not throw an exception.
     */
    @Test
    public void testGetShiftFactoryNoException() {
        System.out.println("getShiftFactory - no exception");
        
        Shift.getShiftFactory();
    }    // testToStringNoException()
    
    /**
     * Tests that the return value of {@link Shift#getShiftFactory()} is not
     * null.
     */
    @Test
    public void testGetShiftFactoryNotNull() {
        System.out.println("getShiftFactory - not null");
        
        ReadWritableFactory factory = Shift.getShiftFactory();
        assertNotNull(factory);
    }    // testGetShiftFactoryNotNull()
    
}    // ShiftTest
