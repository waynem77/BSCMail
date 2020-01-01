/*
 * Copyright © 2019-2020 its authors.  See the file "AUTHORS" for details.
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

import io.github.waynem77.bscmail.persistent.Matchable;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
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

    /**
     * Matchable usable in tests.
     */
    public static class StringMatchable implements Matchable<String> {

        private final String value;

        public StringMatchable(String value) { this.value = value; }

        @Override public boolean matches(String criterion) { return value.contains(criterion); }

        public static Vector asVector(String... elements) {
            Vector<StringMatchable> vector = new Vector<>();
            for (String element : elements) {
                vector.add(new StringMatchable(element));
            }    // for
            return vector;
        }    // asVector()

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof StringMatchable)) {
                return false;
            }
            StringMatchable stringMatchable = (StringMatchable)obj;
            return Objects.equals(value, stringMatchable.value);
        }

        @Override
        public String toString() {
            return value;
        }
    }    // StringMatchable

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
        Vector listData = new Vector(Arrays.asList(new StringMatchable("foo"), null, new StringMatchable("baz")));

        ManagedListControl managedListControl = new ManagedListControl(listData);
    }    // constructorThrowsExceptionWhenListDataContainsNull()

    /**
     * Tests that {@link ManagedListControl#ManagedListControlTest(Vector)} does
     * not throw an exception when listData is not null and does not contain
     * null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenListDataIsNotNullAndDoesNotContainNull() {
        Vector listData = new Vector(Arrays.asList(new StringMatchable("foo"), new StringMatchable("bar"), new StringMatchable("baz")));

        ManagedListControl managedListControl = new ManagedListControl(listData);
    }    // constructorDoesNotThrowExceptionWhenListDataIsNotNullAndDoesNotContainNull()

    /* getListData */

    /**
     * Tests that {@link ManagedListControl#getListData()} does not throw an
     * exception .
     */
    @Test
    public void getListDataDoesNotThrowException() {
        Vector listData = StringMatchable.asVector("foo", "bar", "baz");
        ManagedListControl managedListControl = new ManagedListControl(listData);

        managedListControl.getListData();
    }    // getListDataDoesNotThrowException()

    /**
     * Tests that {@link ManagedListControl#getListData()} initially returns the
     * list data passed to the constructor.
     */
    @Test
    public void getListDataInitiallyReturnsOriginalData() {
        Vector listData = StringMatchable.asVector("foo", "bar", "baz");
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
        Vector listData = StringMatchable.asVector("foo", "bar", "baz");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        listData = StringMatchable.asVector("foo", "bar", "baz", "bazz");
        managedListControl.setListData(listData);
        listData = StringMatchable.asVector("foo", "bar", "baz", "bazz", "bazzz");
        managedListControl.setListData(listData);

        Vector received = managedListControl.getListData();

        Vector expected = listData;
        assertEquals(expected, received);
    }    // getListDataReturnsDataFromLastCallToSetListData()


    /* setFilter */

    /**
     * Tests that {@link ManagedListControl#setFilter(String)} throw a
     * NullPointerException when filter is null.
     */
    @Test(expected = NullPointerException.class)
    public void setFilterThrowsExceptionWhenFilterIsNull() {
        Vector listData = StringMatchable.asVector("foo", "bar", "baz");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        String filter = null;

        managedListControl.setFilter(filter);
    }    // setFilterThrowsExceptionWhenFilterIsNull()

    /**
     * Tests that {@link ManagedListControl#setFilter(String)} does not throw an
     * exception when filter is not null.
     */
    @Test
    public void setFilterDoesNotThrowExceptionWhenFilterIsNotNull() {
        Vector listData = StringMatchable.asVector("foo", "bar", "baz");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        String filter = "smurf";

        managedListControl.setFilter(filter);
    }    // setFilterDoesNotThrowExceptionWhenFilterIsNotNull()

    /* getMatchIndices */

    /**
     * Tests that {@link ManagedListControl#getMatchIndices()} does not throw an
     * exception.
     */
    @Test
    public void getMatchIndicesDoesNotThrowException() {
        Vector listData = StringMatchable.asVector("foo", "bar", "baz", "smurf", "foobar");
        ManagedListControl managedListControl = new ManagedListControl(listData);

        managedListControl.getMatchIndices();
    }    // getMatchIndicesDoesNotThrowException()

    /* getMatchIndices */

    /**
     * Tests that {@link ManagedListControl#getMatchIndices()} returns an empty
     * set when there is no filter.
     */
    @Test
    public void getMatchIndicesReturnsEmptySetWhenThereIsNoFilter() {
        Vector listData = StringMatchable.asVector("foo", "bar", "baz", "smurf", "foobar");
        ManagedListControl managedListControl = new ManagedListControl(listData);

        SortedSet<Integer> received = managedListControl.getMatchIndices();

        assertTrue(received.isEmpty());
    }    // getMatchIndicesReturnsEmptySetWhenThereIsNoFilter()

    /**
     * Tests that {@link ManagedListControl#getMatchIndices()} returns an empty
     * set when there are no matches.
     */
    @Test
    public void getMatchIndicesReturnsEmptySetWhenThereAreNoMatches() {
        Vector listData = StringMatchable.asVector("foo", "bar", "baz", "smurf", "foobar");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        String filter = "xxx";
        managedListControl.setFilter(filter);

        SortedSet<Integer> received = managedListControl.getMatchIndices();

        assertTrue(received.isEmpty());
    }    // getMatchIndicesReturnsEmptySetWhenThereAreNoMatches()

    /**
     * Tests that {@link ManagedListControl#getMatchIndices()} returns the
     * correct value when there are matches.
     */
    @Test
    public void getMatchIndicesReturnsCorrectValueWhenThereAreMatches() {
        Vector listData = StringMatchable.asVector("foo", "bar", "baz", "smurf", "foobar");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        String filter = "ba";
        managedListControl.setFilter(filter);

        SortedSet<Integer> received = managedListControl.getMatchIndices();

        SortedSet<Integer> expected = new TreeSet<Integer>(Arrays.asList(1, 2, 4));
        assertEquals(expected, received);
    }    // getMatchIndicesReturnsCorrectValueWhenThereAreMatches()

    /* getMatches */

    /**
     * Tests that {@link ManagedListControl#getMatches()} does not throw an
     * exception.
     */
    @Test
    public void getMatchesDoesNotThrowException() {
        Vector listData = StringMatchable.asVector("foo", "bar", "baz");
        ManagedListControl managedListControl = new ManagedListControl(listData);

        managedListControl.getMatches();
    }    // getMatchesDoesNotThrowException()

    /* moveSelectionsTo */

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} throws an IndexOutOfBoundsException when index is too
     * low.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void moveSelectionsToThrowsExceptionWhenIndexTooLow() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = -1;
        managedListControl.moveSelectionsTo(index);
    }    // moveSelectionsToThrowsExceptionWhenIndexTooLow()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} throws an IndexOutOfBoundsException when index is too
     * high.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void moveSelectionsToThrowsExceptionWhenIndexTooHigh() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = listData.size() + 1;
        managedListControl.moveSelectionsTo(index);
    }    // moveSelectionsToThrowsExceptionWhenIndexTooHigh()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} does not throw an exception when index is in range.
     */
    @Test
    public void moveSelectionsToDoesNotThrowExceptionWhenIndexInRange() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        for (int index = 0; index <= listData.size(); ++index) {
            ManagedListControl managedListControl = new ManagedListControl(listData);
            int[] initialSelectedIndices = {2, 3, 5};
            managedListControl.setSelectedIndices(initialSelectedIndices);

            managedListControl.moveSelectionsTo(index);
        }
    }    // moveSelectionsToDoesNotThrowExceptionWhenIndexInRange()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} moves elements correctly when index is lower than any
     * selected indices.
     */
    @Test
    public void moveSelectionsToMovesElementsCorrectlyWhenIndexIsLow() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = 0;
        managedListControl.moveSelectionsTo(index);

        Vector received = managedListControl.getListData();
        Vector expected = StringMatchable.asVector("C", "D", "F", "A", "B", "E", "G");
        assertEquals(expected, received);
    }    // moveSelectionsToMovesElementsCorrectlyWhenIndexIsLow()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} retains selections correctly when index is lower than
     * any selected indices.
     */
    @Test
    public void moveSelectionsToRetainsSelectionsCorrectlyWhenIndexIsLow() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = 0;
        managedListControl.moveSelectionsTo(index);

        int[] received = managedListControl.getSelectedIndices();
        int[] expected = {0, 1, 2};
        assertArrayEquals(expected, received);
    }    // moveSelectionsToRetainsSelectionsCorrectlyWhenIndexIsLow()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} moves elements correctly when index is within the
     * selected indices.
     */
    @Test
    public void moveSelectionsToMovesElementsCorrectlyWhenIndexIsWithinSelection() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = 3;
        managedListControl.moveSelectionsTo(index);

        Vector received = managedListControl.getListData();
        List expected = StringMatchable.asVector("A", "B", "C", "D", "F", "E", "G");
        assertEquals(expected, received);
    }    // moveSelectionsToMovesElementsCorrectlyWhenIndexIsWithinSelection()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} retains selections correctly when index is within the
     * selected indices.
     */
    @Test
    public void moveSelectionsToRetainsSelectionsCorrectlyWhenIndexIsWithin() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = 3;
        managedListControl.moveSelectionsTo(index);

        int[] received = managedListControl.getSelectedIndices();
        int[] expected = {2, 3, 4};
        assertArrayEquals(expected, received);
    }    // moveSelectionsToRetainsSelectionsCorrectlyWhenIndexIsWithin()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} moves elements correctly when index is higher than
     * any selected indices.
     */
    @Test
    public void moveSelectionsToMovesElementsCorrectlyWhenIndexIsHigh() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = 6;
        managedListControl.moveSelectionsTo(index);

        Vector received = managedListControl.getListData();
        List expected = StringMatchable.asVector("A", "B", "E", "C", "D", "F", "G");
        assertEquals(expected, received);
    }    // moveSelectionsToMovesElementsCorrectlyWhenIndexIsHigh()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} retains selections correctly when index is higher
     * than any selected indices.
     */
    @Test
    public void moveSelectionsToRetainsSelectionsCorrectlyWhenIndexIsHigh() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = 6;
        managedListControl.moveSelectionsTo(index);

        int[] received = managedListControl.getSelectedIndices();
        int[] expected = {3, 4, 5};
        assertArrayEquals(expected, received);
    }    // moveSelectionsToRetainsSelectionsCorrectlyWhenIndexIsHigh()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} moves elements correctly when index is the maximum.
     */
    @Test
    public void moveSelectionsToMovesElementsCorrectlyWhenIndexIsMax() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = 7;
        managedListControl.moveSelectionsTo(index);

        Vector received = managedListControl.getListData();
        List expected = StringMatchable.asVector("A", "B", "E", "G", "C", "D", "F");
        assertEquals(expected, received);
    }    // moveSelectionsToMovesElementsCorrectlyWhenIndexIsMax()

    /**
     * Tests that {@link ManagedListControl#moveSelectionsTo(int)} retains selections correctly when index is the
     * maximum.
     */
    @Test
    public void moveSelectionsToRetainsSelectionsCorrectlyWhenIndexIsMax() {
        Vector listData = StringMatchable.asVector("A", "B", "C", "D", "E", "F", "G");
        ManagedListControl managedListControl = new ManagedListControl(listData);
        int[] initialSelectedIndices = {2, 3, 5};
        managedListControl.setSelectedIndices(initialSelectedIndices);

        int index = 7;
        managedListControl.moveSelectionsTo(index);

        int[] received = managedListControl.getSelectedIndices();
        int[] expected = {4, 5, 6};
        assertArrayEquals(expected, received);
    }    // moveSelectionsToRetainsSelectionsCorrectlyWhenIndexIsMax()

}
