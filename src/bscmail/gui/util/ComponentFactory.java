/*
 * Copyright © 2019 its authors.  See the file "AUTHORS" for details.
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

import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Factory for statically creating standard components.
 *
 * @author Wayne Miller <waynem77@yahoo.com>
 * @since 3.4
 */
public class ComponentFactory {

    /**
     * The standard border width.
     */
    private static final int BORDER_WIDTH = 4;

    /**
     * The standard strut size.
     */
    private static final int STRUT_SIZE = 4;

    /**
     * Creates and returns a "standard" border for use by GUI containers.
     *
     * @return a "standard" border for use by GUI containers
     */
    public static Border getStandardBorder() {
        return new EmptyBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH);
    }    // getStandardBorder()

    public static Component getStandardVerticalStrut() {
        return Box.createVerticalStrut(STRUT_SIZE);
    }    // getStandardVerticalStrut()
}    // ComponentFactory
