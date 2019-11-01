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

package io.github.waynem77.bscmail.gui;

import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.persistent.Role;
import io.github.waynem77.bscmail.persistent.RolesObserver;
import io.github.waynem77.bscmail.persistent.Volunteer;
import io.github.waynem77.bscmail.persistent.VolunteersObserver;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * A graphical interface to manage the defined list of volunteers in
 * {@link Application}.
 *
 * @since 2.0
 * @author Wayne Miller
 */
public class ManageVolunteersFrame extends ManageListFrame<Volunteer> implements RolesObserver, VolunteersObserver {

    /**
     * Constructs a new manage volunteers frame.
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public ManageVolunteersFrame(Application application) {
        super(
                application,
                new ManageVolunteerPanel(application),
                new Vector<>(application.getVolunteers()),
                new Comparator<Volunteer>(){
                    @Override public int compare(Volunteer volunteer1, Volunteer volunteer2) {
                        assert (volunteer1 != null);
                        assert (volunteer2 != null);
                        return volunteer1.toString().compareTo(volunteer2.toString());
                    }    // compare()
                },    // Comparator
                "Volunteer"
        );
        application.registerObserver((RolesObserver)this);
        application.registerObserver((VolunteersObserver)this);

        setTitle(application.getApplicationName() + " - Manage Volunteers");
    }    // ManageVolunteersFrame()

    /**
     * Saves the given list as the defined list of volunteers.
     *
     * @param volunteers the volunteers to save; may not be null
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void setListDataHook(List<Volunteer> volunteers) throws IOException {
        assert (volunteers != null);
        getApplication().setVolunteers(volunteers);
    }    // saveListData()

    /**
     * This method is called whenever the list of defined volunteer roles
     * changes.
     */
    @Override
    public void rolesChanged() {
        List<Role> canonicalRoles = getApplication().getRoles();
        Vector<Volunteer> volunteers = new Vector<>(getApplication().getVolunteers());
        for (int i = 0; i < volunteers.size(); ++i) {
            Volunteer volunteer = volunteers.get(i);
            List<Role> roles = volunteer.getRoles();
            roles.retainAll(canonicalRoles);
            Volunteer newVolunteer = new Volunteer(
                    volunteer.getName(),
                    volunteer.getEmail(),
                    volunteer.getPhone(),
                    volunteer.getNotes(),
                    false,
                    roles
            );
            volunteers.set(i, newVolunteer);
        }    // for

        try {
            updateListData(volunteers);
            setListDataHook(volunteers);
        } catch (IOException e) {    // try
            application.showErrorDialog(this, "Unable to update volunteer roles:", e);
        }    // catch
    }    // rolesChanged()

    /**
     * Update list of volunteers in manager frame.
     */
    public void volunteersChanged(){
        try {
            updateListData(new Vector<>(getApplication().getVolunteers()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}    // ManageVolunteersFrame
