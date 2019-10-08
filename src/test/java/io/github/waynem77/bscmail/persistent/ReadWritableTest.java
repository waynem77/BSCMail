/*
 * Copyright © 2014-2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.persistent;

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
    public void getReadWritablePropertiesDoesNotThrowException() {
        ReadWritable readWritable = getReadWritable();

        readWritable.getReadWritableProperties();
    }    // getReadWritablePropertiesDoesNotThrowException()

    /**
     * Tests that {@link ReadWritable#getReadWritableProperties()} does not
     * return null.
     */
    @Test
    public void getReadWritablePropertiesDoesNotReturnNull() {
        ReadWritable readWritable = getReadWritable();

        Map<String, Object> properties = readWritable.getReadWritableProperties();

        assertNotNull(properties);
    }    // getReadWritablePropertiesDoesNotReturnNull()

    /**
     * Tests that {@link ReadWritable#getReadWritableProperties()} does not
     * contain any null keys.
     */
    @Test
    public void getReadWritablePropertiesDoesNotReturnMapWithNullKeys() {
        ReadWritable readWritable = getReadWritable();

        Map<String, Object> properties = readWritable.getReadWritableProperties();

        assertFalse(properties.containsKey(null));
    }    // getReadWritablePropertiesDoesNotReturnMapWithNullKeys()

    /**
     * Tests that {@link ReadWritable#getReadWritableFactory()} does not throw
     * an exception.
     */
    @Test
    public void getReadWritableFactoryDoesNotThrowException() {
        ReadWritable readWritable = getReadWritable();

        readWritable.getReadWritableFactory();
    }    // getReadWritableFactoryDoesNotThrowException()

    /**
     * Tests that {@link ReadWritable#getReadWritableFactory()} does not return
     * null.
     */
    @Test
    public void getReadWritableFactoryDoesNotReturnNull() {
        ReadWritable readWritable = getReadWritable();

        ReadWritableFactory received = readWritable.getReadWritableFactory();

        assertNotNull(received);
    }    // getReadWritableFactoryDoesNotReturnNull()

}    // ReadWritableTest
