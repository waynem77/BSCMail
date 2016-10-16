/*
 * Copyright © 2014–2015 Wayne Miller
 */

package bscmail.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import main.Application;


/**
 * Abstract base class for frames used to manage a list of elements.
 * 
 * @param <E> the type of element contained in the list
 * @since 2.0
 * @author Wayne Miller
 */
public abstract class ManageListFrame<E> extends JFrame implements ManageElementPanelObserver<E> {
    
    /**
     * The list box used to display elements.
     */
    private final JList<E> list;

    /**
     * A button that moves the selected item up in the list.
     */
    private final JButton upButton;
    
    /**
     * A button that moves the selected item down in the list.
     */
    private final JButton downButton;
    
    /**
     * The panel used to manipulate individual elements.
     */
    protected final ManageElementPanel<E> managerPanel;
    
    /**
     * A button that overwrites a shift with new values.
     */
    private final JButton saveButton;
    
    /**
     * A button that deletes a shift.
     */
    private final JButton deleteButton;
    
    /**
     * A button that adds a shift.
     */
    private final JButton addButton;

    /**
     * A copy of the data in {@link #list}.
     */
    private Vector<E> listData;
    
    /**
     * An element comparator.
     */
    private final Comparator<E> elementComparator;
    
    /**
     * Constructs a new manage list frame.
     * 
     * @param managerPanel the panel used to manipulate individual elements; may not be null
     * @param initialData the initial data for the list control; may not be null
     * @param elementComparator an element comparator
     * @throws NullPointerException if any parameter is null
     */
    public ManageListFrame(ManageElementPanel<E> managerPanel, java.util.List<E> initialData, Comparator<E> elementComparator) {
        if (managerPanel == null) {
            throw new NullPointerException("managerPanel may not be null");
        }    // if
        if (initialData == null) {
            throw new NullPointerException("initialData may not be null");
        }    // if
        if (elementComparator == null) {
            throw new NullPointerException("elementComparator may not be null");
        }    // if
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        listData = new Vector<>(initialData);
        list = new JList<>(listData);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override public void valueChanged(ListSelectionEvent e) {
                listSelectionValueChanged(e);
            }    // valueChanged()
        });    // addListSelectionListener()
        add(new JScrollPane(list));
        
        final int STRUT_WIDTH = 10;
        final int HALF_STRUT_WIDTH = STRUT_WIDTH / 2;
        add(Box.createHorizontalStrut(HALF_STRUT_WIDTH));

        JPanel movementPanel = new JPanel();
        add(movementPanel);
        movementPanel.setLayout(new BoxLayout(movementPanel, BoxLayout.Y_AXIS));
        upButton = new JButton("▲");
        upButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                upButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        movementPanel.add(upButton);
        downButton = new JButton("▼");
        downButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                downButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        movementPanel.add(downButton);
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                sortButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        movementPanel.add(sortButton);

        add(Box.createHorizontalStrut(STRUT_WIDTH));
        add(new JSeparator(SwingConstants.VERTICAL));
        add(Box.createHorizontalStrut(STRUT_WIDTH));
        
        JPanel actionPanel = new JPanel();
        add(actionPanel);
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        
        this.managerPanel = managerPanel;
        actionPanel.add(this.managerPanel);
        managerPanel.addObserver(this);
        
        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new FlowLayout());
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                saveButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        commandPanel.add(saveButton);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                deleteButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        commandPanel.add(deleteButton);
        
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                addButtonClicked(e);
            }    // actionPerformed()
        });    // addActionListener
        commandPanel.add(addButton);
        actionPanel.add(commandPanel);
        
        pack();
        setButtonStates();

        this.elementComparator = elementComparator;
        
        assertInvariant();
    }    // ManageListFrame()

    /**
     * This method is called when the validity of the element of the
     * {@link ManageElementPanel} being observed changes.
     */
    @Override
    public void elementValidityChanged() {
        setButtonStates();
    }    // elementValidityChanged()
    
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
     * runs any hook defined in {@link #saveListData(java.util.List)}.
     * 
     * @param data the new list data; may not be null
     * @throws IOException if an I/O error occurs
     */
    private void setListData(Vector<E> data) throws IOException {
        assert (data != null);
        list.setListData(data);
        setButtonStates();
        setListDataHook(data);
    }    // setListData()
    
    /**
     * Enables or disables buttons depending on the state of the list box.
     */
    private void setButtonStates() {
        boolean hasSelection = (list.getSelectedIndex() != -1);
        boolean elementIsValid = managerPanel.elementIsValid();
        boolean isAtTop = (list.getSelectedIndex() == 0);
        boolean isAtBottom = (list.getSelectedIndex() == (list.getModel().getSize() - 1));

        upButton.setEnabled(hasSelection && !isAtTop);
        downButton.setEnabled(hasSelection && !isAtBottom);
        saveButton.setEnabled(hasSelection && elementIsValid);
        addButton.setEnabled(elementIsValid);
        deleteButton.setEnabled(hasSelection);
    }    // setButtonStates()
        
    /**
     * Event that fires when a selection is changed in the list box.
     * 
     * @param event the event data
     */
    private void listSelectionValueChanged(ListSelectionEvent event) {
        E element = list.getSelectedValue();
        managerPanel.loadElement(element);
        setButtonStates();
    }    // listSelectionValueChanged(ListSelectionEvent e)
    
    /**
     * Event that fires when the up button is clicked.
     * 
     * @param event the event data
     */
    private void upButtonClicked(ActionEvent event) {
        assertInvariant();
        int index = list.getSelectedIndex();
        assert (index > 0);
        int swapIndex = index - 1;
        Collections.swap(listData, swapIndex, index);
        try {
            setListData(listData);
            list.setSelectedIndex(swapIndex);
        } catch (IOException e) {    // try
            unableToSave(e);
        }    // catch
        assertInvariant();
    }    // upButtonClicked()
    
    /**
     * Event that fires when the down button is clicked.
     * 
     * @param event the event data
     */
    private void downButtonClicked(ActionEvent event) {
        assertInvariant();
        int index = list.getSelectedIndex();
        assert ((index >= 0) && (index < listData.size() - 1));
        int swapIndex = index + 1;
        Collections.swap(listData, swapIndex, index);
        try {
            setListData(listData);
            list.setSelectedIndex(swapIndex);
        } catch (IOException e) {    // try
            unableToSave(e);
        }    // catch
        assertInvariant();
    }    // downButtonClicked()
    
    /**
     * Event that fires when the sort button is clicked.
     * 
     * @param event the event data
     */
    private void sortButtonClicked(ActionEvent event) {
        assertInvariant();
        Collections.sort(listData, elementComparator);
        try {
            setListData(listData);
        } catch (IOException e) {    // try
            unableToSave(e);
        }    // catch
        assertInvariant();
    }    // sortButtonClicked()
    
    /**
     * Event that fires when the save button is clicked.
     * 
     * @param event the event data
     */
    private void saveButtonClicked(ActionEvent event) {
        int index = list.getSelectedIndex();
        assertInvariant();
        E element = managerPanel.createElement();
        assert ((index >= 0) && (index < listData.size()));
        listData.set(index, element);
        try {
            setListData(listData);
        } catch (IOException e) {    // try
            unableToSave(e);
        }    // catch
        assertInvariant();
    }    // saveButtonClicked()
    
    /**
     * Event that fires when the delete button is clicked.
     * 
     * @param event the event data
     */
    private void deleteButtonClicked(ActionEvent event) {
        assertInvariant();
        int index = list.getSelectedIndex();
        assert (index > -1);
        listData.remove(index);
        try {
            setListData(listData);
        } catch (IOException e) {    // try
            unableToSave(e);
        }    // catch
        assertInvariant();
    }    // deleteButtonClicked()
    
    /**
     * Event that fires when the add button is clicked.
     * 
     * @param event the event data
     */
    private void addButtonClicked(ActionEvent event) {
        assertInvariant();
        E element = managerPanel.createElement();
        listData.add(element);
        try {
            setListData(listData);
        } catch (IOException e) {    // try
            unableToSave(e);
        }    // catch
        assertInvariant();
    }    // addButtonClicked()
    
    /**
     * Displays a message box indicating that the frame is unable to save data.
     */
    private void unableToSave(Exception e) {
        Application.showErrorDialog(this, "Unable to save data", e);
    }    // unableToSave()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (list != null);
        assert (this.isAncestorOf(list));
        assert (upButton != null);
        assert (this.isAncestorOf(upButton));
        assert (downButton != null);
        assert (this.isAncestorOf(downButton));
        assert (managerPanel != null);
        assert (this.isAncestorOf(managerPanel));
        assert (saveButton != null);
        assert (this.isAncestorOf(saveButton));
        assert (deleteButton != null);
        assert (this.isAncestorOf(deleteButton));
        assert (addButton != null);
        assert (this.isAncestorOf(addButton));
        assert (listData != null);
        assert (listDataEquals(list, listData));
        assert (elementComparator != null);
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
