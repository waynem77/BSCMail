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

import bscmail.Volunteer;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import main.Application;

/**
 * A graphical interface to manage the defined list of volunteers in
 * {@link Application}.
 * 
 * @since 2.0
 * @author Wayne Miller
 */
public class ManageVolunteersFrame extends ManageListFrame<Volunteer> {
    
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
}    // ManageVolunteersFrame
