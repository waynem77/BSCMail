/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui;

import java.util.*;
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
    public void testLoadElementNullNoException() {
        System.out.println("loadElement - null, no exception");
        
        ManageElementPanel<E> panel = getPanel();
        E element = null;
        panel.loadElement(element);
    }    // testLoadElementNullNoException()
    
    /**
     * Tests that {@link ManageElementPanel#loadElement(Object)} does not throw
     * an exception when element is not null.
     */
    @Test
    public void testLoadElementNotNullNoException() {
        System.out.println("loadElement - no exception");
        
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);
    }    // testLoadElementNotNullNoException()

    /* createElement */
    
    /**
     * Tests that {@link ManageElementPanel#createElement()} does not throw
     * an exception when the panel is loaded.
     */
    @Test
    public void testCreateElementNoException() {
        System.out.println("createElement - no exception");
        
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);
        panel.createElement();
    }    // testCreateElementNoException()
    
    /**
     * Tests that {@link ManageElementPanel#createElement()} does not return
     * null when the panel is loaded.
     */
    @Test
    public void testCreateElementNotNull() {
        System.out.println("createElement - not null");
        
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);
        E received = panel.createElement();
        assertNotNull(received);
    }    // testCreateElementNotNull()
    
    /**
     * Tests that {@link ManageElementPanel#createElement()} returns the correct
     * value when the panel is loaded.
     */
    @Test
    public void testCreateElement() {
        System.out.println("createElement");
        
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);
        E expected = element;
        E received = panel.createElement();
        assertEquals(expected, received);
    }    // testCreateElement()

    /* elementIsValid */
    
    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} does not throw an
     * exception when the element is null.
     */
    @Test
    public void testElementIsValidNullNoException() {
        System.out.println("elementIsValid - element is null, no exception");
        
        ManageElementPanel<E> panel = getPanel();
        E element = null;
        panel.loadElement(element);
        panel.elementIsValid();
    }    // testElementIsValidNullNoException()
    
    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} does not throw an
     * exception when the element is invalid.
     */
    @Test
    public void testElementIsValidInvalidNoException() {
        System.out.println("elementIsValid - element is invalid, no exception");
        
        ManageElementPanel<E> panel = getPanel();
        E element = getInvalidElement();
        panel.loadElement(element);
        panel.elementIsValid();
    }    // testElementIsValidInvalidNoException()
    
    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} returns false when
     * when the element is invalid.
     */
    @Test
    public void testElementIsValidInvalid() {
        System.out.println("elementIsValid - element is invalid");
        
        ManageElementPanel<E> panel = getPanel();
        E element = getInvalidElement();
        panel.loadElement(element);
        boolean expected = false;
        boolean received = panel.elementIsValid();
        assertEquals(expected, received);
    }    // testElementIsValidInvalid()
    
    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} does not throw an
     * exception when the element is valid.
     */
    @Test
    public void testElementIsValidValidNoException() {
        System.out.println("elementIsValid - element is valid, no exception");
        
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);
        panel.elementIsValid();
    }    // testElementIsValidValidNoException()
    
    /**
     * Tests that {@link ManageElementPanel#elementIsValid()} returns true when
     * when the element is valid.
     */
    @Test
    public void testElementIsValidValid() {
        System.out.println("elementIsValid - element is invalid");
        
        ManageElementPanel<E> panel = getPanel();
        E element = getElement();
        panel.loadElement(element);
        boolean expected = true;
        boolean received = panel.elementIsValid();
        assertEquals(expected, received);
    }    // testElementIsValidValid()
    
    /* addObserver */
    
    /**
     * Tests that
     * {@link ManageElementPanel#addObserver(ManageElementPanelObserver)} throws
     * a {@link NullPointerException} when observer is null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddObsercerNull() {
        System.out.println("addObserver - observer is null");
        
        ManageElementPanel<E> panel = getPanel();
        TestObserver observer = null;
        panel.addObserver(observer);
    }    // testAddObsercerNull()
    
    /**
     * Tests that
     * {@link ManageElementPanel#addObserver(ManageElementPanelObserver)} does
     * not throw an exception when observer is not null.
     */
    @Test
    public void testAddObsercerNoException() {
        System.out.println("addObserver - no exception");
        
        ManageElementPanel<E> panel = getPanel();
        TestObserver observer = new TestObserver();
        panel.addObserver(observer);
    }    // testAddObsercerNoException()

    /* notifyObservers */
    
    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} does not throw an
     * exception when there are no observers.
     */
    @Test
    public void testNotifyObserversNoObserversNoException() {
        System.out.println("notifyObservers - no observers, no exception");
        
        ManageElementPanel<E> panel = getPanel();
        panel.notifyObservers();
    }    // testNotifyObserversNoObserversNoException()
    
    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} does not throw an
     * exception when there is one observers.
     */
    @Test
    public void testNotifyObserversOneObserverNoException() {
        System.out.println("notifyObservers - one observer, no exception");
        
        ManageElementPanel<E> panel = getPanel();
        panel.addObserver(new TestObserver());
        panel.notifyObservers();
    }    // testNotifyObserversOneObserverNoException()
    
    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} does not throw an
     * exception when there are multiple observers.
     */
    @Test
    public void testNotifyObserversMultipleObserversNoException() {
        System.out.println("notifyObservers - multiple observers, no exception");
        
        ManageElementPanel<E> panel = getPanel();
        panel.addObserver(new TestObserver());
        panel.addObserver(new TestObserver());
        panel.notifyObservers();
    }    // testNotifyObserversMultipleObserversNoException()
    
    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} works properly.
     */
    @Test
    public void testNotifyObservers() {
        System.out.println("notifyObservers");
        
        ManageElementPanel<E> panel = getPanel();
        List<TestObserver> observers = Arrays.asList(new TestObserver(), new TestObserver());
        for (TestObserver observer : observers) {
            panel.addObserver(observer);
        }    // for
        panel.notifyObservers();
        for (TestObserver observer : observers) {
            assertTrue(observer.panelChanged());
        }    // for
    }    // testNotifyObservers()
    
    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} is called automatically
     * when the validity of the object changes from invalid to valid.
     */
    @Test
    public void testNotifyObserversAutoInvalidToValid() {
        System.out.println("notifyObservers - called automatically when element changes from invalid to valid");
        
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
    }    // testNotifyObserversAutoInvalidToValid()
    
    /**
     * Tests that {@link ManageElementPanel#notifyObservers()} is called automatically
     * when the validity of the object changes from valid to invalid.
     */
    @Test
    public void testNotifyObserversAutoValidToInvalid() {
        System.out.println("notifyObservers - called automatically when element changes from invalid to valid");
        
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
    }    // testNotifyObserversAutoValidToInvalid()
    
}    // ManageElementPanelTest
