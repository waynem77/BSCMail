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

package io.github.waynem77.bscmail.help;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Displays application help by opening and displaying a file contained within
 * the application Jar.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 4.0
 */
public class HelpFileFromResourceDisplay implements HelpDisplay {

    /**
     * The name of the resource containing the help file.
     */
    private final String resource;

    /**
     * The suffix of the help file.
     */
    private final String fileSuffix;

    /**
     * Constructs a new HelpFileFromResourceDisplay object.
     *
     * @param resource the name of the resource containing the help file; may
     * not be null
     * @throws NullPointerException if {@code resource} is null
     */
    public HelpFileFromResourceDisplay(String resource) {
        if (resource == null) {
            throw new NullPointerException("resource may not be null");
        }
        this.resource = resource;

        char suffixSeparator = '.';
        int separatorIndex = resource.lastIndexOf(suffixSeparator);
        // If the resource name has no separator (separatorIndex == -1) or
        // begins with the separator (separatorIndex == 0), then there really is
        // no suffix. Otherwise, we want to store the suffix with the separator.
        fileSuffix = (separatorIndex > 0) ? resource.substring(separatorIndex) : "";

    }    // HelpFileDisplay()

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayHelp() throws IOException {
        final String TEMP_FILE_PREFIX = "bscmail";

        Path tempFile = Files.createTempFile(TEMP_FILE_PREFIX, fileSuffix);
        try (InputStream helpFileInputStream = ClassLoader.getSystemResourceAsStream(resource)) {
            Files.copy(helpFileInputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }    // try

        HelpDisplay helpDisplay = new HelpFileDisplay(tempFile.toString());
        helpDisplay.displayHelp();
    }    // displayHelp()

}    // HelpFileFromResourceDisplay
