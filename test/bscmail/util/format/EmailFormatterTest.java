/*
 * Copyright © 2018 its authors.  See the file "AUTHORS" for details.
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

package bscmail.util.format;

import bscmail.Event;
import java.util.Calendar;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EmailFormatter}.
 *
 * @author Wayne Miller
 */
public class EmailFormatterTest {

    /* object properties */

    /**
     * The date format string used in testing.
     */
    private String dateFormatString;

    /**
     * The email formatter being tested.
     */
//    private EmailFormatter emailFormatter;

    /**
     * The format string used in testing.
     */
    private String format;

    /**
     * The event used in testing.
     */
    private Event event;

    /* helper methods */

    /**
     * Returns an event suitable for use in unit tests.
     *
     * @return a test event
     */
    private Event getTestEvent() {
        Event event = new Event();
        event.setDate(new Date());
        return event;
    }    // getTestEvent()

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void preTestSetup() {
        dateFormatString = "yyyy-MM-dd";
        format = "foo";
        event = getTestEvent();
    }    // preTestSetup()

    /**
     * Tears down the test environment after each test.
     */
    @After
    public void postTestTeardown() {
        dateFormatString = null;
        format = null;
        event = null;
    }    // postTestTeardown()

    /* unit tests: constructor */

    /**
     * Tests that {@link EmailFormatter#EmailFormatter(java.lang.String)} throws
     * a NullPointerException when dateFormatString is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenDateFormatStringIsNull() {
        dateFormatString = null;
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
    }    // constructorThrowsExceptionWhenDateFormatStringIsNull()

    /**
     * Tests that {@link EmailFormatter#EmailFormatter(java.lang.String)} throws
     * an IllegalArgumentException when dateFormatString is not in suitable
     * format.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWhenDateFormatStringIsUnsuitable() {
        dateFormatString = "foo";
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
    }    // constructorThrowsExceptionWhenDateFormatStringIsUnsuitable()

    /**
     * Tests that {@link EmailFormatter#EmailFormatter(java.lang.String)} does
     * not throw an exception when dateFormatString is not null and in proper
     * format.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenDateFormatStringIsSuitable() {
        dateFormatString = "yyyy";
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
    }    // constructorDoesNotThrowExceptionWhenDateFormatStringIsSuitable()

    /* unit tests: formatString */

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}
     * throws a {@link NullPointerException} when format is null.
     */
    @Test(expected = NullPointerException.class)
    public void formatStringThrowsExceptionWhenFormatIsNull() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = null;

        emailFormatter.formatString(format, event);
    }    // formatStringThrowsExceptionWhenFormatIsNull()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)} does
     * not throw an exception when format is empty.
     */
    @Test
    public void formatStringDoesNotThrowExceptionWhenFormatIsEmpty() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = "";

        emailFormatter.formatString(format, event);
    }    // formatStringDoesNotThrowExceptionWhenFormatIsEmpty()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}
     * throws a {@link NullPointerException} when event is null.
     */
    @Test(expected = NullPointerException.class)
    public void formatStringThrowsExceptionWhenEventIsNull() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        event = null;

        emailFormatter.formatString(format, event);
    }    // formatStringThrowsExceptionWhenEventIsNull()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)} does
     * not throw an exception when neither argument is null.
     */
    @Test
    public void formatStringDoesNotThrowExceptionWhenNoArgumentIsNull() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);

        emailFormatter.formatString(format, event);
    }    // formatStringDoesNotThrowExceptionWhenNoArgumentIsNull()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)} does
     * not return null.
     */
    @Test
    public void formatStringDoesNotReturnNull() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        String received = emailFormatter.formatString(format, event);

        assertNotNull(received);
    }    // formatStringDoesNotReturnNull()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}
     * returns the correct result for an input with no formatting marks.
     */
    @Test
    public void formatStringReturnsCorrectResultForInputWithNoFormattingMarks() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = "foo";

        String received = emailFormatter.formatString(format, event);

        String expected = "foo";
        assertEquals(expected, received);
    }    // formatStringReturnsCorrectResultForInputWithNoFormattingMarks()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}
     * returns the correct result for an input with one bare left brace.
     */
    @Test
    public void formatStringReturnsCorrectResultForInputWithOneBareLeftBrace() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = "foo{bar";

        String received = emailFormatter.formatString(format, event);

        String expected = "foobar";
        assertEquals(expected, received);
    }    // formatStringReturnsCorrectResultForInputWithOneBareLeftBrace()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}
     * returns the correct result for an input with one bare right brace.
     */
    @Test
    public void formatStringReturnsCorrectResultForInputWithOneBareRightBrace() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = "foo}bar";

        String received = emailFormatter.formatString(format, event);

        String expected = "foobar";
        assertEquals(expected, received);
    }    // formatStringReturnsCorrectResultForInputWithOneBareRightBrace()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}
     * returns the correct result for an input with an escaped left brace.
     */
    @Test
    public void formatStringReturnsCorrectResultForInputWithEscapedLeftBrace() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = "foo{{bar";

        String received = emailFormatter.formatString(format, event);

        String expected = "foo{bar";
        assertEquals(expected, received);
    }    // formatStringReturnsCorrectResultForInputWithEscapedLeftBrace()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}
     * returns the correct result for an input with an escaped right brace.
     */
    @Test
    public void formatStringReturnsCorrectResultForInputWithEscapedRightBrace() {
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = "foo}}bar";

        String received = emailFormatter.formatString(format, event);

        String expected = "foo}bar";
        assertEquals(expected, received);
    }    // formatStringReturnsCorrectResultForInputWithEscapedRightBrace()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}
     * returns the correct result for an input with the "{date}" command.
     */
    @Test
    public void formatStringReturnsCorrectResultForInputWithDateCommand() {
        dateFormatString = "yyyy-MM-dd";
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = "foo{date}bar";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 1, 5);    // Monday, February 5, 2018
        event.setDate(calendar.getTime());

        String received = emailFormatter.formatString(format, event);

        String expected = "foo2018-02-05bar";
        assertEquals(expected, received);
    }    // formatStringReturnsCorrectResultForInputWithDateCommand()

    /**
     * Tests that
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}
     * returns the correct result for an input with the "{date}" command when
     * the event date is unset.
     */
    @Test
    public void formatStringReturnsCorrectResultForInputWithDateCommandWhenEventDateIsUnset() {
        dateFormatString = "yyyy-MM-dd";
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = "foo{date}bar";
        event.setDate(null);

        String received = emailFormatter.formatString(format, event);

        String expected = "foobar";
        assertEquals(expected, received);
    }    // formatStringReturnsCorrectResultForInputWithDateCommandWhenEventDateIsUnset()

    /**
     * Stress test for
     * {@link EmailFormatter#formatString(java.lang.String, bscmail.Event)}.
     */
    @Test
    public void formatStringStressTest() {
        dateFormatString = "yyyy-MM-dd";
        EmailFormatter emailFormatter = new EmailFormatter(dateFormatString);
        format = "{a{{b{{{c{{{{{{{d}e}}f}}}g}}}}}}}h{date}{date}{{date}}";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 1, 5);    // Monday, February 5, 2018
        event.setDate(calendar.getTime());

        String received = emailFormatter.formatString(format, event);

        String expected = "a{b{c{{{de}f}g}}}h2018-02-052018-02-05{date}";
        assertEquals(expected, received);
    }    // formatStringStressTest()
}    // EmailFormatterTest
