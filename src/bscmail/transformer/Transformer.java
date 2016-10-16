/*
 * Copyright © 2014 Wayne Miller
 */

package bscmail.transformer;

import bscmail.Event;
import java.util.*;
import java.util.regex.*;

/**
 * Transforms document templates into live documents by inserting properties
 * from an {@link Event}.
 *
 * Transformers work by applying named rules to strings. Rules are defined by a
 * name and an event property. The {@link #transform(String)} method transforms
 * a string by replacing all instances of the property name (enclosed in angle
 * brackets) with the value of the event property. For example, if the name is
 * "manager" and the event property returns the name of the event manager, then
 * {@code transform()} replaces all instances of "&lt;manager&gt;" in the string
 * with the name of the event manager.
 *
 * @see EventProperty
 * @author Wayne Miller
 */
public class Transformer {

    private class Rule {
        public final Pattern pattern;
        public final EventProperty eventProperty;
        
        public Rule(Pattern pattern, EventProperty eventProperty) {
            assert (pattern != null);
            assert (eventProperty != null);
            this.pattern = pattern;
            this.eventProperty = eventProperty;
        }    // Rule()
    }    // Rule
    
    private final Map<String, Rule> rules;
    
    /**
     * Constructs a new transformer.
     */
    public Transformer() {
        rules = new LinkedHashMap<>();
        assertInvariant();
    }    // Transformer()
    
    /**
     * Adds a rule to the transformer.  See the class documentation for more
     * information about rules.
     *
     * @param name the name of the rule; may not be null
     * @param eventProperty the event property of the rule; may not be null
     * @throws NullPointerException if either parameter is null
     */
    public void addRule(String name, EventProperty eventProperty) {
        assertInvariant();
        if (name == null) {
            throw new NullPointerException("name may not be null");
        }    // if
        if (eventProperty == null) {
            throw new NullPointerException("eventProperty may not be null");
        }    // if
        String regex = "<" + name + ">";
        rules.put(name, new Rule(Pattern.compile(regex), eventProperty));
        assertInvariant();
    }    // addRule()
    
    /**
     * Removes the rule with the given name from the transformer.  If there is
     * no rule with the given name, this method does nothing.
     * 
     * @param name the name of the rule to remove
     */
    public void removeRule(String name) {
        assertInvariant();
        rules.remove(name);
        assertInvariant();
    }    // removeRule()
    
    /**
     * Transforms the input string by applying all the rules in the transformer
     * to it.  See the class documentation for more information about rules.
     * 
     * @param event the event; may not be null
     * @param input the input string; may not be null
     * @return the transformed string
     * @throws NullPointerException if either parameter is null
     */
    public String transform(Event event, String input) {
        assertInvariant();
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        if (input == null) {
            throw new NullPointerException("input may not be null");
        }    // if

        String output = input;
        for (Rule rule : rules.values()) {
            Matcher matcher = rule.pattern.matcher(output);
            String property = rule.eventProperty.getProperty(event);
            property = Matcher.quoteReplacement(property);
            output = matcher.replaceAll(property);
        }    // for
        return output;
    }    // transform()
    
    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (rules != null);
        assert (! rules.containsKey(null));
        assert (! rules.containsValue(null));
    }    // assertInvariant()
    
}    // Transformer
