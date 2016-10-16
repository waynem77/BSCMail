/*
 * Copyright © 2014 Wayne Miller
 */

package main;

/**
 * A class may implement the {@code VolunteersObserver} interface when it wants to
 * be informed of changes to the list of defined volunteers.
 * 
 * @see Application
 * @see bscmail.Volunteer
 * @since 2.0
 * @author Wayne Miller
 */
public interface VolunteersObserver {

    /**
     * This method is called whenever the list of defined volunteers changes.
     */
    public void volunteersChanged();
    
}    // VolunteersObserver
