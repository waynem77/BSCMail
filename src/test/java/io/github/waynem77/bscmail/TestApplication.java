/**
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

package io.github.waynem77.bscmail;

import io.github.waynem77.bscmail.help.TestHelpDisplay;
import io.github.waynem77.bscmail.iolayer.TestIOLayer;

/**
 * Application used in tests.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class TestApplication extends Application {

    public TestApplication() {
        super(new ApplicationInfo("foo", "bar", "baz", "smurf"),    // applicationInfo
                new TestIOLayer<>(),    // shiftsIOLayer
                new TestIOLayer<>(),    // volunteersIOLayer
                new TestIOLayer<>(),    // rolesIOLayer
                new TestIOLayer<>(),    // emailTemplateIOLayer
                new TestIOLayer<>(),    // emailServerPropertiesIOLayer
                new TestIOLayer<>(),    // eventPropertiesIOLayer
                new TestHelpDisplay());    // helpDisplay
    }    // TestApplication()

}    // TestApplication
