/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */
package bscmail;

/**
 * Represents a person affiliated with BSC.
 *
 * @author Wayne Miller
 */
public interface Person {

    /**
     * Returns the person's name.
     *
     * @return the person's name
     */
    public String getName();

    /**
     * Returns the person's email address.
     *
     * @return the person's email address
     */
    public String getEmail();

}    // Person