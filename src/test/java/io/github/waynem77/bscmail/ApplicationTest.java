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

import io.github.waynem77.bscmail.help.HelpDisplay;
import io.github.waynem77.bscmail.help.TestHelpDisplay;
import io.github.waynem77.bscmail.iolayer.IOLayer;
import io.github.waynem77.bscmail.persistent.EmailServerProperties;
import io.github.waynem77.bscmail.persistent.EmailServerPropertiesObserver;
import io.github.waynem77.bscmail.persistent.EmailTemplate;
import io.github.waynem77.bscmail.persistent.EmailTemplateObserver;
import io.github.waynem77.bscmail.persistent.EventPropertiesObserver;
import io.github.waynem77.bscmail.persistent.EventProperty;
import io.github.waynem77.bscmail.persistent.Role;
import io.github.waynem77.bscmail.persistent.RolesObserver;
import io.github.waynem77.bscmail.persistent.Shift;
import io.github.waynem77.bscmail.persistent.ShiftsObserver;
import io.github.waynem77.bscmail.iolayer.TestIOLayer;
import io.github.waynem77.bscmail.persistent.Volunteer;
import io.github.waynem77.bscmail.persistent.VolunteersObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Application}
 * @author Wayne Miller
 */
public class ApplicationTest {

    /**
     * Observer used in tests.
     */
    private class ApplicationObserver implements ShiftsObserver, VolunteersObserver, RolesObserver, EmailTemplateObserver, EmailServerPropertiesObserver, EventPropertiesObserver {
        private boolean shiftsChanged = false;
        private boolean volunteersChanged = false;
        private boolean rolesChanged = false;
        private boolean emailTemplateChanged = false;
        private boolean emailServerPropertiesChanged = false;
        private boolean eventPropertiesChanged = false;

        @Override public void shiftsChanged() { shiftsChanged = true; }
        @Override public void volunteersChanged() { volunteersChanged = true; }
        @Override public void rolesChanged() { rolesChanged = true; }
        @Override public void emailTemplateChanged() { emailTemplateChanged = true; }
        @Override public void emailServerPropertiesChanged() { emailServerPropertiesChanged = true; }
        @Override public void eventPropertiesChanged() { eventPropertiesChanged = true; }

        public boolean getShiftsChanged() { return shiftsChanged; }
        public boolean getVolunteersChanged() { return volunteersChanged; }
        public boolean getRolesChanged() { return rolesChanged; }
        public boolean getEmailTemplateChanged() { return emailTemplateChanged; }
        public boolean getEmailServerPropertiesChanged() { return emailServerPropertiesChanged; }
        public boolean getEventPropertiesChanged() { return eventPropertiesChanged; }
    }    // ApplicationObserver

    /**
     * Returns properties usable in tests of
     * {@link Application#createApplication(Properties)}. The properties returned have
     * the following properties:
     * <ul>
     * <li>The {@link IOLayerFactory} specified is a
     * {@link TestIOLayerFactory}.</li>
     * <li>The {@link IOLayer} specified for each read-writable is a
     * {@link TestIOLayer}.</li>
     * <li>The arguments specified for each IOLayer consists of a
     * comma-separated list of three values, one plain, one containing a comma,
     * and one containing double-quotes.</li>
     * </ul>
     *
     * @return properties usable in createApplication tests
     */
    private Properties getTestApplicationProperties() {
        Properties applicationProperties = new Properties();

        applicationProperties.setProperty(Application.PropertyKey.APPLICATION_NAME.getKeyString(), "foo");
        applicationProperties.setProperty(Application.PropertyKey.APPLICATION_VERSION.getKeyString(), "bar");
        applicationProperties.setProperty(Application.PropertyKey.APPLICATION_COPYRIGHT.getKeyString(), "baz");
        applicationProperties.setProperty(Application.PropertyKey.APPLICATION_ABOUT_MESSAGE.getKeyString(), "smurf");
        applicationProperties.setProperty(Application.PropertyKey.APPLICATION_IOLAYER_FACTORY.getKeyString(), "io.github.waynem77.bscmail.iolayer.TestIOLayerFactory");
        applicationProperties.setProperty(Application.PropertyKey.APPLICATION_HELP_DISPLAY_FACTORY.getKeyString(), "io.github.waynem77.bscmail.help.TestHelpDisplayFactory");
        applicationProperties.setProperty(Application.PropertyKey.SHIFTS_IOLAYER_CLASS.getKeyString(), "io.github.waynem77.bscmail.iolayer.TestIOLayer");
        applicationProperties.setProperty(Application.PropertyKey.SHIFTS_IOLAYER_ARGUMENTS.getKeyString(), "aaa,\"bb,b\",\"c\"\"c\"\"c\"");
        applicationProperties.setProperty(Application.PropertyKey.VOLUNTEERS_IOLAYER_CLASS.getKeyString(), "io.github.waynem77.bscmail.iolayer.TestIOLayer");
        applicationProperties.setProperty(Application.PropertyKey.VOLUNTEERS_IOLAYER_ARGUMENTS.getKeyString(), "aaa,\"bb,b\",\"c\"\"c\"\"c\"");
        applicationProperties.setProperty(Application.PropertyKey.ROLES_IOLAYER_CLASS.getKeyString(), "io.github.waynem77.bscmail.iolayer.TestIOLayer");
        applicationProperties.setProperty(Application.PropertyKey.ROLES_IOLAYER_ARGUMENTS.getKeyString(), "aaa,\"bb,b\",\"c\"\"c\"\"c\"");
        applicationProperties.setProperty(Application.PropertyKey.EMAIL_TEMPLATE_IOLAYER_CLASS.getKeyString(), "io.github.waynem77.bscmail.iolayer.TestIOLayer");
        applicationProperties.setProperty(Application.PropertyKey.EMAIL_TEMPLATE_IOLAYER_ARGUMENTS.getKeyString(), "aaa,\"bb,b\",\"c\"\"c\"\"c\"");
        applicationProperties.setProperty(Application.PropertyKey.EMAIL_SERVER_PROPERTIES_IOLAYER_CLASS.getKeyString(), "io.github.waynem77.bscmail.iolayer.TestIOLayer");
        applicationProperties.setProperty(Application.PropertyKey.EMAIL_SERVER_PROPERTIES_IOLAYER_ARGUMENTS.getKeyString(), "aaa,\"bb,b\",\"c\"\"c\"\"c\"");
        applicationProperties.setProperty(Application.PropertyKey.EVENT_PROPERTIES_IOLAYER_CLASS.getKeyString(), "io.github.waynem77.bscmail.iolayer.TestIOLayer");
        applicationProperties.setProperty(Application.PropertyKey.EVENT_PROPERTIES_IOLAYER_ARGUMENTS.getKeyString(), "aaa,\"bb,b\",\"c\"\"c\"\"c\"");
        applicationProperties.setProperty(Application.PropertyKey.HELP_DISPLAY_CLASS.getKeyString(), "io.github.waynem77.bscmail.help.TestHelpDisplay");
        applicationProperties.setProperty(Application.PropertyKey.HELP_DISPLAY_ARGUMENTS.getKeyString(), "aaa,\"bb,b\",\"c\"\"c\"\"c\"");

        return applicationProperties;
    }    // getTestApplicationProperties()

    /**
     * Returns applications that can be used in tests.
     *
     * @return applications that can be used in tests
     */
    private List<Application> getTestApplications() {
        List<Application> applications = new ArrayList<>();

        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();
        applications.add(Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay));

        Properties applicationProperties = getTestApplicationProperties();
        applications.add(Application.createApplication(applicationProperties));

        return applications;
    }    // getTestApplications()

    /*
     * Unit tests
     */

    /* createApplication(multi-argument) */

    /**
     * Tests that
     * {@link Application#createApplication(bscmail.ApplicationInfo, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, bscmail.help.HelpDisplay)
     * throws a {@link NullPointerException} when applicationInfo is null.
     */
    @Test(expected = NullPointerException.class)
    public void createApplicationThrowsExceptionWhenApplicationInfoIsNull() {
        ApplicationInfo applicationInfo = null;
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();

        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // createApplicationThrowsExceptionWhenApplicationInfoIsNull()

    /**
     * Tests that
     * {@link Application#createApplication(bscmail.ApplicationInfo, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, bscmail.help.HelpDisplay)
     * throws a {@link NullPointerException} when shiftsIOLayer is null.
     */
    @Test(expected = NullPointerException.class)
    public void createApplicationThrowsExceptionWhenShiftsIoLayerIsNull() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = null;
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();

        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // createApplicationThrowsExceptionWhenShiftsIoLayerIsNull()

    /**
     * Tests that
     * {@link Application#createApplication(bscmail.ApplicationInfo, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, bscmail.help.HelpDisplay)
     * throws a {@link NullPointerException} when volunteersIOLayer is null.
     */
    @Test(expected = NullPointerException.class)
    public void createApplicationThrowsExceptionWhenVolunteersIoLayerIsNull() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = null;
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();

        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // createApplicationThrowsExceptionWhenVolunteersIoLayerIsNull()

    /**
     * Tests that
     * {@link Application#createApplication(bscmail.ApplicationInfo, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, bscmail.help.HelpDisplay)
     * throws a {@link NullPointerException} when rolesIOLayer is null.
     */
    @Test(expected = NullPointerException.class)
    public void createApplicationThrowsExceptionWhenRolesIoLayerIsNull() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = null;
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();

        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // createApplicationThrowsExceptionWhenRolesIoLayerIsNull()

    /**
     * Tests that
     * {@link Application#createApplication(bscmail.ApplicationInfo, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, bscmail.help.HelpDisplay)
     * throws a {@link NullPointerException} when emailTemplateIOLayer is null.
     */
    @Test(expected = NullPointerException.class)
    public void createApplicationThrowsExceptionWhenEmailTemplateIoLayerIsNull() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = null;
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();

        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // createApplicationThrowsExceptionWhenEmailTemplateIoLayerIsNull()

    /**
     * Tests that
     * {@link Application#createApplication(bscmail.ApplicationInfo, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, bscmail.help.HelpDisplay)
     * throws a {@link NullPointerException} when emailServerPropertiesIOLayer
     * is null.
     */
    @Test(expected = NullPointerException.class)
    public void createApplicationThrowsExceptionWhenEmailServerPropertiesIoLayerIsNull() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = null;
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();

        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // createApplicationThrowsExceptionWhenEmailServerPropertiesIoLayerIsNull()

    /**
     * Tests that
     * {@link Application#createApplication(bscmail.ApplicationInfo, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, bscmail.help.HelpDisplay)
     * throws a {@link NullPointerException} when eventPropertiesIOLayer is null.
     */
    @Test(expected = NullPointerException.class)
    public void createApplicationThrowsExceptionWhenEventPropertiesIoLayerIsNull() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = null;
        HelpDisplay helpDisplay = new TestHelpDisplay();

        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // createApplicationThrowsExceptionWhenEventPropertiesIoLayerIsNull()

    /**
     * Tests that
     * {@link Application#createApplication(bscmail.ApplicationInfo, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, bscmail.help.HelpDisplay)
     * throws a {@link NullPointerException} when helpDisplay is null.
     */
    @Test(expected = NullPointerException.class)
    public void createApplicationThrowsExceptionWhenHelpDisplayIsNull() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = null;

        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // createApplicationThrowsExceptionWhenHelpDisplayIsNull()

    /**
     * Tests that
     * {@link Application#createApplication(bscmail.ApplicationInfo, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, iolayer.IOLayer, bscmail.help.HelpDisplay)
     * does not throw an exception when no parameter is null.
     */
    @Test
    public void createApplicationDoesNotThrowExceptionWhenNoparameterIsNull() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();

        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // createApplicationDoesNotThrowExceptionWhenNoparameterIsNull()

    /* createApplication(Properties) */

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link NullPointerException} when applicationProperties is null.
     */
    @Test(expected = NullPointerException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesIsNull() {
        Properties applicationProperties = null;

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesIsNull()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "application.name".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationName() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.APPLICATION_NAME.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationName()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "application.version".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationVersion() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.APPLICATION_VERSION.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationVersion()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "application.copyright".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationCopyright() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.APPLICATION_COPYRIGHT.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationCopyright()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "application.iolayerFactory".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationIolayerFactory() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.APPLICATION_IOLAYER_FACTORY.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationIolayerFactory()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "application.helpDisplayFactory".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationHelpDisplayFactory() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.APPLICATION_HELP_DISPLAY_FACTORY.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainApplicationHelpDisplayFactory()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "shifts.iolayer.class".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainShiftsIolayerClass() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.SHIFTS_IOLAYER_CLASS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainShiftsIolayerClass()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "shifts.iolayer.arguments".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainShiftsIolayerArguments() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.SHIFTS_IOLAYER_ARGUMENTS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainShiftsIolayerArguments()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "volunteers.iolayer.class".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainVolunteersIolayerClass() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.VOLUNTEERS_IOLAYER_CLASS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainVolunteersIolayerClass()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "volunteers.iolayer.arguments".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainVolunteersIolayerArguments() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.VOLUNTEERS_IOLAYER_ARGUMENTS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainVolunteersIolayerArguments()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "roles.iolayer.class".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainRolesIolayerClass() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.ROLES_IOLAYER_CLASS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainRolesIolayerClass()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "roles.iolayer.arguments".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainRolesIolayerArguments() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.ROLES_IOLAYER_ARGUMENTS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainRolesIolayerArguments()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "emailTemplate.iolayer.class".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEmailTemplateIolayerClass() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.EMAIL_TEMPLATE_IOLAYER_CLASS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEmailTemplateIolayerClass()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "emailTemplate.iolayer.arguments".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEmailTemplateIolayerArguments() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.EMAIL_TEMPLATE_IOLAYER_ARGUMENTS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEmailTemplateIolayerArguments()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "emailServerProperties.iolayer.class".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEmailServerPropertiesIolayerClass() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.EMAIL_SERVER_PROPERTIES_IOLAYER_CLASS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEmailServerPropertiesIolayerClass()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "emailServerProperties.iolayer.arguments".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEmailServerPropertiesIolayerArguments() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.EMAIL_SERVER_PROPERTIES_IOLAYER_ARGUMENTS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEmailServerPropertiesIolayerArguments()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "eventProperties.iolayer.class".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEventPropertiesIolayerClass() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.EVENT_PROPERTIES_IOLAYER_CLASS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEventPropertiesIolayerClass()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "eventProperties.iolayer.arguments".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEventPropertiesIolayerArguments() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.EVENT_PROPERTIES_IOLAYER_ARGUMENTS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainEventPropertiesIolayerArguments()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "helpDisplay.class".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainHelpDisplayClass() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.HELP_DISPLAY_CLASS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainHelpDisplayClass()

    /**
     * Tests that {@link Application#createApplication(Properties)} throws a
     * {@link IllegalArgumentException} when applicationProperties does not
     * contain the property "helpDisplay.arguments".
     */
    @Test(expected = IllegalArgumentException.class)
    public void propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainHelpDisplayArguments() {
        Properties applicationProperties = getTestApplicationProperties();
        applicationProperties.remove(Application.PropertyKey.HELP_DISPLAY_ARGUMENTS.getKeyString());

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationThrowsExceptionWhenApplicationPropertiesDoesNotContainHelpDisplayArguments()

    /**
     * Tests that {@link Application#createApplication(Properties)} does not throw an
     * exception when applicationProperties meets the specification.
     */
    @Test
    public void propertiesCreateApplicationDoesNotThrowExceptionWhenApplicationPropertiesMeetsSpecification() {
        Properties applicationProperties = getTestApplicationProperties();

        Application application = Application.createApplication(applicationProperties);
    }    // propertiesCreateApplicationDoesNotThrowExceptionWhenApplicationPropertiesMeetsSpecification()

    /* getApplicationName */

    /**
     * Tests that {@link Application#getApplicationName()} does not throw an
     * exception.
     */
    @Test
    public void getApplicationNameDoesNotThrowException() {
        for (Application application: getTestApplications()) {
            application.getApplicationName();
        }    // for
    }    // getApplicationNameDoesNotThrowException()

    /**
     * Tests that {@link Application#getApplicationName()} does not return null.
     */
    @Test
    public void getApplicationNameDoesNotReturnNull() {
        for (Application application: getTestApplications()) {
            String received = application.getApplicationName();
            assertNotNull(received);
        }    // for
    }    // getApplicationNameDoesNotReturnNull()

    /**
     * Tests that {@link Application#getApplicationName()} returns the correct
     * value.
     */
    @Test
    public void getApplicationNameReturnsCorrectValue() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();
        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);

        String received = application.getApplicationName();

        String expected = applicationInfo.getName();
        assertEquals(expected, received);

        Properties applicationProperties = getTestApplicationProperties();
        application = Application.createApplication(applicationProperties);

        received = application.getApplicationName();

        expected = applicationProperties.getProperty(Application.PropertyKey.APPLICATION_NAME.getKeyString());
        assertEquals(expected, received);
    }    // getApplicationNameReturnsCorrectValue()

    /* getVersion */

    /**
     * Tests that {@link Application#getVersion()} does not throw an exception.
     */
    @Test
    public void getVersionDoesNotThrowException() {
        for (Application application: getTestApplications()) {
            application.getVersion();
        }    // for
    }    // getVersionDoesNotThrowException()

    /**
     * Tests that {@link Application#getVersion()} does not return null.
     */
    @Test
    public void getVersionDoesNotReturnNull() {
        for (Application application: getTestApplications()) {
            String received = application.getVersion();

            assertNotNull(received);
        }    // for
    }    // getVersionDoesNotReturnNull()

    /**
     * Tests that {@link Application#getApplicationVersion()} returns the
     * correct value.
     */
    @Test
    public void getApplicationVersionReturnsCorrectValue() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();
        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);

        String received = application.getVersion();

        String expected = applicationInfo.getVersion();
        assertEquals(expected, received);

        Properties applicationProperties = getTestApplicationProperties();
        application = Application.createApplication(applicationProperties);

        received = application.getVersion();

        expected = applicationProperties.getProperty(Application.PropertyKey.APPLICATION_VERSION.getKeyString());
        assertEquals(expected, received);
    }    // getApplicationVersionReturnsCorrectValue()

    /* getCopyright */

    /**
     * Tests that {@link Application#getCopyright()} does not throw an
     * exception.
     */
    @Test
    public void getCopyrightDoesNotThrowException() {
        for (Application application: getTestApplications()) {
            application.getCopyright();
        }    // for
    }    // getCopyrightDoesNotThrowException()

    /**
     * Tests that {@link Application#getCopyright()} does not return null.
     */
    @Test
    public void getCopyrightDoesNotReturnNull() {
        for (Application application: getTestApplications()) {
            String received = application.getCopyright();

            assertNotNull(received);
        }    // for
    }    // getCopyrightDoesNotReturnNull()

    /**
     * Tests that {@link Application#getApplicationCopyright()} returns the
     * correct value.
     */
    @Test
    public void getApplicationCopyrightReturnsCorrectValue() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();
        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);

        String received = application.getCopyright();

        String expected = applicationInfo.getCopyright();
        assertEquals(expected, received);

        Properties applicationProperties = getTestApplicationProperties();
        application = Application.createApplication(applicationProperties);

        received = application.getCopyright();

        expected = applicationProperties.getProperty(Application.PropertyKey.APPLICATION_COPYRIGHT.getKeyString());
        assertEquals(expected, received);
    }    // getApplicationCopyrightReturnsCorrectValue()

    /* getAboutMessage */

    /**
     * Tests that {@link Application#getAboutMessage()} does not throw an
     * exception.
     */
    @Test
    public void getAboutMessageDoesNotThrowException() {
        for (Application application: getTestApplications()) {
            application.getAboutMessage();
        }    // for
    }    // getAboutMessageDoesNotThrowException()

    /**
     * Tests that {@link Application#getAboutMessage()} does not return null.
     */
    @Test
    public void getAboutMessageDoesNotReturnNull() {
        for (Application application: getTestApplications()) {
            String received = application.getAboutMessage();

            assertNotNull(received);
        }    // for
    }    // getAboutMessageDoesNotReturnNull()

    /**
     * Tests that {@link Application#getApplicationAboutMessage()} returns the
     * correct value.
     */
    @Test
    public void getApplicationAboutMessageReturnsCorrectValue() {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz", "smurf");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new TestHelpDisplay();
        Application application = Application.createApplication(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);

        String received = application.getAboutMessage();

        String expected = applicationInfo.getAboutMessage();
        assertEquals(expected, received);

        Properties applicationProperties = getTestApplicationProperties();
        application = Application.createApplication(applicationProperties);

        received = application.getAboutMessage();

        expected = applicationProperties.getProperty(Application.PropertyKey.APPLICATION_ABOUT_MESSAGE.getKeyString());
        assertEquals(expected, received);
    }    // getApplicationAboutMessageReturnsCorrectValue()

    /* displayHelp */

    /**
     * Tests that {@link Application#displayHelp()} does not throw an exception.
     */
    @Test
    public void displayHelpDoesNotThrowException() {
        for (Application application: getTestApplications()) {
            application.displayHelp();
        }    // for
    }    // displayHelpDoesNotThrowException()

    /* getShifts / setShifts */

    /**
     * Tests that {@link Application#getShifts()} does not throw an exception.
     */
    @Test
    public void getShiftsDoesNotThrowException() {
        for (Application application: getTestApplications()) {
            application.getShifts();
        }    // for
    }    // getShiftsDoesNotThrowException()

    /**
     * Tests that {@link Application#getShifts()} does not return null.
     */
    @Test
    public void getShiftsDoesNotReturnNull() {
        for (Application application: getTestApplications()) {

            List<Shift> received = application.getShifts();

            assertNotNull(received);
        }    // for
    }    // getShiftsDoesNotReturnNull()

    /**
     * Tests that {@link Application#setShifts(List)} throws a
     * {@link NullPointerException} when shifts is null.
     */
    @Test(expected = NullPointerException.class)
    public void setShiftsThrowsExceptionWhenShiftsIsNull() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> shifts = null;

            application.setShifts(shifts);
        }    // for
    }    // setShiftsThrowsExceptionWhenShiftsIsNull()

    /**
     * Tests that {@link Application#setShifts(List)} throws a
     * {@link NullPointerException} when shifts is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void setShiftsThrowsExceptionWhenShiftsContainsNull() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false), null);

            application.setShifts(shifts);
        }    // for
    }    // setShiftsThrowsExceptionWhenShiftsContainsNull()

    /**
     * Tests that {@link Application#setShifts(List)} does not throw an
     * exception when shifts is not null and contains no nulls.
     */
    @Test
    public void setShiftsDoesNotThrowExceptionWithGoodArgument() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                    new Shift("Bar", new LinkedList<Role>(), false, false, false));

            application.setShifts(shifts);
        }    // for
    }    // setShiftsDoesNotThrowExceptionWithGoodArgument()

    /**
     * Tests that {@link Application#setShifts(List)} does not alter its
     * argument.
     */
    @Test
    public void setShiftsDoesNotAlterArgument() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> shifts = Arrays.asList(new Shift("Foo", Arrays.asList(), false, false, false),
                    new Shift("Bar", Arrays.asList(), false, false, false));
            shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", "", true, Arrays.asList()));
            List<Shift> clonedShifts = new LinkedList<>();
            for (Shift shift : shifts) {
                clonedShifts.add(shift.clone());
            }    // for

            application.setShifts(shifts);

            List<Shift> expected = clonedShifts;
            List<Shift> received = shifts;
            assertEquals(expected, received);
        }    // for
    }    // setShiftsDoesNotAlterArgument()

    /**
     * Tests that {@link Application#getShifts()} does not throw an exception
     * when there are no shifts.
     */
    @Test
    public void getShiftsDoesNotThrowExceptionWhenThereAreNoShifts() throws IOException {
        for (Application application : getTestApplications()) {
            application.getShifts();
        }    // for
    }    // getShiftsDoesNotThrowExceptionWhenThereAreNoShifts()

    /**
     * Tests that {@link Application#getShifts()} does not return null when
     * there are no shifts.
     */
    @Test
    public void getShiftsDoesNotReturnNullWhenThereAreNoShifts() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> received = application.getShifts();

            assertNotNull(received);
        }    // for
    }    // getShiftsDoesNotReturnNullWhenThereAreNoShifts()

    /**
     * Tests that {@link Application#getShifts()} does not throw an exception
     * when there are shifts.
     */
    @Test
    public void getShiftsDoesNotThrowExceptionWhenThereAreShifts() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> shifts = Arrays.asList(new Shift("Foo", Arrays.asList(), false, false, false),
                    new Shift("Bar", Arrays.asList(), false, false, false));
            shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", "", true, Arrays.asList()));
            application.setShifts(shifts);

            application.getShifts();
        }    // for
    }    // getShiftsDoesNotThrowExceptionWhenThereAreShifts()

    /**
     * Tests that {@link Application#getShifts()} does not return null when
     * there are shifts.
     */
    @Test
    public void getShiftsDoesNotReturnNullWhenThereAreShifts() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> shifts = Arrays.asList(new Shift("Foo", Arrays.asList(), false, false, false),
                    new Shift("Bar", Arrays.asList(), false, false, false));
            shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", "", true, Arrays.asList()));
            application.setShifts(shifts);

            List<Shift> received = application.getShifts();

            assertNotNull(received);
        }    // for
    }    // getShiftsDoesNotThrowExceptionWhenThereAreShifts()

    /**
     * Tests that {@link Application#getShifts()} returns a list equal to that
     * passed to {@link Application#setShifts(List)}, minus any volunteers.
     */
    @Test
    public void getShiftsReturnsCorrectValue() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> shifts = Arrays.asList(new Shift("Foo", Arrays.asList(), false, false, false),
                    new Shift("Bar", Arrays.asList(), false, false, false));
            shifts.get(0).setVolunteer(new Volunteer("foo", "bar", "", "", true, Arrays.asList()));
            List<Shift> clonedShiftsMinusVolunteers = new LinkedList<>();
            for (Shift shift : shifts) {
                Shift newShift = shift.clone();
                newShift.setVolunteer(null);
                clonedShiftsMinusVolunteers.add(newShift);
            }    // for
            application.setShifts(shifts);

            List<Shift> received = application.getShifts();

            List<Shift> expected = clonedShiftsMinusVolunteers;
            assertEquals(expected, received);
        }    // for
    }    // getShiftsReturnsCorrectValue()

    /**
     * Tests that {@link Application#getShifts()} returns a list that is not
     * identical to that passed to {@link Application#setShifts(List)}.
     */
    @Test
    public void getShiftsDoesNotReturnTheIdenticalListThatWasPassedToSetShifts() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                    new Shift("Bar", new LinkedList<Role>(), false, false, false));
            application.setShifts(shifts);

            List<Shift> received = application.getShifts();

            List<Shift> notExpected = shifts;
            assertFalse(notExpected == received);
        }    // for
    }    // getShiftsDoesNotReturnTheIdenticalListThatWasPassedToSetShifts()

    /**
     * Tests that the elements of the list returned by
     * {@link Application#getShifts()} are not identical identical to those of
     * the list passed to {@link Application#setShifts(List)}.
     */
    @Test
    public void getShiftsReturnsAListWhoseElementsAreNotIdenticalToThosePassedToSetShifts() throws IOException {
        for (Application application : getTestApplications()) {
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                    new Shift("Bar", new LinkedList<Role>(), false, false, false));
            application.setShifts(shifts);

            List<Shift> received = application.getShifts();

            List<Shift> notExpected = shifts;
            for (int i = 0; i < shifts.size(); ++i) {
                assertFalse(notExpected.get(i) == received.get(i));
            }    // for
        }    // for
    }    // testGetShiftsSetShiftsNoElementIdentity()

    /* getVolunteers / setVolunteers */

    /**
     * Tests that {@link Application#getVolunteers()} does not throw an
     * exception when there are no volunteers.
     */
    @Test
    public void getVolunteersDoesNotThrowExceptionWhenThereAreNoVolunteers() {
        for (Application application : getTestApplications()) {
            application.getVolunteers();
        }    // for
    }    // getVolunteersDoesNotThrowExceptionWhenThereAreNoVolunteers()

    /**
     * Tests that {@link Application#getVolunteers()} does not return null when
     * there are no volunteers.
     */
    @Test
    public void getVolunteersDoesNotReturnNullWhenThereAreNoVolunteers() {
        for (Application application : getTestApplications()) {
            List<Volunteer> received = application.getVolunteers();

            assertNotNull(received);
        }    // for
    }    // getVolunteersDoesNotReturnNullWhenThereAreNoVolunteers()

    /**
     * Tests that {@link Application#getVolunteers()} does not throw an
     * exception when there are volunteers.
     */
    @Test
    public void getVolunteersDoesNotThrowExceptionWhenThereAreVolunteers() throws IOException {
        for (Application application : getTestApplications()) {
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers
            application.setVolunteers(volunteers);

            application.getVolunteers();
        }    // for
    }    // getVolunteersDoesNotThrowExceptionWhenThereAreVolunteers()

    /**
     * Tests that {@link Application#getVolunteers()} does not return null when
     * there are volunteers.
     */
    @Test
    public void getVolunteersDoesNotReturnNullWhenThereAreVolunteers() throws IOException {
        for (Application application : getTestApplications()) {
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers
            application.setVolunteers(volunteers);

            List<Volunteer> received = application.getVolunteers();

            assertNotNull(received);
        }    // for
    }    // getVolunteersDoesNotReturnNullWhenThereAreVolunteers()

    /**
     * Tests that {@link Application#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is null.
     */
    @Test(expected = NullPointerException.class)
    public void setVolunteersThrowsExceptionWhenVolunteersIsNull() throws IOException {
        for (Application application : getTestApplications()) {
            List<Volunteer> volunteers = null;

            application.setVolunteers(volunteers);
        }    // for
    }    // setVolunteersThrowsExceptionWhenVolunteersIsNull()

    /**
     * Tests that {@link Application#setVolunteers(List)} throws a
     * {@link NullPointerException} when volunteers is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void setVolunteersThrowsExceptionWhenVolunteersContainsNull() throws IOException {
        for (Application application : getTestApplications()) {
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    null);    // volunteers

            application.setVolunteers(volunteers);
        }    // for
    }    // setVolunteersThrowsExceptionWhenVolunteersContainsNull()

    /**
     * Tests that {@link Application#setVolunteers(List)} does not throw an
     * exception when volunteers is not null and contains no nulls.
     */
    @Test
    public void setVolunteersDoesNotThrowExceptionNormally() throws IOException {
        for (Application application : getTestApplications()) {
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers

            application.setVolunteers(volunteers);
        }    // for
    }    // setVolunteersDoesNotThrowExceptionNormally()

    /**
     * Tests that {@link Application#setVolunteers(List)} does not alter its
     * argument.
     */
    @Test
    public void setVolunteersDoesNotAlterArgument() throws IOException {
        for (Application application : getTestApplications()) {
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers
            List<Volunteer> expected = new ArrayList<>(volunteers);

            application.setVolunteers(volunteers);

            List<Volunteer> received = volunteers;
            assertEquals(expected, received);
        }    // for
    }    // setVolunteersDoesNotAlterArgument()

    /**
     * Tests that {@link Application#getVolunteers()} returns a list that is
     * equal to that passed to {@link Application#setVolunteers(List)}.
     */
    @Test
    public void getVolunteersSetVolunteersListsAreEqual() throws IOException {
        for (Application application : getTestApplications()) {
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers
            application.setVolunteers(volunteers);

            List<Volunteer> received = application.getVolunteers();

            List<Volunteer> expected = volunteers;
            assertEquals(expected, received);
        }    // for
    }    // getVolunteersSetVolunteersListsAreEqual()

    /**
     * Tests that {@link Application#getVolunteers()} returns a list that is not
     * identical to that passed to {@link Application#setVolunteers(List)}.
     */
    @Test
    public void getVolunteersSetVolunteersListsAreNotIdentical() throws IOException {
        for (Application application : getTestApplications()) {
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers
            application.setVolunteers(volunteers);

            List<Volunteer> received = application.getVolunteers();

            List<Volunteer> notExpected = volunteers;
            assertFalse(notExpected == received);
        }    // for
    }    // getVolunteersSetVolunteersListsAreNotIdentical()

    /* getRoles / setRoles */

    /**
     * Tests that {@link Application#getRoles()} does not throw an
     * exception when there are no roles.
     */
    @Test
    public void getRolesDoesNotThrowExceptionWhenThereAreNoRoles() {
        for (Application application : getTestApplications()) {
            application.getRoles();
        }    // for
    }    // getRolesDoesNotThrowExceptionWhenThereAreNoRoles()

    /**
     * Tests that {@link Application#getRoles()} does not return null when
     * there are no roles.
     */
    @Test
    public void getRolesDoesNotReturnNullWhenThereAreNoRoles() {
        for (Application application : getTestApplications()) {
            List<Role> received = application.getRoles();

            assertNotNull(received);
        }    // for
    }    // getRolesDoesNotReturnNullWhenThereAreNoRoles()

    /**
     * Tests that {@link Application#getRoles()} does not throw an
     * exception when there are roles.
     */
    @Test
    public void getRolesDoesNotThrowExceptionWhenThereAreRoles() throws IOException {
        for (Application application : getTestApplications()) {
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
            application.setRoles(roles);

            application.getRoles();
        }    // for
    }    // getRolesDoesNotThrowExceptionWhenThereAreRoles()

    /**
     * Tests that {@link Application#getRoles()} does not return null when
     * there are roles.
     */
    @Test
    public void getRolesDoesNotReturnNullWhenThereAreRoles() throws IOException {
        for (Application application : getTestApplications()) {
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
            application.setRoles(roles);

            List<Role> received = application.getRoles();

            assertNotNull(received);
        }    // for
    }    // getRolesDoesNotReturnNullWhenThereAreRoles()

    /**
     * Tests that {@link Application#setRoles(List)} throws a
     * {@link NullPointerException} when roles is null.
     */
    @Test(expected = NullPointerException.class)
    public void setRolesThrowsExceptionWhenRolesIsNull() throws IOException {
        for (Application application : getTestApplications()) {
            List<Role> roles = null;

            application.setRoles(roles);
        }    // for
    }    // setRolesThrowsExceptionWhenRolesIsNull()

    /**
     * Tests that {@link Application#setRoles(List)} throws a
     * {@link NullPointerException} when roles is not null but contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void setRolesThrowsExceptionWhenRolesContainsNull() throws IOException {
        for (Application application : getTestApplications()) {
            List<Role> roles = Arrays.asList(new Role("Foo"), null);

            application.setRoles(roles);
        }    // for
    }    // setRolesThrowsExceptionWhenRolesContainsNull()

    /**
     * Tests that {@link Application#setRoles(List)} does not throw an
     * exception when roles is not null and contains no nulls.
     */
    @Test
    public void setRolesDoesNotThrowExceptionNormally() throws IOException {
        for (Application application : getTestApplications()) {
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

            application.setRoles(roles);
        }    // for
    }    // setRolesDoesNotThrowExceptionNormally()

    /**
     * Tests that {@link Application#setRoles(List)} does not alter its
     * argument.
     */
    @Test
    public void setRolesDoesNotAlterArgument() throws IOException {
        for (Application application : getTestApplications()) {
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
            List<Role> expected = new ArrayList<>(roles);

            application.setRoles(roles);

            List<Role> received = roles;
            assertEquals(expected, received);
        }    // for
    }    // setRolesDoesNotAlterArgument()

    /**
     * Tests that {@link Application#getRoles()} returns a list that is
     * equal to that passed to {@link Application#setRoles(List)}.
     */
    @Test
    public void getRolesSetRolesListsAreEqual() throws IOException {
        for (Application application : getTestApplications()) {
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
            application.setRoles(roles);

            List<Role> received = application.getRoles();

            List<Role> expected = roles;
            assertEquals(expected, received);
        }    // for
    }    // getRolesSetRolesListsAreEqual()

    /**
     * Tests that {@link Application#getRoles()} returns a list that is not
     * identical to that passed to {@link Application#setRoles(List)}.
     */
    @Test
    public void getRolesSetRolesListsAreNotIdentical() throws IOException {
        for (Application application : getTestApplications()) {
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));
            application.setRoles(roles);

            List<Role> received = application.getRoles();

            List<Role> notExpected = roles;
            assertFalse(notExpected == received);
        }    // for
    }    // getRolesSetRolesListsAreNotIdentical()

    /* getEmailTemplate / setEmailTemplate */

    /**
     * Tests that {@link Application#getEmailTemplate()} does not throw an
     * exception.
     */
    @Test
    public void getEmailTemplateDoesNotThrowException() throws IOException {
        for (Application application : getTestApplications()) {
            application.getEmailTemplate();
        }    // for
    }    // getEmailTemplateDoesNotThrowException()

    /**
     * Tests that {@link Application#getEmailTemplate()} does not return null.
     */
    @Test
    public void getEmailTemplateDoesNotReturnNull() throws IOException {
        for (Application application : getTestApplications()) {
            EmailTemplate received = application.getEmailTemplate();

            assertNotNull(received);
        }    // for
    }    // getEmailTemplateDoesNotReturnNull()

    /**
     * Tests that {@link Application#setEmailTemplate(bscmail.EmailTemplate)}
     * throws a {@link NullPointerException} when emailTemplate is null.
     */
    @Test(expected = NullPointerException.class)
    public void setEmailTemplateThrowsExceptionWhenEmailTemplateIsNull() throws IOException {
        for (Application application : getTestApplications()) {
            EmailTemplate emailTemplate = null;

            application.setEmailTemplate(emailTemplate);
        }    // for
    }    // setEmailTemplateThrowsExceptionWhenEmailTemplateIsNull()

    /**
     * Tests that {@link Application#setEmailTemplate(bscmail.EmailTemplate)} does not throw an
     * exception when emailTemplate is not null.
     */
    @Test
    public void setEmailTemplateDoesNotThrowExceptionWhenEmailTemplateIsNotNull() throws IOException {
        for (Application application : getTestApplications()) {
            EmailTemplate emailTemplate = new EmailTemplate(EmailTemplate.SendType.TO, "", "", "", "");

            application.setEmailTemplate(emailTemplate);
        }    // for
    }    // setEmailTemplateDoesNotThrowExceptionWhenEmailTemplateIsNotNull()

    /**
     * Tests that the email template returned by
     * {@link Application#setEmailTemplate(bscmail.EmailTemplate)} is equal to
     * the email template passed to {@link Application#setEmailTemplate(List)}.
     */
    @Test
    public void getEmailTemplateReturnsArgumentPassedToSetEmailTemplate() throws IOException {
        for (Application application : getTestApplications()) {
            EmailTemplate emailTemplate = new EmailTemplate(EmailTemplate.SendType.TO, "foo", "bar", "baz", "'smurf'");
            application.setEmailTemplate(emailTemplate);

            EmailTemplate received = application.getEmailTemplate();

            EmailTemplate expected = emailTemplate;
            assertEquals(expected, received);
        }    // for
    }    // getEmailTemplateReturnsArgumentPassedToSetEmailTemplate()

    /* getEmailServerProperties / setEmailServerProperties */

    /**
     * Tests that {@link Application#getEmailServerProperties()} does not throw
     * an exception.
     */
    @Test
    public void getEmailServerPropertiesDoesNotThrowException() throws IOException {
        for (Application application : getTestApplications()) {
            application.getEmailServerProperties();
        }    // for
    }    // getEmailServerPropertiesDoesNotThrowException()

    /**
     * Tests that {@link Application#getEmailServerProperties()} does not return
     * null.
     */
    @Test
    public void getEmailServerPropertiesDoesNotReturnNull() throws IOException {
        for (Application application : getTestApplications()) {
            EmailServerProperties received = application.getEmailServerProperties();

            assertNotNull(received);
        }    // for
    }    // getEmailServerPropertiesDoesNotReturnNull()

    /**
     * Tests that
     * {@link Application#setEmailServerProperties(bscmail.EmailServerProperties)}
     * throws a {@link NullPointerException} when emailServerProperties is null.
     */
    @Test(expected = NullPointerException.class)
    public void setEmailServerPropertiesThrowsExceptionWhenEmailServerPropertiesIsNull() throws IOException {
        for (Application application : getTestApplications()) {
            EmailServerProperties emailServerProperties = null;

            application.setEmailServerProperties(emailServerProperties);
        }    // for
    }    // setEmailServerPropertiesThrowsExceptionWhenEmailServerPropertiesIsNull()

    /**
     * Tests that
     * {@link Application#setEmailServerProperties(bscmail.EmailServerProperties)}
     * does not throw an exception when emailServerProperties is not null.
     */
    @Test
    public void setEmailServerPropertiesDoesNotThrowExceptionWhenEmailServerPropertiesIsNotNull() throws IOException {
        for (Application application : getTestApplications()) {
            EmailServerProperties emailServerProperties = new EmailServerProperties("foo", "bar", "baz", false);

            application.setEmailServerProperties(emailServerProperties);
        }    // for
    }    // setEmailServerPropertiesDoesNotThrowExceptionWhenEmailServerPropertiesIsNotNull()

    /**
     * Tests that the email template returned by
     * {@link Application#setEmailServerProperties(bscmail.EmailServerProperties)}
     * is equal to the email template passed to
     * {@link Application#setEmailServerProperties(List)}.
     */
    @Test
    public void getEmailServerPropertiesReturnsArgumentPassedToSetEmailServerProperties() throws IOException {
        for (Application application : getTestApplications()) {
            EmailServerProperties emailServerProperties = new EmailServerProperties("foo", "bar", "baz", false);
            application.setEmailServerProperties(emailServerProperties);

            EmailServerProperties received = application.getEmailServerProperties();

            EmailServerProperties expected = emailServerProperties;
            assertEquals(expected, received);
        }    // for
    }    // getEmailServerPropertiesReturnsArgumentPassedToSetEmailServerProperties()

    /* getEventProperties / setEventProperties */

    /**
     * Tests that {@link Application#getEventProperties()} does not throw an
     * exception when there are no event properties.
     */
    @Test
    public void getEventPropertiesDoesNotThrowExceptionWhenThereAreNoEventProperties() {
        for (Application application : getTestApplications()) {
            application.getEventProperties();
        }    // for
    }    // getEventPropertiesDoesNotThrowExceptionWhenThereAreNoEventProperties()

    /**
     * Tests that {@link Application#getEventProperties()} does not return null
     * when there are no event properties.
     */
    @Test
    public void getEventPropertiesDoesNotReturnNullWhenThereAreNoEventProperties() {
        for (Application application : getTestApplications()) {
            List<EventProperty> received = application.getEventProperties();

            assertNotNull(received);
        }    // for
    }    // getEventPropertiesDoesNotReturnNullWhenThereAreNoEventProperties()

    /**
     * Tests that {@link Application#getEventProperties()} does not throw an
     * exception when there are event properties.
     */
    @Test
    public void getEventPropertiesDoesNotThrowExceptionWhenThereAreEventProperties() throws IOException {
        for (Application application : getTestApplications()) {
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
            application.setEventProperties(eventProperties);

            application.getEventProperties();
        }    // for
    }    // getEventPropertiesDoesNotThrowExceptionWhenThereAreEventProperties()

    /**
     * Tests that {@link Application#getEventProperties()} does not return null
     * when there are event properties.
     */
    @Test
    public void getEventPropertiesDoesNotReturnNullWhenThereAreEventProperties() throws IOException {
        for (Application application : getTestApplications()) {
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
            application.setEventProperties(eventProperties);

            List<EventProperty> received = application.getEventProperties();

            assertNotNull(received);
        }    // for
    }    // getEventPropertiesDoesNotReturnNullWhenThereAreEventProperties()

    /**
     * Tests that {@link Application#setEventProperties(List)} throws a
     * {@link NullPointerException} when eventProperties is null.
     */
    @Test(expected = NullPointerException.class)
    public void setEventPropertiesThrowsExceptionWhenEventPropertiesIsNull() throws IOException {
        for (Application application : getTestApplications()) {
            List<EventProperty> eventProperties = null;

            application.setEventProperties(eventProperties);
        }    // for
    }    // setEventPropertiesThrowsExceptionWhenEventPropertiesIsNull()

    /**
     * Tests that {@link Application#setEventProperties(List)} throws a
     * {@link NullPointerException} when eventProperties is not null but
     * contains a null.
     */
    @Test(expected = NullPointerException.class)
    public void setEventPropertiesThrowsExceptionWhenEventPropertiesContainsNull() throws IOException {
        for (Application application : getTestApplications()) {
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), null);

            application.setEventProperties(eventProperties);
        }    // for
    }    // setEventPropertiesThrowsExceptionWhenEventPropertiesContainsNull()

    /**
     * Tests that {@link Application#setEventProperties(List)} does not throw an
     * exception when eventProperties is not null and contains no nulls.
     */
    @Test
    public void setEventPropertiesDoesNotThrowExceptionNormally() throws IOException {
        for (Application application : getTestApplications()) {
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

            application.setEventProperties(eventProperties);
        }    // for
    }    // setEventPropertiesDoesNotThrowExceptionNormally()

    /**
     * Tests that {@link Application#setEventProperties(List)} does not alter
     * its argument.
     */
    @Test
    public void setEventPropertiesDoesNotAlterArgument() throws IOException {
        for (Application application : getTestApplications()) {
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
            List<EventProperty> expected = new ArrayList<>(eventProperties);

            application.setEventProperties(eventProperties);

            List<EventProperty> received = eventProperties;
            assertEquals(expected, received);
        }    // for
    }    // setEventPropertiesDoesNotAlterArgument()

    /**
     * Tests that {@link Application#getEventProperties()} returns a list that
     * is equal to that passed to {@link Application#setEventProperties(List)}.
     */
    @Test
    public void getEventPropertiesSetEventPropertiesListsAreEqual() throws IOException {
        for (Application application : getTestApplications()) {
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
            application.setEventProperties(eventProperties);

            List<EventProperty> received = application.getEventProperties();

            List<EventProperty> expected = eventProperties;
            assertEquals(expected, received);
        }    // for
    }    // getEventPropertiesSetEventPropertiesListsAreEqual()

    /**
     * Tests that {@link Application#getEventProperties()} returns a list that
     * is not identical to that passed to
     * {@link Application#setEventProperties(List)}.
     */
    @Test
    public void getEventPropertiesSetEventPropertiesListsAreNotIdentical() throws IOException {
        for (Application application : getTestApplications()) {
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));
            application.setEventProperties(eventProperties);

            List<EventProperty> received = application.getEventProperties();

            List<EventProperty> notExpected = eventProperties;
            assertFalse(notExpected == received);
        }    // for
    }    // getEventPropertiesSetEventPropertiesListsAreNotIdentical()

    /* registerObserver(ShiftsObserver) */

    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverShiftsObserverThrowsExceptionWhenObserverIsNull() {
        for (Application application : getTestApplications()) {
            ShiftsObserver observer = null;

            application.registerObserver(observer);
        }    // for
    }    // registerObserverShiftsObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void registerObserverShiftsObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        for (Application application : getTestApplications()) {
            ShiftsObserver observer = new ApplicationObserver();

            application.registerObserver(observer);
        }    // for
    }    // registerObserverShiftsObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(ShiftsObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverShiftsObserverDoesNotThrowExceptionWhenCalledTwice() {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};

            for (ShiftsObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
        }    // for
    }    // registerObserverShiftsObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setShifts(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setShiftsNotifiesAllShiftsObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};
            for (ShiftsObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                    new Shift("Bar", new LinkedList<Role>(), false, false, false));

            application.setShifts(shifts);

            for (ApplicationObserver observer : observers) {
                assertTrue(observer.getShiftsChanged());
            }    // for
        }    // for
    }    // setShiftsNotifiesAllShiftsObservers()

    /**
     * Tests that a call to {@link Application#setVolunteers(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setVolunteersDoesNotNotifyShiftsObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((ShiftsObserver) observer);
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers

            application.setVolunteers(volunteers);

            assertFalse(observer.getShiftsChanged());
        }    // for
    }    // setVolunteersDoesNotNotifyShiftsObservers()

    /**
     * Tests that a call to {@link Application#Roles(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setRolesDoesNotNotifyShiftsObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((ShiftsObserver) observer);
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

            application.setRoles(roles);

            assertFalse(observer.getShiftsChanged());
        }    // for
    }    // setRolesDoesNotNotifyShiftsObservers()

    /**
     * Tests that a call to
     * {@link Application#setEmailTemplate(bscmail.EmailTemplate)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setEmailTemplateDoesNotNotifyShiftObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((ShiftsObserver) observer);
            EmailTemplate emailTemplate = new EmailTemplate(EmailTemplate.SendType.TO, "Foo", "Bar", "Baz", "'Smurf'");

            application.setEmailTemplate(emailTemplate);

            assertFalse(observer.getShiftsChanged());
        }    // for
    }    // setEmailTemplateDoesNotNotifyShiftObservers()

    /**
     * Tests that a call to
     * {@link Application#setEmailServerProperties(bscmail.EmailServerProperties)}
     * does not notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setEmailServerPropertiesDoesNotNotifyShiftObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((ShiftsObserver) observer);
            EmailServerProperties emailTemplate = new EmailServerProperties("Foo", "Bar", "Baz", false);

            application.setEmailServerProperties(emailTemplate);

            assertFalse(observer.getShiftsChanged());
        }    // for
    }    // setEmailServerPropertiesDoesNotNotifyShiftObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(ShiftsObserver)}.
     */
    @Test
    public void setEventPropertiesDoesNotNotifyShiftsObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((ShiftsObserver) observer);
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

            application.setEventProperties(eventProperties);

            assertFalse(observer.getShiftsChanged());
        }    // for
    }    // setEventPropertiesDoesNotNotifyShiftsObservers()

    /* registerObserver(VolunteersObserver) */

    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverVolunteersObserverThrowsExceptionWhenObserverIsNull() {
        for (Application application : getTestApplications()) {
            VolunteersObserver observer = null;

            application.registerObserver(observer);
        }    // for
    }    // registerObserverVolunteersObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void registerObserverVolunteersObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        for (Application application : getTestApplications()) {
            VolunteersObserver observer = new ApplicationObserver();

            application.registerObserver(observer);
        }    // for
    }    // registerObserverVolunteersObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(VolunteersObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverVolunteersObserverDoesNotThrowExceptionWhenCalledTwice() {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};

            for (VolunteersObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
        }    // for
    }    // registerObserverVolunteersObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setVolunteers(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setVolunteersNotifiesAllVolunteersObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};
            for (VolunteersObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers

            application.setVolunteers(volunteers);

            for (ApplicationObserver observer : observers) {
                assertTrue(observer.getVolunteersChanged());
            }    // for
        }    // for
    }    // setVolunteersNotifiesAllVolunteersObservers()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setShiftsDoesNotNotifyVolunteersObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((VolunteersObserver) observer);
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                    new Shift("Bar", new LinkedList<Role>(), false, false, false));

            application.setShifts(shifts);

            assertFalse(observer.getVolunteersChanged());
        }    // for
    }    // setShiftsDoesNotNotifyVolunteersObservers()

    /**
     * Tests that a call to {@link Application#Roles(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setRolesDoesNotNotifyVolunteersObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((VolunteersObserver) observer);
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

            application.setRoles(roles);

            assertFalse(observer.getVolunteersChanged());
        }    // for
    }    // setRolesDoesNotNotifyVolunteersObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setEmailTemplateDoesNotNotifyVolunteerObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((VolunteersObserver) observer);
            EmailTemplate emailTemplate = new EmailTemplate(EmailTemplate.SendType.TO, "Foo", "Bar", "Baz", "'Smurf'");

            application.setEmailTemplate(emailTemplate);

            assertFalse(observer.getVolunteersChanged());
        }    // for
    }    // setEmailTemplateDoesNotNotifyVolunteerObservers()

    /**
     * Tests that a call to
     * {@link Application#setEmailServerProperties(bscmail.EmailServerProperties)}
     * does not notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setEmailServerPropertiesDoesNotNotifyVolunteerObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((VolunteersObserver) observer);
            EmailServerProperties emailTemplate = new EmailServerProperties("Foo", "Bar", "Baz", false);

            application.setEmailServerProperties(emailTemplate);

            assertFalse(observer.getVolunteersChanged());
        }    // for
    }    // setEmailServerPropertiesDoesNotNotifyVolunteerObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setEventPropertiesDoesNotNotifyVolunteersObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((VolunteersObserver) observer);
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

            application.setEventProperties(eventProperties);

            assertFalse(observer.getVolunteersChanged());
        }    // for
    }    // setEventPropertiesDoesNotNotifyVolunteersObservers()

    /* registerObserver(RolesObserver) */

    /**
     * Tests that {@link Application#registerObserver(RolesObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverRolesObserverThrowsExceptionWhenObserverIsNull() {
        for (Application application : getTestApplications()) {
            RolesObserver observer = null;

            application.registerObserver(observer);
        }    // for
    }    // registerObserverRolesObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(RolesObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void registerObserverRolesObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        for (Application application : getTestApplications()) {
            RolesObserver observer = new ApplicationObserver();

            application.registerObserver(observer);
        }    // for
    }    // registerObserverRolesObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(RolesObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverRolesObserverDoesNotThrowExceptionWhenCalledTwice() {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};

            for (RolesObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
        }    // for
    }    // registerObserverRolesObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setRoles(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(RolesObserver)}.
     */
    @Test
    public void setRolesNotifiesAllRolesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};
            for (RolesObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

            application.setRoles(roles);

            for (ApplicationObserver observer : observers) {
                assertTrue(observer.getRolesChanged());
            }    // for
        }    // for
    }    // setRolesNotifiesAllRolesObservers()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(RolesObserver)}.
     */
    @Test
    public void setShiftsDoesNotNotifyRolesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((RolesObserver) observer);
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                    new Shift("Bar", new LinkedList<Role>(), false, false, false));

            application.setShifts(shifts);

            assertFalse(observer.getRolesChanged());
        }    // for
    }    // setShiftsDoesNotNotifyRolesObservers()

    /**
     * Tests that a call to {@link Application#Volunteers(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setVolunteersDoesNotNotifyRolesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((RolesObserver) observer);
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers

            application.setVolunteers(volunteers);

            assertFalse(observer.getVolunteersChanged());
        }    // for
    }    // setVolunteersDoesNotNotifyRolesObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(RolesObserver)}.
     */
    @Test
    public void setEmailTemplateDoesNotNotifyRoleObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((RolesObserver) observer);
            EmailTemplate emailTemplate = new EmailTemplate(EmailTemplate.SendType.TO, "Foo", "Bar", "Baz", "'Smurf'");

            application.setEmailTemplate(emailTemplate);

            assertFalse(observer.getRolesChanged());
        }    // for
    }    // setEmailTemplateDoesNotNotifyRoleObservers()

    /**
     * Tests that a call to
     * {@link Application#setEmailServerProperties(bscmail.EmailServerProperties)}
     * does not notify any observers registered with
     * {@link Application#registerObserver(RolesObserver)}.
     */
    @Test
    public void setEmailServerPropertiesDoesNotNotifyRoleObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((RolesObserver) observer);
            EmailServerProperties emailTemplate = new EmailServerProperties("Foo", "Bar", "Baz", false);

            application.setEmailServerProperties(emailTemplate);

            assertFalse(observer.getRolesChanged());
        }    // for
    }    // setEmailServerPropertiesDoesNotNotifyRoleObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(RolesObserver)}.
     */
    @Test
    public void setEventPropertiesDoesNotNotifyRolesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((RolesObserver) observer);
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

            application.setEventProperties(eventProperties);

            assertFalse(observer.getRolesChanged());
        }    // for
    }    // setEventPropertiesDoesNotNotifyRolesObservers()

    /* registerObserver(EmailTemplateObserver) */

    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)}
     * throws a {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverEmailTemplateObserverThrowsExceptionWhenObserverIsNull() {
        for (Application application : getTestApplications()) {
            EmailTemplateObserver observer = null;

            application.registerObserver(observer);
        }    // for
    }    // registerObserverEmailTemplateObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)}
     * does not throw an exception when observer is not null.
     */
    @Test
    public void registerObserverEmailTemplateObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        for (Application application : getTestApplications()) {
            EmailTemplateObserver observer = new ApplicationObserver();

            application.registerObserver(observer);
        }    // for
    }    // registerObserverEmailTemplateObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(EmailTemplateObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverEmailTemplateObserverDoesNotThrowExceptionWhenCalledTwice() {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};

            for (EmailTemplateObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
        }    // for
    }    // registerObserverEmailTemplateObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void setEmailTemplateNotifiesAllEmailTemplateObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};
            for (EmailTemplateObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
            EmailTemplate emailTemplate = new EmailTemplate(EmailTemplate.SendType.TO, "Foo", "Bar", "Baz", "'Smurf'");

            application.setEmailTemplate(emailTemplate);

            for (ApplicationObserver observer : observers) {
                assertTrue(observer.getEmailTemplateChanged());
            }    // for
        }    // for
    }    // setEmailTemplateNotifiesAllEmailTemplateObservers()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void setShiftsDoesNotNotifyEmailTemplateObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailTemplateObserver) observer);
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                    new Shift("Bar", new LinkedList<Role>(), false, false, false));

            application.setShifts(shifts);

            assertFalse(observer.getEmailTemplateChanged());
        }    // for
    }    // setShiftsDoesNotNotifyEmailTemplateObservers()

    /**
     * Tests that a call to {@link Application#Volunteers(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setVolunteersDoesNotNotifyEmailTemplateObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailTemplateObserver) observer);
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers

            application.setVolunteers(volunteers);

            assertFalse(observer.getVolunteersChanged());
        }    // for
    }    // setVolunteersDoesNotNotifyEmailTemplateObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void setRolesDoesNotNotifyEmailTemplateObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailTemplateObserver) observer);
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

            application.setRoles(roles);

            assertFalse(observer.getEmailTemplateChanged());
        }    // for
    }    // setRolesDoesNotNotifyEmailTemplateObservers()

    /**
     * Tests that a call to
     * {@link Application#setEmailServerProperties(bscmail.EmailServerProperties)}
     * does not notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void setEmailServerPropertiesDoesNotNotifyEmailTemplateObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailTemplateObserver) observer);
            EmailServerProperties emailTemplate = new EmailServerProperties("Foo", "Bar", "Baz", false);

            application.setEmailServerProperties(emailTemplate);

            assertFalse(observer.getEmailTemplateChanged());
        }    // for
    }    // setEmailServerPropertiesDoesNotNotifyEmailTemplateObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EmailTemplateObserver)}.
     */
    @Test
    public void setEventPropertiesDoesNotNotifyEmailTemplateObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailTemplateObserver) observer);
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

            application.setEventProperties(eventProperties);

            assertFalse(observer.getEmailTemplateChanged());
        }    // for
    }    // setEventPropertiesDoesNotNotifyEmailTemplateObservers()

    /* registerObserver(EmailServerPropertiesObserver) */

    /**
     * Tests that
     * {@link Application#registerObserver(EmailServerPropertiesObserver)}
     * throws a {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverEmailServerPropertiesObserverThrowsExceptionWhenObserverIsNull() {
        for (Application application : getTestApplications()) {
            EmailServerPropertiesObserver observer = null;

            application.registerObserver(observer);
        }    // for
    }    // registerObserverEmailServerPropertiesObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that
     * {@link Application#registerObserver(EmailServerPropertiesObserver)} does
     * not throw an exception when observer is not null.
     */
    @Test
    public void registerObserverEmailServerPropertiesObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        for (Application application : getTestApplications()) {
            EmailServerPropertiesObserver observer = new ApplicationObserver();

            application.registerObserver(observer);
        }    // for
    }    // registerObserverEmailServerPropertiesObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that
     * {@link Application#registerObserver(EmailServerPropertiesObserver)} does
     * not throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverEmailServerPropertiesObserverDoesNotThrowExceptionWhenCalledTwice() {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};

            for (EmailServerPropertiesObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
        }    // for
    }    // registerObserverEmailServerPropertiesObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setEmailServerProperties(List)}
     * notifies all observers registered with
     * {@link Application#registerObserver(EmailServerPropertiesObserver)}.
     */
    @Test
    public void setEmailServerPropertiesNotifiesAllEmailServerPropertiesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};
            for (EmailServerPropertiesObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
            EmailServerProperties emailTemplate = new EmailServerProperties("Foo", "Bar", "Baz", false);

            application.setEmailServerProperties(emailTemplate);

            for (ApplicationObserver observer : observers) {
                assertTrue(observer.getEmailServerPropertiesChanged());
            }    // for
        }    // for
    }    // setEmailServerPropertiesNotifiesAllEmailServerPropertiesObservers()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not notify
     * any observers registered with
     * {@link Application#registerObserver(EmailServerPropertiesObserver)}.
     */
    @Test
    public void setShiftsDoesNotNotifyEmailServerPropertiesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailServerPropertiesObserver) observer);
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                    new Shift("Bar", new LinkedList<Role>(), false, false, false));

            application.setShifts(shifts);

            assertFalse(observer.getEmailServerPropertiesChanged());
        }    // for
    }    // setShiftsDoesNotNotifyEmailServerPropertiesObservers()

    /**
     * Tests that a call to {@link Application#Volunteers(List)} does not notify
     * any observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setVolunteersDoesNotNotifyEmailServerPropertiesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailServerPropertiesObserver) observer);
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers

            application.setVolunteers(volunteers);

            assertFalse(observer.getEmailServerPropertiesChanged());
        }    // for
    }    // setVolunteersDoesNotNotifyEmailServerPropertiesObservers()

    /**
     * Tests that a call to {@link Application#setRoles(List)} does not notify
     * any observers registered with
     * {@link Application#registerObserver(EmailServerPropertiesObserver)}.
     */
    @Test
    public void setRolesDoesNotNotifyEmailServerPropertiesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailServerPropertiesObserver) observer);
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

            application.setRoles(roles);

            assertFalse(observer.getEmailServerPropertiesChanged());
        }    // for
    }    // setRolesDoesNotNotifyEmailServerPropertiesObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EmailServerPropertiesObserver)}.
     */
    @Test
    public void setEmailTemplateDoesNotNotifyEmailServerPropertiesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailServerPropertiesObserver) observer);
            EmailTemplate emailTemplate = new EmailTemplate(EmailTemplate.SendType.TO, "Foo", "Bar", "Baz", "'Smurf'");

            application.setEmailTemplate(emailTemplate);

            assertFalse(observer.getEmailServerPropertiesChanged());
        }    // for
    }    // setEmailTemplateDoesNotNotifyEmailServerPropertiesObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EmailServerPropertiesObserver)}.
     */
    @Test
    public void setEventPropertiesDoesNotNotifyEmailServerPropertiesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EmailServerPropertiesObserver) observer);
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

            application.setEventProperties(eventProperties);

            assertFalse(observer.getEmailServerPropertiesChanged());
        }    // for
    }    // setEventPropertiesDoesNotNotifyEmailServerPropertiesObservers()

    /* registerObserver(EventPropertiesObserver) */

    /**
     * Tests that {@link Application#registerObserver(EventPropertiesObserver)} throws a
     * {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverEventPropertiesObserverThrowsExceptionWhenObserverIsNull() {
        for (Application application : getTestApplications()) {
            EventPropertiesObserver observer = null;

            application.registerObserver(observer);
        }    // for
    }    // registerObserverEventPropertiesObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Application#registerObserver(EventPropertiesObserver)} does not
     * throw an exception when observer is not null.
     */
    @Test
    public void registerObserverEventPropertiesObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        for (Application application : getTestApplications()) {
            EventPropertiesObserver observer = new ApplicationObserver();

            application.registerObserver(observer);
        }    // for
    }    // registerObserverEventPropertiesObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Application#registerObserver(EventPropertiesObserver)} does not
     * throw an exception when called twice with different observers.
     */
    @Test
    public void registerObserverEventPropertiesObserverDoesNotThrowExceptionWhenCalledTwice() {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};

            for (EventPropertiesObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
        }    // for
    }    // registerObserverEventPropertiesObserverDoesNotThrowExceptionWhenCalledTwice()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} notifies all
     * observers registered with
     * {@link Application#registerObserver(EventPropertyObserver)}.
     */
    @Test
    public void setEventPropertiesNotifiesAllEventPropertyObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver[] observers = {new ApplicationObserver(), new ApplicationObserver()};
            for (EventPropertiesObserver observer : observers) {
                application.registerObserver(observer);
            }    // for
            List<EventProperty> eventProperties = Arrays.asList(new EventProperty("Foo", "foo"), new EventProperty("Bar", "bar"));

            application.setEventProperties(eventProperties);

            for (ApplicationObserver observer : observers) {
                assertTrue(observer.getEventPropertiesChanged());
            }    // for
        }    // for
    }    // setEventPropertiesNotifiesAllEventPropertyObservers()

    /**
     * Tests that a call to {@link Application#setShifts(List)} does not
     * notify any observers registered with
     * {@link Application#registerObserver(EventPropertyObserver)}.
     */
    @Test
    public void setShiftsDoesNotNotifyEventPropertyObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EventPropertiesObserver) observer);
            List<Shift> shifts = Arrays.asList(new Shift("Foo", new LinkedList<Role>(), false, false, false),
                    new Shift("Bar", new LinkedList<Role>(), false, false, false));

            application.setShifts(shifts);

            assertFalse(observer.getEventPropertiesChanged());
        }    // for
    }    // setShiftsDoesNotNotifyEventPropertyObservers()

    /**
     * Tests that a call to {@link Application#Volunteers(List)} does not notify any
     * observers registered with
     * {@link Application#registerObserver(VolunteersObserver)}.
     */
    @Test
    public void setVolunteersDoesNotNotifyEventPropertyObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EventPropertiesObserver) observer);
            List<Volunteer> volunteers = Arrays.asList(
                    new Volunteer("Foo", "foo", "", "", true, Arrays.asList()),
                    new Volunteer("Bar", "bar", "", "", true, Arrays.asList()));    // volunteers

            application.setVolunteers(volunteers);

            assertFalse(observer.getVolunteersChanged());
        }    // for
    }    // setVolunteersDoesNotNotifyEventPropertyObservers()

    /**
     * Tests that a call to {@link Application#setEventProperties(List)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EventPropertiesObserver)}.
     */
    @Test
    public void setRolesDoesNotNotifyEventPropertyObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EventPropertiesObserver) observer);
            List<Role> roles = Arrays.asList(new Role("Foo"), new Role("Bar"));

            application.setRoles(roles);

            assertFalse(observer.getRolesChanged());
        }    // for
    }    // setRolesDoesNotNotifyEventPropertyObservers()

    /**
     * Tests that a call to {@link Application#setEmailTemplate(Reader)} does
     * not notify any observers registered with
     * {@link Application#registerObserver(EventPropertiesObserver)}.
     */
    @Test
    public void setEmailTemplateDoesNotNotifyEventPropertyObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EventPropertiesObserver) observer);
            EmailTemplate emailTemplate = new EmailTemplate(EmailTemplate.SendType.TO, "Foo", "Bar", "Baz", "'Smurf'");

            application.setEmailTemplate(emailTemplate);

            assertFalse(observer.getEventPropertiesChanged());
        }    // for
    }    // setEmailTemplateDoesNotNotifyEventPropertyObservers()

    /**
     * Tests that a call to
     * {@link Application#setEmailServerProperties(bscmail.EmailServerProperties)}
     * does not notify any observers registered with
     * {@link Application#registerObserver(EventPropertiesObserver)}.
     */
    @Test
    public void setEmailServerPropertiesDoesNotNotifyEventPropertiesObservers() throws IOException {
        for (Application application : getTestApplications()) {
            ApplicationObserver observer = new ApplicationObserver();
            application.registerObserver((EventPropertiesObserver) observer);
            EmailServerProperties emailServerProperties = new EmailServerProperties("Foo", "Bar", "Baz", false);

            application.setEmailServerProperties(emailServerProperties);

            assertFalse(observer.getEventPropertiesChanged());
        }    // for
    }    // setEmailServerPropertiesDoesNotNotifyEventPropertiesObservers()

}    // ApplicationTest
