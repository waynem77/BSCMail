/*
 * Copyright © 2016-2018 its authors.  See the file "AUTHORS" for details.
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
import bscmail.EmailTemplate;
import bscmail.gui.util.LabeledGrid;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
     * The pre-schedule text text area.
     */
    final JTextArea preScheduleTextArea;

    /**
     * The post-schedule text text area.
     */
    final JTextArea postScheduleTextArea;

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
        setTitle(application.getApplicationName() + " - Manage Email");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        LabeledGrid labeledGrid = new LabeledGrid();
        add(labeledGrid);

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
        preScheduleTextArea.setText(emailTemplate.getPreScheduleText());
        postScheduleTextArea.setText(emailTemplate.getPostScheduleText());

        preScheduleTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                textAreasChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                textAreasChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                textAreasChanged();
            }    // changedUpdate()
        });    // addDocumentListener()
        postScheduleTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                textAreasChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                textAreasChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                textAreasChanged();
            }    // changedUpdate()
        });    // addDocumentListener()
    }    // ManageEmailTemplateFrame()

    /**
     * Event fired when either of the text areas change.  All changes are passed
     * back to the {@link Application}.
     */
    private void textAreasChanged() {
        EmailTemplate emailTemplate = new EmailTemplate(preScheduleTextArea.getText(), postScheduleTextArea.getText(), "");
        try {
            application.setEmailTemplate(emailTemplate);
        } catch (IOException e) {
        }
    }    // textAreasChanged()

}    // ManageEmailTemplateFrame
