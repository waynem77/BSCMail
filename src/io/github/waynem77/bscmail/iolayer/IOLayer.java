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
import java.io.IOException;
import java.util.List;

/**
 * Represents an I/O layer that reads and writes {@link ReadWritable}s to
 * storage. The manner of storage is defined by the individual implementation.
 * Data may or may not persist beyond the lifetime of the object; this is
 * defined by the implementation.
 *
 * @author Wayne Miller
 * @param <T> the type of read-writable managed by this I/O layer
 * @since 2.1
 */
public interface IOLayer<T extends ReadWritable> {

    /**
     * Returns the list of read-writables currently stored by the I/O layer.
     *
     * @return the list of read-writables currently stored by the I/O layer
     * @throws IOException if an I/O error occurs
     */
    public List<T> getAll() throws IOException;

    /**
     * Sets the list of read-writables stored by the I/O layer to the given
     * list.
     *
     * @param list the list of read-writables to store; may not be null nor
     * contain a null element
     * @throws NullPointerException if {@code list} is null or contains a null
     * element
     * @throws IOException if an I/O error occurs
     */
    public void setAll(List<T> list) throws IOException;

}    // IOLayer
