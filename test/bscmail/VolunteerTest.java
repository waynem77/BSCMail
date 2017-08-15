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

import java.util.*;
import main.ReadWritableFactory;
import main.ReadWritableTest;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Volunteer}
 *
 * @author Wayne Miller
 */
public class VolunteerTest extends ReadWritableTest {

    /*
     * Class methods
     */

    /**
     * Returns the volunteer to be tested.
     *
     * @return the volunteer to be tested
     */
    @Override
    protected Volunteer getReadWritable() {
        return new Volunteer("foo", "bar", "baz", "smurf", true);
    }    // getReadWritable()

    /**
     * Returns a comparator for roles.
     *
     * @return a comparator for roles
     */
    protected Comparator<Role> getRoleComparator() {
        return new Comparator<Role>(){
            @Override public int compare(Role role1, Role role2) { return role1.getName().compareTo(role2.getName()); }
        };    // comparator
    }    // getRoleComparator()


    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * does not throw an exception when name is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNameIsEmpty() {
        String name = "";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorDoesNotThrowExceptionWhenNameIsEmpty()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * throws a {@link NullPointerException} when name is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenNameIsNull() {
        String name = null;
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorThrowsExceptionWhenNameIsNull()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * does not throw an exception when email is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenEmailIsEmpty() {
        String name = "Foo Bar";
        String email = "";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorDoesNotThrowExceptionWhenEmailIsEmpty()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * throws a {@link NullPointerException} when email is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenEmailIsNull() {
        String name = "Foo Bar";
        String email = null;
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorThrowsExceptionWhenEmailIsNull()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * does not throw an exception when phone is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenPhoneIsEmpty() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorDoesNotThrowExceptionWhenPhoneIsEmpty()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * throws a {@link NullPointerException} when phone is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenPhoneIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = null;
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorThrowsExceptionWhenPhoneIsNull()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * does not throw an exception when notes is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNotesIsEmpty() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorDoesNotThrowExceptionWhenNotesIsEmpty()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * throws a {@link NullPointerException} when notes is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenNotesIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = null;
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorThrowsExceptionWhenNotesIsNull()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * does not throw an exception when no active is true.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenActiveIsTrue() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorDoesNotThrowExceptionWhenActiveIsTrue()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * does not throw an exception when no active is false.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenActiveIsFalse() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = false;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorDoesNotThrowExceptionWhenActiveIsFalse()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * does not throw an exception when roles is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenRolesIsEmpty() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList();

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorDoesNotThrowExceptionWhenRolesIsEmpty()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * throws an exception when roles is null;
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenRolesIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = null;

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorThrowsExceptionWhenRolesIsNull()

    /**
     * Tests that
     * {@link Volunteer#Volunteer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List)}
     * throws an exception when roles contains is null;
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenRolesContainsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), null, new Role("bar"));

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
    }    // constructorThrowsExceptionWhenRolesContainsNull()

    /* getName */

    /**
     * Tests that {@link Volunteer#getName()} does not throw an exception.
     */
    @Test
    public void getNameDoesNotThrowException() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        volunteer.getName();
    }    // getNameDoesNotThrowException()

    /**
     * Tests that {@link Volunteer#getName()} returns the correct value.
     */
    @Test
    public void getNameReturnsTheCorrectValue() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        String received = volunteer.getName();

        String expected = name;
        assertEquals(expected, received);
    }    // getNameReturnsTheCorrectValue()


    /* setName */

    /**
     * Tests that {@link Volunteer#setName(java.lang.String)} does not throw an
     * exception when name is not null.
     */
    @Test
    public void setNameDoesNotThrowExceptionWhenNameIsNotNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        name = "Smurf";

        volunteer.setName(name);
    }    // setNameDoesNotThrowExceptionWhenNameIsNotNull()

    /**
     * Tests that {@link Volunteer#setName(java.lang.String)} does not throw an
     * exception when name is empty.
     */
    @Test
    public void setNameDoesNotThrowExceptionWhenNameIsEmpty() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        name = "";

        volunteer.setName(name);
    }    // setNameDoesNotThrowExceptionWhenNameIsEmpty()

    /**
     * Tests that {@link Volunteer#setName(java.lang.String)} does not throw an
     * exception when name is identical to the existing name.
     */
    @Test
    public void setNameDoesNotThrowExceptionWhenNameIsIdentical() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        volunteer.setName(name);
    }    // setNameDoesNotThrowExceptionWhenNameIsIdentical()

    /**
     * Tests that {@link Volunteer#setName(java.lang.String)} throws a
     * {@link NullPointerException} when name is null.
     */
    @Test(expected = NullPointerException.class)
    public void setNameThrowsExceptionWhenNameIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        name = null;

        volunteer.setName(name);
    }    // setNameThrowsExceptionWhenNameIsNull()

    /**
     * Tests that {@link Volunteer#setName(java.lang.String)} correctly sets the
     * name.
     */
    @Test
    public void setNameActuallySetsName() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        name = "Smurf";

        volunteer.setName(name);
        String received = volunteer.getName();

        String expected = name;
        assertEquals(expected, received);
    }    // setNameActuallySetsName()


    /* getEmail */

    /**
     * Tests that {@link Volunteer#getEmail()} does not throw an exception.
     */
    @Test
    public void getEmailDoesNotThrowException() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        volunteer.getEmail();
    }    // getEmailDoesNotThrowException()

    /**
     * Tests that {@link Volunteer#getEmail()} returns the correct value.
     */
    @Test
    public void getEmailReturnsTheCorrectValue() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        String received = volunteer.getEmail();

        String expected = email;
        assertEquals(expected, received);
    }    // getEmailReturnsTheCorrectValue()


    /* setEmail */

    /**
     * Tests that {@link Volunteer#setEmail(java.lang.String)} does not throw an
     * exception when email is not null.
     */
    @Test
    public void setEmailDoesNotThrowExceptionWhenEmailIsNotNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        email = "Smurf";

        volunteer.setEmail(email);
    }    // setEmailDoesNotThrowExceptionWhenEmailIsNotNull()

    /**
     * Tests that {@link Volunteer#setEmail(java.lang.String)} does not throw an
     * exception when email is empty.
     */
    @Test
    public void setEmailDoesNotThrowExceptionWhenEmailIsEmpty() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        email = "";

        volunteer.setEmail(email);
    }    // setEmailDoesNotThrowExceptionWhenEmailIsEmpty()

    /**
     * Tests that {@link Volunteer#setEmail(java.lang.String)} does not throw an
     * exception when email is identical to the existing email.
     */
    @Test
    public void setEmailDoesNotThrowExceptionWhenEmailIsIdentical() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        volunteer.setEmail(email);
    }    // setEmailDoesNotThrowExceptionWhenEmailIsIdentical()

    /**
     * Tests that {@link Volunteer#setEmail(java.lang.String)} throws a
     * {@link NullPointerException} when email is null.
     */
    @Test(expected = NullPointerException.class)
    public void setEmailThrowsExceptionWhenEmailIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        email = null;

        volunteer.setEmail(email);
    }    // setEmailThrowsExceptionWhenEmailIsNull()

    /**
     * Tests that {@link Volunteer#setEmail(java.lang.String)} correctly sets
     * the email.
     */
    @Test
    public void setEmailActuallySetsEmail() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        email = "Smurf";

        volunteer.setEmail(email);
        String received = volunteer.getEmail();

        String expected = email;
        assertEquals(expected, received);
    }    // setEmailActuallySetsEmail()


    /* getPhone */

    /**
     * Tests that {@link Volunteer#getPhone()} does not throw an exception.
     */
    @Test
    public void getPhoneDoesNotThrowException() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        volunteer.getPhone();
    }    // getPhoneDoesNotThrowException()

    /**
     * Tests that {@link Volunteer#getPhone()} returns the correct value.
     */
    @Test
    public void getPhoneReturnsTheCorrectValue() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        String received = volunteer.getPhone();

        String expected = phone;
        assertEquals(expected, received);
    }    // getPhoneReturnsTheCorrectValue()


    /* setPhone */

    /**
     * Tests that {@link Volunteer#setPhone(java.lang.String)} does not throw an
     * exception when phone is not null.
     */
    @Test
    public void setPhoneDoesNotThrowExceptionWhenPhoneIsNotNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        phone = "Smurf";

        volunteer.setPhone(phone);
    }    // setPhoneDoesNotThrowExceptionWhenPhoneIsNotNull()

    /**
     * Tests that {@link Volunteer#setPhone(java.lang.String)} does not throw an
     * exception when phone is empty.
     */
    @Test
    public void setPhoneDoesNotThrowExceptionWhenPhoneIsEmpty() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        phone = "";

        volunteer.setPhone(phone);
    }    // setPhoneDoesNotThrowExceptionWhenPhoneIsEmpty()

    /**
     * Tests that {@link Volunteer#setPhone(java.lang.String)} does not throw an
     * exception when phone is identical to the existing phone.
     */
    @Test
    public void setPhoneDoesNotThrowExceptionWhenPhoneIsIdentical() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        volunteer.setPhone(phone);
    }    // setPhoneDoesNotThrowExceptionWhenPhoneIsIdentical()

    /**
     * Tests that {@link Volunteer#setPhone(java.lang.String)} throws a
     * {@link NullPointerException} when phone is null.
     */
    @Test(expected = NullPointerException.class)
    public void setPhoneThrowsExceptionWhenPhoneIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        phone = null;

        volunteer.setPhone(phone);
    }    // setPhoneThrowsExceptionWhenPhoneIsNull()

    /**
     * Tests that {@link Volunteer#setPhone(java.lang.String)} correctly sets
     * the phone.
     */
    @Test
    public void setPhoneActuallySetsPhone() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        phone = "Smurf";

        volunteer.setPhone(phone);
        String received = volunteer.getPhone();

        String expected = phone;
        assertEquals(expected, received);
    }    // setPhoneActuallySetsPhone()


    /* getNotes */

    /**
     * Tests that {@link Volunteer#getNotes()} does not throw an exception.
     */
    @Test
    public void getNotesDoesNotThrowException() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        volunteer.getNotes();
    }    // getNotesDoesNotThrowException()

    /**
     * Tests that {@link Volunteer#getNotes()} returns the correct value.
     */
    @Test
    public void getNotesReturnsTheCorrectValue() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        String received = volunteer.getNotes();

        String expected = notes;
        assertEquals(expected, received);
    }    // getNotesReturnsTheCorrectValue()


    /* setNotes */

    /**
     * Tests that {@link Volunteer#setNotes(java.lang.String)} does not throw an
     * exception when notes is not null.
     */
    @Test
    public void setNotesDoesNotThrowExceptionWhenNotesIsNotNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        notes = "Smurf";

        volunteer.setNotes(notes);
    }    // setNotesDoesNotThrowExceptionWhenNotesIsNotNull()

    /**
     * Tests that {@link Volunteer#setNotes(java.lang.String)} does not throw an
     * exception when notes is empty.
     */
    @Test
    public void setNotesDoesNotThrowExceptionWhenNotesIsEmpty() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        notes = "";

        volunteer.setNotes(notes);
    }    // setNotesDoesNotThrowExceptionWhenNotesIsEmpty()

    /**
     * Tests that {@link Volunteer#setNotes(java.lang.String)} does not throw an
     * exception when notes is identical to the existing notes.
     */
    @Test
    public void setNotesDoesNotThrowExceptionWhenNotesIsIdentical() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        volunteer.setNotes(notes);
    }    // setNotesDoesNotThrowExceptionWhenNotesIsIdentical()

    /**
     * Tests that {@link Volunteer#setNotes(java.lang.String)} throws a
     * {@link NullPointerException} when notes is null.
     */
    @Test(expected = NullPointerException.class)
    public void setNotesThrowsExceptionWhenNotesIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        notes = null;

        volunteer.setNotes(notes);
    }    // setNotesThrowsExceptionWhenNotesIsNull()

    /**
     * Tests that {@link Volunteer#setNotes(java.lang.String)} correctly sets
     * the notes.
     */
    @Test
    public void setNotesActuallySetsNotes() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        notes = "Smurf";

        volunteer.setNotes(notes);
        String received = volunteer.getNotes();

        String expected = notes;
        assertEquals(expected, received);
    }    // setNotesActuallySetsNotes()


    /* getRoles */

    /**
     * Tests that {@link Volunteer#getRoles()} does not throw an exception.
     */
    @Test
    public void getRolesDoesNotThrowAnException() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);

        volunteer.getRoles();
    }    // getRolesDoesNotThrowAnException()

    /**
     * Tests that {@link Volunteer#getRoles()} does not return null.
     */
    @Test
    public void getRolesDoesNotReturnNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);

        List<Role> received = volunteer.getRoles();

        assertNotNull(received);
    }    // getRolesDoesNotReturnNull()

    /**
     * Tests that {@link Volunteer#getRoles()} returns the correct value.
     */
    @Test
    public void getRolesReturnsCorrectValue() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);

        List<Role> received = volunteer.getRoles();

        List<Role> expected = roles;
        assertEquals(expected, received);
    }    // getRolesReturnsCorrectValue()

    /**
     * Tests that the list returned by {@link Volunteer#getRoles()} is
     * independent from the volunteer; that is, edits to the return value are
     * not reflected within the volunteer.
     */
    @Test
    public void getRolesReturnsListIndependentOfVolunteer() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);

        List<Role> receivedFirst = volunteer.getRoles();
        receivedFirst.add(new Role("baz"));
        List<Role> receivedSecond = volunteer.getRoles();

        List<Role> expected = roles;
        assertEquals(expected, receivedSecond);
    }    // getRolesDoesNotReturnIdenticalListToThatPassedToSetList()


    /* setRoles */

    /**
     * Tests that {@link Volunteer#setRoles(java.util.List)} throws an exception
     * when roles is null.
     */
    @Test(expected = NullPointerException.class)
    public void setRolesThrowsExceptionWhenRolesIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
        roles = null;

        volunteer.setRoles(roles);
    }    // setRolesThrowsExceptionWhenRolesIsNull()

    /**
     * Tests that {@link Volunteer#setRoles(java.util.List)} throws an exception
     * when roles contains null.
     */
    @Test(expected = NullPointerException.class)
    public void setRolesThrowsExceptionWhenRolesContainsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
        roles = Arrays.asList(new Role("foo"), null, new Role("bar"));

        volunteer.setRoles(roles);
    }    // setRolesThrowsExceptionWhenRolesContainsNull()

    /**
     * Tests that {@link Volunteer#setRoles(java.util.List)} does not throw an
     * exception when roles is not null and does not contain null.
     */
    @Test
    public void setRolesDoesNotThrowExceptionWhenRolesIsNotNullAndDoesNotContainNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList();
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
        roles = Arrays.asList(new Role("foo"), new Role("bar"));

        volunteer.setRoles(roles);
    }    // setRolesDoesNotThrowExceptionWhenRolesIsNotNullAndDoesNotContainNull()

    /**
     * Tests that {@link Volunteer#setRoles(java.util.List)} correctly sets the
     * roles.
     */
    @Test
    public void setRolesSetsRoles() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        List<Role> roles = Arrays.asList(new Role("baz"), new Role("smurf"));
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
        roles = Arrays.asList(new Role("foo"), new Role("bar"));

        volunteer.setRoles(roles);

        List<Role> expected = roles;
        List<Role> received = volunteer.getRoles();
        assertEquals(expected, received);
    }    // setRolesSetsRoles()

    /* addRole*/

    /**
     * Tests that {@link Volunteer#addRole(bscmail.Role)} does not throw an
     * exception when role is not null.
     */
    @Test
    public void addRoleDoesNotThrowAnExceptionWhenRoleIsNotNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        Role role = new Role("smurf");
        volunteer.addRole(role);
    }    // addRoleDoesNotThrowAnExceptionWhenRoleIsNotNull()

    /**
     * Tests that {@link Volunteer#addRole(bscmail.Role)} does not throw an
     * exception when role is identical to an already-added role.
     */
    @Test
    public void addRoleDoesNotThrowAnExceptionWhenRoleHasBeenAdded() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        Role role = new Role("smurf");
        volunteer.addRole(role);

        volunteer.addRole(role);
    }    // addRoleDoesNotThrowAnExceptionWhenRoleHasBeenAdded()

    /**
     * Tests that {@link Volunteer#addRole(bscmail.Role)} does not throw an
     * exception when role is equal to an already-added role.
     */
    @Test
    public void addRoleDoesNotThrowAnExceptionWhenRoleIsEqualToAnAlreadyAddedRole() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        String roleName = "smurf";
        Role role = new Role(roleName);
        volunteer.addRole(role);

        role = new Role(roleName);
        volunteer.addRole(role);
    }    // addRoleDoesNotThrowAnExceptionWhenRoleIsEqualToAnAlreadyAddedRole()

    /**
     * Tests that {@link Volunteer#addRole(bscmail.Role)} throws a {@link NullPointerException}
     * when role is null.
     */
    @Test(expected = NullPointerException.class)
    public void addRoleThrowsAnExceptionWhenRoleIsull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        Role role = null;
        volunteer.addRole(role);
    }    // addRoleThrowsAnExceptionWhenRoleIsull()

    /**
     * Tests that {@link Volunteer#addRole(bscmail.Role)} correctly adds the
     * role when the volunteer has no existing roles.
     */
    @Test
    public void addRoleAddsTheRoleWhenRolesIsEmpty() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        String roleName = "smurf";
        Role role = new Role(roleName);
        volunteer.addRole(role);
        List<Role> received = volunteer.getRoles();

        List<Role> expected = Arrays.asList(new Role(roleName));
        assertEquals(expected, received);
    }    // addRoleAddsTheRoleWhenRolesIsEmpty()

    /**
     * Tests that {@link Volunteer#addRole(bscmail.Role)} correctly adds the
     * role when the volunteer does not already have the role.
     */
    @Test
    public void addRoleAddsTheRole() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        List<Role> roles = Arrays.asList(new Role("first"), new Role("second"), new Role("third"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        List<Role> expected = roles;
        Collections.sort(roles, getRoleComparator());
        List<Role> received = volunteer.getRoles();
        Collections.sort(received, getRoleComparator());
        assertEquals(expected, received);
    }    // addRoleAddsTheRole()

    /**
     * Tests that {@link Volunteer#addRole(bscmail.Role)} does not add the role
     * when it has already been added.
     */
    @Test
    public void addRoleDoesNotAddTheRoleWhenItHasAlreadyBeenAdded() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        String roleName = "smurf";
        Role role = new Role(roleName);
        volunteer.addRole(role);
        volunteer.addRole(role);
        List<Role> received = volunteer.getRoles();

        List<Role> expected = Arrays.asList(new Role(roleName));
        assertEquals(expected, received);
    }    // addRoleDoesNotAddTheRoleWhenItHasAlreadyBeenAdded()

    /**
     * Tests that {@link Volunteer#addRole(bscmail.Role)} does not add the role
     * when it is equal to a role that has already been added.
     */
    @Test
    public void addRoleDoesNotAddTheRoleWhenNotUnique() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        String roleName = "smurf";
        Role role = new Role(roleName);
        volunteer.addRole(role);
        role = new Role(roleName);
        volunteer.addRole(role);
        List<Role> received = volunteer.getRoles();

        List<Role> expected = Arrays.asList(new Role(roleName));
        assertEquals(expected, received);
    }    // addRoleDoesNotAddTheRoleWhenNotUnique()


    /* removeRole*/

    /**
     * Tests that {@link Volunteer#removeRole(bscmail.Role)} does not throw an
     * exception when role is not null.
     */
    @Test
    public void removeRoleDoesNotThrowAnExceptionWhenRoleIsNotNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        Role role = new Role("smurf");
        volunteer.addRole(role);

        volunteer.removeRole(role);
    }    // addRoleDoesNotThrowAnExceptionWhenRoleIsNotNull()

    /**
     * Tests that {@link Volunteer#removeRole(bscmail.Role)} does not throw an
     * exception when the volunteer has added no roles.
     */
    @Test
    public void removeRoleDoesNotThrowAnExceptionWhenVolunteerHasNoRoles() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        Role role = new Role("smurf");

        volunteer.removeRole(role);
    }    // removeRoleDoesNotThrowAnExceptionWhenVolunteerHasNoRoles()

    /**
     * Tests that {@link Volunteer#removeRole(bscmail.Role)} does not throw an
     * exception when the role has not been added.
     */
    @Test
    public void removeRoleDoesNotThrowAnExceptionRoleHasNotBeenAdded() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        String roleName = "smurf";
        Role role = new Role(roleName);
        volunteer.addRole(role);

        roleName += "X";
        role = new Role(roleName);
        volunteer.removeRole(role);
    }    // removeRoleDoesNotThrowAnExceptionWhenVolunteerHasNoRoles()

    /**
     * Tests that {@link Volunteer#removeRole(bscmail.Role)} throws a
     * {@link NullPointerException} when role is null.
     */
    @Test(expected = NullPointerException.class)
    public void removeRoleThrowsAnExceptionWhenRoleIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        Role role = new Role("smurf");
        volunteer.addRole(role);

        role = null;
        volunteer.removeRole(role);
    }    // removeRoleThrowsAnExceptionWhenRoleIsNull()

    /**
     * Tests that {@link Volunteer#removeRole(bscmail.Role)} removes the role.
     */
    @Test
    public void removeRoleRemovesTheRole() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Role role = new Role("B");
        volunteer.removeRole(role);
        List<Role> received = volunteer.getRoles();

        List<Role> expected = Arrays.asList(new Role("A"), new Role("C"));
        Collections.sort(expected, getRoleComparator());
        Collections.sort(received, getRoleComparator());
        assertEquals(expected, received);
    }    // removeRoleRemovesTheRole()

    /**
     * Tests that {@link Volunteer#removeRole(bscmail.Role)} does not alter the
     * roles when role has not been added.
     */
    @Test
    public void removeRoleDoesNotRemoveTheRoleWhenItHasNotBeenAdded() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Role role = new Role("D");
        volunteer.removeRole(role);
        List<Role> received = volunteer.getRoles();

        List<Role> expected = roles;
        Collections.sort(expected, getRoleComparator());
        Collections.sort(received, getRoleComparator());
        assertEquals(expected, received);
    }    // removeRoleDoesNotRemoveTheRoleWhenItHasNotBeenAdded()


    /* isActive */

    /**
     * Tests that {@link Volunteer#isActive()} does not throw an exception.
     */
    @Test
    public void isActiveDoesNotThrowException() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        volunteer.isActive();
    }    // isActiveDoesNotThrowException()

    /**
     * Tests that {@link Volunteer#isActive()} returns the correct value.
     */
    @Test
    public void isActiveReturnsTheCorrectValue() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);

        boolean received = volunteer.isActive();

        boolean expected = active;
        assertEquals(expected, received);
    }    // isActiveReturnsTheCorrectValue()


    /* setActive */

    /**
     * Tests that {@link Volunteer#setActive(boolean)} does not throw an
     * exception when active is true.
     */
    @Test
    public void setActiveDoesNotThrowExceptionWhenActiveIsTrue() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = false;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        active = true;

        volunteer.setActive(active);
    }    // setActiveDoesNotThrowExceptionWhenActiveIsTrue()

    /**
     * Tests that {@link Volunteer#setActive(boolean)} does not throw an
     * exception when active is false.
     */
    @Test
    public void setActiveDoesNotThrowExceptionWhenActiveIsEmpty() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        active = false;

        volunteer.setActive(active);
    }    // setActiveDoesNotThrowExceptionWhenActiveIsEmpty()

    /**
     * Tests that {@link Volunteer#setActive(boolean)} correctly sets
     * the active state.
     */
    @Test
    public void setActiveActuallySetsActiveState() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        active = false;

        volunteer.setActive(active);
        boolean received = volunteer.isActive();

        boolean expected = active;
        assertEquals(expected, received);
    }    // setActiveActuallySetsActiveState()


    /* getReadWritableProperties */

    /**
     * Tests that {@link Volunteer#getReadWritableProperties()} returns the
     * correct value.
     */
    @Test
    public void getReadWritablePropertiesReturnsTheCorrectValue() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Map<String, Object> received = volunteer.getReadWritableProperties();

        String receivedRoleNameString = (String)received.remove("roles");
        String[] receivedRoleNames = receivedRoleNameString.split(",");
        Arrays.sort(receivedRoleNames);
        String[] expectedRoleNames = new String[roles.size()];
        for (int i = 0; i < roles.size(); ++i) {
            expectedRoleNames[i] = roles.get(i).getName();
        }    // for
        Arrays.sort(expectedRoleNames);
        assertArrayEquals(expectedRoleNames, receivedRoleNames);
        Map<String, Object> expected = new HashMap<>();
        expected.put("name", name);
        expected.put("email", email);
        expected.put("phone", phone);
        expected.put("notes", notes);
        expected.put("active", active);
        assertEquals(expected, received);
    }    // Test()

    /**
     * Tests that the return value of {@link Volunteer#getReadWritableProperties()}
     * has the correct iteration order.
     */
    @Test
    public void getReadWritablePropertiesHasTheCorrectIterationOrder() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Map<String, Object> properties = volunteer.getReadWritableProperties();
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for

        List<String> expected = Arrays.asList("name", "email", "phone", "notes", "active", "roles");
        assertEquals(expected, received);
    }    // getReadWritablePropertiesHasTheCorrectIterationOrder()


    /* equals */

    /**
     * Tests that {@link Volunteer#equals(Object)} does not throw an exception
     * when the argument is null.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Object obj = null;
        volunteer.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNull()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Object obj = null;
        boolean received = volunteer.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNull()

    /**
     * Tests that {@link Volunteer#equals(Object)} does not throw an exception
     * when the argument is not a volunteer.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNotVolunteer() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Object obj = 1;
        volunteer.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNotVolunteer()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument is not a volunteer.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNotVolunteer() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Object obj = 1;
        boolean received = volunteer.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNotVolunteer()

    /**
     * Tests that {@link Volunteer#equals(Object)} does not throw an exception
     * when the argument is a volunteer.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsVolunteer() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Object obj = new Volunteer(name, email, phone, notes, active);
        volunteer.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsVolunteer()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument has a different name than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentName() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer obj = new Volunteer(name + "X", email, phone, notes, active);
        for (Role role : roles) {
            obj.addRole(role);
        }    // for
        boolean received = volunteer.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentName()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument has a different email address than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentEmail() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer obj = new Volunteer(name, email + "X", phone, notes, active);
        for (Role role : roles) {
            obj.addRole(role);
        }    // for
        boolean received = volunteer.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentEmail()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument has a different phone number than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentPhone() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer obj = new Volunteer(name, email, phone + "X", notes, active);
        for (Role role : roles) {
            obj.addRole(role);
        }    // for
        boolean received = volunteer.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentPhone()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument has different notes than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentNotes() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer obj = new Volunteer(name, email, phone, notes + "X", active);
        for (Role role : roles) {
            obj.addRole(role);
        }    // for
        boolean received = volunteer.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentNotes()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns false when the
     * argument has a different active status than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentActiveState() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer obj = new Volunteer(name, email, phone, notes, !active);
        for (Role role : roles) {
            obj.addRole(role);
        }    // for
        boolean received = volunteer.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentActiveState()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns true when the
     * argument has different roles than the caller.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentHasDifferentRoles() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer obj = new Volunteer(name, email, phone, notes, active);
        for (int i = 1; i < roles.size(); ++i) {    // Deliberately skipping index 0
            obj.addRole(roles.get(i));
        }    // for
        boolean received = volunteer.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentHasDifferentRoles()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns true when the
     * argument has identical properties and roles.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsEquivalent() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer obj = new Volunteer(name, email, phone, notes, active);
        for (Role role : roles) {
            obj.addRole(role);
        }    // for
        boolean received = volunteer.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsEquivalent()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns true when the
     * argument has identical properties and roles, but the roles were added in
     * a different order.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsEquivalentButRolesAreOrderedDifferently() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer obj = new Volunteer(name, email, phone, notes, active);
        Collections.reverse(roles);
        for (Role role : roles) {
            obj.addRole(role);
        }    // for
        boolean received = volunteer.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsEquivalentButRolesAreOrderedDifferently()

    /**
     * Tests that {@link Volunteer#equals(Object)} returns true when the
     * argument is the same object as the caller.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsIdentical() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer obj = volunteer;
        boolean received = volunteer.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsIdentical()


    /* hashCode */

    /**
     * Tests that {@link Volunteer#hashCode()} does not throw an exception.
     */
    @Test
    public void hashCodeDoesNotThrowException() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        volunteer.hashCode();
    }    // hashCodeDoesNotThrowException()

    /**
     * Tests that {@link Volunteer#hashCode()} returns equal values for equal
     * volunteers.
     */
    @Test
    public void hashCodeReturnsEqualValuesForEquivalentVolunteers() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer1 = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer1.addRole(role);
        }    // for
        Volunteer volunteer2 = new Volunteer(name, email, phone, notes, active);
        for (Role role : roles) {
            volunteer2.addRole(role);
        }    // for

        int expected = volunteer1.hashCode();
        int received = volunteer2.hashCode();
        assertEquals(expected, received);
    }    // hashCodeReturnsEqualValuesForEquivalentVolunteers()


    /* clone */

    /**
     * Tests that {@link Volunteer#clone()} does not throw an exception.
     */
    @Test
    public void cloneDoesNotThrowException() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        volunteer.clone();
    }    // cloneDoesNotThrowException()

    /**
     * Tests that the return value of {@link Volunteer#clone()} is not null.
     */
    @Test
    public void cloneDoesNotReturnNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer received = volunteer.clone();

        assertNotNull(received);
    }    // testCloneNcloneDoesNotReturnNullotNull()

    /**
     * Tests that the return value of {@link Volunteer#clone()} is equal to the
     * argument.
     */
    @Test
    public void returnValueOfCloneIsEqualToOriginal() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer received = volunteer.clone();

        Volunteer expected = volunteer;
        assertEquals(expected, received);
    }    // returnValueOfCloneIsEqualToOriginal()

    /**
     * Tests that the return value of {@link Volunteer#clone()} is not identical
     * to the argument.
     */
    @Test
    public void returnValueOfCloneIsNotIdenticalToOriginal() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        Volunteer received = volunteer.clone();

        assertFalse(volunteer == received);
    }    // returnValueOfCloneIsNotIdenticalToOriginal()

    /* toString */

    /**
     * Tests that {@link Volunteer#toString()} does not throw an exception.
     */
    @Test
    public void toStringDoesNotThrowException() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        volunteer.toString();
    }    // toStringDoesNotThrowException()

    /**
     * Tests that the return value of {@link Volunteer#toString()} is not null.
     */
    @Test
    public void toStringDoesNotReturnNull() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        String received = volunteer.toString();

        assertNotNull(received);
    }    // toStringDoesNotReturnNull()

    /**
     * Tests that {@link Volunteer#toString()} returns the volunteer's name.
     */
    @Test
    public void toStringReturnsName() {
        String name = "Foo Bar";
        String email = "foo@bar";
        String phone = "555-FOO";
        String notes = "baz";
        boolean active = true;
        Volunteer volunteer = new Volunteer(name, email, phone, notes, active);
        List<Role> roles = Arrays.asList(new Role("A"), new Role("B"), new Role("C"));
        for (Role role : roles) {
            volunteer.addRole(role);
        }    // for

        String received = volunteer.toString();

        String expected = name;
        assertEquals(expected, received);
    }    // toStringReturnsName()


    /* getVolunteerFactory */

    /**
     * Tests that {@link Volunteer#getVolunteerFactory()} does not throw an exception.
     */
    @Test
    public void volunteerFactoryDoesNotThrowException() {
        Volunteer.getVolunteerFactory();
    }    // volunteerFactoryDoesNotThrowException()

    /**
     * Tests that the return value of {@link Volunteer#getVolunteerFactory()} is not
     * null.
     */
    @Test
    public void volunteerFactoryDoesNotReturnNull() {
        ReadWritableFactory factory = Volunteer.getVolunteerFactory();
        assertNotNull(factory);
    }    // volunteerFactoryDoesNotReturnNull()

}    // VolunteerTest
