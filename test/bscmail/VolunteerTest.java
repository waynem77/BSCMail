/*
 * Copyright © 2014 Wayne Miller
 */
package bscmail;

import java.util.*;
import main.ReadWritableFactory;
import main.ReadWritableTest;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Volunteer}
 *
 * @author Wayne Miller
 */
public class VolunteerTest extends ReadWritableTest {

    /**
     * Variable used to hold the name used in testing.
     */
    private String name;

    /**
     * Variable used to hold the email address used in testing.
     */
    private String email;

    /**
     * Variable used to hold the "can angel" status used in testing.
     */
    private boolean canAngel;

    /**
     * Variable used to hold the volunteer being tested.
     */
    private Volunteer volunteer;
    
    /**
     * Returns the volunteer to be tested.
     * 
     * @return the volunteer to be tested
     */
    @Override
    protected Volunteer getReadWritable() {
        return new Volunteer("foo", "bar", true);
    }    // getReadWritable()

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Volunteer");
        System.out.println("=========");
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
        name = "Foo Bar";
        email = "foo@bar.baz";
        canAngel = false;
        volunteer = null;
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        name = null;
        email = null;
        canAngel = false;
        volunteer = null;
    }    // tearDown()

    /* constructor */
    
    /**
     * Tests that {@link Volunteer#Volunteer(String, String, boolean)} does not
     * throw an exception when name and email are not null and canAngel is true.
     */
    @Test
    public void testConstructorCanAngelTrue() {
        System.out.println("constructor - canAngel is true");

        canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
    }    // testConstructorCanAngelTrue()

    /**
     * Tests that {@link Volunteer#Volunteer(String, String, boolean)} does not
     * throw an exception when name and email are not null and canAngel is
     * false.
     */
    @Test
    public void testConstructorCanAngelFalse() {
        System.out.println("constructor - canAngel is false");

        canAngel = false;
        volunteer = new Volunteer(name, email, canAngel);
    }    // testConstructorCanAngelTrue()

    /**
     * Tests that {@link Volunteer#Volunteer(String, String, boolean)} throws a
     * {@link NullPointerException} when name is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNameNull() {
        System.out.println("constructor - name is null");

        name = null;
        volunteer = new Volunteer(name, email, canAngel);
    }    // testConstructorNameNull()

    /**
     * Tests that {@link Volunteer#Volunteer(String, String, boolean)} throws a
     * {@link NullPointerException} when email is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorEmailNull() {
        System.out.println("constructor - email is null");

        email = null;
        volunteer = new Volunteer(name, email, canAngel);
    }    // testConstructorEmailNull()

    /* getName */
    
    /**
     * Tests that {@link Volunteer#getName()} does not throw an exception.
     */
    @Test
    public void testGetNameNoException() {
        System.out.println("getName - no exception");

        volunteer = new Volunteer(name, email, canAngel);
        volunteer.getName();
    }    // testGetNameNoException()

    /**
     * Tests that {@link Volunteer#getName()} returns the correct value.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");

        volunteer = new Volunteer(name, email, canAngel);
        String expected = name;
        String received = volunteer.getName();
        assertEquals(expected, received);
    }    // testGetName()

    /* getEmail */
    
    /**
     * Tests that {@link Volunteer#getEmail()} does not throw an exception.
     */
    @Test
    public void testGetEmailNoException() {
        System.out.println("getEmail - no exception");

        volunteer = new Volunteer(name, email, canAngel);
        volunteer.getEmail();
    }    // testGetEmailNoException()

    /**
     * Tests that {@link Volunteer#getEmail()} returns the correct value.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");

        volunteer = new Volunteer(name, email, canAngel);
        String expected = email;
        String received = volunteer.getEmail();
        assertEquals(expected, received);
    }    // testGetEmail()

    /* canAngel */
    
    /**
     * Tests that {@link Volunteer#canAngel()} does not throw an exception when
     * canAngel is true.
     */
    @Test
    public void testGetCanAngelTrueNoException() {
        System.out.println("canAngel - true, no exception");

        canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        volunteer.canAngel();
    }    // testGetCanAngelTrueNoException()

    /**
     * Tests that {@link Volunteer#canAngel()} returns the correct value when
     * canAngel is true.
     */
    @Test
    public void testGetCanAngelTrue() {
        System.out.println("canAngel - true");

        canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        boolean expected = canAngel;
        boolean received = volunteer.canAngel();
        assertEquals(expected, received);
    }    // testGetCanAngelTrue()

    /**
     * Tests that {@link Volunteer#canAngel()} does not throw an exception when
     * canAngel is false.
     */
    @Test
    public void testGetCanAngelFalseNoException() {
        System.out.println("canAngel - false, no exception");

        canAngel = false;
        volunteer = new Volunteer(name, email, canAngel);
        volunteer.canAngel();
    }    // testGetCanAngelFalseNoException()

    /**
     * Tests that {@link Volunteer#canAngel()} returns the correct value when
     * canAngel is false.
     */
    @Test
    public void testGetCanAngelFalse() {
        System.out.println("canAngel - false");

        canAngel = false;
        volunteer = new Volunteer(name, email, canAngel);
        boolean expected = canAngel;
        boolean received = volunteer.canAngel();
        assertEquals(expected, received);
    }    // testGetCanAngelFalse()
    
    /* getReadWritableProperties */
    
    /**
     * Tests that {@link Volunteer#getReadWritableProperties()} returns the
     * correct value.
     */
    @Test
    public void testGetReadWritableProperties() {
        System.out.println("getReadWritableProperties");
        
        volunteer = new Volunteer(name, email, canAngel);
        Map<String, Object> expected = new HashMap<>();
        expected.put("name", name);
        expected.put("email", email);
        expected.put("canAngel", canAngel);
        Map<String, Object> received = volunteer.getReadWritableProperties();
        assertEquals(expected, received);
    }    // testGetReadWritableProperties()
    
    /**
     * Tests that the return value of {@link Volunteer#getReadWritableProperties()}
     * has the correct iteration order.
     */
    @Test
    public void testGetReadWritablePropertiesIterationOrder() {
        System.out.println("getReadWritableProperties - iteration order");
        
        volunteer = new Volunteer(name, email, canAngel);
        Map<String, Object> properties = volunteer.getReadWritableProperties();
        List<String> expected = Arrays.asList("name", "email", "canAngel");
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesIterationOrder()
    
    /* equals */
    
    /**
     * Tests that {@link Volunteer#equals(Object)} does not throw an exception
     * when the argument is null.
     */
    @Test
    public void testEqualsNullNoException() {
        System.out.println("equals - null, no exception");
        
        volunteer = new Volunteer(name, email, canAngel);
        Object obj = null;
        volunteer.equals(obj);
    }    // testEqualsNullNoException()
    
    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void testEqualsNull() {
        System.out.println("equals - null");
        
        volunteer = new Volunteer(name, email, canAngel);
        Object obj = null;
        boolean received = volunteer.equals(obj);
        assertFalse(received);
    }    // testEqualsNull()
    
    /**
     * Tests that {@link Volunteer#equals(Object)} does not throw an exception
     * when the argument is not a volunteer.
     */
    @Test
    public void testEqualsNotAVolunteerNoException() {
        System.out.println("equals - not a volunteer, no exception");
        
        volunteer = new Volunteer(name, email, canAngel);
        Object obj = 1;
        volunteer.equals(obj);
    }    // testEqualsNotAVolunteerNoException()
    
    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument is not a volunteer.
     */
    @Test
    public void testEqualsNotAVolunteer() {
        System.out.println("equals - not a volunteer");
        
        volunteer = new Volunteer(name, email, canAngel);
        Object obj = 1;
        boolean received = volunteer.equals(obj);
        assertFalse(received);
    }    // testEqualsNotAVolunteer()
    
    /**
     * Tests that {@link Volunteer#equals(Object)} does not throw an exception
     * when the argument is a volunteer.
     */
    @Test
    public void testEqualsVolunteerNoException() {
        System.out.println("equals - volunteer, no exception");
        
        volunteer = new Volunteer(name, email, canAngel);
        Object obj = new Volunteer(name, email, !canAngel);
        volunteer.equals(obj);
    }    // testEqualsVolunteerNoException()
    
    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument has a different name than the caller.
     */
    @Test
    public void testEqualsDifferentName() {
        System.out.println("equals - volunteer, different name");
        
        name = "Foo";
        volunteer = new Volunteer(name, email, canAngel);
        name = "Bar";
        Object obj = new Volunteer(name, email, canAngel);
        boolean expected = false;
        boolean received = volunteer.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentName()
    
    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument has a different email address than the caller.
     */
    @Test
    public void testEqualsDifferentEmail() {
        System.out.println("equals - volunteer, different email");
        
        email = "Foo";
        volunteer = new Volunteer(name, email, canAngel);
        email = "Bar";
        Object obj = new Volunteer(name, email, canAngel);
        boolean expected = false;
        boolean received = volunteer.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentEmail()
    
    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument has a different "can angel" status than the caller.
     */
    @Test
    public void testEqualsDifferentCanAngel() {
        System.out.println("equals - volunteer, different can angel");
        
        canAngel = true;
        volunteer = new Volunteer(name, email, canAngel);
        canAngel = !canAngel;
        Object obj = new Volunteer(name, email, canAngel);
        boolean expected = false;
        boolean received = volunteer.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentCanAngel()
    
    /**
     * Tests that {@link Volunteer#equals(Object)} returns true when the
     * argument has the same name, email address, and "can angel" status as the
     * caller.
     */
    @Test
    public void testEqualsEquivalent() {
        System.out.println("equals - volunteer, equivalent");
        
        volunteer = new Volunteer(name, email, canAngel);
        Object obj = new Volunteer(name, email, canAngel);
        boolean expected = true;
        boolean received = volunteer.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsEquivalent()
    
    /**
     * Tests that {@link Volunteer#equals(Object)} returns true when the
     * argument is the same object as the caller.
     */
    @Test
    public void testEqualsIdentical() {
        System.out.println("equals - volunteer, identical");
        
        volunteer = new Volunteer(name, email, canAngel);
        Object obj = volunteer;
        boolean expected = true;
        boolean received = volunteer.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsIdentical()
    
    /* hashCode */
    
    /**
     * Tests that {@link Volunteer#hashCode()} does not throw an exception.
     */
    @Test
    public void testHashCodeNoException() {
        System.out.println("hashCode - no exception");
        
        volunteer = new Volunteer(name, email, canAngel);
        volunteer.hashCode();
    }    // testHashCodeNoException()
    
    /**
     * Tests that {@link Volunteer#hashCode()} returns equal values for equal
     * volunteers.
     */
    @Test
    public void testHashCodeEqual() {
        System.out.println("hashCode - equal volunteers");
        
        volunteer = new Volunteer(name, email, canAngel);
        Volunteer experimental = new Volunteer(name, email, canAngel);
        int expected = volunteer.hashCode();
        int received = experimental.hashCode();
        assertEquals(expected, received);
    }    // testHashCodeEqual()
    
    /* clone */
    
    /**
     * Tests that {@link Volunteer#clone()} does not throw an exception.
     */
    @Test
    public void testCloneNoException() {
        System.out.println("clone - no exception");
        
        volunteer = new Volunteer(name, email, canAngel);
        volunteer.clone();
    }    // testCloneNoException()
    
    /**
     * Tests that the return value of {@link Volunteer#clone()} is not null.
     */
    @Test
    public void testCloneNotNull() {
        System.out.println("clone - not null");
        
        volunteer = new Volunteer(name, email, canAngel);
        Volunteer received = volunteer.clone();
        assertNotNull(received);
    }    // testCloneNotNull()
    
    /**
     * Tests that the return value of {@link Volunteer#clone()} is equal to the
     * argument.
     */
    @Test
    public void testCloneEqual() {
        System.out.println("clone - equal");
        
        volunteer = new Volunteer(name, email, canAngel);
        Volunteer expected = volunteer;
        Volunteer received = volunteer.clone();
        assertEquals(expected, received);
    }    // testCloneEqual()
    
    /**
     * Tests that the return value of {@link Volunteer#clone()} is not identical
     * to the argument.
     */
    @Test
    public void testCloneIdentity() {
        System.out.println("clone - not identical");
        
        volunteer = new Volunteer(name, email, canAngel);
        Volunteer received = volunteer.clone();
        assertFalse(volunteer == received);
    }    // testCloneIdentity()
    
    /* toString */
    
    /**
     * Tests that {@link Volunteer#toString()} does not throw an exception.
     */
    @Test
    public void testToStringNoException() {
        System.out.println("toString - no exception");
        
        volunteer = new Volunteer(name, email, canAngel);
        volunteer.toString();
    }    // testToStringNoException()
    
    /**
     * Tests that the return value of {@link Volunteer#toString()} is not null.
     */
    @Test
    public void testToStringNotNull() {
        System.out.println("toString - not null");
        
        volunteer = new Volunteer(name, email, canAngel);
        String received = volunteer.toString();
        assertNotNull(received);
    }    // testToStringNotNull()
    
    /**
     * Tests that {@link Volunteer#toString()} returns the volunteer's name.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        volunteer = new Volunteer(name, email, canAngel);
        String expected = name;
        String received = volunteer.toString();
        assertEquals(expected, received);
    }    // testToString()
    
    /* getVolunteerFactory */
    
    /**
     * Tests that {@link Volunteer#getVolunteerFactory()} does not throw an exception.
     */
    @Test
    public void testGetVolunteerFactoryNoException() {
        System.out.println("getVolunteerFactory - no exception");
        
        Volunteer.getVolunteerFactory();
    }    // testToStringNoException()
    
    /**
     * Tests that the return value of {@link Volunteer#getVolunteerFactory()} is not
     * null.
     */
    @Test
    public void testGetVolunteerFactoryNotNull() {
        System.out.println("getVolunteerFactory - not null");
        
        ReadWritableFactory factory = Volunteer.getVolunteerFactory();
        assertNotNull(factory);
    }    // testGetVolunteerFactoryNotNull()

}    // VolunteerTest
