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
package bscmail.gui.util;

import java.awt.Component;
import javax.swing.border.Border;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ComponentFactory}.
 *
 * @author Wayne Miller <waynem77@yahoo.com>
 */
public class ComponentFactoryTest {

    /**
     * Tests that {@link ComponentFactory#getStandardBorder()} does not throw an
     * exception.
     */
    @Test
    public void getStandardBorderDoesNotThrowException() {
        ComponentFactory.getStandardBorder();
    }    // getStandardBorderDoesNotThrowException()

    /**
     * Tests that {@link ComponentFactory#getStandardBorder()} does not return
     * null.
     */
    @Test
    public void getStandardBorderDoesNotReturnNull() {
        Border border = ComponentFactory.getStandardBorder();

        assertNotNull(border);
    }    // getStandardBorderDoesNotReturnNull()

    /**
     * Tests that {@link ComponentFactory#getStandardVerticalStrut()} does not
     * throw an exception.
     */
    @Test
    public void getStandardVerticalStrutDoesNotThrowException() {
        ComponentFactory.getStandardVerticalStrut();
    }    // getStandardVerticalStrutDoesNotThrowException()

    /**
     * Tests that {@link ComponentFactory#getStandardVerticalStrut()} does not
     * return null.
     */
    @Test
    public void getStandardVerticalStrutDoesNotReturnNull() {
        Component strut = ComponentFactory.getStandardVerticalStrut();

        assertNotNull(strut);
    }    // getStandardVerticalStrutDoesNotReturnNull()

}
