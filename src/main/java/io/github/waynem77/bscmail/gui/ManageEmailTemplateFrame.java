/*
 * Copyright © 2016-2019 its authors.  See the file "AUTHORS" for details.
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
package io.github.waynem77.bscmail.gui;

import io.github.waynem77.bscmail.gui.util.ComponentFactory;
import io.github.waynem77.bscmail.gui.util.EnumRadioPanel;
import io.github.waynem77.bscmail.gui.util.LabeledGrid;
import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.persistent.EmailTemplate;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A graphical interface to manage email template defined in
 * {@link Application}.
 *
 * @since 3.0
 * @author Wayne Miller
 */
public class ManageEmailTemplateFrame extends JFrame {

    /**
     * The capping application.
     */
    final Application application;

    /**
     * The send type panel.
     */
    final EnumRadioPanel<EmailTemplate.SendType> sendTypePanel;

    /**
     * The pre-schedule text text area.
     */
    final JTextArea preScheduleTextArea;

    /**
     * The post-schedule text text area.
     */
    final JTextArea postScheduleTextArea;

    /**
     * The subject line template text field.
     */
    final JTextField subjectLineTemplateTextField;

    /**
     * The date format string text field.
     */
    final JTextField dateFormatStringTextField;
    /**
     * Constructs a new manage email template frame.
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public ManageEmailTemplateFrame(Application application) {
        final int MIN_TEXT_AREA_COLS = 10;
        final int MIN_TEXT_AREA_ROWS = 2;
        final int TEXT_AREA_COLS = 40;
        final int TEXT_AREA_ROWS = 12;

        this.application = application;
        setTitle(application.getApplicationName() + " - Manage Email Template");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        LabeledGrid labeledGrid = new LabeledGrid();
        labeledGrid.setBorder(ComponentFactory.getStandardBorder());
        add(labeledGrid);

        sendTypePanel = new EnumRadioPanel<>(EmailTemplate.SendType.class);
        labeledGrid.addLabelAndComponent("Place emails in:", sendTypePanel);

        preScheduleTextArea = new JTextArea(MIN_TEXT_AREA_ROWS, MIN_TEXT_AREA_COLS);
        preScheduleTextArea.setLineWrap(true);
        preScheduleTextArea.setWrapStyleWord(true);
        JScrollPane preScheduleScrollPane = new JScrollPane(preScheduleTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        labeledGrid.addLabelAndComponent("Pre-schedule text:", preScheduleScrollPane, true);

        postScheduleTextArea = new JTextArea(MIN_TEXT_AREA_ROWS, MIN_TEXT_AREA_COLS);
        postScheduleTextArea.setLineWrap(true);
        postScheduleTextArea.setWrapStyleWord(true);
        JScrollPane postScheduleScrollPane = new JScrollPane(postScheduleTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        labeledGrid.addLabelAndComponent("Post-schedule text:", postScheduleScrollPane, true);

        subjectLineTemplateTextField = new JTextField();
        labeledGrid.addLabelAndComponent("Subject line template:", subjectLineTemplateTextField);

        dateFormatStringTextField = new JTextField();
        labeledGrid.addLabelAndComponent("Date format string:", dateFormatStringTextField);

        // Setting frame and text area sizes.
        //   1. Create the text areas at their desired minimum rows and columns
        //      and set the minimum size of the frame. (Other techniques for
        //      setting the minimum size did not work well.)
        //   2. Alter the rows and columns of the text areas to their preferred
        //      starting values and set the size of the frame.
        //   3. Set the text area rows back to their minimum values. This
        //      prevents scroll bars from apearing when the height of the frame
        //      is lessened but there is no text to scroll to.
        pack();
        Dimension minimumSize = getSize();
        setMinimumSize(minimumSize);
        preScheduleTextArea.setRows(TEXT_AREA_ROWS);
        preScheduleTextArea.setColumns(TEXT_AREA_COLS);
        postScheduleTextArea.setRows(TEXT_AREA_ROWS);
        postScheduleTextArea.setColumns(TEXT_AREA_COLS);
        pack();
        preScheduleTextArea.setRows(MIN_TEXT_AREA_ROWS);
        postScheduleTextArea.setRows(MIN_TEXT_AREA_ROWS);

        EmailTemplate emailTemplate = application.getEmailTemplate();
        sendTypePanel.setSelection(emailTemplate.getSendType());
        preScheduleTextArea.setText(emailTemplate.getPreScheduleText());
        postScheduleTextArea.setText(emailTemplate.getPostScheduleText());
        subjectLineTemplateTextField.setText(emailTemplate.getSubjectLineTemplate());
        dateFormatStringTextField.setText(emailTemplate.getDateFormatString());

        sendTypePanel.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                valuesChanged();
            }    // actionPerformed()
        });    // addActionLisener()
        preScheduleTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                valuesChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                valuesChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                valuesChanged();
            }    // changedUpdate()
        });    // addDocumentListener()
        postScheduleTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                valuesChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                valuesChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                valuesChanged();
            }    // changedUpdate()
        });    // addDocumentListener()
        subjectLineTemplateTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                valuesChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                valuesChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                valuesChanged();
            }    // changedUpdate()
        });    // addDocumentListener()
        dateFormatStringTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                valuesChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                valuesChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                valuesChanged();
            }    // changedUpdate()
        });    // addDocumentListener()
    }    // ManageEmailTemplateFrame()

    /**
     * Event fired when either of the text areas change.  All changes are passed
     * back to the {@link Application}.
     */
    private void valuesChanged() {
        EmailTemplate.SendType sendType = sendTypePanel.getSelection();
        String preScheduleText = preScheduleTextArea.getText();
        String postScheduleText = postScheduleTextArea.getText();
        String subjectLineTemplate = subjectLineTemplateTextField.getText();
        String dateFormatString = dateFormatStringTextField.getText();
        try {
            dateFormatStringTextField.setBackground(Color.WHITE);
        } catch (Exception e) {    // try
            dateFormatString = "";
            dateFormatStringTextField.setBackground(Color.PINK);
        }    // catch
        EmailTemplate emailTemplate = new EmailTemplate(sendType, preScheduleText, postScheduleText, subjectLineTemplate, dateFormatString);
        try {
            application.setEmailTemplate(emailTemplate);
        } catch (IOException e) {
        }
    }    // valuesChanged()

}    // ManageEmailTemplateFrame
