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
 * Represents a BSC house manager.
 *
 * @author Wayne Miller
 */
public class Manager implements Person, Cloneable, Serializable, ReadWritable {
    
    /*
     * Static class properties and methods.
     */
    
    /**
     * Class version number.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * A factory that creates a {@link Manager} from a set of read-writable
     * properties. These properties may be extracted directly from a manager via
     * {@link Manager#getReadWritableProperties()}, but more typically are
     * extracted from a disk file.
     *
     * To obtain a manager factory, use the method
     * {@link Manager#getManagerFactory()}.
     *
     * @author Wayne Miller
     * @since 2.1
     */
    public static class Factory implements ReadWritableFactory<Manager> {
  
        /**
         * Constructs a new manager factory.
         */
        private Factory() {
        }    // Factory()

        /**
         * Constructs a manager from the given read-writable properties. If the
         * factory is unable to create a manager from the given properties, this
         * method returns null.
         *
         * The manager factory constructs a manager using the following
         * information from the given properties.
         * <ul>
         *   <li>The manager's name is given by the string value of the value
         * corresponding to "name".  If such a value does not exist or is null,
         * the manager's name is empty.</li>
         *   <li>The manager's email address is given by the string value of the
         * value corresponding to "email".  If such a value does not exist or is
         * null, the manager's email address is empty.</li>
         *   <li>The manager's phone number is given by the string value of the
         * value corresponding to "phone".  If such a value does not exist or is
         * null, the manager's email address is empty.</li>
         * </ul>
         * This method effectively acts as the reverse of
         * {@link Manager#getReadWritableProperties()}.
         *
         * @param properties the read-writable properties; may not be null
         * @return a manager constructed from the given properties
         * @throws NullPointerException if {@code properties} is null
         */
        @Override
        public Manager constructReadWritable(Map<String, Object> properties) {
            if (properties == null) {
                throw new NullPointerException("properties may not be null");
            }    // if
            Object nameObject = properties.get("name");
            Object emailObject = properties.get("email");
            Object phoneObject = properties.get("phone");
            String name = (nameObject != null) ? nameObject.toString() : "";
            String email = (emailObject != null) ? emailObject.toString() : "";
            String phone = (phoneObject != null) ? phoneObject.toString() : "";
            return new Manager(name, email, phone);
        }    // constructReadWritable()

    }    // Factory
    
    /**
     * Returns a factory that creates managers from read-writable property maps.
     * 
     * @return a factory that creates managers from read-writable property maps
     */
    public static Factory getManagerFactory() {
        return new Factory();
    }    // getMangerFactory();
    
    /*
     * Instance properties and methods.
     */

    /**
     * The manager's name.
     */
    private final String name;

    /**
     * The manager's email address;
     */
    private final String email;

    /**
     * The manager's phone number;
     */
    private final String phone;

    /**
     * Constructs a new manager.
     *
     * @param name the manager's name
     * @param email the manager's email address
     * @param phone the manager's phone number
     * @throws NullPointerException if any parameter is null
     */
    public Manager(String name, String email, String phone) {
        if (name == null) {
            throw new NullPointerException("name may not be null");
        }    // if
        if (email == null) {
            throw new NullPointerException("email may not be null");
        }    // if
        if (phone == null) {
            throw new NullPointerException("phone may not be null");
        }    // if
        
        this.name = name;
        this.email = email;
        this.phone = phone;
        assertInvariant();
    }    // Manager()

    /**
     * Returns the manager's name.
     *
     * @return the manager's name
     */
    @Override
    public String getName() {
        assertInvariant();
        return name;
    }    // getName()

    /**
     * Returns the manager's email address.
     *
     * @return the manager's email address
     */
    @Override
    public String getEmail() {
        assertInvariant();
        return email;
    }    // getEmail()

    /**
     * Returns the manager's phone number.
     *
     * @return the manager's phone number
     */
    public String getPhone() {
        assertInvariant();
        return phone;
    }    // getPhone()
    
    /**
     * Returns a map containing the read-writable properties of the manager.
     * The map returned by this method is guaranteed to have the following
     * properties.
     * <ul>
     *   <li>The map has exactly three keys: "name", "email", and "phone".</li>
     *   <li>Each value is a non-null {@link String} corresponding to the return
     * value of the appropriate method ({@link #getName()}, {@link #getEmail()},
     * or {@link #getPhone()}).</li>
     *   <li>The iteration order of the elements is fixed in the order the keys
     * are presented above.</li>
     * </ul>
     * 
     * @return a map containing the read-writable properties of the manager
     * @since 2.1
     */
    @Override
    public Map<String, Object> getReadWritableProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);
        return properties;
    }    // getReadWritableProperties()
    
    /**
     * Returns a factory that creates managers from read-writable property maps.
     * 
     * @return a factory that creates managers from read-writable property maps
     */
    @Override
    public Factory getReadWritableFactory() {
        return Manager.getManagerFactory();
    }    // getReadWritableFactory()
    
    /**
     * Indicates whether some other object is "equal to" this one.  An object is
     * equal to this volunteer only if all the following conditions hold:
     * <ol>
     *   <li>the object is another manager,</li>
     *   <li>both managers have the same name,</li>
     *   <li>both managers have the same email address, and</li>
     *   <li>both managers have the same phone number.</li>
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
        if (!(obj instanceof Manager)) {
            return false;
        }    // if
        
        Manager rhs = (Manager)obj;
        return name.equals(rhs.name) && email.equals(rhs.email) && phone.equals(rhs.phone);
    }    // equals()
    
    @Override
    public int hashCode() {
        final int SEED = 109;
        final int MULTIPLIER = 19;
        int code = SEED;
        code = code * MULTIPLIER + name.hashCode();
        code = code * MULTIPLIER + email.hashCode();
        code = code * MULTIPLIER + phone.hashCode();
        return code;
    }    // hashCode()
    
    /**
     * Creates and returns a copy of this manager.
     * 
     * @return a copy of this manager
     * @since 2.0
     */
    @Override
    public Manager clone() {
        Manager clone = null;
        try {
            clone = (Manager)super.clone();
        } catch (CloneNotSupportedException e) {    // try
            // We should never get here.  Manager's only superclass is Object,
            // which shouldn't throw.
            assert (false);
        }    // catch
        return clone;
    }    // clone()

    /**
     * Returns a string representation of the manager. This method is equivalent
     * to {@link #getName()}.
     *
     * @return a string representation of the manager
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
    }    // assertInvariant()
}    // Manager
