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

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EmailServerProperties.Factory}.
 *
 * @author Wayne Miller
 */
public class EmailServerPropertiesFactoryTest {

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
     * Properties map used in testing.
     */
    private Map<String, Object> properties;

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
     * Initializes the class variables before each test.
     */
    @Before
    public void initializeClassVariables() {
        hostname = "foo";
        port = "1234";
        username = "baz";
        properties = new HashMap<>();
        properties.put("hostname", hostname);
        properties.put("port", port);
        properties.put("username", username);
    }    // initializeClassVariables()

    /*
     * Unit tests
     */

    /* constructReadWritable */

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} throws a
     * {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructReadWritableThrowsExceptionWhenPropertiesIsNull() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties = null;

        factory.constructReadWritable(properties);
    }    // constructReadWritableThrowsExceptionWhenPropertiesIsNull()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties = new HashMap<>();

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * return null when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties = new HashMap<>();

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when properties is an empty map.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesIsEmpty() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties = new HashMap<>();

        EmailServerProperties received = factory.constructReadWritable(properties);

        hostname = "";
        port = "";
        username = "";
        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesIsEmpty()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties is missing "hostname".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingHostname() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.remove("hostname");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingHostname()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * return null when properties is missing "hostname".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingHostname() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.remove("hostname");

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingHostname()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when properties is missing "hostname".
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesIsMissingHostname() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.remove("hostname");

        EmailServerProperties received = factory.constructReadWritable(properties);

        hostname = "";
        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesIsMissingHostname()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has a null value for "hostname".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullHostname() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        hostname = null;
        properties.put("hostname", hostname);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullHostname()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * return null when properties has a null value for "hostname".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullhenPropertiesHasNullHostname() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        hostname = null;
        properties.put("hostname", hostname);

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullhenPropertiesHasNullHostname()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when properties has a null value for "hostname".
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasNullHostname() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        hostname = null;
        properties.put("hostname", hostname);

        EmailServerProperties received = factory.constructReadWritable(properties);

        hostname = "";
        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasNullHostname()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties is missing "port".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingPort() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.remove("port");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingPort()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * return null when properties is missing "port".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingPort() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.remove("port");

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingPort()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when properties is missing "port".
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesIsMissingPort() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.remove("port");

        EmailServerProperties received = factory.constructReadWritable(properties);

        port = "";
        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesIsMissingPort()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has a null value for "port".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullPort() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        port = null;
        properties.put("port", port);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullPort()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * return null when properties has a null value for "port".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullhenPropertiesHasNullPort() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        port = null;
        properties.put("port", port);

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullhenPropertiesHasNullPort()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when properties has a null value for "port".
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasNullPort() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        port = null;
        properties.put("port", port);

        EmailServerProperties received = factory.constructReadWritable(properties);

        port = "";
        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasNullPort()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties is missing "username".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingUsername() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.remove("username");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingUsername()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * return null when properties is missing "username".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingUsername() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.remove("username");

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingUsername()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when properties is missing "username".
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesIsMissingUsername() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.remove("username");

        EmailServerProperties received = factory.constructReadWritable(properties);

        username = "";
        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesIsMissingUsername()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has a null value for "username".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullUsername() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        username = null;
        properties.put("username", username);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullUsername()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * return null when properties has a null value for "username".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullhenPropertiesHasNullUsername() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        username = null;
        properties.put("username", username);

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullhenPropertiesHasNullUsername()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when properties has a null value for "username".
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasNullUsername() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        username = null;
        properties.put("username", username);

        EmailServerProperties received = factory.constructReadWritable(properties);

        username = "";
        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasNullUsername()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has all required values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * return null when properties has all required values.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasAllValues() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasAllValues()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when properties has all required values.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasAllValues() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();

        EmailServerProperties received = factory.constructReadWritable(properties);

        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasAllValues()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has extraneous values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousValues() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.put("extra", "baz");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousValues()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * return null when properties has extraneous values.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousValues() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.put("extra", "baz");

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousValues()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when properties has extraneous values.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasExtraneousValues() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        properties.put("extra", "baz");

        EmailServerProperties received = factory.constructReadWritable(properties);

        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasExtraneousValues()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * throw an exception when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        Object hostnameObj = 2.0;
        Object portObj = Boolean.TRUE;
        Object usernameObj = new HashMap();
        properties = new HashMap<>();
        properties.put("hostname", hostnameObj);
        properties.put("port", portObj);
        properties.put("username", usernameObj);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} does not
     * returns null when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        Object hostnameObj = 2.0;
        Object portObj = Boolean.TRUE;
        Object usernameObj = new HashMap();
        properties = new HashMap<>();
        properties.put("hostname", hostnameObj);
        properties.put("port", portObj);
        properties.put("username", usernameObj);

        EmailServerProperties received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} returns
     * the correct value when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasWrongObjects() {
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        Object hostnameObj = 2.0;
        Object portObj = Boolean.TRUE;
        Object usernameObj = new HashMap();
        properties = new HashMap<>();
        properties.put("hostname", hostnameObj);
        properties.put("port", portObj);
        properties.put("username", usernameObj);

        EmailServerProperties received = factory.constructReadWritable(properties);

        hostname = hostnameObj.toString();
        port = portObj.toString();
        username = usernameObj.toString();
        EmailServerProperties expected = makeEmailServerPropertiesFromClassVariables();
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasWrongObjects()

    /**
     * Tests that
     * {@link EmailServerProperties.Factory#constructReadWritable(Map)} acts as
     * the reverse of {@link EmailServerProperties#getReadWritableProperties()}.
     */
    @Test
    public void constructReadWritableWorksReflexively() {
        EmailServerProperties originalEmailServerProperties = makeEmailServerPropertiesFromClassVariables();

        originalEmailServerProperties.getReadWritableProperties();
        EmailServerProperties.Factory factory = EmailServerProperties.getEmailServerPropertiesFactory();
        EmailServerProperties received = factory.constructReadWritable(properties);

        EmailServerProperties expected = originalEmailServerProperties;
        assertEquals(expected, received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

}
