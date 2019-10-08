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

package io.github.waynem77.bscmail;

/**
 * A collection of information about the application, used by an
 * {@link Application} object.
 *
 * An application info object contains the following properties, all accessible
 * through getters.
 * <ul>
 * <li>application name</li>
 * <li>application version</li>
 * <li>copyright information</li>
 * </ul>
 *
 * @author Wayne Miller
 * @since 3.0
 */
public class ApplicationInfo {

    /**
     * The application name.
     */
    private final String name;

    /**
     * The application version.
     */
    private final String version;

    /**
     * The application copyright info.
     */
    private final String copyright;

    /**
     * Creates a new application info object from the given strings.
     * 
     * @param name the name of the application; may not be null
     * @param version the version of the application; may not be null
     * @param copyright the copyright string of the application; may not be null
     * @throws NullPointerException if any parameter is null
     */
    public ApplicationInfo(String name, String version, String copyright) {
        if (name == null) {
            throw new NullPointerException("name may not be null");
        }    // if
        if (version == null) {
            throw new NullPointerException("version may not be null");
        }    // if
        if (copyright == null) {
            throw new NullPointerException("copyright may not be null");
        }    // if

        this.name = name;
        this.version = version;
        this.copyright = copyright;

        assertInvariant();
    }    // ApplicationInfo()

    /**
     * Returns the application name.
     * @return the application name
     */
    public String getName() {
        assertInvariant();
        return name;
    }    // getName()

    /**
     * Returns the application version.
     * @return the application version
     */
    public String getVersion() {
        assertInvariant();
        return version;
    }    // getVersion()

    /**
     * Returns the application copyright information.
     * @return the application copyright information
     */
    public String getCopyright() {
        assertInvariant();
        return copyright;
    }    // getCopyright()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (name != null);
        assert (version != null);
        assert (copyright != null);
    }    // assertInvariant()

}    // ApplicationInfo
