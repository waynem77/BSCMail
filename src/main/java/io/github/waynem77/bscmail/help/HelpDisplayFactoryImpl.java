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
 * Default factory for {@link HelpDisplay} objects.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 4.0
 */
public class HelpDisplayFactoryImpl implements HelpDisplayFactory {

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
     * <th>HelpFileDisplay</th>
     * <td>one element, a string containing the pathname of the help file</td>
     * </tr>
     * <tr>
     * <th>HelpFileFromResourceDisplay</th>
     * <td>one element, a string containing the resource pathname of the help file</td>
     * </tr>
     * </table>
     * 
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
 
        if (helpDisplayClass.equals(HelpFileDisplay.class)) {
            return createHelpFileDisplay(arguments);
        }    // if
        if (helpDisplayClass.equals(HelpFileFromResourceDisplay.class)) {
            return createHelpFileFromResourceDisplay(arguments);
        }    // if
        throw new IllegalArgumentException("Method not implemented for " + helpDisplayClass);
    }    // createHelpDisplay()

    
    /**
     * Creates a {@link HelpFileDisplay} using the given arguments.
     *
     * @param arguments the arguments; may not be null; must contain exactly one
     * argument, a string containing the pathname of the help file
     * @return a HelpFileDisplay
     * @throws IllegalArgumentException if arguments does not meet the criteria
     * above
     */
    private HelpFileDisplay createHelpFileDisplay(Object[] arguments) {
        assert (arguments != null);
        if (arguments.length != 1) {
            throw new IllegalArgumentException("arguments must have length 1");
        }    // if
        
        String pathname = arguments[0].toString();
        return new HelpFileDisplay(pathname);
    }    // createHelpFileDisplay()
    
    /**
     * Creates a {@link HelpFileFromResourceDisplay} using the given arguments.
     *
     * @param arguments the arguments; may not be null; must contain exactly one
     * argument, a string containing the pathname of the help file
     * @return a HelpFileFromResourceDisplay
     * @throws IllegalArgumentException if arguments does not meet the criteria
     * above
     */
    private HelpFileFromResourceDisplay createHelpFileFromResourceDisplay(Object[] arguments) {
        assert (arguments != null);
        if (arguments.length != 1) {
            throw new IllegalArgumentException("arguments must have length 1");
        }    // if
        
        String pathname = arguments[0].toString();
        return new HelpFileFromResourceDisplay(pathname);
    }    // createHelpFileFromResourceDisplay()

}    // HelpDisplayFactoryImpl
