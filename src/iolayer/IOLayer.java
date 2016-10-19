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
import java.util.List;
import main.ReadWritable;
import main.ReadWritableFactory;

/**
 * Represents an I/O layer that reads and writes {@link ReadWritable}s to disk.
 * 
 * @author Wayne Miller
 * @since 2.1
 */
public interface IOLayer {

    /**
     * Constructs a list of read-writables from an input stream using the given
     * read-writable factory. The programmer must ensure that the given factory
     * makes sense for the given file.
     * 
     * If the I/O layer is unable to create a read-writable from the input
     * stream, it will insert a null into the appropriate place in the list.  If
     * the I/O layer is unable to create any read-writables, it will return
     * null.
     *
     * @param <T> the type of read-writable to construct
     * @param input the input stream; may not be null
     * @param factory the read-writable factory to use; may not be null
     * @return a list of read-writables constructed from {@code filename} using
     * {@code factory}, or null if no read-writables could be constructed
     * @throws NullPointerException if either parameter is null
     * @throws IOException if an I/O error occurs
     */
    public <T extends ReadWritable> List<T> readAll(InputStream input, ReadWritableFactory<T> factory) throws IOException;

    /**
     * Writes the given list of read-writables to an output stream.
     *
     * @param output the output stream; may not be null
     * @param readWritables the list of read-writables; may not be null, nor
     * contain any null elements
     * @throws NullPointerException if either parameter is null, or if
     * {@code readWritables} contains a null element
     * @throws IOException if an I/O error occurs
     */
    public void writeAll(OutputStream output, List<? extends ReadWritable> readWritables) throws IOException;
    
}    // IOLayer
