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

package bscmail;

import java.io.Serializable;
import java.util.*;
import main.*;

/**
 * Represents a BSC volunteer.
 *
 * @author Wayne Miller
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
        }    // Factory()

        /**
         * Constructs a volunteer from the given read-writable properties. If
         * the factory is unable to create a volunteer from the given
         * properties, this method returns null.
         *
         * The volunteer factory constructs a volunteer using the following
         * information from the given properties.
         * <ul>
         *   <li>The volunteer's name is given by the string value of the value
         * corresponding to "name".  If such a value does not exist or is null,
         * the volunteer's name is empty.</li>
         *   <li>The volunteer's email address is given by the string value of
         * the value corresponding to "email".  If such a value does not exist
         * or is null, the volunteer's email address is empty.</li>
         *   <li>The volunteer's "can angel" status is is given by the value
         * corresponding to "canAngel", which must be a {@link Boolean}.  If
         * such a value does not exist, or if the value is not a Boolean, then
         * a volunteer cannot be created and this method returns null.</li>
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
                throw new NullPointerException("properties may not be null");
            }    // if

            Volunteer volunteer = null;
            try {
                Object nameObject = properties.get("name");
                Object emailObject = properties.get("email");
                Object canAngelObject = properties.get("canAngel");
                String name = (nameObject != null) ? nameObject.toString() : "";
                String email = (emailObject != null) ? emailObject.toString() : "";
                Boolean canAngel = null;
                if (canAngelObject instanceof Boolean) {
                    canAngel = (Boolean)canAngelObject;
                } else if (canAngelObject instanceof String) {    // if
                    canAngel = Boolean.valueOf((String)canAngelObject);
                }    // else
                if (canAngel != null) {
                    volunteer = new Volunteer(name, email, canAngel);
                }    // if
            } catch (ClassCastException e) {    // try
                // The canAngel property was not a Boolean, so we will simply
                // return null.
            }    // catch
            return volunteer;
        }    // constructReadWritable()

    }    // Factory

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
    private final String name;

    /**
     * The volunteer's email address;
     */
    private final String email;

    /**
     * True if the volunteer can perform angel shifts.
     */
    private final boolean canAngel;


    /**
     * Constructs a new volunteer.
     *
     * @param name the volunteer's name
     * @param email the volunteer's email address
     * @param canAngel true if the volunteer may be an angel; false otherwise
     * @throws NullPointerException if {@code name} or {@code email} are null
     */
    public Volunteer(String name, String email, boolean canAngel) {
        if (name == null) {
            throw new NullPointerException("name may not be null");
        }    // if
        if (email == null) {
            throw new NullPointerException("email may not be null");
        }    // if

        this.name = name;
        this.email = email;
        this.canAngel = canAngel;
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

    /**
     * Returns true if the volunteer can perform an angel shift; false
     * otherwise.
     *
     * @return true if the volunteer can perform an angel shift; false otherwise
     */
    public boolean canAngel() {
        assertInvariant();
        return canAngel;
    }    // canAngel()
    
    /**
     * Returns a map containing the read-writable properties of the volunteer.
     * The map returned by this method is guaranteed to have the following
     * properties.
     * <ul>
     *   <li>The map has exactly three keys: "name", "email", and
     * "canAngel".</li>
     *   <li>No value is null.
     *   <li>The value of "name" is a non-null {@link String} equal to the value
     * of {@link #getName()}.</li>
     *   <li>The value of "email" is a non-null {@link String} equal to the
     * value of {@link #getName()}.</li>
     *   <li>The value of "canAngel" is a non-null {@link Boolean} equal to the
     * value of {@link #canAngel()}.</li>
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
        properties.put("canAngel", canAngel);
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
     *   <li>both volunteers have the same email address, and</li>
     *   <li>both volunteers have the same "can angel" status.</li>
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
        return name.equals(rhs.name) && email.equals(rhs.email) && (canAngel == rhs.canAngel);
    }    // equals()
    
    @Override
    public int hashCode() {
        final int SEED = 5;
        final int MULTIPLIER = 37;
        int code = SEED;
        code = code * MULTIPLIER + name.hashCode();
        code = code * MULTIPLIER + email.hashCode();
        code = code * MULTIPLIER + Boolean.valueOf(canAngel).hashCode();
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
