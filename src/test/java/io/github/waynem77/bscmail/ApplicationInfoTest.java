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

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ApplicationInfo}.
 *
 * @author Wayne Miller
 */
public class ApplicationInfoTest {

    /* constructor */

    /**
     * Tests that
     * {@link ApplicationInfo#ApplicationInfo(String, String, String, String)}
     * throws a {@link NullPointerException} when name is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenNameIsNull() {
        String name = null;
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";

        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);
    }    // constructorThrowsExceptionWhenNameIsNull()

    /**
     * Tests that
     * {@link ApplicationInfo#ApplicationInfo(String, String, String, String)}
     * does not throw an exception when name is empty.
     */
    @Test
    public void constructorDoesNotThrowAnExceptionWhenNameIsEmpty() {
        String name = "";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";

        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);
    }    // constructorDoesNotThrowAnExceptionWhenNameIsEmpty()

    /**
     * Tests that
     * {@link ApplicationInfo#ApplicationInfo(String, String, String, String)}
     * throws a {@link NullPointerException} when version is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenVersionIsNull() {
        String name = "foo";
        String version = null;
        String copyright = "baz";
        String aboutMessage = "smurf";

        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);
    }    // constructorThrowsExceptionWhenVersionIsNull()

    /**
     * Tests that
     * {@link ApplicationInfo#ApplicationInfo(String, String, String, String)}
     * does not throw an exception when version is empty.
     */
    @Test
    public void constructorDoesNotThrowAnExceptionWhenVersionIsEmpty() {
        String name = "foo";
        String version = "";
        String copyright = "baz";
        String aboutMessage = "smurf";

        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);
    }    // constructorDoesNotThrowAnExceptionWhenVersionIsEmpty()

    /**
     * Tests that
     * {@link ApplicationInfo#ApplicationInfo(String, String, String, String)}
     * throws a {@link NullPointerException} when copyright is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenCopyrightIsNull() {
        String name = "foo";
        String version = "bar";
        String copyright = null;
        String aboutMessage = "smurf";

        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);
    }    // constructorThrowsExceptionWhenCopyrightIsNull()

    /**
     * Tests that
     * {@link ApplicationInfo#ApplicationInfo(String, String, String, String)}
     * does not throw an exception when copyright is empty.
     */
    @Test
    public void constructorDoesNotThrowAnExceptionWhenCopyrightIsEmpty() {
        String name = "foo";
        String version = "bar";
        String copyright = "";
        String aboutMessage = "smurf";

        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);
    }    // constructorDoesNotThrowAnExceptionWhenCopyrightIsEmpty()

    /**
     * Tests that
     * {@link ApplicationInfo#ApplicationInfo(String, String, String, String)}
     * throws a {@link NullPointerException} when aboutMessage is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenAboutMessageIsNull() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = null;

        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);
    }    // constructorThrowsExceptionWhenAboutMessageIsNull()

    /**
     * Tests that
     * {@link ApplicationInfo#ApplicationInfo(String, String, String, String)}
     * does not throw an exception when aboutMessage is empty.
     */
    @Test
    public void constructorDoesNotThrowAnExceptionWhenAboutMessageIsEmpty() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "";

        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);
    }    // constructorDoesNotThrowAnExceptionWhenAboutMessageIsEmpty()

    /**
     * Tests that
     * {@link ApplicationInfo#ApplicationInfo(String, String, String, String)}
     * does not throw an exception when no parameter is null nor empty.
     */
    @Test
    public void constructorDoesNotThrowAnExceptionWhenNoParameterIsNullNorEmpty() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";

        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);
    }    // constructorDoesNotThrowAnExceptionWhenNoParameterIsNullNorEmpty()

    /* getName */

    /**
     * Tests that {@link ApplicationInfo#getName()} does not throw an exception.
     */
    @Test
    public void getNameDoesNotThrowException() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        applicationInfo.getName();
    }    // getNameDoesNotThrowException()

    /**
     * Tests that {@link ApplicationInfo#getName()} does not return null.
     */
    @Test
    public void getNameDoesNotReturnNull() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        String received = applicationInfo.getName();

        assertNotNull(received);
    }    // getNameDoesNotReturnNull()

    /**
     * Tests that {@link ApplicationInfo#getName()} returns the name passed to
     * the constructor.
     */
    @Test
    public void getNameReturnsCorrectValue() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        String received = applicationInfo.getName();

        String expected = name;
        assertEquals(expected, received);
    }    // getNameDoesNotReturnNull()

    /* getVersion */

    /**
     * Tests that {@link ApplicationInfo#getVersion()} does not throw an
     * exception.
     */
    @Test
    public void getVersionDoesNotThrowException() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        applicationInfo.getVersion();
    }    // getVersionDoesNotThrowException()

    /**
     * Tests that {@link ApplicationInfo#getVersion()} does not return null.
     */
    @Test
    public void getVersionDoesNotReturnNull() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        String received = applicationInfo.getVersion();

        assertNotNull(received);
    }    // getVersionDoesNotReturnNull()

    /**
     * Tests that {@link ApplicationInfo#getVersion()} returns the version
     * passed to the constructor.
     */
    @Test
    public void getVersionReturnsCorrectValue() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        String received = applicationInfo.getVersion();

        String expected = version;
        assertEquals(expected, received);
    }    // getVersionDoesNotReturnNull()

    /* getCopyright */

    /**
     * Tests that {@link ApplicationInfo#getCopyright()} does not throw an
     * exception.
     */
    @Test
    public void getCopyrightDoesNotThrowException() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        applicationInfo.getCopyright();
    }    // getCopyrightDoesNotThrowException()

    /**
     * Tests that {@link ApplicationInfo#getCopyright()} does not return null.
     */
    @Test
    public void getCopyrightDoesNotReturnNull() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        String received = applicationInfo.getCopyright();

        assertNotNull(received);
    }    // getCopyrightDoesNotReturnNull()

    /**
     * Tests that {@link ApplicationInfo#getCopyright()} returns the copyright
     * string passed to the constructor.
     */
    @Test
    public void getCopyrightReturnsCorrectValue() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        String received = applicationInfo.getCopyright();

        String expected = copyright;
        assertEquals(expected, received);
    }    // getCopyrightReturnsCorrectValue()

    /* getAboutMessage */

    /**
     * Tests that {@link ApplicationInfo#getAboutMessage()} does not throw an
     * exception.
     */
    @Test
    public void getAboutMessageDoesNotThrowException() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        applicationInfo.getAboutMessage();
    }    // getAboutMessageDoesNotThrowException()

    /**
     * Tests that {@link ApplicationInfo#getAboutMessage()} does not return null.
     */
    @Test
    public void getAboutMessageDoesNotReturnNull() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        String received = applicationInfo.getAboutMessage();

        assertNotNull(received);
    }    // getAboutMessageDoesNotReturnNull()

    /**
     * Tests that {@link ApplicationInfo#getAboutMessage()} returns the copyright
     * string passed to the constructor.
     */
    @Test
    public void getAboutMessageReturnsCorrectValue() {
        String name = "foo";
        String version = "bar";
        String copyright = "baz";
        String aboutMessage = "smurf";
        ApplicationInfo applicationInfo = new ApplicationInfo(name, version, copyright, aboutMessage);

        String received = applicationInfo.getAboutMessage();

        String expected = aboutMessage;
        assertEquals(expected, received);
    }    // getAboutMessageReturnsCorrectValue()

}    // ApplicationInfoTest
