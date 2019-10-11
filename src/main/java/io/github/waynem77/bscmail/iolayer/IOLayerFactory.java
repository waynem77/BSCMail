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
import io.github.waynem77.bscmail.persistent.ReadWritableFactory;
import io.github.waynem77.bscmail.persistent.ReadWritableFactoryFactory;
import java.io.Serializable;

/**
 * Factory for {@link IOLayer} objects.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 4.0
 */
public class IOLayerFactory {

    /**
     * Creates an {@link IOLayer} implementation of the specified class
     * parameterized for the specified {@link ReadWritable} implementation using
     * the given arguments. The arguments depend on the type of IOLayer being
     * created.
     *
     * <br>
     *
     * <table style="border: 1px solid black">
     * <caption>{@code arguments} Specifications for IOLayer Implementations</caption>
     * <tr>
     * <th>SerializingIOLayer</th>
     * <td>one element, a string containing the pathname of the output file</td>
     * </tr>
     * <tr>
     * <th>XMLIOLayer</th>
     * <td>one element, a string containing the pathname of the output file</td>
     * </tr>
     * </table>
     *
     * @param <T> the type of the ReadWritable implementation
     * @param ioLayerClass the IOLayer implementation class; may not be null
     * @param readWritableClass the ReadWritable implementation class; may not
     * be null
     * @param arguments the IOLayer constructor arguments
     * @return an IOLayer
     * @throws NullPointerException if ioLayerClass or readWritableClass is null
     * @throws IllegalArgumentException if arguments is invalid for the type of
     * IOLayer being created, or if a factory has not been implemented for
     * ioLayerClass
     */
    public <T extends ReadWritable> IOLayer<T> createIOLayer(Class<? extends IOLayer> ioLayerClass, Class<T> readWritableClass, Object[] arguments) {
        if (ioLayerClass == null) {
            throw new NullPointerException("ioLayerClass may not be null");
        }  // if
        if (readWritableClass == null) {
            throw new NullPointerException("readWritableClass may not be null");
        }  // if

        if (arguments == null) {
            arguments = new Object[0];
        }    // if

        ReadWritableFactoryFactory rwFactoryFactory = new ReadWritableFactoryFactory();
        ReadWritableFactory rwFactory = rwFactoryFactory.getReadWritableFactory(readWritableClass);

        if (ioLayerClass.equals(SerializingIOLayer.class)) {
            return createSerializingIOLayer(rwFactory, arguments);
        }    // if
        if (ioLayerClass.equals(XMLIOLayer.class)) {
            return createXMLIOLayer(rwFactory, arguments);
        }    // if
        throw new IllegalArgumentException("Method not implemented for " + ioLayerClass);
    }    // createIOLayer

    /**
     * Creates a {@link SerializingIOLayer} using the given
     * {@link ReadWritableFactory} and arguments.
     *
     * @param <T> the type of the ReadWritableFactory
     * @param readWritableFactory the ReadWritableFactory; may not be null
     * @param arguments the arguments; may not be null; must contain exactly one
     * argument, a string containing the pathname of the data file
     *
     * @return a SerializingIOLayer
     * @throws IllegalArgumentException if arguments does not meet the criteria
     * above
     */
    private <T extends ReadWritable & Serializable> SerializingIOLayer<T> createSerializingIOLayer(ReadWritableFactory<T> readWritableFactory, Object[] arguments) {
        assert (readWritableFactory != null);
        assert (arguments != null);
        if (arguments.length != 1) {
            throw new IllegalArgumentException("arguments must have length 1");
        }    // if

        String pathname = arguments[0].toString();
        return new SerializingIOLayer(pathname, readWritableFactory);
    }    // createSerializingIOLayer()

    /**
     * Creates an {@link XMLIOLayer} using the given {@link ReadWritableFactory}
     * and arguments.
     *
     * @param <T> the type of the ReadWritableFactory
     * @param readWritableFactory the ReadWritableFactory; may not be null
     * @param arguments the arguments; may not be null; must contain exactly one
     * argument, a string containing the pathname of the data file
     *
     * @return an XMLIOLayer
     * @throws IllegalArgumentException if arguments does not meet the criteria
     * above
     */
    private <T extends ReadWritable> XMLIOLayer<T> createXMLIOLayer(ReadWritableFactory<T> readWritableFactory, Object[] arguments) {
        assert (readWritableFactory != null);
        assert (arguments != null);
        if (arguments.length != 1) {
            throw new IllegalArgumentException("arguments must have length 1");
        }    // if

        String pathname = arguments[0].toString();
        return new XMLIOLayer(pathname, readWritableFactory);
    }    // createXMLIOLayer()

}    // IOLayerFactory
