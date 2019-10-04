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

package io.github.waynem77.bscmail.iolayer;

import io.github.waynem77.bscmail.ReadWritable;
import io.github.waynem77.bscmail.ReadWritableFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
     * File used to store XML data for tests.
     */
    private File tempfile;

    /**
     * The initial data that should be stored in the I/O layer.
     */
    private final List<RWElement> seedList = Arrays.asList(
            new RWElement("foo", 1),
            new RWElement("bar", 2),
            new RWElement("baz", 3));

    /**
     * Returns the serializing I/O layer being tested.
     * 
     * @return the serializing I/O layer being tested
     * @throws IOException if an I/O exception occurs while constructing the
     * I/O layer
     */
    @Override
    protected SerializingIOLayer<RWElement> getIOLayer() throws IOException {
        return new SerializingIOLayer<>(tempfile.getCanonicalPath(), RWElement.getFactory());
    }    // getIOLayer()

    /**
     * Returns true, indicating that the data persists beyond the lifetime of
     * the serializing I/O layer being tested.
     *
     * @return true
     */
    @Override
    protected boolean dataIsPersistent() {
        return true;
    }    // dataIsPersistemt()
    
    /**
     * Creates a temporary file, marks it to be deleted upon application exit,
     * and places a reference to it in {@link tempfile}.
     */
    @Before
    public void setUpFileStorage() throws IOException {
        tempfile = File.createTempFile("bscmailtest", null);
        try (FileOutputStream fileOutputStream = new FileOutputStream(tempfile.getCanonicalPath());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(seedList);
            objectOutputStream.flush();
        }
    }    // setUpFileStorage()

    /**
     * Sets tempfile to null.
     */
    @After
    public void removeFileStorage() {
        tempfile.delete();
        tempfile = null;
    }    // removeFileStorage()


    /*
     * Unit tests
     */

    /**
     * Tests that
     * {@link SerializingIOLayer#SerializingIOLayer(java.lang.String, main.ReadWritableFactory)}
     * throws a {@link NullPointerException} when pathname is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenPathnameIsNull() {
        String pathname = null;
        ReadWritableFactory<RWElement> factory = RWElement.getFactory();

        IOLayer<RWElement> ioLayer = new SerializingIOLayer<>(pathname, factory);
    }    // constructorThrowsExceptionWhenPathnameIsNull()

    /**
     * Tests that
     * {@link SerializingIOLayer#SerializingIOLayer(java.lang.String, main.ReadWritableFactory)}
     * throws a {@link NullPointerException} when factory is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenFactoryIsNull() throws IOException {
        String pathname = tempfile.getCanonicalPath();
        ReadWritableFactory<RWElement> factory = null;

        IOLayer<RWElement> ioLayer = new SerializingIOLayer<>(pathname, factory);
    }    // constructorThrowsExceptionWhenFactoryIsNull()

    /**
     * Tests that
     * {@link SerializingIOLayer#SerializingIOLayer(java.lang.String, main.ReadWritableFactory)}
     * does not throw an exception when no parameter is null.
     */
    @Test
    public void constructorDoesNotThrowAnExceptionWhenNoParameterIsNull() throws IOException {
        String pathname = tempfile.getCanonicalPath();
        ReadWritableFactory<RWElement> factory = RWElement.getFactory();

        IOLayer<RWElement> ioLayer = new SerializingIOLayer<>(pathname, factory);
    }    // constructorDoesNotThrowAnExceptionWhenNoParameterIsNull()

    /**
     * Tests that {@link SerializingIOLayer} correctly processes the data file.
     */
    public void layerCorrectlyProcessesXML() throws IOException {
        String pathname = tempfile.getCanonicalPath();
        ReadWritableFactory<RWElement> factory = RWElement.getFactory();
        IOLayer<RWElement> ioLayer = new SerializingIOLayer<>(pathname, factory);

        List<RWElement> received = ioLayer.getAll();

        List<RWElement> expected = seedList;
        assertEquals(expected, received);
    }    // layerCorrectlyProcessesXML()

    /**
     * Tests that {@link SerializingIOLayer#setAll(java.util.List)} correctly
     * serializes its objects.
     */
    @Test
    public void setAllSerializes() throws IOException {
        String pathname = tempfile.getCanonicalPath();
        ReadWritableFactory<RWElement> factory = RWElement.getFactory();
        IOLayer<RWElement> ioLayer = new SerializingIOLayer<>(pathname, factory);
        List<RWElement> list = Arrays.asList(new RWElement("aaa", 10), new RWElement("bbb", 100));

        ioLayer.setAll(list);

        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(buffer)) {
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
            byte[] expected = buffer.toByteArray();
            byte[] received = Files.readAllBytes(Paths.get(pathname));
            Assert.assertArrayEquals(expected, received);
        }    // try
    }    // setAllSerializes()
}    // SerializingIOLayerTest
