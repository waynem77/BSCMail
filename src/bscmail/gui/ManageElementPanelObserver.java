/*
 * Copyright © 2014 Wayne Miller
 */
package bscmail.gui;

/**
 * A class may implement the {@code ManageElementPanelObserver} interface when
 * it wants to be informed when the validity of the element contained in a
 * {@link ManageElementPanel} changes.
 *
 * @author Wayne Miller
 * @param <E> the type of element created by the {@code ManageElementPanel}
 * @since 2.0.1
 */
public interface ManageElementPanelObserver<E> {

    /**
     * This method is called when the validity of the element of the
     * {@link ManageElementPanel} being observed changes.
     */
    public void elementValidityChanged();

}    // ManageElementPanelObserver
