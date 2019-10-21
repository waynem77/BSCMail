/*
 * Copyright © 2019 its authors.  See the file "AUTHORS" for details.
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

import io.github.waynem77.bscmail.mail.Mailer;
import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.TestApplication;
import org.junit.*;

/**
 * Unit tests for {@link MailerFrame}.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class MailerFrameTest {

    /**
     * Returns an application that can be used in tests.
     */
    private Application getTestApplication()  {
        return new TestApplication();
    }    // getTestApplication()

    /* constructor */

    /**
     * Tests that {@link MailerFrame#MailerFrame(bscmail.Application, Mailer)}
     * throws a NullPointerException when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenServerPropertiesIsNull() {
        Application application = null;
        Mailer mailer = new Mailer(getTestApplication());

        MailerFrame mailerFrame = new MailerFrame(application, mailer);
    }    // constructorThrowsExceptionWhenServerPropertiesIsNull()

    /**
     * Tests that
     * {@link MailerFrame#MailerFrame(bscmail.Application, Mailer)}
     * throws a NullPointerException when mailer is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenMailerIsNull() {
        Application application = getTestApplication();
        Mailer mailer = null;

        MailerFrame mailerFrame = new MailerFrame(application, mailer);
    }    // constructorThrowsExceptionWhenMailerIsNull()

    /**
     * Tests that
     * {@link MailerFrame#MailerFrame(bscmail.Application, Mailer)}
     * does not throw an exception when no argument is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNoArgumentIsNull() {
        Application application = getTestApplication();
        Mailer mailer = new Mailer(application);

        MailerFrame mailerFrame = new MailerFrame(application, mailer);
    }    // constructorDoesNotThrowExceptionWhenNoArgumentIsNull()

    /* mailerStatusChanged */

    /**
     * Tests that {@link MailerFrame#mailerStatusChanged()} does not throw an
     * exception.
     */
    @Test
    public void mailerStatusChangedDoesNotThrowException() {
        Application application = getTestApplication();
        Mailer mailer = new Mailer(application);
        MailerFrame mailerFrame = new MailerFrame(application, mailer);

        mailerFrame.mailerStatusChanged();
    }    // mailerStatusChangedDoesNotThrowException()
}
