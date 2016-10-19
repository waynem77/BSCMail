/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package iolayer;

import java.io.*;
import java.util.*;
import main.*;

/**
 * An I/O layer that serializes and unserializes {@link ReadWritable}s to and
 * from binary files.
 * 
 * @author Wayne Miller
 * @since 2.1
 */
public class SerializingIOLayer implements IOLayer {

    /**
     * Unserializes a list of read-writables from a binary input stream.
     *
     * If the I/O layer is unable to create a read-writable from the input
     * stream, it will insert a null into the appropriate place in the list.  If
     * the I/O layer is unable to create any read-writables, it will return
     * null.
     *
     * @param <T> the type of read-writable to construct
     * @param input the input stream; may not be null
     * @param factory the read-writable factory to use; may not be null
     * @return a list of read-writables constructed from {@code filename}, or
     * null if no read-writables could be constructed
     * @throws NullPointerException if either parameter is null
     * @throws IOException if an I/O error occurs
     */
    @Override
    public <T extends ReadWritable> List<T> readAll(InputStream input, ReadWritableFactory<T> factory) throws IOException {
        if (input == null) {
            throw new NullPointerException("input may not be null");
        }    // if
        if (factory == null) {
            throw new NullPointerException("factory may not be null");
        }    // if
        
        try (ObjectInputStream objectInputStream = new ObjectInputStream(input)) {
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
            return null;
        }    // catch
        
        return null;
    }    // readAll()

    /**
     * Writes the given list of read-writables as XML to an output stream.  The
     * read-writables are required to implement the {@link Serializable}
     * interface.
     *
     * @param output the output stream; may not be null
     * @param readWritables the list of read-writables; may not be null, nor
     * contain any null elements; must be serializable
     * @throws NullPointerException if either parameter is null, or if
     * {@code readWritables} contains a null element
     * @throws NotSerializableException if the elements of {@code readWritables}
     * cannot be serialized
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void writeAll(OutputStream output, List<? extends ReadWritable> readWritables) throws IOException {
        if (output == null) {
            throw new NullPointerException("output may not be null");
        }    // if
        if (readWritables == null) {
            throw new NullPointerException("readWritables may not be null");
        }    // if
        if (readWritables.contains(null)) {
            throw new NullPointerException("readWritables may not contain null");
        }    // if
        
        try (ObjectOutputStream outputStream = new ObjectOutputStream(output)) {
            outputStream.writeObject(readWritables);
        }    // try
     }    // writeAll()

}    // SerializingIOLayer
