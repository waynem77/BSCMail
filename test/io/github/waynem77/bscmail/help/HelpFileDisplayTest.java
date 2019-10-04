/*
 * Copyright © 2017-2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.help;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link HelpFileDisplay}
 *
 * @author Wayne Miller
 */
public class HelpFileDisplayTest {

    /**
     * A string containing the location to an innocuous executable file that can
     * be used in tests. See the documentation for {@link #setPathname()} to see
     * specifics and rational of the file used.
     */
    private String pathname = null;

    /**
     * Sets {@link #pathname} to the location of "java" on the system. This
     * file is guaranteed to be on the system and, when executed with no
     * parameters, should have no undesirable side effects.
     */
    @Before
    public void setPathname() {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin";
        String[] javaFiles = new File(javaBin).list(new FilenameFilter(){
            @Override public boolean accept(File dir, String s) {
                return s.equalsIgnoreCase("java") || s.equalsIgnoreCase("java.exe");
            }    // accept()
        });    // javacFiles

        if (javaFiles.length != 1) {
            fail("Test error: could not locate unique java in " + javaBin + ".  Number of matches: " + javaFiles.length);
        }    // if
        pathname = javaBin + File.separator + javaFiles[0];
    }    // setPathname()

    /**
     * Sets pathname to null.
     */
    @After
    public void unsetPathname() {
        pathname = null;
    }    // unsetPathname()

    /*
     * Unit tests
     */

    /**
     * Tests that {@link HelpFileDisplay#HelpFileDisplay(java.lang.String)}
     * throws a NullPointerException when pathname is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenPathnameIsNull() {
        pathname = null;

        HelpFileDisplay helpFileDisplay = new HelpFileDisplay(pathname);
    }    // constructorThrowsExceptionWhenPathnameIsNull()

    /**
     * Tests that {@link HelpFileDisplay#HelpFileDisplay(java.lang.String)}
     * does not throw an exception when pathname is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenPathnameIsNotNull() {
        pathname = "foo";

        HelpFileDisplay helpFileDisplay = new HelpFileDisplay(pathname);
    }    // constructorDoesNotThrowExceptionWhenPathnameIsNotNull()

    /**
     * Tests that {@link HelpFileDisplay#displayHelp()} does not throw an
     * exception when pathname is valid,
     */
    @Test
    public void displayHelpDoesNotThrowExceptionWhenPathnameIsValid() throws IOException {
        HelpFileDisplay helpFileDisplay = new HelpFileDisplay(pathname);

        helpFileDisplay.displayHelp();
    }    // displayHelpDoesNotThrowExceptionWhenPathnameIsValid()

}    // HelpFileDisplayTest
