package com.cuprum.web.templates.simple.client.page;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Header of the page.
 * 
 * @author Radek Olesiak
 */
public class Header extends VerticalPanel {
	/**
	 * Header of the header.
	 * @author Radek Olesiak
	 */
	class Head extends VerticalPanel {
		/** Sets the css style. */
		public Head() {
			setStyleName(Styles.STYLE_PAGE_HEADER_HEAD);
		}
	}

	/**
	 * Logo of the header.
	 * @author Radek Olesiak
	 */
	class Logo extends VerticalPanel {
		/** Sets the css style. */
		public Logo() {
			setStyleName(Styles.STYLE_PAGE_HEADER_LOGO);
		}
	}

	/**
	 * Menu of the header.
	 * @author Radek Olesiak
	 */
	class Menu extends VerticalPanel {
		/** Sets the css style. */
		public Menu() {
			setStyleName(Styles.STYLE_PAGE_HEADER_MENU);
		}
	}

	/** Sets the css style and widgets. */
	public Header() {
		add(new Header.Head());
		add(new Header.Logo());
		add(new Header.Menu());
		setStyleName(Styles.STYLE_PAGE_HEADER);
	}
}
