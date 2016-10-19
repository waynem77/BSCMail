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

/**
 * A class may implement the {@code ManageElementPanelObserver} interface when
 * it wants to be informed when the validity of the element contained in a
 * {@link ManageElementPanel} changes.
 *
 * @author Wayne Miller
 * @param <E> the type of element created by the {@code ManageElementPanel}
 * @since 2.0.1
 */
public interface ManageElementPanelObserver<E> {

    /**
     * This method is called when the validity of the element of the
     * {@link ManageElementPanel} being observed changes.
     */
    public void elementValidityChanged();

}    // ManageElementPanelObserver
