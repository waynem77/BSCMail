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

import bscmail.Application;
import bscmail.*;
import java.io.*;
import java.util.*;
import main.*;

/**
 * A graphical interface to manage the list of event properties in
 * {@link Application}.
 *
 * @since 3.0
 * @author Chaitra Mayya
 */
public class ManageEventPropertiesFrame extends ManageListFrame<EventProperty> {

  /**
   * Constructs a new manage event properties frame.
   */
  public ManageEventPropertiesFrame() {
    super(
        new ManageEventPropertiesPanel(),
        new Vector<>(Application.getEventProperties()),
        new Comparator<EventProperty>(){
          @Override public int compare(EventProperty EventProperty1, EventProperty EventProperty2) {
            assert (EventProperty1 != null);
            assert (EventProperty2 != null);
            return EventProperty1.toString().compareTo(EventProperty2.toString());
          }    // compare()
        }    // Comparator
    );

    setTitle(Application.getApplicationName() + " - Manage Event Properties");
  }    // ManageVolunteersFrame()

  /**
   * Saves the given list as the defined list of event properties.
   *
   * @param eventProperties properties to save; may not be null
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void setListDataHook(List<EventProperty> eventProperties) throws IOException {
    assert (eventProperties != null);
    Application.setEventProperties(eventProperties);
  }    // saveListData()
} //ManageEventPropertiesFrame