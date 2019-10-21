/*
 * Copyright © 2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.util.parser;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link CsvStringParser}.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class CsvStringParserTest {

    /**
     * Tests that {@link CsvStringParser#parse(String)} throws a
     * NullPointerException when string is null.
     */
    @Test(expected = NullPointerException.class)
    public void parseThrowsExceptionWhenStringIsNull() {
        CsvStringParser parser = new CsvStringParser();
        String string = null;

        parser.parse(string);
    }    // parseThrowsExceptionWhenStringIsNull()

    /**
     * Tests that {@link CsvStringParser#parse(String)} does not throw an
     * exception when string is not-null and valid.
     */
    @Test
    public void parseDoesNotThrowExceptionWhenStringIsNotNullAndValid() {
        CsvStringParser parser = new CsvStringParser();
        String string = "aaa,\"bb,b\",\"cc\"\"c\"";

        parser.parse(string);
    }    // parseDoesNotThrowExceptionWhenStringIsNotNullAndValid()

    /**
     * Tests that {@link CsvStringParser#parse(String)} returns the correct
     * value.
     */
    @Test
    public void parseReturnsCorrectValue() {
        CsvStringParser parser = new CsvStringParser();
        String string = "aaa,\"bb,b\",\"cc\"\"c\"";

        Object[] received = parser.parse(string);

        Object[] expected = { "aaa", "bb,b", "cc\"c" };
        assertArrayEquals(expected, received);
    }    // parseReturnsCorrectValue()

}
