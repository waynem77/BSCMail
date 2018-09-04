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

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * Unit tests for {@link Token}.
 *
 * @author Wayne Miller
 */
public class TokenTest {

    /* constructor */

    /**
     * Tests that {@link Token#Token(java.lang.String, boolean)} throws a
     * NullPointerException when stringValue is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenStringValueIsNull() {
        String stringValue = null;
        boolean isDecomposable = true;

        Token token = new Token(stringValue, isDecomposable);
    }    // constructorThrowsExceptionWhenStringValueIsNull()

    /**
     * Tests that {@link Token#Token(java.lang.String, boolean)} does not throw
     * an exception when stringValue is empty.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenStringValueIsEmpty() {
        String stringValue = "";
        boolean isDecomposable = true;

        Token token = new Token(stringValue, isDecomposable);
    }    // constructorDoesNotThrowExceptionWhenStringValueIsEmpty()

    /**
     * Tests that {@link Token#Token(java.lang.String, boolean)} does not throw
     * an exception when isDecomposable is true.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenIsDecomposableIsTrue() {
        String stringValue = "foo";
        boolean isDecomposable = true;

        Token token = new Token(stringValue, isDecomposable);
    }    // constructorDoesNotThrowExceptionWhenIsDecomposableIsTrue()

    /**
     * Tests that {@link Token#Token(java.lang.String, boolean)} does not throw
     * an exception when isDecomposable is false.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenIsDecomposableIsFalse() {
        String stringValue = "foo";
        boolean isDecomposable = false;

        Token token = new Token(stringValue, isDecomposable);
    }    // constructorDoesNotThrowExceptionWhenIsDecomposableIsFalse()

    /* getStringValue */

    /**
     * Tests that {@link Token#getStringValue()} does not throw an exception
     * when the string value is empty.
     */
    @Test
    public void getStringValueDoesNotThrowExceptionWhenStringValueIsEmpty() {
        String stringValue = "";
        boolean isDecomposable = true;
        Token token = new Token(stringValue, isDecomposable);

        token.getStringValue();
    }    // getStringValueDoesNotThrowExceptionWhenStringValueIsEmpty()

    /**
     * Tests that {@link Token#getStringValue()} does not return null when the
     * string value is empty.
     */
    @Test
    public void getStringValueDoesNotReturnNullWhenStringValueIsEmpty() {
        String stringValue = "";
        boolean isDecomposable = true;
        Token token = new Token(stringValue, isDecomposable);

        String received = token.getStringValue();

        assertNotNull(received);
    }    // getStringValueDoesNotReturnNullWhenStringValueIsEmpty()

    /**
     * Tests that {@link Token#getStringValue()} returns the correct value when
     * the string value is empty.
     */
    @Test
    public void getStringValueReturnsCorrectValueWhenStringValueIsEmpty() {
        String stringValue = "";
        boolean isDecomposable = true;
        Token token = new Token(stringValue, isDecomposable);

        String received = token.getStringValue();

        String expected = stringValue;
        assertEquals(expected, received);
    }    // getStringValueReturnsCorrectValueWhenStringValueIsEmpty()

    /**
     * Tests that {@link Token#getStringValue()} does not throw an exception
     * when the token is decomposable.
     */
    @Test
    public void getStringValueDoesNotThrowExceptionWhenTokenIsDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = true;
        Token token = new Token(stringValue, isDecomposable);

        token.getStringValue();
    }    // getStringValueDoesNotThrowExceptionWhenTokenIsDecomposable()

    /**
     * Tests that {@link Token#getStringValue()} does not return null when the
     * token is decomposable.
     */
    @Test
    public void getStringValueDoesNotReturnNullWhenIsDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = true;
        Token token = new Token(stringValue, isDecomposable);

        String received = token.getStringValue();

        assertNotNull(received);
    }    // getStringValueDoesNotReturnNullWhenIsDecomposable()

    /**
     * Tests that {@link Token#getStringValue()} returns the correct value when
     * the token is decomposable.
     */
    @Test
    public void getStringValueReturnsCorrectValueWhenIsDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = true;
        Token token = new Token(stringValue, isDecomposable);

        String received = token.getStringValue();

        String expected = stringValue;
        assertEquals(expected, received);
    }    // getStringValueReturnsCorrectValueWhenIsDecomposable()

    /**
     * Tests that {@link Token#getStringValue()} does not throw an exception
     * when the token is not decomposable.
     */
    @Test
    public void getStringValueDoesNotThrowExceptionWhenTokenIsNotDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);

        token.getStringValue();
    }    // getStringValueDoesNotThrowExceptionWhenTokenIsNotDecomposable()

    /**
     * Tests that {@link Token#getStringValue()} does not return null when the
     * token is not decomposable.
     */
    @Test
    public void getStringValueDoesNotReturnNullWhenIsNotDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);

        String received = token.getStringValue();

        assertNotNull(received);
    }    // getStringValueDoesNotReturnNullWhenIsNotDecomposable()

    /**
     * Tests that {@link Token#getStringValue()} returns the correct value when
     * the token is not decomposable.
     */
    @Test
    public void getStringValueReturnsCorrectValueWhenIsNotDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);

        String received = token.getStringValue();

        String expected = stringValue;
        assertEquals(expected, received);
    }    // getStringValueReturnsCorrectValueWhenIsNotDecomposable()

    /* isDecomposable */

    /**
     * Tests that {@link Token#isDecomposable()} does not throw an exception
     * when the token is decomposable.
     */
    @Test
    public void isDecomposableDoesNotThrowExceptionWhenTokenIsDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = true;
        Token token = new Token(stringValue, isDecomposable);

        token.isDecomposable();
    }    // isDecomposableDoesNotThrowExceptionWhenTokenIsDecomposable()

    /**
     * Tests that {@link Token#isDecomposable()} returns the correct value when
     * the token is decomposable.
     */
    @Test
    public void isDecomposableReturnsCorrectValueWhenTokenIsDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = true;
        Token token = new Token(stringValue, isDecomposable);

        boolean received = token.isDecomposable();

        boolean expected = isDecomposable;
        assertEquals(expected, received);
    }    // isDecomposableReturnsCorrectValueWhenTokenIsDecomposable()

    /**
     * Tests that {@link Token#isDecomposable()} does not throw an exception
     * when the token is not decomposable.
     */
    @Test
    public void isDecomposableDoesNotThrowExceptionWhenTokenIsNotDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);

        token.isDecomposable();
    }    // isDecomposableDoesNotThrowExceptionWhenTokenIsNotDecomposable()

    /**
     * Tests that {@link Token#isDecomposable()} returns the correct value when
     * the token is not decomposable.
     */
    @Test
    public void isDecomposableReturnsCorrectValueWhenTokenIsNotDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);

        boolean received = token.isDecomposable();

        boolean expected = isDecomposable;
        assertEquals(expected, received);
    }    // isDecomposableReturnsCorrectValueWhenTokenIsNotDecomposable()

    /* equals */

    /**
     * Tests that {@link Token#equals(Object)} does not throw an exception
     * when the argument is null.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNull() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Object obj = null;

        token.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNull()

    /**
     * Tests that {@link Token#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNull() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Object obj = null;

        boolean received = token.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentIsNull()

    /**
     * Tests that {@link Token#equals(Object)} does not throw an exception
     * when the argument is not a token.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsNotAToken() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Object obj = 1;

        token.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsNotAToken()

    /**
     * Tests that {@link Token#equals(Object)} returns false when the
     * argument is not a token.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentIsNotAToken() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Object obj = 1;

        boolean received = token.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentIsNotAToken()

    /**
     * Tests that {@link Token#equals(Object)} does not throw an exception
     * when the argument is a token.
     */
    @Test
    public void equalsDoesNotThrowExceptionWhenArgumentIsAToken() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Object obj = new Token(stringValue, isDecomposable);

        token.equals(obj);
    }    // equalsDoesNotThrowExceptionWhenArgumentIsAToken()

    /**
     * Tests that {@link Token#equals(Object)} returns false when the
     * argument has a different stringValue than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentStringValue() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Object obj = new Token(stringValue + "X", isDecomposable);

        boolean received = token.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentStringValue()

    /**
     * Tests that {@link Token#equals(Object)} returns false when the
     * argument has a different isDecomposable value than the caller.
     */
    @Test
    public void equalsReturnsFalseWhenArgumentHasDifferentIsDecomposable() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Object obj = new Token(stringValue, !isDecomposable);

        boolean received = token.equals(obj);

        boolean expected = false;
        assertEquals(expected, received);
    }    // equalsReturnsFalseWhenArgumentHasDifferentIsDecomposable()

    /**
     * Tests that {@link Token#equals(Object)} returns true when the
     * argument has the same stringValue and isDecomposable value.
     */
    @Test
    public void equalsReturnsWhenArgumentHasSameValuesAsCaller() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Object obj = new Token(stringValue, isDecomposable);

        boolean received = token.equals(obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsWhenArgumentHasSameValuesAsCaller()

    /**
     * Tests that {@link Token#equals(Object)} returns true when the argument is
     * identical to the caller.
     */
    @Test
    public void equalsReturnsTrueWhenArgumentIsIdentical() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Object obj = token;

        boolean received = token.equals(obj);

        boolean expected = true;
        assertEquals(expected, received);
    }    // equalsReturnsTrueWhenArgumentIsIdentical()

    /* hashCode */

    /**
     * Tests that {@link Token#hashCode()} does not throw an exception.
     */
    @Test
    public void hashCodeDoesNotThrowException() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);

        token.hashCode();
    }    // hashCodeDoesNotThrowException()

    /**
     * Tests that {@link Token#hashCode()} returns equal values for equal
     * shifts without volunteers.
     */
    @Test
    public void hashCodeReturnsEqualValuesForEqualTokens() {
        String stringValue = "foo";
        boolean isDecomposable = false;
        Token token = new Token(stringValue, isDecomposable);
        Token experimental = new Token(stringValue, isDecomposable);

        int first = token.hashCode();
        int second = experimental.hashCode();

        assertEquals(first, second);
    }    // hashCodeReturnsEqualValuesForEqualTokens()

}    // TokenTest
