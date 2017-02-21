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
import bscmail.EmailTemplate;
import bscmail.EventProperty;
import bscmail.Role;
import bscmail.Shift;
import bscmail.Volunteer;
import bscmail.gui.MainFrame;
import bscmail.help.HelpDisplay;
import bscmail.help.HelpFileDisplay;
import iolayer.IOLayer;
import iolayer.XMLIOLayer;
import javax.swing.JFrame;

/**
 * Main program.
 *
 * @author Wayne Miller
 */
public class Main {

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
        final String APPLICATION_NAME = "BSCMail";
        final String APPLICATION_VERSION = "3.0";
        final String APPLICATION_COPYRIGHT = "Copyright © 2014-2017 its authors.  See the file \"AUTHORS\" for details.";
        ApplicationInfo applicationInfo = new ApplicationInfo(APPLICATION_NAME, APPLICATION_VERSION, APPLICATION_COPYRIGHT);

        final String SHIFTS_FILE = "shifts.xml";
        IOLayer<Shift> shiftsIOLayer = new XMLIOLayer(SHIFTS_FILE, Shift.getShiftFactory());

        final String VOLUNTEERS_FILE = "volunteers.xml";
        IOLayer<Volunteer> volunteersIOLayer = new XMLIOLayer(VOLUNTEERS_FILE, Volunteer.getVolunteerFactory());

        final String ROLES_FILE = "roles.xml";
        IOLayer<Role> rolesIOLayer = new XMLIOLayer(ROLES_FILE, Role.getRoleFactory());

        final String EMAIL_TEMPLATE_FILE = "emailTemplate.xml";
        IOLayer<EmailTemplate> emailTemplateIOLayer = new XMLIOLayer(EMAIL_TEMPLATE_FILE, EmailTemplate.getEmailTemplateFactory());

        final String EVENT_PROPERTIES_FILE = "eventProperties.xml";
        IOLayer<EventProperty> eventPropertiesIOLayer = new XMLIOLayer(EVENT_PROPERTIES_FILE, EventProperty.getEventPropertyFactory());

        final String USER_GUIDE_FILE = "userguide.pdf";
        HelpDisplay helpDisplay = new HelpFileDisplay(USER_GUIDE_FILE);

        Application application = new Application(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, eventPropertiesIOLayer, helpDisplay);
        JFrame frame = new MainFrame(application);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }    // run()

}
