/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package main;

/**
 * Indicates that an XML file is malformed.
 * 
 * @author Wayne Miller
 */
public class MalformedInputException extends RuntimeException {

    /**
     * Creates a new instance of <code>MalformedInputException</code> without detail message.
     */
    public MalformedInputException() {
    }    // MalformedInputException()

    /**
     * Constructs an instance of <code>MalformedInputException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public MalformedInputException(String msg) {
        super(msg);
    }    // MalformedInputException(String msg)
}    // MalformedInputException
