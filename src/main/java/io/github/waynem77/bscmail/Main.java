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
import io.github.waynem77.bscmail.help.HelpDisplay;
import io.github.waynem77.bscmail.help.HelpFileFromResourceDisplay;
import io.github.waynem77.bscmail.iolayer.IOLayer;
import io.github.waynem77.bscmail.iolayer.IOLayerFactoryImpl;
import io.github.waynem77.bscmail.iolayer.XMLIOLayer;
import io.github.waynem77.bscmail.persistent.EmailServerProperties;
import io.github.waynem77.bscmail.persistent.EmailTemplate;
import io.github.waynem77.bscmail.persistent.EventProperty;
import io.github.waynem77.bscmail.persistent.Role;
import io.github.waynem77.bscmail.persistent.Shift;
import io.github.waynem77.bscmail.persistent.Volunteer;
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
        Application application = getApplication();
        JFrame frame = new MainFrame(application);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }    // run()

    /**
     * Returns the application used in this program.
     *
     * Note to developers: This method defines the application information used
     * by the application. Any changes to the name, version, copyright, or
     * datafiles should occur here.
     *
     * @return the application used in this program
     * @since 3.1
     */
    private Application getApplication() {
        final String APPLICATION_NAME = "BSCMail";
        final String APPLICATION_VERSION = "3.4";
        final String APPLICATION_COPYRIGHT = "Copyright © 2014-2019 its authors.  See the file \"AUTHORS\" for details.";
        ApplicationInfo applicationInfo = new ApplicationInfo(APPLICATION_NAME, APPLICATION_VERSION, APPLICATION_COPYRIGHT);

        IOLayerFactoryImpl ioLayerFactory = new IOLayerFactoryImpl();

        final Class SHIFTS_IOLAYER_CLASS = XMLIOLayer.class;
        final Class SHIFTS_READ_WRITABLE_CLASS = Shift.class;
        final String SHIFTS_FILE = "shifts.xml";
        IOLayer<Shift> shiftsIOLayer = ioLayerFactory.createIOLayer(SHIFTS_IOLAYER_CLASS, SHIFTS_READ_WRITABLE_CLASS, new Object[]{ SHIFTS_FILE });

        final Class VOLUNTEERS_IOLAYER_CLASS = XMLIOLayer.class;
        final Class VOLUNTEERS_READ_WRITABLE_CLASS = Volunteer.class;
        final String VOLUNTEERS_FILE = "volunteers.xml";
        IOLayer<Volunteer> volunteersIOLayer = ioLayerFactory.createIOLayer(VOLUNTEERS_IOLAYER_CLASS, VOLUNTEERS_READ_WRITABLE_CLASS, new Object[]{ VOLUNTEERS_FILE });

        final Class ROLES_IOLAYER_CLASS = XMLIOLayer.class;
        final Class ROLES_READ_WRITABLE_CLASS = Role.class;
        final String ROLES_FILE = "roles.xml";
        IOLayer<Role> rolesIOLayer = ioLayerFactory.createIOLayer(ROLES_IOLAYER_CLASS, ROLES_READ_WRITABLE_CLASS, new Object[]{ ROLES_FILE });

        final Class EMAIL_TEMPLATE_IOLAYER_CLASS = XMLIOLayer.class;
        final Class EMAIL_TEMPLATE_READ_WRITABLE_CLASS = EmailTemplate.class;
        final String EMAIL_TEMPLATE_FILE = "emailTemplate.xml";
        IOLayer<EmailTemplate> emailTemplateIOLayer = ioLayerFactory.createIOLayer(EMAIL_TEMPLATE_IOLAYER_CLASS, EMAIL_TEMPLATE_READ_WRITABLE_CLASS, new Object[]{ EMAIL_TEMPLATE_FILE });

        final Class EMAIL_SERVER_PROPERTIES_IOLAYER_CLASS = XMLIOLayer.class;
        final Class EMAIL_SERVER_PROPERTIES_READ_WRITABLE_CLASS = EmailServerProperties.class;
        final String EMAIL_SERVER_PROPERTIES_FILE = "emailServerProperties.xml";
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = ioLayerFactory.createIOLayer(EMAIL_SERVER_PROPERTIES_IOLAYER_CLASS, EMAIL_SERVER_PROPERTIES_READ_WRITABLE_CLASS, new Object[]{ EMAIL_SERVER_PROPERTIES_FILE });

        final Class EVENT_PROPERTIES_IOLAYER_CLASS = XMLIOLayer.class;
        final Class EVENT_PROPERTIES_READ_WRITABLE_CLASS = EventProperty.class;
        final String EVENT_PROPERTIES_FILE = "eventProperties.xml";
        IOLayer<EventProperty> eventPropertiesIOLayer = ioLayerFactory.createIOLayer(EVENT_PROPERTIES_IOLAYER_CLASS, EVENT_PROPERTIES_READ_WRITABLE_CLASS, new Object[]{ EVENT_PROPERTIES_FILE });

        final String USER_GUIDE_FILE = "userguide.pdf";
        HelpDisplay helpDisplay = new HelpFileFromResourceDisplay(USER_GUIDE_FILE);

        return Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // getApplication()
}
