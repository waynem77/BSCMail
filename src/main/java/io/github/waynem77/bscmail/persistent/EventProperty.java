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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an Event Property.
 *
 * @author Chaitra Mayya
 * @since 3.0
 */
public class EventProperty implements Cloneable, Serializable, ReadWritable {

    /*
     * Static class properties and methods.
     */

    /**
     * Class version number.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A factory that creates an {@link Event Property} from a set of
     * read-writable properties. These properties may be extracted directly from
     * an Event Property via {@link EventProperty#getReadWritableProperties()},
     * but more typically are extracted from a disk file.
     *
     * To obtain an Event Property factory, use the method
     * {@link EventProperty#getEventPropertyFactory()}.
     *
     * @author Chaitra Mayya
     * @since 3.0
     */
    public static class Factory implements ReadWritableFactory<EventProperty> {

        /**
         * Constructs a new EventProperty factory.
         */
        Factory() {
        }    // Factory()

        /**
         * Constructs an event property from the given read-writable properties.
         * If the factory is unable to create an event property from the given
         * properties, this method returns null.
         *
         * The event property factory constructs an event property using the
         * following information from the given properties.
         * <ul>
         * <li>The event property's name is given by the string value of the
         * value corresponding to "name". If such a value does not exist or is
         * null, the event property's description is empty.</li>
         * <li>The event property's default value is given by the string value
         * of the value corresponding to "defaultValue". If such a value does
         * not exist or is null, the event property's description is empty.</li>
         * <li>The event property's value is given by the string value of the
         * value corresponding to "value". If such a value does not exist or is
         * null, the event property's value is empty.</li>
         * </ul>
         * This method effectively acts as the reverse of
         * {@link EventProperty#getReadWritableProperties()}.
         *
         * @param properties the read-writable properties; may not be null
         * @return an event property constructed from the given properties, or
         * null if the factory is unable to construct an event property
         * @throws NullPointerException if {@code properties} is null
         */
        @Override
        public EventProperty constructReadWritable(Map<String, Object> properties) {
            if (properties == null) {
                throw new NullPointerException("properties may not be null");
            }    // if

            EventProperty eventProperty = null;
            Object nameObject = properties.get("name");
            String name = (nameObject != null) ? nameObject.toString() : "";

            Object defaultValueObject = properties.get("defaultValue");
            String defaultValue = (defaultValueObject != null) ? defaultValueObject.toString() : "";

            Object valueObject = properties.get("value");
            String value = (valueObject != null) ? valueObject.toString() : "";

            eventProperty = new EventProperty(name, defaultValue);
            eventProperty.setValue(value);

            return eventProperty;
        }    // constructReadWritable()
    }    // Factory

    /**
     * Returns a factory that creates event properties from read-writable
     * property maps. This factory effectively reverses the actions of
     * {@link EventProperty#getReadWritableProperties()}.
     *
     * @return a factory that creates event properties from read-writable
     * property maps
     */
    public static Factory getEventPropertyFactory() {
        return new Factory();
    }    // getEventPropertyFactory();

    /*
     * Instance properties and methods.
     */

    /**
     * The event property's name.
     */
    private final String name;

    /**
     * The event property's default value.
     */
    private final String defaultValue;

    /**
     * The event property's value.
     */
    private String value;

    /**
     * Constructs a new event property.
     *
     * @param name name of the event property; may not be null
     * @param defaultValue default value of the event property; may not be null
     * @throws NullPointerException if {@code name} is null
     */
    public EventProperty(String name, String defaultValue) {
        if (name == null) {
            throw new NullPointerException("name may not be null");
        }    // if
        if (defaultValue == null) {
            throw new NullPointerException("defaultValue may not be null");
        }    // if

        this.name = name;
        this.defaultValue = defaultValue;
        this.value = "";
        assertInvariant();
    }    // EventProperty()

    /**
     * Returns the event property's name.
     *
     * @return the event property's name.
     */
    public String getPropertyName() {
        assertInvariant();
        return name;
    }    // getPropertyName()

    /**
     * Returns the event property's default value.
     *
     * @return the event property's default value.
     */
    public String getDefaultValue() {
        assertInvariant();
        return defaultValue;
    }    // getDefaultValue()

    /**
     * Returns the event property's value.
     *
     * @return the event property's value.
     */
    public String getValue() {
        assertInvariant();
        return value;
    }    // getValue()

    /**
     * Sets the event property's value to the given string.
     *
     * @param value the new value; may not be null
     * @throws NullPointerException if {@code value} is null
     */
    public void setValue(String value) {
        assertInvariant();
        if (value == null) {
            throw new NullPointerException("value may not be null");
        }    // if

        this.value = value;
        assertInvariant();
    }    // getValue()

    /**
     * Returns a map containing the read-writable properties of the event
     * property. The map returned by this method is guaranteed to have the
     * following properties.
     * <ul>
     * <li>The map has a key "name". The value for this key is a non-null
     * {@link String} equal to the return value of
     * {@link #getPropertyName()}.</li>
     * <li>The map has a key "defaultValue". The value for this key is a
     * non-null {@link String} equal to the return value of
     * {@link #getDefaultValue()}.</li>
     * <li>The map has a key "value". The value for this key is a non-null
     * {@link String} equal to the return value of {@link #getValue()}.
     * <li>The iteration order of the elements is fixed in the order the keys
     * are presented above.</li>
     * </ul>
     *
     * @return a map containing the read-writable properties of the event
     * property
     */
    @Override
    public Map<String, Object> getReadWritableProperties() {
        assertInvariant();
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);
        return properties;
    }    // getReadWritableProperties()

    /**
     * Returns a factory that creates event properties from read-writable
     * property maps.
     *
     * @return a factory that creates event properties from read-writable
     * property maps
     */
    @Override
    public Factory getReadWritableFactory() {
        assertInvariant();
        return EventProperty.getEventPropertyFactory();
    }    // getReadWritableFactory()

    /**
     * Indicates whether some other object is "equal to" this one. An object is
     * equal to this event property only if all the following conditions hold:
     * <ol>
     * <li>the object is another event property,</li>
     * <li>both event properties have the same name,</li>
     * <li>both event properties have the same default value, and</li>
     * <li>both event properties have the same value.</li>
     * </ol>
     *
     * @param obj the object with which to compare
     * @return true if the objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        assertInvariant();
        if (obj == null) {
            return false;
        }    // if
        if (!(obj instanceof EventProperty)) {
            return false;
        }    // if

        EventProperty rhs = (EventProperty) obj;

        return name.equals(rhs.name) && defaultValue.equals(rhs.defaultValue) && value.equals(rhs.value);
    }    // equals()

    @Override
    public int hashCode() {
        assertInvariant();
        final int SEED = 5;
        final int MULTIPLIER = 37;
        int code = SEED;
        code = code * MULTIPLIER + name.hashCode();
        code = code * MULTIPLIER + defaultValue.hashCode();
        code = code * MULTIPLIER + value.hashCode();
        return code;
    }    // hashCode()

    /**
     * Creates and returns a copy of this event property.
     *
     * @return a copy of this event property
     */
    @Override
    public EventProperty clone() {
        assertInvariant();
        EventProperty clone = null;
        try {
            clone = (EventProperty) super.clone();
        } catch (CloneNotSupportedException e) {    // try
            // We should never get here.  EventProperty's only superclass is Object,
            // which shouldn't throw.
            assert (false);
        }    // catch
        return clone;
    }    // clone()

    /**
     * Returns a string representation of the event property. This method is
     * equivalent to {@link #getPropertyName()}.
     *
     * @return a string representation of the event property
     */
    @Override
    public String toString() {
        assertInvariant();
        return name;
    }    // toString()

    /**
     * Asserts the correctness of the internal state of the object.
     */
    private void assertInvariant() {
        assert (name != null);
        assert (defaultValue != null);
        assert (value != null);
    }    // assertInvariant()
}    // EventProperty
