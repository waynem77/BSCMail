/*
 * Copyright © 2016 its authors.  See the file "AUTHORS" for details.
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

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import main.Application;

/**
 * A graphical interface to manage email template defined in
 * {@link Application}.
 * 
 * @since 3.0
 * @author Wayne Miller
 */
public class ManageEmailTemplateFrame extends JFrame {

    /**
     * Constructs a new manage email template frame.
     */
    public ManageEmailTemplateFrame() {
        final int TEXT_AREA_COLS = 40;
        final int TEXT_AREA_ROWS = 12;

        setTitle(Application.getApplicationName() + " - Manage Email");

        JLabel preScheduleLabel = new JLabel("Pre-schedule text:");
        JTextArea preScheduleTextArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLS);
        preScheduleTextArea.setLineWrap(true);
        preScheduleTextArea.setWrapStyleWord(true);
        JScrollPane preScheduleScrollPane = new JScrollPane(preScheduleTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JLabel postScheduleLabel = new JLabel("Post-schedule text:");
        JTextArea postScheduleTextArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLS);
        postScheduleTextArea.setLineWrap(true);
        postScheduleTextArea.setWrapStyleWord(true);
        JScrollPane postScheduleScrollPane = new JScrollPane(postScheduleTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(preScheduleLabel)
                    .addComponent(postScheduleLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(preScheduleScrollPane)
                    .addComponent(postScheduleScrollPane))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(preScheduleLabel)
                    .addComponent(preScheduleScrollPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(postScheduleLabel)
                    .addComponent(postScheduleScrollPane))
        );
        pack();
    }    // ManageEmailTemplateFrame()

}    // ManageEmailTemplateFrame
