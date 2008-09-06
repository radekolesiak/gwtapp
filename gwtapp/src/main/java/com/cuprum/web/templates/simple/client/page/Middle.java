package com.cuprum.web.templates.simple.client.page;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Container of the page context.
 * @author Radek Olesiak 
 */
public class Middle extends HorizontalPanel {
	/** Sets the css style and widgets. */
	public Middle() {
		add(new Left());
		add(contentContainer);
		add(new Right());
		setStyleName(Styles.STYLE_PAGE_MIDDLE);
	}

	/** The artifical container for the content. */
	private final VerticalPanel contentContainer = new VerticalPanel();
	
	/**
	 * @param content
	 *            Content of the page. Sets the content. Sets the css style of
	 *            the content.
	 */
	public final void setContent(final Widget content) {
		contentContainer.clear();
		contentContainer.add(content);
	}	
}
