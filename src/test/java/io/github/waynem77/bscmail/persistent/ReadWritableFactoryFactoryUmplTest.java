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

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ReadWritableFactoryFactoryImpl}
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class ReadWritableFactoryFactoryUmplTest {

    /**
     * Tests that
     * {@link ReadWritableFactoryFactoryImpl#getReadWritableFactory(Class)} throws a
     * NullPointerException when readWritableClass is null.
     */
    @Test(expected = NullPointerException.class)
    public void getReadWritableFactoryThrowsExceptionWhenReadWritableClassIsNull() {
        ReadWritableFactoryFactoryImpl factoryFactory = new ReadWritableFactoryFactoryImpl();
        Class<? extends ReadWritable> readWritableClass = null;

        factoryFactory.getReadWritableFactory(readWritableClass);
    }    // getReadWritableFactoryThrowsExceptionWhenReadWritableClassIsNull

    /**
     * Tests that
     * {@link ReadWritableFactoryFactoryImpl#getReadWritableFactory(Class)} throws
     * an IllegalArgumentException when no factory has been implemented for
     * readWritableClass.
     */
    @Test(expected = IllegalArgumentException.class)
    public void getReadWritableFactoryThrowsExceptionWhenRWClassIsUnknown() {
        class FactoryNotImplemented implements ReadWritable {
            @Override public Map<String, Object> getReadWritableProperties() { return null; }
            @Override public ReadWritableFactory<FactoryNotImplemented> getReadWritableFactory() { return null; }
        }    // FactoryNotImplemented
        ReadWritableFactoryFactoryImpl factoryFactory = new ReadWritableFactoryFactoryImpl();
        Class<? extends ReadWritable> readWritableClass = FactoryNotImplemented.class;

        factoryFactory.getReadWritableFactory(readWritableClass);
    }    // getReadWritableFactoryThrowsExceptionWhenRWClassIsNull

    /**
     * Tests that
     * {@link ReadWritableFactoryFactoryImpl#getReadWritableFactory(Class)} works
     * with {@link EmailServerProperties}.
     */
    @Test
    public void getReadWritableFactoryWorksWithEmailServerProperties() {
        ReadWritableFactoryFactoryImpl factoryFactory = new ReadWritableFactoryFactoryImpl();
        Class<EmailServerProperties> readWritableClass = EmailServerProperties.class;

        ReadWritableFactory<EmailServerProperties> rwFactory = factoryFactory.getReadWritableFactory(readWritableClass);
        assertNotNull(rwFactory);

        Map<String, Object> properties = new HashMap<>();
        EmailServerProperties received = rwFactory.constructReadWritable(properties);
        assertNotNull(received);
    }    // getReadWritableFactoryWorksWithEmailServerProperties

    /**
     * Tests that
     * {@link ReadWritableFactoryFactoryImpl#getReadWritableFactory(Class)} works
     * with {@link EmailTemplate}.
     */
    @Test
    public void getReadWritableFactoryWorksWithEmailTemplate() {
        ReadWritableFactoryFactoryImpl factoryFactory = new ReadWritableFactoryFactoryImpl();
        Class<EmailTemplate> readWritableClass = EmailTemplate.class;

        ReadWritableFactory<EmailTemplate> rwFactory = factoryFactory.getReadWritableFactory(readWritableClass);
        assertNotNull(rwFactory);

        Map<String, Object> properties = new HashMap<>();
        EmailTemplate received = rwFactory.constructReadWritable(properties);
        assertNotNull(received);
    }    // getReadWritableFactoryWorksWithEmailTemplate

    /**
     * Tests that
     * {@link ReadWritableFactoryFactoryImpl#getReadWritableFactory(Class)} works
     * with {@link EventProperty}.
     */
    @Test
    public void getReadWritableFactoryWorksWithEventProperty() {
        ReadWritableFactoryFactoryImpl factoryFactory = new ReadWritableFactoryFactoryImpl();
        Class<EventProperty> readWritableClass = EventProperty.class;

        ReadWritableFactory<EventProperty> rwFactory = factoryFactory.getReadWritableFactory(readWritableClass);
        assertNotNull(rwFactory);

        Map<String, Object> properties = new HashMap<>();
        EventProperty received = rwFactory.constructReadWritable(properties);
        assertNotNull(received);
    }    // getReadWritableFactoryWorksWithEventProperty

    /**
     * Tests that
     * {@link ReadWritableFactoryFactoryImpl#getReadWritableFactory(Class)} works
     * with {@link Role}.
     */
    @Test
    public void getReadWritableFactoryWorksWithRole() {
        ReadWritableFactoryFactoryImpl factoryFactory = new ReadWritableFactoryFactoryImpl();
        Class<Role> readWritableClass = Role.class;

        ReadWritableFactory<Role> rwFactory = factoryFactory.getReadWritableFactory(readWritableClass);
        assertNotNull(rwFactory);

        Map<String, Object> properties = new HashMap<>();
        Role received = rwFactory.constructReadWritable(properties);
        assertNotNull(received);
    }    // getReadWritableFactoryWorksWithRole

    /**
     * Tests that
     * {@link ReadWritableFactoryFactoryImpl#getReadWritableFactory(Class)} works
     * with {@link Shift}.
     */
    @Test
    public void getReadWritableFactoryWorksWithShift() {
        ReadWritableFactoryFactoryImpl factoryFactory = new ReadWritableFactoryFactoryImpl();
        Class<Shift> readWritableClass = Shift.class;

        ReadWritableFactory<Shift> rwFactory = factoryFactory.getReadWritableFactory(readWritableClass);
        assertNotNull(rwFactory);

        Map<String, Object> properties = new HashMap<>();
        Shift received = rwFactory.constructReadWritable(properties);
        assertNotNull(received);
    }    // getReadWritableFactoryWorksWithShift

    /**
     * Tests that
     * {@link ReadWritableFactoryFactoryImpl#getReadWritableFactory(Class)} works
     * with {@link Volunteer}.
     */
    @Test
    public void getReadWritableFactoryWorksWithVolunteer() {
        ReadWritableFactoryFactoryImpl factoryFactory = new ReadWritableFactoryFactoryImpl();
        Class<Volunteer> readWritableClass = Volunteer.class;

        ReadWritableFactory<Volunteer> rwFactory = factoryFactory.getReadWritableFactory(readWritableClass);
        assertNotNull(rwFactory);

        Map<String, Object> properties = new HashMap<>();
        Volunteer received = rwFactory.constructReadWritable(properties);
        assertNotNull(received);
    }    // getReadWritableFactoryWorksWithVolunteer

}    // ReadWritableFactoryFactoryImplTest
