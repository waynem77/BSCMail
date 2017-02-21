/*
 * Copyright © 2017 its authors.  See the file "AUTHORS" for details.
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

package bscmail.help;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Displays application help by opening and displaying a file.
 *
 * @author Wayne Miller
 * @since 3.1
 */
public class HelpFileDisplay implements HelpDisplay {

    /**
     * The pathname of the help file.
     */
    private final String pathname;

    /**
     * Constructs a new help file display.
     *
     * @param pathname the pathname of the help file; may not be null
     * @throws NullPointerException if {@code pathname} is null
     */
    public HelpFileDisplay(String pathname) {
        if (pathname == null) {
            throw new NullPointerException("pathname may not be null");
        }
        this.pathname = pathname;
    }    // HelpFileDisplay()

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayHelp() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        File userGuide = new File(pathname);
        desktop.open(userGuide);
    }    // displayHelp()

}    // HelpFileDisplay
