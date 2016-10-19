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

package bscmail;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Event}.
 * 
 * @author Wayne Miller
 */
public class EventTest {

    /**
     * Variable used to hold the event being tested.
     */
    Event event;
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Event");
        System.out.println("=====");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass(

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        event = new Event();
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        event = null;
    }    // tearDown()
    
    /* constructor */
    
    /**
     * Tests that {@link Event#Event()} does not throw an exception.
     */
    @Test
    public void testConstructor() {
        System.out.println("constructor");

        event = new Event();
    }    // testConstructor()

    /* hasDate / getDate / setDate */
    
    /**
     * Tests that {@link Event#hasDate()} does not throw an exception.
     */
    @Test
    public void testHasDateNoException() {
        System.out.println("hasDate - no exception");
        
        event.hasDate();
    }    // testHasDateNoException()
    
    /**
     * Tests that {@link Event#hasDate()} returns false when called before
     * {@link Event#setDate(Date)}.
     */
    @Test
    public void testHasDateBeforeSetDate() {
        System.out.println("hasDate - before setDate");
        
        boolean expected = false;
        boolean received = event.hasDate();
        assertEquals(expected, received);
    }    // testHasDateBeforeSetDate()
    
    /**
     * Tests that {@link Event#hasDate()} returns true when called after
     * {@link Event#setDate(Date)}.
     */
    @Test
    public void testHasDateAfterSetDate() {
        System.out.println("hasDate - after setDate");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        boolean expected = true;
        boolean received = event.hasDate();
        assertEquals(expected, received);
    }    // testHasDateAfterSetDate()
    
    /**
     * Tests that {@link Event#hasDate()} returns false when called after
     * {@link Event#setDate(Date)}, called with null.
     */
    @Test
    public void testHasDateAfterSetDateNull() {
        System.out.println("hasDate - after setDate, null");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        date = null;
        event.setDate(date);
        boolean expected = false;
        boolean received = event.hasDate();
        assertEquals(expected, received);
    }    // testHasDateAfterSetDateNull()
    
    /**
     * Tests that {@link Event#getDate()} does not throw an exception.
     */
    @Test
    public void testGetDateNoException() {
        System.out.println("getDate - no exception");
        
        event.getDate();
    }    // testGetDateNoException()
    
    /**
     * Tests that {@link Event#getDate()} returns null when called before
     * {@link Event#setDate(Date)}.
     */
    @Test
    public void testGetDateBeforeSetDate() {
        System.out.println("getDate - before setDate");
        
        Date expected = null;
        Date received = event.getDate();
        assertEquals(expected, received);
    }    // testGetDateBeforeSetDate()
    
    /**
     * Tests that {@link Event#getDate()} returns the correct value when
     * called after {@link Event#setDate(Date)}.
     */
    @Test
    public void testGetDateAfterSetDate() {
        System.out.println("getDate - after setDate");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        Date expected = date;
        Date received = event.getDate();
        assertEquals(expected, received);
    }    // testGetDateAfterSetDate()
    
    /**
     * Tests that {@link Event#getDate()} returns null when called after
     * {@link Event#setDate(Date)}, called with null.
     */
    @Test
    public void testGetDateAfterSetDateNull() {
        System.out.println("getDate - after setDate, null");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        date = null;
        event.setDate(date);
        Date expected = null;
        Date received = event.getDate();
        assertEquals(expected, received);
    }    // testGetDateAfterSetDateNull()
    
    /**
     * Tests that {@link Event#setDate(Date)} does not throw an exception.
     */
    @Test
    public void testSetDateNoException() {
        System.out.println("setDate - no exception");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
    }    // testSetDateNoException()
    
    /**
     * Tests that {@link Event#setDate(Date)} does not throw an exception
     * when the parameter is null, for an event with no date assigned.
     */
    @Test
    public void testSetDateNullNoDateNoException() {
        System.out.println("setDate - null, no previous date, no exception");
        
        Date date = null;
        event.setDate(date);
    }    // testSetDateNullNoDateNoException()
    
    /**
     * Tests that {@link Event#setDate(Date)} does not throw an exception
     * when the parameter is null.
     */
    @Test
    public void testSetDateNullNoException() {
        System.out.println("setDate - null, no exception");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        date = null;
        event.setDate(date);
    }    // testSetDateNullNoException()

    /* getBand / setBand */
    
    /**
     * Tests that {@link Event#getBand()} does not throw an exception.
     */
    @Test
    public void testGetBandNoException() {
        System.out.println("getBand - no exception");

        event.getBand();
    }    // testGetBandNoException()

    /**
     * Tests that {@link Event#getBand()} returns an empty string when called
     * before {@link Event#setBand(String)}.
     */
    @Test
    public void testGetBandBeforeSetBand() {
        System.out.println("getBand - before setBand");

        String expected = "";
        String received = event.getBand();
        assertEquals(expected, received);
    }    // testGetBandNoException()

    /**
     * Tests that (@link Event#getBand()} returns the correct value when called
     * after {@link Event#setBand(String)}.
     */
    @Test
    public void testGetBandAfterSetBand() {
        System.out.println("getBand - after setBand");

        String band = "foo";
        event.setBand(band);
        String expected = band;
        String received = event.getBand();
        assertEquals(expected, received);
    }    // testGetBandAfterSetBand()

    /**
     * Tests that (@link Event#getBand()} returns the correct value when called
     * after {@link Event#setBand(String)}, called with null.
     */
    @Test
    public void testGetBandAfterSetBandNull() {
        System.out.println("getBand - after setBand(null)");

        String band = null;
        event.setBand(band);
        String expected = "";
        String received = event.getBand();
        assertEquals(expected, received);
    }    // testGetBandAfterSetBandNull()
    
    /**
     * Tests that {@link Event#setBand(String)} does not throw an exception.
     */
    @Test
    public void testSetBandNoException() {
        System.out.println("setBand - no exception");
        
        String band = "foo";
        event.setBand(band);
    }    // testSetBandNoException()
    
    /**
     * Tests that {@link Event#setBand(String)} does not throw an exception
     * when the parameter is null.
     */
    @Test
    public void testSetBandNullNoException() {
        System.out.println("setBand - null, no exception");
        
        String band = null;
        event.setBand(band);
    }    // testSetBandNullNoException()

    /* getInstructors / setInstructors */
    
    /**
     * Tests that {@link Event#getInstructors()} does not throw an exception.
     */
    @Test
    public void testGetInstructorsNoException() {
        System.out.println("getInstructors - no exception");

        event.getInstructors();
    }    // testGetInstructorsNoException()

    /**
     * Tests that {@link Event#getInstructors()} returns an empty string when called
     * before {@link Event#setInstructors(String)}.
     */
    @Test
    public void testGetInstructorsBeforeSetInstructors() {
        System.out.println("getInstructors - before setInstructors");

        String expected = "";
        String received = event.getInstructors();
        assertEquals(expected, received);
    }    // testGetInstructorsNoException()

    /**
     * Tests that (@link Event#getInstructors()} returns the correct value when called
     * after {@link Event#setInstructors(String)}.
     */
    @Test
    public void testGetInstructorsAfterSetInstructors() {
        System.out.println("getInstructors - after setInstructors");

        String instructors = "foo";
        event.setInstructors(instructors);
        String expected = instructors;
        String received = event.getInstructors();
        assertEquals(expected, received);
    }    // testGetInstructorsAfterSetInstructors()

    /**
     * Tests that (@link Event#getInstructors()} returns the correct value when called
     * after {@link Event#setInstructors(String)}, called with null.
     */
    @Test
    public void testGetInstructorsAfterSetInstructorsNull() {
        System.out.println("getInstructors - after setInstructors(null)");

        String instructors = null;
        event.setInstructors(instructors);
        String expected = "";
        String received = event.getInstructors();
        assertEquals(expected, received);
    }    // testGetInstructorsAfterSetInstructorsNull()
    
    /**
     * Tests that {@link Event#setInstructors(String)} does not throw an exception.
     */
    @Test
    public void testSetInstructorsNoException() {
        System.out.println("setInstructors - no exception");
        
        String instructors = "foo";
        event.setInstructors(instructors);
    }    // testSetInstructorsNoException()
    
    /**
     * Tests that {@link Event#setInstructors(String)} does not throw an exception
     * when the parameter is null.
     */
    @Test
    public void testSetInstructorsNullNoException() {
        System.out.println("setInstructors - null, no exception");
        
        String instructors = null;
        event.setInstructors(instructors);
    }    // testSetInstructorsNullNoException()

    /* getGeneralPrice / setGeneralPrice */
    
    /**
     * Tests that {@link Event#getGeneralPrice()} does not throw an exception.
     */
    @Test
    public void testGetGeneralPriceNoException() {
        System.out.println("getGeneralPrice - no exception");

        event.getGeneralPrice();
    }    // testGetGeneralPriceNoException()

    /**
     * Tests that {@link Event#getGeneralPrice()} returns an empty string when called
     * before {@link Event#setGeneralPrice(String)}.
     */
    @Test
    public void testGetGeneralPriceBeforeSetGeneralPrice() {
        System.out.println("getGeneralPrice - before setGeneralPrice");

        String expected = "";
        String received = event.getGeneralPrice();
        assertEquals(expected, received);
    }    // testGetGeneralPriceNoException()

    /**
     * Tests that (@link Event#getGeneralPrice()} returns the correct value when called
     * after {@link Event#setGeneralPrice(String)}.
     */
    @Test
    public void testGetGeneralPriceAfterSetGeneralPrice() {
        System.out.println("getGeneralPrice - after setGeneralPrice");

        String generalPrice = "foo";
        event.setGeneralPrice(generalPrice);
        String expected = generalPrice;
        String received = event.getGeneralPrice();
        assertEquals(expected, received);
    }    // testGetGeneralPriceAfterSetGeneralPrice()

    /**
     * Tests that (@link Event#getGeneralPrice()} returns the correct value when called
     * after {@link Event#setGeneralPrice(String)}, called with null.
     */
    @Test
    public void testGetGeneralPriceAfterSetGeneralPriceNull() {
        System.out.println("getGeneralPrice - after setGeneralPrice(null)");

        String generalPrice = null;
        event.setGeneralPrice(generalPrice);
        String expected = "";
        String received = event.getGeneralPrice();
        assertEquals(expected, received);
    }    // testGetGeneralPriceAfterSetGeneralPriceNull()
    
    /**
     * Tests that {@link Event#setGeneralPrice(String)} does not throw an exception.
     */
    @Test
    public void testSetGeneralPriceNoException() {
        System.out.println("setGeneralPrice - no exception");
        
        String generalPrice = "foo";
        event.setGeneralPrice(generalPrice);
    }    // testSetGeneralPriceNoException()
    
    /**
     * Tests that {@link Event#setGeneralPrice(String)} does not throw an exception
     * when the parameter is null.
     */
    @Test
    public void testSetGeneralPriceNullNoException() {
        System.out.println("setGeneralPrice - null, no exception");
        
        String generalPrice = null;
        event.setGeneralPrice(generalPrice);
    }    // testSetGeneralPriceNullNoException()

    /* getStudentPrice / setStudentPrice */
    
    /**
     * Tests that {@link Event#getStudentPrice()} does not throw an exception.
     */
    @Test
    public void testGetStudentPriceNoException() {
        System.out.println("getStudentPrice - no exception");

        event.getStudentPrice();
    }    // testGetStudentPriceNoException()

    /**
     * Tests that {@link Event#getStudentPrice()} returns an empty string when called
     * before {@link Event#setStudentPrice(String)}.
     */
    @Test
    public void testGetStudentPriceBeforeSetStudentPrice() {
        System.out.println("getStudentPrice - before setStudentPrice");

        String expected = "";
        String received = event.getStudentPrice();
        assertEquals(expected, received);
    }    // testGetStudentPriceNoException()

    /**
     * Tests that (@link Event#getStudentPrice()} returns the correct value when called
     * after {@link Event#setStudentPrice(String)}.
     */
    @Test
    public void testGetStudentPriceAfterSetStudentPrice() {
        System.out.println("getStudentPrice - after setStudentPrice");

        String studentPrice = "foo";
        event.setStudentPrice(studentPrice);
        String expected = studentPrice;
        String received = event.getStudentPrice();
        assertEquals(expected, received);
    }    // testGetStudentPriceAfterSetStudentPrice()

    /**
     * Tests that (@link Event#getStudentPrice()} returns the correct value when called
     * after {@link Event#setStudentPrice(String)}, called with null.
     */
    @Test
    public void testGetStudentPriceAfterSetStudentPriceNull() {
        System.out.println("getStudentPrice - after setStudentPrice(null)");

        String studentPrice = null;
        event.setStudentPrice(studentPrice);
        String expected = "";
        String received = event.getStudentPrice();
        assertEquals(expected, received);
    }    // testGetStudentPriceAfterSetStudentPriceNull()
    
    /**
     * Tests that {@link Event#setStudentPrice(String)} does not throw an exception.
     */
    @Test
    public void testSetStudentPriceNoException() {
        System.out.println("setStudentPrice - no exception");
        
        String studentPrice = "foo";
        event.setStudentPrice(studentPrice);
    }    // testSetStudentPriceNoException()
    
    /**
     * Tests that {@link Event#setStudentPrice(String)} does not throw an exception
     * when the parameter is null.
     */
    @Test
    public void testSetStudentPriceNullNoException() {
        System.out.println("setStudentPrice - null, no exception");
        
        String studentPrice = null;
        event.setStudentPrice(studentPrice);
    }    // testSetStudentPriceNullNoException()

    /* getDiscountPrice / setDiscountPrice */
    
    /**
     * Tests that {@link Event#getDiscountPrice()} does not throw an exception.
     */
    @Test
    public void testGetDiscountPriceNoException() {
        System.out.println("getDiscountPrice - no exception");

        event.getDiscountPrice();
    }    // testGetDiscountPriceNoException()

    /**
     * Tests that {@link Event#getDiscountPrice()} returns an empty string when called
     * before {@link Event#setDiscountPrice(String)}.
     */
    @Test
    public void testGetDiscountPriceBeforeSetDiscountPrice() {
        System.out.println("getDiscountPrice - before setDiscountPrice");

        String expected = "";
        String received = event.getDiscountPrice();
        assertEquals(expected, received);
    }    // testGetDiscountPriceNoException()

    /**
     * Tests that (@link Event#getDiscountPrice()} returns the correct value when called
     * after {@link Event#setDiscountPrice(String)}.
     */
    @Test
    public void testGetDiscountPriceAfterSetDiscountPrice() {
        System.out.println("getDiscountPrice - after setDiscountPrice");

        String discountPrice = "foo";
        event.setDiscountPrice(discountPrice);
        String expected = discountPrice;
        String received = event.getDiscountPrice();
        assertEquals(expected, received);
    }    // testGetDiscountPriceAfterSetDiscountPrice()

    /**
     * Tests that (@link Event#getDiscountPrice()} returns the correct value when called
     * after {@link Event#setDiscountPrice(String)}, called with null.
     */
    @Test
    public void testGetDiscountPriceAfterSetDiscountPriceNull() {
        System.out.println("getDiscountPrice - after setDiscountPrice(null)");

        String discountPrice = null;
        event.setDiscountPrice(discountPrice);
        String expected = "";
        String received = event.getDiscountPrice();
        assertEquals(expected, received);
    }    // testGetDiscountPriceAfterSetDiscountPriceNull()
    
    /**
     * Tests that {@link Event#setDiscountPrice(String)} does not throw an exception.
     */
    @Test
    public void testSetDiscountPriceNoException() {
        System.out.println("setDiscountPrice - no exception");
        
        String discountPrice = "foo";
        event.setDiscountPrice(discountPrice);
    }    // testSetDiscountPriceNoException()
    
    /**
     * Tests that {@link Event#setDiscountPrice(String)} does not throw an exception
     * when the parameter is null.
     */
    @Test
    public void testSetDiscountPriceNullNoException() {
        System.out.println("setDiscountPrice - null, no exception");
        
        String discountPrice = null;
        event.setDiscountPrice(discountPrice);
    }    // testSetDiscountPriceNullNoException()
    
    /* hasManager / getManager / setManager */
    
    /**
     * Tests that {@link Event#hasManager()} does not throw an exception.
     */
    @Test
    public void testHasManagerNoException() {
        System.out.println("hasManager - no exception");
        
        event.hasManager();
    }    // testHasManagerNoException()
    
    /**
     * Tests that {@link Event#hasManager()} returns false when called before
     * {@link Event#setManager(Manager)}.
     */
    @Test
    public void testHasManagerBeforeSetManager() {
        System.out.println("hasManager - before setManager");
        
        boolean expected = false;
        boolean received = event.hasManager();
        assertEquals(expected, received);
    }    // testHasManagerBeforeSetManager()
    
    /**
     * Tests that {@link Event#hasManager()} returns true when called after
     * {@link Event#setManager(Manager)}.
     */
    @Test
    public void testHasManagerAfterSetManager() {
        System.out.println("hasManager - after setManager");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setManager(manager);
        boolean expected = true;
        boolean received = event.hasManager();
        assertEquals(expected, received);
    }    // testHasManagerAfterSetManager()
    
    /**
     * Tests that {@link Event#hasManager()} returns false when called after
     * {@link Event#setManager(Manager)}, called with null.
     */
    @Test
    public void testHasManagerAfterSetManagerNull() {
        System.out.println("hasManager - after setManager, null");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setManager(manager);
        manager = null;
        event.setManager(manager);
        boolean expected = false;
        boolean received = event.hasManager();
        assertEquals(expected, received);
    }    // testHasManagerAfterSetManagerNull()
    
    /**
     * Tests that {@link Event#getManager()} does not throw an exception.
     */
    @Test
    public void testGetManagerNoException() {
        System.out.println("getManager - no exception");
        
        event.getManager();
    }    // testGetManagerNoException()
    
    /**
     * Tests that {@link Event#getManager()} returns null when called before
     * {@link Event#setManager(Manager)}.
     */
    @Test
    public void testGetManagerBeforeSetManager() {
        System.out.println("getManager - before setManager");
        
        Manager expected = null;
        Manager received = event.getManager();
        assertEquals(expected, received);
    }    // testGetManagerBeforeSetManager()
    
    /**
     * Tests that {@link Event#getManager()} returns the correct value when
     * called after {@link Event#setManager(Manager)}.
     */
    @Test
    public void testGetManagerAfterSetManager() {
        System.out.println("getManager - after setManager");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setManager(manager);
        Manager expected = manager;
        Manager received = event.getManager();
        assertEquals(expected, received);
    }    // testGetManagerAfterSetManager()
    
    /**
     * Tests that {@link Event#getManager()} returns null when called after
     * {@link Event#setManager(Manager)}, called with null.
     */
    @Test
    public void testGetManagerAfterSetManagerNull() {
        System.out.println("getManager - after setManager, null");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setManager(manager);
        manager = null;
        event.setManager(manager);
        Manager expected = null;
        Manager received = event.getManager();
        assertEquals(expected, received);
    }    // testGetManagerAfterSetManagerNull()
    
    /**
     * Tests that {@link Event#setManager(Manager)} does not throw an exception.
     */
    @Test
    public void testSetManagerNoException() {
        System.out.println("setManager - no exception");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setManager(manager);
    }    // testSetManagerNoException()
    
    /**
     * Tests that {@link Event#setManager(Manager)} does not throw an exception
     * when the parameter is null, for an event with no manager assigned.
     */
    @Test
    public void testSetManagerNullNoManagerNoException() {
        System.out.println("setManager - null, no previous manager, no exception");
        
        Manager manager = null;
        event.setManager(manager);
    }    // testSetManagerNullNoManagerNoException()
    
    /**
     * Tests that {@link Event#setManager(Manager)} does not throw an exception
     * when the parameter is null.
     */
    @Test
    public void testSetManagerNullNoException() {
        System.out.println("setManager - null, no exception");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setManager(manager);
        manager = null;
        event.setManager(manager);
    }    // testSetManagerNullNoException()
    
    /* hasAssistantManager / getAssistantManager / setAssistantManager */
    
    /**
     * Tests that {@link Event#hasAssistantManager()} does not throw an exception.
     */
    @Test
    public void testHasAssistantManagerNoException() {
        System.out.println("hasAssistantManager - no exception");
        
        event.hasAssistantManager();
    }    // testHasAssistantManagerNoException()
    
    /**
     * Tests that {@link Event#hasAssistantManager()} returns false when called before
     * {@link Event#setAssistantManager(AssistantManager)}.
     */
    @Test
    public void testHasAssistantManagerBeforeSetAssistantManager() {
        System.out.println("hasAssistantManager - before setAssistantManager");
        
        boolean expected = false;
        boolean received = event.hasAssistantManager();
        assertEquals(expected, received);
    }    // testHasAssistantManagerBeforeSetAssistantManager()
    
    /**
     * Tests that {@link Event#hasAssistantManager()} returns true when called after
     * {@link Event#setAssistantManager(AssistantManager)}.
     */
    @Test
    public void testHasAssistantManagerAfterSetAssistantManager() {
        System.out.println("hasAssistantManager - after setAssistantManager");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setAssistantManager(manager);
        boolean expected = true;
        boolean received = event.hasAssistantManager();
        assertEquals(expected, received);
    }    // testHasAssistantManagerAfterSetAssistantManager()
    
    /**
     * Tests that {@link Event#hasAssistantManager()} returns false when called after
     * {@link Event#setAssistantManager(AssistantManager)}, called with null.
     */
    @Test
    public void testHasAssistantManagerAfterSetAssistantManagerNull() {
        System.out.println("hasAssistantManager - after setAssistantManager, null");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setAssistantManager(manager);
        manager = null;
        event.setAssistantManager(manager);
        boolean expected = false;
        boolean received = event.hasAssistantManager();
        assertEquals(expected, received);
    }    // testHasAssistantManagerAfterSetAssistantManagerNull()
    
    /**
     * Tests that {@link Event#getAssistantManager()} does not throw an exception.
     */
    @Test
    public void testGetAssistantManagerNoException() {
        System.out.println("getAssistantManager - no exception");
        
        event.getAssistantManager();
    }    // testGetAssistantManagerNoException()
    
    /**
     * Tests that {@link Event#getAssistantManager()} returns null when called before
     * {@link Event#setAssistantManager(AssistantManager)}.
     */
    @Test
    public void testGetAssistantManagerBeforeSetAssistantManager() {
        System.out.println("getAssistantManager - before setAssistantManager");
        
        Manager expected = null;
        Manager received = event.getAssistantManager();
        assertEquals(expected, received);
    }    // testGetAssistantManagerBeforeSetAssistantManager()
    
    /**
     * Tests that {@link Event#getAssistantManager()} returns the correct value when
     * called after {@link Event#setAssistantManager(AssistantManager)}.
     */
    @Test
    public void testGetAssistantManagerAfterSetAssistantManager() {
        System.out.println("getAssistantManager - after setAssistantManager");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setAssistantManager(manager);
        Manager expected = manager;
        Manager received = event.getAssistantManager();
        assertEquals(expected, received);
    }    // testGetAssistantManagerAfterSetAssistantManager()
    
    /**
     * Tests that {@link Event#getAssistantManager()} returns null when called after
     * {@link Event#setAssistantManager(AssistantManager)}, called with null.
     */
    @Test
    public void testGetAssistantManagerAfterSetAssistantManagerNull() {
        System.out.println("getAssistantManager - after setAssistantManager, null");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setAssistantManager(manager);
        manager = null;
        event.setAssistantManager(manager);
        Manager expected = null;
        Manager received = event.getAssistantManager();
        assertEquals(expected, received);
    }    // testGetAssistantManagerAfterSetAssistantManagerNull()
    
    /**
     * Tests that {@link Event#setAssistantManager(AssistantManager)} does not throw an exception.
     */
    @Test
    public void testSetAssistantManagerNoException() {
        System.out.println("setAssistantManager - no exception");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setAssistantManager(manager);
    }    // testSetAssistantManagerNoException()
    
    /**
     * Tests that {@link Event#setAssistantManager(AssistantManager)} does not throw an exception
     * when the parameter is null, for an event with no manager assigned.
     */
    @Test
    public void testSetAssistantManagerNullNoAssistantManagerNoException() {
        System.out.println("setAssistantManager - null, no previous manager, no exception");
        
        Manager manager = null;
        event.setAssistantManager(manager);
    }    // testSetAssistantManagerNullNoAssistantManagerNoException()
    
    /**
     * Tests that {@link Event#setAssistantManager(AssistantManager)} does not throw an exception
     * when the parameter is null.
     */
    @Test
    public void testSetAssistantManagerNullNoException() {
        System.out.println("setAssistantManager - null, no exception");
        
        Manager manager = new Manager("foo", "bar", "baz");
        event.setAssistantManager(manager);
        manager = null;
        event.setAssistantManager(manager);
    }    // testSetAssistantManagerNullNoException()
    
    /* addShift / getShifts */
    
    /**
     * Tests that {@link Event#addShift(Shift)} throws a
     * {@link NullPointerException} when the parameter is null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddShiftNull() {
        System.out.println("addShift - null");
        
        Shift shift = null;
        event.addShift(shift);
    }    // testAddShiftNull()
    
    /**
     * Tests that {@link Event#addShift(Shift)} does not throw an exception
     * when the parameter is not null.
     */
    @Test
    public void testAddShift() {
        System.out.println("addShift");
        
        String description = "foo";
        boolean isAngelShift = false;
        Shift shift = new Shift(description, isAngelShift);
        event.addShift(shift);
    }    // testAddShift()
    
    /**
     * Tests that {@link Event#getShifts()} does not throw an exception when no
     * shifts have been added.
     */
    @Test
    public void testGetShiftsEmptyNoException() {
        System.out.println("getShifts - empty, no exception");
        
        event.getShifts();
    }    // testGetShiftsEmptyNoException()
    
    /**
     * Tests that {@link Event#getShifts()} returns an empty list when no shifts
     * have been added.
     */
    @Test
    public void testGetShiftsEmpty() {
        System.out.println("getShifts - empty");
        
        List<Shift> expected = Collections.emptyList();
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testGetShiftsEmpty()
        
    /**
     * Tests that {@link Event#getShifts()} does not throw an exception after
     * shifts have been added.
     */
    @Test
    public void testGetShiftsNoException() {
        System.out.println("getShifts - no exception");
        
        
        String description = "foo";
        boolean isAngelShift = false;
        Shift shift = new Shift(description, isAngelShift);
        event.addShift(shift);
        event.getShifts();
    }    // testGetShiftsNoException()
        
    /**
     * Tests that {@link Event#getShifts()} returns the correct value after
     * shifts have been added.
     */
    @Test
    public void testGetShifts() {
        System.out.println("getShifts");
        
        List<Shift> shifts = Arrays.asList(new Shift("foo", false), new Shift("bar", true), new Shift("baz", false));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testGetShifts()
        
    /**
     * Tests that the list returned from {@link Event#getShifts()} is
     * unmodifiable.
     */
    @Test
    public void testGetShiftsUnmodifiable() {
        System.out.println("getShifts - return value is unmodifiable");
        
        List<Shift> shifts = Arrays.asList(new Shift("foo", false), new Shift("bar", true), new Shift("baz", false));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for
        List<Shift> list = event.getShifts();
        
        Shift shift = new Shift("smurf", true);
        try {
            list.add(shift);
            fail("able to add shifts to return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.remove(0);
            fail("able to remove shifts from return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.set(0, shift);
            fail("able to replace shifts in return value");
        } catch (RuntimeException e) {    // try
        }    // catch
    }    // testGetShiftsUnmodifiable()
        
    /**
     * Tests that the elements of the list returned from
     * {@link Event#getShifts()} are modifiable.
     */
    @Test
    public void testGetShiftsElementsModifiable() {
        System.out.println("getShifts - elements of return value are modifiable");
        
        List<Shift> shifts = Arrays.asList(new Shift("foo", false), new Shift("bar", true), new Shift("baz", false));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for
        List<Shift> list = event.getShifts();
        int index = 0;
        Volunteer volunteer = new Volunteer("smurf", "quux", true);
        list.get(index).setVolunteer(volunteer);
    }    // testGetShiftsElementsModifiable()
        
    /**
     * Tests that changes to the elements of the list returned from
     * {@link Event#getShifts()} are persistent.
     */
    @Test
    public void testGetShiftsPersistence() {
        System.out.println("getShifts - persistence");
        
        List<Shift> shifts = Arrays.asList(new Shift("foo", false), new Shift("bar", true), new Shift("baz", false));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for
        List<Shift> list = event.getShifts();
        int index = 0;
        Volunteer volunteer = new Volunteer("smurf", "quux", true);
        list.get(index).setVolunteer(volunteer);
        list = null;
        
        list = event.getShifts();
        Volunteer expected = volunteer;
        Volunteer received = list.get(index).getVolunteer();
        assertEquals(expected, received);
    }    // testGetShiftsPersistence()

}    // EventTest
