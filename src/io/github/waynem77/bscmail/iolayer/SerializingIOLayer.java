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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An I/O layer that serializes and unserializes {@link ReadWritable}s to and
 * from binary storage files. The data persists in the file, beyond the lifetime
 * of the object.
 *
 * This I/O layer may only be used with {@link Serializable} read-writables.
 *
 * @author Wayne Miller
 * @param <T> the type of read-writable managed by this I/O layer
 * @since 2.1
 */
public class SerializingIOLayer<T extends ReadWritable & Serializable> implements IOLayer<T> {

    /**
     * The pathname of the XML file used to store the read-writables
     */
    private final String pathname;

    /**
     * The factory used to create read-writables from property maps.
     */
    private final ReadWritableFactory<T> factory;

    /**
     * Constructs a new serializing I/O layer. The pathname of the storage file
     * and the factory used to create the read-writables are supplied as
     * parameters. It is the responsibility of the programmer to ensure that
     * both parameters make sense. (Isn't it always?)
     *
     * @param pathname the pathname of the storage file used to store the
     * read-writables; may not be null
     * @param factory the factory used to construct read-writables; may not be
     * null
     * @throws NullPointerException if either parameter is null
     */
    public SerializingIOLayer(String pathname, ReadWritableFactory<T> factory) {
        if (pathname == null) {
            throw new NullPointerException("pathname may not be null");
        }    // if
        if (factory == null) {
            throw new NullPointerException("factory may not be null");
        }    // if

        this.pathname = pathname;
        this.factory = factory;
        assertInvariant();
    }    // XMLIOLayer

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() throws IOException {
        assertInvariant();
        try (FileInputStream fileInputStream = new FileInputStream(pathname);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Object object = objectInputStream.readObject();
            if (object instanceof List) {
                List oldList = (List)object;
                List<T> newList = new ArrayList<>();
                for (Object oldElement : oldList) {
                    T newElement = null;
                    if (oldElement instanceof ReadWritable) {
                        newElement = factory.constructReadWritable(((ReadWritable)oldElement).getReadWritableProperties());
                    }    // if
                    newList.add(newElement);
                }    // for
                return newList;
            }    // if
        } catch (ClassNotFoundException | ClassCastException e) {    // try
            // We could not create an object.
        }    // catch
        
        return null;
    }    // getAll()

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAll(List<T> list) throws IOException {
        assertInvariant();
        if (list == null) {
            throw new NullPointerException("list may not be null");
        }    // if
        if (list.contains(null)) {
            throw new NullPointerException("list may not contain null");
        }    // if

        try (FileOutputStream fileOutputStream = new FileOutputStream(pathname);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(list);
        }    // try
    }    // setAll()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (pathname != null);
        assert (factory != null);
    }    // assertInvariant()
}    // SerializingIOLayer
