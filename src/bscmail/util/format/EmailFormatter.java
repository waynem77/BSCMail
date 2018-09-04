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
 * "<strong>}}</strong>" produces "}"
 * <li>"<strong>{date}</strong>" produces the event date in the format "Friday
 * February 2", or "" if the event date is null.
 * </ul>
 *
 * @author Wayne Miller
 * @since 3.3
 */
public class EmailFormatter {

    List<Token> tokenizeString(String string, Event event) {
        assert (string != null);
        assert (event != null);

        Date eventDate = event.getDate();
        Token dateToken = (eventDate == null) ? TokenMaker.makeStringAtom("") : TokenMaker.makeDateAtom(eventDate);

        List<Token> tokenizedList = Arrays.asList(TokenMaker.makeDecomposableToken(string));
        tokenizedList = tokenizeListForOneToken(tokenizedList, "\\{\\{", TokenMaker.makeStringAtom("{"));
        tokenizedList = tokenizeListForOneToken(tokenizedList, "\\}\\}", TokenMaker.makeStringAtom("}"));
        tokenizedList = tokenizeListForOneToken(tokenizedList, "\\{date\\}", dateToken);
        tokenizedList = tokenizeListForOneToken(tokenizedList, "\\{|\\}", TokenMaker.makeStringAtom(""));
        return tokenizedList;
    }    // tokenizeString()

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
        if (format == null) {
            throw new NullPointerException("format may not be null");
        }    // if
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if

        List<Token> tokens = tokenizeString(format, event);
        return stringifyTokens(tokens);
    }    // formatString()

}    // EmailFormatter
