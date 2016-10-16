/*
 * Copyright © 2014 Wayne Miller
 */

package main;

/**
 * A class may implement the {@code EmailTemplateObserver} interface when it
 * wants to be informed of changes to the defined email template.
 *
 * @see Application
 * @since 2.0
 * @author Wayne Miller
 */
public interface EmailTemplateObserver {

    /**
     * This method is called whenever the defined email template changes.
     */
    public void emailTemplateChanged();

}    // EmailTemplateObserver
