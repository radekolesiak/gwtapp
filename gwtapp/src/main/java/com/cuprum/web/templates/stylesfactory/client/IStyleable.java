package com.cuprum.web.templates.stylesfactory.client;

import com.google.gwt.user.client.ui.Widget;

/**
 *  Styles modifier.
 * @author Radek Olesiak
 */
public interface IStyleable {
	/**
	 *  Modifies style of the widget.
	 *  @param widget Widget to modify its styles
	 */
	void setupStyle(Widget widget);
}
