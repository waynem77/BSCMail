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

import io.github.waynem77.bscmail.gui.util.ComponentFactory;
import io.github.waynem77.bscmail.gui.util.EventFrameGrid;
import io.github.waynem77.bscmail.gui.util.EventPropertyControl;
import io.github.waynem77.bscmail.gui.util.ShiftControl;
import io.github.waynem77.bscmail.gui.util.VolunteerDisplayWrapper;
import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.persistent.Event;
import io.github.waynem77.bscmail.persistent.EventPropertiesObserver;
import io.github.waynem77.bscmail.persistent.EventProperty;
import io.github.waynem77.bscmail.persistent.Shift;
import io.github.waynem77.bscmail.persistent.VolunteersObserver;
import io.github.waynem77.bscmail.persistent.ShiftsObserver;
import io.github.waynem77.bscmail.persistent.Volunteer;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSpinner;


/**
 * A graphical interface for an {@link Event}.
 * @author Wayne Miller
 */
public class EventFrame extends JFrame implements ShiftsObserver, VolunteersObserver,
                                                  EventPropertiesObserver {

    /**
     * The grid of controls.
     */
    private final EventFrameGrid eventFrameGrid;

    /**
     * The calling application.
     */
    private final Application application;

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
            throw new NullPointerException("application may not be null");
        }    // if
        this.application = application;

        List<EventProperty> eventProperties = application.getEventProperties();
        List<Shift> shifts = application.getShifts();

        setTitle(application.createWindowTitle("Event Setup"));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        eventFrameGrid = new EventFrameGrid();
        eventFrameGrid.setBorder(ComponentFactory.getStandardBorder());
        add(eventFrameGrid);

        setEventProperties(eventProperties);
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
        JSpinner dateControl = eventFrameGrid.getDateControl();
        event.setDate((Date)dateControl.getValue());
        for (EventPropertyControl eventPropertyControl : getEventPropertyControls()) {
            EventProperty eventProperty = eventPropertyControl.getEventProperty();
            eventProperty.setValue(eventPropertyControl.getValue());
            event.addEventProperty(eventProperty);
        }    // for
        for (ShiftControl shiftControl : getShiftControls()) {
            Shift shift = shiftControl.getShift();
            shift.setVolunteer(shiftControl.getVolunteer());
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

    /**
     * {@inheritDoc }
     */
    @Override
    public void pack() {
        setMinimumSize(null);
        super.pack();
        setMinimumSize(getMinimumSize());
    }    // pack()

    /*
     * Private methods
     */

    /**
     * Returns the list of event property controls.
     *
     * @since 3.1
     * @return the list of event property controls
     */
    private List<EventPropertyControl> getEventPropertyControls() {
        return eventFrameGrid.getEventPropertyControls();
    }    // getEventPropertyControls()

    /**
     * Returns the list of shift controls.
     *
     * @since 3.1
     * @return the list of shift controls
     */
    private List<ShiftControl> getShiftControls() {
        return eventFrameGrid.getShiftControls();
    }    // getShiftControls()

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
        if (shifts == null) {
            shifts = new ArrayList<>();
        }    // if
        if (shifts.contains(null)) {
            throw new NullPointerException("shifts may not contain null");
        }    // if

        List<String> selections = getShiftControls().stream()
                .map(ShiftControl::getVolunteer)
                .map(volunteer -> (volunteer == null) ? null : volunteer.getName())
                .collect(Collectors.toList());

        List<Volunteer> volunteers = application.getVolunteers();
        List<ShiftControl> shiftControls = new LinkedList<>();
        for (Shift shift : shifts) {
            ShiftControl shiftControl = new ShiftControl(shift, getQualifiedVolunteers(shift, volunteers));
            shiftControls.add(shiftControl);
        }    // for
        eventFrameGrid.setShiftControls(shiftControls);
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
     * The list of volunteers may not be null nor contain any null elements.
     *
     * @since 2.0
     * @param volunteers the new list of volunteers; may not contain any null
     * elements
     * @throws NullPointerException if {@code volunteers} contains any null
     * elements
     */
    void setVolunteers(List<Volunteer> volunteers) {
        if (volunteers == null) {
            throw new NullPointerException("volunteers may not be null");
        }    // if
        if (volunteers.contains(null)) {
            throw new NullPointerException("volunteers may not contain null");
        }    // if
        List<String> selectedVolunteers = new LinkedList<>();
        for (ShiftControl shiftControl : getShiftControls()) {
            Volunteer selectedVolunteer = shiftControl.getVolunteer();
            String volunteerName = (selectedVolunteer == null) ? null : selectedVolunteer.getName();
            selectedVolunteers.add(volunteerName);
            shiftControl.setModel(getQualifiedVolunteers(shiftControl.getShift(), volunteers));
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
        List<ShiftControl> shiftControls = getShiftControls();
        for (int controlIndex = 0; controlIndex < shiftControls.size(); ++controlIndex) {
            ShiftControl shiftControl = shiftControls.get(controlIndex);
            String volunteer = (controlIndex < volunteers.size()) ? volunteers.get(controlIndex) : null;
            int newIndex = NULL_INDEX;
            for (int volunteerIndex = 0; volunteerIndex < shiftControl.getItemCount(); ++volunteerIndex) {
                VolunteerDisplayWrapper item = shiftControl.getItemAt(volunteerIndex);
                assert (item != null);
                Volunteer itemVolunteer = item.getVolunteer();
                if ((itemVolunteer != null) && (itemVolunteer.getName().equals(volunteer))) {
                    newIndex = volunteerIndex;
                    break;
                }    // if
            }    // for
            shiftControl.setSelectedIndex(newIndex);
        }    // for
    }    // setVolunteers()

    /**
     * Obtains the volunteers that are qualified to work a given shift.
     *
     * Qualified volunteers are defined as:
     * <ul>
     * <li>having all the roles required by the shift, and
     * <li>being active.
     * </ul>
     *
     * @param shift shift to work; may not be null
     * @param volunteers set of volunteers to check; may not be null nor contain
     * null
     * @return a filtered list of qualified volunteers
     */
    private List<Volunteer> getQualifiedVolunteers(Shift shift, List<Volunteer> volunteers) {
        assert (shift != null);
        assert (volunteers != null);
        assert (!volunteers.contains(null));
        return volunteers.stream()
                .filter(Volunteer::isActive)
                .filter(volunteer -> shift.rolesAreCompatible(volunteer))
                .collect(Collectors.toList());
    }    // getQualifiedVolunteers()

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
        if (eventProperties == null) {
            eventProperties = new ArrayList<>();
        }    // if
        if (eventProperties.contains(null)) {
            throw new NullPointerException("event properties may not contain null");
        }    // if

        List<EventPropertyControl> eventPropertyControls = eventProperties.stream()
                .map(EventPropertyControl::new)
                .collect(Collectors.toList());
        eventFrameGrid.setEventPropertyControls(eventPropertyControls);
        pack();
    }    // setEventProperties()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (application != null);
        assert (eventFrameGrid != null);
        assert (isAncestorOf(eventFrameGrid));
    }    // assertInvariant()

}    // EventFrame
