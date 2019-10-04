/*
 * Copyright © 2018-2019 its authors.  See the file "AUTHORS" for details.
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EnumRadioPanel}.
 *
 * @author Wayne Miller
 */
public class EnumRadioPanelTest {

    /**
     * Enum used in testing.
     */
    private enum TestEnum {
        FOO,
        BAR,
        BAZ;
    }    // TestEnum

    /**
     * ActionListener used in testing.
     */
    private class TestActionListener implements ActionListener {
        private boolean receivedActionEvent = false;
        private ActionEvent actionEvent = null;
        @Override public void actionPerformed(ActionEvent e) {
            receivedActionEvent = true;
            this.actionEvent = e;
        }    // actionPerformed()
        public boolean receivedActionEvent() {
            return receivedActionEvent;
        }    // receivedActionEvent()
        public ActionEvent getActionEvent() {
            return actionEvent;
        }    // getActionEvent()
    }
    /* constructor */

    /**
     * Tests that {@link EnumRadioPanel#EnumRadioPanel(java.lang.Class)} throws
     * a NullPointerException when enumClass is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenEnumClassIsNull() {
        Class enumClass = null;

        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
    }    // constructorThrowsExceptionWhenEnumClassIsNull()

    /**
     * Tests that {@link EnumRadioPanel#EnumRadioPanel(java.lang.Class)} does
     * not throw an exception when enumClass is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenEnumClassIsNotNull() {
        Class enumClass = TestEnum.class;

        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
    }    // constructorDoesNotThrowException()

    /**
     * Tests that {@link EnumRadioPanel#EnumRadioPanel()} sets the selection to
     * the first enum value.
     */
    @Test
    public void constructorSetsSelectionToFirstEnumValue() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);

        TestEnum received = panel.getSelection();

        TestEnum expected = TestEnum.FOO;
        assertEquals(expected, received);
    }    // constructorSetsSelectionToFirstEnumValue()

    /* setSelection */

    /**
     * Tests that {@link EnumRadioPanel#setSelection(java.lang.Enum)} throws a
     * NullPointerException if selection is null.
     */
    @Test(expected = NullPointerException.class)
    public void setSelectionThrowsExceptionIfSelectionIsNull() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        TestEnum selection = null;

        panel.setSelection(selection);
    }    // setSelectionThrowsExceptionIfSelectionIsNull()

    /**
     * Tests that {@link EnumRadioPanel#setSelection(java.lang.Enum)} does not
     * throw an exception.
     */
    @Test
    public void setSelectionDoesNotThrowExceptionWhenSelectionIsNotNull() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        TestEnum selection = TestEnum.BAR;

        panel.setSelection(selection);
    }    // setSelectionDoesNotThrowExceptionWhenSelectionIsNotNull()

    /**
     * Tests that {@link EnumRadioPanel#setSelection(java.lang.Enum)} sets the
     * selection.
     */
    @Test
    public void setSelectionSetsSelection() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        TestEnum selection = TestEnum.BAR;
        panel.setSelection(selection);

        TestEnum received = panel.getSelection();

        TestEnum expected = selection;
        assertEquals(expected, received);
    }    // setSelectionSetsSelection()

    /* getSelection */

    /**
     * Tests that {@link EnumRadioPanel#getSelection()} does not throw an
     * exception.
     */
    @Test
    public void getSelectionDoesNotThrowException() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);

        panel.getSelection();
    }    // getSelectionDoesNotThrowException()

    /* addActionListener */

    /**
     * Tests that
     * {@link EnumRadioPanel#addActionListener(java.awt.event.ActionListener)}
     * throws a NullPointerException when listener is null.
     */
    @Test(expected = NullPointerException.class)
    public void addActionListenerThrowsExceptionWhenListenerIsNull() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        ActionListener listener = null;

        panel.addActionListener(listener);
    }    // addActionListenerThrowsExceptionWhenListenerIsNull()

    /**
     * Tests that
     * {@link EnumRadioPanel#addActionListener(java.awt.event.ActionListener)}
     * does not throw an exception when listener is not null.
     */
    @Test
    public void addActionListenerDoesNotThrowsExceptionWhenListenerIsNotNull() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        ActionListener listener = new  TestActionListener();

        panel.addActionListener(listener);
    }    // addActionListenerDoesNotThrowsExceptionWhenListenerIsNotNull()

    /* notifyActionListeners */

    /**
     * Tests that {@link EnumRadioPanel#notifyActionListeners()} does not throw
     * an exception when there are no listeners.
     */
    @Test
    public void notifyActionListenersDoesNotThrowExceptionWhenThereAreNoListeners() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);

        panel.notifyActionListeners();
    }    // notifyActionListenersDoesNotThrowExceptionWhenThereAreNoListeners()

    /**
     * Tests that {@link EnumRadioPanel#notifyActionListeners()} does not throw
     * an exception when there is one listener.
     */
    @Test
    public void notifyActionListenersDoesNotThrowExceptionWhenThereIsOneListener() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        panel.addActionListener(new TestActionListener());

        panel.notifyActionListeners();
    }    // notifyActionListenersDoesNotThrowExceptionWhenThereIsOneListener()

    /**
     * Tests that {@link EnumRadioPanel#notifyActionListeners()} does not throw
     * an exception when there are multiple listeners.
     */
    @Test
    public void notifyActionListenersDoesNotThrowExceptionWhenThereAreMultipleListeners() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        panel.addActionListener(new TestActionListener());
        panel.addActionListener(new TestActionListener());

        panel.notifyActionListeners();
    }    // notifyActionListenersDoesNotThrowExceptionWhenThereAreMultipleListeners()

    /**
     * Tests that {@link EnumRadioPanel#notifyActionListeners()} sends the
     * listener an object.
     */
    @Test
    public void notifyActionListenersSendsObject() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        TestActionListener listener = new TestActionListener();
        panel.addActionListener(listener);

        panel.notifyActionListeners();

        assertTrue(listener.receivedActionEvent);
    }    // notifyActionListenersSendsObject()

    /**
     * Tests that {@link EnumRadioPanel#notifyActionListeners()} sends the
     * listener an object with source identical to the panel.
     */
    @Test
    public void notifyActionListenersSendsObjectWithCorrectSource() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        TestActionListener listener = new TestActionListener();
        panel.addActionListener(listener);

        panel.notifyActionListeners();

        ActionEvent event = listener.getActionEvent();
        Object received = event.getSource();
        Object expected = panel;
        assertSame(expected, received);
    }    // notifyActionListenersSendsObjectWithCorrectSource()

    /**
     * Tests that {@link EnumRadioPanel#notifyActionListeners()} sends the
     * listener an object with id equal to {@link ActionEvent#ACTION_PERFORMED}.
     */
    @Test
    public void notifyActionListenersSendsObjectWithCorrectId() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        TestActionListener listener = new TestActionListener();
        panel.addActionListener(listener);

        panel.notifyActionListeners();

        ActionEvent event = listener.getActionEvent();
        int received = event.getID();
        int expected = ActionEvent.ACTION_PERFORMED;
        assertEquals(expected, received);
    }    // notifyActionListenersSendsObjectWithCorrectId()

    /**
     * Tests that {@link EnumRadioPanel#notifyActionListeners()} sends the
     * listener an object with command string null.
     */
    @Test
    public void notifyActionListenersSendsObjectWithCorrectCommand() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        TestActionListener listener = new TestActionListener();
        panel.addActionListener(listener);

        panel.notifyActionListeners();

        ActionEvent event = listener.getActionEvent();
        String received = event.getActionCommand();
        String expected = null;
        assertEquals(expected, received);
    }    // notifyActionListenersSendsObjectWithCorrectCommand()

    /**
     * Tests that {@link EnumRadioPanel#notifyActionListeners()} sends all
     * listeners equal objects.
     */
    @Test
    public void notifyActionListenersSendsAllListenersEqualObjects() {
        Class enumClass = TestEnum.class;
        EnumRadioPanel<TestEnum> panel = new EnumRadioPanel<>(enumClass);
        TestActionListener listener1 = new TestActionListener();
        panel.addActionListener(listener1);
        TestActionListener listener2 = new TestActionListener();
        panel.addActionListener(listener2);

        panel.notifyActionListeners();

        ActionEvent event1 = listener1.getActionEvent();
        ActionEvent event2 = listener2.getActionEvent();
        assertEquals(event1.getSource(), event2.getSource());
        assertEquals(event1.getID(), event2.getID());
        assertEquals(event1.getActionCommand(), event2.getActionCommand());
    }    // notifyActionListenersSendsObjectWithCorrectCommand()

}    // EnumRadioPanelTest
