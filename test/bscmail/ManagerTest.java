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
public class ManagerTest extends ReadWritableTest {

    /**
     * Variable used to hold the name used in testing.
     */
    private String name;
    
    /**
     * Variable used to hold the email address used in testing.
     */
    private String email;
    
    /**
     * Variable used to hold the phone number used in testing.
     */
    private String phone;
    
    /**
     * Variable used to hold the manager being tested.
     */
    private Manager manager;
    
    
    /**
     * Returns the manager to be tested.
     * 
     * @return the manager to be tested
     */
    @Override
    protected Manager getReadWritable() {
        return new Manager("foo", "bar", "baz");
    }    // getReadWritable()
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Manager");
        System.out.println("=======");
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
        name = "Foo Bar";
        email = "foo@bar.baz";
        phone = "555-FOO-BARR";
        manager = null;
    }    // beforeTest()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void afterTest() {
        name = null;
        email = null;
        phone = null;
        manager = null;
    }    // afterTest()
    
    /* constructor */
    
    /**
     * Tests that {@link Manager#Manager(String, String, String)} does not
     * throw an exception when no parameter is null.
     */
    @Test
    public void testConstructor() {
        System.out.println("constructor");
        
        manager = new Manager(name, email, phone);
    }    // testConstructor()
    
    /**
     * Tests that {@link Manager#Manager(String, String, String)} throws an
     * {@link NullPointerException} when the name is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNameNull() {
        System.out.println("constructor - name is null");
        
        name = null;
        manager = new Manager(name, email, phone);
    }    // testConstructorNameNull()
    
    /**
     * Tests that {@link Manager#Manager(String, String, String)} throws an
     * {@link NullPointerException} when the email address is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorEmailNull() {
        System.out.println("constructor - email is null");
        
        email = null;
        manager = new Manager(name, email, phone);
    }    // testConstructorEmailNull()
    
    /**
     * Tests that {@link Manager#Manager(String, String, String)} throws an
     * {@link NullPointerException} when the phone number is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorPhoneNull() {
        System.out.println("constructor - phone is null");
        
        phone = null;
        manager = new Manager(name, email, phone);
    }    // testConstructorPhoneNull()
    
    /* getName */
    
    /**
     * Tests that {@link Manager#getName()} does not throw an exception.
     */
    @Test
    public void testGetNameNoException() {
        System.out.println("getName - no expcetion");
        
        manager = new Manager(name, email, phone);
        manager.getName();
    }    // testGetNameNoException()
    
    /**
     * Tests that {@link Manager#getName()} returns the correct value.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        manager = new Manager(name, email, phone);
        String expected = name;
        String received = manager.getName();
        assertEquals(expected, received);
    }    // testGetName()
    
    /* getEmail */
    
    /**
     * Tests that {@link Manager#getEmail()} does not throw an exception.
     */
    @Test
    public void testGetEmailNoException() {
        System.out.println("getEmail - no expcetion");
        
        manager = new Manager(name, email, phone);
        manager.getEmail();
    }    // testGetEmailNoException()
    
    /**
     * Tests that {@link Manager#getEmail()} returns the correct value.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        
        manager = new Manager(name, email, phone);
        String expected = email;
        String received = manager.getEmail();
        assertEquals(expected, received);
    }    // testGetEmail()
    
    /* getPhone */
    
    /**
     * Tests that {@link Manager#getPhone()} does not throw an exception.
     */
    @Test
    public void testGetPhoneNoException() {
        System.out.println("getPhone - no expcetion");
        
        manager = new Manager(name, email, phone);
        manager.getPhone();
    }    // testGetPhoneNoException()
    
    /**
     * Tests that {@link Manager#getPhone()} returns the correct value.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        
        manager = new Manager(name, email, phone);
        String expected = phone;
        String received = manager.getPhone();
        assertEquals(expected, received);
    }    // testGetPhone()
    
    /* getReadWritableProperties */
    
    /**
     * Tests that {@link Manager#getReadWritableProperties()} returns the
     * correct value.
     */
    @Test
    public void testGetReadWritableProperties() {
        System.out.println("getReadWritableProperties");
        
        manager = new Manager(name, email, phone);
        Map<String, String> expected = new HashMap<>();
        expected.put("name", name);
        expected.put("email", email);
        expected.put("phone", phone);
        Map<String, Object> received = manager.getReadWritableProperties();
        assertEquals(expected, received);
    }    // testGetReadWritableProperties()
    
    /**
     * Tests that the return value of {@link Manager#getReadWritableProperties()}
     * has the correct iteration order.
     */
    @Test
    public void testGetReadWritablePropertiesIterationOrder() {
        System.out.println("getReadWritableProperties - iteration order");
        
        manager = new Manager(name, email, phone);
        Map<String, Object> properties = manager.getReadWritableProperties();
        List<String> expected = Arrays.asList("name", "email", "phone");
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesIterationOrder()
    
    /* equals */
    
    /**
     * Tests that {@link Manager#equals(Object)} does not throw an exception
     * when the argument is null.
     */
    @Test
    public void testEqualsNullNoException() {
        System.out.println("equals - null, no exception");
        
        manager = new Manager(name, email, phone);
        Object obj = null;
        manager.equals(obj);
    }    // testEqualsNullNoException()
    
    /**
     * Tests that {@link Manager#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void testEqualsNull() {
        System.out.println("equals - null");
        
        manager = new Manager(name, email, phone);
        Object obj = null;
        boolean received = manager.equals(obj);
        assertFalse(received);
    }    // testEqualsNull()
    
    /**
     * Tests that {@link Manager#equals(Object)} does not throw an exception
     * when the argument is not a manager.
     */
    @Test
    public void testEqualsNotAManagerNoException() {
        System.out.println("equals - not a manager, no exception");
        
        manager = new Manager(name, email, phone);
        Object obj = 1;
        manager.equals(obj);
    }    // testEqualsNotAManagerNoException()
    
    /**
     * Tests that {@link Manager#equals(Object)} returns false when the
     * argument is not a manager.
     */
    @Test
    public void testEqualsNotAManager() {
        System.out.println("equals - not a manager");
        
        manager = new Manager(name, email, phone);
        Object obj = 1;
        boolean received = manager.equals(obj);
        assertFalse(received);
    }    // testEqualsNotAManager()
    
    /**
     * Tests that {@link Manager#equals(Object)} does not throw an exception
     * when the argument is a manager.
     */
    @Test
    public void testEqualsManagerNoException() {
        System.out.println("equals - manager, no exception");
        
        phone = "Foo";
        manager = new Manager(name, email, phone);
        phone = "Bar";
        Object obj = new Manager(name, email, phone);
        manager.equals(obj);
    }    // testEqualsManagerNoException()
    
    /**
     * Tests that {@link Manager#equals(Object)} returns false when the
     * argument has a different name than the caller.
     */
    @Test
    public void testEqualsDifferentName() {
        System.out.println("equals - manager, different name");
        
        name = "Foo";
        manager = new Manager(name, email, phone);
        name = "Bar";
        Object obj = new Manager(name, email, phone);
        boolean expected = false;
        boolean received = manager.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentName()
    
    /**
     * Tests that {@link Manager#equals(Object)} returns false when the
     * argument has a different email address than the caller.
     */
    @Test
    public void testEqualsDifferentEmail() {
        System.out.println("equals - manager, different email");
        
        email = "Foo";
        manager = new Manager(name, email, phone);
        email = "Bar";
        Object obj = new Manager(name, email, phone);
        boolean expected = false;
        boolean received = manager.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentEmail()
    
    /**
     * Tests that {@link Manager#equals(Object)} returns false when the
     * argument has a different phone number than the caller.
     */
    @Test
    public void testEqualsDifferentCanAngel() {
        System.out.println("equals - manager, different phone");
        
        phone = "Foo";
        manager = new Manager(name, email, phone);
        phone = "Bar";
        Object obj = new Manager(name, email, phone);
        boolean expected = false;
        boolean received = manager.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentCanAngel()
    
    /**
     * Tests that {@link Manager#equals(Object)} returns true when the
     * argument has the same name, email address, and "can angel" status as the
     * caller.
     */
    @Test
    public void testEqualsEquivalent() {
        System.out.println("equals - manager, equivalent");
        
        manager = new Manager(name, email, phone);
        Object obj = new Manager(name, email, phone);
        boolean expected = true;
        boolean received = manager.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsEquivalent()
    
    /**
     * Tests that {@link Manager#equals(Object)} returns true when the
     * argument is the same object as the caller.
     */
    @Test
    public void testEqualsIdentical() {
        System.out.println("equals - manager, identical");
        
        manager = new Manager(name, email, phone);
        Object obj = manager;
        boolean expected = true;
        boolean received = manager.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsIdentical()
    
    /* hashCode */
    
    /**
     * Tests that {@link Manager#hashCode()} does not throw an exception.
     */
    @Test
    public void testHashCodeNoException() {
        System.out.println("hashCode - no exception");
        
        manager = new Manager(name, email, phone);
        manager.hashCode();
    }    // testHashCodeNoException()
    
    /**
     * Tests that {@link Manager#hashCode()} returns equal values for equal
     * managers.
     */
    @Test
    public void testHashCodeEqual() {
        System.out.println("hashCode - equal managers");
        
        manager = new Manager(name, email, phone);
        Manager experimental = new Manager(name, email, phone);
        int expected = manager.hashCode();
        int received = experimental.hashCode();
        assertEquals(expected, received);
    }    // testHashCodeEqual()
    
    /* clone */
    
    /**
     * Tests that {@link Manager#clone()} does not throw an exception.
     */
    @Test
    public void testCloneNoException() {
        System.out.println("clone - no exception");
        
        manager = new Manager(name, email, phone);
        manager.clone();
    }    // testCloneNoException()
    
    /**
     * Tests that the return value of {@link Manager#clone()} is not null.
     */
    @Test
    public void testCloneNotNull() {
        System.out.println("clone - not null");
        
        manager = new Manager(name, email, phone);
        Manager received = manager.clone();
        assertNotNull(received);
    }    // testCloneNotNull()
    
    /**
     * Tests that the return value of {@link Manager#clone()} is equal to the
     * argument.
     */
    @Test
    public void testCloneEqual() {
        System.out.println("clone - equal");
        
        manager = new Manager(name, email, phone);
        Manager expected = manager;
        Manager received = manager.clone();
        assertEquals(expected, received);
    }    // testCloneEqual()
    
    /**
     * Tests that the return value of {@link Manager#clone()} is not identical
     * to the argument.
     */
    @Test
    public void testCloneIdentity() {
        System.out.println("clone - not identical");
        
        manager = new Manager(name, email, phone);
        Manager received = manager.clone();
        assertFalse(manager == received);
    }    // testCloneIdentity()
    
    /* toString */
    
    /**
     * Tests that {@link Manager#toString()} does not throw an exception.
     */
    @Test
    public void testToStringNoException() {
        System.out.println("toString - no exception");
        
        manager = new Manager(name, email, phone);
        manager.toString();
    }    // testToStringNoException()
    
    /**
     * Tests that the return value of {@link Manager#toString()} is not null.
     */
    @Test
    public void testToStringNotNull() {
        System.out.println("toString - not null");
        
        manager = new Manager(name, email, phone);
        String received = manager.toString();
        assertNotNull(received);
    }    // testToStringNotNull()
    
    /**
     * Tests that {@link Manager#toString()} returns the manager's name.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        manager = new Manager(name, email, phone);
        String expected = name;
        String received = manager.toString();
        assertEquals(expected, received);
    }    // testToString()
    
    /* getManagerFactory */
    
    /**
     * Tests that {@link Manager#getManagerFactory()} does not throw an exception.
     */
    @Test
    public void testGetManagerFactoryNoException() {
        System.out.println("getManagerFactory - no exception");
        
        Manager.getManagerFactory();
    }    // testToStringNoException()
    
    /**
     * Tests that the return value of {@link Manager#getManagerFactory()} is not
     * null.
     */
    @Test
    public void testGetManagerFactoryNotNull() {
        System.out.println("getManagerFactory - not null");
        
        ReadWritableFactory factory = Manager.getManagerFactory();
        assertNotNull(factory);
    }    // testGetManagerFactoryNotNull()

}    // ManagerTest
