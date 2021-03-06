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

package io.github.waynem77.bscmail.help;

/**
 * Help display factory used in tests.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class TestHelpDisplayFactory implements HelpDisplayFactory {
        
    /**
     * Creates a {@link HelpDisplay} implementation of the specified class using
     * the given arguments. The arguments depend on the type of HelpDisplay
     * being created.
     * 
     * <br>
     * 
     * <table style="border: 1px solid black">
     * <caption>{@code arguments} Specifications for HelpDisplay Implementations</caption>
     * <tr>
     * <th>TestHelpDisplay</th>
     * <td>three elements, which are ignored</td>
     * </tr>
     * </table>
     *
     * @param helpDisplayClass the HelpDisplay implementation class; may not be
     * null
     * @param arguments the HelpDisplay constructor arguments
     * @return a HelpDisplay
     * @throws NullPointerException if helpDisplayClass is null
     * @throws IllegalArgumentException if arguments is invalid for the type of
     * HelpDisplay being created, or if a factory has not been implemented for
     * helpDisplayClass
     */
    @Override
    public HelpDisplay createHelpDisplay(Class<? extends HelpDisplay> helpDisplayClass, Object[] arguments) {
        if (helpDisplayClass == null) {
            throw new NullPointerException("helpDisplayClass may not be null");
        }  // if
        if ((arguments == null) || (arguments.length != 3)) {
            throw new IllegalArgumentException("arguments must be non-null and contain three items");
        }

        return new TestHelpDisplay();
    }    // createHelpDisplay()

}
