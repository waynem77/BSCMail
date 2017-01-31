/*
 * Copyright © 2014-2017 its authors.  See the file "AUTHORS" for details.
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

package main;

import bscmail.Application;
import bscmail.ApplicationInfo;
import bscmail.gui.MainFrame;
import javax.swing.JFrame;

/**
 * Main program.
 *
 * @author Wayne Miller
 */
public class Main {

    /**
     * The name of this application.
     */
    private final String APPLICATION_NAME = "BSCMail";

    /**
     * The version of this application.
     */
    private final String APPLICATION_VERSION = "3.0";

    /**
     * The copyright info for this application.
     */
    private final String APPLICATION_COPYRIGHT = "Copyright © 2014-2017 its authors.  See the file \"AUTHORS\" for details.";

    /**
     * Main program.  This method just executes {@link Main#run()}.
     *
     * @param args the command line arguments; not used
     */
    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }    // main(String[] args)

    /**
     * Runs and tests the system.
     */
    private void run() {
        ApplicationInfo applicationInfo = new ApplicationInfo(APPLICATION_NAME, APPLICATION_VERSION, APPLICATION_COPYRIGHT);
        Application application = new Application(applicationInfo);
        JFrame frame = new MainFrame(application);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }    // run()

}
