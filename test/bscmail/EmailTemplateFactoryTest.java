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

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)}
     * throws a {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructReadWritableThrowsExceptionWhenPropertiesIsNull() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        Map<String, Object> properties = null;

        factory.constructReadWritable(properties);
    }    // constructReadWritableThrowsExceptionWhenPropertiesIsNull()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        Map<String, Object> properties = new HashMap<>();

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        Map<String, Object> properties = new HashMap<>();

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
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingPreScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "preScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingPreScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

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
        String preScheduleText = null;
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullPreScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value for "preScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullhenPropertiesHasNullPreScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = null;
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullhenPropertiesHasNullPreScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingPostScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingPostScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingPostScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

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
        String preScheduleText = "foo";
        String postScheduleText = null;
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissinPostScheduleText()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasNullPostScheduleText() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String postScheduleText = null;
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

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
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingSubjectLineTemplate()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "subjectLineTemplate".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingSubjectLineTemplate() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

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
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        String subjectLineTemplate = null;
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
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
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        String subjectLineTemplate = null;
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasNullSubjectLineTemplate()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties all required values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties all required values.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasAllValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

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
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = new EmailTemplate(preScheduleText, postScheduleText, subjectLineTemplate);
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasAllValues()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has extraneous values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);
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
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);
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
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);
        properties.put("extra", "baz");

        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = new EmailTemplate(preScheduleText, postScheduleText, subjectLineTemplate);
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        Object preScheduleText = 1;
        Object postScheduleText = 2.0;
        Object subjectLineTemplate = Boolean.TRUE;
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not returns null when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        Object preScheduleText = 1;
        Object postScheduleText = 2.0;
        Object subjectLineTemplate = Boolean.TRUE;
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} acts
     * as the reverse of {@link EmailTemplate#getReadWritableProperties()}.
     */
    @Test
    public void constructReadWritableWorksReflexively() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        String subjectLineTemplate = "baz";
        EmailTemplate originalEmailTemplate = new EmailTemplate(preScheduleText, postScheduleText, subjectLineTemplate);

        Map<String, Object> properties = originalEmailTemplate.getReadWritableProperties();
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = originalEmailTemplate;
        assertEquals(expected, received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

}    // EmailTemplateFactoryTest
