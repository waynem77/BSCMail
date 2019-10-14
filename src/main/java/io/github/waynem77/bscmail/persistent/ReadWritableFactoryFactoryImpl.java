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

package io.github.waynem77.bscmail.persistent;

/**
 * Default factory for {@link ReadWritableFactory} objects.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 4.0
 */
public class ReadWritableFactoryFactoryImpl implements ReadWritableFactoryFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends ReadWritable> ReadWritableFactory getReadWritableFactory(Class<T> readWritableClass) {
        if (readWritableClass == null) {
            throw new NullPointerException("readWritableClass may not be null");
        }    // if

        if (readWritableClass.equals(EmailServerProperties.class)) {
            return new EmailServerProperties.Factory();
        } else if (readWritableClass.equals(EmailTemplate.class)) {    // if
            return new EmailTemplate.Factory();
        } else if (readWritableClass.equals(EventProperty.class)) {    // else if
            return new EventProperty.Factory();
        } else if (readWritableClass.equals(Role.class)) {    // else if
            return new Role.Factory();
        } else if (readWritableClass.equals(Shift.class)) {    // else if
            return new Shift.Factory();
        } else if (readWritableClass.equals(Volunteer.class)) {    // else if
            return new Volunteer.Factory();
        }    // else if
        throw new IllegalArgumentException("Method not implemented for " + readWritableClass);
    }

}    // ReadWritableFactoryFactoryImpl
