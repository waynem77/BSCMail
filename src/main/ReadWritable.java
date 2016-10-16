/*
 * Copyright © 2015 Wayne Miller
 */

package main;

import java.util.Map;

/**
 * A class may implement this interface to be read from and written to disk by
 * an {@link IOLayer}
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
