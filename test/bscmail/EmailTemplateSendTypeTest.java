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

package bscmail;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EmailTemplate.SendType}
 *
 * @author Wayne Miller
 */
public class EmailTemplateSendTypeTest {

    /* getRwRepresentation */

    /**
     * Tests that {@link EmailTemplate.SendType#getRwRepresentation()} does not
     * throw an exception.
     */
    @Test
    public void getRwRepresentationDoesNotThrowException() {
        for (EmailTemplate.SendType sendType : EmailTemplate.SendType.values()) {
            sendType.getRwRepresentation();
        }    // for
    }    // getRwRepresentationDoesNotThrowException()

    /**
     * Tests that {@link EmailTemplate.SendType#getRwRepresentation()} does not
     * return null
     */
    @Test
    public void getRwRepresentationDoesNotReturnNull() {
        for (EmailTemplate.SendType sendType : EmailTemplate.SendType.values()) {
            String received = sendType.getRwRepresentation();

            assertNotNull(received);
        }    // for
    }    // getRwRepresentationDoesNotThrowException()

    /**
     * Tests that the value returned by
     * {@link EmailTemplate.SendType#getRwRepresentation()} is unique.
     */
    @Test
    public void getRwRepresentationReturnsUniqueValue() {
        Set<String> representations = new HashSet<>();
        for (EmailTemplate.SendType sendType : EmailTemplate.SendType.values()) {
            representations.add(sendType.getRwRepresentation());
        }    // for

        int received = representations.size();
        int expected = EmailTemplate.SendType.values().length;
        assertEquals(expected, received);
    }    // getRwRepresentationDoesNotThrowException()

    /* fromRwRepresentation */

    /**
     * Tests that
     * {@link EmailTemplate.SendType#fromRwRepresentation(java.lang.String)}
     * throws a NullPointerException if rwRepresentation is null.
     */
    @Test(expected = NullPointerException.class)
    public void fromRwRepresentationThrowsExceptionIfRwRepresentationIsNull() {
        String rwRepresentation = null;

        EmailTemplate.SendType.fromRwRepresentation(rwRepresentation);
    }    // fromRwRepresentationThrowsExceptionIfRwRepresentationIsNull()

    /**
     * Tests that
     * {@link EmailTemplate.SendType#fromRwRepresentation(java.lang.String)}
     * does not throw an exception when rwRepresentation is valid.
     */
    @Test
    public void fromRwRepresentationDoesNotThrowExceptionWhenRwRepresentationIsValud() {
        for (EmailTemplate.SendType sendType : EmailTemplate.SendType.values()) {
            String rwRepresentation = sendType.getRwRepresentation();

            EmailTemplate.SendType.fromRwRepresentation(rwRepresentation);
        }    // for
    }    // fromRwRepresentationDoesNotThrowExceptionWhenRwRepresentationIsValud()

    /**
     * Tests that
     * {@link EmailTemplate.SendType#fromRwRepresentation(java.lang.String)}
     * returns the correct value when rwRepresentation is valid.
     */
    @Test
    public void fromRwRepresentationReturnsCorrectValueWhenRwRepresentationIsValid() {
        for (EmailTemplate.SendType sendType : EmailTemplate.SendType.values()) {
            String rwRepresentation = sendType.getRwRepresentation();

            EmailTemplate.SendType received = EmailTemplate.SendType.fromRwRepresentation(rwRepresentation);

            EmailTemplate.SendType expected = sendType;
            assertEquals(expected, received);
        }    // for
    }    // fromRwRepresentationReturnsCorrectValueWhenRwRepresentationIsValid()

    /**
     * Tests that
     * {@link EmailTemplate.SendType#fromRwRepresentation(java.lang.String)}
     * throws an InvalidArgumentException when rwRepresentation is invalid.
     */
    @Test(expected = IllegalArgumentException.class)
    public void fromRwRepresentationThrowsExceptionWhenRwRepresentationIsInvalid() {
        String rwRepresentation = "";
        for (boolean rwRepresentationIsValid = true; rwRepresentationIsValid; ) {
            rwRepresentation += "x";
            rwRepresentationIsValid = false;
            for (EmailTemplate.SendType sendType : EmailTemplate.SendType.values()) {
                if (rwRepresentation.equals(sendType.getRwRepresentation())) {
                    rwRepresentationIsValid = true;
                }    // if
            }    // for
        }    // for
        // At the end of this loop, rwRepresentation is not equal to the read-
        // writable representation of any SendType.

        EmailTemplate.SendType.fromRwRepresentation(rwRepresentation);
    }    // fromRwRepresentationThrowsExceptionWhenRwRepresentationIsInvalid()

}    // EmailTemplateSendTypeTest
