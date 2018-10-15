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

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import main.ReadWritable;
import main.ReadWritableFactory;

/**
 * Represents an email template.  Email templates have the following properties:
 * <ul>
 *   <li>pre-schedule text,</li>
 *   <li>post-schedule text,</li>
 *   <li>subject line template, and</li>
 *   <li>date format string.</li>
 * </ul>
 *
 * The pre-schedule and post-schedule text are simple strings, placed before and
 * after the schedule in the generated email body. The subject line text is a
 * string of a format appropriate for a
 * {@link bscmail.util.format.EmailFormatter}. The date format string is a
 * string of a format appropriate for a {@link java.text.SimpleDateFormat}.
 *
 * @author wayne.miller
 */
public class EmailTemplate implements Cloneable, Serializable, ReadWritable {

    /**
     * Class version number.
     */
    private static final long serialVersionUID = 2L;

    /**
     * A factory that creates an {@link EmailTemplate} from a set of
     * read-writable properties. These properties may be extracted directly from
     * an email template via {@link EmailTemplate#getReadWritableProperties()},
     * but more typically are extracted from a disk file.
     *
     * To obtain an email template factory, use the method
     * {@link EmailTemplate#getEmailTemplateFactory()}.
     *
     * @author Wayne Miller
     */
    public static class Factory implements ReadWritableFactory<EmailTemplate> {

        /**
         * Constructs a new email template factory.
         */
        private Factory() {
        }    // Factory()

        /**
         * Constructs an email template from the given read-writable properties.
         * If the factory is unable to create an email template from the given
         * properties, this method returns null.
         *
         * The email template factory constructs an email template using the
         * following information from the given properties.
         * <ul>
         *   <li>The email template's pre-schedule text is given by the string
         * value of the value corresponding to "preScheduleText".  If such a
         * value does not exist or is null, the pre-schedule text is empty.</li>
         *   <li>The email template's post-schedule text is given by the string
         * value of the value corresponding to "postScheduleText".  If such a
         * value does not exist or is null, the post-schedule text is empty.</li>
         *   <li>The email template's subject line template is given by the
         * string value of the value corresponding to "subjectLineTemplate".  If
         * such a value does not exist or is null, the subject line template is
         * empty.</li>
         *   <li>The email template's date format string is given by the string
         * value of the value corresponding to "dateFormatString". If such a
         * value does not exist, is null, or is in a format not accepted by
         * {@link EmailTemplate#EmailTemplate(java.lang.String, java.lang.String, java.lang.String, java.lang.String)},
         * the date format string is empty.</li>
         * </ul>
         * This method effectively acts as the reverse of
         * {@link EmailTemplate#getReadWritableProperties()}.
         *
         * @param properties the read-writable properties; may not be null
         * @return an email template constructed from the given properties
         * @throws NullPointerException if {@code properties} is null
         */
        @Override
        public EmailTemplate constructReadWritable(Map<String, Object> properties) {
            if (properties == null) {
                throw new NullPointerException("properties may not be null");
            }    // if
            Object preScheduleTextObject = properties.get("preScheduleText");
            Object postScheduleTextObject = properties.get("postScheduleText");
            Object subjectLineTemplateObject = properties.get("subjectLineTemplate");
            Object dateFormatStringObject = properties.get("dateFormatString");
            String preScheduleText = (preScheduleTextObject != null) ? preScheduleTextObject.toString() : "";
            String postScheduleText = (postScheduleTextObject != null) ? postScheduleTextObject.toString() : "";
            String subjectLineTemplate = (subjectLineTemplateObject != null) ? subjectLineTemplateObject.toString() : "";
            String dateFormatString = (dateFormatStringObject != null) ? dateFormatStringObject.toString() : "";
            EmailTemplate emailTemplate;
            try {
                emailTemplate = new EmailTemplate(preScheduleText, postScheduleText, subjectLineTemplate, dateFormatString);
            } catch (Exception e) {    // try
                dateFormatString = "";
                emailTemplate = new EmailTemplate(preScheduleText, postScheduleText, subjectLineTemplate, dateFormatString);
            }    // catch
            return emailTemplate;
        }    // constructReadWritable()

    }    // Factory

    /**
     * Returns a factory that creates email templates from read-writable
     * property maps.
     *
     * @return a factory that creates email templates from read-writable
     * property maps
     */
    public static Factory getEmailTemplateFactory() {
        return new Factory();
    }    // getMangerFactory();

    /**
     * The email template's pre-schedule text.
     */
    private final String preScheduleText;

    /**
     * The email template's post-schedule text.
     */
    private final String postScheduleText;

    /**
     * The email template's subject line template
     */
    private final String subjectLineTemplate;

    /**
     * The date format used in the email.
     */
    private final String dateFormatString;

    /**
     * Constructs a new email template.
     *
     * @param preScheduleText the email template's pre-schedule text; may be
     * empty, but not null
     * @param postScheduleText the email template's post-schedule text; may be
     * empty, but not null
     * @param subjectLineTemplate the email template's subject line template;
     * may be empty, but not null
     * @param dateFormatString the date format to use in the email; may not be
     * null; must be in a format appropriate for a
     * {@link java.text.SimpleDateFormat}
     * @throws NullPointerException if any parameter is null
     * @throws IllegalArgumentException if dateFormatString is not in an
     * appropriate format
     */
    public EmailTemplate(String preScheduleText, String postScheduleText, String subjectLineTemplate, String dateFormatString) {
        if (preScheduleText == null) {
            throw new NullPointerException("preScheduleText may not be null");
        }    // if
        if (postScheduleText == null) {
            throw new NullPointerException("postScheduleText may not be null");
        }    // if
        if (subjectLineTemplate == null) {
            throw new NullPointerException("subjectLineTemplate may not be null");
        }    // if
        if (dateFormatString == null) {
            throw new NullPointerException("dateFormatString may not be null");
        }    // if
        try {
            DateFormat dummy = new SimpleDateFormat(dateFormatString);
        } catch (Exception e) {    // try
            throw new IllegalArgumentException("dateFormatString must be in appropriate format for SimpleDateFormat", e);
        }    // catch

        this.preScheduleText = preScheduleText;
        this.postScheduleText = postScheduleText;
        this.subjectLineTemplate = subjectLineTemplate;
        this.dateFormatString = dateFormatString;
        assertInvariant();
    }    // Shift()

    /**
     * Returns the email template's pre-schedule text.
     *
     * @return the email template's pre-schedule text
     */
    public String getPreScheduleText() {
        assertInvariant();
        return preScheduleText;
    }    // getDescription()

    /**
     * Returns the email template's post-schedule text.
     *
     * @return the email template's post-schedule text
     */
    public String getPostScheduleText() {
        assertInvariant();
        return postScheduleText;
    }    // getDescription()

    /**
     * Returns the email template's subject line template.
     *
     * @return the email template's subject line template
     * @since 3.3
     */
    public String getSubjectLineTemplate() {
        assertInvariant();
        return subjectLineTemplate;
    }    // getSubjectLineTemplate()

    /**
     * Returns the email template's date format string.
     *
     * @return the email template's date format string
     * @see #getDateFormatter()
     * @since 3.3
     */
    public String getDateFormatString() {
        assertInvariant();
        return dateFormatString;
    }    // getDateFormatString()

    /**
     * Returns a {@link DateFormat} that formats a {@link java.util.Date} object
     * into the format particular to the email template.
     *
     * @return an appropriate DateFormat
     * @see #getDateFormatString()
     * @since 3.3
     */
    public DateFormat getDateFormatter() {
        assertInvariant();
        return new SimpleDateFormat(dateFormatString);
    }    // getDateFormatter()

    /**
     * Returns a map containing the read-writable properties of the email
     * template. The map returned by this method is guaranteed to have the
     * following properties.
     * <ul>
     *   <li>The map has exactly four keys: "preScheduleText",
     * "postScheduleText", and "subjectLineTemplate", and
     * "dateFormatString".</li>
     *   <li>Each value is a non-null {@link String} corresponding to the return
     * value of the appropriate getter method.</li>
     *   <li>The iteration order of the elements is fixed in the order the keys
     * are presented above.</li>
     * </ul>
     *
     * @return a map containing the read-writable properties of the email
     * template
     */
    @Override
    public Map<String, Object> getReadWritableProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("preScheduleText", preScheduleText);
        properties.put("postScheduleText", postScheduleText);
        properties.put("subjectLineTemplate", subjectLineTemplate);
        properties.put("dateFormatString", dateFormatString);
        return properties;
    }    // getReadWritableProperties()

    /**
     * Returns a factory that creates email templates from read-writable
     * property maps.
     *
     * @return a factory that creates email templates from read-writable
     * property maps
     */
    @Override
    public EmailTemplate.Factory getReadWritableFactory() {
        return EmailTemplate.getEmailTemplateFactory();
    }    // getReadWritableFactory()

    /**
     * Indicates whether some other object is "equal to" this one.  An object is
     * equal to this email template only if all the following conditions hold:
     * <ol>
     *   <li>the object is another email template,</li>
     *   <li>both email templates have the same pre-schedule text,</li>
     *   <li>both email templates have the same pre-schedule text,</li>
     *   <li>both email templates have the same subject line template, and</li>
     *   <li>both email templates have the same date format string.</li>
     * </ol>
     *
     * @param obj the object with which to compare
     * @return true if the objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }    // if
        if (!(obj instanceof EmailTemplate)) {
            return false;
        }    // if

        EmailTemplate rhs = (EmailTemplate)obj;
        return preScheduleText.equals(rhs.preScheduleText)
                && postScheduleText.equals(rhs.postScheduleText)
                && subjectLineTemplate.equals(rhs.subjectLineTemplate)
                && dateFormatString.equals(rhs.dateFormatString);
    }    // equals()

    @Override
    public int hashCode() {
        final int SEED = 109;
        final int MULTIPLIER = 19;
        int code = SEED;
        code = code * MULTIPLIER + preScheduleText.hashCode();
        code = code * MULTIPLIER + postScheduleText.hashCode();
        code = code * MULTIPLIER + subjectLineTemplate.hashCode();
        code = code * MULTIPLIER + dateFormatString.hashCode();
        return code;
    }    // hashCode()

    /**
     * Creates and returns a copy of this email template.
     *
     * @return a copy of this email template
     */
    @Override
    public EmailTemplate clone() {
        EmailTemplate clone = null;
        try {
            clone = (EmailTemplate)super.clone();
        } catch (CloneNotSupportedException e) {    // try
            // We should never get here.  Manager's only superclass is Object,
            // which shouldn't throw.
            assert (false);
        }    // catch
        return clone;
    }    // clone()

    /**
     * Asserts the correctness of the internal state of the object.
     */
    private void assertInvariant() {
        assert (preScheduleText != null);
        assert (postScheduleText != null);
        assert (subjectLineTemplate != null);
        assert (dateFormatString != null);
    }    // assertInvariant()
}    // EmailTemplate
