/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Transformer}.
 * @author Wayne Miller
 */
public class TransformerTest {

    /**
     * Variable used to hold the transformer being tested.
     */
    private Transformer transformer;
    
    /**
     * Variable used to hold the name of the rule used in testing.
     */
    private String name;
    
    /**
     * Variable used to hold the event property for the rule used in testing.
     */
    private EventProperty eventProperty;
    
    /**
     * Variable used to hold the event used in testing.
     */
    private Event event;
    
    /**
     * Variable used to hold the input string used in testing.
     */
    private String input;
    
    /**
     * Returns an event property that returns the given string.
     *
     * @param output the string to return; may not be null
     */
    private EventProperty makeEventProperty(final String output) {
        return new EventProperty() {
            public String getProperty(Event event) {
                return output;
            }    // getProperty()
        };    // return
    }    // makeEventProperty()
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Transformer");
        System.out.println("===========");
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
        transformer = new Transformer();
        name = null;
        eventProperty = null;
        event = new Event();
        input = null;
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        transformer = null;
        name = null;
        eventProperty = null;
        event = null;
        input = null;
    }    // tearDown()

    /**
     * Tests that {@link Transformer#Transformer()} does not throw an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        transformer = new Transformer();
    }    // testConstructorNoException()

    /**
     * Tests that {@link Transformer#addRule(String, EventProperty)} throws a
     * {@link NullPointerException} when the name is null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddRuleNameNull() {
        System.out.println("addRule - name is null");
        name = null;
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
    }    // testAddRuleNameNull()

    /**
     * Tests that {@link Transformer#addRule(String, EventProperty)} throws a
     * {@link NullPointerException} when the event property is null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddRuleEventPropertyNull() {
        System.out.println("addRule - event property is null");
        name = "1";
        eventProperty = null;
        transformer.addRule(name, eventProperty);
    }    // testAddRuleEventPropertyNull()

    /**
     * Tests that {@link Transformer#addRule(String, EventProperty)} does not
     * throw an exception when neither parameter is null.
     */
    @Test
    public void testAddRuleNoException() {
        System.out.println("addRule - no exception");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
    }    // testAddRuleNoException()

    /**
     * Tests that {@link Transformer#addRule(String, EventProperty)} does not
     * throw an exception when multiple rules are added.
     */
    @Test
    public void testAddRuleMultipleNoException() {
        System.out.println("addRule - multiple rules, no exception");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        name = "2";
        eventProperty = makeEventProperty("two");
        transformer.addRule(name, eventProperty);
    }    // testAddRuleMultipleNoException()

    /**
     * Tests that {@link Transformer#addRule(String, EventProperty)} does not
     * throw an exception when adding identical rules.
     */
    @Test
    public void testAddRuleMultipleIdenticalNoException() {
        System.out.println("addRule - multiple identical rules, no exception");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        transformer.addRule(name, eventProperty);
    }    // testAddRuleMultipleIdenticalNoException()

    /**
     * Tests that {@link Transformer#addRule(String, EventProperty)} does not
     * throw an exception when adding distinct, identically-named rules.
     */
    @Test
    public void testAddRuleMultipleSameNameNoException() {
        System.out.println("addRule - same name, no exception");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        eventProperty = makeEventProperty("two");
        transformer.addRule(name, eventProperty);
    }    // testAddRuleMultipleSameNameNoException()

    /**
     * Tests that {@link Transformer#removeRule(String)} does not throw an
     * exception when the name is null.
     */
    @Test
    public void testRemoveRuleNullNoException() {
        System.out.println("removeRule - null, no exception");
        name = null;
        transformer.removeRule(name);
    }    // testRemoveRuleNullNoException()

    /**
     * Tests that {@link Transformer#removeRule(String)} does not throw an
     * exception when the rule exists in the transformer.
     */
    @Test
    public void testRemoveRuleExistsNoException() {
        System.out.println("removeRule - rule exists, no exception");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        transformer.removeRule(name);
    }    // testRemoveRuleExistsNoException()

    /**
     * Tests that {@link Transformer#removeRule(String)} does not throw an
     * exception when the rule does not exist in the transformer.
     */
    @Test
    public void testRemoveRuleDoesNotExistNoException() {
        System.out.println("removeRule - rule does not exist, no exception");
        name = "1";
        transformer.removeRule(name);
    }    // testRemoveRuleDoesNotExistNoException()

    /**
     * Tests that {@link Transformer#transform(Event, String)} throws an
     * {@link NullPointerException} when the event is null.
     */
    @Test(expected = NullPointerException.class)
    public void testTransformEventNull() {
        System.out.println("transform - event is null");
        event = null;
        input = "<1>, <2>, <3>, and <1>";
        transformer.transform(event, input);
    }    // testTransformEventNull()

    /**
     * Tests that {@link Transformer#transform(Event, String)} throws an
     * {@link NullPointerException} when the input string is null.
     */
    @Test(expected = NullPointerException.class)
    public void testTransformInputNull() {
        System.out.println("transform - event is null");
        input = null;
        transformer.transform(event, input);
    }    // testTransformInputNull()

    /**
     * Tests that {@link Transformer#transform(Event, String)} does not throw an
     * exception when neither parameter is null and the transformer has no
     * rules.
     */
    @Test
    public void testTransformNoRulesNoException() {
        System.out.println("transform - no rules, no exception");
        input = "<1>, <2>, <3>, and <1>";
        transformer.transform(event, input);
    }    // testTransformNoRulesNoException()

    /**
     * Tests that {@link Transformer#transform(Event, String)} does not throw an
     * exception when neither parameter is null and the transformer has one
     * rule.
     */
    @Test
    public void testTransformOneRuleNoException() {
        System.out.println("transform - one rule, no exception");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        input = "<1>, <2>, <3>, and <1>";
        transformer.transform(event, input);
    }    // testTransformOneRuleNoException()

    /**
     * Tests that {@link Transformer#transform(Event, String)} does not throw an
     * exception when neither parameter is null and the transformer has multiple
     * rules.
     */
    @Test
    public void testTransformMultipleRulesNoException() {
        System.out.println("transform - multiple rules, no exception");
        name = "1";
        eventProperty = makeEventProperty("one");
        name = "2";
        eventProperty = makeEventProperty("two");
        transformer.addRule(name, eventProperty);
        input = "<1>, <2>, <3>, and <1>";
        transformer.transform(event, input);
    }    // testTransformMultipleRulesNoException()

    /**
     * Tests that {@link Transformer#transform(Event, String)} does not throw an
     * exception when neither parameter is null and the transformer has a rule
     * that doesn't apply to the input.
     */
    @Test
    public void testTransformInapplicableRuleNoException() {
        System.out.println("transform - inapplicable rule, no exception");
        name = "4";
        eventProperty = makeEventProperty("four");
        transformer.addRule(name, eventProperty);
        input = "<1>, <2>, <3>, and <1>";
        transformer.transform(event, input);
    }    // testTransformInapplicableRuleNoException()

    /**
     * Tests that {@link Transformer#transform(Event, String)} returns the
     * correct value when there are no rules.
     */
    @Test
    public void testTransformNoRules() {
        System.out.println("transform - no rules");
        input = "<1>, <2>, <3>, and <1>";
        String expected = input;
        String received = transformer.transform(event, input);
        assertEquals(expected, received);
    }    // testTransformNoRules()

    /**
     * Tests that {@link Transformer#transform(Event, String)} returns the
     * correct value when there is one rule.
     */
    @Test
    public void testTransformOneRule() {
        System.out.println("transform/addRule - one rule");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        input = "<1>, <2>, <3>, and <1>";
        String expected = "one, <2>, <3>, and one";
        String received = transformer.transform(event, input);
        assertEquals(expected, received);
    }    // testTransformOneRule()

    /**
     * Tests that {@link Transformer#transform(Event, String)} returns the
     * correct value when a rule is overwritten.
     */
    @Test
    public void testTransformRuleOverwritten() {
        System.out.println("transform/addRule - rule overwritten");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        name = "1";
        eventProperty = makeEventProperty("uno");
        transformer.addRule(name, eventProperty);
        input = "<1>, <2>, <3>, and <1>";
        String expected = "uno, <2>, <3>, and uno";
        String received = transformer.transform(event, input);
        assertEquals(expected, received);
    }    // testTransformRuleOverwritten()

    /**
     * Tests that {@link Transformer#transform(Event, String)} returns the
     * correct value when there are multiple rules.
     */
    @Test
    public void testTransformMultipleRules() {
        System.out.println("transform/addRule - multiple rules");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        name = "2";
        eventProperty = makeEventProperty("two");
        transformer.addRule(name, eventProperty);
        input = "<1>, <2>, <3>, and <1>";
        String expected = "one, two, <3>, and one";
        String received = transformer.transform(event, input);
        assertEquals(expected, received);
    }    // testTransformMultipleRules()

    /**
     * Tests that {@link Transformer#transform(Event, String)} returns the
     * correct value when there is an inapplicable rule.
     */
    @Test
    public void testTransformInapplicableRule() {
        System.out.println("transform - inapplicable rule");
        name = "4";
        eventProperty = makeEventProperty("four");
        transformer.addRule(name, eventProperty);
        input = "<1>, <2>, <3>, and <1>";
        String expected = input;
        String received = transformer.transform(event, input);
        assertEquals(expected, received);
    }    // testTransformInapplicableRule()

    /**
     * Tests that {@link Transformer#transform(Event, String)} returns the
     * correct value after an unsuccessful call to
     * {@link Transformer#removeRule(String)}.
     */
    @Test
    public void testTransformAfterUnsuccessfulRemoveRule() {
        System.out.println("transform/removeRule - unsuccessful remove");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        name = "2";
        eventProperty = makeEventProperty("two");
        transformer.addRule(name, eventProperty);
        name = "3";
        transformer.removeRule(name);
        input = "<1>, <2>, <3>, and <1>";
        String expected = "one, two, <3>, and one";
        String received = transformer.transform(event, input);
        assertEquals(expected, received);
    }    // testTransformAfterUnsuccessfulRemoveRule()

    /**
     * Tests that {@link Transformer#transform(Event, String)} returns the
     * correct value after a successful call to
     * {@link Transformer#removeRule(String)}.
     */
    @Test
    public void testTransformAfterSuccessfulRemoveRule() {
        System.out.println("transform/removeRule - successful remove");
        name = "1";
        eventProperty = makeEventProperty("one");
        transformer.addRule(name, eventProperty);
        name = "2";
        eventProperty = makeEventProperty("two");
        transformer.addRule(name, eventProperty);
        name = "1";
        transformer.removeRule(name);
        input = "<1>, <2>, <3>, and <1>";
        String expected = "<1>, two, <3>, and <1>";
        String received = transformer.transform(event, input);
        assertEquals(expected, received);
    }    // testTransformAfterSuccessfulRemoveRule()

    /**
     * Tests that {@link Transformer#transform(Event, String)} returns the
     * correct value when the replacement text contains the special characters
     * '\' and '$'.
     */
    @Test
    public void testTransformSpecialCharacters() {
        System.out.println("transform - special characters");
        name = "price";
        eventProperty = makeEventProperty("$10 general \\ $5 discounted");
        transformer.addRule(name, eventProperty);
        input = "Event pricing: <price>";
        String expected = "Event pricing: $10 general \\ $5 discounted";
        String received = transformer.transform(event, input);
        assertEquals(expected, received);
    }    // testTransformOneRule()
    
}    // TransformerTest
