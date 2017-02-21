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

package bscmail;

import bscmail.gui.error.ErrorDialog;
import bscmail.help.HelpDisplay;
import iolayer.IOLayer;
import iolayer.XMLIOLayer;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JDialog;

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
    private final List<EventPropertiesObserver> eventPropertiesObservers;

    /**
     * The help displayer.
     */
    private final HelpDisplay helpDisplay;

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
     * @param eventPropertiesIOLayer the I/O layer used for storing the event
     * properties; may not be null
     * @param helpDisplay the help displayer; may not be null
     * @throws NullPointerException if any parameter is null
     */
    public Application(ApplicationInfo applicationInfo,
            IOLayer<Shift> shiftsIOLayer,
            IOLayer<Volunteer> volunteersIOLayer,
            IOLayer<Role> rolesIOLayer,
            IOLayer<EmailTemplate> emailTemplateIOLayer,
            IOLayer<EventProperty> eventPropertiesIOLayer,
            HelpDisplay helpDisplay) throws ExceptionInInitializerError {
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
        emailTemplate = ((emailTemplates != null) && !emailTemplates.isEmpty()) ? emailTemplates.get(0) : new EmailTemplate("", "");

        try {
            eventProperties = eventPropertiesIOLayer.getAll();
        } catch (IOException e) {    // try
            eventProperties = new ArrayList<>();
        }    // catch

        shiftsObservers = new LinkedList<>();
        volunteersObservers = new LinkedList<>();
        rolesObservers = new LinkedList<>();
        emailTemplateObservers = new LinkedList<>();
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
