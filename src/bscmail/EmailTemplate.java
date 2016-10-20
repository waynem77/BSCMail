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

/**
 * Represents an email template.  Email templates have the following properties:
 * <ul>
 *   <li>pre-schedule text, and</li>
 *   <li>post-schedule text</li>
 * </ul>
 * 
 * @author wayne.miller
 */
public class EmailTemplate {

    /**
     * The email template's pre-schedule text.
     */
    private String preScheduleText;

    /**
     * The email template's post-schedule text.
     */
    private String postScheduleText;

    /**
     * Constructs a new email template.
     *
     * @param preScheduleText the email template's pre-schedule text; may be
     * empty, but not null
     * @param postScheduleText the email template's post-schedule text; may be
     * empty, but not null
     * @throws NullPointerException if either parameter is null
     */
    public EmailTemplate(String preScheduleText, String postScheduleText) {
        if (preScheduleText == null) {
            throw new NullPointerException("preScheduleText may not be null");
        }    // if
        if (postScheduleText == null) {
            throw new NullPointerException("postScheduleText may not be null");
        }    // if
        this.preScheduleText = preScheduleText;
        this.postScheduleText = postScheduleText;
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
     * Sets the email template's pre-schedule text to the given parameter.
     *
     * @param preScheduleText the email template's pre-schedule text; may be
     * empty, but not null
     * @throws NullPointerException if {@code preScheduleText} is null
     */
    public void setPreScheduleText(String preScheduleText) {
        assertInvariant();
        if (preScheduleText == null) {
            throw new NullPointerException("preScheduleText may not be null");
        }    // if
        this.preScheduleText = preScheduleText;
        assertInvariant();
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
     * Sets the email template's post-schedule text to the given parameter.
     *
     * @param postScheduleText the email template's post-schedule text; may be
     * empty, but not null
     * @throws NullPointerException if {@code postScheduleText} is null
     */
    public void setPostScheduleText(String postScheduleText) {
        assertInvariant();
        if (postScheduleText == null) {
            throw new NullPointerException("postScheduleText may not be null");
        }    // if
        this.postScheduleText = postScheduleText;
        assertInvariant();
    }    // getDescription()

    /**
     * Asserts the correctness of the internal state of the object.
     */
    private void assertInvariant() {
        assert (preScheduleText != null);
        assert (postScheduleText != null);
    }    // assertInvariant()
}    // EmailTemplate
