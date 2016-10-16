/*
 * Copyright © 2015 Wayne Miller
 */

package iolayer;

import java.io.*;
import java.util.*;
import main.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Abstract base class for unit tests of implementations of {@link IOLayer}.
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
     * Composite read-writable used in testing.
     */
    protected static class RWContainer implements ReadWritable, Serializable {
        protected static class Factory implements ReadWritableFactory<RWContainer> {
            private Factory() {
            }    // Factory()

            @Override
            public RWContainer constructReadWritable(Map<String, Object> properties) {
                Object nameObject = properties.get("name");
                Object elementObject = properties.get("element");
                String name = (nameObject != null) ? nameObject.toString() : null;
                RWElement element = null;
                if (elementObject instanceof RWElement) {
                    element = (RWElement)elementObject;
                } else if (elementObject instanceof Map) {
                    ReadWritableFactory<RWElement> factory = RWElement.getFactory();
                    element = factory.constructReadWritable((Map<String, Object>)elementObject);
                }    // else if
                if ((name == null) || (element == null)) {
                    return null;
                }    // if
                return new RWContainer(name, element);
            }    // constructReadWritable()
        }    // Factory()

        public static Factory getFactory() {
            return new Factory();
        }    // getFactory()
        private final String name;
        private final RWElement element;
        
        public RWContainer(String name, RWElement element) {
            assert (name != null);
            assert (element != null);
            this.name = name;
            this.element = element;
        }    // RWElement()
        
        @Override
        public Map<String, Object> getReadWritableProperties() {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("name", name);
            map.put("element", element);
            return map;
        }    // getReadWritableProperties()
        
        @Override
        public Factory getReadWritableFactory() {
            return RWContainer.getFactory();
        }    // getReadWritableFactory()
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof RWContainer) {
                RWContainer rhs = (RWContainer)o;
                return name.equals(rhs.name) && element.equals(rhs.element);
            }    // if
            return false;
        }    // equals()
    }    // RWContainer
    
    /**
     * Returns the I/O layer being tested.
     * 
     * @return the I/O layer being tested
     */
    protected abstract IOLayer getIOLayer();
    
    /*
     * Unit tests.
     */

    /**
     * Tests that {@link IOLayer#readAll(InputStream, ReadWritableFactory)} throws a
     * {@link NullPointerException} when input is null.
     */
    @Test(expected = NullPointerException.class)
    public void testReadAllInputNull() throws IOException {
        System.out.println("(IOLayer) readAll - input is null");
        
        IOLayer ioLayer = getIOLayer();
        InputStream input = null;
        ReadWritableFactory<RWElement> factory = RWElement.getFactory();
        ioLayer.readAll(input, factory);
    }    // testReadAllInputNull()

    /**
     * Tests that {@link IOLayer#readAll(InputStream, ReadWritableFactory)} throws a
     * {@link NullPointerException} when factory is null.
     */
    @Test(expected = NullPointerException.class)
    public void testReadAllFactoryNull() throws IOException {
        System.out.println("(IOLayer) readAll - factory is null");
        
        List<RWElement> readWritables = Arrays.asList(new RWElement("foo", 1), new RWElement("bar", 2), new RWElement("baz", 3));
        IOLayer ioLayer = getIOLayer();
        byte[] buffer = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ioLayer.writeAll(output, readWritables);
            buffer = output.toByteArray();
        }    // try
        assert (buffer != null);

        try (InputStream input = new ByteArrayInputStream(buffer)) {
            ReadWritableFactory<RWElement> factory = null;
            ioLayer.readAll(input, factory);
        }    // try
    }    // testReadAllFactoryNull()

    /**
     * Tests that {@link IOLayer#readAll(InputStream, ReadWritableFactory)} does not
     * throw an exception when neither parameter is null.
     */
    @Test
    public void testReadAllNoException() throws IOException {
        System.out.println("(IOLayer) readAll - no exception");
        
        List<RWElement> readWritables = Arrays.asList(new RWElement("foo", 1), new RWElement("bar", 2), new RWElement("baz", 3));
        IOLayer ioLayer = getIOLayer();
        byte[] buffer = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ioLayer.writeAll(output, readWritables);
            buffer = output.toByteArray();
        }    // try
        assert (buffer != null);

        try (InputStream input = new ByteArrayInputStream(buffer)) {
            ReadWritableFactory<RWElement> factory = RWElement.getFactory();
            ioLayer.readAll(input, factory);
        }    // try
    }    // testReadAllNoException()

    /**
     * Tests that {@link IOLayer#readAll(InputStream, ReadWritableFactory)} inserts
     * a null into its return value wherever it is unable to create a
     * read-writable.
     */
    @Test
    public void testReadAllInsertsNull() throws IOException {
        System.out.println("(IOLayer) readAll - inserts null when unable to create single read-writable");
        
        List<ReadWritable> readWritables = Arrays.<ReadWritable>asList(
                new RWElement("foo", 1),
                new RWContainer("two", new RWElement("bar", 2)),
                new RWElement("baz", 3)
        );    // readWritables
        
        IOLayer ioLayer = getIOLayer();
        byte[] buffer = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ioLayer.writeAll(output, readWritables);
            buffer = output.toByteArray();
        }    // try
        assert (buffer != null);

        List<ReadWritable> expected = new ArrayList<>(readWritables);
        expected.set(1, null);
        List<RWElement> received = null;
        try (InputStream input = new ByteArrayInputStream(buffer)) {
            ReadWritableFactory<RWElement> factory = RWElement.getFactory();
            received = ioLayer.readAll(input, factory);
        }    // try
        
        assertEquals(expected, received);
    }    // testReadAllInsertsNull()

    /**
     * Tests that {@link IOLayer#readAll(InputStream, ReadWritableFactory)} returns
     * null when it is unable to create any read-writables.
     */
    @Test
    public void testReadAllReturnsNull() throws IOException {
        System.out.println("(IOLayer) readAll - unable to construct any elements, returns null");

        byte[] gibberish = null;
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            output.writeObject(this.getClass());
            gibberish = buffer.toByteArray();
        }    // try
        assert (gibberish != null);

        List<RWElement> expected = null;
        List<RWElement> received = Arrays.asList(new RWElement("foo", 1));
        IOLayer ioLayer = getIOLayer();
        try (InputStream input = new ByteArrayInputStream(gibberish)) {
            ReadWritableFactory<RWElement> factory = RWElement.getFactory();
            received = ioLayer.readAll(input, factory);
        }    // try
        assertEquals(expected, received);
    }    // testReadAllReturnsNull()

    /**
     * Tests that {@link IOLayer#writeAll(OutputStream, List)} throws a
     * {@link NullPointerException} when output is null.
     */
    @Test(expected = NullPointerException.class)
    public void testWriteAllOutputNull() throws IOException {
        System.out.println("(IOLayer) writeAll - output is null");
        
        List<RWElement> readWritables = Arrays.asList(new RWElement("foo", 1), new RWElement("bar", 2), new RWElement("baz", 3));
        IOLayer ioLayer = getIOLayer();
        OutputStream output = null;
        ioLayer.writeAll(output, readWritables);
    }    // testWriteAllOutputNull()

    /**
     * Tests that {@link IOLayer#writeAll(OutputStream, List)} throws a
     * {@link NullPointerException} when readWritables is null.
     */
    @Test(expected = NullPointerException.class)
    public void testWriteAllReadWritablesNull() throws IOException {
        System.out.println("(IOLayer) writeAll - readwritables is null");
        
        List<RWElement> readWritables = null;
        IOLayer ioLayer = getIOLayer();
        try (OutputStream output = new ByteArrayOutputStream()) {
            ioLayer.writeAll(output, readWritables);
        }    // try
    }    // testWriteAllReadWritablesNull()

    /**
     * Tests that {@link IOLayer#writeAll(OutputStream, List)} throws a
     * {@link NullPointerException} when readWritables contains a null element.
     */
    @Test(expected = NullPointerException.class)
    public void testWriteAllReadWritablesContainsNull() throws IOException {
        System.out.println("(IOLayer) writeAll - readwritables contains null");
        
        List<RWElement> readWritables = Arrays.asList(new RWElement("foo", 1), null, new RWElement("baz", 3));
        IOLayer ioLayer = getIOLayer();
        try (OutputStream output = new ByteArrayOutputStream()) {
            ioLayer.writeAll(output, readWritables);
        }    // try
    }    // writeAll()

    /**
     * Tests that {@link IOLayer#writeAll(OutputStream, List)} does not throw an
     * exception when neither parameter is null.
     */
    @Test
    public void testWriteAllNoException() throws IOException {
        System.out.println("(IOLayer) writeAll - no exception");
        
        List<RWElement> readWritables = Arrays.asList(new RWElement("foo", 1), new RWElement("bar", 2), new RWElement("baz", 3));
        IOLayer ioLayer = getIOLayer();
        try (OutputStream output = new ByteArrayOutputStream()) {
            ioLayer.writeAll(output, readWritables);
        }    // try
    }    // testWriteAllOutputNull()
    
    /**
     * Tests that {@link IOLayer#writeAll(OutputStream, List)} and
     * {@link IOLayer#readAll(InputStream, ReadWritableFactory)} are reflexive.
     */
    @Test
    public void testWriteAllReadAll() throws IOException {
        System.out.println("(IOLayer) writeAll/readAll - reflexivity");
        
        List<RWElement> readWritables = Arrays.asList(new RWElement("foo", 1), new RWElement("bar", 2), new RWElement("baz", 3));
        IOLayer ioLayer = getIOLayer();
        byte[] buffer = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ioLayer.writeAll(output, readWritables);
            buffer = output.toByteArray();
        }    // try
        assert (buffer != null);

        List<RWElement> expected = readWritables;
        List<RWElement> received = null;
        try (InputStream input = new ByteArrayInputStream(buffer)) {
            ReadWritableFactory<RWElement> factory = RWElement.getFactory();
            received = ioLayer.readAll(input, factory);
        }    // try
        assert (received != null);
        
        assertEquals(expected, received);
    }    // testReadAllFactoryNull()

    /**
     * Tests that {@link IOLayer#writeAll(OutputStream, List)} and
     * {@link IOLayer#readAll(InputStream, ReadWritableFactory)} are reflexive for
     * composite read-writables.
     */
    @Test
    public void testWriteAllReadAllComposite() throws IOException {
        System.out.println("(IOLayer) writeAll/readAll - reflexivity, composite elements");
        
        List<RWContainer> readWritables = Arrays.asList(
                new RWContainer("one", new RWElement("foo", 1)),
                new RWContainer("two", new RWElement("bar", 2)),
                new RWContainer("one", new RWElement("baz", 3))
        );    // readWritables
        IOLayer ioLayer = getIOLayer();
        byte[] buffer = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ioLayer.writeAll(output, readWritables);
            buffer = output.toByteArray();
        }    // try
        assert (buffer != null);

        List<RWContainer> expected = readWritables;
        List<RWContainer> received = null;
        try (InputStream input = new ByteArrayInputStream(buffer)) {
            ReadWritableFactory<RWContainer> factory = RWContainer.getFactory();
            received = ioLayer.readAll(input, factory);
        }    // try
        assert (received != null);
        
        assertEquals(expected, received);
    }    // testWriteAllReadAllComposite()
    
}    // IOLayerTest
