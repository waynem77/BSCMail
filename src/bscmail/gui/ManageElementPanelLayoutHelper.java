/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.gui;

import java.awt.*;
import javax.swing.JLabel;

/**
 * A suggested layout helper for implementations of {
 *
 * @linl ManageElementPanel}. This class is used to set the panel's layout
 * manager to {@link GridBagLayout}, and has methods for pairing components with
 * identifying labels in rows.
 *
 * To use this layout helper, first set the layout manager via
 * {@link #setLayoutManager()}, and then sequentially add components along with
 * their identifying strings via the {@link #addComponent(String, Component)}
 * method. Each label and component take up a single row in the container, and
 * appear in the order they are entered.
 *
 * @since 2.0
 * @author Wayne Miller
 */
class ManageElementPanelLayoutHelper {

    /**
     * The container to which to add components.
     */
    private final Container container;
    
    /**
     * The constraints used in laying out components.
     */
    private final GridBagConstraints constraints;
    
    /**
     * Constructs a new layout helper on the given container.
     * 
     * @param container the container using the layout manager; may not be null
     * @throws NullPointerException if {@code container} is null
     */
    public ManageElementPanelLayoutHelper(Container container) {
        if (container == null) {
            throw new NullPointerException("container may not be null");
        }    // if
        this.container = container;
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
    }    // ManageElementPanelLayoutHelper()

    /**
     * Sets the layout manager for the container.  The layout manager used is a
     * {@link GridBagContainer}.
     */
    public void setLayoutManager() {
        container.setLayout(new GridBagLayout());
        container.validate();
    }    // setLayoutManager()
    
    /**
     * Adds a component with an identifying label to the container.
     * 
     * @param name the name that appears in the label; may not be null
     * @param component the component to add; may not be null
     * @throws NullPointerException if either parameter is null
     */
    public void addComponent(String name, Component component) {
        if (name == null) {
            throw new NullPointerException("name may not be null");
        }    // if
        if (component == null) {
            throw new NullPointerException("component may not be null");
        }    // if
        constraints.weightx = 0.0;
        container.add(new JLabel(name), constraints);
        ++constraints.gridx;
        constraints.weightx = 1.0;
        container.add(component, constraints);
        constraints.gridx = 0;
        ++constraints.gridy;
    }    // addComponent()

}    // ManageElementPanelLayoutHelper
