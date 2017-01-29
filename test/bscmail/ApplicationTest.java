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
    private class ApplicationObserver implements ShiftsObserver, VolunteersObserver, RolesObserver, EmailTemplateObserver, EventPropertiesObserver {
        private boolean shiftsChanged = false;
        private boolean volunteersChanged = false;
        private boolean rolesChanged = false;
        private boolean emailTemplateChanged = false;
        private boolean eventPropertiesChanged = false;

        @Override public void shiftsChanged() { shiftsChanged = true; }
        @Override public void volunteersChanged() { volunteersChanged = true; }
        @Override public void rolesChanged() { rolesChanged = true; }
        @Override public void emailTemplateChanged() { emailTemplateChanged = true; }
        @Override public void eventPropertiesChanged() { eventPropertiesChanged = true; }

        public boolean getShiftsChanged() { return shiftsChanged; }
        public boolean getVolunteersChanged() { return volunteersChanged; }
        public boolean getRolesChanged() { return rolesChanged; }
        public boolean getEmailTemplateChanged() { return emailTemplateChanged; }
        public boolean getEventPropertiesChanged() { return eventPropertiesChanged; }
    }    // ApplicationObserver

    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that {@link Application#Application()} does not throw an exception.
     */
    @Test
    public void constructorDoesNotThrowException() {
        Application application = new Application();
    }    // constructorDoesNotThrowException()

    /* getApplicationName */

    /**
     * Tests that {@link Application#getApplicationName()} does not throw an
     * exception.
     */
    @Test
    public void getApplicationNameDoesNotThrowException() {
        Application application = new Application();

        application.getApplicationName();
    }    // getApplicationNameDoesNotThrowException()

    /**
     * Tests that {@link Application#getApplicationName()} does not return null.
     */
    @Test
    public void getApplicationNameDoesNotReturnNull() {
        Application application = new Application();

        String received = application.getApplicationName();
        assertNotNull(received);
    }    // getApplicationNameDoesNotReturnNull()

    /* getVersion */

    /**
     * Tests that {@link Application#getVersion()} does not throw an exception.
     */
    @Test
    public void getVersionDoesNotThrowException() {
        Application application = new Application();

        application.getVersion();
    }    // getVersionDoesNotThrowException()

    /**
     * Tests that {@link Application#getVersion()} does not return null.
     */
    @Test
    public void getVersionDoesNotReturnNull() {
        Application application = new Application();

        String received = application.getVersion();

        assertNotNull(received);
    }    // getVersionDoesNotReturnNull()

    /* getCopyright */

    /**
     * Tests that {@link Application#getCopyright()} does not throw an exception.
     */
    @Test
    public void getCopyrightDoesNotThrowException() {
        Application application = new Application();

        application.getCopyright();
    }    // getCopyrightDoesNotThrowException()

    /**
     * Tests that {@link Application#getCopyright()} does not return null.
     */
    @Test
    public void getCopyrightDoesNotReturnNull() {
        Application application = new Application();

        String received = application.getCopyright();

        assertNotNull(received);
    }    // getCopyrightDoesNotReturnNull()

    /* getUserGuideFilename */

    /**
     * Tests that {@link Application#getUserGuideFilename()} does not throw an
     * exception.
     */
    @Test
    public void getUserGuideFilenameDoesNotThrowException() {
        Application application = new Application();

        application.getUserGuideFilename();
    }    // getUserGuideFilenameDoesNotThrowException()

    /**
     * Tests that {@link Application#getUserGuideFilename()} does not return
     * null.
     */
    @Test
    public void getUserGuideFilenameDoesNotReturnNull() {
        Application application = new Application();

        String received = application.getUserGuideFilename();

        assertNotNull(received);
    }    // getUserGuideFilenameDoesNotReturnNull()

    /* getShifts / setShifts */

    /**
     * Tests that {@link Application#getShifts()} does not throw an exception.
     */
    @Test
    public void getShiftsDoesNotThrowException() {
        Application application = new Application();

        application.getShifts();
    }    // getShiftsDoesNotThrowException()

    /**
     * Tests that {@link Application#getShifts()} does not return null.
     */
    @Test
    public void getShiftsDoesNotReturnNull() {
        Application application = new Application();

        List<Shift> received = application.getShifts();

        assertNotNull(received);
    }    // getShiftsDoesNotReturnNull()

    /**
     * Tests that {@link Application#setShifts(List)} throws a
     * {@link NullPointerException} when shifts is null.
     */
    @Test(expected = NullPointerException.class)
    public void setShiftsThrowsExceptionWhenShiftsIsNull() throws IOException {
        Application application = new Application();
        List<Shift> shifts = null;

        application.setShifts(shifts);
    }    // setShiftsThrowsExceptionWhenShiftsIsNull()

    /**
     * Tests that {@link Application#setShifts(List)} throws a
     * {@link NullPointerException} when shifts is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void setShiftsThrowsExceptionWhenShiftsContainsNull() throws IOException {
        Application application = new Application();
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false), null);

        application.setShifts(shifts);
    }    // setShiftsThrowsExceptionWhenShiftsContainsNull()

    /**
     * Tests that {@link Application#setShifts(List)} does not throw an
     * exception when shifts is not null and contains no nulls.
     */
    @Test
    public void setShiftsDoesNotThrowExceptionWithGoodArgument() throws IOException {
        Application application = new Application();
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));

        application.setShifts(shifts);
    }    // setShiftsDoesNotThrowExceptionWithGoodArgument()

    /**
     * Tests that {@link Application#setShifts(List)} does not alter its
     * argument.
     */
    @Test
    public void setShiftsDoesNotAlterArgument() throws IOException {
        Application application = new Application();
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", ""));
        List<Shift> clonedShifts = new LinkedList<>();
        for (Shift shift : shifts) {
            clonedShifts.add(shift.clone());
        }    // for

        application.setShifts(shifts);

        List<Shift> expected = clonedShifts;
        List<Shift> received = shifts;
        assertEquals(expected, received);
    }    // setShiftsDoesNotAlterArgument()

    /**
     * Tests that {@link Application#getShifts()} does not throw an exception
     * when there are no shifts.
     */
    @Test
    public void getShiftsDoesNotThrowExceptionWhenThereAreNoShifts() throws IOException {
        Application application = new Application();

        application.getShifts();
    }    // getShiftsDoesNotThrowExceptionWhenThereAreNoShifts()

    /**
     * Tests that {@link Application#getShifts()} does not return null when
     * there are no shifts.
     */
    @Test
    public void getShiftsDoesNotReturnNullWhenThereAreNoShifts() throws IOException {
        Application application = new Application();

        List<Shift> received = application.getShifts();

        assertNotNull(received);
    }    // getShiftsDoesNotReturnNullWhenThereAreNoShifts()

    /**
     * Tests that {@link Application#getShifts()} does not throw an exception
     * when there are shifts.
     */
    @Test
    public void getShiftsDoesNotThrowExceptionWhenThereAreShifts() throws IOException {
        Application application = new Application();
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", ""));
        application.setShifts(shifts);

        application.getShifts();
    }    // getShiftsDoesNotThrowExceptionWhenThereAreShifts()

    /**
     * Tests that {@link Application#getShifts()} does not return null when
     * there are shifts.
     */
    @Test
    public void getShiftsDoesNotReturnNullWhenThereAreShifts() throws IOException {
        Application application = new Application();
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", ""));
        application.setShifts(shifts);

        List<Shift> received = application.getShifts();

        assertNotNull(received);
    }    // getShiftsDoesNotThrowExceptionWhenThereAreShifts()

    /**
     * Tests that {@link Application#getShifts()} returns a list equal to that
     * passed to {@link Application#setShifts(List)}, minus any volunteers.
     */
    @Test
    public void getShiftsReturnsCorrectValue() throws IOException {
        Application application = new Application();
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", ""));
        List<Shift> clonedShiftsMinusVolunteers = new LinkedList<>();
        for (Shift shift : shifts) {
            Shift newShift = shift.clone();
            newShift.setVolunteer(null);
            clonedShiftsMinusVolunteers.add(newShift);
        }    // for
        application.setShifts(shifts);

        List<Shift> received = application.getShifts();

        List<Shift> expected = clonedShiftsMinusVolunteers;
        assertEquals(expected, received);
    }    // getShiftsReturnsCorrectValue()

    /**
     * Tests that {@link Application#getShifts()} returns a list that is not
     * identical to that passed to {@link Application#setShifts(List)}.
     */
    @Test
    public void getShiftsDoesNotReturnTheIdenticalListThatWasPassedToSetShifts() throws IOException {
        Application application = new Application();
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        application.setShifts(shifts);

        List<Shift> received = application.getShifts();

        List<Shift> notExpected = shifts;
        assertFalse(notExpected == received);
    }    // getShiftsDoesNotReturnTheIdenticalListThatWasPassedToSetShifts()

    /**
     * Tests that the elements of the list returned by
     * {@link Application#getShifts()} are not identical identical to those of
     * the list passed to {@link Application#setShifts(List)}.
     */
    @Test
    public void getShiftsReturnsAListWhoseElementsAreNotIdenticalToThosePassedToSetShifts() throws IOException {
        Application application = new Application();
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));
        application.setShifts(shifts);

        List<Shift> received = application.getShifts();

        List<Shift> notExpected = shifts;
        for (int i = 0; i < shifts.size(); ++i) {
            assertFalse(notExpected.get(i) == received.get(i));
        }    // for
    }    // testGetShiftsSetShiftsNoElementIdentity()

    /* getVolunteers / setVolunteers */

    /**
     * Tests that {@link Application#getVolunteers()} does not throw an
     * exception when there are no volunteers.
     */
    @Test
    public void getVolunteersDoesNotThrowExceptionWhenThereAreNoVolunteers() {
        Application application = new Application();

        application.getVolunteers();
    }    // getVolunteersDoesNotThrowExceptionWhenThereAreNoVolunteers()

    /**
     * Tests that {@link Application#getVolunteers()} does not return null when
     * there are no volunteers.
     */
    @Test
    public void getVolunteersDoesNotReturnNullWhenThereAreNoVolunteers() {
        Application application = new Application();

        List<Volunteer> received = application.getVolunteers();

        assertNotNull(received);
    }    // getVolunteersDoesNotReturnNullWhenThereAreNoVolunteers()

    /**
     * Tests that {@link Application#getVolunteers()} does not throw an
     * exception when there are volunteers.
     */
    @Test
    public void getVolunteersDoesNotThrowExceptionWhenThereAreVolunteers() throws IOException {
        Application application = new Application();
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        application.setVolunteers(volunteers);

        application.getVolunteers();
    }    // getVolunteersDoesNotThrowExceptionWhenThereAreVolunteers()

    /**
     * Tests that {@link Application#getVolunteers()} does not return null when
     * there are volunteers.
     */
    @Test
    public void getVolunteersDoesNotReturnNullWhenThereAreVolunteers() throws IOException {
        Application application = new Application();
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        application.setVolunteers(volunteers);

        List<Volunteer> received = application.getVolunteers();

        assertNotNull(received);
    }    // getVolunteersDoesNotReturnNullWhenThereAreVolunteers()

    /**
     * Tests that {@link Application#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is null.
     */
    @Test(expected = NullPointerException.class)
    public void setVolunteersThrowsExceptionWhenVolunteersIsNull() throws IOException {
        Application application = new Application();
        List<Volunteer> volunteers = null;

        application.setVolunteers(volunteers);
    }    // setVolunteersThrowsExceptionWhenVolunteersIsNull()

    /**
     * Tests that {@link Application#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void setVolunteersThrowsExceptionWhenVolunteersContainsNull() throws IOException {
        Application application = new Application();
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), null);

        application.setVolunteers(volunteers);
    }    // setVolunteersThrowsExceptionWhenVolunteersContainsNull()

    /**
     * Tests that {@link Application#setVolunteers(List)} does not throw an
     * exception when volunteers is not null and contains no nulls.
     */
    @Test
    public void setVolunteersDoesNotThrowExceptionNormally() throws IOException {
        Application application = new Application();
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));

        application.setVolunteers(volunteers);
    }    // setVolunteersDoesNotThrowExceptionNormally()

    /**
     * Tests that {@link Application#setVolunteers(List)} does not alter its
     * argument.
     */
    @Test
    public void setVolunteersDoesNotAlterArgument() throws IOException {
        Application application = new Application();
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        List<Volunteer> expected = new ArrayList<>(volunteers);

        application.setVolunteers(volunteers);

        List<Volunteer> received = volunteers;
        assertEquals(expected, received);
    }    // setVolunteersDoesNotAlterArgument()

    /**
     * Tests that {@link Application#getVolunteers()} returns a list that is
     * equal to that passed to {@link Application#setVolunteers(List)}.
     */
    @Test
    public void getVolunteersSetVolunteersListsAreEqual() throws IOException {
        Application application = new Application();
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        application.setVolunteers(volunteers);

        List<Volunteer> received = application.getVolunteers();

        List<Volunteer> expected = volunteers;
        assertEquals(expected, received);
    }    // getVolunteersSetVolunteersListsAreEqual()

    /**
     * Tests that {@link Application#getVolunteers()} returns a list that is not
     * identical to that passed to {@link Application#setVolunteers(List)}.
     */
    @Test
    public void getVolunteersSetVolunteersListsAreNotIdentical() throws IOException {
        Application application = new Application();
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));
        application.setVolunteers(volunteers);

        List<Volunteer> received = application.getVolunteers();

        List<Volunteer> notExpected = volunteers;
        assertFalse(notExpected == received);
    }    // getVolunteersSetVolunteersListsAreNotIdentical()

    /* getRoles / setRoles */

    /**
     * Tests that {@link Application#getRoles()} does not throw an
     * exception when there are no roles.
     */
    @Test
    public void getRolesDoesNotThrowExceptionWhenThereAreNoRoles() {
        Application application = new Application();

        application.getRoles();
    }    // getRolesDoesNotThrowExceptionWhenThereAreNoRoles()

    /**
     * Tests that {@link Application#getRoles()} does not return null when
     * there are no roles.
     */
    @Test
    public void getRolesDoesNotReturnNullWhenThereAreNoRoles() {
        Application application = new Application();

        List<Role> received = application.getRoles();

        assertNotNull(received);
    }    // getRolesDoesNotReturnNullWhenThereAreNoRoles()

    /**
     * Tests that {@link Application#getRoles()} does not throw an
     * exception when there are roles.
     */
    @Test
    public void getRolesDoesNotThrowExceptionWhenThereAreRoles() throws IOException {
        Application application = new Application();
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
        application.setRoles(roles);

        application.getRoles();
    }    // getRolesDoesNotThrowExceptionWhenThereAreRoles()

    /**
     * Tests that {@link Application#getRoles()} does not return null when
     * there are roles.
     */
    @Test
    public void getRolesDoesNotReturnNullWhenThereAreRoles() throws IOException {
        Application application = new Application();
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
        application.setRoles(roles);

        List<Role> received = application.getRoles();

        assertNotNull(received);
    }    // getRolesDoesNotReturnNullWhenThereAreRoles()

    /**
     * Tests that {@link Application#setRoles(List)} throws a
     * {@link NullPointerException} when roles is null.
     */
    @Test(expected = NullPointerException.class)
    public void setRolesThrowsExceptionWhenRolesIsNull() throws IOException {
        Application application = new Application();
        List<Role> roles = null;

        application.setRoles(roles);
    }    // setRolesThrowsExceptionWhenRolesIsNull()

    /**
     * Tests that {@link Application#setRoles(List)} throws a
     * {@link NullPointerException} when roles is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void setRolesThrowsExceptionWhenRolesContainsNull() throws IOException {
        Application application = new Application();
        List<Role> roles = Arrays.asList(new Role("Foo"), null);

        application.setRoles(roles);
    }    // setRolesThrowsExceptionWhenRolesContainsNull()

    /**
     * Tests that {@link Application#setRoles(List)} does not throw an
     * exception when roles is not null and contains no nulls.
     */
    @Test
    public void setRolesDoesNotThrowExceptionNormally() throws IOException {
        Application application = new Application();
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

        application.setRoles(roles);
    }    // setRolesDoesNotThrowExceptionNormally()

    /**
     * Tests that {@link Application#setRoles(List)} does not alter its
     * argument.
     */
    @Test
    public void setRolesDoesNotAlterArgument() throws IOException {
        Application application = new Application();
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
        List<Role> expected = new ArrayList<>(roles);

        application.setRoles(roles);

        List<Role> received = roles;
        assertEquals(expected, received);
    }    // setRolesDoesNotAlterArgument()

    /**
     * Tests that {@link Application#getRoles()} returns a list that is
     * equal to that passed to {@link Application#setRoles(List)}.
     */
    @Test
    public void getRolesSetRolesListsAreEqual() throws IOException {
        Application application = new Application();
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
        application.setRoles(roles);

        List<Role> received = application.getRoles();

        List<Role> expected = roles;
        assertEquals(expected, received);
    }    // getRolesSetRolesListsAreEqual()

    /**
     * Tests that {@link Application#getRoles()} returns a list that is not
     * identical to that passed to {@link Application#setRoles(List)}.
     */
    @Test
    public void getRolesSetRolesListsAreNotIdentical() throws IOException {
        Application application = new Application();
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
        application.setRoles(roles);

        List<Role> received = application.getRoles();

        List<Role> notExpected = roles;
        assertFalse(notExpected == received);
    }    // getRolesSetRolesListsAreNotIdentical()

    /* getEmailTemplate / setEmailTemplate */

    /**
     * Tests that {@link Application#getEmailTemplate()} does not throw an
     * exception.
     */
    @Test
    public void getEmailTemplateDoesNotThrowException() throws IOException {
        Application application = new Application();

        application.getEmailTemplate();
    }    // getEmailTemplateDoesNotThrowException()

    /**
     * Tests that {@link Application#getEmailTemplate()} does not return null.
     */
    @Test
    public void getEmailTemplateDoesNotReturnNull() throws IOException {
        Application application = new Application();

        EmailTemplate received = application.getEmailTemplate();

        assertNotNull(received);
    }    // getEmailTemplateDoesNotReturnNull()

    /**
     * Tests that {@link Application#setEmailTemplate(bscmail.EmailTemplate)}
     * throws a {@link NullPointerException} when emailTemplate is null.
     */
    @Test(expected = NullPointerException.class)
    public void setEmailTemplateThrowsExceptionWhenEmailTemplateIsNull() throws IOException {
        Application application = new Application();
        EmailTemplate emailTemplate = null;

        application.setEmailTemplate(emailTemplate);
    }    // setEmailTemplateThrowsExceptionWhenEmailTemplateIsNull()

    /**
     * Tests that {@link Application#setEmailTemplate(bscmail.EmailTemplate)} does not throw an
     * exception when emailTemplate is not null.
     */
    @Test
    public void setEmailTemplateDoesNotThrowExceptionWhenEmailTemplateIsNotNull() throws IOException {
        Application application = new Application();
        EmailTemplate emailTemplate = new EmailTemplate("", "");

        application.setEmailTemplate(emailTemplate);
    }    // setEmailTemplateDoesNotThrowExceptionWhenEmailTemplateIsNotNull()

    /**
     * Tests that the email template returned by
     * {@link Application#setEmailTemplate(bscmail.EmailTemplate)} is equal to
     * the email template passed to {@link Application#setEmailTemplate(List)}.
     */
    @Test
    public void getEmailTemplateReturnsArgumentPassedToSetEmailTemplate() throws IOException {
        Application application = new Application();
        EmailTemplate emailTemplate = new EmailTemplate("foo", "bar");
        application.setEmailTemplate(emailTemplate);

        EmailTemplate received = application.getEmailTemplate();

        EmailTemplate expected = emailTemplate;
        assertEquals(expected, received);
    }    // getEmailTemplateReturnsArgumentPassedToSetEmailTemplate()

    /* getEventProperties / setEventProperties */

    /**
     * Tests that {@link Application#getEventProperties()} does not throw an
     * exception when there are no event properties.
     */
    @Test
    public void getEventPropertiesDoesNotThrowExceptionWhenThereAreNoEventProperties() {
        Application application = new Application();

        application.getEventProperties();
    }    // getEventPropertiesDoesNotThrowExceptionWhenThereAreNoEventProperties()

    /**
     * Tests that {@link Application#getEventProperties()} does not return null
     * when there are no event properties.
     */
    @Test
    public void getEventPropertiesDoesNotReturnNullWhenThereAreNoEventProperties() {
        Application application = new Application();

        List<EventProperty> received = application.getEventProperties();

        assertNotNull(received);
    }    // getEventPropertiesDoesNotReturnNullWhenThereAreNoEventProperties()

    /**
     * Tests that {@link Application#getEventProperties()} does not throw an
     * exception when there are event properties.
     */
    @Test
    public void getEventPropertiesDoesNotThrowExceptionWhenThereAreEventProperties() throws IOException {
        Application application = new Application();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
        application.setEventProperties(eventProperties);

        application.getEventProperties();
    }    // getEventPropertiesDoesNotThrowExceptionWhenThereAreEventProperties()

    /**
     * Tests that {@link Application#getEventProperties()} does not return null
     * when there are event properties.
     */
    @Test
    public void getEventPropertiesDoesNotReturnNullWhenThereAreEventProperties() throws IOException {
        Application application = new Application();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
        application.setEventProperties(eventProperties);

        List<EventProperty> received = application.getEventProperties();

        assertNotNull(received);
    }    // getEventPropertiesDoesNotReturnNullWhenThereAreEventProperties()

    /**
     * Tests that {@link Application#setEventProperties(List)} throws a
     * {@link NullPointerException} when eventProperties is null.
     */
    @Test(expected = NullPointerException.class)
    public void setEventPropertiesThrowsExceptionWhenEventPropertiesIsNull() throws IOException {
        Application application = new Application();
        List<EventProperty> eventProperties = null;

        application.setEventProperties(eventProperties);
    }    // setEventPropertiesThrowsExceptionWhenEventPropertiesIsNull()

    /**
     * Tests that {@link Application#setEventProperties(List)} throws a
     * {@link NullPointerException} when eventProperties is not null but
     * contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void setEventPropertiesThrowsExceptionWhenEventPropertiesContainsNull() throws IOException {
        Application application = new Application();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), null);

        application.setEventProperties(eventProperties);
    }    // setEventPropertiesThrowsExceptionWhenEventPropertiesContainsNull()

    /**
     * Tests that {@link Application#setEventProperties(List)} does not throw an
     * exception when eventProperties is not null and contains no nulls.
     */
    @Test
    public void setEventPropertiesDoesNotThrowExceptionNormally() throws IOException {
        Application application = new Application();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

        application.setEventProperties(eventProperties);
    }    // setEventPropertiesDoesNotThrowExceptionNormally()

    /**
     * Tests that {@link Application#setEventProperties(List)} does not alter
     * its argument.
     */
    @Test
    public void setEventPropertiesDoesNotAlterArgument() throws IOException {
        Application application = new Application();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
        List<EventProperty> expected = new ArrayList<>(eventProperties);

        application.setEventProperties(eventProperties);

        List<EventProperty> received = eventProperties;
        assertEquals(expected, received);
    }    // setEventPropertiesDoesNotAlterArgument()

    /**
     * Tests that {@link Application#getEventProperties()} returns a list that
     * is equal to that passed to {@link Application#setEventProperties(List)}.
     */
    @Test
    public void getEventPropertiesSetEventPropertiesListsAreEqual() throws IOException {
        Application application = new Application();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
        application.setEventProperties(eventProperties);

        List<EventProperty> received = application.getEventProperties();

        List<EventProperty> expected = eventProperties;
        assertEquals(expected, received);
    }    // getEventPropertiesSetEventPropertiesListsAreEqual()

    /**
     * Tests that {@link Application#getEventProperties()} returns a list that
     * is not identical to that passed to
     * {@link Application#setEventProperties(List)}.
     */
    @Test
    public void getEventPropertiesSetEventPropertiesListsAreNotIdentical() throws IOException {
        Application application = new Application();
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
        application.setEventProperties(eventProperties);

        List<EventProperty> received = application.getEventProperties();

        List<EventProperty> notExpected = eventProperties;
        assertFalse(notExpected == received);
    }    // getEventPropertiesSetEventPropertiesListsAreNotIdentical()

    /* registerObserver(ShiftsObserver) */

    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverShiftsObserverThrowsExceptionWhenObserverIsNull() {
        Application application = new Application();
        ShiftsObserver observer = null;

        application.registerObserver(observer);
    }    // registerObserverShiftsObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void registerObserverShiftsObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        Application application = new Application();
        ShiftsObserver observer = new ApplicationObserver();

        application.registerObserver(observer);
    }    // registerObserverShiftsObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverShiftsObserverDoesNotThrowExceptionWhenCalledTwice() {
        Application application = new Application();
        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };

        for (ShiftsObserver observer : observers) {
            application.registerObserver(observer);
        }    // for
    }    // registerObserverShiftsObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setShifts(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setShiftsNotifiesAllShiftsObservers() throws IOException {
        Application application = new Application();
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
    }    // setShiftsNotifiesAllShiftsObservers()

    /**
     * Tests that a call to {@link Application#setVolunteers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setVolunteersDoesNotNotifyShiftsObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((ShiftsObserver)observer);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));

        application.setVolunteers(volunteers);

        assertFalse(observer.getShiftsChanged());
    }    // setVolunteersDoesNotNotifyShiftsObservers()

    /**
     * Tests that a call to {@link Application#Roles(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setRolesDoesNotNotifyShiftsObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((ShiftsObserver)observer);
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

        application.setRoles(roles);

        assertFalse(observer.getShiftsChanged());
    }    // setRolesDoesNotNotifyShiftsObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setEmailTemplateDoesNotNotifyShiftObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((ShiftsObserver)observer);
        EmailTemplate emailTemplate = new EmailTemplate("Foo", "Bar");

        application.setEmailTemplate(emailTemplate);

        assertFalse(observer.getShiftsChanged());
    }    // setEmailTemplateDoesNotNotifyShiftObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setEventPropertiesDoesNotNotifyShiftsObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((ShiftsObserver)observer);
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

        application.setEventProperties(eventProperties);

        assertFalse(observer.getShiftsChanged());
    }    // setEventPropertiesDoesNotNotifyShiftsObservers()

    /* registerObserver(VolunteersObserver) */

    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverVolunteersObserverThrowsExceptionWhenObserverIsNull() {
        Application application = new Application();
        VolunteersObserver observer = null;

        application.registerObserver(observer);
    }    // registerObserverVolunteersObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void registerObserverVolunteersObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        Application application = new Application();
        VolunteersObserver observer = new ApplicationObserver();

        application.registerObserver(observer);
    }    // registerObserverVolunteersObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverVolunteersObserverDoesNotThrowExceptionWhenCalledTwice() {
        Application application = new Application();
        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };

        for (VolunteersObserver observer : observers) {
            application.registerObserver(observer);
        }    // for
    }    // registerObserverVolunteersObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setVolunteers(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setVolunteersNotifiesAllVolunteersObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (VolunteersObserver observer : observers) {
            application.registerObserver(observer);
        }    // for
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));

        application.setVolunteers(volunteers);

        for (ApplicationObserver observer : observers) {
            assertTrue(observer.getVolunteersChanged());
        }    // for
    }    // setVolunteersNotifiesAllVolunteersObservers()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setShiftsDoesNotNotifyVolunteersObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((VolunteersObserver)observer);
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));

        application.setShifts(shifts);

        assertFalse(observer.getVolunteersChanged());
    }    // setShiftsDoesNotNotifyVolunteersObservers()

    /**
     * Tests that a call to {@link Application#Roles(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setRolesDoesNotNotifyVolunteersObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((VolunteersObserver)observer);
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

        application.setRoles(roles);

        assertFalse(observer.getVolunteersChanged());
    }    // setRolesDoesNotNotifyVolunteersObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setEmailTemplateDoesNotNotifyVolunteerObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((VolunteersObserver)observer);
        EmailTemplate emailTemplate = new EmailTemplate("Foo", "Bar");

        application.setEmailTemplate(emailTemplate);

        assertFalse(observer.getVolunteersChanged());
    }    // setEmailTemplateDoesNotNotifyVolunteerObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setEventPropertiesDoesNotNotifyVolunteersObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((VolunteersObserver)observer);
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

        application.setEventProperties(eventProperties);

        assertFalse(observer.getVolunteersChanged());
    }    // setEventPropertiesDoesNotNotifyVolunteersObservers()

    /* registerObserver(RolesObserver) */

    /**
     * Tests that {@link Application#registerObserver(RolesObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverRolesObserverThrowsExceptionWhenObserverIsNull() {
        Application application = new Application();
        RolesObserver observer = null;

        application.registerObserver(observer);
    }    // registerObserverRolesObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(RolesObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void registerObserverRolesObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        Application application = new Application();
        RolesObserver observer = new ApplicationObserver();

        application.registerObserver(observer);
    }    // registerObserverRolesObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(RolesObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverRolesObserverDoesNotThrowExceptionWhenCalledTwice() {
        Application application = new Application();
        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };

        for (RolesObserver observer : observers) {
            application.registerObserver(observer);
        }    // for
    }    // registerObserverRolesObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setRoles(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(RolesObserver)}.
     */
    @Test
    public void setRolesNotifiesAllRolesObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (RolesObserver observer : observers) {
            application.registerObserver(observer);
        }    // for
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

        application.setRoles(roles);

        for (ApplicationObserver observer : observers) {
            assertTrue(observer.getRolesChanged());
        }    // for
    }    // setRolesNotifiesAllRolesObservers()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(RolesObserver)}.
     */
    @Test
    public void setShiftsDoesNotNotifyRolesObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((RolesObserver)observer);
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));

        application.setShifts(shifts);

        assertFalse(observer.getRolesChanged());
    }    // setShiftsDoesNotNotifyRolesObservers()

    /**
     * Tests that a call to {@link Application#Volunteers(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setVolunteersDoesNotNotifyRolesObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((RolesObserver)observer);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));

        application.setVolunteers(volunteers);

        assertFalse(observer.getVolunteersChanged());
    }    // setVolunteersDoesNotNotifyRolesObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(RolesObserver)}.
     */
    @Test
    public void setEmailTemplateDoesNotNotifyRoleObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((RolesObserver)observer);
        EmailTemplate emailTemplate = new EmailTemplate("Foo", "Bar");

        application.setEmailTemplate(emailTemplate);

        assertFalse(observer.getRolesChanged());
    }    // setEmailTemplateDoesNotNotifyRoleObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(RolesObserver)}.
     */
    @Test
    public void setEventPropertiesDoesNotNotifyRolesObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((RolesObserver)observer);
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

        application.setEventProperties(eventProperties);

        assertFalse(observer.getRolesChanged());
    }    // setEventPropertiesDoesNotNotifyRolesObservers()

    /* registerObserver(EmailTemplateObserver) */

    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)}
     * throws a {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverEmailTemplateObserverThrowsExceptionWhenObserverIsNull() {
        Application application = new Application();
        EmailTemplateObserver observer = null;

        application.registerObserver(observer);
    }    // registerObserverEmailTemplateObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)}
     * does not throw an exception when observer is not null.
     */
    @Test
    public void registerObserverEmailTemplateObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        Application application = new Application();
        EmailTemplateObserver observer = new ApplicationObserver();

        application.registerObserver(observer);
    }    // registerObserverEmailTemplateObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverEmailTemplateObserverDoesNotThrowExceptionWhenCalledTwice() {
        Application application = new Application();
        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };

        for (EmailTemplateObserver observer : observers) {
            application.registerObserver(observer);
        }    // for
    }    // registerObserverEmailTemplateObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void setEmailTemplateNotifiesAllEmailTemplateObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (EmailTemplateObserver observer : observers) {
            application.registerObserver(observer);
        }    // for
        EmailTemplate emailTemplate = new EmailTemplate("Foo", "Bar");

        application.setEmailTemplate(emailTemplate);

        for (ApplicationObserver observer : observers) {
            assertTrue(observer.getEmailTemplateChanged());
        }    // for
    }    // setEmailTemplateNotifiesAllEmailTemplateObservers()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void setShiftsDoesNotNotifyEmailTemplateObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((EmailTemplateObserver)observer);
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));

        application.setShifts(shifts);

        assertFalse(observer.getEmailTemplateChanged());
    }    // setShiftsDoesNotNotifyEmailTemplateObservers()

    /**
     * Tests that a call to {@link Application#Volunteers(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setVolunteersDoesNotNotifyEmailTemplateObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((EmailTemplateObserver)observer);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));

        application.setVolunteers(volunteers);

        assertFalse(observer.getVolunteersChanged());
    }    // setVolunteersDoesNotNotifyEmailTemplateObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void setRolesDoesNotNotifyEmailTemplateObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((EmailTemplateObserver)observer);
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

        application.setRoles(roles);

        assertFalse(observer.getEmailTemplateChanged());
    }    // setRolesDoesNotNotifyEmailTemplateObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void setEventPropertiesDoesNotNotifyEmailTemplateObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((EmailTemplateObserver)observer);
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

        application.setEventProperties(eventProperties);

        assertFalse(observer.getEmailTemplateChanged());
    }    // setEventPropertiesDoesNotNotifyEmailTemplateObservers()

    /* registerObserver(EventPropertiesObserver) */

    /**
     * Tests that {@link Application#registerObserver(EventPropertiesObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverEventPropertiesObserverThrowsExceptionWhenObserverIsNull() {
        Application application = new Application();
        EventPropertiesObserver observer = null;

        application.registerObserver(observer);
    }    // registerObserverEventPropertiesObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(EventPropertiesObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void registerObserverEventPropertiesObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        Application application = new Application();
        EventPropertiesObserver observer = new ApplicationObserver();

        application.registerObserver(observer);
    }    // registerObserverEventPropertiesObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(EventPropertiesObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverEventPropertiesObserverDoesNotThrowExceptionWhenCalledTwice() {
        Application application = new Application();
        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };

        for (EventPropertiesObserver observer : observers) {
            application.registerObserver(observer);
        }    // for
    }    // registerObserverEventPropertiesObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(EventPropertyObserver)}.
     */
    @Test
    public void setEventPropertiesNotifiesAllEventPropertyObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver[] observers = { new ApplicationObserver(), new ApplicationObserver() };
        for (EventPropertiesObserver observer : observers) {
            application.registerObserver(observer);
        }    // for
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

        application.setEventProperties(eventProperties);

        for (ApplicationObserver observer : observers) {
            assertTrue(observer.getEventPropertiesChanged());
        }    // for
    }    // setEventPropertiesNotifiesAllEventPropertyObservers()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(EventPropertyObserver)}.
     */
    @Test
    public void setShiftsDoesNotNotifyEventPropertyObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((EventPropertiesObserver)observer);
        List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                new Shift("Bar", new LinkedList<Role>(), false, false, false));

        application.setShifts(shifts);

        assertFalse(observer.getEventPropertiesChanged());
    }    // setShiftsDoesNotNotifyEventPropertyObservers()

    /**
     * Tests that a call to {@link Application#Volunteers(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setVolunteersDoesNotNotifyEventPropertyObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((EventPropertiesObserver)observer);
        List<Volunteer> volunteers = Arrays.asList(new Volunteer("Foo", "foo", "", ""), new Volunteer("Bar", "bar", "", ""));

        application.setVolunteers(volunteers);

        assertFalse(observer.getVolunteersChanged());
    }    // setVolunteersDoesNotNotifyEventPropertyObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EventPropertiesObserver)}.
     */
    @Test
    public void setRolesDoesNotNotifyEventPropertyObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((EventPropertiesObserver)observer);
        List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

        application.setRoles(roles);

        assertFalse(observer.getRolesChanged());
    }    // setRolesDoesNotNotifyEventPropertyObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EventPropertiesObserver)}.
     */
    @Test
    public void setEmailTemplateDoesNotNotifyEventPropertyObservers() throws IOException {
        Application application = new Application();
        ApplicationObserver observer = new ApplicationObserver();
        application.registerObserver((EventPropertiesObserver)observer);
        EmailTemplate emailTemplate = new EmailTemplate("Foo", "Bar");

        application.setEmailTemplate(emailTemplate);

        assertFalse(observer.getEventPropertiesChanged());
    }    // setEmailTemplateDoesNotNotifyEventPropertyObservers()

}    // ApplicationTest
