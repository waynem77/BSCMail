/*
 * Copyright © 2017 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui.util;

import java.awt.Component;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Wayne Miller
 * @since 3.1
 */
public class GroupedGrid extends JPanel {

    /**
     * Constructs a new grouped grid component with the specified number of groups and columns.
     *
     * @param groups the number of groups; must be 1 or more
     * @throws IllegalArgumentException if {@code groups < 1}
     */
    public GroupedGrid(int groups) {
        throw new RuntimeException("Method not implemented.");
    }    // GroupedGrid()

    public void setComponents(List<LabeledComponent> components, int group) {
        throw new RuntimeException("Method not implemented.");
    }    // setComponents()

    public void clearGroup(int group) {
        throw new RuntimeException("Method not implemented.");
    }    // clearGroup()

    public List<JComponent> getComponents(int group) {
        throw new RuntimeException("Method not implemented.");
    }    // getValues()

    public int getNumberOfGroups() {
        throw new RuntimeException("Method not implemented.");
    }    // getNumberOfGroups()

}    // GroupedGrid
