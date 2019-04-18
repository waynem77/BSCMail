/*
 * Copyright © 2014-2019 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui;

import bscmail.Application;
import bscmail.Event;
import bscmail.gui.util.ComponentFactory;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The main window for BSCMail.
 *
 * @since 2.0
 * @author Wayne Miller
 */
public class MainFrame extends JFrame {

    /**
     * A list with a readable name.
     *
     * @param <E> the type of element contained by the list
     */
    private class NamedList<E> extends LinkedList<E> {

        /**
         * The name of the list.
         */
        private final String name;

        /**
         * Constructs a named list with the given name.
         *
         * @param name the name of the list; may not be null
         */
        public NamedList(String name) {
            assert (name != null);
            this.name = name;
        }    // NamedList()

        /**
         * Returns the name of the list
         *
         * @return the name of the list
         */
        public String getName() {
            return name;
        }    // getName()

    }    // NamedList

    /**
     * A collection of groups of buttons.
     *
     * This class is used to create buttons and help streamline their layout.
     */
    class ButtonCollection {

        /**
         * The buttons.
         */
        private final Map<String, List<JButton>> buttons;

        /**
         * Constructs a new button collection.
         */
        public ButtonCollection() {
            buttons = new LinkedHashMap<>();
        }    // ButtonCollection()

        /**
         * Adds a button to the given group with the given name and action listener.
         * @param groupName the name of the group; may not be null
         * @param buttonName the name of the button; may not be null
         * @param actionListener the action listener; may not be null
         */
        public void addButton(String groupName, String buttonName, ActionListener actionListener) {
            assert (groupName != null);
            assert (buttonName != null);
            assert (actionListener != null);
            List<JButton> buttonGroup = buttons.get(groupName);
            if (buttonGroup == null) {
                buttonGroup = new LinkedList<>();
            }    // if
            JButton button = new JButton(buttonName);
            button.addActionListener(actionListener);
            buttonGroup.add(button);
            buttons.put(groupName, buttonGroup);
        }    // addButton()

        /**
         * Returns the number of groups.
         *
         * @return the number of groups
         */
        public int getNumberOfGroups() {
            return buttons.keySet().size();
        }    // getNumberOfGroups()

        /**
         * Returns the number of buttons in the largest group.
         * @return the number of buttons in the largest group
         */
        public int getMaxSizeOfGroups() {
            int max = 0;
            for (List<JButton> buttonGroup : buttons.values()) {
                if (buttonGroup.size() > max) {
                    max = buttonGroup.size();
                }    // if
            }    // for
            return max;
        }    // getMaxSizeOfGroups()

        /**
         * Returns the grouped buttons.
         *
         * @return the grouped buttons
         */
        public List<NamedList<JButton>> getButtons() {
            List<NamedList<JButton>> buttonList = new LinkedList<>();
            for (String buttonGroupName : buttons.keySet()) {
                NamedList<JButton> buttonGroup = new NamedList<>(buttonGroupName);
                buttonGroup.addAll(buttons.get(buttonGroupName));
                buttonList.add(buttonGroup);
            }    // for
            return buttonList;
        }    // getButtons()

    }    // ButtonCollection

    /**
     * The calling application.
     */
    private final Application application;

    /**
     * A frame to manage shifts.
     */
    private final ManageShiftsFrame manageShiftsFrame;

    /**
     * A frame to manage volunteers.
     */
    private final ManageVolunteersFrame manageVolunteersFrame;

    /**
     * A frame to manage the email template.
     */
    private final ManageEmailTemplateFrame manageEmailTemplateFrame;

    /**
     * A frame to manage the email server properties.
     */
    private final ManageEmailServerPropertiesFrame manageEmailServerPropertiesFrame;

    /**
     * A frame to manage roles.
     */
    private final ManageRolesFrame manageRolesFrame;

    /**
     * A frame to manage event properties.
     */
    private final ManageEventPropertiesFrame manageEventPropertiesFrame;

    /**
     * A frame to create an event.
     */
    private final EventFrame eventFrame;

    /**
     * Constructs a new main frame.
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public MainFrame(Application application) {
        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if
        this.application = application;

        setTitle(application.getApplicationName());

        // These are the buttons we want on the form. Using the ButtonCollection
        // class helps streamline the addition and removal of buttons.
        ButtonCollection buttonCollection = new ButtonCollection();
        buttonCollection.addButton("Manage", "Shifts", (ActionListener) (ActionEvent e) -> { manageShiftsButtonClicked(); });
        buttonCollection.addButton("Manage", "Volunteers", (ActionListener) (ActionEvent e) -> { manageVolunteersButtonClicked(); });
        buttonCollection.addButton("Manage", "Roles", (ActionListener) (ActionEvent e) -> { manageRolesButtonClicked(); });
        buttonCollection.addButton("Manage", "Email Template", (ActionListener) (ActionEvent e) -> { manageEmailTemplateButtonClicked(); });
        buttonCollection.addButton("Manage", "Email Server", (ActionListener) (ActionEvent e) -> { manageEmailServerButtonClicked(); });
        buttonCollection.addButton("Manage", "Event Properties", (ActionListener) (ActionEvent e) -> { manageEventPropertiesButtonClicked(); });
        buttonCollection.addButton("Create", "Event", (ActionListener) (ActionEvent e) -> { createEventButtonClicked(); });
        buttonCollection.addButton("Create", "Email", (ActionListener) (ActionEvent e) -> { createEmailButtonClicked(); });
        buttonCollection.addButton("Help", "Help", (ActionListener) (ActionEvent e) -> { helpHelpButtonClicked(); });
        buttonCollection.addButton("Help", "About", (ActionListener) (ActionEvent e) -> { helpAboutButtonClicked(); });

        final int LAYOUT_COLUMNS = buttonCollection.getNumberOfGroups();
        final int LAYOUT_ROWS = buttonCollection.getMaxSizeOfGroups();
        List<NamedList<JButton>> buttonGroups = buttonCollection.getButtons();

        setLayout(new GridLayout(1, LAYOUT_COLUMNS));
        for (NamedList<JButton> buttonGroup : buttonGroups) {
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createTitledBorder(buttonGroup.getName()));
            panel.setLayout(new GridLayout(LAYOUT_ROWS, 1));
            for (JButton button : buttonGroup) {
                panel.add(button);
            }    // for
            add(panel);
        }    // for

        pack();
        Dimension packedSize = this.getSize();
        setMinimumSize(packedSize);

        manageShiftsFrame = new ManageShiftsFrame(application);
        manageShiftsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        manageVolunteersFrame = new ManageVolunteersFrame(application);
        manageVolunteersFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        manageEmailTemplateFrame = new ManageEmailTemplateFrame(application);
        manageEmailTemplateFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        manageEmailServerPropertiesFrame = new ManageEmailServerPropertiesFrame(application);
        manageEmailServerPropertiesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        manageRolesFrame = new ManageRolesFrame(application);
        manageRolesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        manageEventPropertiesFrame = new ManageEventPropertiesFrame(application);
        manageEventPropertiesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        eventFrame = new EventFrame(application);
        eventFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        assertInvariant();
    }    // MainFrame()

    /**
     * Event fired when the manage shifts button is clicked.
     */
    private void manageShiftsButtonClicked() {
        assertInvariant();
        manageShiftsFrame.setVisible(true);
        assertInvariant();
    }    // manageShiftsButtonClicked()

    /**
     * Event fired when the manage volunteers button is clicked.
     */
    private void manageVolunteersButtonClicked() {
        assertInvariant();
        manageVolunteersFrame.setVisible(true);
        assertInvariant();
    }    // manageVolunteersButtonClicked()

    /**
     * Event fired when the manage roles button is clicked.
     */
    private void manageRolesButtonClicked() {
        assertInvariant();
        manageRolesFrame.setVisible(true);
        assertInvariant();
    }    // manageRolesButtonClicked()

    /**
     * Event fired when the manage email template button is clicked.
     */
    private void manageEmailTemplateButtonClicked() {
        assertInvariant();
        manageEmailTemplateFrame.setVisible(true);
        assertInvariant();
    }    // manageEmailTemplateButtonClicked()

    /**
     * Event fired when the manage email server button is clicked.
     */
    private void manageEmailServerButtonClicked() {
        assertInvariant();
        manageEmailServerPropertiesFrame.setVisible(true);
        assertInvariant();
    }    // manageEmailTemplateButtonClicked()

    /**
     * Event fired when the manage volunteers button is clicked.
     */
    private void manageEventPropertiesButtonClicked() {
        assertInvariant();
        manageEventPropertiesFrame.setVisible(true);
        assertInvariant();
    }    // manageEventPropertiesButtonClicked()

    /**
     * Event fired when the create event button is clicked.
     */
    private void createEventButtonClicked() {
        assertInvariant();
        eventFrame.setVisible(true);
        assertInvariant();
    }    // createEventButtonClicked()

    /**
     * Event fired when the create email button is clicked.
     */
    private void createEmailButtonClicked() {
        assertInvariant();
        Event event = eventFrame.getEvent();
        DisplayEmailFrame displayFrame = new DisplayEmailFrame(application, event);
        displayFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        displayFrame.setVisible(true);
        assertInvariant();
    }    // createEmailButtonClicked()

    /**
     * Event fired when the help help button is clicked.
     */
    private void helpHelpButtonClicked() {
        assertInvariant();
        application.displayHelp();
    }    // helpHelpButtonClicked()

    /**
     * Event fired when the help about button is clicked.
     */
    private void helpAboutButtonClicked() {
        assertInvariant();
        String versionString = application.getApplicationName() + " v" + application.getVersion();
        String aboutText = application.getCopyright() + "\n\n";
        aboutText += application.getApplicationName() + " is free software: you can redistribute it and/or modify\n";
        aboutText += "it under the terms of the GNU General Public License as published by\n";
        aboutText += "the Free Software Foundation, either version 3 of the License, or\n";
        aboutText += "(at your option) any later version.\n\n";
        aboutText += application.getApplicationName() + " is distributed in the hope that it will be useful,\n";
        aboutText += "but WITHOUT ANY WARRANTY; without even the implied warranty of\n";
        aboutText += "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n";
        aboutText += "GNU General Public License for more details.\n\n";
        aboutText += "You should have received a copy of the GNU General Public License\n";
        aboutText += "along with " + application.getApplicationName() + ".  If not, see <http://www.gnu.org/licenses/>.";

        JFrame frame = new JFrame();
        frame.setTitle(application.getApplicationName() + " - About");
        Box contentPane = Box.createVerticalBox();
        contentPane.setBorder(ComponentFactory.getStandardBorder());
        frame.setContentPane(contentPane);
        JPanel panel = new JPanel();
        panel.add(new JLabel(versionString));
        frame.add(panel);
        frame.add(ComponentFactory.getStandardVerticalStrut());
        panel = new JPanel();
        JTextArea aboutTextArea = new JTextArea(aboutText);
        aboutTextArea.setEditable(false);
        panel.add(aboutTextArea);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        assertInvariant();
    }    // helpAboutButtonClicked()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert(application != null);
        assert(manageShiftsFrame != null);
        assert(manageVolunteersFrame != null);
        assert(manageEmailTemplateFrame != null);
        assert(manageEmailServerPropertiesFrame != null);
        assert(manageRolesFrame != null);
        assert(manageEventPropertiesFrame != null);
        assert(eventFrame != null);
    }    // assertInvariant()
}    // MainFrame
