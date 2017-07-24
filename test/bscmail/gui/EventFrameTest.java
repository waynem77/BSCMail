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
import bscmail.ApplicationInfo;
import bscmail.EmailTemplate;
import bscmail.Event;
import bscmail.EventProperty;
import bscmail.Role;
import bscmail.Shift;
import bscmail.TestIOLayer;
import bscmail.Volunteer;
import bscmail.help.HelpDisplay;
import iolayer.IOLayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EventFrame}.
 *
 * @author Wayne Miller
 */
public class EventFrameTest {

    /**
     * A role used in testing.
     */
    private final Role ANGEL_ROLE = new Role("Angel");

    /**
     * Returns an application that can be used in tests.
     */
    private Application getTestApplication()  {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz");
        TestIOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        shiftsIOLayer.setAll(getTestShifts());
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new HelpDisplay(){ @Override public void displayHelp() {} };
        return new Application(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // getTestApplication()

    /**
     * Returns a list of shifts to use in testing.
     *
     * The shifts in the list are:
     * <ul>
     *   <li>Door 1, no roles</li>
     *   <li>Door 2, no roles</li>
     *   <li>Angel, Angel role</li>
     * </ul>
     *
     * @return a list of shifts to use in testing
     */
    private List<Shift> getTestShifts() {
        return new LinkedList<>(Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Arrays.asList(ANGEL_ROLE), false, false, false)
        ));    // LinkedList
    }    // getTestShifts()

    /**
     * Returns a list of volunteers to use in testing.
     *
     * The volunteers in the list are:
     * <ul>
     *   <li>Val Unteer, Angel role</li>
     *   <li>Halva Ticket, no roles</li>
     *   <li>Ang El, Angel role</li>
     *   <li>Colleen Up, no roles</li>
     * </ul>
     *
     * @return a list of volunteers to use in testing
     */
    private List<Volunteer> getTestVolunteers() {
        List<Volunteer> volunteers = new LinkedList<>();

        Volunteer volunteer = new Volunteer("Val Unteer", "val@unteer", "555-VAL", "Val!", true);
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);

        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!", true);
        volunteers.add(volunteer);

        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!", true);
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);

        volunteer = new Volunteer("Colleen Up", "colleen@up", "555-COLLEEN", "Colleen!", true);
        volunteers.add(volunteer);

        return volunteers;
    }    // getTestVolunteers()

    /**
     * Returns a list containing the volunteers selected in the event shifts of
     * the given event frame.
     *
     * @param eventFrame the event frame; may not be null
     * @return a list containing the volunteers selected in the event shifts of
     * {@code eventFrame}
     */
    private List<Volunteer> getSelectedVolunteers(EventFrame eventFrame) {
        assert(eventFrame != null);
        Event event = eventFrame.getEvent();
        List<Shift> shifts = event.getShifts();
        List<Volunteer> volunteers = new ArrayList<>();
        for (Shift shift : shifts) {
            volunteers.add(shift.getVolunteer());
        }    // for
        return volunteers;
    }    // getSelectedVolunteers()

    /**
     * Given a list of volunteers, returns a list consisting of the names of the
     * volunteers.
     *
     * @param volunteers the list of volunteers; may not be null
     * @return a list consisting of the names of the elements of
     * {@code volunteers}
     */
    private List<String> getVolunteerNames(List<Volunteer> volunteers) {
       assert (volunteers != null);
       List<String> names = new ArrayList<>();
       for (Volunteer volunteer : volunteers) {
           names.add((volunteer == null) ? null : volunteer.getName());
       }    // for
       return names;
    }    // getVolunteerNames()

    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that {@link EventFrame#EventFrame(bscmail.Application)} throws a
     * {@link NullPointerException} when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicationIsNull() {
        Application application = null;

        EventFrame eventFrame = new EventFrame(application);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that {@link EventFrame#EventFrame(bscmail.Application)} does not
     * throws an exception when application is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenApplicationIsNotNull() {
        Application application = getTestApplication();

        EventFrame eventFrame = new EventFrame(application);
    }    // constructorDoesNotThrowExceptionWhenApplicationIsNotNull()

    /* getEvent */

    /**
     * Tests that {@link EventFrame#getEvent()} does not throw an exception.
     */
    @Test
    public void getEventDoesNotThrowException() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);

        Event event = eventFrame.getEvent();
    }    // getEventDoesNotThrowException()

    /**
     * Tests that {@link EventFrame#getEvent()} does not return null.
     */
    @Test
    public void getEventDoesNotReturnNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);

        Event event = eventFrame.getEvent();

        assertNotNull(event);
    }    // getEventDoesNotReturnNull()

    /**
     * Tests that the event returned by {@link EventFrame#getEvent()} has the
     * list of defined shifts.
     */
    @Test
    public void eventReturnedBuGetEventHasCorrectShifts() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);

        Event event = eventFrame.getEvent();

        List<Shift> expected = application.getShifts();
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // eventReturnedBuGetEventHasCorrectShifts()

    /* setShifts */

    /**
     * Tests that {@link EventFrame#setShifts(List)} does not throw an exception
     * when shifts is null.
     */
    @Test
    public void setShiftsDoesNotThrowExceptionWhenShiftsIsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = null;

        eventFrame.setShifts(shifts);
    }    // setShiftsDoesNotThrowExceptionWhenShiftsIsNull()

    /**
     * Tests that {@link EventFrame#setShifts(List)} throws a
     * {@link NullPointerException} when shifts is not null but contains a null
     * element.
     */
    @Test(expected = NullPointerException.class)
    public void setShiftsThrowsExceptionWhenShiftsContainsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        final int INSERTION_INDEX = 1;
        assertTrue("Test setup error", shifts.size() > INSERTION_INDEX + 1);
        shifts.add(INSERTION_INDEX, null);

        eventFrame.setShifts(shifts);
    }    // setShiftsThrowsExceptionWhenShiftsContainsNull()

    /**
     * Tests that {@link EventFrame#setShifts(List)} does not throw an exception
     * when shifts is not null and does not contains a null element.
     */
    @Test
    public void setShiftsDoesNotThrowExceptionWhenShiftsIsNotNullAndDoesNotContainNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();

        eventFrame.setShifts(shifts);
    }    // setShiftsDoesNotThrowExceptionWhenShiftsIsNotNullAndDoesNotContainNull()

    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument is null.
     */
    @Test
    public void setShiftSetsShiftsWhenShiftsIsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        eventFrame.setShifts(original);
        List<Shift> shifts = null;

        eventFrame.setShifts(shifts);

        Event event = eventFrame.getEvent();
        List<Shift> expected = new ArrayList<>();
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // setShiftSetsShiftsWhenShiftsIsNull()

    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument is empty.
     */
    @Test
    public void setShiftSetsShiftsWhenShiftsIsEmpty() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        eventFrame.setShifts(original);
        List<Shift> shifts = new ArrayList<>();

        eventFrame.setShifts(shifts);

        Event event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // setShiftSetsShiftsWhenShiftsIsEmpty()

    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument has fewer shifts than are in the frame.
     */
    @Test
    public void setShiftSetsShiftsWhenShiftsHasTooFewElements() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        final int ELEMENT_INDEX = 1;
        assertTrue("Test setup error", original.size() > ELEMENT_INDEX + 1);
        eventFrame.setShifts(original);
        List<Shift> shifts = new LinkedList<>();
        shifts.add(original.get(ELEMENT_INDEX));

        eventFrame.setShifts(shifts);

        Event event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // setShiftSetsShiftsWhenShiftsHasTooFewElements()

    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument has the same number of shifts as the frame.
     */
    @Test
    public void setShiftSetsShiftsWhenShiftsHasRightNumberOfElements() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        eventFrame.setShifts(original);
        List<Shift> shifts = getTestShifts();
        Collections.reverse(shifts);

        eventFrame.setShifts(shifts);

        Event event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // setShiftSetsShiftsWhenShiftsHasRightNumberOfElements()

    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument has more shifts that are in the frame.
     */
    @Test
    public void setShiftSetsShiftsWhenShiftsHasTooManyElements() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        eventFrame.setShifts(original);
        List<Shift> shifts = getTestShifts();
        shifts.add(shifts.get(0));

        eventFrame.setShifts(shifts);

        Event event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // setShiftSetsShiftsWhenShiftsHasTooManyElements()

    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the shift roles change.
     */
    @Test
    public void setShiftSetsShiftsWhenShiftRolesChange() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> original = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Arrays.asList(ANGEL_ROLE), false, false, false));
        eventFrame.setShifts(original);
        List<Shift> shifts = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Arrays.asList(ANGEL_ROLE), false, false, false),
                new Shift("Angel", Collections.<Role>emptyList(), false, false, false));

        eventFrame.setShifts(shifts);

        Event event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // setShiftSetsShiftsWhenShiftRolesChange()

    /**
     * Tests that {@link EventFrame#setShifts(List)} retains selections
     * correctly.
     */
    @Test
    public void setShiftsRetainsVolunteerSelections() throws IOException {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> original = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Arrays.asList(ANGEL_ROLE), false, false, false));
        eventFrame.setShifts(original);
        List<Volunteer> volunteers = getTestVolunteers();
        application.setVolunteers(volunteers);
        eventFrame.setVolunteers(volunteers);
        List<Volunteer> selections = volunteers.subList(0, original.size() - 1);
        eventFrame.setSelectedVolunteers(getVolunteerNames(selections));
        List<Shift> shifts = Arrays.asList(
                new Shift("Foo", Collections.<Role>emptyList(), false, false, false),
                new Shift("Bar", Collections.<Role>emptyList(), false, false, false),
                new Shift("Baz", Arrays.asList(ANGEL_ROLE), false, false, false));

        eventFrame.setShifts(shifts);

        Event event = eventFrame.getEvent();
        List<Volunteer> expected = new ArrayList<>(selections);
        while (expected.size() < shifts.size()) {
            expected.add(null);
        }    // while
        List<Volunteer> received = new LinkedList<>();
        for (Shift shift : event.getShifts()) {
            received.add(shift.getVolunteer());
        }    // for
        assertEquals(expected, received);
    }    // setShiftsRetainsVolunteerSelections()

    /**
     * Tests that {@link EventFrame#setShifts(List)} sets selections correctly
     * when the shift roles change.
     */
    @Test
    public void setShiftsSetsVolunteerSelectionsWhenShiftRolesChange() throws IOException {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> original = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Arrays.asList(ANGEL_ROLE), false, false, false));
        eventFrame.setShifts(original);
        List<Volunteer> volunteers = new LinkedList<>();
        Volunteer volunteer = new Volunteer("Val Unteer", "val@unteer", "555-VAL", "Val!", true);
        volunteers.add(volunteer);
        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!", true);
        volunteers.add(volunteer);
        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!", true);
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);
        application.setVolunteers(volunteers);
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(getVolunteerNames(volunteers));
        List<Shift> shifts = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Arrays.asList(ANGEL_ROLE), false, false, false),
                new Shift("Angel", Collections.<Role>emptyList(), false, false, false));

        eventFrame.setShifts(shifts);

        Event event = eventFrame.getEvent();
        List<Volunteer> expected = volunteers;
        expected.set(1, null);
        List<Volunteer> received = new LinkedList<>();
        for (Shift shift : event.getShifts()) {
            received.add(shift.getVolunteer());
        }    // for
        assertEquals(expected, received);
    }    // setShiftsSetsVolunteerSelectionsWhenShiftRolesChange()

    /* setVolunteers */

    /**
     * Tests that {@link EventFrame#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is null.
     */
    @Test(expected = NullPointerException.class)
    public void setVolunteersThrowsExceptionWhenVolunteersIsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = null;

        eventFrame.setVolunteers(volunteers);
    }    // setVolunteersThrowsExceptionWhenVolunteersIsNull()

    /**
     * Tests that {@link EventFrame#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is not null but contains a
     * null element.
     */
    @Test(expected = NullPointerException.class)
    public void setVolunteersThrowsExceptionWhenVolunteersContainsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        final int INSERTION_INDEX = 1;
        assertTrue("Test setup error", volunteers.size() > INSERTION_INDEX + 1);
        volunteers.add(INSERTION_INDEX, null);

        eventFrame.setVolunteers(volunteers);
    }    // setVolunteersThrowsExceptionWhenVolunteersContainsNull()

    /**
     * Tests that {@link EventFrame#setVolunteers(List)} does not throw an
     * exception when volunteers is not null and does not contain a null
     * element.
     */
    @Test
    public void setVolunteersDoesNotThrowExceptionWhenVolunteersIsNotNullNorContainsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();

        eventFrame.setVolunteers(volunteers);
    }    // setVolunteersDoesNotThrowExceptionWhenVolunteersIsNotNullNorContainsNull()

    /**
     * Tests that, after a call to {@link EventFrame#setVolunteers(List)},
     * selected volunteers have the same name as those previously selected.
     */
    @Test
    public void setVolunteersRetainsVolunteerSelectionsByName() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(getVolunteerNames(volunteers));
        List<Volunteer> newVolunteers = getTestVolunteers();
        final int INDEX = 0;
        Volunteer oldVolunteer = newVolunteers.get(INDEX);
        Volunteer newVolunteer = new Volunteer(oldVolunteer.getName(), oldVolunteer.getEmail() + "X", oldVolunteer.getPhone() + "X", oldVolunteer.getNotes() + "X", !oldVolunteer.isActive());
        newVolunteers.set(INDEX, newVolunteer);

        eventFrame.setVolunteers(newVolunteers);

        List<Volunteer> receivedVolunteers = getSelectedVolunteers(eventFrame);
        String expected = oldVolunteer.getName();
        String received = receivedVolunteers.get(INDEX).getName();
        assertEquals(expected, received);
    }    // setVolunteersRetainsVolunteerSelectionsByName()

    /**
     * Tests that, after a call to {@link EventFrame#setVolunteers(List)},
     * selected volunteers are the elements of the argument with the same names
     * as the previously selected volunteers.
     */
    @Test
    public void setVolunteersUpdatesSelectedVolunteers() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(getVolunteerNames(volunteers));
        List<Volunteer> newVolunteers = getTestVolunteers();
        final int INDEX = 0;
        Volunteer oldVolunteer = newVolunteers.get(INDEX);
        Volunteer newVolunteer = new Volunteer(oldVolunteer.getName(), oldVolunteer.getEmail() + "X", oldVolunteer.getPhone() + "X", oldVolunteer.getNotes() + "X", !oldVolunteer.isActive());
        newVolunteers.set(INDEX, newVolunteer);

        eventFrame.setVolunteers(newVolunteers);

        List<Volunteer> receivedVolunteers = getSelectedVolunteers(eventFrame);
        Volunteer expected = newVolunteer;
        Volunteer received = receivedVolunteers.get(INDEX);
        assertEquals(expected, received);
    }    // setVolunteersUpdatesSelectedVolunteers()

    /**
     * Tests that, after a call to {@link EventFrame#setVolunteers(List)}, the
     * selection is cleared if no element of the argument has the same name as
     * the selection.
     */
    @Test
    public void setVolunteersClearsSelectedVolunteersWhenNeeded() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = new LinkedList<>();
        Volunteer volunteer = new Volunteer("Val Unteer", "val@unteer", "555-VAL", "Val!", true);
        volunteers.add(volunteer);
        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!", true);
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);
        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!", true);
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(getVolunteerNames(volunteers));
        List<Volunteer> newVolunteers = new LinkedList<>();
        volunteer = new Volunteer("Foor", "val@unteer", "555-VAL", "Val!", true);
        newVolunteers.add(volunteer);
        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!", true);
        volunteer.addRole(ANGEL_ROLE);
        newVolunteers.add(volunteer);
        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!", true);
        volunteer.addRole(ANGEL_ROLE);
        newVolunteers.add(volunteer);
        final int INDEX_AT_WHICH_VOLUNTEER_LISTS_DIFFER = 0;

        eventFrame.setVolunteers(newVolunteers);

        List<Volunteer> receivedVolunteers = getSelectedVolunteers(eventFrame);
        Volunteer received = receivedVolunteers.get(INDEX_AT_WHICH_VOLUNTEER_LISTS_DIFFER);
        assertNull(received);
    }    // setVolunteersClearsSelectedVolunteersWhenNeeded()

    /* setSelectedVolunteers */

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers is null.
     */
    @Test
    public void setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersIsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = null;

        eventFrame.setSelectedVolunteers(volunteers);
    }    // setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersIsNull()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers is null.
     */
    @Test
    public void setSelectedVolunteersSetsVolunteersCorrectlyWhenVolunteersIsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        final int SIZE = shifts.size();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = null;

        eventFrame.setSelectedVolunteers(volunteers);

        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        List<Volunteer> expected = Collections.nCopies(SIZE, null);
        assertEquals(expected, received);
    }    // setSelectedVolunteersSetsVolunteersCorrectlyWhenVolunteersIsNull()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers is empty.
     */
    @Test
    public void setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersIsEmpty() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        final int SIZE = shifts.size();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = new ArrayList<>();

        getSelectedVolunteers(eventFrame);
    }    // setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersIsEmpty()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers is empty.
     */
    @Test
    public void setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersIsEmpty() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        final int SIZE = shifts.size();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = new ArrayList<>();

        eventFrame.setSelectedVolunteers(volunteers);

        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        List<Volunteer> expected = Collections.nCopies(SIZE, null);
        assertEquals(expected, received);
    }    // setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersIsEmpty()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers contains a null element.
     */
    @Test
    public void setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersContainsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        volunteers.add(0, null);
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);
    }    // setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersContainsNull()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers contains a null element.
     */
    @Test
    public void setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersContainsNull() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        volunteers.add(0, null);
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);

        List<Volunteer> expected = new ArrayList<>(getTestVolunteers());
        Collections.reverse(expected);
        expected.add(0, null);
        expected = expected.subList(0, SIZE);
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        assertEquals(expected, received);
    }    // setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersContainsNull()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers has fewer elements than there are shifts.
     */
    @Test
    public void setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersHasTooFewElements() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int SIZE = shifts.size() - 1;
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);
    }    // setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersHasTooFewElements()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers has fewer elements than
     * there are shifts.
     */
    @Test
    public void setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersHasTooFewElements() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int SIZE = shifts.size() - 1;
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);

        List<Volunteer> expected = new ArrayList<>(getTestVolunteers());
        Collections.reverse(expected);
        expected = expected.subList(0, SIZE);
        while (expected.size() < shifts.size()) {
            expected.add(null);
        }    // while
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        assertEquals(expected, received);
    }    // setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersHasTooFewElements()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers has the same number of elements as there are
     * shifts.
     */
    @Test
    public void setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersHasRightNumberOfElements() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);
    }    // setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersHasRightNumberOfElements()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers has the same number of
     * elements as there are shifts.
     */
    @Test
    public void setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersHasRightNumberOfElements() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Collections.<Role>emptyList(), false, false, false));
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);

        List<Volunteer> expected = new ArrayList<>(getTestVolunteers());
        Collections.reverse(expected);
        expected = expected.subList(0, SIZE);
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        assertEquals(expected, received);
    }    // setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersHasRightNumberOfElements()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers has more elements than there are shifts.
     */
    @Test
    public void setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersHasTooManyElements() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int SIZE = shifts.size() + 1;
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);
    }    // setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersHasTooManyElements()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers has more elements than
     * there are shifts.
     */
    @Test
    public void setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersHasTooManyElements() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Collections.<Role>emptyList(), false, false, false));
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int SIZE = shifts.size() + 1;
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);

        List<Volunteer> expected = new ArrayList<>(getTestVolunteers());
        Collections.reverse(expected);
        expected = expected.subList(0, shifts.size());
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        assertEquals(expected, received);
    }    // setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersHasTooManyElements()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when the volunteers do not exist in the combobox lists.
     */
    @Test
    public void setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersElementsDoNotExistInEvent() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int INDEX = 0;
        volunteers.set(INDEX, "foo");
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);
    }    // setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersElementsDoNotExistInEvent()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when the volunteers do not exist in the
     * combobox lists.
     */
    @Test
    public void setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersElementsDoNotExistInEvent() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Collections.<Role>emptyList(), false, false, false));
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int INDEX = 0;
        volunteers.set(INDEX, "foo");
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);

        List<Volunteer> expected = new ArrayList<>(getTestVolunteers());
        Collections.reverse(expected);
        expected.set(INDEX, null);
        expected = expected.subList(0, SIZE);
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        assertEquals(expected, received);
    }    // setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersElementsDoNotExistInEvent()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when the volunteers have improper roles.
     */
    @Test
    public void setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersHaveImproperRoles() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        final int ANGEL_SHIFT_INDEX = 2;
        final int NON_ANGEL_VOLUNTEER_INDEX = 3;
        volunteers.set(ANGEL_SHIFT_INDEX, volunteers.get(NON_ANGEL_VOLUNTEER_INDEX));
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteers);
    }    // setSelectedVolunteersDoesNotThrowExceptionWhenVolunteersHaveImproperRoles()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when trying to fit a volunteer who has the
     * wrong roles into a shift.
     */
    @Test
    public void setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersHaveImproperRoles() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> shifts = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Arrays.asList(ANGEL_ROLE), false, false, false));

        List<Volunteer> volunteers = new LinkedList<>();
        Volunteer volunteer = new Volunteer("Val Unteer", "val@unteer", "555-VAL", "Val!", true);
        volunteers.add(volunteer);
        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!", true);
        volunteers.add(volunteer);
        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!", true);
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);
        volunteer = new Volunteer("Colleen Up", "colleen@up", "555-COLLEEN", "Colleen!", true);
        volunteers.add(volunteer);
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(volunteers);
        List<String> volunteerNames = getVolunteerNames(volunteers);
        final int ANGEL_SHIFT_INDEX = 2;
        final int NON_ANGEL_VOLUNTEER_INDEX = 3;
        volunteerNames.set(ANGEL_SHIFT_INDEX, volunteers.get(NON_ANGEL_VOLUNTEER_INDEX).getName());
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteerNames = volunteerNames.subList(0, SIZE);

        eventFrame.setSelectedVolunteers(volunteerNames);

        List<Volunteer> expected = new ArrayList<>(volunteers);
        expected.set(ANGEL_SHIFT_INDEX, null);
        expected = expected.subList(0, SIZE);
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        assertEquals(expected, received);
    }    // setSelectedVolunteersSetsSelectionsCorrectlyWhenVolunteersHaveImproperRoles()

    /* shiftsChanged */

    /**
     * Tests that {@link EventFrame#shiftsChanged()} does not throw an
     * exception.
     */
    @Test
    public void shiftsChangedDoesNotThrowException() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);

        eventFrame.shiftsChanged();
    }    // shiftsChangedDoesNotThrowException()

    /**
     * Tests that {@link EventFrame#shiftsChanged()} fires and works properly
     * when {@link Application#setShifts(List)} is called.
     */
    @Test
    public void shiftsChangedFiresAndWorksWhenSetShiftsIsCalled() throws IOException {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Shift> originalShifts = getTestShifts();
        eventFrame.setShifts(originalShifts);
        Event event = eventFrame.getEvent();
        List<Shift> shifts = new ArrayList<>(event.getShifts());
        assert (shifts != null);
        shifts.add(new Shift("Foo", Collections.<Role>emptyList(), false, false, false));

        application.setShifts(shifts);

        event = eventFrame.getEvent();
        List<Shift> received = event.getShifts();
        List<Shift> expected = shifts;
        assertEquals(expected, received);
    }    // shiftsChangedFiresAndWorksWhenSetShiftsIsCalled()

    /* volunteersChanged */

    /**
     * Tests that {@link EventFrame#volunteersChanged()} does not throw an
     * exception.
     */
    @Test
    public void volunteersChangedDoesNotThrowExxception() {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);

        eventFrame.volunteersChanged();
    }    // volunteersChangedDoesNotThrowExxception()

    /**
     * Tests that {@link EventFrame#volunteersChanged()} fires and works
     * properly when {@link Application#setVolunteers(List)} is called.
     */
    @Test
    public void volunteersChangedFiresAndWorksWhenSetShiftsIsCalled() throws IOException {
        Application application = getTestApplication();
        EventFrame eventFrame = new EventFrame(application);
        List<Volunteer> originalVolunteers = getTestVolunteers();
        eventFrame.setVolunteers(originalVolunteers);
        eventFrame.setSelectedVolunteers(Arrays.asList(originalVolunteers.get(0).getName()));
        Event event = eventFrame.getEvent();
        List<Shift> shifts = event.getShifts();
        Volunteer originalVolunteer = shifts.get(0).getVolunteer();
        assert (originalVolunteer != null);
        List<Volunteer> volunteers = new ArrayList<>();

        application.setVolunteers(volunteers);

        event = eventFrame.getEvent();
        shifts = event.getShifts();
        Volunteer received = shifts.get(0).getVolunteer();
        Volunteer expected = null;
        assertEquals(expected, received);
    }    // volunteersChangedFiresAndWorksWhenSetShiftsIsCalled()

}    // EventFrameTest
