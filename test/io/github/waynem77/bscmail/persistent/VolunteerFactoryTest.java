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

package io.github.waynem77.bscmail.persistent;

import io.github.waynem77.bscmail.ReadWritableFactoryTest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Volunteer.Factory}.
 *
 * @author Wayne Miller
 */
public class VolunteerFactoryTest extends ReadWritableFactoryTest<Volunteer> {

    /**
     * Returns the volunteer name used in {@link #getTestProperties()}.
     *
     * @return the volunteer name used in {@link #getTestProperties()}
     */
    private String getTestName() {
        return "foo";
    }    // getTestName()

    /**
     * Returns the volunteer email used in {@link #getTestProperties()}.
     *
     * @return the volunteer email used in {@link #getTestProperties()}
     */
    private String getTestEmail() {
        return "bar";
    }    // getTestEmail()

    /**
     * Returns the volunteer phone used in {@link #getTestProperties()}.
     *
     * @return a volunteer phone used in {@link #getTestProperties()}
     */
    private String getTestPhone() {
        return "baz";
    }    // getTestPhone()

    /**
     * Returns the volunteer notes value used in {@link #getTestProperties()}.
     *
     * @return the volunteer notes value used in {@link #getTestProperties()}
     */
    private String getTestNotes() {
        return "smurf";
    }    // getTestNotes()

    /**
     * Returns the volunteer notes active status used in
     * {@link #getTestProperties()}.
     *
     * @return the volunteer notes active status used in
     * {@link #getTestProperties()}
     */
    private boolean getActive() {
        return true;
    }    // getActive()

    /**
     * Returns the volunteer factory being tested.
     *
     * @return the volunteer factory being tested
     */
    @Override
    protected Volunteer.Factory getTestFactory() {
        return Volunteer.getVolunteerFactory();
    }    // getTestFactory()

    /**
     * Returns a properties map appropriate for testing.
     *
     * @return a properties map appropriate for testing
     */
    @Override
    protected Map<String, Object> getTestProperties() {
        String name = getTestName();
        String email = getTestEmail();
        String phone = getTestPhone();
        String notes = getTestNotes();
        String active = Boolean.TRUE.toString();

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);
        properties.put("notes", notes);
        properties.put("active", active);
        return properties;
    }    // getTestProperties()

    /**
     * Returns the volunteer that ought to be created from the given
     * properties.  This method is guaranteed never to return null.
     *
     * @param properties the properties; should be the (possibly modified)
     * return value of {@link #getTestProperties()}
     * @return the volunteer that ought to be created from the given
     * properties
     */
    @Override
    protected Volunteer getReadWritableFromProperties(Map<String, Object> properties) {
        assert (properties != null);

        Object nameObject = properties.get("name");
        String name = (nameObject == null) ? "" : nameObject.toString();

        Object emailObject = properties.get("email");
        String email = (emailObject == null) ? "" : emailObject.toString();

        Object phoneObject = properties.get("phone");
        String phone = (phoneObject == null) ? "" : phoneObject.toString();

        Object notesObject = properties.get("notes");
        String notes = (notesObject == null) ? "" : notesObject.toString();

        Object activeObject = properties.get("active");
        String activeObjectAsString = (activeObject == null) ? null : activeObject.toString();
        Boolean activeObjectAsBoolean = (activeObjectAsString == null) ? null : Boolean.valueOf(activeObjectAsString);
        boolean active = (activeObjectAsBoolean == null) ? true : activeObjectAsBoolean;

        Object rolesObject = properties.get("roles");
        String rolesString = (rolesObject == null) ? "" : rolesObject.toString();
        String[] rolesStrings = rolesString.isEmpty() ? new String[0] : rolesString.split(",");
        List<Role> roles = new LinkedList<>();
        for (String role : rolesStrings) {
            roles.add(new Role(role));
        }    // for

        Volunteer volunteer = new Volunteer(name, email, phone, notes, active, roles);
        assert (volunteer != null);
        return volunteer;
    }    // getReadWritableFromProperties()

}    // VolunteerFactoryTest
