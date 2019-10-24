/*
 * Copyright © 2016-2019 its authors.  See the file "AUTHORS" for details.
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


package io.github.waynem77.bscmail.gui;

import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.TestApplication;
import io.github.waynem77.bscmail.persistent.Role;
import org.junit.*;

/**
 *
 * @author Wayne Miller
 */
public class ManageRolePanelTest extends ManageElementPanelTest<Role> {

    /**
     * Returns an application that can be used in tests.
     */
    private Application getTestApplication()  {
        return new TestApplication();
    }    // getTestApplication()

    /**
     * Returns the manage role panel to be tested.
     *
     * @return the manage role panel to be tested
     */
    @Override
    protected ManageRolePanel getPanel() {
        Application application = getTestApplication();
        return new ManageRolePanel(application);
    }    // getPanel()

    /**
     * Returns an invalid role to use in testing.
     *
     * @return an invalid role to use in testing
     */
    @Override
    protected Role getInvalidElement() {
        return null;
    }    // gerInvalidElement()

    /**
     * Returns a valid role to use in testing.
     *
     * @return a valid role to use in testing
     */
    @Override
    protected Role getElement() {
        return new Role("foo");
    }    // getElement()

    /**
     * Tests that
     * {@link ManageRolesFrame#ManageRolesFrame(bscmail.Application)} throws a
     * {@link NullPointerException} when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicationIsNull() {
        Application application = null;

        ManageRolesFrame frame = new ManageRolesFrame(application);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that
     * {@link ManageRolesFrame#ManageRolesFrame(bscmail.Application)} does not
     * throw an exception when application is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenApplicationIsNotNull() {
        Application application = getTestApplication();
        ManageRolesFrame frame = new ManageRolesFrame(application);
    }    // constructorDoesNotThrowExceptionWhenApplicationIsNotNull()

}    // ManageRolePanelTest
