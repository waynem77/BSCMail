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
