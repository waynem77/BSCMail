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

/**
 * Represents a piece of a string that is being parsed for transformation. The token is either
 * <em>atomic</em>, which means it should not be parsed further, or <em>decomposable</em>,
 * which means that it may be parsed and broken down further. Additionally, each
 * token has a string value that represents it.
 *
 * @author Wayne Miller
 * @since 3.3
 */
class Token {

    /**
     * The string representation of the token.
     */
    private final String stringValue;

    /**
     * True if the token is decomposable, false if it is atomic.
     */
    private final boolean isDecomposable;

    /**
     * Creates a new token with the given string representation and
     * decomposability.
     *
     * @param stringValue the string representation of the token; may not be
     * null
     * @param isDecomposable true if the token is decomposable; false if the
     * token is atomic
     * @throws NullPointerException if {@code stringValue} is null
     */
    Token(String stringValue, boolean isDecomposable) {
        if (stringValue == null) {
            throw new NullPointerException("stringValue may not be null");
        }    // if

        this.stringValue = stringValue;
        this.isDecomposable = isDecomposable;
        assertInvariant();
    }    // Token

    /**
     * Returns the string value of the token. The string value is guaranteed to
     * not be null.
     *
     * @return the string value of the token
     */
    public String getStringValue() {
        assertInvariant();
        return stringValue;
    }    // getStringValue()

    /**
     * Returns the decomposability of the token; that is, it returns true if the
     * token is decomposable, or false if the token is atomic.
     *
     * @return true if the token is decomposable, false if it is atomic
     */
    public boolean isDecomposable() {
        assertInvariant();
        return isDecomposable;
    }    // isDecomposable()

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }    // if
        if (!(obj instanceof Token)) {
            return false;
        }    // if

        Token rhs = (Token)obj;
        return stringValue.equals(rhs.getStringValue())
                && (isDecomposable == rhs.isDecomposable());
    }    // equals()

    @Override
    public int hashCode() {
        final int SEED = 797;
        final int MULTIPLIER = 109;
        int code = SEED;
        code = code * MULTIPLIER + stringValue.hashCode();
        code = code * MULTIPLIER + booleanHashCode(isDecomposable);
        return code;
    }    // hashCode()

    /**
     * Hash code for primitive boolean
     * @param bool the boolean value
     * @return The boolean's hash code
     */
    private int booleanHashCode(boolean bool) {
        return bool ? 1913 : 1931;
    }

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (stringValue != null);
    }    // assertInvariant()

    @Override public String toString() { return "Token(\"" + stringValue + "\"," + isDecomposable + ")"; }

}    // Token
