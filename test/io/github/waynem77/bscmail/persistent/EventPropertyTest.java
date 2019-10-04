/*
 * Copyright © 2016-2019 its authors.  See the file "AUTHORS" for details.
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

import io.github.waynem77.bscmail.ReadWritableFactory;
import io.github.waynem77.bscmail.ReadWritableTest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EventProperty}.
 *
 * @author Wayne Miller
 */
public class EventPropertyTest extends ReadWritableTest {

    /*
     * Class methods
     */

    /**
     * Returns the event property to be tested.
     * 
     * @return the event property to be tested
     */
    @Override
    protected EventProperty getReadWritable() {
        return new EventProperty("foo", "bar");
    }    // getReadWritable()


    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that
     * {@link EventProperty#EventProperty(java.lang.String, java.lang.String)}
     * throws a {@link NullPointerException} when name is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenNameIsNull() {
        String name = null;
        String defaultValue = "bar";

        EventProperty eventProperty = new EventProperty(name, defaultValue);
    }    // constructorThrowsExceptionWhenNameIsNull()

    /**
     * Tests that
     * {@link EventProperty#EventProperty(java.lang.String, java.lang.String)}
     * does not throw an exception when name is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNameIsEmpty() {
        String name = "";
        String defaultValue = "bar";

        EventProperty eventProperty = new EventProperty(name, defaultValue);
    }    // constructorDoesNotThrowExceptionWhenNameIsEmpty()

    /**
     * Tests that
     * {@link EventProperty#EventProperty(java.lang.String, java.lang.String)}
     * throws a {@link NullPointerException} when defaultValue is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenDefaultValueIsNull() {
        String name = "foo";
        String defaultValue = null;

        EventProperty eventProperty = new EventProperty(name, defaultValue);
    }    // constructorThrowsExceptionWhenDefaultValueIsNull()

    /**
     * Tests that
     * {@link EventProperty#EventProperty(java.lang.String, java.lang.String)}
     * does not throw an exception when defaultValue is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenDefaultValueIsEmpty() {
        String name = "foo";
        String defaultValue = "";

        EventProperty eventProperty = new EventProperty(name, defaultValue);
    }    // constructorDoesNotThrowExceptionWhenDefaultValueIsEmpty()

    /**
     * Tests that
     * {@link EventProperty#EventProperty(java.lang.String, java.lang.String)}
     * does not throw an exception when neither parameter is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNoParamIsNull() {
        String name = "foo";
        String defaultValue = "bar";

        EventProperty eventProperty = new EventProperty(name, defaultValue);
    }    // constructorDoesNotThrowExceptionWhenNoParamIsNull()

    /* getPropertyName */

    /**
     * Tests that {@link EventProperty#getPropertyName()} does not throw an
     * exception.
     */
    @Test
    public void getPropertyNameDoesNotThrowException() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        eventProperty.getPropertyName();
    }    // getPropertyNameDoesNotThrowException()

    /**
     * Tests that {@link EventProperty#getPropertyName()} returns the value
     * passed to the constructor.
     */
    @Test
    public void getPropertyNameReturnsCorrectValue() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        String received = eventProperty.getPropertyName();

        String expected = name;
        assertEquals(expected, received);
    }    // getPropertyNameReturnsCorrectValue()

    /* getDefaultValue */

    /**
     * Tests that {@link EventProperty#getDefaultValue()} does not throw an
     * exception.
     */
    @Test
    public void getDefaultValueDoesNotThrowException() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        eventProperty.getDefaultValue();
    }    // getDefaultValueDoesNotThrowException()

    /**
     * Tests that {@link EventProperty#getDefaultValue()} returns the value
     * passed to the constructor.
     */
    @Test
    public void getDefaultValueReturnsCorrectValue() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        String received = eventProperty.getDefaultValue();

        String expected = defaultValue;
        assertEquals(expected, received);
    }    // getDefaultValueReturnsCorrectValue()

    /* getValue / setValue */

    /**
     * Tests that {@link EventProperty#setValue(java.lang.String)} throws a
     * {@link NullPointerException} when value is null.
     */
    @Test(expected = NullPointerException.class)
    public void setValueThrowsExceptionWhenValueIsNull() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        String value = null;
        eventProperty.setValue(value);
    }    // setValueThrowsExceptionWhenValueIsNull()

    /**
     * Tests that {@link EventProperty#setValue(java.lang.String)} does not
     * throw an exception when value is empty.
     */
    @Test
    public void setValueDoesNotThrowExceptionWhenValueIsEmpty() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        String value = "";
        eventProperty.setValue(value);
    }    // setValueDoesNotThrowExceptionWhenValueIsEmpty()

    /**
     * Tests that {@link EventProperty#setValue(java.lang.String)} does not
     * throw an exception when value is not null nor empty.
     */
    @Test
    public void setValueDoesNotThrowExceptionWhenValueIsNotNull() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        String value = "baz";
        eventProperty.setValue(value);
    }    // setValueDoesNotThrowExceptionWhenValueIsNotNull()

    /**
     * Tests that {@link EventProperty#getValue()} does not throw an exception.
     */
    @Test
    public void getValueDoesNotThrowException() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        eventProperty.getValue();
    }    // getValueDoesNotThrowException()

    /**
     * Tests that {@link EventProperty#getValue()} returns an empty string when
     * called from a new object.
     */
    @Test
    public void getValueInitiallyReturnsEmptyString() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        String received = eventProperty.getValue();

        String expected = "";
        assertEquals(expected, received);
    }    // getValueInitiallyReturnsEmptyString()

    /**
     * Tests that {@link EventProperty#getValue()} returns the value passed to
     * {@link EventProperty#setValue(java.lang.String)}.
     */
    @Test
    public void getValueReturnsValuePassedToSetValue() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        String received = eventProperty.getValue();

        String expected = value;
        assertEquals(expected, received);
    }    // getValueReturnsValuePassedToSetValue()

    /* getReadWritableProperties */
    
    /**
     * Tests that {@link EventProperty#getReadWritableProperties()} returns the
     * correct value.
     */
    @Test
    public void getReadWritablePropertiesReturnsTheCorrectValue() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        Map<String, Object> received = eventProperty.getReadWritableProperties();

        Map<String, Object> expected = new HashMap<>();
        expected.put("name", name);
        expected.put("defaultValue", defaultValue);
        expected.put("value", value);
        assertEquals(expected, received);
    }    // Test()
    
    /**
     * Tests that the return value of
     * {@link EventProperty#getReadWritableProperties()} has the correct
     * iteration order.
     */
    @Test
    public void getReadWritablePropertiesHasTheCorrectIterationOrder() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        Map<String, Object> properties = eventProperty.getReadWritableProperties();
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for

        List<String> expected = Arrays.asList("name", "defaultValue", "value");
        assertEquals(expected, received);
    }    // getReadWritablePropertiesHasTheCorrectIterationOrder()

    /* equals */

    /**
     * Tests that {@link EventProperty#equals(Object)} does not throw an
     * exception when the argument is null.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNull() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        Object obj = null;
        eventProperty.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNull()
    
    /**
     * Tests that {@link EventProperty#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNull() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        Object obj = null;
        boolean received = eventProperty.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNull()
    
    /**
     * Tests that {@link EventProperty#equals(Object)} does not throw an
     * exception when the argument is not a event property.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNotEventProperty() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        Object obj = 1;
        eventProperty.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNotEventProperty()
    
    /**
     * Tests that {@link EventProperty#equals(Object)} returns false when the
     * argument is not a event property.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNotEventProperty() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        Object obj = 1;
        boolean received = eventProperty.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNotEventProperty()
    
    /**
     * Tests that {@link EventProperty#equals(Object)} does not throw an
     * exception when the argument is a event property.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsEventProperty() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        Object obj = new EventProperty(name, defaultValue);
        eventProperty.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsEventProperty()
    
    /**
     * Tests that {@link EventProperty#equals(Object)} returns false when the
     * argument has a different name than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentName() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        EventProperty obj = new EventProperty(name + "X", defaultValue);
        boolean received = eventProperty.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentName()

    /**
     * Tests that {@link EventProperty#equals(Object)} returns false when the
     * argument has a different default value than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentDefaultValue() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        EventProperty obj = new EventProperty(name, defaultValue + "X");
        boolean received = eventProperty.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentDefaultValue()

    /**
     * Tests that {@link EventProperty#equals(Object)} returns false when the
     * argument has a different value than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentValue() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        EventProperty obj = new EventProperty(name, defaultValue);
        obj.setValue(value + "X");
        boolean received = eventProperty.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentValue()

    /**
     * Tests that {@link EventProperty#equals(Object)} returns true when the
     * argument has identical properties.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentHasSameNameAndDefaultValue() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        EventProperty obj = new EventProperty(name, defaultValue);
        obj.setValue(value);
        boolean received = eventProperty.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentHasSameNameAndDefaultValue()

    /**
     * Tests that {@link EventProperty#equals(Object)} returns true when the
     * argument is the same object as the caller.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsIdentical() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        EventProperty obj = eventProperty;
        boolean received = eventProperty.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsIdentical()

    /* hashCode */
    
    /**
     * Tests that {@link EventProperty#hashCode()} does not throw an exception.
     */
    @Test
    public void hashCodeDoesNotThrowException() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        eventProperty.hashCode();
    }    // hashCodeDoesNotThrowException()

    /**
     * Tests that {@link EventProperty#hashCode()} returns equal values for
     * equal volunteers.
     */
    @Test
    public void hashCodeReturnsEqualValuesForEquivalentVolunteers() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty1 = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty1.setValue(value);
        EventProperty eventProperty2 = new EventProperty(name, defaultValue);
        eventProperty2.setValue(value);

        int expected = eventProperty1.hashCode();
        int received = eventProperty2.hashCode();
        assertEquals(expected, received);
    }    // hashCodeReturnsEqualValuesForEquivalentVolunteers()

    /* clone */
    
    /**
     * Tests that {@link EventProperty#clone()} does not throw an exception.
     */
    @Test
    public void cloneDoesNotThrowException() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        eventProperty.clone();
    }    // cloneDoesNotThrowException()
    
    /**
     * Tests that the return value of {@link EventProperty#clone()} is not null.
     */
    @Test
    public void cloneDoesNotReturnNull() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        EventProperty received = eventProperty.clone();

        assertNotNull(received);
    }    // testCloneNcloneDoesNotReturnNullotNull()
    
    /**
     * Tests that the return value of {@link EventProperty#clone()} is equal to the
     * argument.
     */
    @Test
    public void returnValueOfCloneIsEqualToOriginal() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        EventProperty received = eventProperty.clone();

        EventProperty expected = eventProperty;
        assertEquals(expected, received);
    }    // returnValueOfCloneIsEqualToOriginal()
    
    /**
     * Tests that the return value of {@link EventProperty#clone()} is not identical
     * to the argument.
     */
    @Test
    public void returnValueOfCloneIsNotIdenticalToOriginal() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        EventProperty received = eventProperty.clone();

        assertFalse(eventProperty == received);
    }    // returnValueOfCloneIsNotIdenticalToOriginal()

    /* toString */
    
    /**
     * Tests that {@link EventProperty#toString()} does not throw an exception.
     */
    @Test
    public void toStringDoesNotThrowException() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        eventProperty.toString();
    }    // toStringDoesNotThrowException()

    /**
     * Tests that the return value of {@link EventProperty#toString()} is not
     * null.
     */
    @Test
    public void toStringDoesNotReturnNull() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        String received = eventProperty.toString();

        assertNotNull(received);
    }    // toStringDoesNotReturnNull()
    
    /**
     * Tests that {@link EventProperty#toString()} returns the eventProperty's
     * name.
     */
    @Test
    public void toStringReturnsName() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        String value = "baz";
        eventProperty.setValue(value);

        String received = eventProperty.toString();

        String expected = name;
        assertEquals(expected, received);
    }    // toStringReturnsName()

    /* getVolunteerFactory */
    
    /**
     * Tests that {@link EventProperty#getEventPropertyFactory()} does not throw
     * an exception.
     */
    @Test
    public void volunteerFactoryDoesNotThrowException() {
        EventProperty.getEventPropertyFactory();
    }    // volunteerFactoryDoesNotThrowException()
    
    /**
     * Tests that the return value of
     * {@link EventProperty#getVolunteerFactory()} is not null.
     */
    @Test
    public void volunteerFactoryDoesNotReturnNull() {
        ReadWritableFactory factory = EventProperty.getEventPropertyFactory();
        assertNotNull(factory);
    }    // volunteerFactoryDoesNotReturnNull()

}    // EventPropertyTest
