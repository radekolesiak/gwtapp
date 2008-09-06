package com.cuprum.web.templates.simple.client;

import com.cuprum.web.templates.simple.client.page.Body;
import com.cuprum.web.templates.simple.client.page.Header;
import com.cuprum.web.templates.simple.client.page.Styles;
import com.cuprum.web.templates.stylesfactory.client.IStyleable;
import com.cuprum.web.templates.stylesfactory.client.StylesFactory;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * {@inheritDoc}
 */
public class Simple extends StylesFactory {
	/**
	 * {@inheritDoc}
	 */
	public final void onInit() {
		DOM.setStyleAttribute(RootPanel.getBodyElement(), "color", "#4C4C4C");
		DOM.setStyleAttribute(RootPanel.getBodyElement(), "font-size", "11px");
		DOM.setStyleAttribute(RootPanel.getBodyElement(), "font-family",
				"Trebuchet MS, \"Lucida Sans Unicode\","
						+ " Arial, Lucida Sans, Tahoma, Sans-Serif");
		DOM.setStyleAttribute(RootPanel.getBodyElement(), "text-align",
				"center");
		DOM.setStyleAttribute(RootPanel.getBodyElement(), "background",
				"#123454 url(images/01_bg.gif) top repeat-x");
		RootPanel.get().setStyleName("simple");

		StylesFactory.STYLES.put(Header.class, new IStyleable() {
			public void setupStyle(final Widget widget) {
				widget.addStyleName(Styles.STYLE_PAGE_HEADER_BG);
			}
		});
	}

	final Body body = new Body();

	/**
	 * {@inheritDoc}
	 */
	public final Widget getBody() {
		 return body;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setContent(final Widget content) {
		// Container.get().setContent(content);
		body.setContent(content);
	}

}
