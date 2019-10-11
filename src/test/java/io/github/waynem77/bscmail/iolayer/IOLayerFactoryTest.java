/*
 * Copyright © 2019 its authors.  See the file "AUTHORS" for details.
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

import io.github.waynem77.bscmail.persistent.ReadWritable;
import io.github.waynem77.bscmail.persistent.Shift;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link IOLayerFactory}.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class IOLayerFactoryTest {

    /**
     * Tests that {@link IOLayerFactory#createIOLayer(Class, Class, Object[])}
     * throws a NullPointerException when ioLayerClass is null.
     */
    @Test(expected = NullPointerException.class)
    public void createIOLayerThrowsExceptionWhenIoLayerClassIsNull() {
        IOLayerFactory ioLayerFactory = new IOLayerFactory();
        Class<? extends IOLayer> ioLayerClass = null;
        Class<? extends ReadWritable> readWritableClass = io.github.waynem77.bscmail.persistent.Shift.class;
        Object[] arguments = new Object[0];

        ioLayerFactory.createIOLayer(ioLayerClass, readWritableClass, arguments);
    }    // createIOLayerThrowsExceptionWhenIoLayerClassIsNull

    /**
     * Tests that {@link IOLayerFactory#createIOLayer(Class, Class, Object[])}
     * throws a NullPointerException when readWritableClass is null.
     */
    @Test(expected = NullPointerException.class)
    public void createIOLayerThrowsExceptionWhenReadWritableClassIsNull() {
        IOLayerFactory ioLayerFactory = new IOLayerFactory();
        Class<? extends IOLayer> ioLayerClass = XMLIOLayer.class;
        Class<? extends ReadWritable> readWritableClass = null;
        Object[] arguments = new Object[0];

        ioLayerFactory.createIOLayer(ioLayerClass, readWritableClass, arguments);
    }    // createIOLayerThrowsExceptionWhenReadWritableClassIsNull

    /**
     * Tests that {@link IOLayerFactory#createIOLayer(Class, Class, Object[])}
     * throws a NullPointerException when ioLayerClass is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIOLayerThrowsExceptionWhenIoLayerClassIsUnknown() {
        class FactoryNotImplemented implements IOLayer {
            @Override public List getAll() { return null; }
            @Override public void setAll(List list) { }
        }    // FactoryNotImplemented
        IOLayerFactory ioLayerFactory = new IOLayerFactory();
        Class<? extends IOLayer> ioLayerClass = FactoryNotImplemented.class;
        Class<? extends ReadWritable> readWritableClass = io.github.waynem77.bscmail.persistent.Shift.class;
        Object[] arguments = new Object[0];

        ioLayerFactory.createIOLayer(ioLayerClass, readWritableClass, arguments);
    }    // createIOLayerThrowsExceptionWhenIoLayerClassIsUnknown

    /* tests with SerializingIOLayer */

    /**
     * Tests that {@link IOLayerFactory#createIOLayer(Class, Class, Object[])}
     * works with {@link SerializingIOLayer}.
     */
    @Test
    public void createIOLayerWorksWithSerializingIoLayer() {
        IOLayerFactory ioLayerFactory = new IOLayerFactory();
        Class<? extends IOLayer> ioLayerClass = SerializingIOLayer.class;
        Class<Shift> readWritableClass = Shift.class;
        Object[] arguments = new Object[]{ "foo" };

        IOLayer<Shift> received = ioLayerFactory.createIOLayer(ioLayerClass, readWritableClass, arguments);

        assertNotNull(received);
    }    // createIOLayerWorksWithSerializingIoLayer()

    /**
     * Tests that {@link IOLayerFactory#createIOLayer(Class, Class, Object[])}
     * throws an IllegalArgumentException with {@link SerializingIOLayer} when
     * there are too few arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIOLayerThrowsExceptionWithSerializingIoLayerWhenThereAreTooFewArguments() {
        IOLayerFactory ioLayerFactory = new IOLayerFactory();
        Class<? extends IOLayer> ioLayerClass = SerializingIOLayer.class;
        Class<? extends ReadWritable> readWritableClass = io.github.waynem77.bscmail.persistent.Shift.class;
        Object[] arguments = new Object[0];

        ioLayerFactory.createIOLayer(ioLayerClass, readWritableClass, arguments);
    }    // createIOLayerThrowsExceptionWithSerializingIoLayerWhenThereAreTooFewArguments()

    /**
     * Tests that {@link IOLayerFactory#createIOLayer(Class, Class, Object[])}
     * throws an IllegalArgumentException with {@link SerializingIOLayer} when
     * there are too many arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIOLayerThrowsExceptionWithSerializingIoLayerWhenThereAreTooManyArguments() {
        IOLayerFactory ioLayerFactory = new IOLayerFactory();
        Class<? extends IOLayer> ioLayerClass = SerializingIOLayer.class;
        Class<? extends ReadWritable> readWritableClass = io.github.waynem77.bscmail.persistent.Shift.class;
        Object[] arguments = new Object[]{ "foo", "bar" };

        ioLayerFactory.createIOLayer(ioLayerClass, readWritableClass, arguments);
    }    // createIOLayerThrowsExceptionWithSerializingIoLayerWhenThereAreTooManyArguments()

    /* tests with XMLIOLayer */

    /**
     * Tests that {@link IOLayerFactory#createIOLayer(Class, Class, Object[])}
     * works with {@link XMLIOLayer}.
     */
    @Test
    public void createIOLayerWorksWithXMLIoLayer() {
        IOLayerFactory ioLayerFactory = new IOLayerFactory();
        Class<? extends IOLayer> ioLayerClass = XMLIOLayer.class;
        Class<Shift> readWritableClass = Shift.class;
        Object[] arguments = new Object[]{ "foo" };

        IOLayer<Shift> received = ioLayerFactory.createIOLayer(ioLayerClass, readWritableClass, arguments);

        assertNotNull(received);
    }    // createIOLayerWorksWithXMLIoLayer()

    /**
     * Tests that {@link IOLayerFactory#createIOLayer(Class, Class, Object[])}
     * throws an IllegalArgumentException with {@link XMLIOLayer} when
     * there are too few arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIOLayerThrowsExceptionWithXMLIoLayerWhenThereAreTooFewArguments() {
        IOLayerFactory ioLayerFactory = new IOLayerFactory();
        Class<? extends IOLayer> ioLayerClass = XMLIOLayer.class;
        Class<? extends ReadWritable> readWritableClass = io.github.waynem77.bscmail.persistent.Shift.class;
        Object[] arguments = new Object[0];

        ioLayerFactory.createIOLayer(ioLayerClass, readWritableClass, arguments);
    }    // createIOLayerThrowsExceptionWithXMLIoLayerWhenThereAreTooFewArguments()

    /**
     * Tests that {@link IOLayerFactory#createIOLayer(Class, Class, Object[])}
     * throws an IllegalArgumentException with {@link XMLIOLayer} when
     * there are too many arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIOLayerThrowsExceptionWithXMLIoLayerWhenThereAreTooManyArguments() {
        IOLayerFactory ioLayerFactory = new IOLayerFactory();
        Class<? extends IOLayer> ioLayerClass = XMLIOLayer.class;
        Class<? extends ReadWritable> readWritableClass = io.github.waynem77.bscmail.persistent.Shift.class;
        Object[] arguments = new Object[]{ "foo", "bar" };

        ioLayerFactory.createIOLayer(ioLayerClass, readWritableClass, arguments);
    }    // createIOLayerThrowsExceptionWithXMLIoLayerWhenThereAreTooManyArguments()

}
