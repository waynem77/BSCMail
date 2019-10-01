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

package bscmail;

/**
 * A class may implement the {@code EmailTemplateObserver} interface when it
 * wants to be informed of changes to the defined email template.
 *
 * @see Application
 * @since 3.4
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public interface EmailServerPropertiesObserver {

    /**
     * This method is called whenever the defined email server properties
     * change.
     */
    public void emailServerPropertiesChanged();

}    // EmailServerPropertiesObserver
