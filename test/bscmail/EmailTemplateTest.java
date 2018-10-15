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

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
     * Class methods and properties
     */

    /**
     * Pre-schedule text variable for testing.
     */
    private String preScheduleText;

    /**
     * Post-schedule text variable for testing.
     */
    private String postScheduleText;

    /**
     * Subject line template variable for testing.
     */
    private String subjectLineTemplate;

    /**
     * Date format string variable for testing.
     */
    private String dateFormatString;

    /**
     * Returns the event property to be tested.
     *
     * @return the event property to be tested
     */
    @Override
    protected EmailTemplate getReadWritable() {
        return new EmailTemplate("foo", "bar", "baz", "MMM dd, YYYY");
    }    // getReadWritable()

    /**
     * Constructs and returns an EmailTemplate created from the class variables
     * {@link #preScheduleText}, {@link #postScheduleText}, and
     * {@link #subjectLineTemplate}. This method may be used to test the
     * constructor or in general tests.
     *
     * @return an EmailTemplate created from the class variables
     */
    protected EmailTemplate makeEmailTemplateFromClassVariables(){
        return new EmailTemplate(preScheduleText, postScheduleText, subjectLineTemplate, dateFormatString);
    }    // makeEmailTemplateFromClassVariables
    /**
     * Populates the class properties used as constructor arguments before each
     * test.
     */
    @Before
    public void populateConstructorVariables() {
        preScheduleText = "foo";
        postScheduleText = "bar";
        subjectLineTemplate = "baz";
        dateFormatString = "MMM dd, YYYY";
    }    // populateConstructorVariables()

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
    public void constructorThrowsExceptionWhenPreScheduleTextIsNull() {
        preScheduleText = null;

        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorThrowsExceptionWhenPreScheduleTextIsNull()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * does not throw an exception when preScheduleText is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenPreScheduleTextIsEmpty() {
        preScheduleText = "";

        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenPreScheduleTextIsEmpty()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * throws a {@link NullPointerException} when postScheduleText is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenPostScheduleTextIsNull() {
        postScheduleText = null;

        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorThrowsExceptionWhenPostScheduleTextIsNull()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * does not throw an exception when postScheduleText is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenPostScheduleTextIsEmpty() {
        postScheduleText = "";

        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenPostScheduleTextIsEmpty()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * throws a {@link NullPointerException} when subjectLineTemplate is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenSubjectLineTemplateIsNull() {
        subjectLineTemplate = null;

        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorThrowsExceptionWhenSubjectLineTemplateIsNull()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * does not throw an exception when subjectLineTemplate is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionSubjectLineTemplateIsEmpty() {
        subjectLineTemplate = "";

        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenSubjectLineTemplateIsEmpty()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * throws a {@link NullPointerException} when dateFormatString is
     * null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenDateFormatStringIsNull() {
        dateFormatString = null;

        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorThrowsExceptionWhenDateFormatStringIsNull()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * does not throw an exception when dateFormatString is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionDateFormatStringIsEmpty() {
        dateFormatString = "";

        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorDoesNotThrowExceptionDateFormatStringIsEmpty()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * throws a {@link IllegalArgumentException} when dateFormatString is
     * not in appropriate format.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWhenDateFormatStringIsNotInAppropriateFormat() {
        dateFormatString = "foo";

        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorThrowsExceptionWhenDateFormatStringIsNotInAppropriateFormat()

    /**
     * Tests that
     * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String)}
     * does not throw an exception when no parameter is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNoParamIsNull() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();
    }    // constructorDoesNotThrowExceptionWhenNoParamIsNull()

    /* getPreScheduleText */

    /**
     * Tests that {@link EmailTemplate#getPreScheduleText()} does not throw an
     * exception.
     */
    @Test
    public void getPreScheduleTextDoesNotThrowException() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        emailTemplate.getPreScheduleText();
    }    // getPreScheduleTextDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate#getPreScheduleText()} returns the value
     * passed to the constructor.
     */
    @Test
    public void getPreScheduleTextReturnsCorrectValue() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

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
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        emailTemplate.getPostScheduleText();
    }    // getPostScheduleTextDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate#getPostScheduleText()} returns the value
     * passed to the constructor.
     */
    @Test
    public void getPostScheduleTextReturnsCorrectValue() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        String received = emailTemplate.getPostScheduleText();

        String expected = postScheduleText;
        assertEquals(expected, received);
    }    // getPostScheduleTextReturnsCorrectValue()

    /* getSubjectLineTemplate */

    /**
     * Tests that {@link EmailTemplate#getSubjectLineTemplate()} does not throw
     * an exception.
     */
    @Test
    public void getSubjectLineTemplateDoesNotThrowException() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        emailTemplate.getSubjectLineTemplate();
    }    // getSubjectLineTemplateDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate#getSubjectLineTemplate()} returns the
     * value passed to the constructor.
     */
    @Test
    public void getSubjectLineTemplateReturnsCorrectValue() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        String received = emailTemplate.getSubjectLineTemplate();

        String expected = subjectLineTemplate;
        assertEquals(expected, received);
    }    // getSubjectLineTemplateReturnsCorrectValue()

    /* getDateFormatString */

    /**
     * Tests that {@link EmailTemplate#getDateFormatString()} does not
     * throw an exception.
     */
    @Test
    public void getDateFormatStringDoesNotThrowException() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        emailTemplate.getDateFormatString();
    }    // getDateFormatStringDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate#getDateFormatString()} does not
     * return null.
     */
    @Test
    public void getDateFormatStringDoesNotReturnNull() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        String received = emailTemplate.getDateFormatString();

        assertNotNull(received);
    }    // getDateFormatStringDoesNotReturnNull()

    /**
     * Tests that {@link EmailTemplate#getDateFormatString()} returns
     * the value passed to the constructor.
     */
    @Test
    public void getDateFormatStringReturnsCorrectValue() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        String received = emailTemplate.getDateFormatString();

        String expected = dateFormatString;
        assertEquals(expected, received);
    }    // getDateFormatStringReturnsCorrectValue()

    /* getDateFormatter */

    /**
     * Tests that {@link EmailTemplate#getDateFormatter()} does not throw an
     * exception.
     */
    @Test
    public void getDateFormatterDoesNotThrowException() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        emailTemplate.getDateFormatter();
    }    // getDateFormatterDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate#getDateFormatter()} does not return null.
     */
    @Test
    public void getDateFormatterDoesNotReturnNull() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        DateFormat received = emailTemplate.getDateFormatter();

        assertNotNull(received);
    }    // getDateFormatterDoesNotReturnNull()

    /**
     * Tests that {@link EmailTemplate#getDateFormatter()} returns a formatter
     * that formats dates according to the dateFormatString passed to the
     * constructor.
     */
    @Test
    public void getDateFormatterReturnsCorrectFormatter() {
        dateFormatString = "yyyy:MM:dd GG";
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        DateFormat formatter = emailTemplate.getDateFormatter();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 11, 25);
        Date date = calendar.getTime();
        String received = formatter.format(date);
        String expected = "2017:12:25 AD";
        assertEquals(expected, received);
    }    // getDateFormatterReturnsCorrectFormatter()

    /* getReadWritableProperties */

    /**
     * Tests that {@link EmailTemplate#getReadWritableProperties()} returns the
     * correct value.
     */
    @Test
    public void getReadWritablePropertiesReturnsTheCorrectValue() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        Map<String, Object> received = emailTemplate.getReadWritableProperties();

        Map<String, Object> expected = new HashMap<>();
        expected.put("preScheduleText", preScheduleText);
        expected.put("postScheduleText", postScheduleText);
        expected.put("subjectLineTemplate", subjectLineTemplate);
        expected.put("dateFormatString", dateFormatString);
        assertEquals(expected, received);
    }    // Test()

    /**
     * Tests that the return value of
     * {@link EmailTemplate#getReadWritableProperties()} has the correct
     * iteration order.
     */
    @Test
    public void getReadWritablePropertiesHasTheCorrectIterationOrder() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        Map<String, Object> properties = emailTemplate.getReadWritableProperties();
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for

        List<String> expected = Arrays.asList("preScheduleText", "postScheduleText", "subjectLineTemplate", "dateFormatString");
        assertEquals(expected, received);
    }    // getReadWritablePropertiesHasTheCorrectIterationOrder()

    /* equals */

    /**
     * Tests that {@link EmailTemplate#equals(Object)} does not throw an
     * exception when the argument is null.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNull() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        Object obj = null;
        emailTemplate.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNull()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNull() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

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
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        Object obj = 1;
        emailTemplate.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNotEmailTemplate()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument is not an email template.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNotEmailTemplate() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

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
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        Object obj = makeEmailTemplateFromClassVariables();
        emailTemplate.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsEmailTemplate()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument has a different preScheduleText than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentPreScheduleText() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        preScheduleText += "X";
        EmailTemplate obj = makeEmailTemplateFromClassVariables();
        boolean received = emailTemplate.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentPreScheduleText()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument has a different postScheduleText than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentPostScheduleText() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        postScheduleText += "X";
        EmailTemplate obj = makeEmailTemplateFromClassVariables();
        boolean received = emailTemplate.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentPostScheduleText()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument has a different subjectLineTemplate than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentSubjectLineTemplate() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        subjectLineTemplate += "X";
        EmailTemplate obj = makeEmailTemplateFromClassVariables();
        boolean received = emailTemplate.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentSubjectLineTemplate()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns false when the
     * argument has a different dateFormatString than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentDateFormatString() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        dateFormatString += "X";
        EmailTemplate obj = makeEmailTemplateFromClassVariables();
        boolean received = emailTemplate.equals((Object)obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentDateFormatString()

    /**
     * Tests that {@link EmailTemplate#equals(Object)} returns true when the
     * argument has identical properties.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentHasSameNameAndDefaultValue() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        EmailTemplate obj = makeEmailTemplateFromClassVariables();
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
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

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
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        emailTemplate.hashCode();
    }    // hashCodeDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate#hashCode()} returns equal values for
     * equal email templates.
     */
    @Test
    public void hashCodeReturnsEqualValuesForEquivalentVolunteers() {
        EmailTemplate emailTemplate1 = makeEmailTemplateFromClassVariables();
        EmailTemplate emailTemplate2 = makeEmailTemplateFromClassVariables();

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
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        emailTemplate.clone();
    }    // cloneDoesNotThrowException()

    /**
     * Tests that the return value of {@link EmailTemplate#clone()} is not null.
     */
    @Test
    public void cloneDoesNotReturnNull() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

        EmailTemplate received = emailTemplate.clone();

        assertNotNull(received);
    }    // testCloneNcloneDoesNotReturnNullotNull()

    /**
     * Tests that the return value of {@link EmailTemplate#clone()} is equal to the
     * argument.
     */
    @Test
    public void returnValueOfCloneIsEqualToOriginal() {
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

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
        EmailTemplate emailTemplate = makeEmailTemplateFromClassVariables();

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
