/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package main;

import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Abstract base class for unit tests for {@link ReadWritable} implementations.
 * 
 * @author Wayne Miller
 */
@Ignore
public abstract class ReadWritableTest {
    
    /*
     * Helper classes and methods.
     */
    
    /**
     * Returns the read-writable to be tested.
     * 
     * @return the read-writable to be tested
     */
    protected abstract ReadWritable getReadWritable();

    /*
     * Unit tests
     */
    
    /**
     * Tests that {@link ReadWritable#getReadWritableProperties()} does not
     * throw an exception.
     */
    @Test
    public void testGetReadWritablePropertiesNoException() {
        System.out.println("(ReadWritable) getReadWritableProperties - no exception");
        
        ReadWritable readWritable = getReadWritable();
        readWritable.getReadWritableProperties();
    }    // testGetReadWritablePropertiesNoException()
    
    /**
     * Tests that {@link ReadWritable#getReadWritableProperties()} does not
     * return null.
     */
    @Test
    public void testGetReadWritablePropertiesDoesNotReturnNull() {
        System.out.println("(ReadWritable) getReadWritableProperties - does not return null");
        
        ReadWritable readWritable = getReadWritable();
        Map<String, Object> properties = readWritable.getReadWritableProperties();
        assertNotNull(properties);
    }    // testGetReadWritablePropertiesDoesNotReturnNull()
    
    /**
     * Tests that {@link ReadWritable#getReadWritableProperties()} does not
     * contain any null keys.
     */
    @Test
    public void testGetReadWritablePropertiesDoesNotHaveNullKey() {
        System.out.println("(ReadWritable) getReadWritableProperties - does not contain a null key");
        
        ReadWritable readWritable = getReadWritable();
        Map<String, Object> properties = readWritable.getReadWritableProperties();
        assertFalse(properties.containsKey(null));
    }    // testGetReadWritablePropertiesDoesNotHaveNullKey()
    
    /**
     * Tests that {@link ReadWritable#getReadWritableFactory()} does not throw
     * an exception.
     */
    @Test
    public void testGetReadWritableFactoryNoException() {
        System.out.println("(ReadWritable) getReadWritableFactory - no exception");
        
        ReadWritable readWritable = getReadWritable();
        readWritable.getReadWritableFactory();
    }    // testGetReadWritableFactoryNoException()
    
    /**
     * Tests that {@link ReadWritable#getReadWritableFactory()} does not return
     * null.
     */
    @Test
    public void testGetReadWritableFactoryNotNull() {
        System.out.println("(ReadWritable) getReadWritableFactory - does not return null");
        
        ReadWritable readWritable = getReadWritable();
        ReadWritableFactory received = readWritable.getReadWritableFactory();
        assertNotNull(received);
    }    // testGetReadWritableFactoryNotNull()
    
}    // ReadWritableTest
