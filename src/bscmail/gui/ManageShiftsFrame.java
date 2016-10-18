/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.gui;

import bscmail.*;
import java.io.*;
import java.util.*;
import main.*;

/**
 * A graphical interface to manage the defined list of shifts in
 * {@link Application}.
 * 
 * @since 2.0
 * @author Wayne Miller
 */
public class ManageShiftsFrame extends ManageListFrame<Shift> {
    
    /**
     * Constructs a new manage shifts frame.
     */
    public ManageShiftsFrame() {
        super(
                new ManageShiftPanel(),
                new Vector<>(Application.getShifts()),
                new Comparator<Shift>(){
                    @Override public int compare(Shift shift1, Shift shift2) {
                        assert (shift1 != null);
                        assert (shift2 != null);
                        return shift1.toString().compareTo(shift2.toString());
                    }    // compare()
                }    // Comparator
        );
        
        setTitle(Application.getApplicationName() + " - Manage Shifts");
    }    // ManageShiftsFrame()
    
    /**
     * Saves the given list as the defined list of shifts.
     * 
     * @param shifts the shifts to save; may not be null
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void setListDataHook(List<Shift> shifts) throws IOException {
        assert (shifts != null);
        Application.setShifts(shifts);
    }    // saveListData()
}    // ManageShiftsFrame
