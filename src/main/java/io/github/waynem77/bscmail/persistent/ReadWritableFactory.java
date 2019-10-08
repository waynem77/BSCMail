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

/**
 * A factory that creates a {@link ReadWritable} from a set of read-writable
 * properties. These properties may be extracted directly from an object via
 * {@link ReadWritable#getReadWritableProperties()}, but more typically are
 * extracted from a disk file.
 *
 * It is recommended that {@code ReadWritableFactory} implementations be
 * generous in extracting values from the properties, as values may be encoded
 * in different (but compatible) ways. For instance, a {@link Boolean} could be
 * encoded as a Boolean or as the string "true" or "false". In particular,
 * read-writables may be encoded as objects or as nested property maps.
 *
 * @author Wayne Miller
 * @param <T> the type of object created by the factory
 * @since 2.1
 */
public interface ReadWritableFactory<T extends ReadWritable> {
    
    /**
     * Constructs a read-writable from the given read-writable properties.  If
     * the factory is unable to create a read-writable from the given
     * properties, this method returns null.
     * 
     * @param properties the read-writable properties; may not be null
     * @return a read-writable constructed from the given properties, or null if
     * the factory is unable to construct a read-writable
     * @throws NullPointerException if {@code properties} is null
     */
    public T constructReadWritable(Map<String, Object> properties);
    
}    // ReadWritableFactory
