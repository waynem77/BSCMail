/*
 * Copyright © 2018 its authors.  See the file "AUTHORS" for details.
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

package bscmail.util.format;

import bscmail.Event;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formats a template string with appropriate event information.
 *
 * Most text is reproduced as is with a few exceptions.
 * <ul>
 * <li>The <strong>brace characters</strong> ("{" and "}") are used as escape
 * characters. Single braces are not reproduced.</li>
 * <li>Brace characters may be escaped: "<strong>{{</strong>" produces "{", and
 * "<strong>}}</strong>" produces "}"</li>
 * <li>"<strong>{date}</strong>" produces the event date in the format given by
 * the application's {@link EmailTemplate}, or "" if the event date is
 * null.</li>
 * </ul>
 *
 * @author Wayne Miller
 * @since 3.3
 */
public class EmailFormatter {

    /**
     * The format for dates.
     */
    private final String dateFormatString;

    /**
     * Creates a new email formatter with the given date format
     *
     * @param dateFormatString the date format to use; may not be null; must be
     * in a format appropriate for a {@link java.text.SimpleDateFormat}
     * @throws NullPointerException if any parameter is null
     * @throws IllegalArgumentException if dateFormatString is not in an
     * appropriate format
     */
    public EmailFormatter(String dateFormatString) {
        if (dateFormatString == null) {
            throw new NullPointerException("dateFormatString may not be null");
        }    // if
        try {
            new SimpleDateFormat(dateFormatString);    // Just checking to see if it throws
        } catch (IllegalArgumentException e) {    // try
            throw new IllegalArgumentException("dateFormatString must be in acceptible format", e);
        }    // catch

        this.dateFormatString = dateFormatString;
        assertInvariant();
    }    // EmailFormatter()

    /**
     * Turns a string template into a list of tokens.
     *
     * @param string the string template; may not be null
     * @param event the event; may not be null
     * @return a list of tokens based on the given string
     */
    private List<Token> tokenizeString(String string, Event event) {
        assert (string != null);
        assert (event != null);

        Date eventDate = event.getDate();
        Token dateToken = (eventDate == null) ? TokenMaker.makeStringAtom("") : TokenMaker.makeDateAtom(eventDate, dateFormatString);

        List<Token> tokenizedList = Arrays.asList(TokenMaker.makeDecomposableToken(string));
        tokenizedList = tokenizeListForOneToken(tokenizedList, "\\{\\{", TokenMaker.makeStringAtom("{"));
        tokenizedList = tokenizeListForOneToken(tokenizedList, "\\}\\}", TokenMaker.makeStringAtom("}"));
        tokenizedList = tokenizeListForOneToken(tokenizedList, "\\{date\\}", dateToken);
        tokenizedList = tokenizeListForOneToken(tokenizedList, "\\{|\\}", TokenMaker.makeStringAtom(""));
        return tokenizedList;
    }    // tokenizeString()

    /**
     * Further decomposes a list of tokens and strings by replacing matches of a
     * regular expression with a particular token.
     *
     * @param list the list of tokens and strings; may not be null nor contain
     * null
     * @param regex the regular expression; may not be null
     * @param replacementToken the replacement token; may not be null
     * @return the further decomposed list
     */
    private List<Token> tokenizeListForOneToken(List<Token> list, String regex, Token replacementToken) {
        assert (list != null);
        assert (!list.contains(null));
        assert (regex != null);
        assert (replacementToken != null);

        Pattern regexPattern = Pattern.compile(regex);
        List<Token> furtherTokenizedList = new LinkedList<>();
        for (Token token : list) {
            if (token.isDecomposable()) {
                List<Token> decomposedToken = decomposeToken(token, regexPattern, replacementToken);
                assert (decomposedToken != null);
                assert (! decomposedToken.contains(null));
                furtherTokenizedList.addAll(decomposedToken);
            } else {    // if
                furtherTokenizedList.add(token);
            }    // else
        }    // for
        return furtherTokenizedList;
    }    // tokenizeList

    /**
     * Decomposes a single token into a list of tokens by replacing matches of a
     * regular expression with a particular token.
     *
     * @param token the token to decompose; may not be null nor atomic
     * @param regexPattern the regular expression pattern; may not be null
     * @param replacementToken the replacement token; may not be null
     * @return the resulting list
     */
    private List<Token> decomposeToken(Token token, Pattern regexPattern, Token replacementToken) {
        assert (token != null);
        assert (token.isDecomposable());
        assert (regexPattern != null);
        assert (replacementToken != null);

        List<Token> tokenizedList = new LinkedList<>();
        String tokenValue = token.getStringValue();
        Matcher regexMatcher = regexPattern.matcher(tokenValue);
        int preMatchTokenStart = 0;
        while (regexMatcher.find()) {
            int preMatchTokenEnd = regexMatcher.start();
            tokenizedList.add(TokenMaker.makeDecomposableToken(tokenValue.substring(preMatchTokenStart, preMatchTokenEnd)));
            tokenizedList.add(replacementToken);
            preMatchTokenStart = regexMatcher.end();
        }    // while
        tokenizedList.add(TokenMaker.makeDecomposableToken(tokenValue.substring(preMatchTokenStart)));

        return tokenizedList;
    }    // decomposeToken()

    /**
     * Constructs a string from a list of tokens.
     *
     * @param tokens the list of tokens; may not be null nor contain null
     * @return a string representing the list of tokens
     */
    private String stringifyTokens(List<Token> tokens) {
        assert (tokens != null);
        assert (! tokens.contains(null));

        StringBuilder builder = new StringBuilder();
        for (Token token : tokens) {
            builder.append(token.getStringValue());
        }    // for
        return builder.toString();
    }    // stringifyTokens()

    /**
     * Returns a string created from the given template and populated with information from the
     * given event according to the rules listed in the class documentation.
     *
     * @param format the format; may not be null
     * @param event the event; may not be null
     * @return the formatted string
     * @throws NullPointerException if either argument is null
     */
    public String formatString(String format, Event event) {
        assertInvariant();
        if (format == null) {
            throw new NullPointerException("format may not be null");
        }    // if
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if

        List<Token> tokens = tokenizeString(format, event);
        return stringifyTokens(tokens);
    }    // formatString()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (dateFormatString != null);
    }    // assertInvariant()

}    // EmailFormatter
