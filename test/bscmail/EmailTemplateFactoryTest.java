/*
 * Copyright © 2016 Wayne Miller
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
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingName() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String postScheduleText = "bar";
        Map<String, Object> properties = new HashMap<>();
        properties.put("postScheduleText", postScheduleText);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingName()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "preScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingName() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingName()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value for "preScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullName() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = null;
        String postScheduleText = "bar";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullName()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value for "preScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullhenPropertiesHasNullName() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = null;
        String postScheduleText = "bar";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullhenPropertiesHasNullName()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingDefaultValue() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingDefaultValue()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingDefaultValue() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingDefaultValue()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullDefaultValue() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String postScheduleText = null;
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissinDefaultValue()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value "postScheduleText".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasNullDefaultValue() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String postScheduleText = null;
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

        EmailTemplate received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasNullDefaultValue()

    /**
     * Tests that {@link EmailTemplate.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties all required values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues() {
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

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
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

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
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = new EmailTemplate(preScheduleText, postScheduleText);
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
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
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
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
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
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("extra", "baz");

        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = new EmailTemplate(preScheduleText, postScheduleText);
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
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

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
        Map<String, Object> properties = new HashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);

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
        EmailTemplate originalEmailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        Map<String, Object> properties = originalEmailTemplate.getReadWritableProperties();
        Factory factory = EmailTemplate.getEmailTemplateFactory();
        EmailTemplate received = factory.constructReadWritable(properties);

        EmailTemplate expected = originalEmailTemplate;
        assertEquals(expected, received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

}    // EmailTemplateFactoryTest
