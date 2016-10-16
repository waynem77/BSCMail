/*
 * Copyright © 2014–2015 Wayne Miller
 */

package bscmail.gui;

import java.util.*;
import javax.swing.*;

/**
 * An abstract base class for a panel that displays and manages an element of a
 * list.
 * 
 * @param <E> the type of element being managed
 * @since 2.0
 * @author Wayne Miller
 */
public abstract class ManageElementPanel<E> extends JPanel {
    
    private final List<ManageElementPanelObserver<E>> observers = new LinkedList<>();

    /**
     * Loads the details of an element into the panel.  If the given element is
     * null, then this method clears the panel.
     * 
     * @param element the element to load
     */
    public abstract void loadElement(E element);
    
    /**
     * Creates and returns a new element from the values of the components in
     * the panel.  Guaranteed to not be null.
     *
     * @return a new element created from the values of the components in the
     * panel
     */
    public abstract E createElement();
    
    /**
     * Returns true if the panel's element is valid for the frame containing it,
     * or false otherwise.
     *
     * @return true if the panel's element is valid for the frame containing it;
     * false otherwise
     * @since 2.0.1
     */
    public abstract boolean elementIsValid();
    
    /**
     * Adds an observer to this panel.
     *
     * @param observer the observer to add; may not be null
     * @throws NullPointerException if {@code observer} is null
     * @since 2.0.1
     */
    public void addObserver(ManageElementPanelObserver<E> observer) {
        assertInvariant();
        if (observer == null) {
            throw new NullPointerException("observer may not be null");
        }    // if
        observers.add(observer);
        assertInvariant();
    }    // addCanCreateElementObserver()

    /**
     * Notifies the observers when the return value of {@link #elementIsValid()}
     * changes.
     * 
     * @since 2.0.1
     */
    public void notifyObservers() {
        assertInvariant();
        for (ManageElementPanelObserver<E> observer : observers) {
            observer.elementValidityChanged();
        }    // for
    }    // notifyObservers()
    
    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (observers != null);
        assert (! observers.contains(null));
    }    // assertInvariant()
    
}    // ManageElementPanel
