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
import java.io.IOException;
import java.util.*;
import bscmail.Application;
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
     * Variable used to hold the event frame being tested.
     */
    private EventFrame eventFrame;

    /**
     * Variable used to hold the application used in testing.
     */
    private Application application;

    /**
     * Variable used to hold the event used in testing.
     */
    private Event event;

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

        Volunteer volunteer = new Volunteer("Val Unteer", "val@unteer", "555-VAL", "Val!");
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);

        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!");
        volunteers.add(volunteer);

        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!");
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);

        volunteer = new Volunteer("Colleen Up", "colleen@up", "555-COLLEEN", "Colleen!");
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
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("EventFrame");
        System.out.println("==========");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass(

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        eventFrame = null;
        application = null;
        event = null;
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        eventFrame = null;
        application = null;
        event = null;
    }    // tearDown()

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
        application = null;
        eventFrame = new EventFrame(application);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that {@link EventFrame#EventFrame(bscmail.Application)} does not
     * throws an exception when application is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenApplicationIsNotNull() {
        application = new Application();
        eventFrame = new EventFrame(application);
    }    // constructorDoesNotThrowExceptionWhenApplicationIsNotNull()
    
    /* getEvent */

    /**
     * Tests that {@link EventFrame#getEvent()} does not throw an exception.
     */
    @Test
    public void testGetEventNoException() {
        System.out.println("getEvent - no event");
        application = new Application();
        eventFrame = new EventFrame(application);
        event = eventFrame.getEvent();
    }    // testGetEventNoException()
    
    /**
     * Tests that {@link EventFrame#getEvent()} does not return null.
     */
    @Test
    public void testGetEventDoesNotReturnNull() {
        System.out.println("getEvent - does not return null");
        application = new Application();
        eventFrame = new EventFrame(application);
        event = eventFrame.getEvent();
        assertNotNull(event);
    }    // testGetEventDoesNotReturnNull()
    
    /**
     * Tests that the event returned by {@link EventFrame#getEvent()} has the
     * list of defined shifts.
     */
    @Test
    public void testGetEventCorrectShifts() {
        System.out.println("getEvent - correct shifts");
        application = new Application();
        eventFrame = new EventFrame(application);
        event = eventFrame.getEvent();
        List<Shift> expected = application.getShifts();
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testGetEventCorrectShifts()

    /* setShifts */
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} does not throw an exception
     * when shifts is null.
     */
    @Test
    public void testSetShiftsNullNoException() {
        System.out.println("setShifts - shifts is null, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = null;
        eventFrame.setShifts(shifts);
    }    // testSetShiftsNullNoException()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} throws a
     * {@link NullPointerException} when shifts is not null but contains a null
     * element.
     */
    @Test(expected = NullPointerException.class)
    public void testSetShiftsContainsNull() {
        System.out.println("setShifts - shifts contains null");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        final int INSERTION_INDEX = 1;
        assertTrue("Test setup error", shifts.size() > INSERTION_INDEX + 1);
        shifts.add(INSERTION_INDEX, null);
        eventFrame.setShifts(shifts);
    }    // testSetShiftsContainsNull()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} does not throw an exception
     * when shifts is not null and does not contains a null element.
     */
    @Test
    public void testSetShiftsNotNullNoException() {
        System.out.println("setShifts - shifts is not null, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
    }    // testSetShiftsNotNullNoException()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument is null.
     */
    @Test
    public void testSetShiftsNull() {
        System.out.println("setShifts - shifts is null");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        eventFrame.setShifts(original);
        List<Shift> shifts = null;
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Shift> expected = new ArrayList<>();
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testSetShiftsNull()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument is empty.
     */
    @Test
    public void testSetShiftsEmpty() {
        System.out.println("setShifts - shifts is empty");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        eventFrame.setShifts(original);
        List<Shift> shifts = new ArrayList<>();
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testSetShiftsEmpty()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument has fewer shifts than are in the frame.
     */
    @Test
    public void testSetShiftsFewerShifts() {
        System.out.println("setShifts - shifts is smaller than those in frame");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        final int ELEMENT_INDEX = 1;
        assertTrue("Test setup error", original.size() > ELEMENT_INDEX + 1);
        eventFrame.setShifts(original);
        List<Shift> shifts = new LinkedList<>();
        shifts.add(original.get(ELEMENT_INDEX));
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testSetShiftsFewerShifts()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument has the same number of shifts as the frame.
     */
    @Test
    public void testSetShiftsSameNumberOfShifts() {
        System.out.println("setShifts - shifts is as large as those in frame");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        eventFrame.setShifts(original);
        List<Shift> shifts = getTestShifts();
        Collections.reverse(shifts);
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testSetShiftsSameNumberOfShifts()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument has more shifts that are in the frame.
     */
    @Test
    public void testSetShiftsMoreShifts() {
        System.out.println("setShifts - shifts is larger than those in frame");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> original = getTestShifts();
        eventFrame.setShifts(original);
        List<Shift> shifts = getTestShifts();
        shifts.add(shifts.get(0));
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testSetShiftsMoreShifts()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the shift roles change.
     */
    @Test
    public void testSetShiftsShiftRolesChange() {
        System.out.println("setShifts - shift roles change");
        application = new Application();
        eventFrame = new EventFrame(application);
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
        event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testSetShiftsShiftRolesChange()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} retains selections
     * correctly.
     */
    @Test
    public void testSetShiftsRetainsSelections() {
        System.out.println("setShifts - retains selections");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> original = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Arrays.asList(ANGEL_ROLE), false, false, false));
        eventFrame.setShifts(original);
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
        List<Volunteer> selections = volunteers.subList(0, original.size() - 1);
        eventFrame.setSelectedVolunteers(getVolunteerNames(selections));
        List<Shift> shifts = Arrays.asList(
                new Shift("Foo", Collections.<Role>emptyList(), false, false, false),
                new Shift("Bar", Collections.<Role>emptyList(), false, false, false),
                new Shift("Baz", Arrays.asList(ANGEL_ROLE), false, false, false));
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Volunteer> expected = new ArrayList<>(selections);
        while (expected.size() < shifts.size()) {
            expected.add(null);
        }    // while
        List<Volunteer> received = new LinkedList<>();
        for (Shift shift : event.getShifts()) {
            received.add(shift.getVolunteer());
        }    // for
        assertEquals(expected, received);
    }    // testSetShiftsCanAngelChanges()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} retains selections
     * correctly when the shift roles change.
     */
    @Test
    public void testSetShiftsRetainsSelectionsWhenShiftRolesChange() {
        System.out.println("setShifts - retains selections when shift roles change");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> original = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Arrays.asList(ANGEL_ROLE), false, false, false));
        eventFrame.setShifts(original);

        List<Volunteer> volunteers = new LinkedList<>();
        Volunteer volunteer = new Volunteer("Val Unteer", "val@unteer", "555-VAL", "Val!");
        volunteers.add(volunteer);
        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!");
        volunteers.add(volunteer);
        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!");
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);

        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(getVolunteerNames(volunteers));
        List<Shift> shifts = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Arrays.asList(ANGEL_ROLE), false, false, false),
                new Shift("Angel", Collections.<Role>emptyList(), false, false, false));
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Volunteer> expected = volunteers;
        expected.set(1, null);
        List<Volunteer> received = new LinkedList<>();
        for (Shift shift : event.getShifts()) {
            received.add(shift.getVolunteer());
        }    // for
        assertEquals(expected, received);
    }    // testSetShiftsRetainsSelectionsWhenShiftRolesChange()

    /* setVolunteers */
    
    /**
     * Tests that {@link EventFrame#setVolunteers(List)} does not throw an
     * exception when volunteers is null.
     */
    @Test
    public void testSetVolunteersNullNoException() {
        System.out.println("setVolunteers - volunteers is null, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = null;
        eventFrame.setVolunteers(volunteers);
    }    // testSetVolunteersNullNoException()
    
    /**
     * Tests that {@link EventFrame#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is not null but contains a
     * null element.
     */
    @Test(expected = NullPointerException.class)
    public void testSetVolunteersContainsNull() {
        System.out.println("setVolunteers - volunteers contains null");
        application = new Application();
        eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        final int INSERTION_INDEX = 1;
        assertTrue("Test setup error", volunteers.size() > INSERTION_INDEX + 1);
        volunteers.add(INSERTION_INDEX, null);
        eventFrame.setVolunteers(volunteers);
    }    // testSetVolunteersContainsNull()
    
    /**
     * Tests that {@link EventFrame#setVolunteers(List)} does not throw an
     * exception when volunteers is not null and does not contain a null
     * element.
     */
    @Test
    public void testSetVolunteersNotNullNoException() {
        System.out.println("setVolunteers - volunteers is not null, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
    }    // testSetVolunteersNotNullNoException()
    
    /**
     * Tests that, after a call to {@link EventFrame#setVolunteers(List)}, 
     * selected volunteers have the same name as those previously selected.
     */
    @Test
    public void testSetVolunteersNewVolunteersHasSameName() {
        System.out.println("setVolunteers - selected volunteers have same name as those previously selected");
        application = new Application();
        eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(getVolunteerNames(volunteers));
        List<Volunteer> newVolunteers = getTestVolunteers();
        final int INDEX = 0;
        Volunteer oldVolunteer = newVolunteers.get(INDEX);
        Volunteer newVolunteer = new Volunteer(oldVolunteer.getName(), oldVolunteer.getEmail() + "X", oldVolunteer.getPhone() + "X", oldVolunteer.getNotes() + "X");
        newVolunteers.set(INDEX, newVolunteer);
        eventFrame.setVolunteers(newVolunteers);
        List<Volunteer> receivedVolunteers = getSelectedVolunteers(eventFrame);
        String expected = oldVolunteer.getName();
        String received = receivedVolunteers.get(INDEX).getName();
        assertEquals(expected, received);
    }    // testSetVolunteersNewVolunteersHasSameName()

    /**
     * Tests that, after a call to {@link EventFrame#setVolunteers(List)}, 
     * selected volunteers are the elements of the argument with the same names
     * as the previously selected volunteers.
     */
    @Test
    public void testSetVolunteersNewVolunteersAreFromList() {
        System.out.println("setVolunteers - selected volunteers are from the lsit");
        application = new Application();
        eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(getVolunteerNames(volunteers));
        List<Volunteer> newVolunteers = getTestVolunteers();
        final int INDEX = 0;
        Volunteer oldVolunteer = newVolunteers.get(INDEX);
        Volunteer newVolunteer = new Volunteer(oldVolunteer.getName(), oldVolunteer.getEmail() + "X", oldVolunteer.getPhone() + "X", oldVolunteer.getNotes() + "X");
        newVolunteers.set(INDEX, newVolunteer);
        eventFrame.setVolunteers(newVolunteers);
        List<Volunteer> receivedVolunteers = getSelectedVolunteers(eventFrame);
        Volunteer expected = newVolunteer;
        Volunteer received = receivedVolunteers.get(INDEX);
        assertEquals(expected, received);
    }    // testSetVolunteersNewVolunteersAreFromList()

    /**
     * Tests that, after a call to {@link EventFrame#setVolunteers(List)}, the
     * selection is cleared if no element of the argument has the same name as
     * the selection.
     */
    @Test
    public void testSetVolunteersClearsSelection() {
        System.out.println("setVolunteers - selected volunteers are cleared");
        application = new Application();
        eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());

        List<Volunteer> volunteers = new LinkedList<>();
        Volunteer volunteer = new Volunteer("Val Unteer", "val@unteer", "555-VAL", "Val!");
        volunteers.add(volunteer);
        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!");
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);
        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!");
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);

        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(getVolunteerNames(volunteers));

        List<Volunteer> newVolunteers = new LinkedList<>();
        volunteer = new Volunteer("Foor", "val@unteer", "555-VAL", "Val!");
        newVolunteers.add(volunteer);
        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!");
        volunteer.addRole(ANGEL_ROLE);
        newVolunteers.add(volunteer);
        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!");
        volunteer.addRole(ANGEL_ROLE);
        newVolunteers.add(volunteer);

        final int INDEX_AT_WHICH_VOLUNTEER_LISTS_DIFFER = 0;
        eventFrame.setVolunteers(newVolunteers);
        List<Volunteer> receivedVolunteers = getSelectedVolunteers(eventFrame);
        Volunteer received = receivedVolunteers.get(INDEX_AT_WHICH_VOLUNTEER_LISTS_DIFFER);
        assertNull(received);
    }    // testSetVolunteersClearsSelection()
    
    /* setSelectedVolunteers */
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers is null.
     */
    @Test
    public void testSetSelectedVolunteersNullNoException() {
        System.out.println("setSelectedVolunteers - volunteers is null, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        eventFrame.setShifts(getTestShifts());
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = null;
        eventFrame.setSelectedVolunteers(volunteers);
    }    // testSetSelectedVolunteersNullNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers contains a null element.
     */
    @Test
    public void testSetSelectedVolunteersContainsNullNoException() {
        System.out.println("setSelectedVolunteers - volunteers contains null, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        volunteers.add(0, null);
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);
        eventFrame.setSelectedVolunteers(volunteers);
    }    // testSetSelectedVolunteersContainsNullNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers has fewer elements than there are shifts.
     */
    @Test
    public void testSetSelectedVolunteersSmallNoException() {
        System.out.println("setSelectedVolunteers - volunteers is smaller than the number of shifts, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int SIZE = shifts.size() - 1;
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);
        eventFrame.setSelectedVolunteers(volunteers);
    }    // testSetSelectedVolunteersSmallNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers has the same number of elements as there are
     * shifts.
     */
    @Test
    public void testSetSelectedVolunteersJustRightNoException() {
        System.out.println("setSelectedVolunteers - volunteers is the same size as the number of shifts, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int SIZE = shifts.size();
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);
        eventFrame.setSelectedVolunteers(volunteers);
    }    // testSetSelectedVolunteersJustRightNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when volunteers has more elements than there are shifts.
     */
    @Test
    public void testSetSelectedVolunteersLargeNoException() {
        System.out.println("setSelectedVolunteers - volunteers is larger than the number of shifts, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = getVolunteerNames(getTestVolunteers());
        Collections.reverse(volunteers);
        final int SIZE = shifts.size() + 1;
        assert (volunteers.size() >= SIZE);
        volunteers = volunteers.subList(0, SIZE);
        eventFrame.setSelectedVolunteers(volunteers);
    }    // testSetSelectedVolunteersLargeNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when the volunteers do not exist in the combobox lists.
     */
    @Test
    public void testSetSelectedVolunteersDoNotCorrespondNoException() {
        System.out.println("setSelectedVolunteers - volunteers do not correspond, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
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
    }    // testSetSelectedVolunteersDoNotCorrespondNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} does not throw
     * an exception when trying to fit a volunteer who is not and angel into an
     * angel shift.
     */
    @Test
    public void testSetSelectedVolunteersAngelConflictNoException() {
        System.out.println("setSelectedVolunteers - angel conflict, no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
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
    }    // testSetSelectedVolunteersAngelConflictNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers is null.
     */
    @Test
    public void testSetSelectedVolunteersNull() {
        System.out.println("setSelectedVolunteers - volunteers is null");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        final int SIZE = shifts.size();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = null;
        
        List<Volunteer> expected = Collections.nCopies(SIZE, null);
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        eventFrame.setSelectedVolunteers(volunteers);
        assertEquals(expected, received);
    }    // testSetSelectedVolunteersNull()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers is empty.
     */
    @Test
    public void testSetSelectedVolunteersEmpty() {
        System.out.println("setSelectedVolunteers - volunteers is empty");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = getTestShifts();
        final int SIZE = shifts.size();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = new ArrayList<>();
        
        List<Volunteer> expected = Collections.nCopies(SIZE, null);
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        eventFrame.setSelectedVolunteers(volunteers);
        assertEquals(expected, received);
    }    // testSetSelectedVolunteersEmpty()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers contains a null element.
     */
    @Test
    public void testSetSelectedVolunteersContainsNull() {
        System.out.println("setSelectedVolunteers - volunteers contains null");
        application = new Application();
        eventFrame = new EventFrame(application);
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
    }    // testSetSelectedVolunteersContainsNull()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers has fewer elements than
     * there are shifts.
     */
    @Test
    public void testSetSelectedVolunteersSmall() {
        System.out.println("setSelectedVolunteers - volunteers is smaller than the number of shifts");
        application = new Application();
        eventFrame = new EventFrame(application);
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
    }    // testSetSelectedVolunteersSmall()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers has the same number of
     * elements as there are shifts.
     */
    @Test
    public void testSetSelectedVolunteersJustRight() {
        System.out.println("setSelectedVolunteers - volunteers is the same size as the number of shifts");
        application = new Application();
        eventFrame = new EventFrame(application);
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
    }    // testSetSelectedVolunteersJustRight()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when volunteers has more elements than
     * there are shifts.
     */
    @Test
    public void testSetSelectedVolunteersLarge() {
        System.out.println("setSelectedVolunteers - volunteers is larger than the number of shifts");
        application = new Application();
        eventFrame = new EventFrame(application);
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
    }    // testSetSelectedVolunteersLarge()

    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when the volunteers do not exist in the
     * combobox lists.
     */
    @Test
    public void testSetSelectedVolunteersDoNotCorrespond() {
        System.out.println("setSelectedVolunteers - volunteers do not correspond");
        application = new Application();
        eventFrame = new EventFrame(application);
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
    }    // testSetSelectedVolunteersDoNotCorrespond()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when trying to fit a volunteer who has the
     * wrong roles into a shift.
     */
    @Test
    public void testSetSelectedVolunteersRoleConflict() {
        System.out.println("setSelectedVolunteers - role conflict");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> shifts = Arrays.asList(
                new Shift("Door 1", Collections.<Role>emptyList(), false, false, false),
                new Shift("Door 2", Collections.<Role>emptyList(), false, false, false),
                new Shift("Angel", Arrays.asList(ANGEL_ROLE), false, false, false));

        List<Volunteer> volunteers = new LinkedList<>();
        Volunteer volunteer = new Volunteer("Val Unteer", "val@unteer", "555-VAL", "Val!");
        volunteers.add(volunteer);
        volunteer = new Volunteer("Halva Ticket", "halva@ticket", "555-HALVA", "Halva!");
        volunteers.add(volunteer);
        volunteer = new Volunteer("Ang El", "ang@el", "555-ANG", "Ang!");
        volunteer.addRole(ANGEL_ROLE);
        volunteers.add(volunteer);
        volunteer = new Volunteer("Colleen Up", "colleen@up", "555-COLLEEN", "Colleen!");
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
    }    // testSetSelectedVolunteersRoleConflict()

    /* shiftsChanged */
    
    /**
     * Tests that {@link EventFrame#shiftsChanged()} does not throw an
     * exception.
     */
    @Test
    public void testShiftsChangedNoException() {
        System.out.println("shiftsChanged - no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        eventFrame.shiftsChanged();
    }    // testShiftsChangedNoException()
    
    /**
     * Tests that {@link EventFrame#shiftsChanged()} fires and works properly
     * when {@link Application#setShifts(List)} is called.
     */
    @Test
    public void testShiftsChanged() throws IOException {
        System.out.println("shiftsChanged");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Shift> originalShifts = getTestShifts();
        eventFrame.setShifts(originalShifts);
        event = eventFrame.getEvent();
        List<Shift> shifts = new ArrayList<>(event.getShifts());
        assert (shifts != null);
        shifts.add(new Shift("Foo", Collections.<Role>emptyList(), false, false, false));
        application.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testShiftsChanged()

    /* volunteersChanged */
    
    /**
     * Tests that {@link EventFrame#volunteersChanged()} does not throw an
     * exception.
     */
    @Test
    public void testVolunteersChangedNoException() {
        System.out.println("volunteersChanged - no exception");
        application = new Application();
        eventFrame = new EventFrame(application);
        eventFrame.volunteersChanged();
    }    // testVolunteersChangedNoException()
    
    /**
     * Tests that {@link EventFrame#volunteersChanged()} fires and works
     * properly when {@link Application#setVolunteers(List)} is called.
     */
    @Test
    public void testVolunteersChanged() throws IOException {
        System.out.println("volunteersChanged");
        application = new Application();
        eventFrame = new EventFrame(application);
        List<Volunteer> originalVolunteers = getTestVolunteers();
        eventFrame.setVolunteers(originalVolunteers);
        eventFrame.setSelectedVolunteers(Arrays.asList(originalVolunteers.get(0).getName()));
        event = eventFrame.getEvent();
        List<Shift> shifts = event.getShifts();
        Volunteer originalVolunteer = shifts.get(0).getVolunteer();
        assert (originalVolunteer != null);
        List<Volunteer> volunteers = new ArrayList<>();
        application.setVolunteers(volunteers);
        event = eventFrame.getEvent();
        shifts = event.getShifts();
        Volunteer expected = null;
        Volunteer received = shifts.get(0).getVolunteer();
        assertEquals(expected, received);
    }    // testVolunteersChanged()
    
}    // EventFrameTest
