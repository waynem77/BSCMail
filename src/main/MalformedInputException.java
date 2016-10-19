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

package main;

/**
 * Indicates that an XML file is malformed.
 * 
 * @author Wayne Miller
 */
public class MalformedInputException extends RuntimeException {

    /**
     * Creates a new instance of <code>MalformedInputException</code> without detail message.
     */
    public MalformedInputException() {
    }    // MalformedInputException()

    /**
     * Constructs an instance of <code>MalformedInputException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public MalformedInputException(String msg) {
        super(msg);
    }    // MalformedInputException(String msg)
}    // MalformedInputException
