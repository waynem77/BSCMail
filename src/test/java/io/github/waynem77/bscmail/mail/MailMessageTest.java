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

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link MailMessage}
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class MailMessageTest {

    /**
     * Variable to hold "to" addresses for testing.
     */
    private String toAddresses;

    /**
     * Variable to hold "CC" addresses for testing.
     */
    private String ccAddresses;

    /**
     * Variable to hold "BCC" addresses for testing.
     */
    private String bccAddresses;

    /**
     * Variable to hold the message subject for testing.
     */
    private String subject;

    /**
     * Variable to hold the message body for testing.
     */
    private String body;


    /**
     * Constructs and returns a MailMessage object created from the
     * class variables {@link #toAddresses}, {@link #ccAddresses}, {@link #bccAddresses}, {@link #subject} and
     * {@link #body}. This method may be used to test the constructor or in
     * general tests.
     *
     * @return a MailMessage object created from the class variables
     */
    protected MailMessage makeMailMessageFromClassVariables(){
        return new MailMessage(toAddresses, ccAddresses, bccAddresses, subject, body);
    }    // makeMailMessageFromClassVariables()

    /**
     * Populates the class properties used as constructor arguments before each
     * test.
     */
    @Before
    public void populateConstructorVariables() {
        toAddresses = "one@one, two@two";
        ccAddresses = "three@three, four@four";
        bccAddresses = "five@five, six@six";
        subject = "foo";
        body = "bar";
    }    // populateConstructorVariables()

    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that
     * {@link MailMessage#MailMessage(String, String, String, String, String)}
     * does not throw an exception when toAddresses is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenToAddressesIsNull() {
        toAddresses = null;

        MailMessage mailMessage = makeMailMessageFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenToAddressesIsNull()

    /**
     * Tests that
     * {@link MailMessage#MailMessage(String, String, String, String, String)}
     * does not throw an exception when ccAddresses is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenCcAddressesIsNull() {
        ccAddresses = null;

        MailMessage mailMessage = makeMailMessageFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenCcAddressesIsNull()

    /**
     * Tests that
     * {@link MailMessage#MailMessage(String, String, String, String, String)}
     * does not throw an exception when bccAddresses is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenBccAddressesIsNull() {
        bccAddresses = null;

        MailMessage mailMessage = makeMailMessageFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenBccAddressesIsNull()

    /**
     * Tests that
     * {@link MailMessage#MailMessage(String, String, String, String, String)}
     * throws an exception when subject is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenSubjectIsNull() {
        subject = null;

        MailMessage mailMessage = makeMailMessageFromClassVariables();
    }    // constructorThrowsExceptionWhenSubjectIsNull()

    /**
     * Tests that
     * {@link MailMessage#MailMessage(String, String, String, String, String)}
     * throws an exception when body is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenBodyIsNull() {
        body = null;

        MailMessage mailMessage = makeMailMessageFromClassVariables();
    }    // constructorThrowsExceptionWhenBodyIsNull()

    /**
     * Tests that
     * {@link MailMessage#MailMessage(String, String, String, String, String)}
     * does not throw an exception when no parameter is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNoParameterIsNull() {
        MailMessage mailMessage = makeMailMessageFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenNoParameterIsNull()

    /* getToAddresses */

    /**
     * Tests that {@link MailMessage#getToAddresses()} does not throw an
     * exception when toAddresses is null.
     */
    @Test
    public void getToAddressessDoesNotThrowExceptionWhenToAddressesIsNull() {
        toAddresses = null;
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        mailMessage.getToAddresses();
    }    // getToAddressessDoesNotThrowExceptionWhenToAddressesIsNull()

    /**
     * Tests that {@link MailMessage#getToAddresses()} returns the correct value
     * when toAddresses is null.
     */
    @Test
    public void getToAddressessReturnsCorrectValueWhenToAddressesIsNull() {
        toAddresses = null;
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        String received = mailMessage.getToAddresses();

        String expected = toAddresses;
        assertEquals(expected, received);
    }    // getToAddressessDoesNotThrowExceptionWhenToAddressesIsNull()

    /**
     * Tests that {@link MailMessage#getToAddresses()} does not throw an
     * exception when toAddresses is not null.
     */
    @Test
    public void getToAddressessDoesNotThrowExceptionWhenToAddressesIsNotNull() {
        assertNotNull("Incorrect test!", toAddresses);
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        mailMessage.getToAddresses();
    }    // getToAddressessDoesNotThrowExceptionWhenToAddressesIsNotNull()

    /**
     * Tests that {@link MailMessage#getToAddresses()} returns the correct value
     * when toAddresses is not null.
     */
    @Test
    public void getToAddressessReturnsCorrectValueWhenToAddressesIsNotNull() {
        assertNotNull("Incorrect test!", toAddresses);
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        String received = mailMessage.getToAddresses();

        String expected = toAddresses;
        assertEquals(expected, received);
    }    // getToAddressessReturnsCorrectValueWhenToAddressesIsNotNull()

    /* getCcAddresses */

    /**
     * Tests that {@link MailMessage#getCcAddresses()} does not throw an
     * exception when ccAddresses is null.
     */
    @Test
    public void getCcAddressessDoesNotThrowExceptionWhenCcAddressesIsNull() {
        ccAddresses = null;
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        mailMessage.getCcAddresses();
    }    // getCcAddressessDoesNotThrowExceptionWhenCcAddressesIsNull()

    /**
     * Tests that {@link MailMessage#getCcAddresses()} returns the correct value
     * when ccAddresses is null.
     */
    @Test
    public void getCcAddressessReturnsCorrectValueWhenCcAddressesIsNull() {
        ccAddresses = null;
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        String received = mailMessage.getCcAddresses();

        String expected = ccAddresses;
        assertEquals(expected, received);
    }    // getCcAddressessDoesNotThrowExceptionWhenCcAddressesIsNull()

    /**
     * Tests that {@link MailMessage#getCcAddresses()} does not throw an
     * exception when ccAddresses is not null.
     */
    @Test
    public void getCcAddressessDoesNotThrowExceptionWhenCcAddressesIsNotNull() {
        assertNotNull("Incorrect test!", ccAddresses);
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        mailMessage.getCcAddresses();
    }    // getCcAddressessDoesNotThrowExceptionWhenCcAddressesIsNotNull()

    /**
     * Tests that {@link MailMessage#getCcAddresses()} returns the correct value
     * when ccAddresses is not null.
     */
    @Test
    public void getCcAddressessReturnsCorrectValueWhenCcAddressesIsNotNull() {
        assertNotNull("Incorrect test!", ccAddresses);
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        String received = mailMessage.getCcAddresses();

        String expected = ccAddresses;
        assertEquals(expected, received);
    }    // getCcAddressessReturnsCorrectValueWhenCcAddressesIsNotNull()

    /* getBccAddresses */

    /**
     * Tests that {@link MailMessage#getBccAddresses()} does not throw an
     * exception when bccAddresses is null.
     */
    @Test
    public void getBccAddressessDoesNotThrowExceptionWhenBccAddressesIsNull() {
        bccAddresses = null;
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        mailMessage.getBccAddresses();
    }    // getBccAddressessDoesNotThrowExceptionWhenBccAddressesIsNull()

    /**
     * Tests that {@link MailMessage#getBccAddresses()} returns the correct
     * value when bccAddresses is null.
     */
    @Test
    public void getBccAddressessReturnsCorrectValueWhenBccAddressesIsNull() {
        bccAddresses = null;
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        String received = mailMessage.getBccAddresses();

        String expected = bccAddresses;
        assertEquals(expected, received);
    }    // getBccAddressessDoesNotThrowExceptionWhenBccAddressesIsNull()

    /**
     * Tests that {@link MailMessage#getBccAddresses()} does not throw an
     * exception when bccAddresses is not null.
     */
    @Test
    public void getBccAddressessDoesNotThrowExceptionWhenBccAddressesIsNotNull() {
        assertNotNull("Incorrect test!", bccAddresses);
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        mailMessage.getBccAddresses();
    }    // getBccAddressessDoesNotThrowExceptionWhenBccAddressesIsNotNull()

    /**
     * Tests that {@link MailMessage#getBccAddresses()} returns the correct
     * value when bccAddresses is not null.
     */
    @Test
    public void getBccAddressessReturnsCorrectValueWhenBccAddressesIsNotNull() {
        assertNotNull("Incorrect test!", bccAddresses);
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        String received = mailMessage.getBccAddresses();

        String expected = bccAddresses;
        assertEquals(expected, received);
    }    // getBccAddressessReturnsCorrectValueWhenBccAddressesIsNotNull()

    /* getSubject */

    /**
     * Tests that {@link MailMessage#getSubject()} does not throw an exception.
     */
    @Test
    public void getSubjectsDoesNotThrowException() {
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        mailMessage.getSubject();
    }    // getSubjectsDoesNotThrowException()

    /**
     * Tests that {@link MailMessage#getSubject()} returns the correct value.
     */
    @Test
    public void getSubjectsReturnsCorrectValue() {
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        String received = mailMessage.getSubject();

        String expected = subject;
        assertEquals(expected, received);
    }    // getSubjectsReturnsCorrectValue()

    /* getBody */

    /**
     * Tests that {@link MailMessage#getBody()} does not throw an exception.
     */
    @Test
    public void getBodysDoesNotThrowException() {
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        mailMessage.getBody();
    }    // getBodysDoesNotThrowException()

    /**
     * Tests that {@link MailMessage#getBody()} returns the correct value.
     */
    @Test
    public void getBodysReturnsCorrectValue() {
        MailMessage mailMessage = makeMailMessageFromClassVariables();

        String received = mailMessage.getBody();

        String expected = body;
        assertEquals(expected, received);
    }    // getBodysReturnsCorrectValue()

}    // MailMessageTest
