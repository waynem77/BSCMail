/*
 * Copyright © 2014-2020 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.gui;

import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.persistent.Role;
import io.github.waynem77.bscmail.persistent.RolesObserver;
import io.github.waynem77.bscmail.persistent.Shift;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * A graphical interface to manage the defined list of shifts in
 * {@link Application}.
 *
 * @since 2.0
 * @author Wayne Miller
 */
public class ManageShiftsFrame extends ManageListFrame<Shift> implements RolesObserver {

    /**
     * Constructs a new manage shifts frame.
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public ManageShiftsFrame(Application application) {
        super(
                application,
                new ManageShiftPanel(application),
                new Vector<>(application.getShifts()),
                Comparator.comparing(Shift::toString),
                "Shift"
        );

        setTitle(application.createWindowTitle("Manage Shifts"));

        application.registerObserver(this);
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
        getApplication().setShifts(shifts);
    }    // saveListData()

    /**
     * This method is called whenever the list of defined volunteer roles
     * changes.
     */
    @Override
    public void rolesChanged() {
        List<Role> canonicalRoles = getApplication().getRoles();
        Vector<Shift> shifts = new Vector<>(getApplication().getShifts());
        for (int i = 0; i < shifts.size(); ++i) {
            Shift shift = shifts.get(i);
            List<Role> roles = shift.getRoles();
            roles.retainAll(canonicalRoles);
            Shift newShift = new Shift(shift.getDescription(),
                    roles,
                    shift.getDisplayVolunteerEmail(),
                    shift.getDisplayVolunteerPhone(),
                    shift.getDisplayVolunteerNotes());
            shifts.set(i, newShift);
        }    // for

        try {
            updateListData(shifts);
            setListDataHook(shifts);
        } catch (IOException e) {    // try
            application.showErrorDialog(this, "Unable to update shift roles:", e);
        }    // catch
    }    // rolesChanged()
}    // ManageShiftsFrame
