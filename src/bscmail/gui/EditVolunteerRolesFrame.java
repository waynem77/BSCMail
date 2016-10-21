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
import main.Application;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Created by nathan.cordner on 10/20/16.
 */
public class EditVolunteerRolesFrame extends JFrame {

    /**
     * The Volunteer object being managed.
     */
    private Volunteer volunteer;

    /**
     * The list box for currently exisiting roles.
     */
    private final JList<Role> existingRoles;

    /**
     * The list box for Volunteer's currently assigned roles
     */
    private final JList<Role> volunteerRoles;


    public EditVolunteerRolesFrame(Volunteer volunteer){

        this.volunteer = volunteer;

        setTitle(Application.getApplicationName() + " - Edit Roles for " + volunteer.getName());
        setLayout(new GridLayout(1, 3));

        //Left-hand column
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300,200));
        panel.setBorder(BorderFactory.createTitledBorder("Existing Roles"));
        Vector<Role> roleList = new Vector<Role>(Application.getRoles()); //read all roles from XML file
        existingRoles = new JList<Role>(roleList);
        existingRoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(existingRoles));
        add(panel);

        //Center column
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(new JLabel(""));
        JButton button = new JButton("Assign");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                assignButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);
        panel.add(new JLabel(""));
        button = new JButton("Unassign");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                unassignButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);
        panel.add(new JLabel(""));
        add(panel);

        //Right hand column
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300,200));
        panel.setBorder(BorderFactory.createTitledBorder("Assigned Roles"));
        Vector<Role> assignedRoles = new Vector<Role>(volunteer.getRoles()); //get roles from Volunteer object
        volunteerRoles = new JList<Role>(assignedRoles);
        volunteerRoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        volunteerRoles.setPreferredSize(existingRoles.getPreferredScrollableViewportSize());
        panel.add(new JScrollPane(volunteerRoles));
        add(panel);

    }

    /**
     * Event fired when the assign button is clicked.
     */
    private void assignButtonClicked() {
        // 1. Get particular role from the left column
        // 2. Call the volunteer.addRole() function
        // 3. Update the right column (if need be)

        //Step 1.
        Role role = existingRoles.getSelectedValue();

        //Step 2.
        if (role != null)
            volunteer.addRole(role);

        //Step 3.
        Vector<Role> assignedRoles = new Vector<Role>(volunteer.getRoles());
        volunteerRoles.setListData(assignedRoles);

    }    // assignButtonClicked()

    /**
     * Event fired when the unassign roles button is clicked.
     */
    private void unassignButtonClicked() {
        Role role = volunteerRoles.getSelectedValue();
        if (role != null)
            volunteer.removeRole(role);
        Vector<Role> assignedRoles = new Vector<Role>(volunteer.getRoles());
        volunteerRoles.setListData(assignedRoles);
    }    // unassignButtonClicked()



}
