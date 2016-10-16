/*
 * Copyright © 2014 Wayne Miller
 */

package main;

/**
 * A class may implement the {@code ManagersObserver} interface when it wants to
 * be informed of changes to the list of defined managers.
 * 
 * @see Application
 * @see bscmail.Manager
 * @since 2.0
 * @author Wayne Miller
 */
public interface ManagersObserver {

    /**
     * This method is called whenever the list of defined managers changes.
     */
    public void managersChanged();
    
}    // ManagersObserver
