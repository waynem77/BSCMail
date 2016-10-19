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

package bscmail.gui;

import bscmail.Event;
import bscmail.transformer.Transformer;
import java.io.*;
import main.Application;

/**
 * Constructs and displays an email.  The email is constructed from an email
 * template and transformed by a {@link Transformer}, with values filled in from
 * an {@link Event}.
 * 
 * @author Wayne Miller
 */
public class DisplayEmailFrame extends ManageTextFrame {
    
    /**
     * Constructs a new display email frame displaying an email constructed from
     * the template in the given reader, and transformed via the given
     * transformer with values filled in from the given event.
     * 
     * @param reader the template text stream; may not be null
     * @param transformer the transformer that transforms the template text; may not be null
     * @param event the event that supplies the transformation values; may not be null
     * @throws NullPointerException if any parameter is null
     * @throws IOException if an I/O error occurs
     */
    public DisplayEmailFrame(Reader reader, Transformer transformer, Event event) throws IOException {
        if (reader == null) {
            throw new NullPointerException("reader may not be null");
        }    // if
        if (transformer == null) {
            throw new NullPointerException("transformer may not be null");
        }    // if
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        
        setTitle(Application.getApplicationName() + " - Event Email Text");

        // We aren't interested in catching any exceptions.  However, we do want
        // the resources to close automatically in case of problems.
        try (BufferedReader input = new BufferedReader(reader)) {
            String line;
            while ((line = input.readLine()) != null) {
                appendText(transformer.transform(event, line));
            }    // while
        }    // try
        scrollToTop();
    }    // DisplayEmailFrame()
    
}    // DisplayEmailFrame