/*
 * Copyright © 2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.gui.util;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

/**
 * A list control with special properties, appropriate for a
 * {@link io.github.waynem77.bscmail.gui.ManageListFrame}. A managed list
 * control provides the following functionality beyond that of a normal
 * {@link JList} control:
 * <ul>
 * <li>A method that allows access to the underlying data as a list for easy manipulation.</li>
 * </ul>
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @param <E> the type of element contained in the list
 * @since 4.0
 */
public class ManagedListControl<E> extends JList<E> {

    /**
     * The drag and drop handler for {@link ManagedListControl}. The handler
     * allows the list control to reorder its elements by dragging with the
     * mouse. It does not support drag and drop between distinct lists or other
     * elements.
     * <p>
     * DragDropHandler is intended to be a singleton used by all instances of
     * ManagedListControl. The reason is that Swing's basic drag and drop
     * functionality offers no access to the source component. In order to
     * restrict drag and drop within a single component, we need to have all
     * instances of ManagedListControl use the same transfer handler, and have
     * that handler store the address of the source component.
     */
    private static class DragDropHandler extends TransferHandler {

        /**
         * The source control of a drag event.
         */
        private ManagedListControl dragSource = null;

        /**
         * Returns the type of transfer actions supported by the source.
         * @param component the component holding the data to be transferred
         * @return TransferHandler.MOVE
         */
        @Override
        public int getSourceActions(JComponent component) {
            return TransferHandler.MOVE;
        }

        /**
         * Creates a Transferable to use as the source for a data transfer.
         * Returns the representation of the data to be transferred.
         *
         * @param component the component holding the data to be transferred
         * @return the representation of the data to be transferred
         */
        @Override
        protected Transferable createTransferable(JComponent component) {
            assert (component instanceof ManagedListControl);
            ManagedListControl list = (ManagedListControl)component;
            StringSelection transferable = new StringSelection(Integer.toString(list.getSelectedIndex()));
            return transferable;
        }

        /**
         * Causes the Swing drag support to be initiated. This method stores a
         * reference to the source component for later comparison.
         *
         * @param component the component holding the data to be transferred
         * @param event the event that triggered the transfer
         * @param action the transfer action initially requested
         */
        @Override
        public void exportAsDrag(JComponent component, InputEvent event, int action) {
            assert (component instanceof ManagedListControl);
            dragSource = (ManagedListControl)component;
            super.exportAsDrag(component, event, action);
        }

        /**
         * Invoked after data has been exported. This method clears the
         * reference to the source component.
         *
         * @param component the component that was the source of the data
         * @param transferable the data that was transferred
         * @param action the actual action that was performed
         */
        @Override
        protected void exportDone(JComponent component, Transferable transferable, int action) {
            dragSource = null;
        }

        /**
         * This method is called repeatedly during a drag and drop operation to
         * allow the developer to configure properties of, and to return the
         * acceptability of transfers; with a return value of true indicating
         * that the transfer represented by the given TransferSupport (which
         * contains all of the details of the transfer) is acceptable at the
         * current time, and a value of false rejecting the transfer.
         *
         * @param support the object containing the details of the transfer, not
         * null
         * @return true if the target component is also the source component and
         * the drop location is valid
         */
        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            assert (support.getDropLocation() instanceof JList.DropLocation);
            JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
            return (support.getComponent() == dragSource) && (dl.getIndex() != -1);
        }

        /**
         * Causes a transfer to occur from a drag and drop operation. This
         * method moves the dragged item in the associated JList to the location
         * in which it was dropped.
         *
         * @param support the object containing the details of the transfer
         * @return true if the data was inserted into the component, false
         * otherwise
         */
        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            assert (support.getComponent() instanceof ManagedListControl);
            ManagedListControl dropTarget = (ManagedListControl)support.getComponent();
            Transferable transferable = support.getTransferable();
            String indexString;
            try {
                indexString = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception e) {
                return false;
            }

            int dragFromIndex = Integer.parseInt(indexString);
            JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
            int dropToIndex = dl.getIndex();

            Vector listData = dropTarget.getListData();
            listData.add(dropToIndex, listData.get(dragFromIndex));
            if (dragFromIndex > dropToIndex) {
                ++dragFromIndex;
            }    // if
            listData.remove(dragFromIndex);
            dropTarget.setListData(listData);
            dropTarget.notifyDragAndDropListeners();

            return true;
        }

    }    // DragDropHandler

    /**
     * The drag and drop handler for all instances of ManagedListControl.
     */
    private static final DragDropHandler dragDropHandler = new DragDropHandler();

    /**
     * The drag and drop listeners.
     */
    private final List<DragAndDropListener> dragAndDropListeners;

    /**
     * Constructs a new ManagedListControl with the given data.
     * @param listData the initial data; may not be null nor contain null
     * @throws NullPointerException if listData is null or contains null
     */
    public ManagedListControl(Vector<E> listData) {
        if ((listData == null) || listData.contains(null)) {
            throw new NullPointerException("listData may not be null nor contain null");
        }

        setListData(listData);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setDragEnabled(true);
        setDropMode(DropMode.INSERT);
        setTransferHandler(dragDropHandler);

        dragAndDropListeners = new ArrayList<>();

        assertInvariant();
    }   // ManagedListControl()

    /**
     * Returns the data within the managed list control as a vector.
     * @return the data within the managed list
     */
    public Vector<E> getListData() {
        assertInvariant();
        ListModel<E> listModel = getModel();
        Vector<E> listData = new Vector<>();
        for (int i = 0; i < listModel.getSize(); ++i) {
            listData.add(listModel.getElementAt(i));
        }    // for
        return listData;
    }    // getListData()

    /**
     * Adds a drag and drop listener. The listeners will be called whenever the
     * list experiences a drag and drop event.
     *
     * @param listener the new listener; may not be null
     * @throws NullPointerException when listener is null
     */
    public void addDragAndDropListener(DragAndDropListener listener) {
        assertInvariant();
        if (listener == null) {
            throw new NullPointerException("listener may not be null");
        }    // if

        dragAndDropListeners.add(listener);
        assertInvariant();
    }    // addDragAndDropListener()

    /**
     * Notifies all drag and drop listeners.
     */
    public void notifyDragAndDropListeners() {
        assertInvariant();
        for (DragAndDropListener listener : dragAndDropListeners) {
            listener.dragAndDropPerformed(this);
        }    // for
    }    // notifyDragAndDropListeners()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (dragDropHandler != null);
        assert (dragAndDropListeners != null);
        assert (!dragAndDropListeners.contains(null));
    }    // assertInvariant()

}    // ManagedListControl
