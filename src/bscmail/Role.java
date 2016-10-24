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
 * Represents a BSC Role.
 *
 * @author Nathan Cordner
 */
public class Role implements Cloneable, Serializable, ReadWritable {

    /*
     * Static class properties and methods.
     */

    /**
     * Class version number.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A factory that creates a {@link Role} from a set of read-writable
     * properties. These properties may be extracted directly from a role
     * via {@link Role#getReadWritableProperties()}, but more typically are
     * extracted from a disk file.
     *
     * To obtain a role factory, use the method
     * {@link Role#getRoleFactory()}.
     *
     * @author Nathan Cordner, on behalf of Vecna
     * @since 3.0
     */
    public static class Factory implements ReadWritableFactory<Role> {

        /**
         * Constructs a new role factory.
         */
        private Factory() {
        }

        /**
         * Constructs a role from the given read-writable properties. If
         * the factory is unable to create a role from the given
         * properties, this method returns null.
         *
         * The role factory constructs a role using the following
         * information from the given properties.
         * <ul>
         *   <li>The role's name is given by the string value of the value
         * corresponding to "name".  If such a value does not exist or is null,
         * the role's name is empty.</li>
         * </ul>
         * This method effectively acts as the reverse of
         * {@link Role#getReadWritableProperties()}.
         *
         * @param properties the read-writable properties; may not be null
         * @return a role constructed from the given properties, or
         * null if the factory is unable to construct a role
         * @throws NullPointerException if {@code properties} is null
         */
        @Override
        public Role constructReadWritable(Map<String, Object> properties) {
            if (properties == null) {
                throw new NullPointerException("properties may not be null");
            }    // if

            Role role;
            try {
                Object nameObject = properties.get("name");
                String name = (nameObject != null) ? nameObject.toString() : "";
                role = new Role(name);
            } catch (ClassCastException e) {
                return null;
            }
            return role;
        }    // constructReadWritable()

    }    // Factory

    /**
     * Returns a factory that creates roles from read-writable property
     * maps. This factory effectively reverses the actions of
     * {@link Role#getReadWritableProperties()}.
     *
     * @return a factory that creates roles from read-writable property
     * maps
     */
    public static Factory getRoleFactory() {
        return new Factory();
    }    // getRoleFactory();

    /*
     * Instance properties and methods.
     */

    /**
     * The role's name.
     */
    private final String name;


    /**
     * Constructs a new role.
     *
     * @param name the role's name
     * @throws NullPointerException if {@code name} or {@code email} are null
     */
    public Role(String name) {
        if (name == null) {
            throw new NullPointerException("name may not be null");
        }    // if

        this.name = name;
        assertInvariant();
    }    // Role()

    /**
     * Returns the role's name.
     *
     * @return the role's name
     */
    //@Override
    public String getName() {
        assertInvariant();
        return name;
    }    // getName()

    /**
     * Returns a map containing the read-writable properties of the role.
     * The map returned by this method is guaranteed to have the following
     * properties.
     * <ul>
     *   <li>The map has exactly one key, namely "name".</li>
     *   <li>No value is null.
     *   <li>The value of "name" is a non-null {@link String} equal to the value
     * of {@link #getName()}.</li>
     *   <li>The iteration order of the elements is fixed in the order the keys
     * are presented above.</li>
     * </ul>
     *
     * @return a map containing the read-writable properties of the role
     * @since 3.0
     */
    @Override
    public Map<String, Object> getReadWritableProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("name", name);
        return properties;
    }    // getReadWritableProperties()

    /**
     * Returns a factory that creates roles from read-writable property maps.
     *
     * @return a factory that creates roles from read-writable property maps
     */
    @Override
    public Factory getReadWritableFactory() {return Role.getRoleFactory();}    // getReadWritableFactory()

    /**
     * Indicates whether some other object is "equal to" this one.  An object is
     * equal to this role only if all the following conditions hold:
     * <ol>
     *   <li>the object is another role,</li>
     *   <li>both roles have the same name.</li>
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
        if (!(obj instanceof Role)) {
            return false;
        }    // if

        Role rhs = (Role)obj;
        return name.equals(rhs.name);
    }    // equals()

    @Override
    public int hashCode() {
        final int SEED = 5;
        final int MULTIPLIER = 37;
        int code = SEED;
        code = code * MULTIPLIER + name.hashCode();
        return code;
    }    // hashCode()

    /**
     * Creates and returns a copy of this role.
     *
     * @return a copy of this role
     * @since 3.0
     */
    @Override
    public Role clone() {
        Role clone = null;
        try {
            clone = (Role)super.clone();
        } catch (CloneNotSupportedException e) {    // try
            // We should never get here.  Role's only superclass is Object,
            // which shouldn't throw.
            assert (false);
        }    // catch
        return clone;
    }    // clone()

    /**
     * Returns a string representation of the role. This method is
     * equivalent to {@link #getName()}.
     *
     * @return a string representation of the role
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
    }    // assertInvariant()

}  //Role
