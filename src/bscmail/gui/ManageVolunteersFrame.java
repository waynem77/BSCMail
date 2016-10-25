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

import bscmail.Role;
import bscmail.Volunteer;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import main.Application;
import main.RolesObserver;
import main.VolunteersObserver;

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
     */
    public ManageVolunteersFrame() {
        super(
                new ManageVolunteerPanel(),
                new Vector<>(Application.getVolunteers()),
                new Comparator<Volunteer>(){
                    @Override public int compare(Volunteer volunteer1, Volunteer volunteer2) {
                        assert (volunteer1 != null);
                        assert (volunteer2 != null);
                        return volunteer1.toString().compareTo(volunteer2.toString());
                    }    // compare()
                }    // Comparator
        );
        Application.registerObserver((RolesObserver)this);
        Application.registerObserver((VolunteersObserver)this);
        
        setTitle(Application.getApplicationName() + " - Manage Volunteers");
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
        Application.setVolunteers(volunteers);
    }    // saveListData()

    /**
     * This method is called whenever the list of defined volunteer roles
     * changes.
     */
    @Override
    public void rolesChanged() {
        List<Role> canonicalRoles = Application.getRoles();
        Vector<Volunteer> volunteers = new Vector<>(Application.getVolunteers());
        for (int i = 0; i < volunteers.size(); ++i) {
            Volunteer volunteer = volunteers.get(i);
            List<Role> roles = volunteer.getRoles();
            roles.retainAll(canonicalRoles);
            Volunteer newVolunteer = new Volunteer(volunteer.getName(),
                    volunteer.getEmail(),
                    volunteer.getPhone(),
                    volunteer.getNotes());
            for (Role role : roles) {
                newVolunteer.addRole(role);
            }    // for
            volunteers.set(i, newVolunteer);
        }    // for

        try {
            updateListData(volunteers);
            setListDataHook(volunteers);
        } catch (IOException e) {    // try
            JOptionPane.showMessageDialog(this, "Unable to update shift roles:\n\n" + e);
        }    // catch
    }    // rolesChanged()

    /**
     * Update list of volunteers in manager frame.
     */
    public void volunteersChanged(){
        try {
            updateListData(new Vector<>(Application.getVolunteers()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}    // ManageVolunteersFrame
