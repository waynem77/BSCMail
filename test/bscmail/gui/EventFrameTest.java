/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package bscmail.gui;

import bscmail.*;
import java.io.IOException;
import java.util.*;
import main.Application;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EventFrame}.
 * 
 * @author Wayne Miller
 */
public class EventFrameTest {

    /**
     * Variable used to hold the event frame being tested.
     */
    private EventFrame eventFrame;
    
    /**
     * Variable used to hold the event used in testing.
     */
    private Event event;

    /**
     * Returns a list of shifts to use in testing.
     * 
     * The shifts in the list are:
     * <ul>
     *   <li>Door 1, false</li>
     *   <li>Door 2, false</li>
     *   <li>Angel, true</li>
     * </ul>
     * 
     * @return a list of shifts to use in testing
     */
    private List<Shift> getTestShifts() {
        return Arrays.asList(
                new Shift("Door 1", false),
                new Shift("Door 2", false),
                new Shift("Angel", true)
        );    // asList
    }    // getTestShifts()
    
    /**
     * Returns a list of managers to use in testing.
     * 
     * The managers in the list are:
     * <ul>
     *   <li>Manny Ager</li>
     *   <li>Courtney Nader</li>
     * </ul>
     * 
     * @return a list of managers to use in testing
     */
    private List<Manager> getTestManagers() {
        return Arrays.asList(
                new Manager("Manny Ager", "manny@ager", "555-MANNY"),
                new Manager("Courtney Nader", "courtney@nader", "555-COURTNEY")
        );    // asList
    }    // getTestManagers()
    
    /**
     * Returns a list of volunteers to use in testing.
     * 
     * The volunteers in the list are:
     * <ul>
     *   <li>Val Unteer, true</li>
     *   <li>Halva Ticket, true</li>
     *   <li>Ang El, true</li>
     *   <li>Colleen Up, false</li>
     * </ul>
     * 
     * @return a list of volunteers to use in testing
     */
    private List<Volunteer> getTestVolunteers() {
        return Arrays.asList(
                new Volunteer("Val Unteer", "val@unteer", true),
                new Volunteer("Halva Ticket", "halva@ticket", true),
                new Volunteer("Ang El", "ang@el", true),
                new Volunteer("Colleen Up", "colleen@up", false)
        );    // asList
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
        Application.setTestMode(true);
        eventFrame = null;
        event = null;
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        Application.setTestMode(false);
        eventFrame = null;
        event = null;
    }    // tearDown()

    /*
     * Unit tests
     */
    
    /* constructor */

    /**
     * Tests that {@link EventFrame#EventFrame()} does not throw an exception.
     */
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");

        eventFrame = new EventFrame();
    }    // testConstructorNoException()
    
    /* getEvent */
    
    /**
     * Tests that {@link EventFrame#getEvent()} does not throw an exception.
     */
    @Test
    public void testGetEventNoException() {
        System.out.println("getEvent - no event");
        eventFrame = new EventFrame();
        event = eventFrame.getEvent();
    }    // testGetEventNoException()
    
    /**
     * Tests that {@link EventFrame#getEvent()} does not return null.
     */
    @Test
    public void testGetEventDoesNotReturnNull() {
        System.out.println("getEvent - does not return null");
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
        event = eventFrame.getEvent();
        List<Shift> expected = Application.getShifts();
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
        List<Shift> shifts = Arrays.asList(new Shift("foo", true), null, new Shift("bar", false));
        eventFrame.setShifts(shifts);
    }    // testSetShiftsContainsNull()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} does not throw an exception
     * when shifts is not null and does not contains a null element.
     */
    @Test
    public void testSetShiftsNotNullNoException() {
        System.out.println("setShifts - shifts is not null, no exception");
        eventFrame = new EventFrame();
        List<Shift> shifts = Arrays.asList(new Shift("foo", true), new Shift("bar", false));
        eventFrame.setShifts(shifts);
    }    // testSetShiftsNotNullNoException()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the argument is null.
     */
    @Test
    public void testSetShiftsNull() {
        System.out.println("setShifts - shifts is null");
        eventFrame = new EventFrame();
        List<Shift> original = Arrays.asList(new Shift("one", false), new Shift("two", false));
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
        eventFrame = new EventFrame();
        List<Shift> original = Arrays.asList(new Shift("one", false), new Shift("two", false));
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
        eventFrame = new EventFrame();
        List<Shift> original = Arrays.asList(new Shift("one", false), new Shift("two", false));
        eventFrame.setShifts(original);
        List<Shift> shifts = Arrays.asList(new Shift("foo", true));
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
        eventFrame = new EventFrame();
        List<Shift> original = Arrays.asList(new Shift("one", false), new Shift("two", false));
        eventFrame.setShifts(original);
        List<Shift> shifts = Arrays.asList(new Shift("foo", false), new Shift("bar", false));
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
        eventFrame = new EventFrame();
        List<Shift> original = Arrays.asList(new Shift("one", false), new Shift("two", false));
        eventFrame.setShifts(original);
        List<Shift> shifts = Arrays.asList(new Shift("foo", false), new Shift("bar", false), new Shift("baz", false));
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testSetShiftsMoreShifts()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} sets the shifts correctly
     * when the "is angel shift" status of the shifts changes.
     */
    @Test
    public void testSetShiftsIsAngelChanges() {
        System.out.println("setShifts - is angel shift status changes");
        eventFrame = new EventFrame();
        List<Shift> original = Arrays.asList(new Shift("one", true), new Shift("two", false));
        eventFrame.setShifts(original);
        List<Shift> shifts = Arrays.asList(new Shift("foo", false), new Shift("bar", true));
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testSetShiftsIsAngelChanges()
    
    /**
     * Tests that {@link EventFrame#setShifts(List)} retains selections
     * correctly.
     */
    @Test
    public void testSetShiftsRetainsSelections() {
        System.out.println("setShifts - retains selections");
        eventFrame = new EventFrame();
        List<Shift> original = Arrays.asList(new Shift("one", true), new Shift("two", false));
        eventFrame.setShifts(original);
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
        List<Volunteer> selections = volunteers.subList(0, original.size() - 1);
        eventFrame.setSelectedVolunteers(getVolunteerNames(selections));
        List<Shift> shifts = Arrays.asList(new Shift("foo", false), new Shift("bar", true));
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
     * correctly when the "is angel shift" status changes..
     */
    @Test
    public void testSetShiftsRetainsSelectionsWhenIsAngelChanges() {
        System.out.println("setShifts - retains selections when is angel status changes");
        eventFrame = new EventFrame();
        List<Shift> original = Arrays.asList(new Shift("one", true), new Shift("two", false));
        eventFrame.setShifts(original);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Angie El", "angie@el", true), new Volunteer("Val Unteer", "val@unteer", false));
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(this.getVolunteerNames(volunteers));
        List<Shift> shifts = Arrays.asList(new Shift("foo", false), new Shift("bar", true));
        eventFrame.setShifts(shifts);
        event = eventFrame.getEvent();
        List<Volunteer> expected = volunteers;
        expected.set(1, null);
        List<Volunteer> received = new LinkedList<>();
        for (Shift shift : event.getShifts()) {
            received.add(shift.getVolunteer());
        }    // for
        assertEquals(expected, received);
    }    // testSetShiftsRetainsSelectionsWhenIsAngelChanges()
    
    /* setManagers */
    
    /**
     * Tests that {@link EventFrame#setManagers(List)} does not throw an
     * exception when managers is null.
     */
    @Test
    public void testSetManagersNullNoException() {
        System.out.println("setManagers - managers is null, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = null;
        eventFrame.setManagers(managers);
    }    // testSetManagersNullNoException()
    
    /**
     * Tests that {@link EventFrame#setManagers(List)} throws a
     * {@link NullPointerException} when managers is not null but contains a
     * null element.
     */
    @Test(expected = NullPointerException.class)
    public void testSetManagersContainsNull() {
        System.out.println("setManagers - managers contains null");
        eventFrame = new EventFrame();
        List<Manager> managers = Arrays.asList(new Manager("foo", "foo", "foo"), null, new Manager("bar", "bar", "bar"));
        eventFrame.setManagers(managers);
    }    // testSetManagersContainsNull()
    
    /**
     * Tests that {@link EventFrame#setManagers(List)} does not throw an
     * exception when managers is not null and does not contain a null element.
     */
    @Test
    public void testSetManagersNotNullNoException() {
        System.out.println("setManagers - managers is not null, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = Arrays.asList(new Manager("foo", "foo", "foo"), new Manager("bar", "bar", "bar"));
        eventFrame.setManagers(managers);
    }    // testSetManagersNotNullNoException()
    
    /**
     * Tests that, after a call to {@link EventFrame#setManagers(List)}, the
     * selected manager has the same name that previously selected.
     */
    @Test
    public void testSetManagersNewManagerHasSameName() {
        System.out.println("setManagers - selected manager has same name as that previously selected");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        Manager selectedManager = managers.get(0);
        eventFrame.setManagers(managers);
        eventFrame.setSelectedManager(managers.get(0).getName());
        managers = Arrays.asList(new Manager("baz", "baz", "baz"), new Manager(selectedManager.getName(), "foo", "foo"));
        eventFrame.setManagers(managers);
        event = eventFrame.getEvent();
        String expected = selectedManager.getName();
        String received = event.getManager().getName();
        assertEquals(expected, received);
    }    // testSetManagersNewManagerHasSameName()
    
    /**
     * Tests that, after a call to {@link EventFrame#setManagers(List)}, the
     * selected manager is the element of the argument with the same name as the
     * previously selected manager.
     */
    @Test
    public void testSetManagersNewManagerIsFromList() {
        System.out.println("setManagers - selected manager is from the list");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        Manager selectedManager = managers.get(0);
        eventFrame.setManagers(managers);
        eventFrame.setSelectedManager(managers.get(0).getName());
        managers = Arrays.asList(new Manager("baz", "baz", "baz"), new Manager(selectedManager.getName(), "foo", "foo"));
        eventFrame.setManagers(managers);
        event = eventFrame.getEvent();
        Manager expected = managers.get(1);
        Manager received = event.getManager();
        assertEquals(expected, received);
    }    // testSetManagersNewManagerIsFromList()
    
    /**
     * Tests that, after a call to {@link EventFrame#setManagers(List)}, the
     * selection is cleared if no element of the argument has the same name as
     * the selection.
     */
    @Test
    public void testSetManagersClearsSelection() {
        System.out.println("setManagers - selected manager is cleared");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        eventFrame.setSelectedManager(managers.get(0).getName());
        managers = Arrays.asList(new Manager("foo", "foo", "foo"), new Manager("bar", "bar", "bar"));
        eventFrame.setManagers(managers);
        event = eventFrame.getEvent();
        Manager received = event.getManager();
        assertNull(received);
    }    // testSetManagersClearsSelection()
    
    /**
     * Tests that, after a call to {@link EventFrame#setManagers(List)}, the
     * selected assistant manager has the same name as that previously selected.
     */
    @Test
    public void testSetManagersNewAssistantManagerHasSameName() {
        System.out.println("setManagers - selected assistant manager has same name as that previously selected");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        Manager selectedManager = managers.get(0);
        eventFrame.setManagers(managers);
        eventFrame.setSelectedAssistantManager(managers.get(0).getName());
        managers = Arrays.asList(new Manager("baz", "baz", "baz"), new Manager(selectedManager.getName(), "foo", "foo"));
        eventFrame.setManagers(managers);
        event = eventFrame.getEvent();
        String expected = selectedManager.getName();
        String received = event.getAssistantManager().getName();
        assertEquals(expected, received);
    }    // testSetManagersNewAssistantManagerHasSameName()
    
    /**
     * Tests that, after a call to {@link EventFrame#setManagers(List)}, the
     * selected assistant manager is the element of the argument with the same
     * name as the previously selected assistant manager.
     */
    @Test
    public void testSetManagersNewAssistantManagerIsFromList() {
        System.out.println("setManagers - selected assistant manager is from the list");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        Manager selectedManager = managers.get(0);
        eventFrame.setManagers(managers);
        eventFrame.setSelectedAssistantManager(managers.get(0).getName());
        managers = Arrays.asList(new Manager("baz", "baz", "baz"), new Manager(selectedManager.getName(), "foo", "foo"));
        eventFrame.setManagers(managers);
        event = eventFrame.getEvent();
        Manager expected = managers.get(1);
        Manager received = event.getAssistantManager();
        assertEquals(expected, received);
    }    // testSetManagersNewAssistantManagerIsFromList()
    
    /**
     * Tests that, after a call to {@link EventFrame#setManagers(List)}, the
     * selected assistant manager is cleared if no element of the argument has
     * the same name as the selection.
     */
    @Test
    public void testSetAssistantManagersClearsSelection() {
        System.out.println("setManagers - selected assistant manager is cleared");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        eventFrame.setSelectedAssistantManager(managers.get(0).getName());
        managers = Arrays.asList(new Manager("foo", "foo", "foo"), new Manager("bar", "bar", "bar"));
        eventFrame.setManagers(managers);
        event = eventFrame.getEvent();
        Manager received = event.getAssistantManager();
        assertNull(received);
    }    // testSetAssistantManagersClearsSelection()
    
    /* setSelectedManager */
    
    /**
     * Tests that {@link EventFrame#setSelectedManager(String)} does not throw
     * an exception when the argument is null.
     */
    @Test
    public void testSetSelectedManagerNullNoException() {
        System.out.println("setSelectedManager - null, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = null;
        eventFrame.setSelectedManager(manager);
    }    // testSetSelectedManagerNullNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedManager(String)} does not throw
     * an exception when the argument is not null and corresponds to an option
     * in the manager combobox.
     */
    @Test
    public void testSetSelectedManagerInListNoException() {
        System.out.println("setSelectedManager - in list, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = managers.get(0).getName();
        eventFrame.setSelectedManager(manager);
    }    // testSetSelectedManagerInListNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedManager(String)} does not throw
     * an exception when the argument is not null and does not correspond to an
     * option in the manager combobox.
     */
    @Test
    public void testSetSelectedManagerNotInListNoException() {
        System.out.println("setSelectedManager - not in list, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = "foo";
        eventFrame.setSelectedManager(manager);
    }    // testSetSelectedManagerNotInListNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedManager(String)} clears the
     * manager correctly when the argument is null.
     */
    @Test
    public void testSetSelectedManagerNull() {
        System.out.println("setSelectedManager - null, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = null;
        eventFrame.setSelectedManager(manager);
        Manager received = eventFrame.getEvent().getManager();
        assertNull(received);
    }    // testSetSelectedManagerNull()
    
    /**
     * Tests that {@link EventFrame#setSelectedManager(String)} sets the
     * manager correctly when the argument is not null and corresponds to an
     * option in the manager combobox.
     */
    @Test
    public void testSetSelectedManagerInList() {
        System.out.println("setSelectedManager - in list, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        Manager expected = managers.get(0);
        String manager = expected.getName();
        eventFrame.setSelectedManager(manager);
        Manager received = eventFrame.getEvent().getManager();
        assertEquals(expected, received);
    }    // testSetSelectedManagerInList()
    
    /**
     * Tests that {@link EventFrame#setSelectedManager(String)} clears the
     * manager correctly when the argument is not null and does not correspond
     * to an option in the manager combobox.
     */
    @Test
    public void testSetSelectedManagerNotInList() {
        System.out.println("setSelectedManager - not in list, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = "foo";
        eventFrame.setSelectedManager(manager);
        Manager received = eventFrame.getEvent().getManager();
        assertNull(received);
    }    // testSetSelectedManagerNotInList()
    
    /* setSelectedAssistantManager */
    
    /**
     * Tests that {@link EventFrame#setSelectedAssistantManager(String)} does
     * not throw an exception when the argument is null.
     */
    @Test
    public void testSetSelectedAssistantManagerNullNoException() {
        System.out.println("setSelectedAssistantManager - null, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = null;
        eventFrame.setSelectedAssistantManager(manager);
    }    // testSetSelectedAssistantManagerNullNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedAssistantManager(String)} does
     * not throw an exception when the argument is not null and corresponds to
     * an option in the manager combobox.
     */
    @Test
    public void testSetSelectedAssistantManagerInListNoException() {
        System.out.println("setSelectedAssistantManager - in list, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = managers.get(0).getName();
        eventFrame.setSelectedAssistantManager(manager);
    }    // testSetSelectedAssistantManagerInListNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedAssistantManager(String)} does
     * not throw an exception when the argument is not null and does not
     * correspond to an option in the manager combobox.
     */
    @Test
    public void testSetSelectedAssistantManagerNotInListNoException() {
        System.out.println("setSelectedAssistantManager - not in list, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = "foo";
        eventFrame.setSelectedAssistantManager(manager);
    }    // testSetSelectedAssistantManagerNotInListNoException()
    
    /**
     * Tests that {@link EventFrame#setSelectedAssistantManager(String)} clears
     * the assistant manager correctly when the argument is null.
     */
    @Test
    public void testSetSelectedAssistantManagerNull() {
        System.out.println("setSelectedAssistantManager - null, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = null;
        eventFrame.setSelectedAssistantManager(manager);
        Manager received = eventFrame.getEvent().getAssistantManager();
        assertNull(received);
    }    // testSetSelectedAssistantManagerNull()
    
    /**
     * Tests that {@link EventFrame#setSelectedAssistantManager(String)} sets
     * the assistant manager correctly when the argument is not null and
     * corresponds to an option in the manager combobox.
     */
    @Test
    public void testSetSelectedAssistantManagerInList() {
        System.out.println("setSelectedAssistantManager - in list, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        Manager expected = managers.get(0);
        String manager = expected.getName();
        eventFrame.setSelectedAssistantManager(manager);
        Manager received = eventFrame.getEvent().getAssistantManager();
        assertEquals(expected, received);
    }    // testSetSelectedAssistantManagerInList()
    
    /**
     * Tests that {@link EventFrame#setSelectedAssistantManager(String)} clears
     * the assistant manager correctly when the argument is not null and does
     * not correspond to an option in the manager combobox.
     */
    @Test
    public void testSetSelectedAssistantManagerNotInList() {
        System.out.println("setSelectedAssistantManager - not in list, no exception");
        eventFrame = new EventFrame();
        List<Manager> managers = getTestManagers();
        eventFrame.setManagers(managers);
        String manager = "foo";
        eventFrame.setSelectedAssistantManager(manager);
        Manager received = eventFrame.getEvent().getAssistantManager();
        assertNull(received);
    }    // testSetSelectedAssistantManagerNotInList()
    
    /* setVolunteers */
    
    /**
     * Tests that {@link EventFrame#setVolunteers(List)} does not throw an
     * exception when volunteers is null.
     */
    @Test
    public void testSetVolunteersNullNoException() {
        System.out.println("setVolunteers - volunteers is null, no exception");
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("foo", "foo", true), null, new Volunteer("bar", "bar", true));
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
        eventFrame = new EventFrame();
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("foo", "foo", true), new Volunteer("bar", "bar", true));
        eventFrame.setVolunteers(volunteers);
    }    // testSetVolunteersNotNullNoException()
    
    /**
     * Tests that, after a call to {@link EventFrame#setVolunteers(List)}, 
     * selected volunteers have the same name as those previously selected.
     */
    @Test
    public void testSetVolunteersNewVolunteersHasSameName() {
        System.out.println("setVolunteers - selected volunteers have same name as those previously selected");
        eventFrame = new EventFrame();
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(this.getVolunteerNames(volunteers));
        List<Volunteer> newVolunteers = getTestVolunteers();
        final int INDEX = 0;
        Volunteer oldVolunteer = newVolunteers.get(INDEX);
        Volunteer newVolunteer = new Volunteer(oldVolunteer.getName(), "foo", true);
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
        eventFrame = new EventFrame();
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(this.getVolunteerNames(volunteers));
        List<Volunteer> newVolunteers = getTestVolunteers();
        final int INDEX = 0;
        Volunteer oldVolunteer = newVolunteers.get(INDEX);
        Volunteer newVolunteer = new Volunteer(oldVolunteer.getName(), "foo", true);
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
        eventFrame = new EventFrame();
        eventFrame.setShifts(getTestShifts());
        List<Volunteer> volunteers = getTestVolunteers();
        eventFrame.setVolunteers(volunteers);
        eventFrame.setSelectedVolunteers(this.getVolunteerNames(volunteers));
        List<Volunteer> newVolunteers = getTestVolunteers();
        final int INDEX = 0;
        Volunteer oldVolunteer = newVolunteers.get(INDEX);
        Volunteer newVolunteer = new Volunteer("foo", "foo", true);
        newVolunteers.set(INDEX, newVolunteer);
        eventFrame.setVolunteers(newVolunteers);
        List<Volunteer> receivedVolunteers = getSelectedVolunteers(eventFrame);
        Volunteer received = receivedVolunteers.get(INDEX);
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
        List<Shift> shifts = getTestShifts();
        final int SIZE = shifts.size();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = null;
        
        List<Volunteer> expected = Collections.nCopies(SIZE, null);
        List<Volunteer> received = this.getSelectedVolunteers(eventFrame);
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
        eventFrame = new EventFrame();
        List<Shift> shifts = getTestShifts();
        final int SIZE = shifts.size();
        eventFrame.setShifts(shifts);
        eventFrame.setVolunteers(getTestVolunteers());
        List<String> volunteers = new ArrayList<>();
        
        List<Volunteer> expected = Collections.nCopies(SIZE, null);
        List<Volunteer> received = this.getSelectedVolunteers(eventFrame);
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
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
        eventFrame = new EventFrame();
        List<Shift> shifts = getTestShifts();
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
        eventFrame = new EventFrame();
        List<Shift> shifts = getTestShifts();
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
        eventFrame = new EventFrame();
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

        List<Volunteer> expected = new ArrayList<>(getTestVolunteers());
        Collections.reverse(expected);
        expected.set(INDEX, null);
        expected = expected.subList(0, SIZE);
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        assertEquals(expected, received);
    }    // testSetSelectedVolunteersDoNotCorrespond()
    
    /**
     * Tests that {@link EventFrame#setSelectedVolunteers(List)} sets the
     * selected volunteers correctly when trying to fit a volunteer who is not
     * and angel into an angel shift.
     */
    @Test
    public void testSetSelectedVolunteersAngelConflict() {
        System.out.println("setSelectedVolunteers - angel conflict");
        eventFrame = new EventFrame();
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

        List<Volunteer> expected = new ArrayList<>(getTestVolunteers());
        expected.set(ANGEL_SHIFT_INDEX, null);
        expected = expected.subList(0, SIZE);
        List<Volunteer> received = getSelectedVolunteers(eventFrame);
        assertEquals(expected, received);
    }    // testSetSelectedVolunteersAngelConflict()

    /* managersChanged */
    
    /**
     * Tests that {@link EventFrame#managersChanged()} does not throw an
     * exception.
     */
    @Test
    public void testManagersChangedNoException() {
        System.out.println("managersChanged - no exception");
        eventFrame = new EventFrame();
        eventFrame.managersChanged();
    }    // testManagersChangedNoException()
    
    /**
     * Tests that {@link EventFrame#managersChanged()} fires and works properly
     * when {@link Application#setManagers(List)} is called.
     */
    @Test
    public void testManagersChanged() throws IOException {
        System.out.println("managersChanged");
        eventFrame = new EventFrame();
        List<Manager> originalManagers = getTestManagers();
        eventFrame.setManagers(originalManagers);
        eventFrame.setSelectedManager(originalManagers.get(0).getName());
        event = eventFrame.getEvent();
        Manager originalManager = event.getManager();
        assert (originalManager != null);
        List<Manager> managers = new ArrayList<>();
        Application.setManagers(managers);
        event = eventFrame.getEvent();
        Manager expected = null;
        Manager received = event.getManager();
        assertEquals(expected, received);
    }    // testManagersChanged()

    /* shiftsChanged */
    
    /**
     * Tests that {@link EventFrame#shiftsChanged()} does not throw an
     * exception.
     */
    @Test
    public void testShiftsChangedNoException() {
        System.out.println("shiftsChanged - no exception");
        eventFrame = new EventFrame();
        eventFrame.shiftsChanged();
    }    // testShiftsChangedNoException()
    
    /**
     * Tests that {@link EventFrame#shiftsChanged()} fires and works properly
     * when {@link Application#setShifts(List)} is called.
     */
    @Test
    public void testShiftsChanged() throws IOException {
        System.out.println("shiftsChanged");
        eventFrame = new EventFrame();
        List<Shift> originalShifts = getTestShifts();
        eventFrame.setShifts(originalShifts);
        event = eventFrame.getEvent();
        List<Shift> shifts = new ArrayList<>(event.getShifts());
        assert (shifts != null);
        shifts.add(new Shift("Foo", false));
        Application.setShifts(shifts);
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
        eventFrame = new EventFrame();
        eventFrame.volunteersChanged();
    }    // testVolunteersChangedNoException()
    
    /**
     * Tests that {@link EventFrame#volunteersChanged()} fires and works
     * properly when {@link Application#setVolunteers(List)} is called.
     */
    @Test
    public void testVolunteersChanged() throws IOException {
        System.out.println("volunteersChanged");
        eventFrame = new EventFrame();
        List<Volunteer> originalVolunteers = getTestVolunteers();
        eventFrame.setVolunteers(originalVolunteers);
        eventFrame.setSelectedVolunteers(Arrays.asList(originalVolunteers.get(0).getName()));
        event = eventFrame.getEvent();
        List<Shift> shifts = event.getShifts();
        Volunteer originalVolunteer = shifts.get(0).getVolunteer();
        assert (originalVolunteer != null);
        List<Volunteer> volunteers = new ArrayList<>();
        Application.setVolunteers(volunteers);
        event = eventFrame.getEvent();
        shifts = event.getShifts();
        Volunteer expected = null;
        Volunteer received = shifts.get(0).getVolunteer();
        assertEquals(expected, received);
    }    // testVolunteersChanged()
    
}    // EventFrameTest
