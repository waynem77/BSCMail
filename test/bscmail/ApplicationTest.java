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

package bscmail;

import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Application}
 * @author Wayne Miller
 */
public class ApplicationTest {

    /**
     * Observer used in tests.
     */
    private class ApplicationObserver implements ShiftsObserver, VolunteersObserver, EmailTemplateObserver {
        private boolean shiftsChanged = false;
        private boolean volunteersChanged = false;
        private boolean emailTemplateObserver = false;
        
        @Override public void shiftsChanged() { shiftsChanged = true; }
        @Override public void volunteersChanged() { volunteersChanged = true; }
        @Override public void emailTemplateChanged() { emailTemplateObserver = true; }
        
        public boolean getShiftsChanged() { return shiftsChanged; }
        public boolean getVolunteersChanged() { return volunteersChanged; }
        public boolean getEmailTemplateChanged() { return emailTemplateObserver; }
    }    // ApplicationObserver

    /**
     * The application being tested.
     */
    private Application application;

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
        application = new Application();
        application.setTestMode(true);
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        application = null;
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
        application.setTestMode(testMode);
    }    // testSetTestModeTrue()

    /**
     * Tests that {@link Application#setTestMode(boolean)} does not throw an
     * exception when testMode is false.
     */
    public void testSetTestModeFalse() {
        System.out.println("setTestMode - no exception when arg is false");
        
        boolean testMode = false;
        application.setTestMode(testMode);
    }    // testSetTestModeFalse()
    
    /* getApplicationName */
    
    /**
     * Tests that {@link Application#getApplicationName()} does not throw an
     * exception.
     */
    @Test
    public void testGetApplicationNameNoException() {
        System.out.println("getApplicationName - no exception");
        
        application.getApplicationName();
    }    // testGetApplicationNameNoException()
    
    /**
     * Tests that {@link Application#getApplicationName()} does not return null.
     */
    @Test
    public void testGetApplicationNameNotNull() {
        System.out.println("getApplicationName - not null");
        
        String received = application.getApplicationName();
        assertNotNull(received);
    }    // testGetApplicationNameNotNull()
    
    /* getVersion */
    
    /**
     * Tests that {@link Application#getVersion()} does not throw an exception.
     */
    @Test
    public void testGetVersionNoException() {
        System.out.println("getVersion - no exception");

        application.getVersion();
    }    // testGetVersionNoException()

    /**
     * Tests that {@link Application#getVersion()} does not return null.
     */
    @Test
    public void testGetVersionNotNull() {
        System.out.println("getVersion - not null");

        String received = application.getVersion();
        assertNotNull(received);
    }    // testGetVersionNotNull()
    
    /* getCopyright */
    
    /**
     * Tests that {@link Application#getCopyright()} does not throw an exception.
     */
    @Test
    public void testGetCopyrightNoException() {
        System.out.println("getCopyright - no exception");

        application.getCopyright();
    }    // testGetCopyrightNoException()

    /**
     * Tests that {@link Application#getCopyright()} does not return null.
     */
    @Test
    public void testGetCopyrightNotNull() {
        System.out.println("getCopyright - not null");

        String received = application.getCopyright();
        assertNotNull(received);
    }    // testGetCopyrightNotNull()
    
    /* getUserGuideFilename */

    /**
     * Tests that {@link Application#getUserGuideFilename()} does not throw an
     * exception.
     */
    @Test
    public void testGetUserGuideFilenameNoException() {
        System.out.println("getUserGuideFilename - no exception");

        application.getUserGuideFilename();
    }    // testGetUserGuideFilenameNoException()

    /**
     * Tests that {@link Application#getUserGuideFilename()} does not return
     * null.
     */
    @Test
    public void testGetUserGuideFilenameNotNull() {
        System.out.println("getUserGuideFilename - not null");

        String received = application.getUserGuideFilename();
        assertNotNull(received);
    }    // testGetUserGuideFilenameNotNull()

    /* getShifts / setShifts */
    
    /**
     * Tests that {@link Application#getShifts()} does not throw an exception.
     */
    @Test
    public void testGetShiftsNoException() {
        System.out.println("getShifts - no exception");

        application.getShifts();
    }    // testGetShiftsNoException()

    /**
     * Tests that {@link Application#getShifts()} does not return null.
     */
    @Test
    public void testGetShiftsNotNull() {
        System.out.println("getShifts - not null");

        List<Shift> received = application.getShifts();
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
        application.setShifts(shifts);
    }    // testSetShiftsShiftsNull()
    
    /**
     * Tests that {@link Application#setShifts(List)} throws a
     * {@link NullPointerException} when shifts is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetShiftsShiftsContainsNull() throws IOException {
        System.out.println("setShifts - shifts contains null");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false), null);
        application.setShifts(shifts);
    }    // testSetShiftsShiftsContainsNull()
    
    /**
     * Tests that {@link Application#setShifts(List)} does not throw an
     * exception when shifts is not null and contains no nulls.
     */
    @Test
    public void testSetShiftsNoException() throws IOException {
        System.out.println("setShifts - no exception");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        application.setShifts(shifts);
    }    // testSetShiftsNoException()
    
    /**
     * Tests that {@link Application#setShifts(List)} does not alter its
     * argument.
     */
    @Test
    public void testSetShiftsDoesNotAlterArgument() throws IOException {
        System.out.println("setShifts - does not alter argument");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", ""));
        List<Shift> expected = new LinkedList<>();
        for (Shift shift : shifts) {
            expected.add(shift.clone());
        }    // for
        application.setShifts(shifts);
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

        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", ""));
        List<Shift> expected = new LinkedList<>();
        for (Shift shift : shifts) {
            Shift newShift = shift.clone();
            newShift.setVolunteer(null);
            expected.add(newShift);
        }    // for
        application.setShifts(shifts);
        List<Shift> received = application.getShifts();
        assertEquals(expected, received);
    }    // testGetShiftsSetShiftsListsAreEqualMinusVolunteers()
    
    /**
     * Tests that {@link Application#getShifts()} returns a list that is not
     * identical to that passed to {@link Application#setShifts(List)}.
     */
    @Test
    public void testGetShiftsSetShiftsNoIdentity() throws IOException {
        System.out.println("getShifts/setShifts - lists are not identical");

        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        List<Shift> expected = shifts;
        application.setShifts(shifts);
        List<Shift> received = application.getShifts();
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

        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        application.setShifts(shifts);
        List<Shift> received = application.getShifts();
        for (int i = 0; i < shifts.size(); ++i) {
            assertFalse(shifts.get(i) == received.get(i));
        }    // for
    }    // testGetShiftsSetShiftsNoElementIdentity()

    /* getVolunteers / setVolunteers */
    
    /**
     * Tests that {@link Application#getVolunteers()} does not throw an exception.
     */
    @Test
    public void testGetVolunteersNoException() {
        System.out.println("getVolunteers - no exception");

        application.getVolunteers();
    }    // testGetVolunteersNoException()

    /**
     * Tests that {@link Application#getVolunteers()} does not return null.
     */
    @Test
    public void testGetVolunteersNotNull() {
        System.out.println("getVolunteers - not null");

        List<Volunteer> received = application.getVolunteers();
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
        application.setVolunteers(volunteers);
    }    // testSetVolunteersVolunteersNull()
    
    /**
     * Tests that {@link Application#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetVolunteersVolunteersContainsNull() throws IOException {
        System.out.println("setVolunteers - volunteers contains null");

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), null);
        application.setVolunteers(volunteers);
    }    // testSetVolunteersVolunteersContainsNull()
    
    /**
     * Tests that {@link Application#setVolunteers(List)} does not throw an
     * exception when volunteers is not null and contains no nulls.
     */
    @Test
    public void testSetVolunteersNoException() throws IOException {
        System.out.println("setVolunteers - no exception");

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        application.setVolunteers(volunteers);
    }    // testSetVolunteersNoException()
    
    /**
     * Tests that {@link Application#setVolunteers(List)} does not alter its
     * argument.
     */
    @Test
    public void testSetVolunteersDoesNotAlterArgument() throws IOException {
        System.out.println("setVolunteers - does not alter argument");

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        List<Volunteer> expected = new ArrayList<>(volunteers);
        application.setVolunteers(volunteers);
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

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        List<Volunteer> expected = volunteers;
        application.setVolunteers(volunteers);
        List<Volunteer> received = application.getVolunteers();
        assertEquals(expected, received);
    }    // testGetVolunteersSetVolunteersListsAreEqual()
    
    /**
     * Tests that {@link Application#getVolunteers()} returns a list that is not
     * identical to that passed to {@link Application#setVolunteers(List)}.
     */
    @Test
    public void testGetVolunteersSetVolunteersNoIdentity() throws IOException {
        System.out.println("getVolunteers/setVolunteers - lists are not identical");

        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        List<Volunteer> expected = volunteers;
        application.setVolunteers(volunteers);
        List<Volunteer> received = application.getVolunteers();
        assertFalse(expected == received);
    }    // testGetVolunteersSetVolunteersNoIdentity()
    
    /* getEmailTemplate / setEmailTemplate */
    
    /**
     * Tests that {@link Application#getEmailTemplate()} does not throw an exception.
     */
    @Test
    public void testGetEmailTemplateNoException() throws IOException {
        System.out.println("getEmailTemplate - no exception");

        application.getEmailTemplate();
    }    // testGetEmailTemplateNoException()

    /**
     * Tests that {@link Application#getEmailTemplate()} does not return null.
     */
    @Test
    public void testGetEmailTemplateNotNull() throws IOException {
        System.out.println("getEmailTemplate - not null");

        EmailTemplate received = application.getEmailTemplate();
        assertNotNull(received);
    }    // testGetEmailTemplateNotNull()
    
    /**
     * Tests that {@link Application#setEmailTemplate(bscmail.EmailTemplate)} throws a
     * {@link NullPointerException} when templateReader is null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetEmailTemplateArgNull() throws IOException {
        System.out.println("setEmailTemplate - templateReader is null");

        EmailTemplate emailTemplate = null;
        application.setEmailTemplate(emailTemplate);
    }    // testSetEmailTemplateArgNull()
    
    /**
     * Tests that {@link Application#setEmailTemplate(bscmail.EmailTemplate)} does not throw an
     * exception when templateReAder is not null.
     */
    @Test
    public void testSetEmailTemplateArgNotNull() throws IOException {
        System.out.println("setEmailTemplate - templateReader is not null");

        EmailTemplate emailTemplate = new EmailTemplate("", "");
        application.setEmailTemplate(emailTemplate);
    }    // testSetEmailTemplateArgNotNull()
    
    /**
     * Tests that the email template returned by
     * {@link Application#setEmailTemplate(bscmail.EmailTemplate)} is equal to the email template
     * passed to {@link Application#setEmailTemplate(List)}.
     */
    @Test
    public void testGetEmailTemplateSetEmailTemplateContents() throws IOException {
        System.out.println("getEmailTemplate/setEmailTemplate - character streams have equal contents");

        EmailTemplate emailTemplate = new EmailTemplate("foo", "bar");
        application.setEmailTemplate(emailTemplate);

        EmailTemplate received = application.getEmailTemplate();
        EmailTemplate expected = emailTemplate;
        assertEquals(expected, received);
    }    // testGetEmailTemplateSetEmailTemplateContents()
    
    /* registerObserver(ShiftsObserver) */
    
    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterObserverShiftsObserverNull() {
        System.out.println("registerObserver(ShiftsObserver) - observer is null");

        ShiftsObserver observer = null;
        application.registerObserver(observer);
    }    // testRegisterObserverShiftsObserverNull()
    
    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void testRegisterObserverShiftsObserverNotNull() {
        System.out.println("registerObserver(ShiftsObserver) - observer is null");

        ShiftsObserver observer = new ApplicationObserver();
        application.registerObserver(observer);
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
            application.registerObserver(observer);
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
            application.registerObserver(observer);
        }    // for
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        application.setShifts(shifts);
        
        for (ApplicationObserver observer : observers) {
            assertTrue(observer.getShiftsChanged());
        }    // for
    }    // testSetShiftsRegisterObserverShiftsNotifiesAll()


    /**
     * Tests that a call to {@link Application#setVolunteers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void testSetVolunteersRegisterObserverShiftsNotifiesAll() throws IOException {
        System.out.println("setVolunteers/registerObserver(ShiftsObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((ShiftsObserver)observer);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        application.setVolunteers(volunteers);
        
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
        application.registerObserver((ShiftsObserver)observer);
        EmailTemplate emailTemplate = new EmailTemplate("Foo", "Bar");
        application.setEmailTemplate(emailTemplate);
        
        assertFalse(observer.getShiftsChanged());
    }    // testSetEmailTemplateRegisterObserverShiftsNotifiesAll()

    /* registerObserver(VolunteersObserver) */
     
    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterObserverVolunteersObserverNull() {
        System.out.println("registerObserver(VolunteersObserver) - observer is null");

        VolunteersObserver observer = null;
        application.registerObserver(observer);
    }    // testRegisterObserverVolunteersObserverNull()
    
    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void testRegisterObserverVolunteersObserverNotNull() {
        System.out.println("registerObserver(VolunteersObserver) - observer is null");

        VolunteersObserver observer = new ApplicationObserver();
        application.registerObserver(observer);
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
            application.registerObserver(observer);
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
            application.registerObserver(observer);
        }    // for
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        application.setVolunteers(volunteers);
        
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
        application.registerObserver((VolunteersObserver)observer);
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        application.setShifts(shifts);
        
        assertFalse(observer.getVolunteersChanged());
    }    // testSetShiftsRegisterObserverVolunteersNotifiesAll()

    /* registerObserver(EmailTemplateObserver) */
     
    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)}
     * throws a {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterObserverEmailTemplateObserverNull() {
        System.out.println("registerObserver(EmailTemplateObserver) - observer is null");

        EmailTemplateObserver observer = null;
        application.registerObserver(observer);
    }    // testRegisterObserverEmailTemplateObserverNull()
    
    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)}
     * does not throw an exception when observer is not null.
     */
    @Test
    public void testRegisterObserverEmailTemplateObserverNotNull() {
        System.out.println("registerObserver(EmailTemplateObserver) - observer is null");

        EmailTemplateObserver observer = new ApplicationObserver();
        application.registerObserver(observer);
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
            application.registerObserver(observer);
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
            application.registerObserver(observer);
        }    // for
        EmailTemplate emailTemplate = new EmailTemplate("Foo", "Bar");
        application.setEmailTemplate(emailTemplate);
        
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
        application.registerObserver((EmailTemplateObserver)observer);
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        application.setShifts(shifts);
        
        assertFalse(observer.getEmailTemplateChanged());
    }    // testSetShiftsRegisterObserverEmailTemplateNotifiesAll()

    /**
     * Tests that a call to {@link Application#setVolunteers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void testSetVolunteersRegisterObserverEmailTemplateNotifiesAll() throws IOException {
        System.out.println("setVolunteers/registerObserver(EmailTemplateObserver) - does not notify");

        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((EmailTemplateObserver)observer);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        application.setVolunteers(volunteers);
        
        assertFalse(observer.getEmailTemplateChanged());
    }    // testSetVolunteersRegisterObserverEmailTemplateNotifiesAll()
    
}    // ApplicationTest
