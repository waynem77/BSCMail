/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package main;

/**
 * A class may implement the {@code VolunteersObserver} interface when it wants to
 * be informed of changes to the list of defined volunteers.
 * 
 * @see Application
 * @see bscmail.Volunteer
 * @since 2.0
 * @author Wayne Miller
 */
public interface VolunteersObserver {

    /**
     * This method is called whenever the list of defined volunteers changes.
     */
    public void volunteersChanged();
    
}    // VolunteersObserver
