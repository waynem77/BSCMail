/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
