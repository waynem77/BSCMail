/*
 * Copyright © 2017 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui.util;

import bscmail.EventProperty;
import bscmail.Shift;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 * A control grid used in {@link EventFrame}. The grid contains a date control
 * for the event, a variable set of text controls for event properties, a
 * variable set of dropdown controls for shifts, and their corresponding labels.
 *
 * This class is something of a facade for {@link GroupedGrid}.
 *
 * @author Wayne Miller
 * @since 3.1
 */
public class EventFrameGrid extends JPanel {

    /**
     * The standard control group.
     */
    private static final int DATE_GROUP = 0;

    /**
     * The event properties group.
     */
    private static final int EVENT_PROPERTIES_GROUP = 1;

    /**
     * The shifts group.
     */
    private static final int SHIFTS_GROUP = 2;

    /**
     * The total number of groups.
     */
    private static final int GROUPS = 3;

    /**
     * The grouped grid providing the functionality.
     */
    final private GroupedGrid groupedGrid;

    /**
     * Constructs a new event frame grid,
     */
    public EventFrameGrid() {
        groupedGrid = new GroupedGrid(GROUPS);
        add(groupedGrid);

        JSpinner dateControl = new JSpinner(new SpinnerDateModel());
        SimpleDateFormat dateFormat = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM);
        dateControl.setEditor(new JSpinner.DateEditor(dateControl, dateFormat.toPattern()));
        List<LabeledComponent> components = Arrays.asList(new LabeledComponent("Date:", dateControl));
        groupedGrid.setComponents(components, EventFrameGrid.DATE_GROUP);

        assertInvariant();
    }    // EventFrameGrid()

    /**
     * Returns the event date control on the grid.
     *
     * @return the event date control on the grid
     */
    public JSpinner getDateControl() {
        assertInvariant();
        List<Component> components = groupedGrid.getComponents(EventFrameGrid.DATE_GROUP);
        assert (components.size() == 1);
        assert (components.get(0) instanceof JSpinner);
        JSpinner dateControl = (JSpinner)components.get(0);
        return dateControl;
    }    // getDateControl()

    /**
     * Returns a list containing the event property text controls on the grid.
     *
     * @return a list containing the event property text controls on the grid
     */
    public List<EventPropertyControl> getEventPropertyControls() {
        assertInvariant();
        List<Component> rawComponents = groupedGrid.getComponents(EventFrameGrid.EVENT_PROPERTIES_GROUP);
        List<EventPropertyControl> eventPropertyControls = new LinkedList<>();
        for (Component component : rawComponents) {
            assert (component instanceof EventPropertyControl);
            eventPropertyControls.add((EventPropertyControl)component);
        }    // for
        return eventPropertyControls;
    }    // getEventPropertyControls()

    /**
     * Sets the set of event property controls to the elements of the given
     * list.
     *
     * @param eventPropertyControls a list containing the event property text
     * controls to place on the grid; may not be null nor contain null
     * @throws NullPointerException if {@code eventPropertyControls} is null or
     * contains null
     */
    public void setEventPropertyControls(List<EventPropertyControl> eventPropertyControls) {
        assertInvariant();
        if (eventPropertyControls == null) {
            throw new NullPointerException("eventPropertyControls may not be null");
        }    // if
        if (eventPropertyControls.contains(null)) {
            throw new NullPointerException("eventPropertyControls may not contain null");
        }    // if

        List<LabeledComponent> labeledComponents = new LinkedList<>();
        for (EventPropertyControl eventPropertyControl : eventPropertyControls) {
            EventProperty eventProperty = eventPropertyControl.getEventProperty();
            LabeledComponent<EventPropertyControl> labeledComponent = new LabeledComponent<>(eventProperty.getPropertyName() + ":", eventPropertyControl);
            labeledComponents.add(labeledComponent);
        }    // for
        groupedGrid.setComponents(labeledComponents, EventFrameGrid.EVENT_PROPERTIES_GROUP);
        assertInvariant();
    }    // setEventPropertyControls()

    /**
     * Returns a list containing the shift dropdown controls on the grid.
     *
     * @return a list containing the shift dropdown controls on the grid
     */
    public List<ShiftControl> getShiftControls() {
        assertInvariant();
        List<Component> rawComponents = groupedGrid.getComponents(EventFrameGrid.SHIFTS_GROUP);
        List<ShiftControl> shiftControls = new LinkedList<>();
        for (Component component : rawComponents) {
            assert (component instanceof ShiftControl);
            shiftControls.add((ShiftControl)component);
        }    // for
        return shiftControls;
    }    // getShiftControls()

    /**
     * Sets the set of shift dropdown controls to the elements of the given
     * list.
     *
     * @param shiftControls a list containing the shift dropdown controls to
     * place on the grid; may not be null nor contain null
     * @throws NullPointerException if {@code shiftControls} is null or contains
     * null
     */
    public void setShiftControls(List<ShiftControl> shiftControls) {
        assertInvariant();
        if (shiftControls == null) {
            throw new NullPointerException("shiftControls may not be null");
        }    // if
        if (shiftControls.contains(null)) {
            throw new NullPointerException("shiftControls may not contain null");
        }    // if

        List<LabeledComponent> labeledComponents = new LinkedList<>();
        for (ShiftControl shiftControl : shiftControls) {
            Shift shift = shiftControl.getShift();
            LabeledComponent<ShiftControl> labeledComponent = new LabeledComponent<>(shift.getDescription() + ":", shiftControl);
            labeledComponents.add(labeledComponent);
        }    // for
        groupedGrid.setComponents(labeledComponents, EventFrameGrid.SHIFTS_GROUP);
        assertInvariant();
    }    // setShiftControls()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert(groupedGrid != null);
        assert(isAncestorOf(groupedGrid));
    }    // assertInvariant()

}    // EventFrameGrid
