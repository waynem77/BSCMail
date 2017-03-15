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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import main.ReadWritableFactoryTest;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Shift.Factory}.
 *
 * @author Wayne Miller
 */
public class ShiftFactoryTest extends ReadWritableFactoryTest<Shift> {

    /**
     * Returns the shift description used in {@link #getTestProperties()}.
     *
     * @return the shift description used in {@link #getTestProperties()}
     */
    private String getTestDescription() {
        return "Foo";
    }    // getTestDescription()

    /**
     * Returns the shift volunteer used in {@link #getTestProperties()}.
     *
     * @return the shift volunteer used in {@link #getTestProperties()}
     */
    private Volunteer getTestVolunteer() {
        return new Volunteer("foo", "bar", "baz", "smurf");
    }    // getTestVolunteer()

    /**
     * Returns a string representation of the shift roles used in
     * {@link #getTestProperties()}.
     *
     * @return a string representation of the shift roles used in
     * {@link #getTestProperties()}
     */
    private String getTestRoles() {
        return "foo,bar";
    }    // getTestRoles()

    /**
     * Returns the shift displayVolunteerEmail value used in
     * {@link #getTestProperties()}.
     *
     * @return the shift displayVolunteerEmail value used in
     * {@link #getTestProperties()}
     */
    private boolean getTestDisplayVolunteerEmail() {
        return true;
    }    // getTestDisplayVolunteerEmail()

    /**
     * Returns the shift displayVolunteerPhone value used in
     * {@link #getTestProperties()}.
     *
     * @return the shift displayVolunteerPhone value used in
     * {@link #getTestProperties()}
     */
    private boolean getTestDisplayVolunteerPhone() {
        return true;
    }    // getTestDisplayVolunteerEmail()

    /**
     * Returns the shift displayVolunteerNotes value used in
     * {@link #getTestProperties()}.
     *
     * @return the shift displayVolunteerNotes value used in
     * {@link #getTestProperties()}
     */
    private boolean getTestDisplayVolunteerNotes() {
        return true;
    }    // getTestDisplayVolunteerEmail()

    /**
     * Returns the shift factory being tested.
     *
     * @return the shift factory being tested
     */
    @Override
    protected Shift.Factory getTestFactory() {
        return Shift.getShiftFactory();
    }    // getTestFactory()

    /**
     * Returns a properties map appropriate for testing.
     *
     * @return a properties map appropriate for testing
     */
    @Override
    protected Map<String, Object> getTestProperties() {
        String description = getTestDescription();
        Volunteer volunteer = getTestVolunteer();
        String roles = getTestRoles();
        boolean displayVolunteerEmail = getTestDisplayVolunteerEmail();
        boolean displayVolunteerPhone = getTestDisplayVolunteerPhone();
        boolean displayVolunteerNotes = getTestDisplayVolunteerNotes();

        Map<String, Object> properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", roles);
        properties.put("displayVolunteerEmail", displayVolunteerEmail);
        properties.put("displayVolunteerPhone", displayVolunteerPhone);
        properties.put("displayVolunteerNotes", displayVolunteerNotes);
        return properties;
    }    // getTestProperties()

    /**
     * Returns the shift that ought to be created from the given properties.
     * This method is guaranteed to never return null.
     *
     * @param properties the properties; should be the (possibly modified)
     * return value of {@link #getTestProperties()}
     * @return the shift that ought to be created from the given
     * properties
     */
    @Override
    protected Shift getReadWritableFromProperties(Map<String, Object> properties) {
        assert (properties != null);

        Object descriptionObject = properties.get("description");
        String description = (descriptionObject == null) ? "" : descriptionObject.toString();

        Object rolesObject = properties.get("roles");
        String rolesString = (rolesObject == null) ? "" : rolesObject.toString();
        String[] rolesStrings = rolesString.isEmpty() ? new String[0] : rolesString.split(",");
        List<Role> roles = new LinkedList<>();
        for (String role : rolesStrings) {
            roles.add(new Role(role));
        }    // for

        Object displayVolunteerEmailObject = properties.get("displayVolunteerEmail");
        boolean displayVolunteerEmail = (displayVolunteerEmailObject instanceof Boolean) ? (Boolean)displayVolunteerEmailObject : false;

        Object displayVolunteerPhoneObject = properties.get("displayVolunteerPhone");
        boolean displayVolunteerPhone = (displayVolunteerPhoneObject instanceof Boolean) ? (Boolean)displayVolunteerPhoneObject : false;

        Object displayVolunteerNotesObject = properties.get("displayVolunteerNotes");
        boolean displayVolunteerNotes = (displayVolunteerNotesObject instanceof Boolean) ? (Boolean)displayVolunteerNotesObject : false;

        Object volunteerObject = properties.get("volunteer");
        Volunteer volunteer = (volunteerObject instanceof Volunteer) ? (Volunteer)volunteerObject : null;

        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(volunteer);
        assert (shift != null);
        return shift;
    }    // getReadWritableFromProperties()

}    // ShiftFactoryTest
