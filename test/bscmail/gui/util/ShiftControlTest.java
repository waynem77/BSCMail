/*
 * Copyright © 2017 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui.util;

import bscmail.Role;
import bscmail.Shift;
import bscmail.Volunteer;
import java.util.Arrays;
import java.util.List;
import javax.swing.ComboBoxModel;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ShiftControl}.
 *
 * @author Wayne Miller
 */
public class ShiftControlTest {

    /* constructor */

    /**
     * Tests that
     * {@link ShiftControl#ShiftControl(bscmail.Shift, java.util.List)} throws a
     * {@link NullPointerException} when shift is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenShiftIsNull() {
        Shift shift = null;
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));

        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
    }    // constructorThrowsExceptionWhenShiftIsNull()

    /**
     * Tests that
     * {@link ShiftControl#ShiftControl(bscmail.Shift, java.util.List)} throws a
     * {@link NullPointerException} when volunteers is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenVolunteersIsNull() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = null;

        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
    }    // constructorThrowsExceptionWhenVolunteersIsNull()

    /**
     * Tests that
     * {@link ShiftControl#ShiftControl(bscmail.Shift, java.util.List)} throws a
     * {@link NullPointerException} when volunteers contains null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenVolunteersContainssNull() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                null,
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));

        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
    }    // constructorThrowsExceptionWhenVolunteersContainssNull()

    /**
     * Tests that
     * {@link ShiftControl#ShiftControl(bscmail.Shift, java.util.List)} does not
     * throw an exception when shift is not null and volunteers is not null nor
     * contains any null elements.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenShiftIsNotNullAndVolunteersIsNotNullAndDoesNotContainNull() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));

        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
    }    // constructorDoesNotThrowExceptionWhenShiftIsNotNullAndVolunteersIsNotNullAndDoesNotContainNull()

    /**
     * Tests that
     * {@link ShiftControl#ShiftControl(bscmail.Shift, java.util.List)} sets the
     * volunteer list to the correct size.
     */
    @Test
    public void constructorSetsVolunteerListToCorrectSize() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));

        ShiftControl shiftControl = new ShiftControl(shift, volunteers);

        ComboBoxModel<VolunteerDisplayWrapper> receivedModel = shiftControl.getModel();
        int received = receivedModel.getSize();
        int expected = volunteers.size() + 1;
        assertEquals(expected, received);
    }    // constructorSetsVolunteerListToCorrectSize()

    /**
     * Tests that
     * {@link ShiftControl#ShiftControl(bscmail.Shift, java.util.List)} sets the
     * first entry in the volunteer list to null.
     */
    @Test
    public void constructorSetsFirstEntryInVolunteerListToNull() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));

        ShiftControl shiftControl = new ShiftControl(shift, volunteers);

        ComboBoxModel<VolunteerDisplayWrapper> receivedModel = shiftControl.getModel();
        Volunteer received = receivedModel.getElementAt(0).getVolunteer();
        assertNull(received);
    }    // constructorSetsFirstEntryInVolunteerListToNull()

    /**
     * Tests that
     * {@link ShiftControl#ShiftControl(bscmail.Shift, java.util.List)} sets the
     * entries in the volunteer list, after the first, to those passed to it.
     */
    @Test
    public void constuctorSetsLatterEntriesInVolunteerListCorrectly() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));

        ShiftControl shiftControl = new ShiftControl(shift, volunteers);

        ComboBoxModel<VolunteerDisplayWrapper> receivedModel = shiftControl.getModel();
        for (int i = 0; i < volunteers.size(); ++i) {
            Volunteer received = receivedModel.getElementAt(i + 1).getVolunteer();
            Volunteer expected = volunteers.get(i);
            assertEquals(expected, received);
        }    // for
    }    // constuctorSetsLatterEntriesInVolunteerListCorrectly()

    /* getShift */

    /**
     * Tests that {@link ShiftControl#getShift()} does not throw an exception.
     */
    @Test
    public void getShiftDoesNotThrowException() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);

        shiftControl.getShift();
    }    // getShiftDoesNotThrowException()

    /**
     * Tests that {@link ShiftControl#getShift()} does not return null.
     */
    @Test
    public void getShiftDoesNotReturnNull() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);

        Shift received = shiftControl.getShift();

        assertNotNull(received);
    }    // getShiftDoesNotReturnNull()

    /**
     * Tests that {@link ShiftControl#getShift()} returns the shift passed to
     * the constructor.
     */
    @Test
    public void getShiftReturnsCorrectValue() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);

        Shift received = shiftControl.getShift();

        Shift expected = shift;
        assertEquals(expected, received);
    }    // getShiftReturnsCorrectValue()

    /* getVolunteer */

    /**
     * Tests that {@link ShiftControl#getVolunteer()} does not throw an
     * exception when no volunteer is selected.
     */
    @Test
    public void getVolunteerDoesNotThrowExceptionWhenNoVolunteerIsSelected() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        int selectedIndex = 0;
        shiftControl.setSelectedIndex(selectedIndex);

        shiftControl.getVolunteer();
    }    // getVolunteerDoesNotThrowExceptionWhenNoVolunteerIsSelected()

    /**
     * Tests that {@link ShiftControl#getVolunteer()} does not throw an
     * exception when a volunteer is selected.
     */
    @Test
    public void getVolunteerDoesNotThrowExceptionWhenVolunteerIsSelected() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        int selectedIndex = 1;
        shiftControl.setSelectedIndex(selectedIndex);

        shiftControl.getVolunteer();
    }    // getVolunteerDoesNotThrowExceptionWhenVolunteerIsSelected()

    /**
     * Tests that {@link ShiftControl#getVolunteer()} returns null when no
     * volunteer is selected.
     */
    @Test
    public void getVolunteerReturnsNullWhenNoVolunteerIsSelected() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        int selectedIndex = 0;
        shiftControl.setSelectedIndex(selectedIndex);

        Volunteer received = shiftControl.getVolunteer();

        Volunteer expected = null;
        assertEquals(expected, received);
    }    // getVolunteerReturnsNullWhenNoVolunteerIsSelected()

    /**
     * Tests that {@link ShiftControl#getVolunteer()} does not return null when
     * a volunteer is selected.
     */
    @Test
    public void getVolunteerDoesNotReturnNullWhenVolunteerIsSelected() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        int selectedIndex = 1;
        shiftControl.setSelectedIndex(selectedIndex);

        Volunteer received = shiftControl.getVolunteer();

        assertNotNull(received);
    }    // getVolunteerDoesNotReturnNullWhenVolunteerIsSelected()

    /**
     * Tests that {@link ShiftControl#getVolunteer()} returns the selected
     * volunteer.
     */
    @Test
    public void getVolunteerReturnsTheSelectedVolunteer() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        int selectedIndex = 1;
        shiftControl.setSelectedIndex(selectedIndex);

        Volunteer received = shiftControl.getVolunteer();

        Volunteer expected = volunteers.get(selectedIndex - 1);
    }    // getVolunteerReturnsTheSelectedVolunteer()

    /* setModel */

    /**
     * Tests that {@link ShiftControl#setModel(java.util.List)} throws a
     * {@link NullPointerException} when volunteers is null.
     */
    @Test(expected = NullPointerException.class)
    public void setModelThrowsExceptionWhenVolunteersIsNull() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        volunteers = null;

        shiftControl.setModel(volunteers);
    }    // setModelThrowsExceptionWhenVolunteersIsNull()

    /**
     * Tests that {@link ShiftControl#setModel(java.util.List)} throws a
     * {@link NullPointerException} when volunteers contains null.
     */
    @Test(expected = NullPointerException.class)
    public void setModelThrowsExceptionWhenVolunteersContainsNull() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        volunteers = Arrays.asList(
                new Volunteer("volunteerName3", "volunteerEmail3", "volunteerPhone3", "volunteerNotes3"),
                null,
                new Volunteer("volunteerName5", "volunteerEmail5", "volunteerPhone5", "volunteerNotes5"));

        shiftControl.setModel(volunteers);
    }    // setModelThrowsExceptionWhenVolunteersContainsNull()

    /**
     * Tests that {@link ShiftControl#setModel(java.util.List)} does not throw
     * an exception when volunteers is not null nor contains null.
     */
    @Test
    public void setModelDoesNotThrowExceptionWhenVolunteersIsCorrect() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        volunteers = Arrays.asList(
                new Volunteer("volunteerName3", "volunteerEmail3", "volunteerPhone3", "volunteerNotes3"),
                new Volunteer("volunteerName4", "volunteerEmail4", "volunteerPhone4", "volunteerNotes4"),
                new Volunteer("volunteerName5", "volunteerEmail5", "volunteerPhone5", "volunteerNotes5"));

        shiftControl.setModel(volunteers);
    }    // setModelDoesNotThrowExceptionWhenVolunteersIsCorrect()

    /**
     * Tests that {@link ShiftControl#setModel(java.util.List)} sets the
     * volunteer list to the correct size.
     */
    @Test
    public void setModelSetsVolunteerListToCorrectSize() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        volunteers = Arrays.asList(
                new Volunteer("volunteerName3", "volunteerEmail3", "volunteerPhone3", "volunteerNotes3"),
                new Volunteer("volunteerName4", "volunteerEmail4", "volunteerPhone4", "volunteerNotes4"),
                new Volunteer("volunteerName5", "volunteerEmail5", "volunteerPhone5", "volunteerNotes5"));

        shiftControl.setModel(volunteers);

        ComboBoxModel<VolunteerDisplayWrapper> receivedModel = shiftControl.getModel();
        int received = receivedModel.getSize();
        int expected = volunteers.size() + 1;
        assertEquals(expected, received);
    }    // setModelSetsVolunteerListToCorrectSize()

    /**
     * Tests that {@link ShiftControl#setModel(java.util.List)} sets the first
     * entry in the volunteer list to null.
     */
    @Test
    public void setModelSetsFirstEntryInVolunteerListToNull() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        volunteers = Arrays.asList(
                new Volunteer("volunteerName3", "volunteerEmail3", "volunteerPhone3", "volunteerNotes3"),
                new Volunteer("volunteerName4", "volunteerEmail4", "volunteerPhone4", "volunteerNotes4"),
                new Volunteer("volunteerName5", "volunteerEmail5", "volunteerPhone5", "volunteerNotes5"));

        shiftControl.setModel(volunteers);

        ComboBoxModel<VolunteerDisplayWrapper> receivedModel = shiftControl.getModel();
        Volunteer received = receivedModel.getElementAt(0).getVolunteer();
        assertNull(received);
    }    // setModelSetsFirstEntryInVolunteerListToNull()

    /**
     * Tests that {@link ShiftControl#setModel(java.util.List)} sets the entries
     * in the volunteer list, after the first, to those passed to it.
     */
    @Test
    public void setModelSetsLatterEntriesInVolunteerListCorrectly() {
        Shift shift = new Shift("shiftName", Arrays.<Role>asList(), false, false, false);
        List<Volunteer> volunteers = Arrays.asList(
                new Volunteer("volunteerName1", "volunteerEmail1", "volunteerPhone1", "volunteerNotes1"),
                new Volunteer("volunteerName2", "volunteerEmail2", "volunteerPhone2", "volunteerNotes2"));
        ShiftControl shiftControl = new ShiftControl(shift, volunteers);
        volunteers = Arrays.asList(
                new Volunteer("volunteerName3", "volunteerEmail3", "volunteerPhone3", "volunteerNotes3"),
                new Volunteer("volunteerName4", "volunteerEmail4", "volunteerPhone4", "volunteerNotes4"),
                new Volunteer("volunteerName5", "volunteerEmail5", "volunteerPhone5", "volunteerNotes5"));
        assertTrue("Invalid test", volunteers.size() > 0);

        shiftControl.setModel(volunteers);

        ComboBoxModel<VolunteerDisplayWrapper> receivedModel = shiftControl.getModel();
        for (int i = 0; i < volunteers.size(); ++i) {
            Volunteer received = receivedModel.getElementAt(i + 1).getVolunteer();
            Volunteer expected = volunteers.get(i);
            assertEquals(expected, received);
        }    // for
    }    // setModelSetsLatterEntriesInVolunteerListCorrectly()

}    // ShiftControlTest
