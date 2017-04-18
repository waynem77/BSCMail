/*
 * Copyright © 2014-2017 its authors.  See the file "AUTHORS" for details.
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

import bscmail.Application;
import bscmail.Event;
import bscmail.EventPropertiesObserver;
import bscmail.EventProperty;
import bscmail.Shift;
import bscmail.VolunteersObserver;
import bscmail.ShiftsObserver;
import bscmail.Volunteer;
import bscmail.gui.util.LabeledComponent;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;


/**
 * A graphical interface for an {@link Event}.
 * @author Wayne Miller
 */
public class EventFrame extends JFrame implements ShiftsObserver, VolunteersObserver,
                                                  EventPropertiesObserver {

    /* Private Classes */

    /**
     * Wrapper for a volunteer. When displayed in a combo box, the container
     * displays the name of the volunteer, or "(open)" if the volunteer is null.
     */
    private class VolunteerContainer {
        /**
         * The volunteer being wrapped.
         */
        private final Volunteer volunteer;

        /**
         * Constructs a new volunteer container.
         *
         * @param volunteer the volunteer wrapped in the container
         */
        public VolunteerContainer(Volunteer volunteer) {
            this.volunteer = volunteer;
        }    // PersonContainer

        /**
         * Returns the volunteer wrapped in the container.
         *
         * @return the volunteer wrapped in the container
         */
        public Volunteer getVolunteer() {
            return volunteer;
        }    // getPerson()

        /**
         * Returns the name of the volunteer wrapped in the container, or
         * "(open)" if the volunteer is null.
         *
         * @return name of the volunteer wrapped in the container, or "(open)"
         * if the volunteer is null
         */
        @Override
        public String toString() {
            return (volunteer == null) ? "(open)" : volunteer.getName();
        }    // toString()
    }    // VolunteerContainer

    /**
     * A text field that allows the user to select event properties. This class extends
     * {@code JTextField}.
     */
    private class EventPropertiesTextField extends JTextField {
        /**
         * The event property corresponding to this text field.
         */
        private final EventProperty eventProperty;

        /**
         * Constructs a new event Property text field.
         *
         * @param eventProperty the event property corresponding to this text field; may not be
         * null
         */
        public EventPropertiesTextField(EventProperty eventProperty) {
            super(eventProperty.getDefaultValue());
            assert (eventProperty != null);
            this.eventProperty = eventProperty;
            //new JTextField(eventProperty.getDefaultValue());
            //add();

        }    // EventPropertiesTextField()

        /**
         * Returns the event property corresponding to this text field.
         *
         * @return the event property corresponding to this text field
         */
        public EventProperty getEventProperty() {
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
     * A combo box that contains a shift and allows the user to select
     * volunteers. This class extends {@code JComboBox} to contain extra data
     * (the shift) and easily convert person containers to volunteers.
     */
    private class ShiftComboBox extends JComboBox<VolunteerContainer> {
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
            VolunteerContainer container = (VolunteerContainer)getSelectedItem();
            return container.getVolunteer();
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
            addItem(new VolunteerContainer(null));
            if (volunteers != null) {
                for (Volunteer volunteer : volunteers) {
                    addItem(new VolunteerContainer(volunteer));
                }    // for
            }    // if
        }    // setModel()
    }    // ShiftComboBox

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
     * Shift dropdown controls.
     */
    private final List<LabeledComponent<ShiftComboBox>> shiftControls;

    /**
     * Other private variables.
     */

    private final Application application;

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
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public EventFrame(Application application) {
        if (application == null) {
            throw new NullPointerException("applicaiton may not be null");
        }    // if
        this.application = application;

        List<EventProperty> eventProperties = application.getEventProperties();
        List<Shift> shifts = application.getShifts();
        volunteers = application.getVolunteers();

        setTitle(application.getApplicationName() + " - Event Setup");
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
        eventPropertiesPanel.setLayout(new GridLayout(0, 2));
        eventPropertyControls = new LinkedList<>();
        setEventProperties(eventProperties);

        shiftsPanel.setLayout(new GridLayout(0, 2));
        shiftControls = new LinkedList<>();
        setShifts(shifts);

        application.registerObserver((EventPropertiesObserver)this);
        application.registerObserver((ShiftsObserver)this);
        application.registerObserver((VolunteersObserver)this);

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
        for (LabeledComponent<EventPropertiesTextField> eventPropertyControl : eventPropertyControls) {
            EventProperty eventProperty = eventPropertyControl.getComponent().getEventProperty();
            eventProperty.setValue(eventPropertyControl.getComponent().getValue());
            event.addEventProperty(eventProperty);
        }    // for
        for (LabeledComponent<ShiftComboBox> shiftControl : shiftControls) {
            Shift shift = shiftControl.getComponent().getShift();
            shift.setVolunteer(shiftControl.getComponent().getVolunteer());
            event.addShift(shift);
        }    // for

        return event;
    }    // getEvent()

    /**
     * This method is called whenever the list of defined volunteer shifts
     * changes.
     */
    @Override
    public void shiftsChanged() {
        setShifts(application.getShifts());
    }    // shiftsChanged()

    /**
     * This method is called whenever the list of defined volunteers changes.
     */
    @Override
    public void volunteersChanged() {
        setVolunteers(application.getVolunteers());
        setShifts(application.getShifts());
    }    // volunteersChanged()

    /**
     * This method is called whenever the list of defined event properties
     * changes.
     */
    @Override
    public void eventPropertiesChanged() {
        setEventProperties(application.getEventProperties());
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
            Volunteer volunteer = shiftControl.getComponent().getVolunteer();
            selections.add((volunteer == null) ? null : volunteer.getName());
            container.remove(shiftControl.getLabel());
            container.remove(shiftControl.getComponent());
        }    // component
        shiftControls.clear();
        for (Shift shift : shifts) {
            LabeledComponent<ShiftComboBox> shiftControl = new LabeledComponent<>(shift.getDescription() + ":", new ShiftComboBox(shift, getQualifiedVolunteers(shift, volunteers)));
            container.add(shiftControl.getLabel());
            container.add(shiftControl.getComponent());
            shiftControls.add(shiftControl);
        }    // for
        pack();
        setSelectedVolunteers(selections);
    }    // setShifts()

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
            Volunteer selectedVolunteer = shiftControl.getComponent().getVolunteer();
            String volunteerName = (selectedVolunteer == null) ? null : selectedVolunteer.getName();
            selectedVolunteers.add(volunteerName);
            shiftControl.getComponent().setModel(this.getQualifiedVolunteers(shiftControl.getComponent().getShift(), this.volunteers));
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
            ShiftComboBox shiftControl = shiftControls.get(controlIndex).getComponent();
            String volunteer = (controlIndex < volunteers.size()) ? volunteers.get(controlIndex) : null;
            int newIndex = NULL_INDEX;
            for (int volunteerIndex = 0; volunteerIndex < shiftControl.getItemCount(); ++volunteerIndex) {
                VolunteerContainer item = shiftControl.getItemAt(volunteerIndex);
                assert (item != null);
                Volunteer itemVolunteer = item.volunteer;
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
    final void setEventProperties(List<EventProperty> eventProperties) {
        final Container container = eventPropertiesPanel;

        if (eventProperties == null) {
            eventProperties = new ArrayList<>();
        }    // if
        if (eventProperties.contains(null)) {
            throw new NullPointerException("event properties may not contain null");
        }    // if

        List<String> selections = new LinkedList<>();
        for (LabeledComponent<EventPropertiesTextField> eventPropertyControl : eventPropertyControls) {
            container.remove(eventPropertyControl.getLabel());
            container.remove(eventPropertyControl.getComponent());
        }    // component
        eventPropertyControls.clear();
        for (EventProperty eventProperty : eventProperties) {
            LabeledComponent<EventPropertiesTextField> eventPropertyControl = new LabeledComponent<>(eventProperty
                .getPropertyName() + ":", new EventPropertiesTextField(eventProperty));
            container.add(eventPropertyControl.getLabel());
            container.add(eventPropertyControl.getComponent());
            eventPropertyControls.add(eventPropertyControl);
        }    // for
        pack();
    }    // setEventProperties()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (application != null);
        assert (dateControl != null);
        assert (isAncestorOf(dateControl));
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
            if (! isAncestorOf(element.getLabel())) {
                return false;
            }    //
            if (! isAncestorOf(element.getComponent())) {
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
            assert (labeledComboBoxe.getComponent() != null);
            if (comboBoxContainsNull(labeledComboBoxe.getComponent())) {
                return true;
            }    // if
        }    // for
        return false;
    }    // anyComboBoxContainsNull()

}    // EventFrame
