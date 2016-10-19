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

import java.io.*;
import java.util.*;
import java.util.List;
import main.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link SerializingIOLayer}.
 * 
 * @author Wayne Miller
 */
public class SerializingIOLayerTest extends IOLayerTest {
    
    private static class NotSerializable implements ReadWritable {
        protected static class Factory implements ReadWritableFactory<NotSerializable> {
            private Factory() {
            }    // Factory()

            @Override
            public NotSerializable constructReadWritable(Map<String, Object> properties) {
                String name = (String)properties.get("name");
                Integer value = (Integer)properties.get("value");
                return new NotSerializable(name, value);
            }    // constructReadWritable()
        }    // Factory()

        public static Factory getFactory() {
            return new Factory();
        }    // getFactory()
        
        private final String name;
        private final Integer value;

        public NotSerializable(String name, Integer value) {
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
            return NotSerializable.getFactory();
        }    // getReadWritableFactory()
   }    // NotSerializable
    
    /**
     * Returns the I/O layer being tested.
     * 
     * @return the I/O layer being tested
     */
    @Override
    protected SerializingIOLayer getIOLayer() {
        return new SerializingIOLayer();
    }    // getIOLayer()
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("SerializingIOLayer");
        System.out.println("==================");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass(

    /*
     * Unit tests
     */
    
    /**
     * Tests that {@link SerializingIOLayer#SerializingIOLayer()} does not throw
     * an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        IOLayer ioLayer = new SerializingIOLayer();
    }    // testConstructorNoException()
    
    
    

    /**
     * Tests that {@link SerializingIOLayer#writeAll(Writer, List)} correctly
     * serializes its argument.
     */
    @Test
    public void testWriteAll() throws IOException {
        System.out.println("writeAll");
        
        List<RWElement> readWritables = Arrays.asList(new RWElement("foo", 1), new RWElement("bar", 2), new RWElement("baz", 3));
        
        byte[] expected = null;
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            output.writeObject(readWritables);
            expected = buffer.toByteArray();
        }    // try
        assert (expected != null);

        byte[] received = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            IOLayer ioLayer = getIOLayer();
            ioLayer.writeAll(output, readWritables);
            received = output.toByteArray();
        }    // try
        
        assertArrayEquals(expected, received);
    }    // testWriteAll()

    /**
     * Tests that {@link SerializingIOLayer#writeAll(Writer, List)} throws a
     * {@link NotSerializableException} if its argument is not serializable.
     * serializes its argument.
     */
    @Test(expected = NotSerializableException.class)
    public void testWriteAllNotSerializable() throws IOException {
        System.out.println("writeAll");
 
        List<NotSerializable> readWritables = Arrays.asList(new NotSerializable("foo", 1), new NotSerializable("bar", 2), new NotSerializable("baz", 3));
        try (OutputStream output = new ByteArrayOutputStream()) {
            IOLayer ioLayer = getIOLayer();
            ioLayer.writeAll(output, readWritables);
        }    // try
    }    // testWriteAllNotSerializable()
    
}    // SerializingIOLayerTest
