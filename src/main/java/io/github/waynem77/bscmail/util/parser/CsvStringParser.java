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

import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Parses comma-delimited data from a string into an array of objects. The data
 * should be compliant with <a href="https://tools.ietf.org/html/rfc4180">RFC
 * 4180</a>.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 4.0
 */
public class CsvStringParser {

    private final CSVFormat csvFormat = CSVFormat.RFC4180;

    /**
     * Parses a string containing comma-delimited data into an array of objects.
     * Each element will be represented in the array as a string.
     *
     * @param string the comma-delimited data; may not be null
     * @return the parsed data
     * @throws NullPointerException if string is null
     * @throws IllegalArgumentException if string cannot be parsed
     */
    public Object[] parse(String string) {
        if (string == null) {
            throw new NullPointerException("string may not be null");
        }    // if

        try (CSVParser parser = CSVParser.parse(string, csvFormat)) {
            List<CSVRecord> records = parser.getRecords();
            if (records.size() != 1) {    // Should be exactly 1 record.
                throw new IllegalArgumentException("Malformed string.");
            }    // if
            CSVRecord record = records.get(0);
            Object[] values = new Object[record.size()];
            for (int i = 0; i < record.size(); ++i) {
                values[i] = record.get(i);
            }    // for
            return values;
        } catch (IOException e) {    // try
            throw new IllegalArgumentException("An I/O error occurred.", e);
        }    // catch
    }    // parse()

}    // CsvStringParser
