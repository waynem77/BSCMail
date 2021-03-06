/*
 * Copyright © 2014-2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.gui;

import io.github.waynem77.bscmail.persistent.Event;
import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.TestApplication;
import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link DisplayEmailFrame}.
 *
 * @author Wayne Miller
 */
public class DisplayEmailFrameTest {

    /**
     * Returns an application that can be used in tests.
     */
    private Application getTestApplication()  {
        return new TestApplication();
    }    // getTestApplication()

    /*
     * Unit tests
     */

    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(bscmail.Application, bscmail.Event)}
     * throws a {@link NullPointerException} when application is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenApplicationIsNull() throws IOException {
        Application application = null;
        Event event = new Event();

        DisplayEmailFrame frame = new DisplayEmailFrame(application, event);
    }    // constructorThrowsExceptionWhenApplicationIsNull()

    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(bscmail.Application, bscmail.Event)}
     * throws a {@link NullPointerException} when event is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenEventIsNull() throws IOException {
        Application application = getTestApplication();
        Event event = null;

        DisplayEmailFrame frame = new DisplayEmailFrame(application, event);
    }    // constructorThrowsExceptionWhenEventIsNull()

    /**
     * Tests that
     * {@link DisplayEmailFrame#DisplayEmailFrame(bscmail.Application, bscmail.Event)}
     * does not throw an exception when no parameter is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNoParamIsNull() throws IOException {
        Application application = getTestApplication();
        Event event = new Event();

        DisplayEmailFrame frame = new DisplayEmailFrame(application, event);
    }    // constructorDoesNotThrowExceptionWhenNoParamIsNull()

}    // DisplayEmailFrameTest
