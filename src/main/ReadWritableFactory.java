/*
 * Copyright © 2015 Wayne Miller
 */

package main;

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
