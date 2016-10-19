/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
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
