/*
 * Copyright © 2014-2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.gui;

import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.gui.util.ComponentFactory;
import io.github.waynem77.bscmail.gui.util.DragAndDropListener;
import io.github.waynem77.bscmail.gui.util.ManagedListControl;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Abstract base class for frames used to manage a list of elements.
 *
 * @param <E> the type of element contained in the list
 * @since 2.0
 * @author Wayne Miller
 */
public abstract class ManageListFrame<E> extends JFrame implements ManageElementPanelObserver<E> {

    /**
     * The calling application.
     */
    final Application application;

    /**
     * The list box used to display elements.
     */
    private final ManagedListControl<E> listControl;

    /**
     * A button that moves the selected item up in the list.
     */
    private final JButton upButton;

    /**
     * A button that moves the selected item down in the list.
     */
    private final JButton downButton;

    /**
     * A button that sorts the list.
     */
    private final JButton sortButton;

    /**
     * The panel used to manipulate individual elements.
     */
    protected final ManageElementPanel<E> managerPanel;

    /**
     * A button that overwrites a shift with new values.
     */
    private final JButton editButton;

    /**
     * A button that deletes a shift.
     */
    private final JButton deleteButton;

    /**
     * A button that adds a shift.
     */
    private final JButton addButton;

    /**
     * An element comparator.
     */
    private final Comparator<E> elementComparator;

    /**
     * The name of the elementType.
     */
    private final String elementName;

    /**
     * Constructs a new manage list frame.
     *
     * @param application the calling application; may not be null
     * @param managerPanel the panel used to manipulate individual elements; may not be null
     * @param initialData the initial data for the list control; may not be null
     * @param elementComparator an element comparator
     * @param elementName the name of the element type
     * @throws NullPointerException if any parameter is null
     */
    public ManageListFrame(Application application, ManageElementPanel<E> managerPanel, java.util.List<E> initialData, Comparator<E> elementComparator, String elementName) {
        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if
        if (managerPanel == null) {
            throw new NullPointerException("managerPanel may not be null");
        }    // if
        if (initialData == null) {
            throw new NullPointerException("initialData may not be null");
        }    // if
        if (elementComparator == null) {
            throw new NullPointerException("elementComparator may not be null");
        }    // if
        if (elementName == null) {
            throw new NullPointerException("elementName may not be null");
        }    // if

        /*
         * Set private variables
         */

        this.application = application;
        this.managerPanel = managerPanel;
        managerPanel.addObserver(this);
        this.elementComparator = elementComparator;
        this.elementName = elementName;

        /*
         * Create GUI controls
         */

        listControl = new ManagedListControl<>(new Vector<>(initialData));

        upButton = new JButton("▲");
        downButton = new JButton("▼");
        sortButton = new JButton("Sort");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        addButton = new JButton("Add");

        /*
         * Set event handlers for controls
         */

        listControl.addListSelectionListener(new ListSelectionListener() {
            @Override public void valueChanged(ListSelectionEvent e) {
                listSelectionValueChanged(e);
            }    // valueChanged()
        });    // addListSelectionListener()
        listControl.addDragAndDropListener(new DragAndDropListener() {
            @Override public void dragAndDropPerformed(Component c) {
                listDataChanged(c);
            }    // valueChanged()
        });    // addListSelectionListener()
        upButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                upButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        downButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                downButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        sortButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                sortButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        editButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                editButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        deleteButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                deleteButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        addButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                addButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener

        /*
         * Create GUI
         */

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.0;
        constraints.weighty = 1.0;
        final int STRUT_WIDTH = 10;
        final int HALF_STRUT_WIDTH = STRUT_WIDTH / 2;

        JScrollPane scrollPane = new JScrollPane(listControl);
        scrollPane.setBorder(ComponentFactory.getStandardBorder());
        add(scrollPane, constraints);

        constraints.gridx++;
        add(Box.createHorizontalStrut(HALF_STRUT_WIDTH), constraints);

        JPanel movementPanel = createMovementPanel();
        movementPanel.setBorder(ComponentFactory.getStandardBorder());
        constraints.gridx++;
        add(movementPanel, constraints);

        constraints.gridx++;
        add(Box.createHorizontalStrut(STRUT_WIDTH), constraints);

        constraints.gridx++;
        add(new JSeparator(SwingConstants.VERTICAL), constraints);

        constraints.gridx++;
        add(Box.createHorizontalStrut(STRUT_WIDTH), constraints);

        JPanel actionPanel = createActionPanel();
        actionPanel.setBorder(ComponentFactory.getStandardBorder());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.gridx++;
        add(actionPanel, constraints);

        pack();
        setMinimumSize(getPreferredSize());

        setButtonStates();
        assertInvariant();
    }    // ManageListFrame()

    private JPanel createMovementPanel() {
        JPanel movementPanel = new JPanel();
        movementPanel.setLayout(new BoxLayout(movementPanel, BoxLayout.Y_AXIS));
        movementPanel.add(upButton);
        movementPanel.add(downButton);
        movementPanel.add(sortButton);

        return movementPanel;
    }    // createMovementPanel()

    private JPanel createActionPanel() {
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;

        managerPanel.setEditable(false);
        actionPanel.add(managerPanel, constraints);
        constraints.gridy++;

        JPanel commandPanel = createCommandPanel();
        constraints.weighty = 0.0;
        actionPanel.add(commandPanel, constraints);
        constraints.gridy++;

        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.weighty = 1.0;
        Component glue = Box.createVerticalGlue();
        actionPanel.add(glue, constraints);

        return actionPanel;
    }    // createActionPanel()

    private JPanel createCommandPanel() {
        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.0;
        constraints.weighty = 1.0;

        commandPanel.add(editButton, constraints);

        constraints.gridx++;
        commandPanel.add(deleteButton);

        constraints.gridx++;
        commandPanel.add(addButton);

        return commandPanel;
    }    // createCommandPanel()

    /**
     * This method is called when the validity of the element of the
     * {@link ManageElementPanel} being observed changes.
     */
    @Override
    public void elementValidityChanged() {
        setButtonStates();
        pack();
    }    // elementValidityChanged()

    /**
     * Returns the calling application.
     *
     * @return the calling application
     */
    protected final Application getApplication() {
        assertInvariant();
        return application;
    }    // getApplication()

    /**
     * A hook run whenever the data in the list control changes. The default
     * implementation of this method does nothing. Any subclasses of
     * {@link ManageListFrame} that wish to store the data externally may do so
     * by overriding this method.
     *
     * @param elements the elements to save
     * @throws IOException if an I/O error occurs
     */
    protected void setListDataHook(java.util.List<E> elements) throws IOException {
    }    // setListDataHook()

    /**
     * Sets the list data in {@link #list} to the given data.  This method also
     * enables or disables buttons (by calling {@link #setButtonStates()} and
     * runs any hook defined with {@link #setListDataHook(java.util.List)}.
     *
     * @param data the new list data; may not be null
     * @throws IOException if an I/O error occurs
     * @see #updateListData(java.util.Vector)
     */
    private void setListData(Vector<E> data) {
        assert (data != null);
        try {
            setListDataHook(data);
            updateListData(data);
        } catch (Exception e) {    // try
            unableToSave(e);
        }    // catch
    }    // setListData()

    /**
     * Sets the listControl data in {@link #listControl} to the given data. This
     * method also enables or disables buttons (by calling
     * {@link #setButtonStates()}, but does not run any hook defined with
     * {@link #setListDataHook(java.util.List)}.
     * <p>
     * This method is typically used to update the list control after data has
     * been changed in the application.
     *
     * @param data the new list data; may not be null
     * @throws IOException if an I/O error occurs
     * @see #setListData(java.util.Vector)
     */
    protected void updateListData(Vector<E> data) throws IOException {
        assert (data != null);
        listControl.setListData(data);
        setButtonStates();
    }

    /**
     * Enables or disables buttons depending on the state of the list box.
     */
    private void setButtonStates() {
        boolean hasSelection = (listControl.getSelectedIndex() != -1);
        boolean isAtTop = (listControl.getSelectedIndex() == 0);
        boolean isAtBottom = (listControl.getSelectedIndex() == (listControl.getModel().getSize() - 1));

        upButton.setEnabled(hasSelection && !isAtTop);         // a non-topmost element is selected
        downButton.setEnabled(hasSelection && !isAtBottom);    // a non-bottommost element is selected
        editButton.setEnabled(hasSelection);                   // an element is selected
        addButton.setEnabled(true);                            // always
        deleteButton.setEnabled(hasSelection);                 // an element is selected
    }    // setButtonStates()

    /**
     * Event that fires when a selection is changed in the list box.
     *
     * @param event the event data
     */
    private void listSelectionValueChanged(ListSelectionEvent event) {
        E element = listControl.getSelectedValue();
        managerPanel.loadElement(element);
        setButtonStates();
        pack();
    }    // listSelectionValueChanged(ListSelectionEvent e)

    /**
     * Event that fires when the list data has changed via drag and drop.
     *
     * @param c the list component
     * @since 4.0
     */
    private void listDataChanged(Component c) {
        Vector<E> listData = listControl.getListData();
        setListData(listData);
    }    // listDataChanged()

    /**
     * Event that fires when the up button is clicked.
     *
     * @param event the event data
     */
    private void upButtonClicked(ActionEvent event) {
        assertInvariant();
        int offset = -1;
        moveSelectedItem(offset);
        assertInvariant();
    }    // upButtonClicked()

    /**
     * Event that fires when the down button is clicked.
     *
     * @param event the event data
     */
    private void downButtonClicked(ActionEvent event) {
        assertInvariant();
        int offset = 1;
        moveSelectedItem(offset);
        assertInvariant();
    }    // downButtonClicked()

    /**
     * Shifts the selected item up or down according to the offset. The item
     * remains selected and visible after the shift.
     *
     * @param offset the amount to shift; positive values indicate downward
     * motion, negative values indicate upward motion; must indicate a valid
     * index
     */
    private void moveSelectedItem(int offset) {
        assert (offset != 0);
        int index = listControl.getSelectedIndex();
        Vector<E> listData = listControl.getListData();
        assert ((index >= 0) && (index < listData.size() - 1));
        int swapIndex = index + offset;
        assert ((swapIndex >= 0) && (swapIndex < listData.size() - 1));
        Collections.swap(listData, swapIndex, index);
        setListData(listData);
        listControl.setSelectedIndex(swapIndex);
        listControl.ensureIndexIsVisible(swapIndex);
    }    // swapItems

    /**
     * Event that fires when the sort button is clicked.
     *
     * @param event the event data
     */
    private void sortButtonClicked(ActionEvent event) {
        assertInvariant();
        Vector<E> listData = listControl.getListData();
        Collections.sort(listData, elementComparator);
        setListData(listData);
        assertInvariant();
    }    // sortButtonClicked()

    /**
     * Event that fires when the save button is clicked.
     *
     * @param event the event data
     */
    private void editButtonClicked(ActionEvent event) {
        assertInvariant();

        ManageElementPanel<E> editPanel = managerPanel.createCopy();
        editPanel.loadElement(managerPanel.createElement());
        boolean elementWasEdited = showEditDialog(editPanel, "Edit");
        if (elementWasEdited) {
            E element = editPanel.createElement();
            int index = listControl.getSelectedIndex();
            Vector<E> listData = listControl.getListData();
            assert ((index >= 0) && (index < listData.size()));
            if (element != null) {
                listData.set(index, element);
            }
            setListData(listData);
            listControl.setSelectedIndex(index);
        }    // if

        assertInvariant();
    }    // editButtonClicked()

    /**
     * Event that fires when the delete button is clicked.
     *
     * @param event the event data
     */
    private void deleteButtonClicked(ActionEvent event) {
        assertInvariant();
        int index = listControl.getSelectedIndex();
        assert (index > -1);
        Vector<E> listData = listControl.getListData();
        listData.remove(index);
        setListData(listData);
        assertInvariant();
    }    // deleteButtonClicked()

    /**
     * Event that fires when the add button is clicked.
     *
     * @param event the event data
     */
    private void addButtonClicked(ActionEvent event) {
        assertInvariant();

        ManageElementPanel<E> editPanel = managerPanel.createCopy();
        boolean elementWasEdited = showEditDialog(editPanel, "Add");
        if (elementWasEdited) {
            E element = editPanel.createElement();
            Vector<E> listData = listControl.getListData();
            if (element != null) {
                listData.add(element);
            }
            setListData(listData);
           listControl.setSelectedIndex(listData.size() - 1);
        }    // if

        assertInvariant();
    }    // addButtonClicked()

    /**
     * Creates and shows a modal {@link JDialog} containing the given manage
     * element panel, with OK and Cancel buttons. This method waits for the
     * dialog to be dismissed and returns true if the OK button was pressed.
     *
     * @param editPanel the manage element panel; may not be null
     * @param action a string describing the dialog's action, used in the window
     * title; may not be null
     * @return true if the OK button was pressed; false otherwise
     * @since 4.0
     */
    private boolean showEditDialog(ManageElementPanel<E> editPanel, String action) {
        assert (editPanel != null);
        assert (action != null);
        editPanel.setEditable(true);
        JOptionPane pane = new JOptionPane(editPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = pane.createDialog(this, application.getApplicationName() + " - " + action + " " + elementName);
        dialog.show();
        Object selectedValue = pane.getValue();
        return (selectedValue != null) && selectedValue.equals(JOptionPane.OK_OPTION);
    }    // createEditDialog()

    /**
     * Displays a message box indicating that the frame is unable to save data.
     *
     * @param e the exception
     */
    private void unableToSave(Exception e) {
        application.showErrorDialog(this, "Unable to save data: " + e.getMessage(), e);
    }    // unableToSave()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (application != null);
        assert (listControl != null);
        assert (this.isAncestorOf(listControl));
        assert (upButton != null);
        assert (this.isAncestorOf(upButton));
        assert (downButton != null);
        assert (this.isAncestorOf(downButton));
        assert (managerPanel != null);
        assert (this.isAncestorOf(managerPanel));
        assert (editButton != null);
        assert (this.isAncestorOf(editButton));
        assert (deleteButton != null);
        assert (this.isAncestorOf(deleteButton));
        assert (addButton != null);
        assert (this.isAncestorOf(addButton));
        assert (elementComparator != null);
        assert (elementName != null);
    }    // assertInvariant()

    /**
     * Returns true if the list data in the given list control exactly matches
     * the given list.
     *
     * @param list the list control; may not be null
     * @param listData the expected list data; may not be null
     * @return true if the list data in {@code list} exactly matches
     * {@code listData}
     */
    private boolean listDataEquals(JList list, java.util.List listData) {
        assert (list != null);
        assert (listData != null);
        ListModel model = list.getModel();
        java.util.List actualData = new LinkedList();
        for (int i = 0; i < model.getSize(); ++i) {
            actualData.add(model.getElementAt(i));
        }    // for
        return actualData.equals(listData);
    }    // listDataEquals()

}    // ManageListFrame
