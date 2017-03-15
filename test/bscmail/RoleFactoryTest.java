/*
 * Copyright © 2016-2017 its authors.  See the file "AUTHORS" for details.
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
import java.util.Map;
import main.ReadWritableFactoryTest;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Role.Factory}.
 *
 * @author Wayne Miller
 */
public class RoleFactoryTest extends ReadWritableFactoryTest<Role> {

    /**
     * Returns the role name used in {@link #getTestProperties()}.
     *
     * @return the role name used in {@link #getTestProperties()}
     */
    private String getTestName() {
        return "foo";
    }    // getTestName()

    /**
     * Returns the role factory being tested.
     *
     * @return the role factory being tested
     */
    @Override
    protected Role.Factory getTestFactory() {
        return Role.getRoleFactory();
    }    // getTestFactory()

    /**
     * Returns a properties map appropriate for testing.
     *
     * @return a properties map appropriate for testing
     */
    @Override
    protected Map<String, Object> getTestProperties() {
        String name = getTestName();

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        return properties;
    }    // getTestProperties()

    /**
     * Returns the role that ought to be created from the given properties. This
     * method is guaranteed never to return null.
     *
     * @param properties the properties; should be the (possibly modified)
     * return value of {@link #getTestProperties()}
     * @return the volunteer that ought to be created from the given properties
     */
    @Override
    protected Role getReadWritableFromProperties(Map<String, Object> properties) {
        assert (properties != null);

        Object nameObject = properties.get("name");
        String name = (nameObject == null) ? "" : nameObject.toString();

        Role role = new Role(name);
        assert (role != null);
        return role;
    }    // getReadWritableFromProperties()

}    // RoleFactoryTest
