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

package io.github.waynem77.bscmail.gui.util;

import java.awt.Component;

/**
 * An observer of a control that will be notified in case of a drag and drop
 * event.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 4.0
 */
public interface DragAndDropListener {

    /**
     * Method that fires when a drag and drop event occurs in the source
     * component.
     *
     * @param component the source component
     */
    public void dragAndDropPerformed(Component component);

}    // DragAndDropListener
