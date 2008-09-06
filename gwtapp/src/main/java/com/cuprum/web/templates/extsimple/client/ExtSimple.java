package com.cuprum.web.templates.extsimple.client;

import com.cuprum.web.templates.extsimple.client.page.Styles;
import com.cuprum.web.templates.simple.client.Simple;
import com.cuprum.web.templates.simple.client.page.Header;
import com.cuprum.web.templates.stylesfactory.client.IStyleable;
import com.cuprum.web.templates.stylesfactory.client.StylesFactory;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ExtSimple extends StylesFactory {
	/** The delegated factory. */
	private final StylesFactory delegated = new Simple();

	/**
	 * {@inheritDoc}
	 */
	public final void onInit() {
		delegated.onInit();

		RootPanel.get().addStyleName("extsimple");

		StylesFactory.STYLES.put(Header.class, new IStyleable() {
			public void setupStyle(final Widget widget) {
				String[] styles = { 
					Styles.STYLE_PAGE_HEADER_BG0,
					Styles.STYLE_PAGE_HEADER_BG1,
					Styles.STYLE_PAGE_HEADER_BG2,
					Styles.STYLE_PAGE_HEADER_BG3 
				};
				int background = (int) (Math.random() * (double) styles.length);
				widget.addStyleName(styles[background]);
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Widget getBody() {
		return delegated.getBody();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setContent(final Widget content) {
		delegated.setContent(content);
	}
}
