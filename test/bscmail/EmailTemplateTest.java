/*
 * Copyright © 2016 its authors.  See the file "AUTHORS" for details.
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import main.ReadWritableFactory;
import main.ReadWritableTest;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EmailTemplate}.
 *
 * @author Wayne Miller
 */
public class EmailTemplateTest extends ReadWritableTest {

    /*
     * Class methods
     */

    /**
     * Returns the event property to be tested.
     * 
     * @return the event property to be tested
     */
    @Override
    protected EmailTemplate getReadWritable() {
        return new EmailTemplate("foo", "bar");
    }    // getReadWritable()


    /*
     * Unit tests
     */

    /* constructor */

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * throws a {@link NullPointerException} when preScheduleText is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenNameIsNull() {
        String preScheduleText = null;
        String postScheduleText = "bar";

        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);
    }    // constructorThrowsExceptionWhenNameIsNull()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * does not throw an exception when preScheduleText is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNameIsEmpty() {
        String preScheduleText = "";
        String postScheduleText = "bar";

        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);
    }    // constructorDoesNotThrowExceptionWhenNameIsEmpty()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * throws a {@link NullPointerException} when postScheduleText is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenDefaultValueIsNull() {
        String preScheduleText = "foo";
        String postScheduleText = null;

        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);
    }    // constructorThrowsExceptionWhenDefaultValueIsNull()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * does not throw an exception when postScheduleText is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenDefaultValueIsEmpty() {
        String preScheduleText = "foo";
        String postScheduleText = "";

        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);
    }    // constructorDoesNotThrowExceptionWhenDefaultValueIsEmpty()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * does not throw an exception when neither parameter is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNoParamIsNull() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";

        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);
    }    // constructorDoesNotThrowExceptionWhenNoParamIsNull()

    /* getPreScheduleText */

    /**
     * Tests that {@link EmailTemplate#getPreScheduleText()} does not throw an
     * exception.
     */
    @Test
    public void getPreScheduleTextDoesNotThrowException() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        emailTemplate.getPreScheduleText();
    }    // getPreScheduleTextDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate#getPreScheduleText()} returns the value
     * passed to the constructor.
     */
    @Test
    public void getPreScheduleTextReturnsCorrectValue() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        String received = emailTemplate.getPreScheduleText();

        String expected = preScheduleText;
        assertEquals(expected, received);
    }    // getPreScheduleTextReturnsCorrectValue()

    /* getPostScheduleText */

    /**
     * Tests that {@link EmailTemplate#getPostScheduleText()} does not throw an
     * exception.
     */
    @Test
    public void getPostScheduleTextDoesNotThrowException() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        emailTemplate.getPostScheduleText();
    }    // getPostScheduleTextDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate#getPostScheduleText()} returns the value
     * passed to the constructor.
     */
    @Test
    public void getPostScheduleTextReturnsCorrectValue() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        String received = emailTemplate.getPostScheduleText();

        String expected = postScheduleText;
        assertEquals(expected, received);
    }    // getPostScheduleTextReturnsCorrectValue()

    /* getReadWritableProperties */
    
    /**
     * Tests that {@link EmailTemplate#getReadWritableProperties()} returns the
     * correct value.
     */
    @Test
    public void getReadWritablePropertiesReturnsTheCorrectValue() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        Map<String, Object> received = emailTemplate.getReadWritableProperties();

        Map<String, Object> expected = new HashMap<>();
        expected.put("preScheduleText", preScheduleText);
        expected.put("postScheduleText", postScheduleText);
        assertEquals(expected, received);
    }    // Test()
    
    /**
     * Tests that the return value of
     * {@link EmailTemplate#getReadWritableProperties()} has the correct
     * iteration order.
     */
    @Test
    public void getReadWritablePropertiesHasTheCorrectIterationOrder() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        Map<String, Object> properties = emailTemplate.getReadWritableProperties();
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for

        List<String> expected = Arrays.asList("preScheduleText", "postScheduleText");
        assertEquals(expected, received);
    }    // getReadWritablePropertiesHasTheCorrectIterationOrder()

    /* equals */

    /**
     * Tests that {@link EmailTemplate#equals(Object)} does not throw an
     * exception when the argument is null.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNull() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        Object obj = null;
        emailTemplate.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNull()
    
    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNull() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        Object obj = null;
        boolean received = emailTemplate.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNull()
    
    /**
     * Tests that {@link EmailTemplate#equals(Object)} does not throw an
     * exception when the argument is not an email template.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNotEmailTemplate() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        Object obj = 1;
        emailTemplate.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNotEmailTemplate()
    
    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument is not an email template.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNotEmailTemplate() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        Object obj = 1;
        boolean received = emailTemplate.equals(obj);

        assertFalse(received);
    }    // equalsReturnsFalseWhenArgumentIsNotEmailTemplate()
    
    /**
     * Tests that {@link EmailTemplate#equals(Object)} does not throw an
     * exception when the argument is an email template.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsEmailTemplate() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        Object obj = new EmailTemplate(preScheduleText, postScheduleText);
        emailTemplate.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsEmailTemplate()
    
    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument has a different preScheduleText than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentName() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        EmailTemplate obj = new EmailTemplate(preScheduleText + "X", postScheduleText);
        boolean received = emailTemplate.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentName()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument has a different postScheduleText than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentDefaultValue() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        EmailTemplate obj = new EmailTemplate(preScheduleText, postScheduleText + "X");
        boolean received = emailTemplate.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentDefaultValue()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns true when the
     * argument has identical properties.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentHasSameNameAndDefaultValue() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        EmailTemplate obj = new EmailTemplate(preScheduleText, postScheduleText);
        boolean received = emailTemplate.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentHasSameNameAndDefaultValue()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns true when the
     * argument is the same object as the caller.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsIdentical() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        EmailTemplate obj = emailTemplate;
        boolean received = emailTemplate.equals((Object)obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsIdentical()

    /* hashCode */
    
    /**
     * Tests that {@link EmailTemplate#hashCode()} does not throw an exception.
     */
    @Test
    public void hashCodeDoesNotThrowException() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        emailTemplate.hashCode();
    }    // hashCodeDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate#hashCode()} returns equal values for
     * equal email templates.
     */
    @Test
    public void hashCodeReturnsEqualValuesForEquivalentVolunteers() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate1 = new EmailTemplate(preScheduleText, postScheduleText);
        EmailTemplate emailTemplate2 = new EmailTemplate(preScheduleText, postScheduleText);

        int expected = emailTemplate1.hashCode();
        int received = emailTemplate2.hashCode();
        assertEquals(expected, received);
    }    // hashCodeReturnsEqualValuesForEquivalentVolunteers()

    /* clone */
    
    /**
     * Tests that {@link EmailTemplate#clone()} does not throw an exception.
     */
    @Test
    public void cloneDoesNotThrowException() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        emailTemplate.clone();
    }    // cloneDoesNotThrowException()
    
    /**
     * Tests that the return value of {@link EmailTemplate#clone()} is not null.
     */
    @Test
    public void cloneDoesNotReturnNull() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        EmailTemplate received = emailTemplate.clone();

        assertNotNull(received);
    }    // testCloneNcloneDoesNotReturnNullotNull()
    
    /**
     * Tests that the return value of {@link EmailTemplate#clone()} is equal to the
     * argument.
     */
    @Test
    public void returnValueOfCloneIsEqualToOriginal() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        EmailTemplate received = emailTemplate.clone();

        EmailTemplate expected = emailTemplate;
        assertEquals(expected, received);
    }    // returnValueOfCloneIsEqualToOriginal()
    
    /**
     * Tests that the return value of {@link EmailTemplate#clone()} is not identical
     * to the argument.
     */
    @Test
    public void returnValueOfCloneIsNotIdenticalToOriginal() {
        String preScheduleText = "foo";
        String postScheduleText = "bar";
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleText, postScheduleText);

        EmailTemplate received = emailTemplate.clone();

        assertFalse(emailTemplate == received);
    }    // returnValueOfCloneIsNotIdenticalToOriginal()

    /* getVolunteerFactory */
    
    /**
     * Tests that {@link EmailTemplate#getEmailTemplateFactory()} does not throw
     * an exception.
     */
    @Test
    public void volunteerFactoryDoesNotThrowException() {
        EmailTemplate.getEmailTemplateFactory();
    }    // volunteerFactoryDoesNotThrowException()
    
    /**
     * Tests that the return value of
     * {@link EmailTemplate#getVolunteerFactory()} is not null.
     */
    @Test
    public void volunteerFactoryDoesNotReturnNull() {
        ReadWritableFactory factory = EmailTemplate.getEmailTemplateFactory();
        assertNotNull(factory);
    }    // volunteerFactoryDoesNotReturnNull()

}    // EmailTemplateTest
