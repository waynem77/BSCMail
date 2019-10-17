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

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class HelpDisplayFactoryImplTest {

    /**
     * Tests that {@link HelpDisplayFactoryImpl#createHelpDisplay(Class, Object[])}
     * throws a NullPointerException when helpDisplayClass is null.
     */
    @Test(expected = NullPointerException.class)
    public void createHelpDisplayThrowsExceptionWhenHelpDisplayClassIsNull() {
        HelpDisplayFactoryImpl helpDisplayFactory = new HelpDisplayFactoryImpl();
        Class<? extends HelpDisplay> helpDisplayClass = null;
        Object[] arguments = new Object[0];

        helpDisplayFactory.createHelpDisplay(helpDisplayClass, arguments);
    }    // createHelpDisplayThrowsExceptionWhenHelpDisplayClassIsNull

    /**
     * Tests that {@link HelpDisplayFactoryImpl#createHelpDisplay(Class, Object[])}
     * throws a NullPointerException when helpDisplayClass is unknown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createHelpDisplayThrowsExceptionWhenHelpLayerClassIsUnknown() {
        class FactoryNotImplemented implements HelpDisplay {
            @Override public void displayHelp() { }
        }    // FactoryNotImplemented
        HelpDisplayFactoryImpl helpDisplayFactory = new HelpDisplayFactoryImpl();
        Class<? extends HelpDisplay> helpDisplayClass = FactoryNotImplemented.class;
        Object[] arguments = new Object[0];

        helpDisplayFactory.createHelpDisplay(helpDisplayClass, arguments);
    }    // createHelpDisplayThrowsExceptionWhenHelpLayerClassIsUnknown

    /* tests with HelpFileDisplay */

    /**
     * Tests that {@link HelpDisplayFactoryImpl#createHelpDisplay(Class, Object[])}
     * works with {@link HelpFileDisplay}.
     */
    @Test
    public void createHelpDisplayWorksWithHelpFileDisplay() {
        HelpDisplayFactoryImpl helpDisplayFactory = new HelpDisplayFactoryImpl();
        Class<? extends HelpDisplay> helpDisplayClass = HelpFileDisplay.class;
        Object[] arguments = new Object[]{ "foo" };

        HelpDisplay received = helpDisplayFactory.createHelpDisplay(helpDisplayClass, arguments);

        assertNotNull(received);
    }    // createHelpDisplayWorksWithHelpFileDisplay()

    /**
     * Tests that {@link HelpDisplayFactoryImpl#createHelpDisplay(Class, Object[])}
     * throws an IllegalArgumentException with {@link HelpFileDisplay} when
     * there are too few arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createHelpDisplayThrowsExceptionWithHelpFileDisplayWhenThereAreTooFewArguments() {
        HelpDisplayFactoryImpl helpDisplayFactory = new HelpDisplayFactoryImpl();
        Class<? extends HelpDisplay> helpDisplayClass = HelpFileDisplay.class;
        Object[] arguments = new Object[0];

        helpDisplayFactory.createHelpDisplay(helpDisplayClass, arguments);
    }    // createHelpDisplayThrowsExceptionWithHelpFileDisplayWhenThereAreTooFewArguments()

    /**
     * Tests that {@link HelpDisplayFactoryImpl#createHelpDisplay(Class, Object[])}
     * throws an IllegalArgumentException with {@link HelpFileDisplay} when
     * there are too many arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createHelpDisplayThrowsExceptionWithHelpFileDisplayWhenThereAreTooManyArguments() {
        HelpDisplayFactoryImpl helpDisplayFactory = new HelpDisplayFactoryImpl();
        Class<? extends HelpDisplay> helpDisplayClass = HelpFileDisplay.class;
        Object[] arguments = new Object[]{ "foo", "bar" };

        helpDisplayFactory.createHelpDisplay(helpDisplayClass, arguments);
    }    // createHelpDisplayThrowsExceptionWithHelpFileDisplayWhenThereAreTooManyArguments()

    /* tests with HelpFileFromResourceDisplay */

    /**
     * Tests that {@link HelpDisplayFactoryImpl#createHelpDisplay(Class, Object[])}
     * works with {@link HelpFileFromResourceDisplay}.
     */
    @Test
    public void createHelpDisplayWorksWithHelpFileFromResourceDisplay() {
        HelpDisplayFactoryImpl helpDisplayFactory = new HelpDisplayFactoryImpl();
        Class<? extends HelpDisplay> helpDisplayClass = HelpFileFromResourceDisplay.class;
        Object[] arguments = new Object[]{ "foo" };

        HelpDisplay received = helpDisplayFactory.createHelpDisplay(helpDisplayClass, arguments);

        assertNotNull(received);
    }    // createHelpDisplayWorksWithHelpFileFromResourceDisplay()

    /**
     * Tests that {@link HelpDisplayFactoryImpl#createHelpDisplay(Class, Object[])}
     * throws an IllegalArgumentException with {@link HelpFileFromResourceDisplay} when
     * there are too few arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createHelpDisplayThrowsExceptionWithHelpFileFromResourceDisplayWhenThereAreTooFewArguments() {
        HelpDisplayFactoryImpl helpDisplayFactory = new HelpDisplayFactoryImpl();
        Class<? extends HelpDisplay> helpDisplayClass = HelpFileFromResourceDisplay.class;
        Object[] arguments = new Object[0];

        helpDisplayFactory.createHelpDisplay(helpDisplayClass, arguments);
    }    // createHelpDisplayThrowsExceptionWithHelpFileFromResourceDisplayWhenThereAreTooFewArguments()

    /**
     * Tests that {@link HelpDisplayFactoryImpl#createHelpDisplay(Class, Object[])}
     * throws an IllegalArgumentException with {@link HelpFileFromResourceDisplay} when
     * there are too many arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createHelpDisplayThrowsExceptionWithHelpFileFromResourceDisplayWhenThereAreTooManyArguments() {
        HelpDisplayFactoryImpl helpDisplayFactory = new HelpDisplayFactoryImpl();
        Class<? extends HelpDisplay> helpDisplayClass = HelpFileFromResourceDisplay.class;
        Object[] arguments = new Object[]{ "foo", "bar" };

        helpDisplayFactory.createHelpDisplay(helpDisplayClass, arguments);
    }    // createHelpDisplayThrowsExceptionWithHelpFileFromResourceDisplayWhenThereAreTooManyArguments()
    
}
