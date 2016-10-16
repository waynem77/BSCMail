/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageElementPanelLayoutHelper}.
 * 
 * @author Wayne Miller
 */
public class ManageElementPanelLayoutHelperTest {
    
    /**
     * Variable used to hold the layout manager being tested.
     */
    ManageElementPanelLayoutHelper layoutHelper;
    
    /**
     * Variable used to hold the container used in testing.
     */
    Container container;
    
    /**
     * Variable used to hold the string used in testing.
     */
    String name;
    
    /**
     * Variable used to hold the component used in testing.
     */
    Component component;
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ManageElementPanelLayoutHelper");
        System.out.println("==============================");
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
        container = new JPanel();
        layoutHelper = new ManageElementPanelLayoutHelper(container);
        name = "Foo";
        component = new JTextField();
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        layoutHelper = null;
        container = null;
        name = null;
        component = null;
    }    // tearDown()
    
    /*
     * Unit tests.
     */
    
    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#ManageElementPanelLayoutHelper(Container)}
     * throws a {@link NullPointerException} when container is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorContainerNull() {
        System.out.println("constructor - container is null");
        
        container = null;
        layoutHelper = new ManageElementPanelLayoutHelper(container);
    }    // testConstructorContainerNull()
    
    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#ManageElementPanelLayoutHelper(Container)}
     * does not throw an exception when the container is not null.
     */
    @Test
    public void testConstructorContainerNoException() {
        System.out.println("constructor - no exception");
        
        layoutHelper = new ManageElementPanelLayoutHelper(container);
    }    // testConstructorContainerNoException()
    
    /**
     * Tests that {@link ManageElementPanelLayoutHelper#setLayoutManager()} does
     * not throw an exception.
     */
    @Test
    public void testSetLayoutManagerContainerNoException() {
        System.out.println("setLayoutManager - no exception");

        layoutHelper.setLayoutManager();
    }    // testSetLayoutManagerContainerNoException()
    
    /**
     * Tests that {@link ManageElementPanelLayoutHelper#setLayoutManager()} sets
     * the appropriate layout manager.
     */
    @Test
    public void testSetLayoutManager() {
        System.out.println("setLayoutManager");

        container.setLayout(new FlowLayout());
        layoutHelper.setLayoutManager();
        Class expected = GridBagLayout.class;
        Class received = container.getLayout().getClass();
        assertEquals(expected, received);
    }    // testSetLayoutManager()
    
    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * throws a {@link NullPointerException} when the name is null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddComponentNameNull() {
        System.out.println("addComponent - name is null");
        
        layoutHelper.setLayoutManager();
        name = null;
        layoutHelper.addComponent(name, component);
    }    // testAddComponentNameNull()
    
    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * throws a {@link NullPointerException} when the component is null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddComponentComponentNull() {
        System.out.println("addComponent - component is null");
        
        layoutHelper.setLayoutManager();
        component = null;
        layoutHelper.addComponent(name, component);
    }    // testAddComponentComponentNull()
    
    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * does not throw an exception when neither parameter is null.
     */
    @Test
    public void testAddComponentNoException() {
        System.out.println("addComponent - no exception");
        
        layoutHelper.setLayoutManager();
        layoutHelper.addComponent(name, component);
    }    // testAddComponentNoException()
    
    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * adds exactly two components to the container.
     */
    @Test
    public void testAddComponentAddsTwoComponents() {
        System.out.println("addComponent - adds exactly two components");
        
        layoutHelper.setLayoutManager();
        container.removeAll();
        layoutHelper.addComponent(name, component);
        Component[] components = container.getComponents();
        int expected = 2;
        int received = components.length;
        assertEquals(expected, received);
    }    // testAddComponentAddsTwoComponents()
    
    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * adds a label to the container.
     */
    @Test
    public void testAddComponentAddsLabel() {
        System.out.println("addComponent - adds label");
        
        layoutHelper.setLayoutManager();
        container.removeAll();
        layoutHelper.addComponent(name, component);
        boolean hasLabel = false;
        Component[] components = container.getComponents();
        for (Component c : components) {
            if (c instanceof JLabel) {
                hasLabel = true;
                break;
            }    // if
        }    // for
        assertTrue(hasLabel);
    }    // testAddComponentAddsLabel()
    
    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * adds the correct label to the container.
     */
    @Test
    public void testAddComponentAddsCorrectLabel() {
        System.out.println("addComponent - adds correct label");
        
        layoutHelper.setLayoutManager();
        container.removeAll();
        layoutHelper.addComponent(name, component);
        Component[] components = container.getComponents();
        String expected = name;
        String received = null;
        for (Component c : components) {
            if (c instanceof JLabel) {
                JLabel label = (JLabel)c;
                received = label.getText();
                break;
            }    // if
        }    // for
        assertEquals(expected, received);
    }    // testAddComponentAddsCorrectLabel()
    
    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * adds the component to the container.
     */
    @Test
    public void testAddComponentAddsComponent() {
        System.out.println("addComponent - adds component");
        
        layoutHelper.setLayoutManager();
        container.removeAll();
        layoutHelper.addComponent(name, component);
        Collection<Component> components = Arrays.asList(container.getComponents());
        boolean hasContainer = components.contains(component);
        assertTrue(hasContainer);
    }    // testAddComponentAddsComponent()

}    // ManageElementPanelLayoutHelperTest
