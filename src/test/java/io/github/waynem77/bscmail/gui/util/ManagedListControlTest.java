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

package io.github.waynem77.bscmail.gui.util;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManagedListControl}.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class ManagedListControlTest {

    public class TestListener implements DragAndDropListener {

        public Component component = null;

        @Override
        public void dragAndDropPerformed(Component component) {
            this.component = component;
        }    // dragAndDropPerformed()

    }    // TestListener

    /* constructor */

    /**
     * Tests that {@link ManagedListControl#ManagedListControlTest(Vector)}
     * throws a NullPointerException when listData is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenListDataIsNull() {
        Vector listData = null;

        ManagedListControl managedListControl = new ManagedListControl(listData);
    }    // constructorThrowsExceptionWhenListDataIsNull()

    /**
     * Tests that {@link ManagedListControl#ManagedListControlTest(Vector)}
     * throws a NullPointerException when listData contains null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenListDataContainsNull() {
        Vector listData = new Vector(Arrays.asList("foo", null, "baz"));

        ManagedListControl managedListControl = new ManagedListControl(listData);
    }    // constructorThrowsExceptionWhenListDataContainsNull()

    /**
     * Tests that {@link ManagedListControl#ManagedListControlTest(Vector)} does
     * not throw an exception when listData is not null and does not contain
     * null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenListDataIsNotNullAndDoesNotContainNull() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));

        ManagedListControl managedListControl = new ManagedListControl(listData);
    }    // constructorDoesNotThrowExceptionWhenListDataIsNotNullAndDoesNotContainNull()

    /* getListData */

    /**
     * Tests that {@link ManagedListControl#getListData()} does not throw an
     * exception .
     */
    @Test
    public void getListDataDoesNotThrowException() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);

        managedListControl.getListData();
    }    // getListDataDoesNotThrowException()

    /**
     * Tests that {@link ManagedListControl#getListData()} initially returns the
     * list data passed to the constructor.
     */
    @Test
    public void getListDataInitiallyReturnsOriginalData() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);

        Vector received = managedListControl.getListData();

        Vector expected = listData;
        assertEquals(expected, received);
    }    // getListDataDoesNotThrowException()

    /**
     * Tests that {@link ManagedListControl#getListData()} returns the list data
     * passed to the last call of {@link ManagedListControl#setListData(Vector)}.
     */
    @Test
    public void getListDataReturnsDataFromLastCallToSetListData() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);
        listData = new Vector(Arrays.asList("foo", "bar", "baz", "bazz"));
        managedListControl.setListData(listData);
        listData = new Vector(Arrays.asList("foo", "bar", "baz", "bazz", "bazzz"));
        managedListControl.setListData(listData);

        Vector received = managedListControl.getListData();

        Vector expected = listData;
        assertEquals(expected, received);
    }    // getListDataReturnsDataFromLastCallToSetListData()

    /* addDragAndDropListener */

    /**
     * Tests that
     * {@link ManagedListControl#addDragAndDropListener(DragAndDropListener)}
     * throws a NullPointerException when listener is null.
     */
    @Test(expected = NullPointerException.class)
    public void addDragAndDropListenerThrowsExceptionWhenListenerIsNull() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);
        TestListener listener = null;

        managedListControl.addDragAndDropListener(listener);
    }    // getListDataReturnsDataFromLastCallToSetListData()

    /**
     * Tests that
     * {@link ManagedListControl#addDragAndDropListener(DragAndDropListener)}
     * does not throw an exception when listener is not null.
     */
    @Test
    public void addDragAndDropListenerDoesNotThrowExceptionWhenListenerIsNotNull() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);
        TestListener listener = new TestListener();

        managedListControl.addDragAndDropListener(listener);
    }    // addDragAndDropListenerDoesNotThrowExceptionWhenListenerIsNotNull()

    /**
     * Tests that
     * {@link ManagedListControl#addDragAndDropListener(DragAndDropListener)}
     * does not throw an exception when multiple listeners are added.
     */
    @Test
    public void addDragAndDropListenerDoesNotThrowExceptionWhenMultipleListenersAreAdded() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);

        managedListControl.addDragAndDropListener(new TestListener());
        managedListControl.addDragAndDropListener(new TestListener());
    }    // addDragAndDropListenerDoesNotThrowExceptionWhenMultipleListenersAreAdded()

    /* notifyDragAndDropListeners */

    /**
     * Tests that {@link ManagedListControl#notifyDragAndDropListeners()} does
     * not throw an exception when there are no listeners.
     */
    @Test
    public void notifyDragAndDropListenersDoesNotThrowExceptionWhenThereAreNoListeners() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);

        managedListControl.notifyDragAndDropListeners();
    }    // notifyDragAndDropListenersDoesNotThrowExceptionWhenThereAreNoListeners()

    /**
     * Tests that {@link ManagedListControl#notifyDragAndDropListeners()} does
     * not throw an exception when there is one listener.
     */
    @Test
    public void notifyDragAndDropListenersDoesNotThrowExceptionWhenThereIsOneListener() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);
        managedListControl.addDragAndDropListener(new TestListener());

        managedListControl.notifyDragAndDropListeners();
    }    // notifyDragAndDropListenersDoesNotThrowExceptionWhenThereIsOneListener()

    /**
     * Tests that {@link ManagedListControl#notifyDragAndDropListeners()} does
     * not throw an exception when there are multiple listeners.
     */
    @Test
    public void notifyDragAndDropListenersDoesNotThrowExceptionWhenThereAreMultipleListeners() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);
        managedListControl.addDragAndDropListener(new TestListener());
        managedListControl.addDragAndDropListener(new TestListener());

        managedListControl.notifyDragAndDropListeners();
    }    // notifyDragAndDropListenersDoesNotThrowExceptionWhenThereAreMultipleListeners()

    /**
     * Tests that {@link ManagedListControl#notifyDragAndDropListeners()}
     * notifies all listeners.
     */
    @Test
    public void notifyDragAndDropListenersNotifiesAllListeners() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);
        List<TestListener> listeners = Arrays.asList(new TestListener(), new TestListener());
        for (TestListener listener : listeners) {
            assertNull("test setup problem", listener.component);
            managedListControl.addDragAndDropListener(listener);
        }

        managedListControl.notifyDragAndDropListeners();

        for (TestListener listener : listeners) {
            assertNotNull(listener.component);
        }    // for
    }    // notifyDragAndDropListenersDoesNotThrowExceptionWhenThereAreMultipleListeners()

    /**
     * Tests that {@link ManagedListControl#notifyDragAndDropListeners()} passes
     * the ManagedListControl as the argument to
     * {@link DragAndDropListener#dragAndDropPerformed(Component)}.
     */
    @Test
    public void notifyDragAndDropListenersPassesListAsArgument() {
        Vector listData = new Vector(Arrays.asList("foo", "bar", "baz"));
        ManagedListControl managedListControl = new ManagedListControl(listData);
        TestListener listener = new TestListener();
        assertNull("test setup problem", listener.component);
        managedListControl.addDragAndDropListener(listener);

        managedListControl.notifyDragAndDropListeners();

        Component expected = managedListControl;
        Component received = listener.component;
        assertSame(expected, received);
    }    // notifyDragAndDropListenersPassesListAsArgument()

}
