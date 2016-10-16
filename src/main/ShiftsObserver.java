/*
 * Copyright © 2014 Wayne Miller
 */

package main;

/**
 * A class may implement the {@code ShiftsObserver} interface when it wants to
 * be informed of changes to the list of defined volunteer shifts.
 * 
 * @see Application
 * @see bscmail.Shift
 * @since 2.0
 * @author Wayne Miller
 */
public interface ShiftsObserver {

    /**
     * This method is called whenever the list of defined volunteer shifts
     * changes.
     */
    public void shiftsChanged();
    
}    // ShiftsObserver
