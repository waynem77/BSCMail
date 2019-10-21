/*
 * Copyright © 2014-2019 its authors.  See the file "AUTHORS" for details.
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

import io.github.waynem77.bscmail.gui.MainFrame;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JFrame;

/**
 * Main program.
 *
 * Note to developers: This class defines the application information used by
 * the application. Any changes to the name, version, copyright, or datafiles
 * should occur in {@link Main#getApplication()}.
 *
 * @author Wayne Miller
 */
public class Main {

    /*
     * Static methods
     */

    /**
     * Main program.  This method just executes {@link Main#run()}.
     *
     * @param args the command line arguments; not used
     */
    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }    // main(String[] args)

    /*
     * Class properties and methods
     */

    /**
     * The name of the application properties resource.
     */
    private final String APPLICATION_PROPERTIES_FILE = "application.properties";

    /**
     * Runs the system.
     */
    private void run() {
        try {
            Application application = getApplication();
            JFrame frame = new MainFrame(application);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    // run()

    /**
     * Returns the application used in this program.
     *
     * @return the application used in this program
     * @throws IOException if the method is unable to read the application
     * properties resource
     * @since 3.1
     */
    private Application getApplication() throws IOException {
        try (InputStream applicationPropertiesInputStream = ClassLoader.getSystemResourceAsStream(APPLICATION_PROPERTIES_FILE)) {
            Properties applicationProperties = new Properties();
            applicationProperties.load(applicationPropertiesInputStream);
            return Application.createApplication(applicationProperties);
        }    // try
    }    // getApplication()
}
