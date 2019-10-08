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
package io.github.waynem77.bscmail.mail;

import io.github.waynem77.bscmail.help.HelpDisplay;
import io.github.waynem77.bscmail.iolayer.IOLayer;
import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.ApplicationInfo;
import io.github.waynem77.bscmail.persistent.EmailServerProperties;
import io.github.waynem77.bscmail.persistent.EmailTemplate;
import io.github.waynem77.bscmail.persistent.EventProperty;
import io.github.waynem77.bscmail.persistent.Role;
import io.github.waynem77.bscmail.persistent.Shift;
import io.github.waynem77.bscmail.persistent.TestIOLayer;
import io.github.waynem77.bscmail.persistent.Volunteer;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Mailer}.
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class MailerTest {

    /**
     * Observer used in tests.
     */
    private class TestObserver implements MailerObserver {
        private int notifications = 0;

        @Override
        public void mailerStatusChanged() {
            ++notifications;
        }    // mailerStatusChanged()

        public boolean hasBeenNotified() {
            return (notifications > 0);
        }    // hasBeenNotified()

        public int getNotifications() {
            return notifications;
        }    // getNotifications()
    }    // TestObserver

    /**
     * Returns an application that can be used in tests.
     */
    private Application getTestApplication()  {
        ApplicationInfo applicationInfo = new ApplicationInfo("foo", "bar", "baz");
        IOLayer<Shift> shiftsIOLayer = new TestIOLayer<>();
        IOLayer<Volunteer> volunteersIOLayer = new TestIOLayer<>();
        IOLayer<Role> rolesIOLayer = new TestIOLayer<>();
        IOLayer<EmailTemplate> emailTemplateIOLayer = new TestIOLayer<>();
        IOLayer<EmailServerProperties> emailServerPropertiesIOLayer = new TestIOLayer<>();
        IOLayer<EventProperty> eventPropertiesIOLayer = new TestIOLayer<>();
        HelpDisplay helpDisplay = new HelpDisplay(){ @Override public void displayHelp() {} };
        return new Application(applicationInfo, shiftsIOLayer, volunteersIOLayer, rolesIOLayer, emailTemplateIOLayer, emailServerPropertiesIOLayer, eventPropertiesIOLayer, helpDisplay);
    }    // getTestApplication()

    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that {@link Mailer#Mailer(Application)} throws a
     * NullPointerException when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicaitonIsNull() {
        Application application = null;

        Mailer mailer = new Mailer(application);
    }    // constructorThrowsExceptionWhenApplicaitonIsNull()

    /**
     * Tests that {@link Mailer#Mailer(Application)} does not throw an exception
     * when application is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenApplicaitonIsNotNull() {
        Application application = getTestApplication();

        Mailer mailer = new Mailer(application);
    }    // constructorDoesNotThrowExceptionWhenApplicaitonIsNotNull()

    /* registerObserver */

    /**
     * Tests that {@link Mailer#registerObserver(MailerObserver)} throws a
     * NullPointerException when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void registerObserverThrowsExceptionWhenObserverIsNull() {
        MailerObserver observer = null;
        Mailer mailer = new Mailer(getTestApplication());

        mailer.registerObserver(observer);
    }    // registerObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that {@link Mailer#registerObserver(MailerObserver)} does not throw
     * an exception when observer is not null.
     */
    @Test
    public void registerObserverDoesNotThrowExceptionWhenObserverIsNotNull() {
        MailerObserver observer = new TestObserver();
        Mailer mailer = new Mailer(getTestApplication());

        mailer.registerObserver(observer);
    }    // registerObserverDoesNotThrowExceptionWhenObserverIsNotNull()

    /**
     * Tests that {@link Mailer#registerObserver(MailerObserver)} does not throw
     * an exception when observer is registered twice.
     */
    @Test
    public void registerObserverDoesNotThrowExceptionWhenObserverIsRegisteredTwice() {
        MailerObserver observer = new TestObserver();
        Mailer mailer = new Mailer(getTestApplication());
        mailer.registerObserver(observer);

        mailer.registerObserver(observer);
    }    // registerObserverDoesNotThrowExceptionWhenObserverIsRegisteredTwice()

    /* notifyObservers */

    /**
     * Tests that {@link Mailer#notifyObservers()} does not throw an exception
     * when there are no observers.
     */
    @Test
    public void notifyObserversDoesNotThrowExceptionWhenThereAreNoObservers() {
        Mailer mailer = new Mailer(getTestApplication());

        mailer.notifyObservers();
    }    // notifyObserversDoesNotThrowExceptionWhenThereAreNoObservers()

    /**
     * Tests that {@link Mailer#notifyObservers()} does not throw an exception
     * when there is only one observer.
     */
    @Test
    public void notifyObserversDoesNotThrowExceptionWhenThereIsOneObserver() {
        MailerObserver observer = new TestObserver();
        Mailer mailer = new Mailer(getTestApplication());
        mailer.registerObserver(observer);

        mailer.notifyObservers();
    }    // notifyObserversDoesNotThrowExceptionWhenThereIsOneObserver()

    /**
     * Tests that {@link Mailer#notifyObservers()} does not throw an exception
     * when there are multiple observers.
     */
    @Test
    public void notifyObserversDoesNotThrowExceptionWhenThereAreMultipleObservers() {
        List<MailerObserver>observers = Arrays.asList(new TestObserver(), new TestObserver());
        Mailer mailer = new Mailer(getTestApplication());
        for (MailerObserver observer : observers) {
            mailer.registerObserver(observer);
        }    // for

        mailer.notifyObservers();
    }    // notifyObserversDoesNotThrowExceptionWhenThereAreMultipleObservers()

    /**
     * Tests that {@link Mailer#notifyObservers()} notifies all observers.
     */
    @Test
    public void notifyObserversNotifiesAllObservers() {
        List<TestObserver>observers = Arrays.asList(new TestObserver(), new TestObserver());
        Mailer mailer = new Mailer(getTestApplication());
        for (TestObserver observer : observers) {
            mailer.registerObserver(observer);
        }    // for

        mailer.notifyObservers();

        for (TestObserver observer : observers) {
            assertTrue(observer.hasBeenNotified());
        }    // for
    }    // notifyObserversDoesNotThrowExceptionWhenThereAreMultipleObservers()

    /**
     * Tests that {@link Mailer#notifyObservers()} notifies all observers
     * exactly once.
     */
    @Test
    public void notifyObserversNotifiesAllObserversExactlyOnce() {
        List<TestObserver>observers = Arrays.asList(new TestObserver(), new TestObserver());
        Mailer mailer = new Mailer(getTestApplication());
        for (TestObserver observer : observers) {
            mailer.registerObserver(observer);
        }    // for

        mailer.notifyObservers();

        for (TestObserver observer : observers) {
            int expected = 1;
            int received = observer.getNotifications();
            assertEquals(expected, received);
        }    // for
    }    // notifyObserversDoesNotThrowExceptionWhenThereAreMultipleObservers()

    /* send */

    /**
     * Tests that {@link Mailer#send(MailMessage, String)} throws a
     * NullPointerException when message is null.
     */
    @Test(expected = NullPointerException.class)
    public void sendThrowsExceptionWhenMessageIsNull() {
        MailMessage message = null;
        String password = "foo";
        Mailer mailer = new Mailer(getTestApplication());

        mailer.send(message, password);
    }    // sendThrowsExceptionWhenMessageIsNull()

    /**
     * Tests that
     * {@link Mailer#send(MailMessage, String)}
     * does not throw an exception when password is null.
     */
    @Test
    public void sendDoesNotThrowExceptionWhenPasswordIsNull() {
        MailMessage message = new MailMessage("foo", "bar", "baz", "smurf", "la");
        String password = null;
        Mailer mailer = new Mailer(getTestApplication());

        mailer.send(message, password);
    }    // sendDoesNotThrowExceptionWhenNoArgumentIsNull()

    /**
     * Tests that
     * {@link Mailer#send(MailMessage, String)}
     * does not throw an exception when no argument is null.
     */
    @Test
    public void sendDoesNotThrowExceptionWhenNoArgumentIsNull() {
        MailMessage message = new MailMessage("foo", "bar", "baz", "smurf", "la");
        String password = "foo";
        Mailer mailer = new Mailer(getTestApplication());

        mailer.send(message, password);
    }    // sendDoesNotThrowExceptionWhenNoArgumentIsNull()

    /* getStatus */

    /**
     * Tests that {@link Mailer#getStatus()} does not throw an exception.
     */
    @Test
    public void getStatusDoesNotThrowException() {
        Mailer mailer = new Mailer(getTestApplication());

        mailer.getStatus();
    }    // getStatusDoesNotThrowException()

    /**
     * Tests that {@link Mailer#getStatus()} does not return null.
     */
    @Test
    public void getStatusDoesNotReturnNull() {
        Mailer mailer = new Mailer(getTestApplication());

        MailerStatus received = mailer.getStatus();

        assertNotNull(received);
    }    // getStatusDoesNotReturnNull()

    /**
     * Tests that {@link Mailer#getStatus()} returns
     * {@link MailerStatus#NOT_STARTED} before {@link Mailer#send()} has been
     * called.
     */
    @Test
    public void getStatusReturnsNotStartedBeforeSend() {
        Mailer mailer = new Mailer(getTestApplication());

        MailerStatus received = mailer.getStatus();

        MailerStatus expected = MailerStatus.NOT_STARTED;
        assertEquals(expected, received);
    }    // getStatusReturnsNotStartedBeforeSend()

    /* getLastServerResponse */

    /**
     * Tests that {@link Mailer#getLastServerResponse()} does not throw an
     * exception.
     */
    @Test
    public void getLastServerResponseDoesNotThrowException() {
        Mailer mailer = new Mailer(getTestApplication());

        mailer.getLastServerResponse();
    }    // getLastServerResponseDoesNotThrowException()

    /**
     * Tests that {@link Mailer#getLastServerResponse()} returns null before
     * {@link Mailer#send()} has been called.
     */
    @Test
    public void getLastServerResponseReturnsNullBeforeSendHasBeenCalled() {
        Mailer mailer = new Mailer(getTestApplication());

        String received = mailer.getLastServerResponse();

        assertNull(received);
    }    // getLastServerResponseReturnsNullBeforeSendHasBeenCalled()

    /* getLastError */

    /**
     * Tests that {@link Mailer#getLastError()} does not throw an
     * exception.
     */
    @Test
    public void getLastErrorDoesNotThrowException() {
        Mailer mailer = new Mailer(getTestApplication());

        mailer.getLastError();
    }    // getLastErrorDoesNotThrowException()

    /**
     * Tests that {@link Mailer#getLastError()} returns null before
     * {@link Mailer#send()} has been called.
     */
    @Test
    public void getLastErrorReturnsNullBeforeSendHasBeenCalled() {
        Mailer mailer = new Mailer(getTestApplication());

        Exception received = mailer.getLastError();

        assertNull(received);
    }    // getLastErrorReturnsNullBeforeSendHasBeenCalled()

}
