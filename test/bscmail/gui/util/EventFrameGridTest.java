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
import bscmail.Role;
import bscmail.Volunteer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JSpinner;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EventFrameGrid}
 *
 * @author Wayne Miller
 */
public class EventFrameGridTest {

    /* constructor */

    /**
     * Tests that {@link EventFrameGrid#EventFrameGrid()} does not throw an
     * exception.
     */
    @Test
    public void constructorDoesNotThrowException() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
    }    // constructorDoesNotThrowException()

    /* getDateControl */

    /**
     * Tests that {@link EventFrameGrid#getDateControl()} does not throw an
     * exception.
     */
    @Test
    public void getDateControlDoesNotThrowException() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();

        eventFrameGrid.getDateControl();
    }    // getDateControlDoesNotThrowException()

    /**
     * Tests that {@link EventFrameGrid#getDateControl()} does not return null.
     */
    @Test
    public void getDateControlDoesNotReturnNull() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();

        JSpinner received = eventFrameGrid.getDateControl();

        assertNotNull(received);
    }    // getDateControlDoesNotReturnNull()

    /**
     * Tests that the control returned by
     * {@link EventFrameGrid#getDateControl()} is contained on the grid.
     */
    @Test
    public void getDateControlReturnsControlOnGrid() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();

        JSpinner received = eventFrameGrid.getDateControl();

        assertTrue(eventFrameGrid.isAncestorOf(received));
    }    // getDateControlReturnsControlOnGrid()

    /* setEventControls */

    /**
     * Tests that
     * {@link EventFrameGrid#setEventPropertyControls(java.util.List)} throws a
     * {@link NullPointerException} when eventPropertyControls is null.
     */
    @Test(expected = NullPointerException.class)
    public void setEventPropertyControlsThrowsExceptionWhenEventPropertyControlsIsNull() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<EventPropertyControl> eventPropertyControls = null;

        eventFrameGrid.setEventPropertyControls(eventPropertyControls);
    }    // setEventPropertyControlsThrowsExceptionWhenEventPropertyControlsIsNull()

    /**
     * Tests that
     * {@link EventFrameGrid#setEventPropertyControls(java.util.List)} throws a
     * {@link NullPointerException} when eventPropertyControls contains null.
     */
    @Test(expected = NullPointerException.class)
    public void setEventPropertyControlsThrowsExceptionWhenEventPropertyControlsContainsNull() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<EventPropertyControl> eventPropertyControls = Arrays.asList(
                new EventPropertyControl(new EventProperty("foo", "")),
                null,
                new EventPropertyControl(new EventProperty("baz", ""))
        );    // eventPropertyControls

        eventFrameGrid.setEventPropertyControls(eventPropertyControls);
    }    // setEventPropertyControlsThrowsExceptionWhenEventPropertyControlsContainsNull()

    /**
     * Tests that
     * {@link EventFrameGrid#setEventPropertyControls(java.util.List)} does not
     * throw an exception when eventPropertyControls is neither null nor
     * contains null.
     */
    @Test
    public void setEventPropertyControlsDoesNotThrowExceptionWhenEventPropertyControlsIsCorrect() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<EventPropertyControl> eventPropertyControls = Arrays.asList(
                new EventPropertyControl(new EventProperty("foo", "")),
                new EventPropertyControl(new EventProperty("bar", "")),
                new EventPropertyControl(new EventProperty("baz", ""))
        );    // eventPropertyControls

        eventFrameGrid.setEventPropertyControls(eventPropertyControls);
    }    // setEventPropertyControlsDoesNotThrowExceptionWhenEventPropertyControlsIsCorrect()

    /* getEventPropertyControls */

    /**
     * Tests that {@link EventFrameGrid#getEventPropertyControls()} does not
     * throw an exception when there are no controls.
     */
    @Test
    public void getEventPropertyControlsDoesNotThrowExceptionWhenThereAreNoControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();

        eventFrameGrid.getEventPropertyControls();
    }    // getEventPropertyControlsDoesNotThrowExceptionWhenThereAreNoControls()

    /**
     * Tests that {@link EventFrameGrid#getEventPropertyControls()} does not
     * throw an exception when there are controls.
     */
    @Test
    public void getEventPropertyControlsDoesNotThrowException() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<EventPropertyControl> eventPropertyControls = Arrays.asList(
                new EventPropertyControl(new EventProperty("foo", "")),
                new EventPropertyControl(new EventProperty("bar", "")),
                new EventPropertyControl(new EventProperty("baz", ""))
        );    // eventPropertyControls
        eventFrameGrid.setEventPropertyControls(eventPropertyControls);

        eventFrameGrid.getEventPropertyControls();
    }    // getEventPropertyControlsDoesNotThrowException()

    /**
     * Tests that {@link EventFrameGrid#getEventPropertyControls()} does not
     * return null when there are no controls.
     */
    @Test
    public void getEventPropertyControlsDoesNotReturnNullWhenThereAreNoControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();

        List<EventPropertyControl> received = eventFrameGrid.getEventPropertyControls();

        assertNotNull(received);
    }    // getEventPropertyControlsDoesNotReturnNullWhenThereAreNoControls()

    /**
     * Tests that {@link EventFrameGrid#getEventPropertyControls()} does not
     * return null when there are controls.
     */
    @Test
    public void getEventPropertyControlsDoesNotReturnNull() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<EventPropertyControl> eventPropertyControls = Arrays.asList(
                new EventPropertyControl(new EventProperty("foo", "")),
                new EventPropertyControl(new EventProperty("bar", "")),
                new EventPropertyControl(new EventProperty("baz", ""))
        );    // eventPropertyControls
        eventFrameGrid.setEventPropertyControls(eventPropertyControls);

        List<EventPropertyControl> received = eventFrameGrid.getEventPropertyControls();

        assertNotNull(received);
    }    // getEventPropertyControlsDoesNotReturnNull()

    /**
     * Tests that {@link EventFrameGrid#getEventPropertyControls()} does not
     * return a list containing null.
     */
    @Test
    public void getEventPropertyControlsDoesNotReturnAListContainingNull() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<EventPropertyControl> eventPropertyControls = Arrays.asList(
                new EventPropertyControl(new EventProperty("foo", "")),
                new EventPropertyControl(new EventProperty("bar", "")),
                new EventPropertyControl(new EventProperty("baz", ""))
        );    // eventPropertyControls
        eventFrameGrid.setEventPropertyControls(eventPropertyControls);

        List<EventPropertyControl> received = eventFrameGrid.getEventPropertyControls();
        assertTrue("Test error: there are no controls", received.size() > 0);

        assertFalse(received.contains(null));
    }    // getEventPropertyControlsDoesNotReturnAListContainingNull()

    /**
     * Tests that all the elements of the return value of
     * {@link EventFrameGrid#getEventPropertyControls()} are contained on the
     * grid.
     */
    @Test
    public void getEventPropertyControlsReturnsControlsOnTheGrid() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<EventPropertyControl> eventPropertyControls = Arrays.asList(
                new EventPropertyControl(new EventProperty("foo", "")),
                new EventPropertyControl(new EventProperty("bar", "")),
                new EventPropertyControl(new EventProperty("baz", ""))
        );    // eventPropertyControls
        eventFrameGrid.setEventPropertyControls(eventPropertyControls);

        List<EventPropertyControl> received = eventFrameGrid.getEventPropertyControls();
        assertTrue("Test error: there are no controls", received.size() > 0);

        for (int i = 0; i < received.size(); ++i) {
            EventPropertyControl control = received.get(i);
            if (! eventFrameGrid.isAncestorOf(control)) {
                fail("Control " + control + " at index " + i + " is not contained on the grid.");
            }    // if
        }    // for
    }    // getEventPropertyControlsReturnsControlsOnTheGrid()

    /**
     * Tests that {@link EventFrameGrid#getEventPropertyControls()} returns a
     * set of controls that correspond to those that were previously passed to
     * {@link EventFrameGrid#setEventPropertyControls(java.util.List)}.
     */
    @Test
    public void getEventPropertyControlsReturnsControlsCorrespondingToThosePassedToSetEventPropertyControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<EventPropertyControl> eventPropertyControls = Arrays.asList(
                new EventPropertyControl(new EventProperty("foo", "")),
                new EventPropertyControl(new EventProperty("bar", "")),
                new EventPropertyControl(new EventProperty("baz", ""))
        );    // eventPropertyControls
        eventFrameGrid.setEventPropertyControls(eventPropertyControls);

        List<EventPropertyControl> receivedControls = eventFrameGrid.getEventPropertyControls();
        assertTrue("Test error: there are no controls", receivedControls.size() > 0);

        List<EventProperty> expected = new LinkedList<>();
        for (EventPropertyControl eventPropertyControl : eventPropertyControls) {
            expected.add(eventPropertyControl.getEventProperty());
        }
        List<EventProperty> received = new LinkedList<>();
        for (EventPropertyControl eventPropertyControl : receivedControls) {
            received.add(eventPropertyControl.getEventProperty());
        }
        assertEquals(expected, received);
    }    // getEventPropertyControlsReturnsControlsCorrespondingToThosePassedToSetEventPropertyControls()

    /**
     * Tests that {@link EventFrameGrid#getEventPropertyControls()} returns the
     * same controls that were previously passed to
     * {@link EventFrameGrid#setEventPropertyControls(java.util.List)}.
     */
    @Test
    public void getEventPropertyControlsReturnsControlsPassedToSetEventPropertyControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<EventPropertyControl> eventPropertyControls = Arrays.asList(
                new EventPropertyControl(new EventProperty("foo", "")),
                new EventPropertyControl(new EventProperty("bar", "")),
                new EventPropertyControl(new EventProperty("baz", ""))
        );    // eventPropertyControls
        eventFrameGrid.setEventPropertyControls(eventPropertyControls);

        List<EventPropertyControl> received = eventFrameGrid.getEventPropertyControls();
        assertTrue("Test error: there are no controls", received.size() > 0);

        List<EventPropertyControl> expected = eventPropertyControls;
        assertTrue("Expected and received lists have different sizes", expected.size() == received.size());
        for (int i = 0; i < received.size(); ++i) {
            assertSame("Elements at index " + i + " differ", expected.get(i), received.get(i));
        }    // for
    }    // getEventPropertyControlsReturnsControlsPassedToSetEventPropertyControls()

    /* setShiftControls */

    /**
     * Tests that {@link EventFrameGrid#setShiftControls(java.util.List)} throws
     * a {@link NullPointerException} when shiftControls is null.
     */
    @Test(expected = NullPointerException.class)
    public void setShiftControlsThrowsExceptionWhenShiftControlsIsNull() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<ShiftControl> shiftControls = null;

        eventFrameGrid.setShiftControls(shiftControls);
    }    // setShiftControlsThrowsExceptionWhenShiftControlsIsNull()

    /**
     * Tests that {@link EventFrameGrid#setShiftControls(java.util.List)} throws
     * a {@link NullPointerException} when shiftControls contains null.
     */
    @Test(expected = NullPointerException.class)
    public void setShiftControlsThrowsExceptionWhenShiftControlsContainsNull() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("one", "", "", ""),
                new Volunteer("two", "", "", "")
        );
        List<ShiftControl> shiftControls = Arrays.asList(
                new ShiftControl(new Shift("foo", Arrays.<Role>asList(), false, false, false), volunteers),
                null,
                new ShiftControl(new Shift("baz", Arrays.<Role>asList(), false, false, false), volunteers)
        );    // shiftControls

        eventFrameGrid.setShiftControls(shiftControls);
    }    // setShiftControlsThrowsExceptionWhenShiftControlsContainsNull()

    /**
     * Tests that
     * {@link EventFrameGrid#setShiftControls(java.util.List)} does not
     * throw an exception when shiftControls is neither null nor
     * contains null.
     */
    @Test
    public void setShiftControlsDoesNotThrowExceptionWhenShiftControlsIsCorrect() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("one", "", "", ""),
                new Volunteer("two", "", "", "")
        );
        List<ShiftControl> shiftControls = Arrays.asList(
                new ShiftControl(new Shift("foo", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("bar", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("baz", Arrays.<Role>asList(), false, false, false), volunteers)
        );    // shiftControls

        eventFrameGrid.setShiftControls(shiftControls);
    }    // setShiftControlsDoesNotThrowExceptionWhenShiftControlsIsCorrect()

    /* getShiftControls */

    /**
     * Tests that {@link EventFrameGrid#getShiftControls()} does not throw an
     * exception when there are no controls.
     */
    @Test
    public void getShiftControlsDoesNotThrowExceptionWhenThereAreNoControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();

        eventFrameGrid.getShiftControls();
    }    // getShiftControlsDoesNotThrowExceptionWhenThereAreNoControls()

    /**
     * Tests that {@link EventFrameGrid#getShiftControls()} does not throw an
     * exception when there are controls.
     */
    @Test
    public void getShiftControlsDoesNotThrowExceptionWhenThereAreControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("one", "", "", ""),
                new Volunteer("two", "", "", "")
        );
        List<ShiftControl> shiftControls = Arrays.asList(
                new ShiftControl(new Shift("foo", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("bar", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("baz", Arrays.<Role>asList(), false, false, false), volunteers)
        );    // shiftControls
        eventFrameGrid.setShiftControls(shiftControls);

        eventFrameGrid.getShiftControls();
    }    // getShiftControlsDoesNotThrowExceptionWhenThereAreControls()

    /**
     * Tests that {@link EventFrameGrid#getShiftControls()} does not return
     * null when there are no controls.
     */
    @Test
    public void getShiftControlsDoesNotReturnNullWhenThereAreNoControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();

        List<ShiftControl> received = eventFrameGrid.getShiftControls();

        assertNotNull(received);
    }    // getShiftControlsDoesNotReturnNullWhenThereAreNoControls()

    /**
     * Tests that {@link EventFrameGrid#getShiftControls()} does not return
     * null when there are controls.
     */
    @Test
    public void getShiftControlsDoesNotReturnNullWhenThereAreControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("one", "", "", ""),
                new Volunteer("two", "", "", "")
        );
        List<ShiftControl> shiftControls = Arrays.asList(
                new ShiftControl(new Shift("foo", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("bar", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("baz", Arrays.<Role>asList(), false, false, false), volunteers)
        );    // shiftControls
        eventFrameGrid.setShiftControls(shiftControls);

        List<ShiftControl> received = eventFrameGrid.getShiftControls();

        assertNotNull(received);
    }    // getShiftControlsDoesNotReturnNullWhenThereAreControls()

    /**
     * Tests that {@link EventFrameGrid#getShiftControls()} does not return a
     * list containing null.
     */
    @Test
    public void getShiftControlsDoesNotReturnAListContainingNull() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("one", "", "", ""),
                new Volunteer("two", "", "", "")
        );
        List<ShiftControl> shiftControls = Arrays.asList(
                new ShiftControl(new Shift("foo", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("bar", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("baz", Arrays.<Role>asList(), false, false, false), volunteers)
        );    // shiftControls
        eventFrameGrid.setShiftControls(shiftControls);

        List<ShiftControl> received = eventFrameGrid.getShiftControls();

        assertFalse(received.contains(null));
    }    // getShiftControlsDoesNotReturnAListContainingNull()

    /**
     * Tests that all the elements of the return value of
     * {@link EventFrameGrid#getShiftControls()} are contained on the grid.
     */
    @Test
    public void getShiftControlsReturnsControlsOnTheGrid() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("one", "", "", ""),
                new Volunteer("two", "", "", "")
        );
        List<ShiftControl> shiftControls = Arrays.asList(
                new ShiftControl(new Shift("foo", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("bar", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("baz", Arrays.<Role>asList(), false, false, false), volunteers)
        );    // shiftControls
        eventFrameGrid.setShiftControls(shiftControls);

        List<ShiftControl> received = eventFrameGrid.getShiftControls();
        assertTrue("Test error: there are no controls", received.size() > 0);

        for (int i = 0; i < received.size(); ++i) {
            ShiftControl control = received.get(i);
            if (! eventFrameGrid.isAncestorOf(control)) {
                fail("Control " + control + " at index " + i + " is not contained on the grid.");
            }    // if
        }    // for
    }    // getShiftControlsReturnsControlsOnTheGrid()

    /**
     * Tests that {@link EventFrameGrid#getShiftControls()} returns a set of
     * controls equal to those that were previously passed to
     * {@link EventFrameGrid#setShiftControls(java.util.List)}.
     */
    @Test
    public void getShiftControlsReturnsControlsEqualToThosePassedToSetShiftControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("one", "", "", ""),
                new Volunteer("two", "", "", "")
        );
        List<ShiftControl> shiftControls = Arrays.asList(
                new ShiftControl(new Shift("foo", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("bar", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("baz", Arrays.<Role>asList(), false, false, false), volunteers)
        );    // shiftControls
        eventFrameGrid.setShiftControls(shiftControls);

        List<ShiftControl> received = eventFrameGrid.getShiftControls();
        assertTrue("Test error: there are no controls", received.size() > 0);

        List<ShiftControl> expected = shiftControls;
        assertEquals(expected, received);
    }    // getShiftControlsReturnsControlsEqualToThosePassedToSetShiftControls()

    /**
     * Tests that {@link EventFrameGrid#getShiftControls()} returns the
     * same controls that were previously passed to
     * {@link EventFrameGrid#setShiftControls(java.util.List)}.
     */
    @Test
    public void getShiftControlsReturnsControlsPassedToSetShiftControls() {
        EventFrameGrid eventFrameGrid = new EventFrameGrid();
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("one", "", "", ""),
                new Volunteer("two", "", "", "")
        );
        List<ShiftControl> shiftControls = Arrays.asList(
                new ShiftControl(new Shift("foo", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("bar", Arrays.<Role>asList(), false, false, false), volunteers),
                new ShiftControl(new Shift("baz", Arrays.<Role>asList(), false, false, false), volunteers)
        );    // shiftControls
        eventFrameGrid.setShiftControls(shiftControls);

        List<ShiftControl> received = eventFrameGrid.getShiftControls();
        assertTrue("Test error: there are no controls", received.size() > 0);

        List<ShiftControl> expected = shiftControls;
        assertTrue("Expected and received lists have different sizes", expected.size() == received.size());
        for (int i = 0; i < received.size(); ++i) {
            assertSame("Elements at index " + i + " differ", expected.get(i), received.get(i));
        }    // for
    }    // getShiftControlsReturnsControlsPassedToSetShiftControls()

}    // EventFrameGridTest
