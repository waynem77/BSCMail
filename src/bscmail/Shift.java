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
 * Represents a BSC volunteer shift.
 *
 * @author Wayne Miller
 */
public class Shift implements Cloneable, Serializable, ReadWritable {
    
    /*
     * Static class properties and methods.
     */

    /**
     * Class version number.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A factory that creates a {@link Shift} from a set of read-writable
     * properties. These properties may be extracted directly from a shift via
     * {@link Shift#getReadWritableProperties()}, but more typically are
     * extracted from a disk file.
     * <p>
     * To obtain a shift factory, use the method
     * {@link Shift#getShiftFactory()}.
     *
     * @author Wayne Miller
     * @since 2.1
     */
    public static class Factory implements ReadWritableFactory<Shift> {

        /**
         * Constructs a new shift factory.
         */
        private Factory() {
        }

        /**
         * Constructs a shift from the given read-writable properties. If
         * the factory is unable to create a shift from the given
         * properties, this method returns null.
         * <p>
         * The shift factory constructs a shift using the following
         * information from the given properties.
         * <ul>
         * <li>The shift's description is given by the string value of the
         * value corresponding to "description".  If such a value does not exist
         * or is null, the shift's description is empty.</li>
         * <li>The volunteer assigned to the shift is given by the value
         * corresponding to "volunteer", which must be a {@link Volunteer}.  If
         * such a value does not exist, or if the value is not a Volunteer, then
         * no volunteer is assigned to the shift.</li>
         * </ul>
         * This method effectively acts as the reverse of
         * {@link Shift#getReadWritableProperties()}.
         *
         * @param properties the read-writable properties; may not be null
         * @return a shift constructed from the given properties, or
         * null if the factory is unable to construct a shift
         * @throws NullPointerException if {@code properties} is null
         */
        @Override
        public Shift constructReadWritable(Map<String, Object> properties) {
            if (properties == null) {
                throw new NullPointerException("properties may not be null");
            }    // if

            Shift shift = null;
            Object descriptionObject = properties.get("description");
            String description = (descriptionObject != null) ? descriptionObject.toString() : "";

            Object volunteerObject = properties.get("volunteer");
            Volunteer volunteer = null;
            if (volunteerObject instanceof Volunteer) {
                volunteer = (Volunteer) volunteerObject;
            } else if (volunteerObject instanceof Map) {    // if
                Map<String, Object> volunteerProperties = (Map<String, Object>) volunteerObject;
                ReadWritableFactory<Volunteer> factory = Volunteer.getVolunteerFactory();
                volunteer = factory.constructReadWritable(volunteerProperties);
            }

            // Construct a shift
            shift = new Shift(description);
            shift.setVolunteer(volunteer);

            return shift;
        }
    }

    /**
     * Returns a factory that creates shifts from read-writable property maps.
     * This factory effectively reverses the actions of
     * {@link Shift#getReadWritableProperties()}.
     *
     * @return a factory that creates shifts from read-writable property maps
     */
    public static Factory getShiftFactory() {
        return new Factory();
    }

    /*
     * Instance properties and methods.
     */

    /**
     * The shift's description.
     */
    private final String description;

    /**
     * The volunteer assigned to the shift.  Null if no volunteer is assigned to
     * the shift.
     */
    private Volunteer volunteer;

    /**
     * Constructs a new shift.
     *
     * @param description  the shift's description; may not be null
     * @throws NullPointerException if {@code description} is null
     */
    public Shift(String description) {
        if (description == null) {
            throw new NullPointerException("description may not be null");
        }
        this.description = description;
        volunteer = null;
        assertInvariant();
    }

    /**
     * Returns the shift's description.
     *
     * @return the shift's description
     */
    public String getDescription() {
        assertInvariant();
        return description;
    }

    /**
     * Returns true if the shift is open; that is, if no volunteer is assigned
     * to it.
     *
     * @return true if the shift is open; false otherwise
     */
    public boolean isOpen() {
        assertInvariant();
        return (volunteer == null);
    }

    /**
     * Returns the volunteer assigned to this shift.  If no volunteer is
     * assigned to this shift, this method returns null.
     *
     * @return the volunteer assigned to this shift
     */
    public Volunteer getVolunteer() {
        assertInvariant();
        return volunteer;
    }

    /**
     * Assigns a volunteer to this shift.
     * <p>
     * If this method is called with a null parameter, the volunteer will be
     * unassigned from the shift, and the shift will become open.
     *
     * @param volunteer the volunteer to assign to this shift
     */
    public void setVolunteer(Volunteer volunteer) {
        assertInvariant();
        this.volunteer = volunteer;
        assertInvariant();
    }

    /**
     * Returns a map containing the read-writable properties of the shift. The
     * map returned by this method is guaranteed to have the following
     * properties.
     * <ul>
     * <li>The map has a key "description".  The value for this key is a
     * non-null {@link String} equal to the return value of
     * {@link #getDescription()}.</li>
     * <li>If the shift has a volunteer, then the map has a key "volunteer".
     * The value for this key is a non-null {@link Volunteer} equal to the
     * return value of {@link #getVolunteer()}.  If the shift has no volunteer,
     * then the map contains no such key-value pair.</li>
     * <li>The iteration order of the elements is fixed in the order the keys
     * are presented above.</li>
     * </ul>
     *
     * @return a map containing the read-writable properties of the shift
     * @since 2.1
     */
    @Override
    public Map<String, Object> getReadWritableProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("description", description);
        if (volunteer != null) {
            properties.put("volunteer", volunteer);
        }
        return properties;
    }

    /**
     * Returns a factory that creates shifts from read-writable property maps.
     *
     * @return a factory that creates shifts from read-writable property maps
     */
    @Override
    public Factory getReadWritableFactory() {
        return Shift.getShiftFactory();
    }    // getReadWritableFactory()

    /**
     * Indicates whether some other object is "equal to" this one.  An object is
     * equal to this volunteer only if all the following conditions hold:
     * <ol>
     * <li>the object is another shift,</li>
     * <li>both shifts have the same description,</li>
     * <li>both shifts have the same volunteer assigned (as determined by the
     * {@link Volunteer#equals(Object)} method or both shifts are open .</li>
     * </ol>
     *
     * @param obj the object with which to compare
     * @return true if the objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Shift)) {
            return false;
        }

        Shift rhs = (Shift) obj;
        boolean volunteersAreEqual = (volunteer == null) ? (rhs.volunteer == null) : volunteer.equals(rhs.volunteer);
        return description.equals(rhs.description) && volunteersAreEqual;
    }

    @Override
    public int hashCode() {
        final int SEED = 5;
        final int MULTIPLIER = 37;
        int code = SEED;
        code = code * MULTIPLIER + description.hashCode();
        code = code * MULTIPLIER + ((volunteer == null) ? 0 : volunteer.hashCode());
        return code;
    }

    /**
     * Creates and returns a copy of this shift.
     *
     * @return a copy of this shift
     * @since 2.0
     */
    @Override
    public Shift clone() {
        Shift clone = null;
        try {
            clone = (Shift) super.clone();
        } catch (CloneNotSupportedException e) {    // try
            // We should never get here.  Shift's only superclass is Object, which shouldn't throw.
            assert false;
        }
        return clone;
    }

    /**
     * Returns a string representation of the shift.  This method is equivalent
     * to {@link #getDescription()}.
     *
     * @return a string representation of the shift
     */
    @Override
    public String toString() {
        assertInvariant();
        return description;
    }

    /**
     * Asserts the correctness of the internal state of the object.
     */
    private void assertInvariant() {
        assert (description != null);
        assert (volunteer != null);
    }
}