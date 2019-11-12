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

import io.github.waynem77.bscmail.gui.error.ErrorDialog;
import io.github.waynem77.bscmail.help.HelpDisplay;
import io.github.waynem77.bscmail.help.HelpDisplayFactory;
import io.github.waynem77.bscmail.iolayer.IOLayer;
import io.github.waynem77.bscmail.iolayer.IOLayerFactory;
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
import io.github.waynem77.bscmail.persistent.Volunteer;
import io.github.waynem77.bscmail.persistent.VolunteersObserver;
import io.github.waynem77.bscmail.util.parser.CsvStringParser;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.swing.JFrame;

/**
 * An application controls application-wide properties and objects.
 *
 * @since 2.0
 * @author Wayne Miller
 */
public class Application {

    /*
     * Static classes and methods
     */

    /**
     * A set of enums corresponding to the required keys of the properties
     * argument of {@link Application#createApplication(Properties)}. The return
     * value of {@link #toString()} for the enum is the value of the
     * corresponding key.
     *
     * @since 4.0
     */
    public static enum PropertyKey {

        /**
         * "application.name", the application name.
         */
        APPLICATION_NAME ("application.name"),

        /**
         * "application.version", the application version.
         */
        APPLICATION_VERSION ("application.version"),


        /**
         * "application.copyright", the application copyright string.
         */
        APPLICATION_COPYRIGHT ("application.copyright"),

        /**
         * "application.aboutMessage", the application about message.
         */
        APPLICATION_ABOUT_MESSAGE ("application.aboutMessage"),

        /**
         * "application.iolayerFactory", the class of the {@link IOLayerFactory}
         * implementation for the application.
         */
        APPLICATION_IOLAYER_FACTORY ("application.iolayerFactory"),

        /**
         * "application.helpDisplayFactory", the class of the
         * {@link HelpDisplayFactory} implementation for the application.
         */
        APPLICATION_HELP_DISPLAY_FACTORY ("application.helpDisplayFactory"),

        /**
         * "shifts.iolayer.class", the class of the {@link IOLayerFactory}
         * implementation for the application.
         */
        SHIFTS_IOLAYER_CLASS ("shifts.iolayer.class"),

        /**
         * "shifts.iolayer.arguments", the arguments for the shifts IOLayer.
         */
        SHIFTS_IOLAYER_ARGUMENTS ("shifts.iolayer.arguments"),

        /**
         * "volunteers.iolayer.class", the class of the {@link IOLayer}
         * implementation for volunteers.
         */
        VOLUNTEERS_IOLAYER_CLASS ("volunteers.iolayer.class"),

        /**
         * "volunteers.iolayer.arguments", the arguments for the volunteers
         * IOLayer constructor.
         */
        VOLUNTEERS_IOLAYER_ARGUMENTS ("volunteers.iolayer.arguments"),

        /**
         * "roles.iolayer.class", the class of the {@link IOLayer}
         * implementation for roles.
         */
        ROLES_IOLAYER_CLASS ("roles.iolayer.class"),

        /**
         * "roles.iolayer.arguments", the arguments for the roles IOLayer
         * constructor.
         */
        ROLES_IOLAYER_ARGUMENTS ("roles.iolayer.arguments"),

        /**
         * "emailTemplate.iolayer.class", the class of the {@link IOLayer}
         * implementation for the email template.
         */
        EMAIL_TEMPLATE_IOLAYER_CLASS ("emailTemplate.iolayer.class"),

        /**
         * "emailTemplate.iolayer.arguments", the arguments for the email
         * template IOLayer constructor.
         */
        EMAIL_TEMPLATE_IOLAYER_ARGUMENTS ("emailTemplate.iolayer.arguments"),

        /**
         * "emailServerProperties.iolayer.class", the class of the
         * {@link IOLayer} implementation for the email server properties.
         */
        EMAIL_SERVER_PROPERTIES_IOLAYER_CLASS ("emailServerProperties.iolayer.class"),

        /**
         * "emailServerProperties.iolayer.arguments", the arguments for the
         * email server properties IOLayer constructor.
         */
        EMAIL_SERVER_PROPERTIES_IOLAYER_ARGUMENTS ("emailServerProperties.iolayer.arguments"),

        /**
         * "eventProperties.iolayer.class", the class of the {@link IOLayer}
         * implementation for event properties.
         */
        EVENT_PROPERTIES_IOLAYER_CLASS ("eventProperties.iolayer.class"),

        /**
         * "eventProperties.iolayer.arguments", the arguments for the event
         * properties IOLayer constructor.
         */
        EVENT_PROPERTIES_IOLAYER_ARGUMENTS ("eventProperties.iolayer.arguments"),

        /**
         * "helpDisplay.class", the class of the {@link HelpDisplay}
         * implementation for the application.
         */
        HELP_DISPLAY_CLASS ("helpDisplay.class"),

        /**
         * "helpDisplay.arguments", the arguments for the HelpDisplay
         * constructor.
         */
        HELP_DISPLAY_ARGUMENTS ("helpDisplay.arguments");

        /**
         * The string value.
         */
        private final String stringValue;

        /**
         * Constructs an enum with the given string value.
         *
         * @param stringValue the string value; may not be null
         */
        private PropertyKey(String stringValue) {
            assert (stringValue != null);
            this.stringValue = stringValue;
        }    // PropertyKey()

        /**
         * Returns the key string of the corresponding property value.
         *
         * @return the key string of the corresponding property value
         */
        public String getKeyString() {
            return stringValue;
        }    // getKeyString()

        /**
         * Returns the string value of the enum. This value is the same as that
         * returned by {@link #getKeyString()}.
         *
         * @return the string value of the enum
         */
        @Override
        public String toString() {
            return stringValue;
        }    // toString()
    }    // PropertyKey

    /**
     * Creates and returns a new application from the given arguments.
     *
     * @param applicationInfo the application info object for the application;
     * may not be null
     * @param shiftsIOLayer the I/O layer to be used for storing shifts; may not
     * be null
     * @param volunteersIOLayer the I/O layer to be used for storing volunteers;
     * may not be null
     * @param rolesIOLayer the I/O layer to be used for storing roles; may not
     * be null
     * @param emailTemplateIOLayer the I/O layer to be used for storing the
     * email template; may not be null
     * @param emailServerPropertiesIOLayer the I/O layer to be used for storing
     * the email server properties; may not be null
     * @param eventPropertiesIOLayer the I/O layer to be used for storing the
     * event properties; may not be null
     * @param helpDisplay the help displayer to use; may not be null
     * @return a new Application
     * @throws NullPointerException if any parameter is null
     */
    public static Application createApplication(ApplicationInfo applicationInfo,
            IOLayer<Shift> shiftsIOLayer,
            IOLayer<Volunteer> volunteersIOLayer,
            IOLayer<Role> rolesIOLayer,
            IOLayer<EmailTemplate> emailTemplateIOLayer,
            IOLayer<EmailServerProperties> emailServerPropertiesIOLayer,
            IOLayer<EventProperty> eventPropertiesIOLayer,
            HelpDisplay helpDisplay) {
        return new Application(applicationInfo,
                shiftsIOLayer,
                volunteersIOLayer,
                rolesIOLayer,
                emailTemplateIOLayer,
                emailServerPropertiesIOLayer,
                eventPropertiesIOLayer,
                helpDisplay);
    }    // createApplication()

    /**
     * Creates a new Application from the given properties. The properties
     * object must contain the following key-value pairs.
     * <table style="border: 1px solid black">
     * <caption>Application Properties Specification</caption>
     * <tr><th>application.name</th><td>the application name</td></tr>
     * <tr><th>application.version</th><td>the application version</td></tr>
     * <tr><th>application.copyright</th><td>the application copyright string</td></tr>
     * <tr><th>application.aboutMessage</th><td>the application about message</td></tr>
     * <tr><th>application.iolayerFactory</th><td>the class of the {@link IOLayerFactory} implementation for the application</td></tr>
     * <tr><th>application.helpDisplayFactory</th><td>the class of the {@link HelpDisplayFactory} implementation for the application</td></tr>
     * <tr><th>shifts.iolayer.class</th><td>the class of the {@link IOLayer} implementation for shifts</td></tr>
     * <tr><th>shifts.iolayer.arguments</th><td>the arguments for the shifts IOLayer constructor</td></tr>
     * <tr><th>volunteers.iolayer.class</th><td>the class of the {@link IOLayer} implementation for volunteers</td></tr>
     * <tr><th>volunteers.iolayer.arguments</th><td>the arguments for the volunteers IOLayer constructor</td></tr>
     * <tr><th>roles.iolayer.class</th><td>the class of the {@link IOLayer} implementation for roles</td></tr>
     * <tr><th>roles.iolayer.arguments</th><td>the arguments for the roles IOLayer constructor</td></tr>
     * <tr><th>emailTemplate.iolayer.class</th><td>the class of the {@link IOLayer} implementation for the email template</td></tr>
     * <tr><th>emailTemplate.iolayer.arguments</th><td>the arguments for the email template IOLayer constructor</td></tr>
     * <tr><th>emailServerProperties.iolayer.class</th><td>the class of the {@link IOLayer} implementation for the email server properties</td></tr>
     * <tr><th>emailServerProperties.iolayer.arguments</th><td>the arguments for the email server properties IOLayer constructor</td></tr>
     * <tr><th>eventProperties.iolayer.class</th><td>the class of the {@link IOLayer} implementation for event properties</td></tr>
     * <tr><th>eventProperties.iolayer.arguments</th><td>the arguments for the even properties IOLayer constructor</td></tr>
     * <tr><th>helpDisplay.class</th><td>the class of the {@link HelpDisplay} implementation for the application</td></tr>
     * <tr><th>helpDisplay.arguments</th><td>the arguments for the HelpDisplay constructor</td></tr>
     * </table>
     * The {@code arguments} properties should all be comma-separated values in
     * the format specified by <a href="https://tools.ietf.org/html/rfc4180">RFC
     * 4180</a>
     *
     * @param applicationProperties the application properties; may not be null;
     * must meet the specification above
     * @return a new Application
     * @throws NullPointerException if applicationProperties is null
     * @throws IllegalArgumentException if applicationProperties does not match
     * the above specification
     * @see PropertyKey
     */
    public static Application createApplication(Properties applicationProperties) {
        if (applicationProperties == null) {
            throw new NullPointerException("applicationProperties may not be null");
        }    // if
        for (PropertyKey propertyKey : PropertyKey.values()) {
            if (!applicationProperties.containsKey(propertyKey.getKeyString())) {
                throw new IllegalArgumentException("applicationProperties must contain property \"" + propertyKey.getKeyString() + "\"");
            }    // if
        }    // for

        // Application info
        String applicationName = applicationProperties.getProperty(PropertyKey.APPLICATION_NAME.getKeyString());
        String applicationVersion = applicationProperties.getProperty(PropertyKey.APPLICATION_VERSION.getKeyString());
        String applicationCopyright = applicationProperties.getProperty(PropertyKey.APPLICATION_COPYRIGHT.getKeyString());
        String applicationAboutMessage = applicationProperties.getProperty(PropertyKey.APPLICATION_ABOUT_MESSAGE.getKeyString());
        ApplicationInfo applicationInfo = new ApplicationInfo(applicationName, applicationVersion, applicationCopyright, applicationAboutMessage);

        try {
            CsvStringParser csvParser = new CsvStringParser();

            String iolayerFactoryClassName = applicationProperties.getProperty(PropertyKey.APPLICATION_IOLAYER_FACTORY.getKeyString());
            Class iolayerFactoryClass = Class.forName(iolayerFactoryClassName);
            IOLayerFactory iolayerFactory = (IOLayerFactory) iolayerFactoryClass.getDeclaredConstructor().newInstance();

            // IOLayer: shifts
            String iolayerClassName = applicationProperties.getProperty(PropertyKey.SHIFTS_IOLAYER_CLASS.getKeyString());
            Class iolayerClass = Class.forName(iolayerClassName);
            Class rwClass = Shift.class;
            String iolayerArgumentsString = applicationProperties.getProperty(PropertyKey.SHIFTS_IOLAYER_ARGUMENTS.getKeyString());
            Object[] iolayerArguments = csvParser.parse(iolayerArgumentsString);
            IOLayer<Shift> shiftsIOLayer = iolayerFactory.createIOLayer(iolayerClass, rwClass, iolayerArguments);

            // IOLayer: volunteers
            iolayerClassName = applicationProperties.getProperty(PropertyKey.VOLUNTEERS_IOLAYER_CLASS.getKeyString());
            iolayerClass = Class.forName(iolayerClassName);
            rwClass = Volunteer.class;
            iolayerArgumentsString = applicationProperties.getProperty(PropertyKey.VOLUNTEERS_IOLAYER_ARGUMENTS.getKeyString());
            iolayerArguments = csvParser.parse(iolayerArgumentsString);
            IOLayer<Volunteer> volunteersIOLayer = iolayerFactory.createIOLayer(iolayerClass, rwClass, iolayerArguments);

            // IOLayer: roles
            iolayerClassName = applicationProperties.getProperty(PropertyKey.ROLES_IOLAYER_CLASS.getKeyString());
            iolayerClass = Class.forName(iolayerClassName);
            rwClass = Role.class;
            iolayerArgumentsString = applicationProperties.getProperty(PropertyKey.ROLES_IOLAYER_ARGUMENTS.getKeyString());
            iolayerArguments = csvParser.parse(iolayerArgumentsString);
            IOLayer<Role> rolesIOLayer = iolayerFactory.createIOLayer(iolayerClass, rwClass, iolayerArguments);

            // IOLayer: email template
            iolayerClassName = applicationProperties.getProperty(PropertyKey.EMAIL_TEMPLATE_IOLAYER_CLASS.getKeyString());
            iolayerClass = Class.forName(iolayerClassName);
            rwClass = EmailTemplate.class;
            iolayerArgumentsString = applicationProperties.getProperty(PropertyKey.EMAIL_TEMPLATE_IOLAYER_ARGUMENTS.getKeyString());
            iolayerArguments = csvParser.parse(iolayerArgumentsString);
            IOLayer<EmailTemplate> emailTemplateIOLayer = iolayerFactory.createIOLayer(iolayerClass, rwClass, iolayerArguments);

            // IOLayer: email server properties
            iolayerClassName = applicationProperties.getProperty(PropertyKey.EMAIL_SERVER_PROPERTIES_IOLAYER_CLASS.getKeyString());
            iolayerClass = Class.forName(iolayerClassName);
            rwClass = EmailServerProperties.class;
            iolayerArgumentsString = applicationProperties.getProperty(PropertyKey.EMAIL_SERVER_PROPERTIES_IOLAYER_ARGUMENTS.getKeyString());
            iolayerArguments = csvParser.parse(iolayerArgumentsString);
            IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = iolayerFactory.createIOLayer(iolayerClass, rwClass, iolayerArguments);

            // IOLayer: event properties
            iolayerClassName = applicationProperties.getProperty(PropertyKey.EVENT_PROPERTIES_IOLAYER_CLASS.getKeyString());
            iolayerClass = Class.forName(iolayerClassName);
            rwClass = EventProperty.class;
            iolayerArgumentsString = applicationProperties.getProperty(PropertyKey.EVENT_PROPERTIES_IOLAYER_ARGUMENTS.getKeyString());
            iolayerArguments = csvParser.parse(iolayerArgumentsString);
            IOLayer<EventProperty> eventPropertiesIOLayer = iolayerFactory.createIOLayer(iolayerClass, rwClass, iolayerArguments);

            String helpDisplayFactoryClassName = applicationProperties.getProperty(PropertyKey.APPLICATION_HELP_DISPLAY_FACTORY.getKeyString());
            Class helpDisplayFactoryClass = Class.forName(helpDisplayFactoryClassName);
            HelpDisplayFactory helpDisplayFactory = (HelpDisplayFactory)helpDisplayFactoryClass.getDeclaredConstructor().newInstance();

            // Help display
            String helpDisplayClassName = applicationProperties.getProperty(PropertyKey.HELP_DISPLAY_CLASS.getKeyString());
            Class helpDisplayClass = Class.forName(helpDisplayClassName);
            String helpDisplayArgumentsString = applicationProperties.getProperty(PropertyKey.HELP_DISPLAY_ARGUMENTS.getKeyString());
            Object[] helpDisplayArguments = csvParser.parse(helpDisplayArgumentsString);
            HelpDisplay helpDisplay = helpDisplayFactory.createHelpDisplay(helpDisplayClass, helpDisplayArguments);

            return new Application(applicationInfo,
                    shiftsIOLayer,
                    volunteersIOLayer,
                    rolesIOLayer,
                    emailTemplateIOLayer,
                    emailServerPropertiesIOLayer,
                    eventPropertiesIOLayer,
                    helpDisplay);
        } catch (Exception e) {    // try
            throw new ApplicationInitializationException(e);
        }    // catch
    }    // createApplication()

    /*
     * Class properties
     */

    /**
     * The application info.
     */
    private final ApplicationInfo applicationInfo;

    /**
     * The list of defined shifts.
     */
    private List<Shift> shifts;

    /**
     * The list of defined volunteers.
     */
    private List<Volunteer> volunteers;

    /**
     * The list of defined roles.
     */
    private List<Role> roles;

    /**
     * The email template.
     */
    private EmailTemplate emailTemplate;

    /**
     * The email server properties.
     */
    private EmailServerProperties emailServerProperties;

    /**
     * The list of defined event properties.
     */
    private List<EventProperty> eventProperties;

    /**
     * The shifts I/O layer.
     */
    private final IOLayer<Shift> shiftsIOLayer;

    /**
     * The volunteers I/O layer.
     */
    private final IOLayer<Volunteer> volunteersIOLayer;

    /**
     * The roles I/O layer.
     */
    private final IOLayer<Role> rolesIOLayer;

    /**
     * The email template I/O layer.
     */
    private final IOLayer<EmailTemplate> emailTemplateIOLayer;

    /**
     * The email server properties I/O layer.
     */
    private final IOLayer<EmailServerProperties> emailServerPropertiesIOLayer;

    /**
     * The event properties I/O layer.
     */
    private final IOLayer<EventProperty> eventPropertiesIOLayer;

    /**
     * The list of shifts observers.
     */
    private final List<ShiftsObserver> shiftsObservers;

    /**
     * The list of volunteers observers.
     */
    private final List<VolunteersObserver> volunteersObservers;

    /**
     * The list of volunteers observers.
     */
    private final List<RolesObserver> rolesObservers;

    /**
     * The list of email template observers.
     */
    private final List<EmailTemplateObserver> emailTemplateObservers;

    /**
     * The list of email server properties observers.
     */
    private final List<EmailServerPropertiesObserver> emailServerPropertiesObservers;

    /**
     * The list of event property observers.
     */
    private final List<EventPropertiesObserver> eventPropertiesObservers;

    /**
     * The help displayer.
     */
    private final HelpDisplay helpDisplay;

    /*
     * Class methods
     */

    /**
     * Constructs a new application.
     *
     * @param applicationInfo the application info object for this application;
     * may not be null
     * @param shiftsIOLayer the I/O layer used for storing shifts; may not be
     * null
     * @param volunteersIOLayer the I/O layer used for storing volunteers; may
     * not be null
     * @param rolesIOLayer the I/O layer used for storing roles; may not be null
     * @param emailTemplateIOLayer the I/O layer used for storing the email
     * template; may not be null
     * @param emailServerPropertiesIOLayer the I/O layer used for storing the
     * email server properties; may not be null
     * @param eventPropertiesIOLayer the I/O layer used for storing the event
     * properties; may not be null
     * @param helpDisplay the help displayer; may not be null
     * @throws NullPointerException if any parameter is null
     */
    protected Application(ApplicationInfo applicationInfo,
            IOLayer<Shift> shiftsIOLayer,
            IOLayer<Volunteer> volunteersIOLayer,
            IOLayer<Role> rolesIOLayer,
            IOLayer<EmailTemplate> emailTemplateIOLayer,
            IOLayer<EmailServerProperties> emailServerPropertiesIOLayer,
            IOLayer<EventProperty> eventPropertiesIOLayer,
            HelpDisplay helpDisplay) {
        if (applicationInfo == null) {
            throw new NullPointerException("applicationInfo may not be null");
        }    // if
        this.applicationInfo = applicationInfo;
        if (shiftsIOLayer == null) {
            throw new NullPointerException("shiftsIOLayer may not be null");
        }    // if
        this.shiftsIOLayer = shiftsIOLayer;
        if (volunteersIOLayer == null) {
            throw new NullPointerException("volunteersIOLayer may not be null");
        }    // if
        this.volunteersIOLayer = volunteersIOLayer;
        if (rolesIOLayer == null) {
            throw new NullPointerException("rolesIOLayer may not be null");
        }    // if
        this.rolesIOLayer = rolesIOLayer;
        if (emailTemplateIOLayer == null) {
            throw new NullPointerException("emailTemplateIOLayer may not be null");
        }    // if
        this.emailTemplateIOLayer = emailTemplateIOLayer;
        if (emailServerPropertiesIOLayer == null) {
            throw new NullPointerException("emailServerPropertiesIOLayer may not be null");
        }    // if
        this.emailServerPropertiesIOLayer = emailServerPropertiesIOLayer;
        if (eventPropertiesIOLayer == null) {
            throw new NullPointerException("eventPropertiesIOLayer may not be null");
        }    // if
        this.eventPropertiesIOLayer = eventPropertiesIOLayer;
        if (helpDisplay == null) {
            throw new NullPointerException("helpDisplay may not be null");
        }    // if
        this.helpDisplay = helpDisplay;

        try {
            shifts = shiftsIOLayer.getAll();
        } catch (IOException e) {    // try
            shifts = new ArrayList<>();
        }    // catch
        for (Shift shift : shifts) {
            shift.setVolunteer(null);
        }    // for

        try {
            volunteers = volunteersIOLayer.getAll();
        } catch (IOException e) {    // try
            volunteers = new ArrayList<>();
        }    // catch

        try {
            roles = rolesIOLayer.getAll();
        } catch (IOException e) {    // try
            roles = new ArrayList<>();
        }    // catch

        List<EmailTemplate> emailTemplates;
        try {
            emailTemplates = emailTemplateIOLayer.getAll();
        } catch (IOException e) {    // try
            emailTemplates = new ArrayList<>();
        }    // catch
        emailTemplate = ((emailTemplates != null) && !emailTemplates.isEmpty()) ? emailTemplates.get(0) : new EmailTemplate(EmailTemplate.SendType.TO, "", "", "", "");

        List<EmailServerProperties> emailServerPropertiesList;
        try {
            emailServerPropertiesList = emailServerPropertiesIOLayer.getAll();
        } catch (IOException e) {    // try
            emailServerPropertiesList = new ArrayList<>();
        }    // catch
        emailServerProperties = ((emailServerPropertiesList != null) && !emailServerPropertiesList.isEmpty()) ? emailServerPropertiesList.get(0) : new EmailServerProperties("", "", "", false);

        try {
            eventProperties = eventPropertiesIOLayer.getAll();
        } catch (IOException e) {    // try
            eventProperties = new ArrayList<>();
        }    // catch

        shiftsObservers = new LinkedList<>();
        volunteersObservers = new LinkedList<>();
        rolesObservers = new LinkedList<>();
        emailTemplateObservers = new LinkedList<>();
        emailServerPropertiesObservers = new LinkedList<>();
        eventPropertiesObservers = new LinkedList<>();

        assertInvariant();
    }    // Application()

    /**
     * Returns the name of the application.
     * @return the name of the application.
     */
    public String getApplicationName() {
        assertInvariant();
        return applicationInfo.getName();
    }    // getApplicationName()

    /**
     * Returns the application version.
     * @return the application version
     */
    public String getVersion() {
        assertInvariant();
        return applicationInfo.getVersion();
    }    // getVersion()

    /**
     * Returns a copyright string for the application.
     * @return a copyright string for the application
     */
    public String getCopyright() {
        assertInvariant();
        return applicationInfo.getCopyright();
    }    // getCopyright()

    /**
     * Returns an about message for the application.
     * @return an about message for the application
     */
    public String getAboutMessage() {
        assertInvariant();
        return applicationInfo.getAboutMessage();
    }    // getAboutMessage()

    /**
     * Returns a string usable as the title of a GUI window with the given
     * subtitile. This method is used to create consistent window titles across
     * the application.
     *
     * @param subtitle the window subtitle; may not be null
     * @return the window title string
     * @throws NullPointerException if subtitle is null
     * @since 4.0
     */
    public String createWindowTitle(String subtitle) {
        assertInvariant();
        if (subtitle == null) {
            throw new NullPointerException("subtitle may not be null");
        }    // if

        return applicationInfo.getName() + " - " + subtitle;
    }    // createWindowTitle()

    /**
     * Displays application help
     */
    public void displayHelp() {
        assertInvariant();
        try {
            helpDisplay.displayHelp();
        } catch (IOException e) {    // try
            showErrorDialog(null, "Error opening user guide", e);
        }    // catch
    }    // displayHelp()

    /**
     * Returns the list of defined volunteer shifts. The list returned is a copy
     * of the master, so changes to it do not affect the master and vice-versa.
     * The shifts returned are guaranteed to be open.
     *
     * @return the list of defined volunteer shifts
     */
    public List<Shift> getShifts() {
        assertInvariant();
        List<Shift> clones = new ArrayList<>();
        for (Shift shift : shifts) {
            clones.add(shift.clone());
        }    // for
        return clones;
    }    // getShifts()

    /**
     * Sets the list of defined volunteer shifts. Shifts are copied into the
     * application without their volunteers, if any. The original shifts are
     * unchanged. The argument is copied to the master, so that changes to the
     * master do not affect the original list and vice-versa.
     *
     * @param shifts the new list of volunteer shifts; may not be null, nor
     * contain any null elements
     * @throws NullPointerException if {@code shifts} is null or contains a null
     * element
     * @throws IOException if an I/O error occurs
     */
    public void setShifts(List<Shift> shifts) throws IOException {
        assertInvariant();
        if (shifts == null) {
            throw new NullPointerException("shifts may not be null");
        }    // if
        if (shifts.contains(null)) {
            throw new NullPointerException("shifts may not contain null");
        }    // if
        this.shifts = new LinkedList<>();
        for (Shift shift : shifts) {
            Shift clone = shift.clone();
            clone.setVolunteer(null);
            this.shifts.add(clone);
        }    // for
        for (ShiftsObserver observer : shiftsObservers) {
            observer.shiftsChanged();
        }    // for

        shiftsIOLayer.setAll(shifts);
        assertInvariant();
    }    // setShifts()

    /**
     * Returns the list of defined volunteers. The list returned is a copy of
     * the master, so changes to it do not affect the master and vice-versa.
     *
     * @return the list of defined volunteers
     */
    public List<Volunteer> getVolunteers() {
        assertInvariant();
        List<Volunteer> clones = new ArrayList<>();
        for (Volunteer volunteer : volunteers) {
            clones.add(volunteer.clone());
        }    // for
        return clones;
    }    // getVolunteers()

    /**
     * Sets the list of defined volunteers. The argument is copied to the
     * master, so that changes to the master do not affect the original list and
     * vice-versa.
     *
     * @param volunteers the new list of volunteers; may not be null, nor
     * contain any null elements
     * @throws NullPointerException if {@code volunteers} is null or contains a
     * null element
     * @throws IOException if an I/O error occurs
     */
    public void setVolunteers(List<Volunteer> volunteers) throws IOException {
        assertInvariant();
        if (volunteers == null) {
            throw new NullPointerException("volunteers may not be null");
        }    // if
        if (volunteers.contains(null)) {
            throw new NullPointerException("volunteers may not contain null");
        }    // if
        this.volunteers = new LinkedList<>();
        for (Volunteer volunteer : volunteers) {
            this.volunteers.add(volunteer.clone());
        }    // for
        for (VolunteersObserver observer : volunteersObservers) {
            observer.volunteersChanged();
        }    // for

        volunteersIOLayer.setAll(volunteers);
        assertInvariant();
    }    // setVolunteers()

    /**
     * Returns the list of defined roles. The list returned is a copy of
     * the master, so changes to it do not affect the master and vice-versa.
     *
     * @return the list of defined roles
     */
    public List<Role> getRoles() {
        assertInvariant();
        List<Role> clones = new ArrayList<>();
        for (Role role : roles) {
            clones.add(role.clone());
        }    // for
        return clones;
    }    // getRoles()

    /**
     * Sets the list of defined roles. The argument is copied to the master, so
     * that changes to the master do not affect the original list and
     * vice-versa.
     *
     * @param roles the new list of roles; may not be null, contain any null
     * elements, nor contain any duplicate elements
     * @throws NullPointerException if {@code roles} is null or contains a null
     * element
     * @throws IllegalArgumentException if {@code roles} contains duplicate
     * elements
     * @throws IOException if an I/O error occurs
     */
    public void setRoles(List<Role> roles) throws IOException {
        assertInvariant();
        if (roles == null) {
            throw new NullPointerException("roles may not be null");
        }    // if
        if (roles.contains(null)) {
            throw new NullPointerException("roles may not contain null");
        }    // if
        if (roles.size() != (new HashSet<Role>(roles).size())) {
            throw new IllegalArgumentException("roles may not contain duplicates");
        }    // if
        this.roles = new LinkedList<>();
        for (Role role : roles) {
            this.roles.add(role.clone());
        }    // for
        for (RolesObserver observer : rolesObservers) {
            observer.rolesChanged();
        }    // for

        rolesIOLayer.setAll(roles);
        assertInvariant();
    }    // setRoles()

    /**
     * Returns the defined email template.
     *
     * @return the defined email template
     */
    public EmailTemplate getEmailTemplate() {
        assertInvariant();
        return emailTemplate.clone();
    }    // getEmailTemplate()

    /**
     * Sets the defined email template.
     *
     * @param emailTemplate the email template to set; may not be null
     * @throws NullPointerException if {@code emailTemplate} is null
     * @throws IOException if an I/O error occurs
     */
    public void setEmailTemplate(EmailTemplate emailTemplate) throws IOException {
        assertInvariant();
        if (emailTemplate == null) {
            throw new NullPointerException("emailTemplate may not be null");
        }    // if
        this.emailTemplate = emailTemplate.clone();
        List<EmailTemplate> wrapper = new LinkedList<>();
        wrapper.add(this.emailTemplate);
        for (EmailTemplateObserver observer : emailTemplateObservers) {
            observer.emailTemplateChanged();
        }    // for

        emailTemplateIOLayer.setAll(wrapper);
        assertInvariant();
    }    // setEmailTemplate()

    /**
     * Returns the defined email server properties.
     *
     * @return the defined email server properties
     */
    public EmailServerProperties getEmailServerProperties() {
        assertInvariant();
        return emailServerProperties.clone();
    }    // getEmailServerProperties()

    /**
     * Sets the defined email server properties.
     *
     * @param emailServerProperties the email server properties to set; may not
     * be null
     * @throws NullPointerException if {@code emailTemplate} is null
     * @throws IOException if an I/O error occurs
     */
    public void setEmailServerProperties(EmailServerProperties emailServerProperties) throws IOException {
        assertInvariant();
        if (emailServerProperties == null) {
            throw new NullPointerException("emailServerProperties may not be null");
        }    // if
        this.emailServerProperties = emailServerProperties.clone();
        List<EmailServerProperties> wrapper = new LinkedList<>();
        wrapper.add(this.emailServerProperties);
        for (EmailServerPropertiesObserver observer : emailServerPropertiesObservers) {
            observer.emailServerPropertiesChanged();
        }    // for

        emailServerPropertiesIOLayer.setAll(wrapper);
        assertInvariant();
    }    // setEmailServerProperties()

    /**
     * Returns the list of defined event properties. The list returned is a copy
     * of the master, so changes to it do not affect the master and vice-versa.
     *
     * @return the list of defined event properties
     */
    public List<EventProperty> getEventProperties() {
        assertInvariant();
        List<EventProperty> clones = new ArrayList<>();
        for (EventProperty eventProperty : eventProperties) {
            clones.add(eventProperty.clone());
        }    // for
        return clones;
    }    // getEventProperties()

    /**
     * Sets the list of defined event properties. Event properties are copied into the
     * application if any. The original event properties are unchanged.
     * The argument is copied to the master, so that changes to the
     * master do not affect the original list and vice-versa.
     *
     * @param eventProperties list of event properties; may not be null, nor
     * contain any null elements
     * @throws NullPointerException if {@code eventProperties} is null or contains a null
     * element
     * @throws IOException if an I/O error occurs
     */
    public void setEventProperties(List<EventProperty> eventProperties) throws IOException {
        assertInvariant();
        if (eventProperties == null) {
            throw new NullPointerException("Event Properties may not be null");
        }    // if
        if (eventProperties.contains(null)) {
            throw new NullPointerException("Event Properties may not contain null");
        }    // if
        this.eventProperties = new LinkedList<>();
        for (EventProperty eventProperty : eventProperties) {
            EventProperty clone = eventProperty.clone();
            this.eventProperties.add(clone);
        }    // for
        for (EventPropertiesObserver observer : eventPropertiesObservers) {
            observer.eventPropertiesChanged();
        }    // for

        eventPropertiesIOLayer.setAll(eventProperties);
        assertInvariant();
    }    // setEventProperties()


    /**
     * Registers a shifts observer with this application.
     *
     * @param observer the observer to register; may not be null
     * @throws NullPointerException if observer is null
     */
    public void registerObserver(ShiftsObserver observer) {
        assertInvariant();
        if (observer == null) {
            throw new NullPointerException("observer may not be null");
        }    // if
        shiftsObservers.add(observer);
        assertInvariant();
    }    // registerObserver()

    /**
     * Registers a volunteers observer with this application.
     *
     * @param observer the observer to register; may not be null
     * @throws NullPointerException if observer is null
     */
    public void registerObserver(VolunteersObserver observer) {
        assertInvariant();
        if (observer == null) {
            throw new NullPointerException("observer may not be null");
        }    // if
        volunteersObservers.add(observer);
        assertInvariant();
    }    // registerObserver()

    /**
     * Registers a roles observer with this application.
     *
     * @param observer the observer to register; may not be null
     * @throws NullPointerException if observer is null
     */
    public void registerObserver(RolesObserver observer) {
        assertInvariant();
        if (observer == null) {
            throw new NullPointerException("observer may not be null");
        }    // if
        rolesObservers.add(observer);
        assertInvariant();
    }    // registerObserver()

    /**
     * Registers an email template observer with this application.
     *
     * @param observer the observer to register; may not be null
     * @throws NullPointerException if observer is null
     */
    public void registerObserver(EmailTemplateObserver observer) {
        assertInvariant();
        if (observer == null) {
            throw new NullPointerException("observer may not be null");
        }    // if
        emailTemplateObservers.add(observer);
        assertInvariant();
    }    // registerObserver()

    /**
     * Registers an email server properties observer with this application.
     *
     * @param observer the observer to register; may not be null
     * @throws NullPointerException if observer is null
     * @since 3.4
     */
    public void registerObserver(EmailServerPropertiesObserver observer) {
        assertInvariant();
        if (observer == null) {
            throw new NullPointerException("observer may not be null");
        }    // if
        emailServerPropertiesObservers.add(observer);
        assertInvariant();
    }    // registerObserver()

    /**
     * Registers a event property observer with this application.
     *
     * @param observer the observer to register; may not be null
     * @throws NullPointerException if observer is null
     */
    public void registerObserver(EventPropertiesObserver observer) {
        assertInvariant();
        if (observer == null) {
            throw new NullPointerException("observer may not be null");
        }    // if
        eventPropertiesObservers.add(observer);
        assertInvariant();
    }    // registerObserver()

    /**
     * Shows an error dialog with the given owner, message, and cause.
     *
     * @param owner the owner of the dialog, or null if there is no owner
     * @param message the message in the dialog, or null if there is no message
     * @param cause the underlying cause of the error, or null if there is no
     * underlying cause
     * @since 2.0.2
     */
    public void showErrorDialog(Frame owner, String message, Throwable cause) {
        assertInvariant();
        ErrorDialog dialog = new ErrorDialog(owner, createWindowTitle("Error"), message, cause);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }    // showErrorDialog()

    /**
     * Shows an error dialog with the given owner and message.
     *
     * @param owner the owner of the dialog, or null if there is no owner
     * @param message the message in the dialog, or null if there is no message
     * @since 2.0.2
     */
    public void showErrorDialog(Frame owner, String message) {
        assertInvariant();
        showErrorDialog(owner, message, null);
    }    // showErrorDialog()

    /*
     * Private class methods.
     */

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (shiftsIOLayer != null);
        assert (volunteersIOLayer != null);
        assert (rolesIOLayer != null);
        assert (emailTemplateIOLayer != null);
        assert (emailServerPropertiesIOLayer != null);
        assert (eventPropertiesIOLayer != null);
        assert (shifts != null);
        assert (! shifts.contains(null));
        assert (shiftsHasNoVolunteers());
        assert (volunteers != null);
        assert (! volunteers.contains(null));
        assert (eventProperties != null);
        assert (! eventProperties.contains(null));
        assert (shiftsObservers != null);
        assert (! shiftsObservers.contains(null));
        assert (volunteersObservers != null);
        assert (! volunteersObservers.contains(null));
        assert (rolesObservers != null);
        assert (! rolesObservers.contains(null));
        assert (emailTemplate != null);
        assert (emailTemplateObservers != null);
        assert (! emailTemplateObservers.contains(null));
        assert (emailServerProperties != null);
        assert (emailServerPropertiesObservers != null);
        assert (! emailServerPropertiesObservers.contains(null));
        assert (eventPropertiesObservers != null);
        assert (! eventPropertiesObservers.contains(null));
    }    // assertInvariant()

    /**
     * Returns true if the elements of {@link shifts} have no volunteers.
     *
     * @return true if the elements of {@link shifts} have no volunteers; false
     * otherwise
     */
    private boolean shiftsHasNoVolunteers() {
        for (Shift shift : shifts) {
            if (! shift.isOpen()) {
                return false;
            }    // if
        }    // for
        return true;
    }    // shiftsHasNoVolunteers()
}    // Application
