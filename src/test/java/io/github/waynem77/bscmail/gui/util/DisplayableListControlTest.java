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

import java.util.Arrays;
import java.util.List;
import javax.swing.ListSelectionModel;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link DisplayableListControl}.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 */
public class DisplayableListControlTest {

    /* constructor */

    /**
     * Tests that {@link DisplayableListControl#DisplayableListControl()} does
     * not throw an exception.
     */
    @Test
    public void constructorDoesNotThrowException() {
        DisplayableListControl listControl = new DisplayableListControl();
    }    // constructorDoesNotThrowException()

    /* setListData */

    /**
     * Tests that {@link DisplayableListControl#setListData(List)} throws a
     * NullPointerException when data is null.
     */
    @Test(expected = NullPointerException.class)
    public void setListDataThrowsExceptionWhenDataIsNull() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = null;

        listControl.setListData(data);
    }    // setListDataThrowsExceptionWhenDataIsNull()

    /**
     * Tests that {@link DisplayableListControl#setListData(List)} throws a
     * NullPointerException when data contains null.
     */
    @Test(expected = NullPointerException.class)
    public void setListDataThrowsExceptionWhenDataContainsNull() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = Arrays.asList("foo", null, 3.0);

        listControl.setListData(data);
    }    // setListDataThrowsExceptionWhenDataContainsNull()

    /**
     * Tests that {@link DisplayableListControl#setListData(List)} does not
     * throw an exception when data is valid.
     */
    @Test
    public void setListDataDoesNotThrowExceptionWhenDataIsValid() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = Arrays.asList("foo", 2, 3.0);

        listControl.setListData(data);
    }    // setListDataDoesNotThrowExceptionWhenDataIsValid()

    /* setSelectionMode */

    /**
     * Tests that {@link DisplayableListControl#setSelectionMode(int)} does not
     * throw an exception when selectionMode is valid.
     */
    @Test
    public void setSelectionModeDoesNotThrowExceptionWhenSelectionModeIsValid() {
        for (int selectionMode : Arrays.asList(
                ListSelectionModel.SINGLE_SELECTION,
                ListSelectionModel.SINGLE_INTERVAL_SELECTION,
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)) {
            DisplayableListControl listControl = new DisplayableListControl();
            List data = Arrays.asList("foo", 2, 3.0);
            listControl.setListData(data);

            listControl.setSelectionMode(selectionMode);
        }    // for
    }    // setSelectionModeDoesNotThrowExceptionWhenSelectionModeIsValid()

    /**
     * Tests that {@link DisplayableListControl#setSelectionMode(int)} throws an
     * IllegalArgumentException when selectionMode is invalid.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setSelectionModeThrowsExceptionWhenSelectionModeIsInvalid() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = Arrays.asList("foo", 2, 3.0);
        listControl.setListData(data);

        int selectionMode = 1;
        while (Arrays.asList(
                ListSelectionModel.SINGLE_SELECTION,
                ListSelectionModel.SINGLE_INTERVAL_SELECTION,
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION).contains(selectionMode)) {
            ++selectionMode;
        }    // while

        listControl.setSelectionMode(selectionMode);
    }    // setSelectionModeThrowsExceptionWhenSelectionModeIsInvalid()

    /* getSelectedIndices / setSelectedIndices */

    /**
     * Tests that {@link DisplayableListControl#setSelectedIndices(int[])}
     * throws a NullPointerException when indices is null.
     */
    @Test(expected = NullPointerException.class)
    public void setSelectedIndicesThrowsExceptionWhenIndicesIsNull() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = Arrays.asList("foo", 2, 3.0);
        listControl.setListData(data);
        int selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        listControl.setSelectionMode(selectionMode);

        int[] indices = null;
        listControl.setSelectedIndices(indices);
    }    // setSelectedIndicesThrowsExceptionWhenIndicesIsNull()

    /**
     * Tests that {@link DisplayableListControl#setSelectedIndices(int[])}
     * does not throw an exception when indices is not null.
     */
    @Test
    public void setSelectedIndicesDoesNotThrowExceptionWhenIndicesIsNotNull() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = Arrays.asList("foo", 2, 3.0);
        listControl.setListData(data);
        int selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        listControl.setSelectionMode(selectionMode);

        int[] indices = {0, 2};
        listControl.setSelectedIndices(indices);
    }    // setSelectedIndicesDoesNotThrowExceptionWhenIndicesIsNotNull()

    /**
     * Tests that {@link DisplayableListControl#getSelectedIndices()} does not
     * throw an exception.
     */
    @Test
    public void getSelectedIndicesDoesNotThrowException() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = Arrays.asList("foo", 2, 3.0);
        listControl.setListData(data);
        int selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        listControl.setSelectionMode(selectionMode);

        listControl.getSelectedIndices();
    }    // getSelectedIndicesDoesNotThrowException()

    /**
     * Tests that {@link DisplayableListControl#getSelectedIndices()} returns
     * the selected indices.
     */
    @Test
    public void getSelectedIndicesReturnsCorrectValue() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = Arrays.asList("foo", 2, 3.0);
        listControl.setListData(data);
        int selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        listControl.setSelectionMode(selectionMode);
        int[] indices = {0, 2};
        listControl.setSelectedIndices(indices);

        int[] received = listControl.getSelectedIndices();

        int[] expected = indices;
        assertArrayEquals(expected, received);
    }    // getSelectedIndicesReturnsCorrectValue()

    /**
     * Tests that {@link DisplayableListControl#getSelectedIndices()} returns
     * an empty array when no indices have been set.
     */
    @Test
    public void getSelectedIndicesReturnsEmptyArrayWhenNoIndicesHaveBeenSet() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = Arrays.asList("foo", 2, 3.0);
        listControl.setListData(data);
        int selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        listControl.setSelectionMode(selectionMode);

        int[] received = listControl.getSelectedIndices();

        int[] expected = { };
        assertArrayEquals(expected, received);
    }    // getSelectedIndicesReturnsEmptyArrayWhenNoIndicesHaveBeenSet()

    /* setDisplayOnly */

    /**
     * Tests that {@link DisplayableListControl#setDisplayOnly(boolean)} does
     * not throw an exception when there is no data.
     */
    @Test
    public void setDisplayOnlyDoesNotThrowExceptionWhenThereIsNoData() {
        DisplayableListControl listControl = new DisplayableListControl();

        for (boolean displayOnly : Arrays.asList(true, false)) {
            listControl.setDisplayOnly(displayOnly);
        }    // for
    }    // setDisplayOnlyDoesNotThrowExceptionWhenThereIsNoData()

    /**
     * Tests that {@link DisplayableListControl#setDisplayOnly(boolean)} does
     * not throw an exception when there is data.
     */
    @Test
    public void setDisplayOnlyDoesNotThrowExceptionWhenThereIsData() {
        DisplayableListControl listControl = new DisplayableListControl();
        List data = Arrays.asList("foo", 2, 3.0);
        listControl.setListData(data);
        int selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        listControl.setSelectionMode(selectionMode);
        int[] indices = {0, 2};
        listControl.setSelectedIndices(indices);

        for (boolean displayOnly : Arrays.asList(true, false)) {
            listControl.setDisplayOnly(displayOnly);
        }    // for
    }    // setDisplayOnlyDoesNotThrowExceptionWhenThereIsData()

}    // DisplayableListControlTest
