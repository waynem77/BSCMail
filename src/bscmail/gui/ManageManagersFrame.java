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

import bscmail.Manager;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import main.Application;

/**
 * A graphical interface to manage the defined list of managers in
 * {@link Application}.
 * 
 * @since 2.0
 * @author Wayne Miller
 */
public class ManageManagersFrame extends ManageListFrame<Manager> {
    
    /**
     * Constructs a new manage managers frame.
     */
    public ManageManagersFrame() {
        super(
                new ManageManagerPanel(),
                new Vector<>(Application.getManagers()),
                new Comparator<Manager>(){
                    @Override public int compare(Manager manager1, Manager manager2) {
                        assert (manager1 != null);
                        assert (manager2 != null);
                        return manager1.toString().compareTo(manager2.toString());
                    }    // compare()
                }    // Comparator
        );
        
        setTitle(Application.getApplicationName() + " - Manage Managers");
    }    // ManageManagersFrame()
    
    /**
     * Saves the given list as the defined list of managers.
     * 
     * @param managers the managers to save; may not be null
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void setListDataHook(List<Manager> managers) throws IOException {
        assert (managers != null);
        Application.setManagers(managers);
    }    // saveListData()
}    // ManageManagersFrame
