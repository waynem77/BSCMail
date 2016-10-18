/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
        panel.setLayout(new GridLayout(4, 1));
        JButton button = new JButton("Shifts");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageShiftsButtonClicked();
            }    // actionPerformed()
        });    // addActionListever()
        panel.add(button);
        button = new JButton("Managers");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageManagersButtonClicked();
            }    // actionPerformed()
        });    // addActionListever()
        panel.add(button);
        button = new JButton("Volunteers");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageVolunteersButtonClicked();
            }    // actionPerformed()
        });    // addActionListever()
        panel.add(button);
        button = new JButton("Email");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                manageEmailButtonClicked();
            }    // actionPerformed()
        });    // addActionListever()
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
        });    // addActionListever()
        panel.add(button);
        button = new JButton("Email");
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                createEmailButtonClicked();
            }    // actionPerformed()
        });    // addActionListever()
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
        });    // addActionListever()
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
     * Event fired when the manage email button is clicked.
     */
    private void manageEmailButtonClicked() {
        try (Reader infile = Application.getEmailTemplate()) {
            ManageTemplateFrame manageTemplateFrame = new ManageTemplateFrame(infile);
            manageTemplateFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            manageTemplateFrame.setVisible(true);
        } catch (IOException e) {    // try
            Application.showErrorDialog(this, "Unable to read email template.", e);
        }    // catch
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
        final int MARGIN = 10;
        final int INTERNAL_MARGIN = MARGIN / 2;
        JFrame frame = new JFrame();
        frame.setTitle(Application.getApplicationName() + " - About");
        Box contentPane = Box.createVerticalBox();
        contentPane.setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
        frame.setContentPane(contentPane);
        JPanel panel = new JPanel();
        panel.add(new JLabel(Application.getApplicationName() + " v" + Application.getVersion()));
        frame.add(panel);
        frame.add(Box.createVerticalStrut(INTERNAL_MARGIN));
        panel = new JPanel();
        panel.add(new JLabel(Application.getCopyright()));
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }    // helpAboutButtonClicked()
}    // MainFrame
