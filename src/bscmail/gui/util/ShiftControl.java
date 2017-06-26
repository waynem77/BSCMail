/*
 * Copyright © 2014-2017 its authors.  See the file "AUTHORS" for details.
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

import bscmail.Shift;
import bscmail.Volunteer;
import java.util.List;
import javax.swing.JComboBox;

/**
 * A combo box that contains a shift and allows the user to select volunteers.
 * This class extends {@code JComboBox}
 *
 * @author Wayne Miller
 */
public class ShiftControl extends JComboBox<VolunteerDisplayWrapper> {

    /**
     * The shift corresponding to this combo box.
     */
    private final Shift shift;

    /**
     * Constructs a new shift control
     *
     * @param shift the shift corresponding to this control; may not be null
     * @param volunteers the list of volunteers used to populate the control;
     * may not be null, nor contain any null elements
     * @throws NullPointerException if {@code shift} is null or
     * {@code voluneers} is null or contains null
     */
    public ShiftControl(Shift shift, List<Volunteer> volunteers) {
        if (shift == null) {
            throw new NullPointerException("shift may not be null");
        }    // if
        if (volunteers == null) {
            throw new NullPointerException("volunteers may not be null");
        }    // if
        if (volunteers.contains(null)) {
            throw new NullPointerException("volunteers may not contain null");
        }    // if

        this.shift = shift;
        setModel(volunteers);
    }    // ShiftControl()

    /**
     * Returns the shift corresponding to this control
     *
     * @return the shift corresponding to this control
     */
    public Shift getShift() {
        return shift;
    }    // getShift()

    /**
     * Returns the volunteer selected by the user.
     *
     * @return the volunteer selected by the user
     */
    public Volunteer getVolunteer() {
        VolunteerDisplayWrapper container = (VolunteerDisplayWrapper) getSelectedItem();
        return container.getVolunteer();
    }    // getVolunteer()

    /**
     * Sets the list of volunteers.
     *
     * @param volunteers the volunteers used to populate the combo box; may not
     * be null nor contain any null elements
     * @throws NullPointerException if {@code voluneers} is null or contains
     * null
     */
    public final void setModel(List<Volunteer> volunteers) {
        if (volunteers == null) {
            throw new NullPointerException("volunteers may not be null");
        }    // if
        if (volunteers.contains(null)) {
            throw new NullPointerException("volunteers may not be null");
        }    // if

        removeAllItems();
        addItem(new VolunteerDisplayWrapper(null));
        for (Volunteer volunteer : volunteers) {
            addItem(new VolunteerDisplayWrapper(volunteer));
        }    // for

    }    // setModel()

}    // ShiftControl
