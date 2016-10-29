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
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import bscmail.Application;
import bscmail.RolesObserver;

/**
 * Created by nathan.cordner on 10/19/16.
 */
public class ManageRolesFrame extends ManageListFrame<Role> implements RolesObserver {

    /**
     * Constructs a new manage roles frame.
     */
    public ManageRolesFrame() {
        super(
                new ManageRolePanel(),
                new Vector<>(Application.getRoles()),
                new Comparator<Role>(){
                    @Override public int compare(Role role1, Role role2) {
                        assert (role1 != null);
                        assert (role2 != null);
                        return role1.toString().compareTo(role2.toString());
                    }    // compare()
                }    // Comparator
        );
        Application.registerObserver(this);

        setTitle(Application.getApplicationName() + " - Manage Roles");
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
        Application.setRoles(roles);
    }    // saveListData()

    public void rolesChanged(){
        try {
            updateListData(new Vector<>(Application.getRoles()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}    // ManageRolesFrame
