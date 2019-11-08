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

import java.util.Vector;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

/**
 * A list control with special properties, appropriate for a
 * {@link io.github.waynem77.bscmail.gui.ManageListFrame}. A managed list
 * control provides the following functionality beyond that of a normal
 * {@link JList} control:
 * <ul>
 * <li>A method that allows access to the underlying data as a list for easy manipulation.</li>
 * </ul>
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @param <E> the type of element contained in the list
 * @since 4.0
 */
public class ManagedListControl<E> extends JList<E> {

    /**
     * Constructs a new ManagedListControl with the given data.
     * @param listData the initial data; may not be null nor contain null
     * @throws NullPointerException if listData is null or contains null
     */
    public ManagedListControl(Vector<E> listData) {
        if ((listData == null) || listData.contains(null)) {
            throw new NullPointerException("listData may not be null nor contain null");
        }

        setListData(listData);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }   // ManagedListControl()

    /**
     * Returns the data within the managed list control as a vector.
     * @return the data within the managed list
     */
    public Vector<E> getListData() {
        ListModel<E> listModel = getModel();
        Vector<E> listData = new Vector<>();
        for (int i = 0; i < listModel.getSize(); ++i) {
            listData.add(listModel.getElementAt(i));
        }    // for
        return listData;
    }

}    // ManagedListControl
