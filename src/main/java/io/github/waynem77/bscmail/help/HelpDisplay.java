/*
 * Copyright © 2017-2017 its authors.  See the file "AUTHORS" for details.
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

import java.io.IOException;

/**
 * Displays application help, in an implementation-defined way.
 *
 * @author Wayne Miller
 * @since 3.1
 */
public interface HelpDisplay {

    /**
     * Displays application help, in an implementation-defined way.
     *
     * @throws IOException if an I/O error occurs
     */
    public void displayHelp() throws IOException;

}    // HelpDisplay
