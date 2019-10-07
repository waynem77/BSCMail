/*
 * Copyright © 2014-2019 its authors.  See the file "AUTHORS" for details.
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

import io.github.waynem77.bscmail.persistent.Volunteer;

/**
 * A wrapper for a {@link Volunteer}. When displayed in a Swing control, the
 * wrapper displays the name of the volunteer, or "(open)" if the volunteer is
 * null.
 *
 * @author Wayne Miller
 */
public class VolunteerDisplayWrapper {

    /**
     * The volunteer being wrapped.
     */
    private final Volunteer volunteer;

    /**
     * Constructs a new wrapper for the given volunteer.
     *
     * @param volunteer  the volunteer to wrap
     */
    public VolunteerDisplayWrapper(Volunteer volunteer) {
        this.volunteer = volunteer;
    }    // VolunteerDisplayWrapper()

    /**
     * Returns the volunteer being wrapped.
     *
     * @return the volunteer being wrapped
     */
    public Volunteer getVolunteer() {
        return volunteer;
    }    // getVolunteer()

    /**
     * Returns the name of the volunteer being wrapped, or "(open)" if the
     * volunteer is null.
     *
     * @return name of the volunteer being wrapped, or "(open)" if the volunteer
     * is null
     */
    @Override
    public String toString() {
        return (volunteer == null) ? "(open)" : volunteer.getName();
    }    // toString()

}    // VolunteerDisplayWrapper
