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
package io.github.waynem77.bscmail.persistent;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the connection properties for an email server. The object has the following properties.
 * <ul>
 *   <li>hostname,</li>
 *   <li>port,</li>
 *   <li>username, and</li>
 *   <li>useTLS.</li>
 * </ul>
 *
 * useTLS is a boolean; the other properties are simple strings.
 *
 * @author wayne.miller
 * @since 3.4
 */
public class EmailServerProperties implements Cloneable, Serializable, ReadWritable {

    /* class properties and methods */

    /**
     * Class version number.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A factory that creates an {@link EmailServerProperties} from a set of
     * read-writable properties. These properties may be extracted directly from
     * an email template via
     * {@link EmailServerProperties#getReadWritableProperties()}, but more
     * typically are extracted from a disk file.
     *
     * To obtain an email template factory, use the method
     * {@link EmailServerProperties#getEmailServerPropertiesFactory()}.
     *
     * @author Wayne Miller
     */
    public static class Factory implements ReadWritableFactory<EmailServerProperties> {

        /**
         * Constructs an EmailServerProperties object from the given
         * read-writable properties. If the factory is unable to create an
         * EmailServerProperties object from the given properties, this method
         * returns null.
         *
         * The factory constructs an EmailServerProperties object using the
         * following information from the given properties.
         * <ul>
         *   <li>The EmailServerProperties object's hostname is given by the
         * string value of the value corresponding to "hostname". If such a
         * value does not exist or is null, the hostname is empty.</li>
         *   <li>The EmailServerProperties object's port is given by the string
         * value of the value corresponding to "port". If such a value does not
         * exist or is null, the port is empty.</li>
         *   <li>The EmailServerProperties object's username is given by the
         * string value of the value corresponding to "username". If such a
         * value does not exist or is null, the username is empty.</li>
         *   <li>The EmailServerProperties object's useTLS is given by the
         * boolean value of the value corresponding to "useTLS". If such a
         * value does not exist or is null, useTLS is set to false.</li>
         * </ul>
         * This method effectively acts as the reverse of
         * {@link EmailServerProperties#getReadWritableProperties()}.
         *
         * @param properties the read-writable properties; may not be null
         * @return an EmailServerProperties object constructed from the given
         * properties
         * @throws NullPointerException if {@code properties} is null
         */
        @Override
        public EmailServerProperties constructReadWritable(Map<String, Object> properties) {
            if (properties == null) {
                throw new NullPointerException("properties may not be null");
            }    // if

            Object hostnameObj = properties.get("hostname");
            String hostname = (hostnameObj != null) ? hostnameObj.toString() : "";

            Object portObj = properties.get("port");
            String port = (portObj != null) ? portObj.toString() : "";

            Object usernameObj = properties.get("username");
            String username = (usernameObj != null) ? usernameObj.toString() : "";

            Object useTLSObj = properties.get("useTLS");
            boolean useTLS = (useTLSObj != null) ? Boolean.parseBoolean(useTLSObj.toString()) : false;

            return new EmailServerProperties(hostname, port, username, useTLS);
        }    // constructReadWritable()

    }    // Factory

    /**
     * Returns a factory that creates email templates from read-writable
     * property maps.
     *
     * @return a factory that creates email templates from read-writable
     * property maps
     */
    public static Factory getEmailServerPropertiesFactory() {
        return new Factory();
    }    // getMangerFactory();

    /* object properties and methods */

    /**
     * The object's hostname.
     */
    private final String hostname;

    /**
     * The object's port.
     */
    private final String port;

    /**
     * The object's username.
     */
    private final String username;

    /**
     * True if the server requires TLS; false otherwise.
     */
    private final Boolean useTLS;

    /**
     * Constructs a new EmailServerProperties object.
     *
     * @param hostname the SMTP server hostname
     * @param port the SMTP server port
     * @param username the username used with the SMTP server
     * @param useTLS true if the server requires TLS; false otherwise
     * @throws NullPointerException if any parameter is null
     */
    public EmailServerProperties(String hostname, String port, String username, boolean useTLS) {
        if (hostname == null) {
            throw new NullPointerException("hostname may not be null");
        }    // if
        if (port == null) {
            throw new NullPointerException("port may not be null");
        }    // if
        if (username == null) {
            throw new NullPointerException("username may not be null");
        }    // if

        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.useTLS = useTLS;
        assertInvariant();
    }    // Shift()

    /**
     * Returns the SMTP server hostname.
     *
     * @return the SMTP server hostname
     */
    public String getHostname() {
        assertInvariant();
        return hostname;
    }    // getHostname()

    /**
     * Returns the SMTP server port.
     *
     * @return the SMTP server port
     */
    public String getPort() {
        assertInvariant();
        return port;
    }    // getPort()

    /**
     * Returns the username used with the SMTP server.
     *
     * @return the username used with the SMTP server
     */
    public String getUsername() {
        assertInvariant();
        return username;
    }    // getUsername()

    /**
     * Returns true if the server requires TLS; false otherwise.
     *
     * @return true if the server requires TLS; false otherwise.
     */
    public boolean useTLS() {
        assertInvariant();
        return useTLS;
    }    // useTLS()

    /**
     * Returns a map containing the read-writable properties of the object. The
     * map returned by this method is guaranteed to have the following
     * properties.
     * <ul>
     *   <li>The map has exactly four keys: "hostname", "port", "username", and
     * "useTLS".</li>
     *   <li>The values of "hostname", "post", are "username" are non-null
     * {@link String}s corresponding to the return values of the appropriate
     * getter methods. The value of "useTLS" is a {@link Boolean} corresponding
     * to the return value of {@link EmailServerProperties#useTLS()}.</li>
     *   <li>The iteration order of the elements is fixed in the order the keys
     * are presented above.</li>
     * </ul>
     *
     * @return a map containing the read-writable properties of the object
     */
    @Override
    public Map<String, Object> getReadWritableProperties() {
        assertInvariant();
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("hostname", hostname);
        properties.put("port", port);
        properties.put("username", username);
        properties.put("useTLS", useTLS);
        return properties;
    }    // getReadWritableProperties()

    /**
     * Returns a factory that creates EmailServerProperties objects from
     * read-writable property maps.
     *
     * @return a factory that creates EmailServerProperties objects from
     * read-writable property maps
     */
    @Override
    public Factory getReadWritableFactory() {
        assertInvariant();
        return new Factory();
    }    // getReadWritableFactory()

    /**
     * Indicates whether some other object is "equal to" this one.  An object is
     * equal to this email template only if all the following conditions hold:
     * <ol>
     *   <li>the object is another EmailServerProperties object,</li>
     *   <li>both objects have the same hostname,</li>
     *   <li>both objects have the same port,</li>
     *   <li>both objects have the same username, and</li>
     *   <li>both objects have the same useTLS.</li>
     * </ol>
     *
     * @param obj the object with which to compare
     * @return true if the objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        assertInvariant();
        if (obj instanceof EmailServerProperties) {
            EmailServerProperties properties = (EmailServerProperties)obj;
            assert (properties != null);
            return properties.getHostname().equals(hostname) &&
                    properties.getPort().equals(port) &&
                    properties.getUsername().equals(username) &&
                    useTLS.equals(properties.useTLS());
        }    // if
        return false;
    }    // equals()

    @Override
    public int hashCode() {
        assertInvariant();
        final int SEED = 97;
        final int MULTIPLIER = 41;
        int code = SEED;
        code = code * MULTIPLIER + hostname.hashCode();
        code = code * MULTIPLIER + port.hashCode();
        code = code * MULTIPLIER + username.hashCode();
        code = code * MULTIPLIER + useTLS.hashCode();
        return code;
    }    // hashCode()

    /**
     * Creates and returns a copy of this object.
     *
     * @return a copy of this object
     */
    @Override
    public EmailServerProperties clone() {
        assertInvariant();
        return new EmailServerProperties(hostname, port, username, useTLS);
    }    // clone()

    /**
     * Asserts the correctness of the internal state of the object.
     */
    private void assertInvariant() {
        assert (hostname != null);
        assert (port != null);
        assert (username != null);
        assert (useTLS != null);
    }    // assertInvariant()
}
