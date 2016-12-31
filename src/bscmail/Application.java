/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
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

package bscmail;

import bscmail.gui.error.ErrorDialog;
import iolayer.IOLayer;
import iolayer.XMLIOLayer;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

/**
 * An application controls application-wide properties and objects.
 * 
 * @since 2.0
 * @author Wayne Miller
 */
public class Application {

    /*
     * Private class properties.
     */
    
    /**
     * String property keys for the application.
     */
    private enum PropertyKey {
        
        /**
         * The application name.
         */
        APPLICATION_NAME           (false),
        
        /**
         * The application version.
         */
        APPLICATION_VERSION        (false),
        
        /**
         * The application copyright notice.
         */
        APPLICATION_COPYRIGHT      (false),
        
        /**
         * The name of the defined shifts file.
         */
        SHIFTS_FILE                (true),
        
        /**
         * The name of the defined volunteers file.
         */
        VOLUNTEERS_FILE            (true),

        /**
         * The name of the defined roles file.
         */
        ROLES_FILE                 (true),
        
        /**
         * The name of the email template file.
         */
        EMAIL_TEMPLATE_FILE        (true),

        /**
         * The name of the defined event properties file.
         */
        EVENT_PROPERTY_FILE        (true),

        /**
         * The name of the user guide file.
         */
        USER_GUIDE_FILE            (true);

        /*
         * Class methods and properties
         */

        /**
         * True if the property key denotes a filename.
         */
        private final boolean isFileName;

        /**
         * Constructs a new property key.
         * 
         * @param isFileName true if the property key denotes a filename; false
         * otherwise
         */
        PropertyKey(boolean isFileName) { this.isFileName = isFileName; }
        
        /**
         * Returns true if the property key denotes a filename; otherwise,
         * returns false.
         * 
         * @return true if the property key denotes a filename; false otherwise
         */
        boolean isFileName() { return isFileName; }
    }    // PropertyKey

    /**
     * Maps {@link PropertyKey}s to {@link String}s for the application. This
     * map has a test mode which may be set or unset. When test mode is enabled,
     * the values returned by {@link #get(Object)} are altered to special test
     * values.  Test mode is disabled by default.
     */
    private class ApplicationProperties extends EnumMap<PropertyKey, String> {

        /**
         * True when test mode is enabled.
         */
        private boolean testMode = false;

        /**
         * Constructs a new application properties map.
         */
        public ApplicationProperties() {
            super(PropertyKey.class);
        }    // ApplicationProperties()

        /**
         * Returns the value to which the specified key is mapped. If test mode
         * is enabled and the key denotes a filename, then the return value is
         * altered to a test value. Otherwise, this method behaves as
         * {@link EnumMap#get(Object)}.
         *
         * @param key the key whose associated value is to be returned
         * @return the value to which the specified key is mapped
         */
        @Override
        public String get(Object key) {
            String value = super.get(key);
            if (key instanceof PropertyKey) {
                PropertyKey propertyKey = (PropertyKey) key;
                if (testMode && propertyKey.isFileName()) {
                    value += ".test";
                }    // if testMode
            }    // if key
            return value;
        }    // get()

        /**
         * Sets or unsets test mode. If test mode is set, filenames will be
         * changed to test values. Test mode is unset by default.
         *
         * @param testMode sets test mode if true; unsets test mode if false
         */
        public void setTestMode(boolean testMode) {
            this.testMode = testMode;
        }    // setTestMode()
        
        /**
         * Returns true if test mode is set, or false otherwise.
         * 
         * @return true if test mode is set; false otherwise
         */
        public boolean getTestMode() {
            return testMode;
        }    // getTestMode()

    }    // ApplicationProperties
    
    /**
     * String properties for the application.
     */
    private final ApplicationProperties properties;

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
     * The shifts I/O layer.
     */
    private final IOLayer<Role> rolesIOLayer;

    /**
     * The shifts I/O layer.
     */
    private final IOLayer<EmailTemplate> emailTemplateIOLayer;

    /**
     * The shifts I/O layer.
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
     * The list of event property observers.
     */
    private final List<EventPropertyObserver> eventPropertyObservers;
    
    /**
     * The list of test dialogs.
     */
    private final List<JDialog> testDialogs;

    /**
     * Tracks name of XML file being imported
     */
    private String currentImportFile;
    
    /**
     * Constructs a new application.
     */
    public Application() throws ExceptionInInitializerError {
        currentImportFile = "";

        try {
            properties = new ApplicationProperties();
            properties.put(PropertyKey.APPLICATION_NAME, "BSCMail");
            properties.put(PropertyKey.APPLICATION_VERSION, "3.0 beta");
            properties.put(PropertyKey.APPLICATION_COPYRIGHT, "Copyright © 2014-2016 its authors.  See the file \"AUTHORS\" for details.");
            properties.put(PropertyKey.SHIFTS_FILE, "shifts.xml");
            properties.put(PropertyKey.VOLUNTEERS_FILE, "volunteers.xml");
            properties.put(PropertyKey.ROLES_FILE, "roles.xml");
            properties.put(PropertyKey.EMAIL_TEMPLATE_FILE, "emailTemplate.xml");
            properties.put(PropertyKey.EVENT_PROPERTY_FILE, "eventProperties.xml");
            properties.put(PropertyKey.USER_GUIDE_FILE, "userguide.pdf");

            shiftsIOLayer = new XMLIOLayer<>(properties.get(PropertyKey.SHIFTS_FILE), Shift.getShiftFactory());
            volunteersIOLayer = new XMLIOLayer<>(properties.get(PropertyKey.VOLUNTEERS_FILE), Volunteer.getVolunteerFactory());
            rolesIOLayer = new XMLIOLayer<>(properties.get(PropertyKey.ROLES_FILE), Role.getRoleFactory());
            emailTemplateIOLayer = new XMLIOLayer<>(properties.get(PropertyKey.EMAIL_TEMPLATE_FILE), EmailTemplate.getEmailTemplateFactory());
            eventPropertiesIOLayer = new XMLIOLayer<>(properties.get(PropertyKey.EVENT_PROPERTY_FILE), EventProperty.getEventPropertyFactory());

            shifts = shiftsIOLayer.getAll();
            for (Shift shift : shifts) {
                shift.setVolunteer(null);
            }    // for
            volunteers = volunteersIOLayer.getAll();
            roles = rolesIOLayer.getAll();
            List<EmailTemplate> emailTemplates = emailTemplateIOLayer.getAll();
            emailTemplate = ((emailTemplates != null) && !emailTemplates.isEmpty()) ? emailTemplates.get(0) : new EmailTemplate("", "");
            eventProperties = eventPropertiesIOLayer.getAll();
            
            shiftsObservers = new LinkedList<>();
            volunteersObservers = new LinkedList<>();
            rolesObservers = new LinkedList<>();
            emailTemplateObservers = new LinkedList<>();
            eventPropertyObservers = new LinkedList<>();
            
            testDialogs = new LinkedList<>();

            currentImportFile = "";
        } catch (IOException e) {    // try
            throw new ExceptionInInitializerError(e);
        }    // catch
        assertInvariant();
    }    // Application()

    /**
     * Sets the name of the current import file.
     *
     * @param fileName the name of the new import file; may not be null
     * @throws NullPointerException if {@code fileName] is null
     */
    public void setImportFileName(String fileName){
        assertInvariant();
        if (fileName == null) {
            throw new NullPointerException("fileName may not be null");
        }    // if
        currentImportFile = fileName;
        assertInvariant();
    }    // setImportFileName()

    /**
     * Returns the name of the current import file.
     *
     * @return the name of the current import file.
     */
    public String getImportFileName(){
        assertInvariant();
        return currentImportFile;
    }    // getImportFileName();

    /**
     * Sets or unsets test mode.  If test mode is set, the application will
     * write any changes to special test files rather than the production files.
     * Test mode is unset by default.
     * 
     * @param testMode sets test mode if true; unsets test mode if false
     */
    public void setTestMode(boolean testMode) {
        assertInvariant();
        properties.setTestMode(testMode);
        assertInvariant();
    }    // setTestMode()

    /**
     * Returns the name of the application.
     * @return the name of the application.
     */
    public String getApplicationName() {
        assertInvariant();
        String property = properties.get(PropertyKey.APPLICATION_NAME);
        return property;
    }    // getApplicationName()
    
    /**
     * Returns the application version.
     * @return the application version
     */
    public String getVersion() {
        assertInvariant();
        String property = properties.get(PropertyKey.APPLICATION_VERSION);
        return property;
    }    // getVersion()

    /**
     * Returns a copyright string for the application.
     * @return a copyright string for the application
     */
    public String getCopyright() {
        assertInvariant();
        String property = properties.get(PropertyKey.APPLICATION_COPYRIGHT);
        return property;
    }    // getCopyright()

    /**
     * Returns the filename of the user guide.
     *
     * @return the filename of the user guide
     */
    public String getUserGuideFilename() {
        assertInvariant();
        String property = properties.get(PropertyKey.USER_GUIDE_FILE);
        return property;
    }    // getUserGuideFilename()

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
     * Imports volunteers from a file and adds them to the list of defined volunteers.
     *
     * @param fileName the filename of the file to import
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if a serialization failure occurs
     */
    public void importVolunteers(String fileName) throws IOException, ClassNotFoundException {
        assertInvariant();
        if (fileName == null) {
            throw new NullPointerException("file name may not be null");
        }    // if

        IOLayer<Volunteer> importIOLayer = new XMLIOLayer<>(fileName, Volunteer.getVolunteerFactory());
        List<Volunteer> importedVolunteers = importIOLayer.getAll();

        if (!importedVolunteers.contains(null)) {
            for (Volunteer volunteer : importedVolunteers) {
                volunteers.add(volunteer.clone());
            }    // for
            for (VolunteersObserver observer : volunteersObservers) {
                observer.volunteersChanged();
            }    // for

            volunteersIOLayer.setAll(volunteers);
            assertInvariant();
        }
    }   // importVolunteers()

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
     * Sets the list of defined roles. The argument is copied to the
     * master, so that changes to the master do not affect the original list and
     * vice-versa.
     *
     * @param roles the new list of roles; may not be null, nor
     * contain any null elements
     * @throws NullPointerException if {@code roles} is null or contains a
     * null element
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
     * Imports roles from a file and adds them to the list of defined roles.
     *
     * @param fileName the filename of the file to import
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if a serialization failure occurs
     */
    public void importRoles(String fileName) throws IOException, ClassNotFoundException {
        assertInvariant();
        if (fileName == null) {
            throw new NullPointerException("file name may not be null");
        }    // if

        IOLayer<Role> importIOLayer = new XMLIOLayer<>(fileName, Role.getRoleFactory());
        List<Role> importedRoles = importIOLayer.getAll();

        if(!importedRoles.contains(null)) {
            for (Role role : importedRoles) {
                if (! roles.contains(role))
                    roles.add(role.clone());
            }    // for
            for (RolesObserver observer : rolesObservers) {
                observer.rolesChanged();
            }    // for

            rolesIOLayer.setAll(roles);
            assertInvariant();
        }
    }   // importRoles()

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
     * @throws NullPointerException
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
        for (EventPropertyObserver observer : eventPropertyObservers) {
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
     * Registers a event property observer with this application.
     *
     * @param observer the observer to register; may not be null
     * @throws NullPointerException if observer is null
     */
    public void registerObserver(EventPropertyObserver observer) {
        assertInvariant();
        if (observer == null) {
            throw new NullPointerException("observer may not be null");
        }    // if
        eventPropertyObservers.add(observer);
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
        ErrorDialog dialog = new ErrorDialog(owner, getApplicationName() + " - Error", message, cause);
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
        assert (properties != null);
        assert (properties.size() == PropertyKey.values().length);
        assert (! properties.containsValue(null));
        assert (shiftsIOLayer != null);
        assert (volunteersIOLayer != null);
        assert (rolesIOLayer != null);
        assert (emailTemplateIOLayer != null);
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
        assert (eventPropertyObservers != null);
        assert (! eventPropertyObservers.contains(null));
        assert (testDialogs != null);
        assert (! testDialogs.contains(null));
        assert (properties.getTestMode() ? true : testDialogs.isEmpty());
        assert (currentImportFile != null);
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
