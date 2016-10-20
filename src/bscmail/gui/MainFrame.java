/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
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

import bscmail.Event;
import bscmail.transformer.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import main.Application;

/**
 * The main window for BSCMail.
 * 
 * @since 2.0
 * @author Wayne Miller
 */
public class MainFrame extends JFrame {

    /**
     * A frame to manage shifts.
     */
    private final ManageShiftsFrame manageShiftsFrame;
    
    /**
     * A frame to manage managers.
     */
    private final ManageManagersFrame manageManagersFrame;
    
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
     * A frame to create an event.
     */
    private final EventFrame eventFrame;
    
    /**
     * Constructs a new main frame.
     */
    public MainFrame() {
        setTitle(Application.getApplicationName());
        setLayout(new GridLayout(1, 3));
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Manage"));
        panel.setLayout(new GridLayout(5, 1));
        JButton button = new JButton("Shifts");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageShiftsButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        panel.add(button);
        button = new JButton("Managers");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageManagersButtonClicked();
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
        add(panel);
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Create"));
        panel.setLayout(new GridLayout(4, 1));
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
        panel.setLayout(new GridLayout(4, 1));
        button = new JButton("Help");
        button.setEnabled(false);
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
        
        manageShiftsFrame = new ManageShiftsFrame();
        manageShiftsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        manageManagersFrame = new ManageManagersFrame();
        manageManagersFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        manageVolunteersFrame = new ManageVolunteersFrame();
        manageVolunteersFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        manageEmailTemplateFrame = new ManageEmailTemplateFrame();
        manageEmailTemplateFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        manageRolesFrame = new ManageRolesFrame();
        manageRolesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        eventFrame = new EventFrame();
        eventFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }    // MainFrame()
    
    /**
     * Event fired when the manage shifts button is clicked.
     */
    private void manageShiftsButtonClicked() {
        manageShiftsFrame.setVisible(true);
    }    // manageShiftsButtonClicked()
    
    /**
     * Event fired when the manage managers button is clicked.
     */
    private void manageManagersButtonClicked() {
        manageManagersFrame.setVisible(true);
    }    // manageManagersButtonClicked()
    
    /**
     * Event fired when the manage volunteers button is clicked.
     */
    private void manageVolunteersButtonClicked() {
        manageVolunteersFrame.setVisible(true);
    }    // manageVolunteersButtonClicked()

    /**
     * Event fired when the manage roles button is clicked.
     */
    private void manageRolesButtonClicked() {
        manageRolesFrame.setVisible(true);
    }    // manageVolunteersButtonClicked()

    /**
     * Event fired when the manage email button is clicked.
     */
    private void manageEmailButtonClicked() {
        manageEmailTemplateFrame.setVisible(true);
    }    // manageVolunteersButtonClicked()
    
    /**
     * Event fired when the create event button is clicked.
     */
    private void createEventButtonClicked() {
        eventFrame.setVisible(true);
    }    // createEventButtonClicked()
    
    /**
     * Event fired when the create email button is clicked.
     */
    private void createEmailButtonClicked() {
        Event event = eventFrame.getEvent();
        Transformer transformer = Application.getTransformer();
        try (Reader infile = Application.getEmailTemplate()) {
            DisplayEmailFrame displayFrame = new DisplayEmailFrame(infile, transformer, event);
            displayFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            displayFrame.setVisible(true);
        } catch (IOException e) {    // try
            Application.showErrorDialog(this, "Unable to create email.", e);
        }    // catch
    }    // createEmailButtonClicked()
    
    /**
     * Event fired when the help about button is clicked.
     */
    private void helpAboutButtonClicked() {
        String versionString = Application.getApplicationName() + " v" + Application.getVersion();
        String aboutText = Application.getCopyright() + "\n\n";
        aboutText += Application.getApplicationName() + " is free software: you can redistribute it and/or modify\n";
        aboutText += "it under the terms of the GNU General Public License as published by\n";
        aboutText += "the Free Software Foundation, either version 3 of the License, or\n";
        aboutText += "(at your option) any later version.\n\n";
        aboutText += Application.getApplicationName() + " is distributed in the hope that it will be useful,\n";
        aboutText += "but WITHOUT ANY WARRANTY; without even the implied warranty of\n";
        aboutText += "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n";
        aboutText += "GNU General Public License for more details.\n\n";
        aboutText += "You should have received a copy of the GNU General Public License\n";
        aboutText += "along with " + Application.getApplicationName() + ".  If not, see <http://www.gnu.org/licenses/>.";

        final int MARGIN = 10;
        final int INTERNAL_MARGIN = MARGIN / 2;
        JFrame frame = new JFrame();
        frame.setTitle(Application.getApplicationName() + " - About");
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
        frame.setVisible(true);
    }    // helpAboutButtonClicked()
}    // MainFrame
