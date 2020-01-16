/*
 * Copyright © 2014-2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * Represents an event volunteer. Volunteers have the following properties.
 * <ul>
 * <li>A name, represented as a {@link String}.</li>
 * <li>An email address, represented as a {@link String}.</li>
 * <li>A phone number, represented as a {@link String}.</li>
 * <li>Notes, represented as a {@link String}.</li>
 * <li>Zero or more user roles, represented as a {@link List} of {@link Role}s.
 * Volunteers are initialized with zero roles.</li>
 * <li>A flag indicating whether or not the volunteer is active or not,
 * represented as a boolean.</li>
 * </ul>
 *
 * @author Wayne Miller, github.com/acadams
 */
public class Volunteer implements Cloneable, Matchable<String>, Serializable, ReadWritable {

    /*
     * Static class properties and methods.
     */

    /**
     * Class version number.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Read-writable key corresponding to volunteer name.
     */
    private static final String RW_NAME_KEY = "name";

    /**
     * Read-writable key corresponding to volunteer email.
     */
    private static final String RW_EMAIL_KEY = "email";

    /**
     * Read-writable key corresponding to volunteer phone.
     */
    private static final String RW_PHONE_KEY = "phone";

    /**
     * Read-writable key corresponding to volunteer notes.
     */
    private static final String RW_NOTES_KEY = "notes";

    /**
     * Read-writable key corresponding to volunteer notes.
     */
    private static final String RW_ACTIVE_KEY = "active";

    /**
     * Read-writable key corresponding to volunteer roles.
     */
    private static final String RW_ROLES_KEY = "roles";

    /**
     * A factory that creates a {@link Volunteer} from a set of read-writable
     * properties. These properties may be extracted directly from a volunteer
     * via {@link Volunteer#getReadWritableProperties()}, but more typically are
     * extracted from a disk file.
     *
     * To obtain a volunteer factory, use the method
     * {@link Volunteer#getVolunteerFactory()}.
     *
     * @author Wayne Miller
     * @since 2.1
     */
    public static class Factory implements ReadWritableFactory<Volunteer> {

        /**
         * Constructs a new volunteer factory.
         */
        Factory() {
            // do nothing
        }

        /**
         * Constructs a volunteer from the given read-writable properties. If
         * the factory is unable to create a volunteer from the given
         * properties, this method returns null.
         *
         * The volunteer factory constructs a volunteer using the following
         * information from the given properties.
         * <ul>
         * <li>The volunteer's name is given by the string value of the value
         * corresponding to "name". If such a value does not exist or is null,
         * the volunteer's name is empty.</li>
         * <li>The volunteer's email address is given by the string value of the
         * value corresponding to "email". If such a value does not exist or is
         * null, the volunteer's email address is empty.</li>
         * <li>The volunteer's phone number is given by the string value of the
         * value corresponding to "phone". If such a value does not exist or is
         * null, the volunteer's phone number is empty.</li>
         * <li>The volunteer's notes are given by the string value of the value
         * corresponding to "notes". If such a value does not exist or is null,
         * the volunteer's notes are empty.</li>
         * <li>The volunteer's active status is given by the boolean value of
         * the value corresponding to "active". If such a value does not exist
         * or is null, the volunteer's active status is true.</li>
         * <li>The volunteer's list of shift roles is given by the string value
         * of the value corresponding to "roles", interpreted as a
         * comma-delimited list. If such a value does not exist or is null, the
         * volunteer's lists of roles is empty.
         * </ul>
         * This method effectively acts as the reverse of
         * {@link Volunteer#getReadWritableProperties()}.
         *
         * @param properties the read-writable properties; may not be null
         * @return a volunteer constructed from the given properties, or null if
         * the factory is unable to construct a volunteer
         * @throws NullPointerException if {@code properties} is null
         */
        @Override
        public Volunteer constructReadWritable(Map<String, Object> properties) {
            if (properties == null) {
                throw new NullPointerException("Volunteer properties were not set for ReadWritable construction.");
            }    // if

            Object nameObject = properties.get(RW_NAME_KEY);
            String name = (nameObject != null) ? nameObject.toString() : "";

            Object emailObject = properties.get(RW_EMAIL_KEY);
            String email = (emailObject != null) ? emailObject.toString() : "";

            Object phoneObject = properties.get(RW_PHONE_KEY);
            String phone = (phoneObject != null) ? phoneObject.toString() : "";

            Object notesObject = properties.get(RW_NOTES_KEY);
            String notes = (notesObject != null) ? notesObject.toString() : "";

            Object activeObject = properties.get(RW_ACTIVE_KEY);
            String activeStringObject = (activeObject != null) ? activeObject.toString() : null;
            Boolean activeBooleanObject = (activeStringObject != null) ? Boolean.valueOf(activeStringObject) : null;
            boolean active = (activeBooleanObject != null) ? activeBooleanObject : true;

            Object rolesObject = properties.get(RW_ROLES_KEY);
            String[] roleNames = {};
            if (rolesObject != null) {
                String rolesString = rolesObject.toString();
                roleNames = rolesString.split(",");
            }
            List<Role> roles = Arrays.stream(roleNames)
                    .map(Role::new)
                    .collect(Collectors.toList());
            Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);

            return volunteer;
        }    // constructReadWritable()
    }

    /**
     * Returns a factory that creates volunteers from read-writable property
     * maps. This factory effectively reverses the actions of
     * {@link Volunteer#getReadWritableProperties()}.
     *
     * @return a factory that creates volunteers from read-writable property
     * maps
     */
    public static Factory getVolunteerFactory() {
        return new Factory();
    }    // getVolunteerFactory();

    /*
     * Instance properties and methods.
     */

    /**
     * The volunteer's name.
     */
    private String name;

    /**
     * The volunteer's email address.
     */
    private String email;

    /**
     * The volunteer's phone number.
     */
    private String phone;

    /**
     * Notes about the volunteer.
     */
    private String notes;

    /**
     * The volunteer's active status.
     */
    private boolean active;

    /**
     * The volunteer's list of roles
     */
    private final List<Role> roles;

    /**
     * Constructs a new volunteer.
     *
     * @param name the volunteer's name; may not be null
     * @param email the volunteer's email address; may not be null
     * @param phone the volunteer's phone number; may not be null
     * @param notes the volunteer's notes; may not be null
     * @param active true if the volunteer is active, false if the volunteer is
     * inactive
     * @param roles the roles the volunteer can perform; may not be null nor
     * contain null
     * @throws NullPointerException if any parameter is null or if {@code roles}
     * contains null
     */
    public Volunteer(String name, String email, String phone, String notes, boolean active, List<Role> roles) {
        if (name == null) {
            throw new NullPointerException("name may not be null");
        }    // if
        if (email == null) {
            throw new NullPointerException("email may not be null");
        }    // if
        if (phone == null) {
            throw new NullPointerException("phone may not be null");
        }    // if
        if (notes == null) {
            throw new NullPointerException("notes may not be null");
        }    // if
        if (roles == null) {
            throw new NullPointerException("roles may not be null");
        }    // if
        if (roles.contains(null)) {
            throw new NullPointerException("roles may not contain null");
        }    // if

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
        this.active = active;
        this.roles = new ArrayList<>(roles);
        assertInvariant();
    }    // Volunteer()

    /**
     * Returns the volunteer's name.
     *
     * @return the volunteer's name
     */
    public String getName() {
        assertInvariant();
        return name;
    }    // getName()

    /**
     * Sets the volunteer's name to the given string.
     *
     * @param name the new name; may not be null
     * @throws NullPointerException if {@code name} is null
     */
    public void setName(String name) {
        assertInvariant();
        if (name == null) {
            throw new NullPointerException("name may not be null");
        }    // if

        this.name = name;
        assertInvariant();
    }    // setName()

    /**
     * Returns the volunteer's email address.
     *
     * @return the volunteer's email address
     */
    public String getEmail() {
        assertInvariant();
        return email;
    }    // getEmail()

    /**
     * Sets the volunteer's email address to the given string.
     *
     * @param email the new email address; may not be null
     * @throws NullPointerException if {@code email} is null
     */
    public void setEmail(String email) {
        assertInvariant();
        if (email == null) {
            throw new NullPointerException("email may not be null");
        }    // if

        this.email = email;
        assertInvariant();
    }    // setEmail()

    /**
     * Returns the volunteer's phone number.
     *
     * @return the volunteer's phone number
     */
    public String getPhone() {
        assertInvariant();
        return phone;
    }    // getPhone()

    /**
     * Sets the volunteer's phone number to the given string.
     *
     * @param phone the new phone number; may not be null
     * @throws NullPointerException if {@code phone} is null
     */
    public void setPhone(String phone) {
        assertInvariant();
        if (phone == null) {
            throw new NullPointerException("phone may not be null");
        }    // if

        this.phone = phone;
        assertInvariant();
    }    // setPhone()

    /**
     * Returns the volunteer notes.
     *
     * @return the volunteer notes
     */
    public String getNotes() {
        assertInvariant();
        return notes;
    }    // getNotes()

    /**
     * Sets the volunteer notes to the given string.
     *
     * @param notes the new notes; may not be null
     * @throws NullPointerException if {@code notes} is null
     */
    public void setNotes(String notes) {
        assertInvariant();
        if (notes == null) {
            throw new NullPointerException("notes may not be null");
        }    // if

        this.notes = notes;
        assertInvariant();
    }    // setNotes()

    /**
     * Returns true if the volunteer is active, or false if the volunteer is inactive.
     *
     * @return true if the volunteer is active; false otherwise
     * @since 3.1
     */
    public boolean isActive() {
        assertInvariant();
        return active;
    }    // isActive()

    /**
     * Sets the active state of the volunteer.
     *
     * @param active true if the volunteer is active, false if inactive
     * @since 3.1
     */
    public void setActive(boolean active) {
        assertInvariant();
        this.active = active;
        assertInvariant();
    }    // setActive()

    /**
     * Returns an unmodifiable list of the roles added to the volunteer.
     *
     * @return the roles added to the volunteer
     */
    public List<Role> getRoles(){
        assertInvariant();
        return new ArrayList<>(roles);
    }    // getRoles()

    /**
     * Sets the list of roles the volunteer can perform to the given list.
     *
     * @param roles the roles the volunteer can perform; may not be null nor
     * contain null
     * @throws NullPointerException if {@code roles} is null or contains null
     * @since 3.2
     */
    public void setRoles(List<Role> roles) {
        assertInvariant();
        if (roles == null) {
            throw new NullPointerException("roles may not be null");
        }    // if
        if (roles.contains(null)) {
            throw new NullPointerException("roles may not contain null");
        }    // if
        this.roles.clear();
        this.roles.addAll(roles);
        assertInvariant();
    }    // setRoles()

    /**
     * Returns a map containing the read-writable properties of the volunteer.
     * The map returned by this method is guaranteed to have the following
     * properties.
     * <ul>
     * <li>The map has five keys: "name", "email", "phone", and "notes".</li>
     * <li>No value is null.
     * <li>The value of "name" is a non-null {@link String} equal to the value
     * of {@link #getName()}.</li>
     * <li>The value of "email" is a non-null {@link String} equal to the value
     * of {@link #getName()}.</li>
     * <li>The value of "phone" is a non-null {@link String} equal to the value
     * of {@link #getPhone()}.</li>
     * <li>The value of "notes" is a non-null {@link String} equal to the value
     * of {@link #getNotes()}.</li>
     * <li>The value of "active" is a non-null {@link Boolean} equal to the
     * value of {@link #isActive()}.</li>
     * <li>The value of "roles" is a non-null {@link String} equal to a
     * comma-separated list of the names of each role returned by
     * {@link #getRoles()}.</li>
     * <li>The iteration order of the elements is fixed in the order the keys
     * are presented above.</li>
     * </ul>
     *
     * @return a map containing the read-writable properties of the volunteer
     * @since 2.1
     */
    @Override
    public Map<String, Object> getReadWritableProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put(RW_NAME_KEY, name);
        properties.put(RW_EMAIL_KEY, email);
        properties.put(RW_PHONE_KEY, phone);
        properties.put(RW_NOTES_KEY, notes);
        properties.put(RW_ACTIVE_KEY, active);
        String roleNames = roles.stream()
                .map(Role::getName)
                .collect(Collectors.joining(","));
        properties.put(RW_ROLES_KEY, roleNames);
        return properties;
    }    // getReadWritableProperties()

    /**
     * Returns a factory that creates volunteers from read-writable property maps.
     *
     * @return a factory that creates volunteers from read-writable property maps
     */
    @Override
    public Factory getReadWritableFactory() {
        return Volunteer.getVolunteerFactory();
    }    // getReadWritableFactory()

    /**
     * Indicates whether the volunteer matches the given string. The volunteer
     * is considered to match the string if <b>any</b> of the following
     * conditions are met:
     * <ul>
     * <li>part of the volunteer's name contains the criterion in a case-insensitive manner,</li>
     * <li>part of the volunteer's email contains the criterion in a case-insensitive manner,</li>
     * <li>part of the volunteer's phone contains the criterion in a case-insensitive manner,</li>
     * <li>part of the volunteer's notes contains the criterion in a case-insensitive manner,</li>
     * <li>any of the volunteer's assigned roles match the criterion,</li>
     * <li>the volunteer is active and the criterion is "active", or</li>
     * <li>the volunteer is inactive and the criterion is "inactive".</li>
     * </ul>
     * The volunteer always matches an empty string.
     *
     * @param criterion the criterion; may not be null
     * @return true if the volunteer matches criterion; false otherwise
     * @throws NullPointerException if criterion is null
     * @see Role#matches(String)
     * @since 4.0
     */
    @Override
    public boolean matches(String criterion) {
        assertInvariant();
        if (criterion == null) {
            throw new NullPointerException("criterion may not be null");
        }    // if

        if (criterion.equals("active")) {
            return active;
        }
        if (criterion.equals("inactive")) {
            return !active;
        }
        return roles.stream().anyMatch(role -> role.matches(criterion))
                || StringUtils.containsIgnoreCase(name, criterion)
                || StringUtils.containsIgnoreCase(email, criterion)
                || StringUtils.containsIgnoreCase(phone, criterion)
                || StringUtils.containsIgnoreCase(notes, criterion);
    }    // matches()

    /**
     * Indicates whether some other object is "equal to" this one.  An object is
     * equal to this volunteer only if all the following conditions hold:
     * <ol>
     *   <li>the object is another volunteer,</li>
     *   <li>both volunteers have the same name,</li>
     *   <li>both volunteers have the same email address,</li>
     *   <li>both volunteers have the same phone number,</li>
     *   <li>both volunteers have the same notes, and</li>
     *   <li>both volunteers have the same active status.</li>
     * </ol>
     *
     * @param obj the object with which to compare
     * @return true if the objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }    // if
        if (!(obj instanceof Volunteer)) {
            return false;
        }    // if

        Volunteer rhs = (Volunteer)obj;
        return name.equals(rhs.name)
                && email.equals(rhs.email)
                && phone.equals(rhs.phone)
                && notes.equals(rhs.notes)
                && (active == rhs.active);
    }    // equals()

    @Override
    public int hashCode() {
        final int SEED = 5;
        final int MULTIPLIER = 37;
        int code = SEED;
        code = code * MULTIPLIER + name.hashCode();
        code = code * MULTIPLIER + email.hashCode();
        code = code * MULTIPLIER + phone.hashCode();
        code = code * MULTIPLIER + notes.hashCode();
        code = code * MULTIPLIER + Boolean.hashCode(active);
        return code;
    }    // hashCode()

    /**
     * Creates and returns a copy of this volunteer.
     *
     * @return a copy of this volunteer
     * @since 2.0
     */
    @Override
    public Volunteer clone() {
        Volunteer clone = null;
        try {
            clone = (Volunteer)super.clone();
        } catch (CloneNotSupportedException e) {    // try
            // We should never get here.  Volunteer's only superclass is Object,
            // which shouldn't throw.
            assert (false);
        }    // catch
        return clone;
    }    // clone()

    /**
     * Returns a string representation of the volunteer. This method is
     * equivalent to {@link #getName()}.
     *
     * @return a string representation of the volunteer
     */
    @Override
    public String toString() {
        assertInvariant();
        return name;
    }    // toString()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (name != null);
        assert (email != null);
        assert (phone != null);
        assert (notes != null);
        assert (roles != null);
        assert (! roles.contains(null));
    }    // assertInvariant()
}    // Volunteer
