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

package iolayer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import main.ReadWritable;
import main.ReadWritableFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

/**
 * Abstract base class for unit tests of implementations of {@link IOLayer}.
 *
 * @author Wayne Miller
 */
@Ignore
public abstract class IOLayerTest {
    
    /*
     * Protected helper methods and classes.
     */

    /**
     * Read-writable used in testing.
     */
    protected static class RWElement implements ReadWritable, Serializable {
        protected static class Factory implements ReadWritableFactory<RWElement> {
            private Factory() {
            }    // Factory()

            @Override
            public RWElement constructReadWritable(Map<String, Object> properties) {
                Object nameObject = properties.get("name");
                Object valueObject = properties.get("value");
                String name = (nameObject != null) ? nameObject.toString() : null;
                Integer value = null;
                if (valueObject instanceof Integer) {
                    value = (Integer)valueObject;
                } else if (valueObject instanceof String) {
                    value = Integer.valueOf((String)valueObject);
                }    // else if
                if ((name == null) || (value == null)) {
                    return null;
                }    // if
                return new RWElement(name, value);
            }    // constructReadWritable()
        }    // Factory()

        public static Factory getFactory() {
            return new Factory();
        }    // getFactory()
        
        private final String name;
        private final Integer value;

        public RWElement(String name, Integer value) {
            assert (name != null);
            assert (value != null);
            this.name = name;
            this.value = value;
        }    // RWElement()

        @Override
        public Map<String, Object> getReadWritableProperties() {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("name", name);
            map.put("value", value);
            return map;
        }    // getReadWritableProperties()
        
        @Override
        public Factory getReadWritableFactory() {
            return RWElement.getFactory();
        }    // getReadWritableFactory()
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof RWElement) {
                RWElement rhs = (RWElement)o;
                return name.equals(rhs.name) && value.equals(rhs.value);
            }    // if
            return false;
        }    // equals()
    }    // RWElement
    
    /**
     * Returns the I/O layer being tested.
     * 
     * @return the I/O layer being tested
     * @throws IOException if an I/O exception occurs while constructing the
     * I/O layer
     */
    protected abstract IOLayer<RWElement> getIOLayer() throws IOException;

    /**
     * Returns true if the data persists beyond the lifetime of the I/O layer
     * being tested, or false if it does not.
     *
     * @return true if the data persists beyond the lifetime of the I/O layer
     * being tested; false otherwise
     */
    protected abstract boolean dataIsPersistent();

    /*
     * Unit tests.
     */

    /**
     * Tests that {@link IOLayer#getAll()} does not throw an exception under
     * normal circumstances.
     */
    @Test
    public void getAllDoesNotThrowException() throws IOException {
        IOLayer<RWElement> ioLayer = getIOLayer();

        ioLayer.getAll();
    }    // getAllDoesNotThrowException()

    /**
     * Tests that {@link IOLayer#getAll()} does not return null.
     */
    @Test
    public void getAllDoesNotReturnNull() throws IOException {
        IOLayer<RWElement> ioLayer = getIOLayer();

        List<RWElement> list = ioLayer.getAll();

        assertNotNull(list);
    }    // getAllDoesNotReturnNull()

    /**
     * Tests that {@link IOLayer#setAll(java.util.List)} throws a
     * {@link NullPointerException} when list is null.
     */
    @Test(expected = NullPointerException.class)
    public void setAllThrowsExceptionWhenListIsNull() throws IOException {
        IOLayer<RWElement> ioLayer = getIOLayer();
        List<RWElement> list = null;

        ioLayer.setAll(list);
    }    // setAllThrowsExceptionWhenListIsNull()

    /**
     * Tests that {@link IOLayer#setAll(java.util.List)} throws a
     * {@link NullPointerException} when list contains null.
     */
    @Test(expected = NullPointerException.class)
    public void setAllThrowsExceptionWhenListContainsNull() throws IOException {
        IOLayer<RWElement> ioLayer = getIOLayer();
        List<RWElement> list = Arrays.asList(new RWElement("one", 1), null, new RWElement("two", 2));

        ioLayer.setAll(list);
    }    // setAllThrowsExceptionWhenListContainsNull()

    /**
     * Tests that {@link IOLayer#setAll(java.util.List)} does not throw an
     * exception when list is not null nor contains null.
     */
    @Test
    public void setAllDoesNotThrowExceptionWhenListIsValid() throws IOException {
        IOLayer<RWElement> ioLayer = getIOLayer();
        List<RWElement> list = Arrays.asList(new RWElement("one", 1), new RWElement("two", 2));

        ioLayer.setAll(list);
    }    // setAllDoesNotThrowExceptionWhenListIsValid()

    /**
     * Tests that {@link IOLayer#getAll()} and
     * {@link IOLayer#setAll(java.util.List)} work properly.
     */
    @Test
    public void getAllAndSetAllBothWork() throws IOException {
        IOLayer<RWElement> ioLayer = getIOLayer();
        List<RWElement> list = Arrays.asList(new RWElement("one", 1), new RWElement("two", 2));

        ioLayer.setAll(list);

        List<RWElement> expected = list;
        List<RWElement> received = ioLayer.getAll();
        assertEquals(expected, received);
    }    // getAllAndSetAllBothWork()

    /**
     * Tests that data persists properly.
     */
    @Test
    public void dataPersists() throws IOException {
        assumeTrue(dataIsPersistent());

        IOLayer<RWElement> ioLayer1 = getIOLayer();
        List<RWElement> list = Arrays.asList(new RWElement("one", 1), new RWElement("two", 2));
        ioLayer1.setAll(list);

        IOLayer<RWElement> ioLayer2 = getIOLayer();

        List<RWElement> expected = list;
        List<RWElement> received = ioLayer2.getAll();
        assertEquals(expected, received);
    }    // dataPersists()
}    // IOLayerTest
