/*
 * Copyright © 2014-2017 its authors.  See the file "AUTHORS" for details.
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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
     * A frame to manage volunteers.
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
     * The number of rows in the layout.  This should be equal to the maximum
     * number of buttons in an individual column.
     */
    private final int LAYOUT_ROWS = 5;

    /**
     * The number of columns in the layout.
     */
    private final int LAYOUT_COLUMNS = 3;

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
        setLayout(new GridLayout(1, LAYOUT_COLUMNS));

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Manage"));
        panel.setLayout(new GridLayout(LAYOUT_ROWS, 1));
        JButton button = new JButton("Shifts");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageShiftsButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);

        button = new JButton("Volunteers");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageVolunteersButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);
        button = new JButton("Roles");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageRolesButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);

        button = new JButton("Email");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageEmailButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);

        button = new JButton("Event Properties");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageEventPropertiesButtonClicked();
            }    // actionPerformed()
        });    // addActionListever()
        panel.add(button);
        add(panel);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Create"));
        panel.setLayout(new GridLayout(LAYOUT_ROWS, 1));
        button = new JButton("Event");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                createEventButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);
        button = new JButton("Email");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                createEmailButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);
        add(panel);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Help"));
        panel.setLayout(new GridLayout(LAYOUT_ROWS, 1));
        button = new JButton("Help");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                helpHelpButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);
        button = new JButton("About");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                helpAboutButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);
        panel.add(button);
        add(panel);

        pack();
        Dimension packedSize = this.getSize();
        setMinimumSize(packedSize);

        manageShiftsFrame = new ManageShiftsFrame(application);
        manageShiftsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        manageVolunteersFrame = new ManageVolunteersFrame(application);
        manageVolunteersFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        manageEmailTemplateFrame = new ManageEmailTemplateFrame(application);
        manageEmailTemplateFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
     * Event fired when the manage email button is clicked.
     */
    private void manageEmailButtonClicked() {
        assertInvariant();
        manageEmailTemplateFrame.setVisible(true);
        assertInvariant();
    }    // manageVolunteersButtonClicked()

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

        final int MARGIN = 10;
        final int INTERNAL_MARGIN = MARGIN / 2;
        JFrame frame = new JFrame();
        frame.setTitle(application.getApplicationName() + " - About");
        Box contentPane = Box.createVerticalBox();
        contentPane.setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
        frame.setContentPane(contentPane);
        JPanel panel = new JPanel();
        panel.add(new JLabel(versionString));
        frame.add(panel);
        frame.add(Box.createVerticalStrut(INTERNAL_MARGIN));
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
        assert(manageRolesFrame != null);
        assert(manageEventPropertiesFrame != null);
        assert(eventFrame != null);
    }    // assertInvariant()
}    // MainFrame
