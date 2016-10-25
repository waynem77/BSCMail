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

import java.io.Serializable;
import java.util.*;
import main.*;

/**
 * Represents an event volunteer.
 *
 * @author Wayne Miller, github.com/acadams
 */
public class Volunteer implements Person, Cloneable, Serializable, ReadWritable {
    
    /*
     * Static class properties and methods.
     */
    
    /**
     * Class version number.
     */
    private static final long serialVersionUID = 1L;
    
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
        private Factory() {
            // do nothing
        }

        /**
         * Constructs a volunteer from the given read-writable properties. If
         * the factory is unable to create a volunteer from the given
         * properties, this method returns null.
         * <p>
         * The volunteer factory constructs a volunteer using the following
         * information from the given properties.
         * <ul>
         * <li>The volunteer's name is given by the string value of the value
         * corresponding to "name".  If such a value does not exist or is null,
         * the volunteer's name is empty.</li>
         * <li>The volunteer's email address is given by the string value of
         * the value corresponding to "email".  If such a value does not exist
         * or is null, the volunteer's email address is empty.</li>
         *   <li>The volunteer's phone number is given by the string value of
         * the value corresponding to "phone".  If such a value does not exist
         * or is null, the volunteer's phone number is empty.</li>
         *   <li>The volunteer's notes are given by the string value of
         * the value corresponding to "notes".  If such a value does not exist
         * or is null, the volunteer's notes are empty.</li>
         * </ul>
         * This method effectively acts as the reverse of
         * {@link Volunteer#getReadWritableProperties()}.
         *
         * @param properties the read-writable properties; may not be null
         * @return a volunteer constructed from the given properties, or
         * null if the factory is unable to construct a volunteer
         * @throws NullPointerException if {@code properties} is null
         */
        @Override
        public Volunteer constructReadWritable(Map<String, Object> properties) {
            if (properties == null) {
                throw new NullPointerException("Volunteer properties were not set for ReadWritable construction.");
            }    // if

            Volunteer volunteer = null;
            try {

                if(Application.getImportFileName() == "" || Application.getImportFileName() == "volunteer-list") {

                    Object nameObject = properties.get("name");
                    Object emailObject = properties.get("email");
                    Object phoneObject = properties.get("phone");
                    Object notesObject = properties.get("notes");
                    String name = (nameObject != null) ? nameObject.toString() : "";
                    String email = (emailObject != null) ? emailObject.toString() : "";
                    String phone = (phoneObject != null) ? phoneObject.toString() : "";
                    String notes = (notesObject != null) ? notesObject.toString() : "";
                    volunteer = new Volunteer(name, email, phone, notes);
                    Object roleObject = properties.get("role");
                    if (roleObject != null) {
                        String roles = roleObject.toString();
                        String[] roleNames = roles.split(",");
                        for (String roleName : roleNames) {
                            Role role = new Role(roleName);
                            volunteer.addRole(role);
                        }
                    }
                }

                //import old manager files
                else if (Application.getImportFileName() == "manager-list") {
                    Object nameObject = properties.get("name");
                    Object emailObject = properties.get("email");
                    Object phoneObject = properties.get("phone");
                    String name = (nameObject != null) ? nameObject.toString() : "";
                    String email = (emailObject != null) ? emailObject.toString() : "";
                    String phone = (phoneObject != null) ? phoneObject.toString() : "";
                    volunteer = new Volunteer(name, email, phone, "");
                    volunteer.addRole(new Role("Manager"));
                }

            } catch (ClassCastException e) {    // try
                // The canAngel property was not a Boolean, so we will simply
                // return null.
            }    // catch
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
     * The volunteer's list of roles
     */
    private List<Role> roles;

    /**
     * The volunteer's phone number.
     */
    private String phone;

    /**
     * Notes about the volunteer.
     */
    private String notes;

    /**
     * Constructs a new volunteer.
     *
     * @param name the volunteer's name
     * @param email the volunteer's email address
     * @param phone the volunteer's phone number
     * @param notes the volunteer's notes
     * @throws NullPointerException if any parameter is null
     */
    public Volunteer(String name, String email, String phone, String notes) {
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

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
        this.roles = new LinkedList<Role>();
        assertInvariant();
    }    // Volunteer()

    /**
     * Returns the volunteer's name.
     *
     * @return the volunteer's name
     */
    @Override
    public String getName() {
        assertInvariant();
        return name;
    }    // getName()

    public void setName(String newName) {
        name = newName;
    }

    /**
     * Returns the volunteer's email address.
     *
     * @return the volunteer's email address
     */
    @Override
    public String getEmail() {
        assertInvariant();
        return email;
    }    // getEmail()

    public void setEmail(String newEmail) {
        email = newEmail;
    }

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
     * Sets the volunteer's phone number.
     *
     * @param phone the new phone number; may not be null
     * @throws NullPointerException if {@code phone} is null
     */
    public void setPhone(String phone) {
        if (phone == null) {
            throw new NullPointerException("phone may not be null");
        }    // if
        this.phone = phone;
        assertInvariant();
    }

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
     * Sets the volunteer notes.
     *
     * @param notes the new notes; may not be null
     * @throws NullPointerException if {@code phone} is null
     */
    public void setNotes(String notes) {
        if (notes == null) {
            throw new NullPointerException("notes may not be null");
        }    // if
        this.notes = notes;
        assertInvariant();
    }

    public void addRole(Role newRole){
        //ensure new role is unique
        for (Role role : roles){
            if (role.equals(newRole))
                return;
        }
        roles.add(newRole);
    }

    public void removeRole(Role oldRole){
        roles.remove(oldRole);
    }

    public List<Role> getRoles(){
        //ensure that Volunteer's current roles are still kosher
        List<Role> validRoles = Application.getRoles();
        for (Role role : roles){
            if (!validRoles.contains(role))
                removeRole(role);
        }

        return roles;
    }

    /**
     * Returns a map containing the read-writable properties of the volunteer.
     * The map returned by this method is guaranteed to have the following
     * properties.
     * <ul>
     *   <li>The map has four keys: "name", "email", "phone", and "notes".</li>
     *   <li>No value is null.
     *   <li>The value of "name" is a non-null {@link String} equal to the value
     * of {@link #getName()}.</li>
     *   <li>The value of "email" is a non-null {@link String} equal to the
     * value of {@link #getName()}.</li>
     *   <li>The value of "phone" is a non-null {@link String} equal to the
     * value of {@link #getPhone()}.</li>
     *   <li>The value of "notes" is a non-null {@link String} equal to the
     * value of {@link #getNotes()}.</li>
     *   <li>The value of each "role" is a non-null {@link Role} found in the list
     * of {@link #getRoles()}.</li>
     *   <li>The iteration order of the elements is fixed in the order the keys
     * are presented above.</li>
     * </ul>
     * 
     * @return a map containing the read-writable properties of the volunteer
     * @since 2.1
     */
    @Override
    public Map<String, Object> getReadWritableProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);
        properties.put("notes", notes);
        String roleNames = "";
        for (Role role : roles){
            roleNames += role.getName() +",";
        }
        if (roleNames.length() > 0)
            roleNames = roleNames.substring(0, roleNames.length() - 1);
        properties.put("role", roleNames);
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
     * Indicates whether some other object is "equal to" this one.  An object is
     * equal to this volunteer only if all the following conditions hold:
     * <ol>
     *   <li>the object is another volunteer,</li>
     *   <li>both volunteers have the same name,</li>
     *   <li>both volunteers have the same email address</li>
     *   <li>both volunteers have the same phone number, and</li>
     *   <li>both volunteers have the same notes.</li>
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
        return name.equals(rhs.name) && email.equals(rhs.email) && phone.equals(rhs.phone) && notes.equals(rhs.notes);
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
    }    // assertInvariant()
}    // Volunteer
