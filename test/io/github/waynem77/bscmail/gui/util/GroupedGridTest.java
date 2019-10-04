/*
 * Copyright © 2017-2019 its authors.  See the file "AUTHORS" for details.
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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for
 * @author Wayne Miller
 */
public class GroupedGridTest {

    /* constructor */

    /**
     * Tests that {@link GroupedGrid#GroupedGrid(int)} throws an
     * {@link IllegalArgumentException} when groups is negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWhenGroupsIsNegative() {
        int groups = -1;

        GroupedGrid groupedGrid = new GroupedGrid(groups);
    }    // constructorThrowsExceptionWhenGroupsIsNegative()

    /**
     * Tests that {@link GroupedGrid#GroupedGrid(int)} throws an
     * {@link IllegalArgumentException} when groups is zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWhenGroupsIsZero() {
        int groups = 0;

        GroupedGrid groupedGrid = new GroupedGrid(groups);
    }    // constructorThrowsExceptionWhenGroupsIsZero()

    /**
     * Tests that {@link GroupedGrid#GroupedGrid(int)} does not throw an
     * exception when groups is one.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenGroupsIsOne() {
        int groups = 1;

        GroupedGrid groupedGrid = new GroupedGrid(groups);
    }    // constructorDoesNotThrowExceptionWhenGroupsIsOne()

    /**
     * Tests that {@link GroupedGrid#GroupedGrid(int)} does not throw an
     * exception when groups is greater than one.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenGroupsIsGreaterThanOne() {
        int groups = 2;

        GroupedGrid groupedGrid = new GroupedGrid(groups);
    }    // constructorDoesNotThrowExceptionWhenGroupsIsGreaterThanOne()

    /* setComponents */

    /**
     * Tests that {@link GroupedGrid#setComponents(java.util.List, int)} throws
     * an {@link NullPointerException} when components is null.
     */
    @Test(expected = NullPointerException.class)
    public void setComponentsThrowsExceptionWhenComponentsIsNull() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        List<LabeledComponent> components = null;
        int group = 0;

        groupedGrid.setComponents(components, group);
    }    // setComponentsThrowsExceptionWhenComponentsIsNull()

    /**
     * Tests that {@link GroupedGrid#setComponents(java.util.List, int)} throws
     * an {@link NullPointerException} when components contains null.
     */
    @Test(expected = NullPointerException.class)
    public void setComponentsThrowsExceptionWhenComponentsContainsNull() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        List<LabeledComponent> components = Arrays.asList(
                new LabeledComponent("Foo", new JComboBox()),
                null,
                new LabeledComponent("Bar", new JTextField()));
        int group = 0;

        groupedGrid.setComponents(components, group);
    }    // setComponentsThrowsExceptionWhenComponentsContainsNull()

    /**
     * Tests that {@link GroupedGrid#setComponents(java.util.List, int)} throws
     * an {@link IndexOutOfBoundsException} when group is less than 0.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void setComponentsThrowsExceptionWhenGroupIsNegative() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        List<LabeledComponent> components = Arrays.asList(
                new LabeledComponent("Foo", new JComboBox()),
                new LabeledComponent("Bar", new JTextField()));
        int group = -1;

        groupedGrid.setComponents(components, group);
    }    // setComponentsThrowsExceptionWhenGroupIsNegative()

    /**
     * Tests that {@link GroupedGrid#setComponents(java.util.List, int)} does
     * not throw an exception when group is 0.
     */
    @Test
    public void setComponentsDoesNotThrowExceptionWhenGroupIsZero() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        List<LabeledComponent> components = Arrays.asList(
                new LabeledComponent("Foo", new JComboBox()),
                new LabeledComponent("Bar", new JTextField()));
        int group = 0;

        groupedGrid.setComponents(components, group);
    }    // setComponentsDoesNotThrowExceptionWhenGroupIsZero()

    /**
     * Tests that {@link GroupedGrid#setComponents(java.util.List, int)} does
     * not throw an exception when group is one less than the number of groups.
     */
    @Test
    public void setComponentsDoesNotThrowExceptionWhenGroupEqualsGroupsMinusOne() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        List<LabeledComponent> components = Arrays.asList(
                new LabeledComponent("Foo", new JComboBox()),
                new LabeledComponent("Bar", new JTextField()));
        int group = groups - 1;

        groupedGrid.setComponents(components, group);
    }    // setComponentsDoesNotThrowExceptionWhenGroupEqualsGroupsMinusOne()

    /**
     * Tests that {@link GroupedGrid#setComponents(java.util.List, int)} throws
     * an {@link IndexOutOfBoundsException} when group is equal to the number of
     * groups.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void setComponentsThrowsExceptionWhenGroupEqualsGroups() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        List<LabeledComponent> components = Arrays.asList(
                new LabeledComponent("Foo", new JComboBox()),
                new LabeledComponent("Bar", new JTextField()));
        int group = groups;

        groupedGrid.setComponents(components, group);
    }    // setComponentsThrowsExceptionWhenGroupEqualsGroups()

    /**
     * Tests that {@link GroupedGrid#setComponents(java.util.List, int)}
     * overwrites any existing components.
     */
    @Test
    public void setComponentsOverwritesExistingComponents() {
        List<List<LabeledComponent>> componentGroups = Arrays.asList(
                Arrays.asList(
                        new LabeledComponent("Zero", new JComboBox()),
                        new LabeledComponent("Zero", new JTextField())),
                Arrays.asList(
                        new LabeledComponent("One", new JComboBox()),
                        new LabeledComponent("One", new JTextField())),
                Arrays.asList(
                        new LabeledComponent("Two", new JComboBox()),
                        new LabeledComponent("Two", new JTextField())));
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        int group = 1;
        for (List<LabeledComponent> components : componentGroups) {
            groupedGrid.setComponents(components, group);
        }    // for

        List<Component> received = groupedGrid.getComponents(group);

        List<Component> expected = new LinkedList<>();
        for (LabeledComponent labeledComponent : componentGroups.get(componentGroups.size() - 1)) {
            expected.add(labeledComponent.getComponent());
        }    // for
        assertEquals(expected, received);
    }    // setComponentsOverwritesExistingComponents()

    /* getComponents */

    /**
     * Tests that {@link GroupedGrid#getComponents(int)} throws an
     * {@link IndexOutOfBoundsException} when group is less than 0.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getComponentsThrowsExceptionWhenGroupIsNegative() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        int group = -1;

        groupedGrid.getComponents(group);
    }    // getComponentsThrowsExceptionWhenGroupIsNegative()

    /**
     * Tests that {@link GroupedGrid#getComponents(int)} does not throw an
     * exception when group is 0.
     */
    @Test
    public void getComponentsThrowsExceptionWhenGroupIsZero() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        int group = 0;

        groupedGrid.getComponents(group);
    }    // getComponentsThrowsExceptionWhenGroupIsZero()

    /**
     * Tests that {@link GroupedGrid#getComponents(int)} does not throw an
     * exception when group is one less than the number of groups.
     */
    @Test
    public void getComponentsThrowsExceptionWhenGroupEqualsGroupsMinusOne() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        int group = groups - 1;

        groupedGrid.getComponents(group);
    }    // getComponentsThrowsExceptionWhenGroupEqualsGroupsMinusOne()

    /**
     * Tests that {@link GroupedGrid#getComponents(int)} throws an
     * {@link IndexOutOfBoundsException} when group is equal to the number of
     * groups.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getComponentsThrowsExceptionWhenGroupEqualsGroups() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        int group = groups;

        groupedGrid.getComponents(group);
    }    // getComponentsThrowsExceptionWhenGroupEqualsGroups()

    /**
     * Tests that {@link GroupedGrid#getComponents(int)} does not return null.
     */
    @Test
    public void getComponentsDoesNotReturnNull() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        int group = 1;

        List<Component> received = groupedGrid.getComponents(group);

        assertNotNull(received);
    }    // getComponentsDoesNotReturnNull()

    /**
     * Tests that {@link GroupedGrid#getComponents(int)} returns an empty list
     * if {@link GroupedGrid#setComponents(java.util.List, int)} has not been
     * called.
     */
    @Test
    public void getComponentsReturnsEmptyListIfSetComponentsHasNotBeenCalled() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        int group = 1;

        List<Component> received = groupedGrid.getComponents(group);

        List<Component> expected = Collections.emptyList();
        assertEquals(expected, received);
    }    // getComponentsReturnsEmptyListIfSetComponentsHasNotBeenCalled()

    /**
     * Tests that {@link GroupedGrid#getComponents(int)} returns a list of the
     * components passed to
     * {@link GroupedGrid#setComponents(java.util.List, int)}.
     */
    @Test
    public void getComponentsReturnsComponentsPassedToSetComponents() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        int group = 1;
        List<LabeledComponent> components = Arrays.asList(
                new LabeledComponent("Foo", new JComboBox()),
                new LabeledComponent("Bar", new JTextField()));
        groupedGrid.setComponents(components, group);

        List<Component> received = groupedGrid.getComponents(group);

        List<Component> expected = new LinkedList<>();
        for (LabeledComponent labeledComponent : components) {
            expected.add(labeledComponent.getComponent());
        }    // for
        assertEquals(expected, received);
    }    // getComponentsReturnsComponentsPassedToSetComponents()

    /**
     * Tests that {@link GroupedGrid#getComponents(int)} returns the correct
     * list of components.
     */
    @Test
    public void getComponentsReturnsCorrectListOfComponents() {
        List<List<LabeledComponent>> componentGroups = Arrays.asList(
                Arrays.asList(
                        new LabeledComponent("Zero", new JComboBox()),
                        new LabeledComponent("Zero", new JTextField())),
                Arrays.asList(
                        new LabeledComponent("One", new JComboBox()),
                        new LabeledComponent("One", new JTextField())),
                Arrays.asList(
                        new LabeledComponent("Two", new JComboBox()),
                        new LabeledComponent("Two", new JTextField())));
        int groups = componentGroups.size();
        GroupedGrid groupedGrid = new GroupedGrid(groups);
        for (int i = 0; i < groups; ++i) {
            groupedGrid.setComponents(componentGroups.get(i), i);
        }    // for
        int group = 1;

        List<Component> received = groupedGrid.getComponents(group);

        List<Component> expected = new LinkedList<>();
        for (LabeledComponent labeledComponent : componentGroups.get(group)) {
            expected.add(labeledComponent.getComponent());
        }    // for
        assertEquals(expected, received);
    }    // getComponentsReturnsCorrectListOfComponents()

    /* getNumberOfGroups */

    /**
     * Tests that {@link GroupedGrid#getNumberOfGroups()} does not throw an
     * exception.
     */
    @Test
    public void getNumberOfGroupsDoesNotThrowException() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);

        groupedGrid.getNumberOfGroups();
    }    // getNumberOfGroupsDoesNotThrowException()

    /**
     * Tests that {@link GroupedGrid#getNumberOfGroups()} returns the number of
     * groups.
     */
    @Test
    public void getNumberOfGroupsReturnsCorrectValue() {
        int groups = 3;
        GroupedGrid groupedGrid = new GroupedGrid(groups);

        int received = groupedGrid.getNumberOfGroups();

        int expected = groups;
        assertEquals(expected, received);
    }    // getNumberOfGroupsReturnsCorrectValue()

}    // GroupedGridTest
