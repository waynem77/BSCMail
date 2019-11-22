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

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Role}.
 *
 * @author Wayne Miller
 */
public class RoleTest extends ReadWritableTest {

    /*
     * Class methods
     */

    /**
     * Returns the event property to be tested.
     *
     * @return the event property to be tested
     */
    @Override
    protected Role getReadWritable() {
        return new Role("foo");
    }    // getReadWritable()


    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that {@link Role#Role(java.lang.String, java.lang.String)} throws a
     * {@link NullPointerException} when name is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenNameIsNull() {
        String name = null;

        Role role = new Role(name);
    }    // constructorThrowsExceptionWhenNameIsNull()

    /**
     * Tests that {@link Role#Role(java.lang.String, java.lang.String)} does not
     * throw an exception when name is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNameIsEmpty() {
        String name = "";

        Role role = new Role(name);
    }    // constructorDoesNotThrowExceptionWhenNameIsEmpty()

    /**
     * Tests that
     * {@link Role#Role(java.lang.String, java.lang.String)}
     * does not throw an exception when name is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNameIsNotNull() {
        String name = "foo";

        Role role = new Role(name);
    }    // constructorDoesNotThrowExceptionWhenNameIsNotNull()

    /* getName */

    /**
     * Tests that {@link Role#getName()} does not throw an exception.
     */
    @Test
    public void getNameDoesNotThrowException() {
        String name = "foo";
        Role role = new Role(name);

        role.getName();
    }    // getNameDoesNotThrowException()

    /**
     * Tests that {@link Role#getName()} returns the value passed to the
     * constructor.
     */
    @Test
    public void getNameReturnsCorrectValue() {
        String name = "foo";
        Role role = new Role(name);

        String received = role.getName();

        String expected = name;
        assertEquals(expected, received);
    }    // getNameReturnsCorrectValue()

    /* getReadWritableProperties */

    /**
     * Tests that {@link Role#getReadWritableProperties()} returns the correct
     * value.
     */
    @Test
    public void getReadWritablePropertiesReturnsTheCorrectValue() {
        String name = "foo";
        Role role = new Role(name);

        Map<String, Object> received = role.getReadWritableProperties();

        Map<String, Object> expected = new HashMap<>();
        expected.put("name", name);
        assertEquals(expected, received);
    }    // Test()

    /* matches */

    /**
     * Tests that {@link Role#matches(String)} throws a NullPointerException
     * when criterion is null.
     */
    @Test(expected = NullPointerException.class)
    public void matchesThrowsExceptionWhenCriterionIsNull() {
        String name = "Foobar Baz";
        Role role = new Role(name);
        String criterion = null;

        role.matches(criterion);
    }    // matchesThrowsExceptionWhenCriterionIsNull()

    /**
     * Tests that {@link Role#matches(String)} does not throws an exception when
     * criterion is not null.
     */
    @Test
    public void matchesThrowsExceptionWhenCriterionIsNotNull() {
        String name = "Foobar Baz";
        Role role = new Role(name);
        String criterion = "oba";

        role.matches(criterion);
    }    // matchesThrowsExceptionWhenCriterionIsNotNull()

    /**
     * Tests that {@link Role#matches(String)} return true when criterion is
     * empty.
     */
    @Test
    public void matchesReturnsTrueWhenCriterionIsEmpty() {
        String name = "Foobar Baz";
        Role role = new Role(name);
        String criterion = "";

        boolean received = role.matches(criterion);

        boolean expected = true;
        assertEquals(expected, received);
    }    // matchesReturnsTrueWhenCriterionIsEmpty()

    /**
     * Tests that {@link Role#matches(String)} returns true when criterion is a
     * subset of the name.
     */
    @Test
    public void matchesReturnsTrueWhenCriterionIsSubsetOfName() {
        String name = "Foobar Baz";
        Role role = new Role(name);
        String criterion = "oba";

        boolean received = role.matches(criterion);

        boolean expected = true;
        assertEquals(expected, received);
    }    // matchesReturnsTrueWhenCriterionIsSubsetOfName()

    /**
     * Tests that {@link Role#matches(String)} returns false when criterion is
     * not a subset of the name.
     */
    @Test
    public void matchesReturnsFalseWhenCriterionIsNotSubsetOfName() {
        String name = "Foobar Baz";
        Role role = new Role(name);
        String criterion = "xxx";

        boolean received = role.matches(criterion);

        boolean expected = false;
        assertEquals(expected, received);
    }    // matchesReturnsFalseWhenCriterionIsNotSubsetOfName()

    /**
     * Tests that {@link Role#matches(String)} is case-insensitive.
     */
    @Test
    public void matchesIsCaseInsensitive() {
        String name = "Foobar Baz";
        Role role = new Role(name);
        String criterion = "ObA";

        boolean received = role.matches(criterion);

        boolean expected = true;
        assertEquals(expected, received);
    }    // matchesIsCaseInsensitive()

    /* equals */

    /**
     * Tests that {@link Role#equals(Object)} does not throw an exception when
     * the argument is null.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNull() {
        String name = "foo";
        Role role = new Role(name);

        Object obj = null;
        role.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNull()

    /**
     * Tests that {@link Role#equals(Object)} returns false when the argument is
     * null.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNull() {
        String name = "foo";
        Role role = new Role(name);

        Object obj = null;
        boolean received = role.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNull()

    /**
     * Tests that {@link Role#equals(Object)} does not throw an exception when
     * the argument is not a role.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNotRole() {
        String name = "foo";
        Role role = new Role(name);

        Object obj = 1;
        role.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNotRole()

    /**
     * Tests that {@link Role#equals(Object)} returns false when the argument is
     * not a role.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNotRole() {
        String name = "foo";
        Role role = new Role(name);

        Object obj = 1;
        boolean received = role.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNotRole()

    /**
     * Tests that {@link Role#equals(Object)} does not throw an exception when
     * the argument is a role.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsRole() {
        String name = "foo";
        Role role = new Role(name);

        Object obj = new Role(name);
        role.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsRole()

    /**
     * Tests that {@link Role#equals(Object)} returns false when the argument
     * has a different name than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentName() {
        String name = "foo";
        Role role = new Role(name);

        Role obj = new Role(name + "X");
        boolean received = role.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentName()

    /**
     * Tests that {@link Role#equals(Object)} returns true when the
     * argument has identical properties.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentHasSameName() {
        String name = "foo";
        Role role = new Role(name);

        Role obj = new Role(name);
        boolean received = role.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentHasSameName()

    /**
     * Tests that {@link Role#equals(Object)} returns true when the argument is
     * the same object as the caller.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsIdentical() {
        String name = "foo";
        Role role = new Role(name);

        Role obj = role;
        boolean received = role.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsIdentical()

    /* hashCode */

    /**
     * Tests that {@link Role#hashCode()} does not throw an exception.
     */
    @Test
    public void hashCodeDoesNotThrowException() {
        String name = "foo";
        Role role = new Role(name);

        role.hashCode();
    }    // hashCodeDoesNotThrowException()

    /**
     * Tests that {@link Role#hashCode()} returns equal values for equal roles.
     */
    @Test
    public void hashCodeReturnsEqualValuesForEquivalentRoles() {
        String name = "foo";
        Role role1 = new Role(name);
        Role role2 = new Role(name);

        int expected = role1.hashCode();
        int received = role2.hashCode();
        assertEquals(expected, received);
    }    // hashCodeReturnsEqualValuesForEquivalentRoles()

    /* clone */

    /**
     * Tests that {@link Role#clone()} does not throw an exception.
     */
    @Test
    public void cloneDoesNotThrowException() {
        String name = "foo";
        Role role = new Role(name);

        role.clone();
    }    // cloneDoesNotThrowException()

    /**
     * Tests that the return value of {@link Role#clone()} is not null.
     */
    @Test
    public void cloneDoesNotReturnNull() {
        String name = "foo";
        Role role = new Role(name);

        Role received = role.clone();

        assertNotNull(received);
    }    // testCloneNcloneDoesNotReturnNullotNull()

    /**
     * Tests that the return value of {@link Role#clone()} is equal to the
     * argument.
     */
    @Test
    public void returnValueOfCloneIsEqualToOriginal() {
        String name = "foo";
        Role role = new Role(name);

        Role received = role.clone();

        Role expected = role;
        assertEquals(expected, received);
    }    // returnValueOfCloneIsEqualToOriginal()

    /**
     * Tests that the return value of {@link Role#clone()} is not identical to
     * the argument.
     */
    @Test
    public void returnValueOfCloneIsNotIdenticalToOriginal() {
        String name = "foo";
        Role role = new Role(name);

        Role received = role.clone();

        assertFalse(role == received);
    }    // returnValueOfCloneIsNotIdenticalToOriginal()

    /* toString */

    /**
     * Tests that {@link Role#toString()} does not throw an exception.
     */
    @Test
    public void toStringDoesNotThrowException() {
        String name = "foo";
        Role role = new Role(name);

        role.toString();
    }    // toStringDoesNotThrowException()

    /**
     * Tests that the return value of {@link Role#toString()} is not null.
     */
    @Test
    public void toStringDoesNotReturnNull() {
        String name = "foo";
        Role role = new Role(name);

        String received = role.toString();

        assertNotNull(received);
    }    // toStringDoesNotReturnNull()

    /**
     * Tests that {@link Role#toString()} returns the role's name.
     */
    @Test
    public void toStringReturnsName() {
        String name = "foo";
        Role role = new Role(name);

        String received = role.toString();

        String expected = name;
        assertEquals(expected, received);
    }    // toStringReturnsName()

    /* getRoleFactory */

    /**
     * Tests that {@link Role#getRoleFactory()} does not throw
     * an exception.
     */
    @Test
    public void roleFactoryDoesNotThrowException() {
        Role.getRoleFactory();
    }    // roleFactoryDoesNotThrowException()

    /**
     * Tests that the return value of
     * {@link Role#getRoleFactory()} is not null.
     */
    @Test
    public void roleFactoryDoesNotReturnNull() {
        ReadWritableFactory factory = Role.getRoleFactory();
        assertNotNull(factory);
    }    // roleFactoryDoesNotReturnNull()

}    // RoleTest
