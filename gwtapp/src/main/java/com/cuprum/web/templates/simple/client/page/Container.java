package com.cuprum.web.templates.simple.client.page;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Container of the page context.
 * @author Radek Olesiak 
 */
public class Container extends VerticalPanel {
	/** Singleton of the Container class. */
	private static Container singleton = null;

	/** @return Returns singleton of the Container class. */
	public static Container get() {
		if (singleton == null) {
			singleton = new Container();
		}
		return singleton;
	}

	/**  The middle layer of the widgets tree. */
	private final Middle middle = new Middle();

	/** Sets the css style. */
	public Container() {
		add(new Header());
		add(middle);
		add(new Footer());
		setStyleName(Styles.STYLE_PAGE_CONTAINER);
	}

	/**
	 * @param content
	 *            Content of the page. Sets the content. Sets the css style of
	 *            the content.
	 */
	public final void setContent(final Widget content) {		
		middle.setContent(content);
	}
}
