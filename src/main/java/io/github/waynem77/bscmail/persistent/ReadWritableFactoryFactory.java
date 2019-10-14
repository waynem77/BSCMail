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

package io.github.waynem77.bscmail.persistent;

/**
 * Factory interface for {@link ReadWritableFactory} objects.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 4.0
 */
public interface ReadWritableFactoryFactory {

    /**
     * Returns a {@link ReadWritableFactory} corresponding to the given {@link
     * ReadWritable} implementation.
     *
     * @param <T> the type of the ReadWritable implementation
     * @param readWritableClass the class of a ReadWritable implementation; may not be
     * null
     * @return a ReadWritableFactory corresponding to readWritableClass
     * @throws IllegalArgumentException if a factory has not been implemented
     * for readWritableClass
     * @throws NullPointerException if readWritableClass is null
     */
    public <T extends ReadWritable> ReadWritableFactory getReadWritableFactory(Class<T> readWritableClass);

}    // ReadWritableFactoryFactory
