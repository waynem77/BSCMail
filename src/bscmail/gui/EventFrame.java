/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui;

import bscmail.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.*;
import java.util.*;
import javax.swing.*;
import main.*;

/**
 * A graphical interface for an {@link Event}.  
 * @author Wayne Miller
 */
public class EventFrame extends JFrame implements ManagersObserver, ShiftsObserver, VolunteersObserver,
                                                  EventPropertyObserver {

    /* Private Classes */
    
    /**
     * Wrapper for a person implementation.  When displayed in a combo box, the
     * container displays the name of the person, or "(open)" if the person is
     * null.
     * 
     * @param <E> the person implementation
     */
    private class PersonContainer<E extends Person> {
        /**
         * The person being wrapped.
         */
        private final E person;

        /**
         * Constructs a new person container.
         * 
         * @param person the person wrapped in the container
         */
        public PersonContainer(E person) {
            this.person = person;
        }    // PersonContainer
        
        /**
         * Returns the person wrapped in the container.
         * 
         * @return the person wrapped in the container
         */
        public E getPerson() {
            return person;
        }    // getPerson()
        
        /**
         * Returns the name of the person wrapped in the container, or "(open)"
         * if the person is null.
         * 
         * @return name of the person wrapped in the container, or "(open)" if
         * the person is null
         */
        @Override
        public String toString() {
            return (person == null) ? "(open)" : person.getName();
        }    // toString()
    }    // PersonContainer

    /**
     * A text field that allows the user to select event properties. This class extends
     * {@code JTextField}.
     */
    private class EventPropertiesTextField extends JTextField {
        /**
         * The event property corresponding to this text field.
         */
        private final EventPropertyList eventProperty;

        /**
         * Constructs a new event Property text field.
         *
         * @param eventProperty the event property corresponding to this text field; may not be
         * null
         */
        public EventPropertiesTextField(EventPropertyList eventProperty) {
            super(eventProperty.getDefaultValue());
            assert (eventProperty != null);
            this.eventProperty = eventProperty;
            //new JTextField(eventProperty.getDefaultValue());
            //add();

        }    // EventPropertiesTextField()

        /**
         * Returns the event property corresponding to this text field.
         *
         * @return the event porperty corresponding to this text field
         */
        public EventPropertyList getEventProperty() {
            return eventProperty;
        }    // getEventProperty()

        /**
         * Returns the value entered by the user.
         *
         * @return the value entered by the user
         */
        public String getValue() {
            return getText();
        }    // getVolunteer()

    }    // EventPropertiesTextField

    /**
     * A combo box that allows the user to select managers.  This class extends
     * {@code JComboBox} to easily convert person containers to managers.
     */
    private class ManagerComboBox extends JComboBox<PersonContainer<Manager>> {
        /**
         * Constructs a new manager combo box.
         * 
         * @param managers the managers used to populate the combo box; may not
         * contain any null elements
         */
        public ManagerComboBox(List<Manager> managers) {
            setModel(managers);
        }    // ManagerComboBox()
        
        /**
         * Returns the manager selected by the user.
         * 
         * @return the manager selected by the user
         */
        public Manager getManager() {
            PersonContainer<Manager> container = (PersonContainer<Manager>)getSelectedItem();
            return container.getPerson();
        }    // getManager()
        
        /**
         * Sets the list of managers.
         * 
         * @param managers the managers used to populate the combo box; may not
         * contain any null elements
         */
        public final void setModel(List<Manager> managers) {
            assert ((managers == null) || (!managers.contains(null)));
            removeAllItems();
            addItem(new PersonContainer<Manager>(null));
            if (managers != null) {
                for (Manager manager : managers) {
                    addItem(new PersonContainer<>(manager));
                }    // for
            }    // if
        }    // setModel()
    }    // ManagerComboBox
    
    /**
     * A combo box that contains a shift and allows the user to select
     * volunteers. This class extends {@code JComboBox} to contain extra data
     * (the shift) and easily convert person containers to volunteers.
     */
    private class ShiftComboBox extends JComboBox<PersonContainer<Volunteer>> {
        /**
         * The shift corresponding to this combo box.
         */
        private final Shift shift;
        
        /**
         * Constructs a new shift combo box.
         * 
         * @param shift the shift corresponding to this combo box; may not be
         * null
         * @param volunteers the volunteers used to populate the combo box; may
         * not be null, nor contain any null elements
         */
        public ShiftComboBox(Shift shift, List<Volunteer> volunteers) {
            assert (shift != null);
            assert (volunteers != null);
            this.shift = shift;
            setModel(volunteers);
        }    // ShiftComboBox()

        /**
         * Returns the shift corresponding to this combo box.
         * 
         * @return the shift corresponding to this combo box
         */
        public Shift getShift() {
            return shift;
        }    // getShift()
        
        /**
         * Returns the volunteer selected by the user.
         * 
         * @return the volunteer selected by the user
         */
        public Volunteer getVolunteer() {
            PersonContainer<Volunteer> container = (PersonContainer<Volunteer>)getSelectedItem();
            return container.getPerson();
        }    // getVolunteer()
        
        /**
         * Sets the list of volunteers.
         *
         * @param volunteers the volunteers used to populate the combo box; may
         * not contain any null elements
         */
        public final void setModel(List<Volunteer> volunteers) {
            assert ((volunteers == null) || (!volunteers.contains(null)));
            removeAllItems();
            addItem(new PersonContainer<Volunteer>(null));
            if (volunteers != null) {
                for (Volunteer volunteer : volunteers) {
                    addItem(new PersonContainer<>(volunteer));
                }    // for
            }    // if
        }    // setModel()
    }    // ShiftComboBox
    
    /**
     * Represents a paired label and component.
     * 
     * @param <E> the type of component paired
     */
    private class LabeledComponent<E extends Component> {
        
        /**
         * The label.
         */
        public final JLabel label;
        
        /**
         * The component.
         */
        public final E component;
        
        /**
         * Constructs a new labeled component.
         * 
         * @param label the text of the label; may not be null
         * @param component the component; may not be null
         */
        public LabeledComponent(String label, E component) {
            assert (label != null);
            assert (component != null);
            this.label = new JLabel(label);
            this.component = component;
        }    // LabeledComponent()
    }    // LabeledComponent
    
    /* Swing controls */

    /**
     * The panel that contains the "standard" event controls.
     */
    private final JPanel standardPanel;

    /**
     * The panel that contains the dynamic event properties.
     */
    private final JPanel eventPropertiesPanel;

    /**
     * The panel that contains the shifts.
     */
    private final JPanel shiftsPanel;

    /**
     * Date selector control.
     */
    private final JSpinner dateControl;

    /**
     * Manager dropdown control.
     */
    private final ManagerComboBox managerControl;
    
    /**
     * Assistant manager dropdown control.
     */
    private final ManagerComboBox assistantManagerControl;
    
    /**
     * Shift dropdown controls.
     */
    private final List<LabeledComponent<ShiftComboBox>> shiftControls;
    
    /**
     * Other private variables.
     */
    
    private final List<Volunteer> volunteers;

    /**
     * Event Property textField controls.
     */
    private final List<LabeledComponent<EventPropertiesTextField>> eventPropertyControls;
    
    /*
     * Public API
     */
    
    /**
     * Constructs a new EventFrame.
     * 
     */
    public EventFrame() {
        List<EventPropertyList> eventProperties = Application.getEventProperties();
        List<Shift> shifts = Application.getShifts();
        List<Manager> managers = Application.getManagers();
        volunteers = Application.getVolunteers();
        
        setTitle(Application.getApplicationName() + " - Event Setup");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        standardPanel = new JPanel();
        add(standardPanel);
        eventPropertiesPanel = new JPanel();
        add(eventPropertiesPanel);
        shiftsPanel = new JPanel();
        add(shiftsPanel);

        standardPanel.setLayout(new GridLayout(0, 2));
        standardPanel.add(new JLabel("Date:"));
        dateControl = new JSpinner(new SpinnerDateModel());
        SimpleDateFormat dateFormat = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM);
        dateControl.setEditor(new JSpinner.DateEditor(dateControl, dateFormat.toPattern()));
        standardPanel.add(dateControl);
        standardPanel.add(new JLabel("Manger:"));
        managerControl = new ManagerComboBox(managers);
        standardPanel.add(managerControl);
        standardPanel.add(new JLabel("Assistant manger:"));
        assistantManagerControl = new ManagerComboBox(managers);
        standardPanel.add(assistantManagerControl);

        eventPropertiesPanel.setLayout(new GridLayout(0, 2));
        eventPropertyControls = new LinkedList<>();
        setEventProperties(eventProperties);

        shiftsPanel.setLayout(new GridLayout(0, 2));
        shiftControls = new LinkedList<>();
        setShifts(shifts);

        Application.registerObserver((EventPropertyObserver)this);
        Application.registerObserver((ManagersObserver)this);
        Application.registerObserver((ShiftsObserver)this);
        Application.registerObserver((VolunteersObserver)this);
        
        assertInvariant();
    }    // EventFrame()
    
    /**
     * Returns an event whose fields correspond to the controls of the GUI.
     * 
     * @return event whose fields correspond to the controls of the GUI
     */
    public Event getEvent() {
        assertInvariant();
        Event event = new Event();
        event.setDate((Date)dateControl.getValue());
        event.setManager(managerControl.getManager());
        event.setAssistantManager(assistantManagerControl.getManager());
        for (LabeledComponent<EventPropertiesTextField> eventPropertyControl : eventPropertyControls) {
            EventPropertyList eventProperty = eventPropertyControl.component.getEventProperty();
            eventProperty.setValue(eventPropertyControl.component.getValue());
            event.addEventProperty(eventProperty);
        }    // for
        for (LabeledComponent<ShiftComboBox> shiftControl : shiftControls) {
            Shift shift = shiftControl.component.getShift();
            shift.setVolunteer(shiftControl.component.getVolunteer());
            event.addShift(shift);
        }    // for

        return event;
    }    // getEvent()

    /**
     * This method is called whenever the list of defined managers changes.
     */
    @Override
    public void managersChanged() {
        setManagers(Application.getManagers());
    }    // managersChanged()
    
    /**
     * This method is called whenever the list of defined volunteer shifts
     * changes.
     */
    @Override
    public void shiftsChanged() {
        setShifts(Application.getShifts());
        for (Shift shift : Application.getShifts()) {
            getQualifiedVolunteers(shift, Application.getVolunteers());
        }
    }    // shiftsChanged()

    /**
     * This method is called whenever the list of defined volunteers changes.
     */
    @Override
    public void volunteersChanged() {
        setVolunteers(Application.getVolunteers());
        for (Shift shift : Application.getShifts()) {
            getQualifiedVolunteers(shift, Application.getVolunteers());
        }
    }    // volunteersChanged()

    /**
     * This method is called whenever the list of defined event properties
     * changes.
     */
    @Override
    public void eventPropertiesChanged() {
        setEventProperties(Application.getEventProperties());
    }    // eventPropertiesChanged()
    
    /*
     * Private methods
     */
    
    /**
     * Sets the list of shifts displayed in the frame to the given list. The
     * volunteers selected in the comboboxes remain selected. If the new list
     * of shifts is smaller than the existing list, the "extra" comboboxes
     * are simply removed. If the new list of shifts is longer than the
     * existing list, then new comboboxes are created, with no selections.
     * If the given list is null, it is treated as an empty list.
     *
     * The list of shifts may not contain any null elements.
     *
     * @since 2.0
     * @param shifts the new list of shifts; may not contain any null elements
     * @throws NullPointerException if {@code shifts} contains any null elements
     */
    final void setShifts(List<Shift> shifts) {
        final Container container = shiftsPanel;

        if (shifts == null) {
            shifts = new ArrayList<>();
        }    // if
        if (shifts.contains(null)) {
            throw new NullPointerException("shifts may not contain null");
        }    // if
        
        List<String> selections = new LinkedList<>();
        for (LabeledComponent<ShiftComboBox> shiftControl : shiftControls) {
            Volunteer volunteer = shiftControl.component.getVolunteer();
            selections.add((volunteer == null) ? null : volunteer.getName());
            container.remove(shiftControl.label);
            container.remove(shiftControl.component);
        }    // component
        shiftControls.clear();
        for (Shift shift : shifts) {
            LabeledComponent<ShiftComboBox> shiftControl = new LabeledComponent<>(shift.getDescription() + ":", new ShiftComboBox(shift, getQualifiedVolunteers(shift, volunteers)));
            container.add(shiftControl.label);
            container.add(shiftControl.component);
            shiftControls.add(shiftControl);
        }    // for
        pack();
        setSelectedVolunteers(selections);
    }    // setShifts()

    /**
     * Sets the manager options displayed in the manager combobox in the frame
     * to those in the given list. If one of the managers in the given list has
     * the same name as that selected in the combobox, that manager becomes the
     * new selection. (The selection is effectively retained.) Otherwise, the
     * selection is cleared. If the given list is null, it is treated as an
     * empty list.
     *
     * The list of managers may not contain any null elements.
     *
     * @since 2.0
     * @param managers the new list of managers; may not contain any null
     * elements
     * @throws NullPointerException if {@code managers} contains any null
     * elements
     */
    void setManagers(List<Manager> managers) {
        if ((managers != null) && managers.contains(null)) {
            throw new NullPointerException("managers may not contain null");
        }    // if
        Manager selectedManager = managerControl.getManager();
        managerControl.setModel(managers);
        if (selectedManager != null) {
            setSelectedManager(selectedManager.getName());
        }    // if
        selectedManager = assistantManagerControl.getManager();
        assistantManagerControl.setModel(managers);
        if (selectedManager != null) {
            setSelectedAssistantManager(selectedManager.getName());
        }    // if
    }    // setVolunteers()
    
    /**
     * Sets the selected manager in the manager combobox to the manager with the
     * given name. If no manager in the list of choices of the combobox has the
     * given name, or if the given name is null, this method clears the selected
     * manager.
     *
     * @since 2.0
     * @param manager the manager to set
     */
    void setSelectedManager(String manager) {
        final int NULL_INDEX = 0;
        int newIndex = NULL_INDEX;
        for (int i = 0; i < managerControl.getItemCount(); ++i) {
            PersonContainer<Manager> item = (PersonContainer<Manager>)managerControl.getItemAt(i);
            assert (item != null);
            Manager itemManager = item.person;
            if ((itemManager != null) && (itemManager.getName().equals(manager))) {
                newIndex = i;
                break;
            }    // if
        }    // for
        managerControl.setSelectedIndex(newIndex);
    }    // setManager()
    
    /**
     * Sets the selected manager in the assistant manager combobox to the
     * manager with the given name. If no manager in the list of choices of the
     * combobox has the given name, or if the given name is null, this method
     * clears the selected manager.
     *
     * @since 2.1.2
     * @param manager the manager to set
     */
    void setSelectedAssistantManager(String manager) {
        final int NULL_INDEX = 0;
        int newIndex = NULL_INDEX;
        for (int i = 0; i < assistantManagerControl.getItemCount(); ++i) {
            PersonContainer<Manager> item = (PersonContainer<Manager>)assistantManagerControl.getItemAt(i);
            assert (item != null);
            Manager itemManager = item.person;
            if ((itemManager != null) && (itemManager.getName().equals(manager))) {
                newIndex = i;
                break;
            }    // if
        }    // for
        assistantManagerControl.setSelectedIndex(newIndex);
    }    // setSelectedAssistantManager()

    /**
     * Sets the volunteer options displayed in the shift comboboxes in the frame
     * to those in the given list. If one of the volunteers in the given list
     * has the same name as that selected in a combobox, that volunteer becomes
     * the new selection in that combobox. (The selection is effectively
     * retained.) Otherwise, the selection is cleared. If the given list is
     * null, it is treated as an empty list.
     *
     * The list of volunteers may not contain any null elements.
     *
     * @since 2.0
     * @param volunteers the new list of volunteers; may not contain any null
     * elements
     * @throws NullPointerException if {@code volunteers} contains any null
     * elements
     */
    void setVolunteers(List<Volunteer> volunteers) {
        if ((volunteers != null) && volunteers.contains(null)) {
            throw new NullPointerException("volunteers may not contain null");
        }    // if
        this.volunteers.clear();
        if (volunteers != null) {
            this.volunteers.addAll(volunteers);
        }    // if
        List<String> selectedVolunteers = new LinkedList<>();
        for (LabeledComponent<ShiftComboBox> shiftControl : shiftControls) {
            Volunteer selectedVolunteer = shiftControl.component.getVolunteer();
            String volunteerName = (selectedVolunteer == null) ? null : selectedVolunteer.getName();
            selectedVolunteers.add(volunteerName);
            shiftControl.component.setModel(this.volunteers);
        }    // for
        setSelectedVolunteers(selectedVolunteers);
    }    // setVolunteers()
    
    /**
     * Sets the selected volunteers in the volunteer comboboxes to the
     * volunteers whose names are the elements of the given list, with the first
     * name corresponding to the first combobox, the second name corresponding
     * to the second combobox, etc. If the volunteer with the given name does
     * not exist as a choice in the given combobox, or if the element is null,
     * the combobox selection is unset. If the list of volunteers is shorted
     * than the number of comboboxes, the "extra" comboboxes have their
     * selections unset. If the list of volunteers is longer than the number of
     * comboboxes, the "extra" names are ignored. If the given list is null,
     * then all combobox selections are unset.
     *
     * @since 2.0
     * @param volunteers the list of selected volunteers to set
     */
    final void setSelectedVolunteers(List<String> volunteers) {
        if (volunteers == null) {
            volunteers = new ArrayList<>();
        }    // if
        final int NULL_INDEX = 0;
        for (int controlIndex = 0; controlIndex < shiftControls.size(); ++controlIndex) {
            ShiftComboBox shiftControl = shiftControls.get(controlIndex).component;
            String volunteer = (controlIndex < volunteers.size()) ? volunteers.get(controlIndex) : null;
            int newIndex = NULL_INDEX;
            for (int volunteerIndex = 0; volunteerIndex < shiftControl.getItemCount(); ++volunteerIndex) {
                PersonContainer<Volunteer> item = (PersonContainer<Volunteer>) shiftControl.getItemAt(volunteerIndex);
                assert (item != null);
                Volunteer itemVolunteer = item.person;
                if ((itemVolunteer != null) && (itemVolunteer.getName().equals(volunteer))) {
                    newIndex = volunteerIndex;
                    break;
                }    // if
            }    // for
            shiftControl.setSelectedIndex(newIndex);
        }    // for
    }    // setVolunteers()

    /**
     * Obtains the volunteers that are qualified to work a given shift
     * @param shift shift to work
     * @param volunteers set of volunteers to check
     * @return a filtered list of qualified volunteers
     */
    private List<Volunteer> getQualifiedVolunteers(Shift shift, List<Volunteer> volunteers) {
        List<Volunteer> filteredList = new ArrayList<>();
        if (shift.getRoles().isEmpty()) {
            return volunteers;
        }
        for (Volunteer volunteer : volunteers) {
            if (!volunteer.getRoles().isEmpty() && volunteer.getRoles().containsAll(shift.getRoles())) {
                filteredList.add(volunteer);
            }
        }
        return filteredList;
    }
    /**
     * Sets the list of event properties displayed in the frame to the given list.
     * If the new list of event properties is smaller than the
     * existing list, the "extra" comboboxes are simply removed. If the new list
     * of event properties is longer than the existing list, then new comboboxes are
     * created, with no selections. If the given list is null, it is treated as
     * an empty list.
     *
     * The list of event properties may not contain any null elements.
     *
     * @since 3.0
     * @param eventProperties the new list of event properties; may not contain any null elements
     * @throws NullPointerException if {@code eventProperties} contains any null elements
     */
    final void setEventProperties(List<EventPropertyList> eventProperties) {
        final Container container = eventPropertiesPanel;

        if (eventProperties == null) {
            eventProperties = new ArrayList<>();
        }    // if
        if (eventProperties.contains(null)) {
            throw new NullPointerException("event properties may not contain null");
        }    // if

        List<String> selections = new LinkedList<>();
        for (LabeledComponent<EventPropertiesTextField> eventPropertyControl : eventPropertyControls) {
            container.remove(eventPropertyControl.label);
            container.remove(eventPropertyControl.component);
        }    // component
        eventPropertyControls.clear();
        for (EventPropertyList eventProperty : eventProperties) {
            LabeledComponent<EventPropertiesTextField> eventPropertyControl = new LabeledComponent<>(eventProperty
                .getPropertyName() + ":", new EventPropertiesTextField(eventProperty));
            container.add(eventPropertyControl.label);
            container.add(eventPropertyControl.component);
            eventPropertyControls.add(eventPropertyControl);
        }    // for
        pack();
    }    // setEventProperties()
    
    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (dateControl != null);
        assert (isAncestorOf(dateControl));
        assert (managerControl != null);
        assert (isAncestorOf(managerControl));
        assert (! comboBoxContainsNull(managerControl));
        assert (assistantManagerControl != null);
        assert (isAncestorOf(assistantManagerControl));
        assert (! comboBoxContainsNull(assistantManagerControl));
        assert (shiftControls != null);
        assert (! shiftControls.contains(null));
        assert (isAncestorOfAll(shiftControls));
        assert (! anyComboBoxContainsNull(shiftControls));
        assert (volunteers != null);
        assert (! volunteers.contains(null));
        assert (eventPropertyControls != null);
        assert (! eventPropertyControls.contains(null));
    }    // assertInvariant()
    
    /**
     * Returns true if the given combo box contains null.  The given combo box
     * is required to have a default combo box model; if not, behavior is
     * undefined.
     * 
     * @param comboBox the combo box to check; may not be null; must have a
     * default combo box model
     * @return true if {@code comboBox} contains null; false otherwise
     */
    private boolean comboBoxContainsNull(JComboBox comboBox) {
        assert (comboBox != null);
        DefaultComboBoxModel model = (DefaultComboBoxModel)comboBox.getModel();
        assert (model != null);
        return (model.getIndexOf(null) >= 0);
    }    // comboBoxContainsNull()
    
    /**
     * Returns true if the frame is the ancestor of all the components contained
     * within the elements in the given list.
     *
     * @param list the labeled components to check
     * @return true if the frame is the ancestor of all the components contained
     * within the elements in {@code list}; false otherwise
     */
    private boolean isAncestorOfAll(List<LabeledComponent<ShiftComboBox>> list) {
        for (LabeledComponent element : list) {
            if (! isAncestorOf(element.label)) {
                return false;
            }    // 
            if (! isAncestorOf(element.component)) {
                return false;
            }    // 
        }    // for
        return true;
    }    // isAncestorOfAll()
    
    /**
     * Returns true if any of the given combo boxes contains null. The given
     * combo boxes are required to be non-null and have a default combo box
     * model; if not, behavior is undefined.
     *
     * @param labeledComboBoxes the labeled combo boxes to check; may not be
     * null; may not contain null; all elements must have a default combo box
     * model
     * @return true if any combobox contained within any element of
     * {@code comboBoxes} contains null; false otherwise
     */
    private boolean anyComboBoxContainsNull(List<LabeledComponent<ShiftComboBox>> labeledComboBoxes) {
        assert (labeledComboBoxes != null);
        for (LabeledComponent<ShiftComboBox> labeledComboBoxe : labeledComboBoxes) {
            assert (labeledComboBoxe != null);
            assert (labeledComboBoxe.component != null);
            if (comboBoxContainsNull(labeledComboBoxe.component)) {
                return true;
            }    // if
        }    // for
        return false;
    }    // anyComboBoxContainsNull()
    
}    // EventFrame
