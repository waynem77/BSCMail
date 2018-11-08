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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Facade for easily creating specific types of {@link Token} objects.
 *
 * @author Wayne Miller
 * @since 3.3
 */
class TokenMaker {

    /**
     * Creates a decomposable token with the given string representation.
     *
     * @param stringValue the string representation of the token; may not be
     * null
     * @return a decomposable token with a string representation equal to
     * {@code stringValue}
     * @throws NullPointerException if {@code stringValue} is null
     */
    public static Token makeDecomposableToken(String stringValue) {
        return new Token(stringValue, true);
    }    // makeAtom()

    /**
     * Creates an atomic token with the given string representation.
     *
     * @param stringValue the string representation of the token; may not be
     * null
     * @return an atomic token with a string representation equal to
     * {@code stringValue}
     * @throws NullPointerException if {@code stringValue} is null
     */
    public static Token makeStringAtom(String stringValue) {
        return new Token(stringValue, false);
    }    // makeAtom()

    /**
     * Creates an atomic token whose string representation is based on the given
     * date. The string value of the token will be in the format described by
     * the given argument.
     *
     * @param date the date from which the string representation is obtained;
     * may not be null
     * @param dateFormatString the date format to use in the email; may not be
     * null; must be in a format appropriate for a
     * {@link java.text.SimpleDateFormat}
     * @return an atomic token whose string representation is based on date
     * @throws NullPointerException if either argument is null
     * @throws IllegalArgumentException if dateFormatString is not in an
     * appropriate format
     */
    public static Token makeDateAtom(Date date, String dateFormatString) {
        if (date == null)  {
            throw new NullPointerException("date may not be null");
        }    // if
        if (dateFormatString == null) {
            throw new NullPointerException("dateFormatString may not be null");
        }    // if

        DateFormat format = new SimpleDateFormat(dateFormatString);
        return new Token(format.format(date), false);
    }    // makeAtom()

}    // TokenMaker
