/*
 * Copyright © 2018-2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.util.format;

import java.util.Calendar;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link TokenMaker}.
 *
 * @author Wayne Miller
 */
public class TokenMakerTest {

    /* makeDecomposableToken */

    /**
     * Tests that {@link TokenMaker#makeDecomposableToken(java.lang.String)}
     * throws a NullPointerException when stringValue is null.
     */
    @Test(expected = NullPointerException.class)
    public void makeDecomposableTokenThrowsExceptionWhenStringValueIsNull() {
        String stringValue = null;

        TokenMaker.makeDecomposableToken(stringValue);
    }    // makeDecomposableTokenThrowsExceptionWhenStringValueIsNull()

    /**
     * Tests that {@link TokenMaker#makeDecomposableToken(java.lang.String)}
     * does not throw an exception when stringValue is empty.
     */
    @Test
    public void makeDecomposableTokenDoesNotThrowExceptionWhenStringValueIsEmpty() {
        String stringValue = "";

        TokenMaker.makeDecomposableToken(stringValue);
    }    // makeDecomposableTokenDoesNotThrowExceptionWhenStringValueIsEmpty()

    /**
     * Tests that {@link TokenMaker#makeDecomposableToken(java.lang.String)}
     * does not throw an exception when stringValue is not empty.
     */
    @Test
    public void makeDecomposableTokenDoesNotThrowExceptionWhenStringValueIsNotEmpty() {
        String stringValue = "bar";

        TokenMaker.makeDecomposableToken(stringValue);
    }    // makeDecomposableTokenDoesNotThrowExceptionWhenStringValueIsNotEmpty()

    /**
     * Tests that {@link TokenMaker#makeDecomposableToken(java.lang.String)}
     * does not return null.
     */
    @Test
    public void makeDecomposableTokenDoesNotReturnNull() {
        String stringValue = "bar";

        Token received = TokenMaker.makeDecomposableToken(stringValue);

        assertNotNull(received);
    }    // makeDecomposableTokenDoesNotReturnNull()

    /**
     * Tests that the token returned by
     * {@link TokenMaker#makeDecomposableToken(java.lang.String)} has the
     * correct string value.
     */
    @Test
    public void makeDecomposableTokenReturnsTokenWithCorrectStringValue() {
        String stringValue = "bar";
        Token token = TokenMaker.makeDecomposableToken(stringValue);

        String received = token.getStringValue();

        String expected = stringValue;
        assertEquals(expected, received);
    }    // makeDecomposableTokenReturnsTokenWithCorrectStringValue()

    /**
     * Tests that the token returned by
     * {@link TokenMaker#makeDecomposableToken(java.lang.String)} is
     * decomposable.
     */
    @Test
    public void makeDecomposableTokenReturnsDecomposableToken() {
        String stringValue = "bar";
        Token token = TokenMaker.makeDecomposableToken(stringValue);

        boolean received = token.isDecomposable();

        boolean expected = true;
        assertEquals(expected, received);
    }    // makeDecomposableTokenReturnsDecomposableToken()

    /* makeStringAtom */

    /**
     * Tests that {@link TokenMaker#makeStringAtom(java.lang.String)} throws a
     * NullPointerException when stringValue is null.
     */
    @Test(expected = NullPointerException.class)
    public void makeStringAtomThrowsExceptionWhenStringValueIsNull() {
        String stringValue = null;

        TokenMaker.makeStringAtom(stringValue);
    }    // makeStringAtomThrowsExceptionWhenStringValueIsNull()

    /**
     * Tests that {@link TokenMaker#makeStringAtom(java.lang.String)} does not
     * throw an exception when stringValue is empty.
     */
    @Test
    public void makeStringAtomDoesNotThrowExceptionWhenStringValueIsEmpty() {
        String stringValue = "";

        TokenMaker.makeStringAtom(stringValue);
    }    // makeStringAtomDoesNotThrowExceptionWhenStringValueIsEmpty()

    /**
     * Tests that {@link TokenMaker#makeStringAtom(java.lang.String)} does not
     * throw an exception when stringValue is not empty.
     */
    @Test
    public void makeStringAtomDoesNotThrowExceptionWhenStringValueIsNotEmpty() {
        String stringValue = "bar";

        TokenMaker.makeStringAtom(stringValue);
    }    // makeStringAtomDoesNotThrowExceptionWhenStringValueIsNotEmpty()

    /**
     * Tests that {@link TokenMaker#makeStringAtom(java.lang.String)} does not
     * return null.
     */
    @Test
    public void makeStringAtomDoesNotReturnNull() {
        String stringValue = "bar";

        Token received = TokenMaker.makeStringAtom(stringValue);

        assertNotNull(received);
    }    // makeStringAtomDoesNotReturnNull()

    /**
     * Tests that the token returned by
     * {@link TokenMaker#makeStringAtom(java.lang.String)} has the correct
     * string value.
     */
    @Test
    public void makeStringAtomReturnsTokenWithCorrectStringValue() {
        String stringValue = "bar";
        Token token = TokenMaker.makeStringAtom(stringValue);

        String received = token.getStringValue();

        String expected = stringValue;
        assertEquals(expected, received);
    }    // makeStringAtomReturnsTokenWithCorrectStringValue()

    /**
     * Tests that the token returned by
     * {@link TokenMaker#makeStringAtom(java.lang.String)} is atomic.
     */
    @Test
    public void makeStringAtomReturnsAtomicToken() {
        String stringValue = "bar";
        Token token = TokenMaker.makeStringAtom(stringValue);

        boolean received = token.isDecomposable();

        boolean expected = false;
        assertEquals(expected, received);
    }    // makeStringAtomReturnsAtomicToken()

    /* makeDateAtom */

    /**
     * Tests that {@link TokenMaker#makeDateAtom(java.util.Date)} throws a
     * NullPointerException when date is null.
     */
    @Test(expected = NullPointerException.class)
    public void makeDateAtomThrowsExceptionWhenDateIsNull() {
        Date date = null;
        String dateFormatString = "EEEE MMMM d";

        TokenMaker.makeDateAtom(date, dateFormatString);
    }    // makeDateAtomThrowsExceptionWhenDateIsNull()

    /**
     * Tests that {@link TokenMaker#makeDateAtom(java.util.Date)} throws a
     * NullPointerException when dateFormatString is null.
     */
    @Test(expected = NullPointerException.class)
    public void makeDateAtomThrowsExceptionWhenDateFormatStringIsNull() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 4, 26);    // Saturday, May 26, 2018
        Date date = calendar.getTime();
        String dateFormatString = null;

        TokenMaker.makeDateAtom(date, dateFormatString);
    }    // makeDateAtomThrowsExceptionWhenDateFormatStringIsNull()

    /**
     * Tests that {@link TokenMaker#makeDateAtom(java.util.Date)} throws an
     * IllegalArgumentException when dateFormatString is not in an appropriate
     * format.
     */
    @Test(expected = IllegalArgumentException.class)
    public void makeDateAtomThrowsExceptionWhenDateFormatStringIsIncorrect() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 4, 26);    // Saturday, May 26, 2018
        Date date = calendar.getTime();
        String dateFormatString = "foo";

        TokenMaker.makeDateAtom(date, dateFormatString);
    }    // makeDateAtomThrowsExceptionWhenDateFormatStringIsIncorrect()

    /**
     * Tests that {@link TokenMaker#makeDateAtom(java.util.Date)} does not throw
     * an exception when date is not null and dateFormatString is not null and
     * is in an appropriate format.
     */
    @Test
    public void makeDateAtomDoesNotThrowExceptionWhenArgumentsAreOK() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 4, 26);    // Saturday, May 26, 2018
        Date date = calendar.getTime();
        String dateFormatString = "EEEE MMMM d";

        TokenMaker.makeDateAtom(date, dateFormatString);
    }    // makeDateAtomDoesNotThrowExceptionWhenArgumentsAreOK()

    /**
     * Tests that {@link TokenMaker#makeDateAtom(java.util.Date)} does not
     * return null.
     */
    @Test
    public void makeDateAtomDoesNotReturnNull() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 4, 26);    // Saturday, May 26, 2018
        Date date = calendar.getTime();
        String dateFormatString = "EEEE MMMM d";

        Token received = TokenMaker.makeDateAtom(date, dateFormatString);

        assertNotNull(received);
    }    // makeDateAtomDoesNotReturnNull()

    /**
     * Tests that the token returned by
     * {@link TokenMaker#makeDateAtom(java.util.Date)} has the correct
     * string value.
     */
    @Test
    public void makeDateAtomReturnsTokenWithCorrectStringValue() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 4, 26);    // Saturday, May 26, 2018
        Date date = calendar.getTime();
        String dateFormatString = "EEEE-MMMM-d";
        Token token = TokenMaker.makeDateAtom(date, dateFormatString);

        String received = token.getStringValue();

        String expected = "Saturday-May-26";
        assertEquals(expected, received);
    }    // makeDateAtomReturnsTokenWithCorrectStringValue()

    /**
     * Tests that the token returned by
     * {@link TokenMaker#makeDateAtom(java.util.Date)} is atomic.
     */
    @Test
    public void makeDateAtomReturnsAtomicToken() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 4, 26);    // Saturday, May 26, 2018
        Date date = calendar.getTime();
        String dateFormatString = "EEEE MMMM d";
        Token token = TokenMaker.makeDateAtom(date, dateFormatString);

        boolean received = token.isDecomposable();

        boolean expected = false;
        assertEquals(expected, received);
    }    // makeDateAtomReturnsAtomicToken()

    /**
     * Tests that the string value of the token returned by
     * {@link TokenMaker#makeDateAtom(java.util.Date)} is not empty when
     * dateFormat is empty; that is, tests that a default date format exists.
     */
    @Test
    public void makeDateAtomReturnsNonemptyTokenWhenDateFormatIsEmpty() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 4, 26);    // Saturday, May 26, 2018
        Date date = calendar.getTime();
        String dateFormatString = "";
        Token token = TokenMaker.makeDateAtom(date, dateFormatString);

        boolean received = token.getStringValue().isEmpty();

        boolean expected = false;
        assertEquals(expected, received);
    }    // makeDateAtomReturnsNonemptyTokenWhenDateFormatIsEmpty()

}    // TokenMakerTest
