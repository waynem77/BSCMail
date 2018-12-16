/*
 * Copyright © 2016-2018 its authors.  See the file "AUTHORS" for details.
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

import bscmail.EmailTemplate.Factory;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EmailTemplate.Factory}.
 *
 * @author Wayne Miller
 */
public class EmailTemplateFactoryTest {

    /* class methods and properties */

    /**
     * Send type variable used in testing.
     */
    private String sendType;

    /**
     * Send type object variable used in testing.
     */
    private EmailTemplate.SendType sendTypeForEmailTemplateConstructor;

    /**
     * Pre schedule text variable used in testing.
     */
    private String preScheduleText;

    /**
     * Post schedule text variable used in testing.
     */
    private String postScheduleText;

    /**
     * Subject line template variable used in testing.
     */
    private String subjectLineTemplate;

    /**
     * Date format string variable used in testing.
     */
    private String dateFormatString;

    /**
     * Properties map used in testing.
     */
    private Map<String, Object> properties;

    /**
     * Initializes the class variables before each test.
     */
    @Before
    public void initializeClassVariables() {
        sendTypeForEmailTemplateConstructor = EmailTemplate.SendType.CC;
        sendType = sendTypeForEmailTemplateConstructor.getRwRepresentation();
        preScheduleText = "foo";
        postScheduleText = "bar";
        subjectLineTemplate = "baz";
        dateFormatString = "YYYY-MM-dd";
        properties = new HashMap<>();
        properties.put("sendType", sendType);
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);
        properties.put("dateFormatString", dateFormatString);
    }    // initializeClassVariables()

    /**
     * Returns a string that is invalid as a send type.
     * @return a string that is invalid as a send type
     */
    private String getInvalidSendTypeString() {
        String value = "";
        boolean stringIsInvalid = true;
        while (stringIsInvalid) {
            value += "x";
            stringIsInvalid = false;
            for (EmailTemplate.SendType sendType : EmailTemplate.SendType.values()) {
                if (value.equals(sendType.getRwRepresentation())) {
                    stringIsInvalid = true;
                }    // if
            }    // for
        }    // while
        return value;
    }    // getInvalidSendTypeString()

    /* constructReadWritable */

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)}
     * throws a {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructReadWritableThrowsExceptionWhenPropertiesIsNull() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties = null;

        factory.constructReadWritable(properties);
    }    // constructReadWritableThrowsExceptionWhenPropertiesIsNull()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties = new HashMap<>();

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties = new HashMap<>();

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "preScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingPreScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("preScheduleText");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingPreScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "preScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingPreScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("preScheduleText");

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingPreScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value for "preScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullPreScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        preScheduleText = null;
        properties.put("preScheduleText", preScheduleText);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullPreScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value for "preScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullhenPropertiesHasNullPreScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        preScheduleText = null;
        properties.put("preScheduleText", preScheduleText);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullhenPropertiesHasNullPreScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "sendType".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingSendType() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("sendType");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingSendType()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "sendType".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingSendType() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("sendType");

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingSendType()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value for "sendType".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullSendType() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        sendType = null;
        properties.put("sendType", sendType);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullSendType()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value for "sendType".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullhenPropertiesHasNullSendType() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        sendType = null;
        properties.put("sendType", sendType);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullhenPropertiesHasNullSendType()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has an invalid value for
     * "sendType".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasInvalidSendType() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        sendType = getInvalidSendTypeString();
        properties.put("sendType", sendType);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasInvalidSendType()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has an invalid value for "sendType".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasInvalidSendType() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        sendType = getInvalidSendTypeString();
        properties.put("sendType", sendType);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasInvalidSendType()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * creates an email template with a send type of "to" when properties has an
     * invalid value for "sendType".
     */
    @Test
    public void constructReadWritableCreatesEmailTemplateWithSendTypeOfToWhenPropertiesHasInvalidSendType() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        sendType = getInvalidSendTypeString();
        properties.put("sendType", sendType);
        EmailTemplate emailTemplate = factory.constructReadWritable(properties);

        EmailTemplate.SendType received = emailTemplate.getSendType();

        EmailTemplate.SendType expected = EmailTemplate.SendType.TO;
        assertEquals(expected, received);
    }    // constructReadWritableCreatesEmailTemplateWithSendTypeOfToWhenPropertiesHasInvalidSendType()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingPostScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("postScheduleText");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingPostScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingPostScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("postScheduleText");

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingPostScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullPostScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        postScheduleText = null;
        properties.put("postScheduleText", postScheduleText);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissinPostScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasNullPostScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        postScheduleText = null;
        properties.put("postScheduleText", postScheduleText);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasNullPostScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "subjectLineTemplate".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingSubjectLineTemplate() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("subjectLineTemplate");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingSubjectLineTemplate()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "subjectLineTemplate".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingSubjectLineTemplate() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("subjectLineTemplate");

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingSubjectLineTemplate()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value
     * "subjectLineTemplate".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullSubjectLineTemplate() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        subjectLineTemplate = null;
        properties.put("subjectLineTemplate", subjectLineTemplate);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissinSubjectLineTemplate()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value "subjectLineTemplate".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasNullSubjectLineTemplate() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        subjectLineTemplate = null;
        properties.put("subjectLineTemplate", subjectLineTemplate);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasNullSubjectLineTemplate()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "dateFormatString".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingDateFormatString() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("dateFormatString");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingDateFormatString()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "dateFormatString".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingDateFormatString() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.remove("dateFormatString");

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingDateFormatString()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value
     * "dateFormatString".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullDateFormatString() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        dateFormatString = null;
        properties.put("dateFormatString", dateFormatString);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissinDateFormatString()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value "dateFormatString".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasNullDateFormatString() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        dateFormatString = null;
        properties.put("dateFormatString", dateFormatString);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasNullDateFormatString()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties all required values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties all required values.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasAllValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasAllValues()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)}
     * returns the correct value when properties all required values.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasAllValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();

        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = new EmailTemplate(sendTypeForEmailTemplateConstructor, preScheduleText, postScheduleText, subjectLineTemplate, dateFormatString);
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasAllValues()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has extraneous values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.put("extra", "baz");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has extraneous values.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        properties.put("extra", "baz");

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)}
     * returns the correct value when properties has extraneous values.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasExtraneousValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();

        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = new EmailTemplate(sendTypeForEmailTemplateConstructor, preScheduleText, postScheduleText, subjectLineTemplate, dateFormatString);
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        Object sendTypeObj = -3;
        Object preScheduleTextObj = 1;
        Object postScheduleTextObj = 2.0;
        Object subjectLineTemplateObj = Boolean.TRUE;
        Object dateFormatStringObj = new HashMap();
        properties = new HashMap<>();
        properties.put("sendType", sendTypeObj);
        properties.put("preScheduleText", preScheduleTextObj);
        properties.put("postScheduleText", postScheduleTextObj);
        properties.put("subjectLineTemplate", subjectLineTemplateObj);
        properties.put("dateFormatString", dateFormatStringObj);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not returns null when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        Object sendTypeObj = -3;
        Object preScheduleTextObj = 1;
        Object postScheduleTextObj = 2.0;
        Object subjectLineTemplateObj = Boolean.TRUE;
        Object dateFormatStringObj = new HashMap();
        properties = new HashMap<>();
        properties.put("sendType", sendTypeObj);
        properties.put("preScheduleText", preScheduleTextObj);
        properties.put("postScheduleText", postScheduleTextObj);
        properties.put("subjectLineTemplate", subjectLineTemplateObj);
        properties.put("dateFormatString", dateFormatStringObj);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)}
     * returns the correct value when the dateFormatString value is not
     * acceptable to
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenDateFormatStringIsIllegal() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        dateFormatString = "bad arg";
        properties.put("dateFormatString", dateFormatString);

        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = new EmailTemplate(sendTypeForEmailTemplateConstructor, preScheduleText, postScheduleText, subjectLineTemplate, "");
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenDateFormatStringIsIllegal()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} acts
     * as the reverse of {@link EmailTemplate#getReadWritableProperties()}.
     */
    @Test
    public void constructReadWritableWorksReflexively() {
        EmailTemplate originalEmailTemplate = new EmailTemplate(sendTypeForEmailTemplateConstructor, preScheduleText, postScheduleText, subjectLineTemplate, dateFormatString);

        originalEmailTemplate.getReadWritableProperties();
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = originalEmailTemplate;
        assertEquals(expected, received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

}    // EmailTemplateFactoryTest
