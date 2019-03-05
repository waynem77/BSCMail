/*
 * Copyright © 2019 its authors.  See the file "AUTHORS" for details.
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import main.ReadWritableFactory;
import main.ReadWritableTest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EmailServerProperties}.
 *
 * @author Wayne Miller
 */
public class EmailServerPropertiesTest extends ReadWritableTest {

    /*
     * Class methods and properties
     */

    /**
     * Hostname variable for testing.
     */
    private String hostname;

    /**
     * Port variable for testing.
     */
    private String port;

    /**
     * Username variable for testing.
     */
    private String username;

    /**
     * Returns the EmailServerProperties object to be tested.
     *
     * @return the EmailServerProperties object to be tested
     */
    @Override
    protected EmailServerProperties getReadWritable() {
        return new EmailServerProperties("foo", "1234", "baz");
    }    // getReadWritable()

    /**
     * Constructs and returns an EmailServerProperties object created from the
     * class variables {@link #hostname}, {@link #port}, and {@link #username}.
     * This method may be used to test the constructor or in general tests.
     *
     * @return an EmailServerProperties object created from the class variables
     */
    protected EmailServerProperties makeEmailServerPropertiesFromClassVariables(){
        return new EmailServerProperties(hostname, port, username);
    }    // makeEmailServerPropertiesFromClassVariables

    /**
     * Populates the class properties used as constructor arguments before each
     * test.
     */
    @Before
    public void populateConstructorVariables() {
        hostname = "foo";
        port = "1234";
        username = "baz";
    }    // populateConstructorVariables()

    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that
     * {@link EmailServerProperties#EmailServerProperties(java.lang.String, java.lang.String, java.lang.String}
     * throws a {@link NullPointerException} when hostname is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenHostnameIsNull() {
        hostname = null;

        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();
    }    // constructorThrowsExceptionWhenHostnameIsNull()

    /**
     * Tests that
     * {@link EmailServerProperties#EmailServerProperties(java.lang.String, java.lang.String, java.lang.String}
     * does not throw an exception when hostname is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenHostnameIsEmpty() {
        hostname = "";

        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenHostnameIsEmpty()

    /**
     * Tests that
     * {@link EmailServerProperties#EmailServerProperties(java.lang.String, java.lang.String, java.lang.String}
     * throws a {@link NullPointerException} when port is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenPortIsNull() {
        port = null;

        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();
    }    // constructorThrowsExceptionWhenPortIsNull()

    /**
     * Tests that
     * {@link EmailServerProperties#EmailServerProperties(java.lang.String, java.lang.String, java.lang.String}
     * does not throw an exception when port is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenPortIsEmpty() {
        port = "";

        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenPortIsEmpty()

    /**
     * Tests that
     * {@link EmailServerProperties#EmailServerProperties(java.lang.String, java.lang.String, java.lang.String}
     * throws a {@link NullPointerException} when username is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenUsernameIsNull() {
        username = null;

        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();
    }    // constructorThrowsExceptionWhenUsernameIsNull()

    /**
     * Tests that
     * {@link EmailServerProperties#EmailServerProperties(java.lang.String, java.lang.String, java.lang.String}
     * does not throw an exception when username is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenUsernameIsEmpty() {
        username = "";

        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenUsernameIsEmpty()

    /**
     * Tests that
     * {@link EmailServerProperties#EmailServerProperties(bscmail.EmailServerProperties.SendType, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * does not throw an exception when no parameter is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNoParamIsNull() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenNoParamIsNull()

    /* getHostname */

    /**
     * Tests that {@link EmailServerProperties#getHostname()} does not throw an
     * exception.
     */
    @Test
    public void getHostnameDoesNotThrowException() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        emailServerProperties.getHostname();
    }    // getHostnameDoesNotThrowException()

    /**
     * Tests that {@link EmailServerProperties#getHostname()} returns the value
     * passed to the constructor.
     */
    @Test
    public void getHostnameReturnsCorrectValue() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        String received = emailServerProperties.getHostname();

        String expected = hostname;
        assertEquals(expected, received);
    }    // getHostnameReturnsCorrectValue()

    /* getPort */

    /**
     * Tests that {@link EmailServerProperties#getPort()} does not throw an
     * exception.
     */
    @Test
    public void getPortDoesNotThrowException() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        emailServerProperties.getPort();
    }    // getPortDoesNotThrowException()

    /**
     * Tests that {@link EmailServerProperties#getPort()} returns the value
     * passed to the constructor.
     */
    @Test
    public void getPortReturnsCorrectValue() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        String received = emailServerProperties.getPort();

        String expected = port;
        assertEquals(expected, received);
    }    // getPortReturnsCorrectValue()

    /* getUsername */

    /**
     * Tests that {@link EmailServerProperties#getUsername()} does not throw an
     * exception.
     */
    @Test
    public void getUsernameDoesNotThrowException() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        emailServerProperties.getUsername();
    }    // getUsernameDoesNotThrowException()

    /**
     * Tests that {@link EmailServerProperties#getUsername()} returns the value
     * passed to the constructor.
     */
    @Test
    public void getUsernameReturnsCorrectValue() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        String received = emailServerProperties.getUsername();

        String expected = username;
        assertEquals(expected, received);
    }    // getUsernameReturnsCorrectValue()

    /* getReadWritableProperties */

    /**
     * Tests that {@link EmailServerProperties#getReadWritableProperties()}
     * returns the correct value.
     */
    @Test
    public void getReadWritablePropertiesReturnsTheCorrectValue() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        Map<String, Object> received = emailServerProperties.getReadWritableProperties();

        Map<String, Object> expected = new HashMap<>();
        expected.put("hostname", hostname);
        expected.put("port", port);
        expected.put("username", username);
        assertEquals(expected, received);
    }    // getReadWritablePropertiesReturnsTheCorrectValue()

    /**
     * Tests that the return value of
     * {@link EmailServerProperties#getReadWritableProperties()} has the correct
     * iteration order.
     */
    @Test
    public void getReadWritablePropertiesHasTheCorrectIterationOrder() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        Map<String, Object> properties = emailServerProperties.getReadWritableProperties();
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for

        List<String> expected = Arrays.asList("hostname", "port", "username");
        assertEquals(expected, received);
    }    // getReadWritablePropertiesHasTheCorrectIterationOrder()

    /* equals */

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} does not throw an
     * exception when the argument is null.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNull() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        Object obj = null;
        emailServerProperties.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNull()

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} returns false
     * when the argument is null.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNull() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        Object obj = null;
        boolean received = emailServerProperties.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNull()

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} does not throw an
     * exception when the argument is not an EmailServerProperties object.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNotEmailServerProperties() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        Object obj = 1;
        emailServerProperties.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNotEmailServerProperties()

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} returns false
     * when the argument is not an EmailServerProperties.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNotEmailServerProperties() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        Object obj = 1;
        boolean received = emailServerProperties.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNotEmailServerProperties()

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} does not throw an
     * exception when the argument is an EmailServerProperties objet.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsEmailServerProperties() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        Object obj = makeEmailServerPropertiesFromClassVariables();
        emailServerProperties.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsEmailServerProperties()

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} returns false
     * when the argument has a different hostname than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentHostname() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        hostname += "X";
        EmailServerProperties obj = makeEmailServerPropertiesFromClassVariables();
        boolean received = emailServerProperties.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentHostname()

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} returns false
     * when the argument has a different port than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentPort() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        port += "1";
        EmailServerProperties obj = makeEmailServerPropertiesFromClassVariables();
        boolean received = emailServerProperties.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentPort()

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} returns false
     * when the argument has a different username than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentUsername() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        username += "X";
        EmailServerProperties obj = makeEmailServerPropertiesFromClassVariables();
        boolean received = emailServerProperties.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentUsername()

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} returns true when
     * the argument has identical properties.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentHasEqualProperties() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        EmailServerProperties obj = makeEmailServerPropertiesFromClassVariables();
        boolean received = emailServerProperties.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentHasEqualProperties()

    /**
     * Tests that {@link EmailServerProperties#equals(Object)} returns true when
     * the argument is the same object as the caller.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsIdentical() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        EmailServerProperties obj = emailServerProperties;
        boolean received = emailServerProperties.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsIdentical()

    /* hashCode */

    /**
     * Tests that {@link EmailServerProperties#hashCode()} does not throw an
     * exception.
     */
    @Test
    public void hashCodeDoesNotThrowException() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        emailServerProperties.hashCode();
    }    // hashCodeDoesNotThrowException()

    /**
     * Tests that {@link EmailServerProperties#hashCode()} returns equal values
     * for equal email templates.
     */
    @Test
    public void hashCodeReturnsEqualValuesForEquivalentVolunteers() {
        EmailServerProperties emailServerProperties1 = makeEmailServerPropertiesFromClassVariables();
        EmailServerProperties emailServerProperties2 = makeEmailServerPropertiesFromClassVariables();

        int expected = emailServerProperties1.hashCode();
        int received = emailServerProperties2.hashCode();
        assertEquals(expected, received);
    }    // hashCodeReturnsEqualValuesForEquivalentVolunteers()

    /* clone */

    /**
     * Tests that {@link EmailServerProperties#clone()} does not throw an
     * exception.
     */
    @Test
    public void cloneDoesNotThrowException() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        emailServerProperties.clone();
    }    // cloneDoesNotThrowException()

    /**
     * Tests that the return value of {@link EmailServerProperties#clone()} is
     * not null.
     */
    @Test
    public void cloneDoesNotReturnNull() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        EmailServerProperties received = emailServerProperties.clone();

        assertNotNull(received);
    }    // testCloneNcloneDoesNotReturnNullotNull()

    /**
     * Tests that the return value of {@link EmailServerProperties#clone()} is
     * equal to the argument.
     */
    @Test
    public void returnValueOfCloneIsEqualToOriginal() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        EmailServerProperties received = emailServerProperties.clone();

        EmailServerProperties expected = emailServerProperties;
        assertEquals(expected, received);
    }    // returnValueOfCloneIsEqualToOriginal()

    /**
     * Tests that the return value of {@link EmailServerProperties#clone()} is
     * not identical to the argument.
     */
    @Test
    public void returnValueOfCloneIsNotIdenticalToOriginal() {
        EmailServerProperties emailServerProperties = makeEmailServerPropertiesFromClassVariables();

        EmailServerProperties received = emailServerProperties.clone();

        assertFalse(emailServerProperties == received);
    }    // returnValueOfCloneIsNotIdenticalToOriginal()

    /* getEmailServerPropertiesFactory */

    /**
     * Tests that
     * {@link EmailServerProperties#getEmailServerPropertiesFactory()} does not
     * throw an exception.
     */
    @Test
    public void getEmailServerPropertiesFactoryDoesNotThrowException() {
        EmailServerProperties.getEmailServerPropertiesFactory();
    }    // getEmailServerPropertiesFactoryDoesNotThrowException()

    /**
     * Tests that the return value of
     * {@link EmailServerProperties#getVolunteerFactory()} is not null.
     */
    @Test
    public void getEmailServerPropertiesFactoryDoesNotReturnNull() {
        ReadWritableFactory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        assertNotNull(factory);
    }    // getEmailServerPropertiesFactoryDoesNotReturnNull()

}
