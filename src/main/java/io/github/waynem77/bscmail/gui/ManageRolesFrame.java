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

import io.github.waynem77.bscmail.persistent.Application;
import io.github.waynem77.bscmail.persistent.Role;
import io.github.waynem77.bscmail.persistent.RolesObserver;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * Created by nathan.cordner on 10/19/16.
 */
public class ManageRolesFrame extends ManageListFrame<Role> implements RolesObserver {

    /**
     * Constructs a new manage roles frame.
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public ManageRolesFrame(Application application) {
        super(
                application,
                new ManageRolePanel(application),
                new Vector<>(application.getRoles()),
                new Comparator<Role>(){
                    @Override public int compare(Role role1, Role role2) {
                        assert (role1 != null);
                        assert (role2 != null);
                        return role1.toString().compareTo(role2.toString());
                    }    // compare()
                }    // Comparator
        );
        application.registerObserver(this);

        setTitle(application.getApplicationName() + " - Manage Roles");
    }    // ManageRolesFrame()

    /**
     * Saves the given list as the defined list of roles.
     *
     * @param roles the roles to save; may not be null
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void setListDataHook(List<Role> roles) throws IOException {
        assert (roles != null);
        getApplication().setRoles(roles);
    }    // saveListData()

    public void rolesChanged(){
        try {
            updateListData(new Vector<>(getApplication().getRoles()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}    // ManageRolesFrame
