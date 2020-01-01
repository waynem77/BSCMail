/*
 * Copyright © 2014-2020 its authors.  See the file "AUTHORS" for details.
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
import io.github.waynem77.bscmail.gui.util.ManagedListControl;
import io.github.waynem77.bscmail.persistent.Matchable;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.Vector;
import java.util.stream.IntStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Abstract base class for frames used to manage a list of elements.
 *
 * @param <E> the type of element contained in the list
 * @since 2.0
 * @author Wayne Miller
 */
public abstract class ManageListFrame<E extends Matchable<String>> extends JFrame implements ManageElementPanelObserver<E> {

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
     * A text field that filters the list.
     */
    private final JTextField filterTextField;

    /**
     * A label that displays the number of matching items.
     */
    private final JLabel matchesLabel;

    /**
     * A button that navigates to the previous matching item.
     */
    private final JButton prevMatchButton;

    /**
     * A button that navigates to the next matching item.
     */
    private final JButton nextMatchButton;

    /**
     * The panel used to manipulate individual elements.
     */
    protected final ManageElementPanel<E> managerPanel;

    /**
     * A button that edits an element.
     */
    private final JButton editButton;

    /**
     * A button that creates a copy of an element.
     */
    private final JButton copyButton;

    /**
     * A button that deletes an element.
     */
    private final JButton deleteButton;

    /**
     * A button that adds an element.
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

        upButton = new JButton("<html><center>▲<br />Move</center></html>");
        downButton = new JButton("<html><center>▼<br />Move</center></html>");
        sortButton = new JButton("Sort");
        filterTextField = new JTextField();
        matchesLabel = new JLabel("Matches: 0");
        prevMatchButton = new JButton("<html><center>⯇<br />Match</center></html>");
        nextMatchButton = new JButton("<html><center>⯈<br />Match</center></html>");
        editButton = new JButton("Edit");
        copyButton = new JButton("Copy");
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
        filterTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
               filterChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                filterChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                filterChanged();
            }    // changedUpdate()
        });    // addDocumentListener()
        prevMatchButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                prevMatchButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        nextMatchButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                nextMatchButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        editButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                editButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        copyButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                copyButtonClicked(e);
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

        constraints.gridx++;
        JPanel movementPanel = new JPanel();
        movementPanel.setBorder(ComponentFactory.getStandardBorder());
        movementPanel.setLayout(new BoxLayout(movementPanel, BoxLayout.Y_AXIS));
        movementPanel.add(createSortingPanel());
        movementPanel.add(this.createFilterPanel());
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

    /**
     * Creates the sorting panel.
     *
     * @return the sorting panel
     */
    private JPanel createSortingPanel() {
        JPanel sortingPanel = new JPanel();
        sortingPanel.setBorder(BorderFactory.createTitledBorder("Sorting"));
        sortingPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        sortingPanel.add(upButton, constraints);

        ++constraints.gridx;
        sortingPanel.add(downButton, constraints);

        constraints.gridx = 0;
        ++constraints.gridy;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        sortingPanel.add(sortButton, constraints);

        return sortingPanel;
    }    // createSortingPanel()

    /**
     * Creates the filter panel.
     *
     * @return the filter panel
     */
    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filter"));
        filterPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        filterPanel.add(filterTextField, constraints);

        ++constraints.gridy;
        filterPanel.add(matchesLabel, constraints);

        constraints.gridwidth = 1;
        ++constraints.gridy;
        filterPanel.add(prevMatchButton, constraints);

        ++constraints.gridx;
        filterPanel.add(nextMatchButton, constraints);

        return filterPanel;
    }    // createFilterPanel()

    /**
     * Creates the action panel.
     *
     * @return the action panel
     */
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

    /**
     * Creates the command panel.
     *
     * @return the command panel
     */
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
        commandPanel.add(copyButton);

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
        boolean hasSelections = (listControl.getSelectedIndices().length > 0);
        boolean isAtTop = (listControl.getMinSelectionIndex() == 0);
        boolean isAtBottom = (listControl.getMaxSelectionIndex() == (listControl.getModel().getSize() - 1));
        boolean hasMatches = (listControl.getMatches() > 0);

        upButton.setEnabled(hasSelections && !isAtTop);         // a non-topmost element is selected
        downButton.setEnabled(hasSelections && !isAtBottom);    // a non-bottommost element is selected
        prevMatchButton.setEnabled(hasMatches);                 // there are filter matches
        nextMatchButton.setEnabled(hasMatches);                 // there are filter matches
        editButton.setEnabled(hasSelections);                   // an element is selected
        copyButton.setEnabled(hasSelections);                   // an element is selected
        deleteButton.setEnabled(hasSelections);                 // an element is selected
        addButton.setEnabled(true);                             // always
    }    // setButtonStates()

    /**
     * Event that fires when a selection is changed in the list box.
     *
     * @param event the event data
     */
    private void listSelectionValueChanged(ListSelectionEvent event) {
        E element = null;
        // If there is a single selection, load it.
        if (listControl.getSelectedIndices().length == 1) {
            element = listControl.getSelectedValue();
        }    // if
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
        int minSelectedIndex = listControl.getMinSelectionIndex();
        moveSelectedItems(minSelectedIndex - 1);
        assertInvariant();
    }    // upButtonClicked()

    /**
     * Event that fires when the down button is clicked.
     *
     * @param event the event data
     */
    private void downButtonClicked(ActionEvent event) {
        assertInvariant();

        int[] selectedIndices = listControl.getSelectedIndices();
        // Get the index one past that of the lowest-numbered unselected element higher than the lowest-numbered
        // selection.
        int newIndex = IntStream.rangeClosed(listControl.getMinSelectionIndex(), listControl.getMaxSelectionIndex() + 1)
                .filter(x -> !IntStream.of(selectedIndices).anyMatch(arrayValue -> arrayValue == x))
                .min()
                .getAsInt()
                + 1;
        moveSelectedItems(newIndex);
        assertInvariant();
    }    // downButtonClicked()

    /**
     * Shifts the selected items up or down according to the offset. The item
     * remains selected and visible after the shift.
     *
     * @param index the new index for the first item; must indicate a valid
     * index
     */
    private void moveSelectedItems(int index) {
        Vector<E> listData = listControl.getListData();
        assert ((index >= 0) && (index < listData.size()));
        listControl.moveSelectionsTo(index);
        listControl.ensureIndexIsVisible(index);
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
     * Event that fires when the text in the filter text field changes.
     */
    private void filterChanged() {
        assertInvariant();
        listControl.setFilter(filterTextField.getText());
        matchesLabel.setText("Matches: " + listControl.getMatches());
        setButtonStates();
        pack();
    }    // filterChanged()

    /**
     * Event that fires when the previous match button is clicked.
     *
     * @param event the event data
     */
    private void prevMatchButtonClicked(ActionEvent event) {
        assertInvariant();

        int selectedIndex = listControl.getSelectedIndex();
        SortedSet<Integer> matchingIndices = listControl.getMatchIndices();
        assert (! matchingIndices.isEmpty());
        SortedSet<Integer> headMatches = matchingIndices.headSet(selectedIndex);
        int nextIndex = headMatches.isEmpty() ? matchingIndices.last() : headMatches.last();
        listControl.setSelectedIndex(nextIndex);
        listControl.ensureIndexIsVisible(nextIndex);

        assertInvariant();
    }    // prevMatchButtonClicked()

    /**
     * Event that fires when the next match button is clicked.
     *
     * @param event the event data
     */
    private void nextMatchButtonClicked(ActionEvent event) {
        assertInvariant();

        int selectedIndex = listControl.getSelectedIndex();
        SortedSet<Integer> matchingIndices = listControl.getMatchIndices();
        assert (! matchingIndices.isEmpty());
        SortedSet<Integer> tailMatches = matchingIndices.tailSet(selectedIndex + 1);
        int nextIndex = tailMatches.isEmpty() ? matchingIndices.first() : tailMatches.first();
        listControl.setSelectedIndex(nextIndex);
        listControl.ensureIndexIsVisible(nextIndex);

        assertInvariant();
    }    // nextMatchButtonClicked()

    /**
     * Event that fires when the edit button is clicked.
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
            Vector<E> listData = listControl.getListData();
            int[] indices = listControl.getSelectedIndices();
            if (element != null) {
                for (int index : indices) {
                    listData.set(index, element);
                }    // for
            }    // if
            setListData(listData);
            listControl.setSelectedIndices(indices);
        }    // if

        assertInvariant();
    }    // editButtonClicked()

    /**
     * Event that fires when the copy button is clicked.
     *
     * @param event the event data
     */
    private void copyButtonClicked(ActionEvent event) {
        assertInvariant();

        Vector<E> listData = listControl.getListData();
        int[] selectedIndices = listControl.getSelectedIndices();
        int newIndex = selectedIndices[selectedIndices.length - 1] + 1;
        int firstCopyIndex = newIndex;
        for (int i : listControl.getSelectedIndices()) {
            listControl.setSelectedIndex(i);
            E element = managerPanel.createElement();
            if (element != null) {
                listData.add(newIndex, element);
                ++newIndex;
            }
        }
        setListData(listData);
        listControl.setSelectedIndices(IntStream.range(firstCopyIndex, newIndex).toArray());

        assertInvariant();
    }    // copyButtonClicked()

    /**
     * Event that fires when the delete button is clicked.
     *
     * @param event the event data
     */
    private void deleteButtonClicked(ActionEvent event) {
        assertInvariant();

        Vector<E> listData = listControl.getListData();
        int[] indices = listControl.getSelectedIndices();
        int numberOfItems = indices.length;
        int result = JOptionPane.showConfirmDialog(
                this,
                "Really delete " + numberOfItems + " " + ((numberOfItems == 1) ? "item" : "items") + "?",
                application.createWindowTitle("Delete " + elementName),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            for (int i = indices.length - 1; i >= 0; --i) {
                int index = indices[i];
                listData.remove(index);
            }
            setListData(listData);
        }

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
        JDialog dialog = pane.createDialog(this, application.createWindowTitle(action + " " + elementName));
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
        assert (sortButton != null);
        assert (this.isAncestorOf(sortButton));
        assert (filterTextField != null);
        assert (this.isAncestorOf(filterTextField));
        assert (matchesLabel != null);
        assert (this.isAncestorOf(matchesLabel));
        assert (prevMatchButton != null);
        assert (this.isAncestorOf(prevMatchButton));
        assert (nextMatchButton != null);
        assert (this.isAncestorOf(nextMatchButton));
        assert (managerPanel != null);
        assert (this.isAncestorOf(managerPanel));
        assert (editButton != null);
        assert (this.isAncestorOf(editButton));
        assert (copyButton != null);
        assert (this.isAncestorOf(copyButton));
        assert (deleteButton != null);
        assert (this.isAncestorOf(deleteButton));
        assert (addButton != null);
        assert (this.isAncestorOf(addButton));
        assert (elementComparator != null);
        assert (elementName != null);
    }    // assertInvariant()

}    // ManageListFrame
