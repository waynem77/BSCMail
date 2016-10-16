/*
 * Copyright © 2014 Wayne Miller
 */

package main;

import bscmail.*;
import bscmail.transformer.Transformer;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Application}
 * @author Wayne Miller
 */
public class ApplicationTest {

    private class ApplicationObserver implements ShiftsObserver, ManagersObserver, VolunteersObserver, EmailTemplateObserver {
        private boolean shiftsChanged = false;
        private boolean managersChanged = false;
        private boolean volunteersChanged = false;
        private boolean emailTemplateObserver = false;
        
        @Override public void shiftsChanged() { shiftsChanged = true; }
        @Override public void managersChanged() { managersChanged = true; }
        @Override public void volunteersChanged() { volunteersChanged = true; }
        @Override public void emailTemplateChanged() { emailTemplateObserver = true; }
        
        public boolean getShiftsChanged() { return shiftsChanged; }
        public boolean getManagersChanged() { return managersChanged; }
        public boolean getVolunteersChanged() { return volunteersChanged; }
        public boolean getEmailTemplateChanged() { return emailTemplateObserver; }
    }    // ApplicationObserver
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Application");
        System.out.println("===========");
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
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        Application.setTestMode(false);
    }    // tearDown()

    /*
     * Unit tests
     */

    /* setTestMode */

    /**
     * Tests that {@link Application#setTestMode(boolean)} does not throw an
     * exception when testMode is true.
     */
    public void testSetTestModeTrue() {
        System.out.println("setTestMode - no exception when arg is true");
        
        boolean testMode = true;
        Application.setTestMode(testMode);
    }    // testSetTestModeTrue()

    /**
     * Tests that {@link Application#setTestMode(boolean)} does not throw an
     * exception when testMode is false.
     */
    public void testSetTestModeFalse() {
        System.out.println("setTestMode - no exception when arg is false");
        
        boolean testMode = false;
        Application.setTestMode(testMode);
    }    // testSetTestModeFalse()
    
    /* getApplicationName */
    
    /**
     * Tests that {@link Application#getApplicationName()} does not throw an
     * exception.
     */
    @Test
    public void testGetApplicationNameNoException() {
        System.out.println("getApplicationName - no exception");
        
        Application.getApplicationName();
    }    // testGetApplicationNameNoException()
    
    /**
     * Tests that {@link Application#getApplicationName()} does not return null.
     */
    @Test
    public void testGetApplicationNameNotNull() {
        System.out.println("getApplicationName - not null");
        
        String received = Application.getApplicationName();
        assertNotNull(received);
    }    // testGetApplicationNameNotNull()
    
    /* getVersion */
    
    /**
     * Tests that {@link Application#getVersion()} does not throw an exception.
     */
    @Test
    public void testGetVersionNoException() {
        System.out.println("getVersion - no exception");

        Application.getVersion();
    }    // testGetVersionNoException()

    /**
     * Tests that {@link Application#getVersion()} does not return null.
     */
    @Test
    public void testGetVersionNotNull() {
        System.out.println("getVersion - not null");

        String received = Application.getVersion();
        assertNotNull(received);
    }    // testGetVersionNotNull()
    
    /* getCopyright */
    
    /**
     * Tests that {@link Application#getCopyright()} does not throw an exception.
     */
    @Test
    public void testGetCopyrightNoException() {
        System.out.println("getCopyright - no exception");

        Application.getCopyright();
    }    // testGetCopyrightNoException()

    /**
     * Tests that {@link Application#getCopyright()} does not return null.
     */
    @Test
    public void testGetCopyrightNotNull() {
        System.out.println("getCopyright - not null");

        String received = Application.getCopyright();
        assertNotNull(received);
    }    // testGetCopyrightNotNull()
    
    /* getShifts / setShifts */
    
    /**
     * Tests that {@link Application#getShifts()} does not throw an exception.
     */
    @Test
    public void testGetShiftsNoException() {
        System.out.println("getShifts - no exception");

        Application.getShifts();
    }    // testGetShiftsNoException()

    /**
     * Tests that {@link Application#getShifts()} does not return null.
     */
    @Test
    public void testGetShiftsNotNull() {
        System.out.println("getShifts - not null");

        List<Shift> received = Application.getShifts();
        assertNotNull(received);
    }    // testGetShiftsNotNull()
    
    /**
     * Tests that {@link Application#setShifts(List)} throws a
     * {@link NullPointerException} when shifts is null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetShiftsShiftsNull() throws IOException {
        System.out.println("setShifts - shifts is null");

        List<Shift> shifts = null;
        Application.setShifts(shifts);
    }    // testSetShiftsShiftsNull()
    
    /**
     * Tests that {@link Application#setShifts(List)} throws a
     * {@link NullPointerException} when shifts is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetShiftsShiftsContainsNull() throws IOException {
        System.out.println("setShifts - shifts contains null");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), null);
        Application.setShifts(shifts);
    }    // testSetShiftsShiftsContainsNull()
    
    /**
     * Tests that {@link Application#setShifts(List)} does not throw an
     * exception when shifts is not null and contains no nulls.
     */
    @Test
    public void testSetShiftsNoException() throws IOException {
        System.out.println("setShifts - no exception");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), new Shift("Bar", false));
        Application.setShifts(shifts);
    }    // testSetShiftsNoException()
    
    /**
     * Tests that {@link Application#setShifts(List)} does not alter its
     * argument.
     */
    @Test
    public void testSetShiftsDoesNotAlterArgument() throws IOException {
        System.out.println("setShifts - does not alter argument");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), new Shift("Bar", false));
        shifts.get(0).setVolunteer(new Volunteer("foo", "bar", true));
        List<Shift> expected = new LinkedList<>();
        for (Shift shift : shifts) {
            expected.add(shift.clone());
        }    // for
        Application.setShifts(shifts);
        List<Shift> received = shifts;
        assertEquals(expected, received);
    }    // testSetShiftsDoesNotAlterArgument()
    
    /**
     * Tests that {@link Application#getShifts()} returns a list equal to that
     * passed to {@link Application#setShifts(List)}, minus any volunteers.
     */
    @Test
    public void testGetShiftsSetShiftsListsAreEqualMinusVolunteers() throws IOException {
        System.out.println("getShifts/setShifts - lists are equal minus volunteers");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), new Shift("Bar", false));
        shifts.get(0).setVolunteer(new Volunteer("foo", "bar", true));
        List<Shift> expected = new LinkedList<>();
        for (Shift shift : shifts) {
            Shift newShift = shift.clone();
            newShift.setVolunteer(null);
            expected.add(newShift);
        }    // for
        Application.setShifts(shifts);
        List<Shift> received = Application.getShifts();
        assertEquals(expected, received);
    }    // testGetShiftsSetShiftsListsAreEqualMinusVolunteers()
    
    /**
     * Tests that {@link Application#getShifts()} returns a list that is not
     * identical to that passed to {@link Application#setShifts(List)}.
     */
    @Test
    public void testGetShiftsSetShiftsNoIdentity() throws IOException {
        System.out.println("getShifts/setShifts - lists are not identical");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), new Shift("Bar", false));
        List<Shift> expected = shifts;
        Application.setShifts(shifts);
        List<Shift> received = Application.getShifts();
        assertFalse(expected == received);
    }    // testGetShiftsSetShiftsNoIdentity()
    
    /**
     * Tests that the elements of the list returned by
     * {@link Application#getShifts()} are not identical identical to those of
     * the list passed to {@link Application#setShifts(List)}.
     */
    @Test
    public void testGetShiftsSetShiftsNoElementIdentity() throws IOException {
        System.out.println("getShifts/setShifts - lists elements are not identical");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), new Shift("Bar", false));
        Application.setShifts(shifts);
        List<Shift> received = Application.getShifts();
        for (int i = 0; i < shifts.size(); ++i) {
            assertFalse(shifts.get(i) == received.get(i));
        }    // for
    }    // testGetShiftsSetShiftsNoElementIdentity()
    
    /* getManagers / setManagers */
    
    /**
     * Tests that {@link Application#getManagers()} does not throw an exception.
     */
    @Test
    public void testGetManagersNoException() {
        System.out.println("getManagers - no exception");

        Application.getManagers();
    }    // testGetManagersNoException()

    /**
     * Tests that {@link Application#getManagers()} does not return null.
     */
    @Test
    public void testGetManagersNotNull() {
        System.out.println("getManagers - not null");

        List<Manager> received = Application.getManagers();
        assertNotNull(received);
    }    // testGetManagersNotNull()
    
    /**
     * Tests that {@link Application#setManagers(List)} throws a
     * {@link NullPointerException} when managers is null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetManagersManagersNull() throws IOException {
        System.out.println("setManagers - managers is null");

        List<Manager> managers = null;
        Application.setManagers(managers);
    }    // testSetManagersManagersNull()
    
    /**
     * Tests that {@link Application#setManagers(List)} throws a
     * {@link NullPointerException} when managers is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetManagersManagersContainsNull() throws IOException {
        System.out.println("setManagers - managers contains null");

        List<Manager> managers = Arrays.asList(new Manager("Foo", "foo", "555-FOO"), null);
        Application.setManagers(managers);
    }    // testSetManagersManagersContainsNull()
    
    /**
     * Tests that {@link Application#setManagers(List)} does not throw an
     * exception when managers is not null and contains no nulls.
     */
    @Test
    public void testSetManagersNoException() throws IOException {
        System.out.println("setManagers - no exception");

        List<Manager> managers = Arrays.asList(new Manager("Foo", "foo", "555-FOO"), new Manager("Bar", "bar", "555-BAR"));
        Application.setManagers(managers);
    }    // testSetManagersNoException()
    
    /**
     * Tests that {@link Application#setManagers(List)} does not alter its
     * argument.
     */
    @Test
    public void testSetManagersDoesNotAlterArgument() throws IOException {
        System.out.println("setManagers - does not alter argument");

        List<Manager> managers = Arrays.asList(new Manager("Foo", "foo", "555-FOO"), new Manager("Bar", "bar", "555-BAR"));
        List<Manager> expected = new ArrayList<>(managers);
        Application.setManagers(managers);
        List<Manager> received = managers;
        assertEquals(expected, received);
    }    // testSetManagersDoesNotAlterArgument()
    
    /**
     * Tests that {@link Application#getManagers()} returns a list that is
     * equal to that passed to {@link Application#setManagers(List)}.
     */
    @Test
    public void testGetManagersSetManagersListsAreEqual() throws IOException {
        System.out.println("getManagers/setManagers - lists are equal");

        List<Manager> managers = Arrays.asList(new Manager("Foo", "foo", "555-FOO"), new Manager("Bar", "bar", "555-BAR"));
        List<Manager> expected = managers;
        Application.setManagers(managers);
        List<Manager> received = Application.getManagers();
        assertEquals(expected, received);
    }    // testGetManagersSetManagersListsAreEqual()
    
    /**
     * Tests that {@link Application#getManagers()} returns a list that is not
     * identical to that passed to {@link Application#setManagers(List)}.
     */
    @Test
    public void testGetManagersSetManagersNoIdentity() throws IOException {
        System.out.println("getManagers/setManagers - lists are not identical");

        List<Manager> managers = Arrays.asList(new Manager("Foo", "foo", "555-FOO"), new Manager("Bar", "bar", "555-BAR"));
        List<Manager> expected = managers;
        Application.setManagers(managers);
        List<Manager> received = Application.getManagers();
        assertFalse(expected == received);
    }    // testGetManagersSetManagersNoIdentity()
    
    /* getVolunteers / setVolunteers */
    
    /**
     * Tests that {@link Application#getVolunteers()} does not throw an exception.
     */
    @Test
    public void testGetVolunteersNoException() {
        System.out.println("getVolunteers - no exception");

        Application.getVolunteers();
    }    // testGetVolunteersNoException()

    /**
     * Tests that {@link Application#getVolunteers()} does not return null.
     */
    @Test
    public void testGetVolunteersNotNull() {
        System.out.println("getVolunteers - not null");

        List<Volunteer> received = Application.getVolunteers();
        assertNotNull(received);
    }    // testGetVolunteersNotNull()
    
    /**
     * Tests that {@link Application#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetVolunteersVolunteersNull() throws IOException {
        System.out.println("setVolunteers - volunteers is null");

        List<Volunteer> volunteers = null;
        Application.setVolunteers(volunteers);
    }    // testSetVolunteersVolunteersNull()
    
    /**
     * Tests that {@link Application#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetVolunteersVolunteersContainsNull() throws IOException {
        System.out.println("setVolunteers - volunteers contains null");

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", false), null);
        Application.setVolunteers(volunteers);
    }    // testSetVolunteersVolunteersContainsNull()
    
    /**
     * Tests that {@link Application#setVolunteers(List)} does not throw an
     * exception when volunteers is not null and contains no nulls.
     */
    @Test
    public void testSetVolunteersNoException() throws IOException {
        System.out.println("setVolunteers - no exception");

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", false), new Volunteer("Bar", "bar", true));
        Application.setVolunteers(volunteers);
    }    // testSetVolunteersNoException()
    
    /**
     * Tests that {@link Application#setVolunteers(List)} does not alter its
     * argument.
     */
    @Test
    public void testSetVolunteersDoesNotAlterArgument() throws IOException {
        System.out.println("setVolunteers - does not alter argument");

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", false), new Volunteer("Bar", "bar", true));
        List<Volunteer> expected = new ArrayList<>(volunteers);
        Application.setVolunteers(volunteers);
        List<Volunteer> received = volunteers;
        assertEquals(expected, received);
    }    // testSetVolunteersDoesNotAlterArgument()
    
    /**
     * Tests that {@link Application#getVolunteers()} returns a list that is
     * equal to that passed to {@link Application#setVolunteers(List)}.
     */
    @Test
    public void testGetVolunteersSetVolunteersListsAreEqual() throws IOException {
        System.out.println("getVolunteers/setVolunteers - lists are equal");

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", false), new Volunteer("Bar", "bar", true));
        List<Volunteer> expected = volunteers;
        Application.setVolunteers(volunteers);
        List<Volunteer> received = Application.getVolunteers();
        assertEquals(expected, received);
    }    // testGetVolunteersSetVolunteersListsAreEqual()
    
    /**
     * Tests that {@link Application#getVolunteers()} returns a list that is not
     * identical to that passed to {@link Application#setVolunteers(List)}.
     */
    @Test
    public void testGetVolunteersSetVolunteersNoIdentity() throws IOException {
        System.out.println("getVolunteers/setVolunteers - lists are not identical");

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", false), new Volunteer("Bar", "bar", true));
        List<Volunteer> expected = volunteers;
        Application.setVolunteers(volunteers);
        List<Volunteer> received = Application.getVolunteers();
        assertFalse(expected == received);
    }    // testGetVolunteersSetVolunteersNoIdentity()
    
    /* getEmailTemplate / setEmailTemplate */
    
    /**
     * Tests that {@link Application#getEmailTemplate()} does not throw an exception.
     */
    @Test
    public void testGetEmailTemplateNoException() throws IOException {
        System.out.println("getEmailTemplate - no exception");

        Application.getEmailTemplate();
    }    // testGetEmailTemplateNoException()

    /**
     * Tests that {@link Application#getEmailTemplate()} does not return null.
     */
    @Test
    public void testGetEmailTemplateNotNull() throws IOException {
        System.out.println("getEmailTemplate - not null");

        Reader received = Application.getEmailTemplate();
        assertNotNull(received);
    }    // testGetEmailTemplateNotNull()
    
    /**
     * Tests that {@link Application#setEmailTemplate(Reader)} throws a
     * {@link NullPointerException} when templateReader is null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetEmailTemplateArgNull() throws IOException {
        System.out.println("setEmailTemplate - templateReader is null");

        Reader templateReader = null;
        Application.setEmailTemplate(templateReader);
    }    // testSetEmailTemplateArgNull()
    
    /**
     * Tests that {@link Application#setEmailTemplate(Reader)} does not throw an
     * exception when templateReAder is not null.
     */
    @Test
    public void testSetEmailTemplateArgNotNull() throws IOException {
        System.out.println("setEmailTemplate - templateReader is not null");

        Reader templateReader = new StringReader("foo");
        Application.setEmailTemplate(templateReader);
    }    // testSetEmailTemplateArgNotNull()
    
    /**
     * Tests that the content of the stream returned by
     * {@link Application#getEmailTemplate()} is equal to the content of the
     * stream passed to {@link Application#setEmailTemplate(List)}.
     */
    @Test
    public void testGetEmailTemplateSetEmailTemplateContents() throws IOException {
        System.out.println("getEmailTemplate/setEmailTemplate - character streams have equal contents");

        String template = "Foo.\nBar bar.\nBork bork bork.\n";
        Reader input = new StringReader(template);
        Application.setEmailTemplate(input);
        BufferedReader output = new BufferedReader(Application.getEmailTemplate());

        try {
            String expected = template;
            String received = "";
            String line;
            while ((line = output.readLine()) != null) {
                received += line + "\n";
            }    // while()
            assertEquals(expected, received);
        } catch (IOException e) {    // try
            fail("Test not completed: IOException: " + e);
        }    // catch
    }    // testGetEmailTemplateSetEmailTemplateContents()
    
    /* getTransformer */
    
    /**
     * Tests that {@link Application#getTransformer()} does not throw an exception.
     */
    @Test
    public void testGetTransformerNoException() {
        System.out.println("getTransformer - no exception");

        Application.getTransformer();
    }    // testGetTransformerNoException()

    /**
     * Tests that {@link Application#getTransformer()} does not return null.
     */
    @Test
    public void testGetTransformerNotNull() {
        System.out.println("getTransformer - not null");

        Transformer received = Application.getTransformer();
        assertNotNull(received);
    }    // testGetTransformerNotNull()
    
    /* registerObserver(ShiftsObserver) */
    
    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterObserverShiftsObserverNull() {
        System.out.println("registerObserver(ShiftsObserver) - observer is null");

        ShiftsObserver observer = null;
        Application.registerObserver(observer);
    }    // testRegisterObserverShiftsObserverNull()
    
    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void testRegisterObserverShiftsObserverNotNull() {
        System.out.println("registerObserver(ShiftsObserver) - observer is null");

        ShiftsObserver observer = new ApplicationObserver();
        Application.registerObserver(observer);
    }    // testRegisterObserverShiftsObserverNotNull()
    
    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void testRegisterObserverShiftsTwice() {
        System.out.println("registerObserver(ShiftsObserver) - called twice");

        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (ShiftsObserver observer : observers) {
            Application.registerObserver(observer);
        }    // for
    }    // testRegisterObserverShiftsTwice()

    /**
     * Tests that a call to {@link Application#setShifts(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void testSetShiftsRegisterObserverShiftsNotifiesAll() throws IOException {
        System.out.println("setShifts/registerObserver(ShiftsObserver) - notifies all observers");

        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (ShiftsObserver observer : observers) {
            Application.registerObserver(observer);
        }    // for
        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), new Shift("Bar", false));
        Application.setShifts(shifts);
        
        for (ApplicationObserver observer : observers) {
            assertTrue(observer.getShiftsChanged());
        }    // for
    }    // testSetShiftsRegisterObserverShiftsNotifiesAll()

    /**
     * Tests that a call to {@link Application#setManagers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void testSetManagersRegisterObserverShiftsNotifiesAll() throws IOException {
        System.out.println("setManagers/registerObserver(ShiftsObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((ShiftsObserver)observer);
        List<Manager> managers = Arrays.asList(new Manager("Foo", "foo", "555-FOO"), new Manager("Bar", "bar", "555-BAR"));
        Application.setManagers(managers);
        
        assertFalse(observer.getShiftsChanged());
    }    // testSetManagersRegisterObserverShiftsNotifiesAll()

    /**
     * Tests that a call to {@link Application#setVolunteers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void testSetVolunteersRegisterObserverShiftsNotifiesAll() throws IOException {
        System.out.println("setVolunteers/registerObserver(ShiftsObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((ShiftsObserver)observer);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", false), new Volunteer("Bar", "bar", true));
        Application.setVolunteers(volunteers);
        
        assertFalse(observer.getShiftsChanged());
    }    // testSetVolunteersRegisterObserverShiftsNotifiesAll()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void testSetEmailTemplateRegisterObserverShiftsNotifiesAll() throws IOException {
        System.out.println("setEmailTemplate/registerObserver(ShiftsObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((ShiftsObserver)observer);
        String text = "Foo\nBar\n";
        Reader templateReader = new StringReader(text);
        Application.setEmailTemplate(templateReader);
        
        assertFalse(observer.getShiftsChanged());
    }    // testSetEmailTemplateRegisterObserverShiftsNotifiesAll()
    
    /* registerObserver(ManagersObserver) */
    
    /**
     * Tests that {@link Application#registerObserver(ManagersObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterObserverManagersObserverNull() {
        System.out.println("registerObserver(ManagersObserver) - observer is null");

        ManagersObserver observer = null;
        Application.registerObserver(observer);
    }    // testRegisterObserverManagersObserverNull()
    
    /**
     * Tests that {@link Application#registerObserver(ManagersObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void testRegisterObserverManagersObserverNotNull() {
        System.out.println("registerObserver(ManagersObserver) - observer is null");

        ManagersObserver observer = new ApplicationObserver();
        Application.registerObserver(observer);
    }    // testRegisterObserverManagersObserverNotNull()
    
    /**
     * Tests that {@link Application#registerObserver(ManagersObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void testRegisterObserverManagersTwice() {
        System.out.println("registerObserver(ManagersObserver) - called twice");

        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (ManagersObserver observer : observers) {
            Application.registerObserver(observer);
        }    // for
    }    // testRegisterObserverManagersTwice()

    /**
     * Tests that a call to {@link Application#setManagers(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(ManagersObserver)}.
     */
    @Test
    public void testSetManagersRegisterObserverManagersNotifiesAll() throws IOException {
        System.out.println("setManagers/registerObserver(ManagersObserver) - notifies all observers");

        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (ManagersObserver observer : observers) {
            Application.registerObserver(observer);
        }    // for
        List<Manager> managers = Arrays.asList(new Manager("Foo", "foo", "555-FOO"), new Manager("Bar", "bar", "555-BAR"));
        Application.setManagers(managers);
        
        for (ApplicationObserver observer : observers) {
            assertTrue(observer.getManagersChanged());
        }    // for
    }    // testSetManagersRegisterObserverManagersNotifiesAll()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(ManagersObserver)}.
     */
    @Test
    public void testSetShiftsRegisterObserverManagersNotifiesAll() throws IOException {
        System.out.println("setShifts/registerObserver(ManagersObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((ManagersObserver)observer);
        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), new Shift("Bar", false));
        Application.setShifts(shifts);
        
        assertFalse(observer.getManagersChanged());
    }    // testSetShiftsRegisterObserverManagersNotifiesAll()

    /**
     * Tests that a call to {@link Application#setVolunteers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(ManagersObserver)}.
     */
    @Test
    public void testSetVolunteersRegisterObserverManagersNotifiesAll() throws IOException {
        System.out.println("setVolunteers/registerObserver(ManagersObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((ManagersObserver)observer);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", false), new Volunteer("Bar", "bar", true));
        Application.setVolunteers(volunteers);
        
        assertFalse(observer.getManagersChanged());
    }    // testSetVolunteersRegisterObserverManagersNotifiesAll()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(ManagersObserver)}.
     */
    @Test
    public void testSetEmailTemplateRegisterObserverManagerssNotifiesAll() throws IOException {
        System.out.println("setEmailTemplate/registerObserver(ManagersObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((ShiftsObserver)observer);
        String text = "Foo\nBar\n";
        Reader templateReader = new StringReader(text);
        Application.setEmailTemplate(templateReader);
        
        assertFalse(observer.getManagersChanged());
    }    // testSetEmailTemplateRegisterObserverManagerssNotifiesAll()
    
    /* registerObserver(VolunteersObserver) */
     
    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterObserverVolunteersObserverNull() {
        System.out.println("registerObserver(VolunteersObserver) - observer is null");

        VolunteersObserver observer = null;
        Application.registerObserver(observer);
    }    // testRegisterObserverVolunteersObserverNull()
    
    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void testRegisterObserverVolunteersObserverNotNull() {
        System.out.println("registerObserver(VolunteersObserver) - observer is null");

        VolunteersObserver observer = new ApplicationObserver();
        Application.registerObserver(observer);
    }    // testRegisterObserverVolunteersObserverNotNull()
    
    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void testRegisterObserverVolunteersTwice() {
        System.out.println("registerObserver(VolunteersObserver) - called twice");

        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (VolunteersObserver observer : observers) {
            Application.registerObserver(observer);
        }    // for
    }    // testRegisterObserverVolunteersTwice()

    /**
     * Tests that a call to {@link Application#setVolunteers(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void testSetVolunteersRegisterObserverVolunteersNotifiesAll() throws IOException {
        System.out.println("setVolunteers/registerObserver(VolunteersObserver) - notifies all observers");

        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (VolunteersObserver observer : observers) {
            Application.registerObserver(observer);
        }    // for
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", false), new Volunteer("Bar", "bar", true));
        Application.setVolunteers(volunteers);
        
        for (ApplicationObserver observer : observers) {
            assertTrue(observer.getVolunteersChanged());
        }    // for
    }    // testSetVolunteersRegisterObserverVolunteersNotifiesAll()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void testSetShiftsRegisterObserverVolunteersNotifiesAll() throws IOException {
        System.out.println("setShifts/registerObserver(VolunteersObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((VolunteersObserver)observer);
        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), new Shift("Bar", false));
        Application.setShifts(shifts);
        
        assertFalse(observer.getVolunteersChanged());
    }    // testSetShiftsRegisterObserverVolunteersNotifiesAll()

    /**
     * Tests that a call to {@link Application#setManagers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void testSetManagersRegisterObserverVolunteersNotifiesAll() throws IOException {
        System.out.println("setVolunteers/registerObserver(VolunteersObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((VolunteersObserver)observer);
        List<Manager> managers = Arrays.asList(new Manager("Foo", "foo", "555-FOO"), new Manager("Bar", "bar", "555-BAR"));
        Application.setManagers(managers);
        
        assertFalse(observer.getVolunteersChanged());
    }    // testSetManagersRegisterObserverVolunteersNotifiesAll()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void testSetEmailTemplateRegisterObserverVolunteersNotifiesAll() throws IOException {
        System.out.println("setEmailTemplate/registerObserver(VolunteersObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((ShiftsObserver)observer);
        String text = "Foo\nBar\n";
        Reader templateReader = new StringReader(text);
        Application.setEmailTemplate(templateReader);
        
        assertFalse(observer.getVolunteersChanged());
    }    // testSetEmailTemplateRegisterObserverManagerssNotifiesAll()
    
    /* registerObserver(EmailTemplateObserver) */
     
    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)}
     * throws a {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterObserverEmailTemplateObserverNull() {
        System.out.println("registerObserver(EmailTemplateObserver) - observer is null");

        EmailTemplateObserver observer = null;
        Application.registerObserver(observer);
    }    // testRegisterObserverEmailTemplateObserverNull()
    
    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)}
     * does not throw an exception when observer is not null.
     */
    @Test
    public void testRegisterObserverEmailTemplateObserverNotNull() {
        System.out.println("registerObserver(EmailTemplateObserver) - observer is null");

        EmailTemplateObserver observer = new ApplicationObserver();
        Application.registerObserver(observer);
    }    // testRegisterObserverEmailTemplateObserverNotNull()
    
    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)}
     * does not throw an exception when called twice with different observers.
     */
    @Test
    public void testRegisterObserverEmailTemplateTwice() {
        System.out.println("registerObserver(EmailTemplateObserver) - called twice");

        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (EmailTemplateObserver observer : observers) {
            Application.registerObserver(observer);
        }    // for
    }    // testRegisterObserverEmailTemplateTwice()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(List)} notifies
     * all observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void testSetEmailTemplateRegisterObserverEmailTemplateNotifiesAll() throws IOException {
        System.out.println("setEmailTemplate/registerObserver(EmailTemplateObserver) - notifies all observers");

        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (EmailTemplateObserver observer : observers) {
            Application.registerObserver(observer);
        }    // for
        String text = "Foo\nBar\n";
        Reader templateReader = new StringReader(text);
        Application.setEmailTemplate(templateReader);
        
        for (ApplicationObserver observer : observers) {
            assertTrue(observer.getEmailTemplateChanged());
        }    // for
    }    // testSetEmailTemplateRegisterObserverEmailTemplateNotifiesAll()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void testSetShiftsRegisterObserverEmailTemplateNotifiesAll() throws IOException {
        System.out.println("setShifts/registerObserver(EmailTemplateObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((EmailTemplateObserver)observer);
        List<Shift> shifts = Arrays.asList(new Shift("Foo", false), new Shift("Bar", false));
        Application.setShifts(shifts);
        
        assertFalse(observer.getEmailTemplateChanged());
    }    // testSetShiftsRegisterObserverEmailTemplateNotifiesAll()

    /**
     * Tests that a call to {@link Application#setManagers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void testSetManagersRegisterObserverEmailTemplateNotifiesAll() throws IOException {
        System.out.println("setEmailTemplate/registerObserver(EmailTemplateObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((EmailTemplateObserver)observer);
        List<Manager> managers = Arrays.asList(new Manager("Foo", "foo", "555-FOO"), new Manager("Bar", "bar", "555-BAR"));
        Application.setManagers(managers);
        
        assertFalse(observer.getEmailTemplateChanged());
    }    // testSetManagersRegisterObserverEmailTemplateNotifiesAll()

    /**
     * Tests that a call to {@link Application#setVolunteers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void testSetVolunteersRegisterObserverEmailTemplateNotifiesAll() throws IOException {
        System.out.println("setVolunteers/registerObserver(EmailTemplateObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        Application.registerObserver((EmailTemplateObserver)observer);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", false), new Volunteer("Bar", "bar", true));
        Application.setVolunteers(volunteers);
        
        assertFalse(observer.getEmailTemplateChanged());
    }    // testSetVolunteersRegisterObserverEmailTemplateNotifiesAll()
    
}    // ApplicationTest
