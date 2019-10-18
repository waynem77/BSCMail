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
 * An {@link IOLayerFactory} that creates {@link TestIOLayer}s.
 * 
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class TestIOLayerFactory implements IOLayerFactory {

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
     * <th>TestIOLayer</th>
     * <td>three elements, which are ignored</td>
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
    @Override
    public <T extends ReadWritable> IOLayer<T> createIOLayer(Class<? extends IOLayer> ioLayerClass, Class<T> readWritableClass, Object[] arguments) {
        if (ioLayerClass == null) {
            throw new NullPointerException("ioLayerClass may not be null");
        }  // if
        if (readWritableClass == null) {
            throw new NullPointerException("readWritableClass may not be null");
        }  // if
        if ((arguments == null) || (arguments.length != 3)) {
            throw new IllegalArgumentException("arguments must be non-null and contain three items");
        }

        return new TestIOLayer<>();
    }    // createIOLayer
    
}
