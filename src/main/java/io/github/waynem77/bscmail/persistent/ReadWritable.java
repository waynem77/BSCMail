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
 * A class may implement this interface to be read from and written to disk by
 * an {@link iolayer.IOLayer}
 * 
 * @author Wayne Miller
 * @since 2.1
 */
public interface ReadWritable {

    /**
     * Returns a map containing the read-writable properties of the object.
     * Implementations of {@link ReadWritable} must ensure that the return
     * value does not have any null keys.
     * 
     * @return a map containing the read-writable properties of the object
     */
    public Map<String, Object> getReadWritableProperties();
    
    /**
     * Returns a factory that creates read-writable objects of this type from
     * property maps.
     * 
     * @return a factory that creates read-writable objects of this type from
     * property maps
     */
    public ReadWritableFactory getReadWritableFactory();
    
}    // ReadWritable
