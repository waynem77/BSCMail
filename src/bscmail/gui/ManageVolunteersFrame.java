/*
 * Copyright © 2014 Wayne Miller
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
