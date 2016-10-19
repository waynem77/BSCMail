/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
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
