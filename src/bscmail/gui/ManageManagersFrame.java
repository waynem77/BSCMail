/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
