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

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import main.ReadWritableFactory;
import main.ReadWritableTest;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Shift}
 * @author Wayne Miller
 */
public class ShiftTest extends ReadWritableTest {

    /**
     * Returns a valid volunteer
     */
    private Volunteer getVolunteer() {
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        boolean active = true;
        List<Role> roles = Arrays.asList();
        return new Volunteer(name, email, phone, notes, active, roles);
    }    // getVolunteer()

    /**
     * Returns the shift to be tested.
     *
     * @return the shift to be tested
     */
    @Override
    protected Shift getReadWritable() {
        String description = "foo";
        List<Role> roles = new LinkedList<>();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(getVolunteer());
        return shift;
    }    // getReadWritable()

    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that
     * {@link Shift#Shift(java.lang.String, java.util.List, boolean, boolean, boolean)}
     * throws a {@link NullPointerException} when description is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenDescriptionIsNull() {
        String description = null;
        List<Role> roles = new LinkedList<>();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;

        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
    }    // constructorThrowsExceptionWhenDescriptionIsNull()

    /**
     * Tests that
     * {@link Shift#Shift(java.lang.String, java.util.List, boolean, boolean, boolean)}
     * throws a {@link NullPointerException} when roles is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenRolesIsNull() {
        String description = "foo";
        List<Role> roles = null;
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;

        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
    }    // constructorThrowsExceptionWhenRolesIsNull()

    /**
     * Tests that
     * {@link Shift#Shift(java.lang.String, java.util.List, boolean, boolean, boolean)}
     * does not throw an exception when no parameter is null nor contains null.
     */
    @Test
    public void constructorDoesNotThrowsExceptionNormally() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;

        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
    }    // constructorDoesNotThrowsExceptionNormally()

    /**
     * Tests that
     * {@link Shift#Shift(java.lang.String, java.util.List, boolean, boolean, boolean)}
     * throws a {@link NullPointerException} when roles contains null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenRolesContainsNull() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), null, new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;

        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
    }    // constructorThrowsExceptionWhenRolesContainsNull()

    /* getDescription */

    /**
     * Tests that {@link Shift#getDescription()} does not throw an exception.
     */
    @Test
    public void getDescriptionDoesNotThrowException() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.getDescription();
    }    // getDescriptionDoesNotThrowException()

    /**
     * Tests that {@link Shift#getDescription()} returns the correct value.
     */
    @Test
    public void getDescriptionReturnsCorrectValue() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        String expected = description;
        String received = shift.getDescription();
        assertEquals(expected, received);
    }    // getDescriptionReturnsCorrectValue()

    /* isOpen */

    /**
     * Tests that {@link Shift#isOpen()} returns true when called before
     * {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void isOpenReturnsTrueWhenCalledBeforeSetVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean received = shift.isOpen();

        boolean expected = true;
        assertEquals(expected, received);
    }    // isOpenReturnsTrueWhenCalledBeforeSetVolunteer()

    /**
     * Tests that {@link Shift#isOpen()} returns false when called after
     * {@link Shift#setVolunteer(Volunteer)} has been called with a volunteer.
     */
    @Test
    public void isOpenReturnsFalseWhenCalledAfterSetVolunteerHasBeenCalledWithVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(getVolunteer());

        boolean received = shift.isOpen();

        boolean expected = false;
        assertEquals(expected, received);
    }    // isOpenReturnsFalseWhenCalledAfterSetVolunteerHasBeenCalledWithVolunteer()

    /**
     * Tests that {@link Shift#ifOpen()} returns true when called after
     * {@link Shift#setVolunteer()} has been called with null.
     */
    @Test
    public void isOpenReturnsFalseWhenCalledAfterSetVolunteerHasBeenCalledWithNull() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(getVolunteer());
        Volunteer volunteer = null;
        shift.setVolunteer(volunteer);

        boolean received = shift.isOpen();

        boolean expected = true;
        assertEquals(expected, received);
    }    // isOpenReturnsFalseWhenCalledAfterSetVolunteerHasBeenCalledWithNull()

    /* getVolunteer / setVolunteer */

    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception when
     * called before {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void getVolunteerDoesNotThrowExceptionWhenCalledBeforeSetVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.getVolunteer();
    }    // getVolunteerDoesNotThrowExceptionWhenCalledBeforeSetVolunteer()

    /**
     * Tests that {@link Shift#getVolunteer()} returns null when called before
     * {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void getVolunteerReturnsNullWhenCalledBeforeSetVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Volunteer received = shift.getVolunteer();

        Volunteer expected = null;
        assertEquals(expected, received);
    }    // getVolunteerReturnsNullWhenCalledBeforeSetVolunteer()

    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception when
     * called after {@link Shift#setVolunteer(Volunteer)} has been called with a
     * volunteer.
     */
    @Test
    public void getVolunteerDoesNotThrowExceptionWhenCalledAfterSetVolunteerHasBeenCalledWithVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(getVolunteer());

        shift.getVolunteer();
    }    // getVolunteerDoesNotThrowExceptionWhenCalledAfterSetVolunteerHasBeenCalledWithVolunteer()

    /**
     * Tests that {@link Shift#getVolunteer()} returns the correct value when
     * called after {@link Shift#setVolunteer(Volunteer)} has been called with a
     * volunteer.
     */
    @Test
    public void getVolunteerReturnsCorrectValueWhenCalledAfterSetVolunteerHasBeenCalledWithVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Volunteer volunteer = getVolunteer();
        shift.setVolunteer(volunteer);

        Volunteer received = shift.getVolunteer();

        Volunteer expected = volunteer;
        assertEquals(expected, received);
    }    // getVolunteerReturnsCorrectValueWhenCalledAfterSetVolunteerHasBeenCalledWithVolunteer()

    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception when
     * called after {@link Shift#setVolunteer()} has been called with null.
     */
    @Test
    public void getVolunteerDoesNotThrowExceptionWhenCalledAfterSetVolunteerHasBeenCalledWithNull() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(getVolunteer());
        Volunteer volunteer = null;
        shift.setVolunteer(volunteer);

        shift.getVolunteer();
    }    // getVolunteerDoesNotThrowExceptionWhenCalledAfterSetVolunteerHasBeenCalledWithNull

    /**
     * Tests that {@link Shift#getVolunteer()} returns the correct value when
     * called after {@link Shift#setVolunteer(Volunteer)} has been called with
     * null.
     */
    @Test
    public void getVolunteerReturnsCorrectValueWhenCalledAfterSetVolunteerHasBeenCalledWithNull() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(getVolunteer());
        Volunteer volunteer = null;
        shift.setVolunteer(volunteer);

        Volunteer received = shift.getVolunteer();

        Volunteer expected = volunteer;
        assertEquals(expected, received);
    }    // getVolunteerReturnsCorrectValueWhenCalledAfterSetVolunteerHasBeenCalledWithNull()

    /**
     * Tests that {@link Shift#setVolunteer(Volunteer)} does not throw an
     * exception when volunteer is null.
     */
    @Test
    public void setVolunteerDoesNotThrowExceptionWhenVolunteerIsNull() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Volunteer volunteer = null;

        shift.setVolunteer(volunteer);
    }    // setVolunteerDoesNotThrowExceptionWhenVolunteerIsNull()

    /* rolesAreCompatible */

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} throws a
     * {@code NullPointerException} when volunteer is null.
     */
    @Test(expected = NullPointerException.class)
    public void rolesAreCompatibleThrowsExceptionWhenVolunteerIsNull() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Volunteer volunteer = null;

        shift.rolesAreCompatible(volunteer);
    }    // rolesAreCompatibleThrowsExceptionWhenVolunteerIsNull()

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} does not
     * throws an exception when volunteer is not null.
     */
    @Test
    public void rolesAreCompatibleDoesNotThrowExceptionWhenVolunteerIsNotNull() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "bar";
        String email = "";
        String phone = "";
        String notes = "";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);

        shift.rolesAreCompatible(volunteer);
    }    // rolesAreCompatibleDoesNotThrowExceptionWhenVolunteerIsNotNull()

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} returns
     * true when the shift has no roles and the volunteer has no roles.
     */
    @Test
    public void rolesAreCompatibleReturnsTrueWhenShiftHasNoRolesAndVolunteerHasNoRoles() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "bar";
        String email = "";
        String phone = "";
        String notes = "";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);

        boolean received = shift.rolesAreCompatible(volunteer);

        boolean expected = true;
        assertEquals(expected, received);
    }    // rolesAreCompatibleDoesNotThrowExceptionWhenVolunteerIsNotNull()

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} returns
     * true when the shift has no roles and the volunteer has roles.
     */
    @Test
    public void rolesAreCompatibleReturnsTrueWhenShiftHasNoRolesAndVolunteerHasRoles() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "bar";
        String email = "";
        String phone = "";
        String notes = "";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList(new Role("role1"), new Role("role2"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);

        boolean received = shift.rolesAreCompatible(volunteer);

        boolean expected = true;
        assertEquals(expected, received);
    }    // rolesAreCompatibleReturnsTrueWhenShiftHasNoRolesAndVolunteerHasRoles()

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} returns
     * false when the shift has roles and the volunteer has no roles.
     */
    @Test
    public void rolesAreCompatibleReturnsFalseWhenShiftHasRolesAndVolunteerHasNoRoles() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("role1"), new Role("role2"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "bar";
        String email = "";
        String phone = "";
        String notes = "";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);

        boolean received = shift.rolesAreCompatible(volunteer);

        boolean expected = false;
        assertEquals(expected, received);
    }    // rolesAreCompatibleReturnsFalseWhenShiftHasRolesAndVolunteerHasNoRoles()

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} returns
     * false when the shift has roles and the volunteer has completely different
     * roles.
     */
    @Test
    public void rolesAreCompatibleReturnsFalseWhenShiftHasRolesAndVolunteerHasCompletelyDifferentRoles() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("role1"), new Role("role2"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "bar";
        String email = "";
        String phone = "";
        String notes = "";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList(new Role("role3"), new Role("role4"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);

        boolean received = shift.rolesAreCompatible(volunteer);

        boolean expected = false;
        assertEquals(expected, received);
    }    // rolesAreCompatibleReturnsFalseWhenShiftHasRolesAndVolunteerHasCompletelyDifferentRoles()

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} returns
     * false when the shift has roles and the volunteer has some but not all of
     * the roles.
     */
    @Test
    public void rolesAreCompatibleReturnsFalseWhenShiftHasRolesAndVolunteerHasSomeButNotAllRoles() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("role1"), new Role("role2"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "bar";
        String email = "";
        String phone = "";
        String notes = "";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList(new Role("role2"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);

        boolean received = shift.rolesAreCompatible(volunteer);

        boolean expected = false;
        assertEquals(expected, received);
    }    // rolesAreCompatibleReturnsFalseWhenShiftHasRolesAndVolunteerHasSomeButNotAllRoles()

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} returns
     * true when the shift has roles and the volunteer has the same roles.
     */
    @Test
    public void rolesAreCompatibleReturnsTrueWhenShiftHasRolesAndVolunteerHasSameRoles() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("role1"), new Role("role2"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "bar";
        String email = "";
        String phone = "";
        String notes = "";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList(new Role("role1"), new Role("role2"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);

        boolean received = shift.rolesAreCompatible(volunteer);

        boolean expected = true;
        assertEquals(expected, received);
    }    // rolesAreCompatibleReturnsTrueWhenShiftHasRolesAndVolunteerHasSameRoles()

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} returns
     * true when the shift has roles and the volunteer has the same roles plus
     * additions.
     */
    @Test
    public void rolesAreCompatibleReturnsTrueWhenShiftHasRolesAndVolunteerHasSamePlusAdditionalRoles() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("role1"), new Role("role2"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "bar";
        String email = "";
        String phone = "";
        String notes = "";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList(new Role("role1"), new Role("role2"), new Role("role3"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);

        boolean received = shift.rolesAreCompatible(volunteer);

        boolean expected = true;
        assertEquals(expected, received);
    }    // rolesAreCompatibleReturnsTrueWhenShiftHasRolesAndVolunteerHasSamePlusAdditionalRoles()

    /**
     * Tests that {@link Shift#rolesAreCompatible(bscmail.Volunteer)} returns
     * true when the shift has roles and the volunteer has the same roles in a
     * different order.
     */
    @Test
    public void rolesAreCompatibleReturnsTrueWhenShiftHasRolesAndVolunteerHasSameRolesInDifferentOrder() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("role1"), new Role("role2"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "bar";
        String email = "";
        String phone = "";
        String notes = "";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList(new Role("role2"), new Role("role1"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);

        boolean received = shift.rolesAreCompatible(volunteer);

        boolean expected = true;
        assertEquals(expected, received);
    }    // rolesAreCompatibleReturnsTrueWhenShiftHasRolesAndVolunteerHasSameRolesInDifferentOrder()

    /* getReadWritableProperties */

    /**
     * Tests that {@link Shift#getReadWritableProperties()} returns the
     * correct value for a shift with a volunteer.
     */
    @Test
    public void getReadWritablePropertiesReturnsCorrectValueForShiftWithVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Volunteer volunteer = getVolunteer();
        shift.setVolunteer(volunteer);

        Map<String, Object> received = shift.getReadWritableProperties();

        Map<String, Object> expected = new HashMap<>();
        expected.put("description", description);
        expected.put("roles", "bar,baz");
        expected.put("displayVolunteerEmail", displayVolunteerEmail);
        expected.put("displayVolunteerPhone", displayVolunteerPhone);
        expected.put("displayVolunteerNotes", displayVolunteerNotes);
        expected.put("volunteer", volunteer);
        assertEquals(expected, received);
    }    // getReadWritablePropertiesReturnsCorrectValueForShiftWithVolunteer()

    /**
     * Tests that {@link Shift#getReadWritableProperties()} returns the
     * correct value for a shift with no volunteer.
     */
    @Test
    public void getReadWritablePropertiesReturnsCorrectValueForShiftWithNoVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Map<String, Object> received = shift.getReadWritableProperties();

        Map<String, Object> expected = new HashMap<>();
        expected.put("description", description);
        expected.put("roles", "bar,baz");
        expected.put("displayVolunteerEmail", displayVolunteerEmail);
        expected.put("displayVolunteerPhone", displayVolunteerPhone);
        expected.put("displayVolunteerNotes", displayVolunteerNotes);
        assertEquals(expected, received);
    }    // getReadWritablePropertiesReturnsCorrectValueForShiftWithNoVolunteer()

    /**
     * Tests that the return value of {@link Shift#getReadWritableProperties()}
     * has the correct iteration order for a shift with a volunteer.
     */
    @Test
    public void getReadWritablePropertiesReturnsMapWithCorrectIterationOrderForShiftWithVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(getVolunteer());

        Map<String, Object> properties = shift.getReadWritableProperties();
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for

        List<String> expected = Arrays.asList("description", "roles", "displayVolunteerEmail", "displayVolunteerPhone", "displayVolunteerNotes", "volunteer");
        assertEquals(expected, received);
    }    // getReadWritablePropertiesReturnsMapWithCorrectIterationOrderForShiftWithVolunteer()

    /**
     * Tests that the return value of {@link Shift#getReadWritableProperties()}
     * has the correct iteration order for a shift with no volunteer.
     */
    @Test
    public void getReadWritablePropertiesReturnsMapWithCorrectIterationOrderForShiftWithNoVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Map<String, Object> properties = shift.getReadWritableProperties();
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for

        List<String> expected = Arrays.asList("description", "roles", "displayVolunteerEmail", "displayVolunteerPhone", "displayVolunteerNotes");
        assertEquals(expected, received);
    }    // getReadWritablePropertiesReturnsMapWithCorrectIterationOrderForShiftWithNoVolunteer()

    /* equals */

    /**
     * Tests that {@link Shift#equals(Object)} does not throw an exception
     * when the argument is null.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNull() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = null;

        shift.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNull()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNull() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = null;

        boolean received = shift.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentIsNull()

    /**
     * Tests that {@link Shift#equals(Object)} does not throw an exception
     * when the argument is not a shift.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNotAShift() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = 1;

        shift.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNotAShift()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument is not a shift.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNotAShift() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = 1;

        boolean received = shift.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentIsNotAShift()

    /**
     * Tests that {@link Shift#equals(Object)} does not throw an exception
     * when the argument is a shift.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsAShift() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsAShift()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument has a different description than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentDescription() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        description = "Bar";
        Object obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean received = shift.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentDescription()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * caller has no volunteer assigned and the argument has a volunteer
     * assigned.
     */
    @Test
    public void equalsReturnsFalseWhenShiftHasNoVolunteerAndArgumentHasVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Shift obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Volunteer volunteer = getVolunteer();
        obj.setVolunteer(volunteer);

        boolean received = shift.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenShiftHasNoVolunteerAndArgumentHasVolunteer()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * caller has a volunteer assigned and the argument has no volunteer
     * assigned.
     */
    @Test
    public void equalsReturnsFalseWhenShiftHasVolunteerAndArgumentHasNoVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Volunteer volunteer = getVolunteer();
        shift.setVolunteer(volunteer);
        Shift obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean received = shift.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenShiftHasVolunteerAndArgumentHasNoVolunteer()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * caller has a different volunteer assigned to it than the argument.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentVolunteer() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        List<Role> volunteerRoles = Arrays.asList();
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        shift.setVolunteer(volunteer);
        Shift obj = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        name += "X";
        volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        obj.setVolunteer(volunteer);

        boolean received = shift.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentVolunteer()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument has a different display volunteer email flag than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentDisplayVolunteerEmail() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        displayVolunteerEmail = false;
        Object obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean received = shift.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentDisplayVolunteerEmail()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument has a different display volunteer phone flag than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentDisplayVolunteerPhone() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        displayVolunteerPhone = false;
        Object obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean received = shift.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentDisplayVolunteerPhone()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument has a different display volunteer notes flag than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentDisplayVolunteerNotes() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        displayVolunteerNotes = false;
        Object obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean received = shift.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentDisplayVolunteerNotes()

    /**
     * Tests that {@link Shift#equals(Object)} returns true when neither the
     * caller nor the argument have volunteers assigned to them.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsEqualAndNeitherHasAVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean received = shift.equals(obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsEqualAndNeitherHasAVolunteer()

    /**
     * Tests that {@link Shift#equals(Object)} returns true when the
     * caller has the same volunteer assigned to it as the argument.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsEqualAndVolunteerIsEqual() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        shift.setVolunteer(volunteer);
        Shift obj = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        obj.setVolunteer(volunteer);

        boolean received = shift.equals(obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsEqualAndVolunteerIsEqual()

    /**
     * Tests that {@link Shift#equals(Object)} returns true when the
     * argument is the same object as the caller.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsIdentical() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = shift;

        boolean received = shift.equals(obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsIdentical()

    /* hashCode */

    /**
     * Tests that {@link Shift#hashCode()} does not throw an exception.
     */
    @Test
    public void hashCodeDoesNotThrowException() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.hashCode();
    }    // hashCodeDoesNotThrowException()

    /**
     * Tests that {@link Shift#hashCode()} returns equal values for equal
     * shifts without volunteers.
     */
    @Test
    public void hashCodeReturnsEqualValuesForEqualShiftsWithoutVolunteers() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Shift experimental  = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        int first = shift.hashCode();
        int second = experimental.hashCode();

        assertEquals(first, second);
    }    // hashCodeReturnsEqualValuesForEqualShiftsWithoutVolunteers()

    /**
     * Tests that {@link Shift#hashCode()} returns equal values for equal
     * shifts with volunteers.
     */
    @Test
    public void hashCodeReturnsEqualValuesForEqualShiftsWithVolunteers() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        shift.setVolunteer(volunteer);
        Shift experimental = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        experimental.setVolunteer(volunteer);

        int first = shift.hashCode();
        int second = experimental.hashCode();

        assertEquals(first, second);
    }    // hashCodeReturnsEqualValuesForEqualShiftsWithVolunteers()

    /* clone */

    /**
     * Tests that {@link Shift#clone()} does not throw an exception when the
     * shift does not have a volunteer.
     */
    @Test
    public void cloneDoesNotThrowExceptionWhenShiftHasNoVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.clone();
    }    // cloneDoesNotThrowExceptionWhenShiftHasNoVolunteer()

    /**
     * Tests that {@link Shift#clone()} does not throw an exception when the
     * shift has a volunteer.
     */
    @Test
    public void cloneDoesNotThrowExceptionWhenShiftHasVolunteer() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        shift.setVolunteer(volunteer);

        shift.clone();
    }    // cloneDoesNotThrowExceptionWhenShiftHasVolunteer()

    /**
     * Tests that the return value of {@link Shift#clone()} is not null when the
     * shift does not have a volunteer.
     */
    @Test
    public void cloneDoesNotReturnNullWhenShiftHasNoVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Shift received = shift.clone();

        assertNotNull(received);
    }    // cloneDoesNotReturnNullWhenShiftHasNoVolunteer()

    /**
     * Tests that the return value of {@link Shift#clone()} is not null when the
     * shift has a volunteer.
     */
    @Test
    public void cloneDoesNotReturnNullWhenShiftHasVolunteer() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        shift.setVolunteer(volunteer);

        Shift received = shift.clone();

        assertNotNull(received);
    }    // cloneDoesNotReturnNullWhenShiftHasVolunteer()

    /**
     * Tests that the return value of {@link Shift#clone()} is equal to the
     * argument when the shift does not have a volunteer.
     */
    @Test
    public void cloneReturnsValueEqualToArgumentWhenShiftHasNoVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Shift received = shift.clone();

        Shift expected = shift;
        assertEquals(expected, received);
    }    // cloneReturnsValueEqualToArgumentWhenShiftHasNoVolunteer()

    /**
     * Tests that the return value of {@link Shift#clone()} is equal to the
     * argument when the shift has a volunteer.
     */
    @Test
    public void cloneReturnsValueEqualToArgumentWhenShiftHasVolunteer() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        shift.setVolunteer(volunteer);

        Shift received = shift.clone();

        Shift expected = shift;
        assertEquals(expected, received);
    }    // cloneReturnsValueEqualToArgumentWhenShiftHasVolunteer()

    /**
     * Tests that the return value of {@link Shift#clone()} is not identical to
     * the argument when the shift does not have a volunteer.
     */
    @Test
    public void cloneReturnsValueNotIdenticalToArgumentWhenShiftHasNoVolunteer() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Shift received = shift.clone();

        assertFalse(shift == received);
    }    // cloneReturnsValueNotIdenticalToArgumentWhenShiftHasNoVolunteer()

    /**
     * Tests that the return value of {@link Shift#clone()} is not identical to
     * the argument when the shift has a volunteer.
     */
    @Test
    public void cloneReturnsValueNotIdenticalToArgumentWhenShiftHasVolunteer() {
        String description = "foo";
        List<Role> shiftRoles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, shiftRoles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        boolean active = true;
        List<Role> volunteerRoles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, volunteerRoles);
        shift.setVolunteer(volunteer);

        Shift received = shift.clone();

        assertFalse(shift == received);
    }    // cloneReturnsValueNotIdenticalToArgumentWhenShiftHasVolunteer()

    /* toString */

    /**
     * Tests that {@link Shift#toString()} does not throw an exception.
     */
    @Test
    public void toStringDoesNotThrowException() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.toString();
    }    // toStringDoesNotThrowException()

    /**
     * Tests that the return value of {@link Shift#toString()} is not null.
     */
    @Test
    public void toStringDoesNotReturnNull() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        String received = shift.toString();

        assertNotNull(received);
    }    // toStringDoesNotReturnNull()

    /**
     * Tests that {@link Shift#toString()} returns the shift's description.
     */
    @Test
    public void toStringReturnsCorrectValue() {
        String description = "foo";
        List<Role> roles = Arrays.asList(new Role("bar"), new Role("baz"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        String received = shift.toString();

        String expected = description;
        assertEquals(expected, received);
    }    // toStringReturnsCorrectValue()

    /* getShiftFactory */

    /**
     * Tests that {@link Shift#getShiftFactory()} does not throw an exception.
     */
    @Test
    public void getShiftFactoryDoesNotThrowException() {
        Shift.getShiftFactory();
    }    // getShiftFactoryDoesNotThrowException()

    /**
     * Tests that the return value of {@link Shift#getShiftFactory()} is not
     * null.
     */
    @Test
    public void getShiftFactoryDoesNotReturnNull() {
        ReadWritableFactory factory = Shift.getShiftFactory();

        assertNotNull(factory);
    }    // getShiftFactoryDoesNotReturnNull()

}    // ShiftTest
