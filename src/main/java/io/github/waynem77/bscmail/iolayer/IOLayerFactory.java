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

/**
 * Factory interface for {@link IOLayer} objects.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 4.0
 */
public interface IOLayerFactory {

    /**
     * Creates an {@link IOLayer} implementation of the specified class
     * parameterized for the specified {@link ReadWritable} implementation using
     * the given arguments. The arguments depend on the type of IOLayer being
     * created and the IOLayerFactory implementation.
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
    public <T extends ReadWritable> IOLayer<T> createIOLayer(Class<? extends IOLayer> ioLayerClass, Class<T> readWritableClass, Object[] arguments);

}    // IOLayerFactory
