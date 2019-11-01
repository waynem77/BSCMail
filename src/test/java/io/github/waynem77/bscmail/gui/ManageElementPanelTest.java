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

import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Abstract base class for unit tests for implementations of
 * {@link ManageElementPanel}.
 *
 * @param <E> the type of element being manipulated
 * @since 2.0
 * @author Wayne Miller
 */
@Ignore
public abstract class ManageElementPanelTest<E> {

    private class TestObserver implements ManageElementPanelObserver<E> {
        private boolean changed = false;
        @Override public void elementValidityChanged() { changed = true; }
        public boolean panelChanged() { return changed; }
    }    // TestObserver

    /**
     * Returns the manage element panel to be tested.
     *
     * @return the manage element panel to be tested
     */
    protected abstract ManageElementPanel getPanel();

    /**
     * Returns an invalid element to use in testing.
     *
     * @return an invalid element to use in testing
     */
    protected abstract E getInvalidElement();

    /**
     * Returns a valid element to use in testing.
     *
     * @return a valid element to use in testing
     */
    protected abstract E getElement();

    /*
     * Unit tests
     */

    /* loadElement */

    /**
     * Tests that {@link ManageElementPanel#loadElement(Object)} does not throw
     * an exception when element is null.
     */
    @Test
    public void loadElementDoesNotThrowExceptionWhenElementIsNull() {
        ManageElementPanel<E> panel = getPanel();
        E element = null;

        panel.loadElement(element);
    }    // loadElementDoesNotThrowExceptionWhenElementIsNull()

    /**
     * Tests that {@link ManageElementPanel#loadElement(Object)} does not throw
     * an exception when element is not null.
     */
    @Test
    public void loadElementDoesNotThrowExceptionWhenElementIsNotNull() {
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();

        panel.loadElement(element);
    }    // loadElementDoesNotThrowExceptionWhenElementIsNotNull()

    /* createElement */

    /**
     * Tests that {@link ManageElementPanel#createElement()} does not throw
     * an exception when the panel is loaded.
     */
    @Test
    public void createElementDoesNotThrowExceptionWhenPanelIsLoaded() {
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);

        panel.createElement();
    }    // createElementDoesNotThrowExceptionWhenPanelIsLoaded()

    /**
     * Tests that {@link ManageElementPanel#createElement()} does not return
     * null when the panel is loaded.
     */
    @Test
    public void createElementDoesNotReturnNullWhenPanelIsLoaded() {
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);

        E received = panel.createElement();

        assertNotNull(received);
    }    // createElementDoesNotReturnNullWhenPanelIsLoaded()

    /**
     * Tests that {@link ManageElementPanel#createElement()} returns the correct
     * value when the panel is loaded.
     */
    @Test
    public void createElementReturnsCorrectValueWhenPanelIsLoaded() {
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);

        E received = panel.createElement();

        E expected = element;
        assertEquals(expected, received);
    }    // createElementReturnsCorrectValueWhenPanelIsLoaded()

    /* elementIsValid */

    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} does not throw an
     * exception when the loaded element is null.
     */
    @Test
    public void elementIsValidDoesNotThrowExceptionWhenLoadedElementIsNull() {
        ManageElementPanel<E> panel = getPanel();
        E element = null;
        panel.loadElement(element);

        panel.elementIsValid();
    }    // elementIsValidDoesNotThrowExceptionWhenLoadedElementIsNull()

    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} returns false when
     * when the loaded element is null.
     */
    @Test
    public void elementIsValidReturnsFalseWhenLoadedElementIsNull() {
        ManageElementPanel<E> panel = getPanel();
        E element = null;
        panel.loadElement(element);

        boolean received = panel.elementIsValid();

        boolean expected = false;
        assertEquals(expected, received);
    }    // elementIsValidReturnsFalseWhenLoadedElementIsNull()

    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} does not throw an
     * exception when the loaded element is invalid.
     */
    @Test
    public void elementIsValidDoesNotThrowExceptionWhenLoadedElementIsInvalid() {
        ManageElementPanel<E> panel = getPanel();
        E element = getInvalidElement();
        panel.loadElement(element);

        panel.elementIsValid();
    }    // elementIsValidDoesNotThrowExceptionWhenLoadedElementIsInvalid()

    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} returns false when
     * when the loaded element is invalid.
     */
    @Test
    public void elementIsValidReturnsFalseWhenLoadedElementIsInvalid() {
        ManageElementPanel<E> panel = getPanel();
        E element = getInvalidElement();
        panel.loadElement(element);

        boolean received = panel.elementIsValid();

        boolean expected = false;
        assertEquals(expected, received);
    }    // elementIsValidReturnsFalseWhenLoadedElementIsInvalid()

    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} does not throw an
     * exception when the loaded element is valid.
     */
    @Test
    public void elementIsValidDoesNotThrowExceptionWhenLoadedElementIsValid() {
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);

        panel.elementIsValid();
    }    // elementIsValidDoesNotThrowExceptionWhenLoadedElementIsValid()

    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} returns true when
     * when the loaded element is valid.
     */
    @Test
    public void elementIsValidReturnsTrueWhenLoadedElementIsValid() {
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);

        boolean received = panel.elementIsValid();

        boolean expected = true;
        assertEquals(expected, received);
    }    // elementIsValidReturnsTrueWhenLoadedElementIsValid()

    /* setEditable */

    /**
     * Tests that {@link ManageElementPanel#setEditable(boolean)} does
     * not throw an exception when enabled is true.
     */
    @Test
    public void setControlsEnabledDoesNotThrowExceptionWhenEnabledIsTrue() {
        ManageElementPanel<E> panel = getPanel();
        boolean enabled = true;

        panel.setEditable(true);
    }    // setControlsEnabledDoesNotThrowExceptionWhenEnabledIsTrue()

    /**
     * Tests that {@link ManageElementPanel#setEditable(boolean)} does
     * not throw an exception when enabled is false.
     */
    @Test
    public void setControlsEnabledDoesNotThrowExceptionWhenEnabledIsFalse() {
        ManageElementPanel<E> panel = getPanel();
        boolean enabled = false;

        panel.setEditable(false);
    }    // setControlsEnabledDoesNotThrowExceptionWhenEnabledIsFalse()

    /* addObserver */

    /**
     * Tests that
     * {@link ManageElementPanel#addObserver(ManageElementPanelObserver)} throws
     * a {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void addObserverThrowsExceptionWhenObserverIsNull() {
        ManageElementPanel<E> panel = getPanel();
        TestObserver observer = null;

        panel.addObserver(observer);
    }    // addObserverThrowsExceptionWhenObserverIsNull()

    /**
     * Tests that
     * {@link ManageElementPanel#addObserver(ManageElementPanelObserver)} does
     * not throw an exception when observer is not null.
     */
    @Test
    public void addObserverDoesThrowsExceptionWhenObserverIsNotNull() {
        ManageElementPanel<E> panel = getPanel();
        TestObserver observer = new TestObserver();

        panel.addObserver(observer);
    }    // addObserverDoesThrowsExceptionWhenObserverIsNotNull()

    /* notifyObservers */

    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} does not throw an
     * exception when there are no observers.
     */
    @Test
    public void notifyObserversDoesNotThrowExceptionWhenThereAreNoObservers() {
        ManageElementPanel<E> panel = getPanel();

        panel.notifyObservers();
    }    // notifyObserversDoesNotThrowExceptionWhenThereAreNoObservers()

    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} does not throw an
     * exception when there is one observer.
     */
    @Test
    public void notifyObserversDoesNotThrowExceptionWhenThereIsOneObserver() {
        ManageElementPanel<E> panel = getPanel();
        panel.addObserver(new TestObserver());

        panel.notifyObservers();
    }    // notifyObserversDoesNotThrowExceptionWhenThereIsOneObserver()

    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} does not throw an
     * exception when there are multiple observers.
     */
    @Test
    public void notifyObserversDoesNotThrowExceptionWhenThereAreMultipleObservers() {
        ManageElementPanel<E> panel = getPanel();
        panel.addObserver(new TestObserver());
        panel.addObserver(new TestObserver());

        panel.notifyObservers();
    }    // notifyObserversDoesNotThrowExceptionWhenThereAreMultipleObservers()

    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} notifies the
     * observers.
     */
    @Test
    public void notifyObserversNotifiesAllObservers() {
        ManageElementPanel<E> panel = getPanel();
        List<TestObserver> observers = Arrays.asList(new TestObserver(), new TestObserver());
        for (TestObserver observer : observers) {
            panel.addObserver(observer);
        }    // for

        panel.notifyObservers();

        for (TestObserver observer : observers) {
            assertTrue(observer.panelChanged());
        }    // for
    }    // notifyObserversNotifiesAllObservers()

    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} is called automatically
     * when the validity of the object changes from invalid to valid.
     */
    @Test
    public void notifyObserversIsCalledWhenObjectChangesFromInvalidToValid() {
        ManageElementPanel<E> panel = getPanel();
        panel.loadElement(getInvalidElement());
        List<TestObserver> observers = Arrays.asList(new TestObserver(), new TestObserver());
        for (TestObserver observer : observers) {
            panel.addObserver(observer);
        }    // for

        panel.loadElement(getElement());

        for (TestObserver observer : observers) {
            assertTrue(observer.panelChanged());
        }    // for
    }    // notifyObserversIsCalledWhenObjectChangesFromInvalidToValid()

    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} is called automatically
     * when the validity of the object changes from valid to invalid.
     */
    @Test
    public void notifyObserversIsCalledWhenObjectChangesFromValidToInvalid() {
        ManageElementPanel<E> panel = getPanel();
        panel.loadElement(getElement());
        List<TestObserver> observers = Arrays.asList(new TestObserver(), new TestObserver());
        for (TestObserver observer : observers) {
            panel.addObserver(observer);
        }    // for

        panel.loadElement(getInvalidElement());

        for (TestObserver observer : observers) {
            assertTrue(observer.panelChanged());
        }    // for
    }    // notifyObserversIsCalledWhenObjectChangesFromValidToInvalid()

    /* createCopy */

    /**
     * Tests that {@link ManageElementPanel#createCopy()} does not throw an
     * exception.
     */
    @Test
    public void createCopyDoesNotThrowException() {
        ManageElementPanel<E> panel = getPanel();

        panel.createCopy();
    }    // createCopyDoesNotThrowException()

    /**
     * Tests that {@link ManageElementPanel#createCopy()} does not return null.
     */
    @Test
    public void createCopyDoesNotReturnNull() {
        ManageElementPanel<E> panel = getPanel();

        ManageElementPanel<E> received = panel.createCopy();

        assertNotNull(received);
    }    // createCopyDoesNotReturnNull()

    /**
     * Tests that {@link ManageElementPanel#createCopy()} returns an object not
     * identical to the original.
     */
    @Test
    public void createCopyReturnsObjectDistinctFromOriginal() {
        ManageElementPanel<E> panel = getPanel();

        ManageElementPanel<E> received = panel.createCopy();

        ManageElementPanel<E> expected = panel;
        assertNotSame(expected, received);
    }    // createCopyReturnsObjectDistinctFromOriginal()

}    // ManageElementPanelTest
