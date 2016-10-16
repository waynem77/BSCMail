/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.Event;

/**
 * Returns a property from an {@link Event}.  Implementations of this interface
 * are used by the {@link Transformer} to transform a document template into a
 * custom email message.
 * 
 * @see Transformer
 * @author Wayne Miller
 */
public interface EventProperty {
    
    /**
     * Returns a property from an event.  The specific property is determined by
     * the implementation.
     * 
     * @param event the event from which to retrieve properties; may not be null
     * @return a string property from {@code event}
     * @throws NullPointerException if {@code event} is null
     */
    public String getProperty(Event event);
    
}    // EventProperty
