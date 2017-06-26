/*
 * Copyright © 2014-2017 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui.util;

import bscmail.EventProperty;
import javax.swing.JTextField;

/**
 * A text field that allows the user to enter values for an event property. This
 * class extends {@code JTextField}.
 *
 * @author Wayne Miller
 */
public class EventPropertyControl extends JTextField {

    /**
     * The event property corresponding to this control.
     */
    private final EventProperty eventProperty;

    /**
     * Constructs a new event property control from the given event property.
     *
     * @param eventProperty the event property corresponding to this control;
     * may not be null
     * @throws NullPointerException if {@code eventProperty} is null
     */
    public EventPropertyControl(EventProperty eventProperty) {
        if (eventProperty == null) {
            throw new NullPointerException("eventProperty may not be null");
        }    // if
        this.eventProperty = eventProperty;
        setText(eventProperty.getDefaultValue());
    }    // EventPropertyControl()

    /**
     * Returns the event property corresponding to this control.
     *
     * @return the event property corresponding to this control
     */
    public EventProperty getEventProperty() {
        return eventProperty;
    }    // getEventProperty()

    /**
     * Returns the value entered by the user.
     *
     * @return the value entered by the user
     */
    public String getValue() {
        return getText();
    }    // getVolunteer()

}    // EventPropertyControl
