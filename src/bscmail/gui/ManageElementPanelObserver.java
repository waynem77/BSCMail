/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */
package bscmail.gui;

/**
 * A class may implement the {@code ManageElementPanelObserver} interface when
 * it wants to be informed when the validity of the element contained in a
 * {@link ManageElementPanel} changes.
 *
 * @author Wayne Miller
 * @param <E> the type of element created by the {@code ManageElementPanel}
 * @since 2.0.1
 */
public interface ManageElementPanelObserver<E> {

    /**
     * This method is called when the validity of the element of the
     * {@link ManageElementPanel} being observed changes.
     */
    public void elementValidityChanged();

}    // ManageElementPanelObserver
