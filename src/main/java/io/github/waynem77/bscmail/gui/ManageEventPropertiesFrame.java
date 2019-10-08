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

package io.github.waynem77.bscmail.gui;

import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.persistent.EventProperty;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

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
   *
   * @param application the calling application; may not be null
   * @throws NullPointerException if {@code application} is null
   */
  public ManageEventPropertiesFrame(Application application) {
    super(
        application,
        new ManageEventPropertiesPanel(),
        new Vector<>(application.getEventProperties()),    // may throw
        new Comparator<EventProperty>(){
          @Override public int compare(EventProperty EventProperty1, EventProperty EventProperty2) {
            assert (EventProperty1 != null);
            assert (EventProperty2 != null);
            return EventProperty1.toString().compareTo(EventProperty2.toString());
          }    // compare()
        }    // Comparator
    );

    setTitle(application.getApplicationName() + " - Manage Event Properties");
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
    application.setEventProperties(eventProperties);
  }    // saveListData()
} //ManageEventPropertiesFrame