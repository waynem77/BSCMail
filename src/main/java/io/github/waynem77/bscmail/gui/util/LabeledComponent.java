/*
 * Copyright © 2016-2019 its authors.  See the file "AUTHORS" for details.
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
package io.github.waynem77.bscmail.gui.util;

import java.awt.Component;
import javax.swing.JLabel;

/**
 * Represents a paired label and component.
 *
 * @param <E> the type of component paired
 */
public class LabeledComponent<E extends Component> {

    /**
     * The label portion of the pair.
     */
    private final JLabel label;

    /**
     * The component portion of the pair.
     */
    private final E component;

    /**
     * Constructs a new labeled component. The label is created from the
     * provided string; the component is passed as an argument.
     *
     * @param labelText the text of the label; may not be null
     * @param component the component; may not be null
     * @throws NullPointerException if either parameter is null
     */
    public LabeledComponent(String labelText, E component) {
        if (labelText == null) {
            throw new NullPointerException("labelText may not be null");
        }    // if
        if (component == null) {
            throw new NullPointerException("component may not be null");
        }    // if

        label = new JLabel(labelText);
        this.component = component;
    }    // LabeledComponent

    /**
     * Returns the label associated with the component. The text of the label is
     * equal to that passed to the constructor.
     *
     * @return the label associated with the component
     */
    public JLabel getLabel() {
        return label;
    }    // getLabel()

    /**
     * Returns the component.
     *
     * @return the component
     */
    public E getComponent() {
        return component;
    }    // getComponent()

}    // LabeledComponent
