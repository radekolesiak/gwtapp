package com.cuprum.web.templates.stylesfactory.client;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Styles modifier.
 * 
 * @author Radek Olesiak
 */
public abstract class StylesFactory {
	/**
	 * Definition of hash map.
	 * 
	 * @author Radek Olesiak
	 */
	public static class Widget2IStyleable extends HashMap<Class, IStyleable> {
		/** UID. */
		private static final long serialVersionUID = -969293334654261912L;
	}

	/** Styles modifiers by widget. */
	public static final Widget2IStyleable STYLES = new Widget2IStyleable();

	/**
	 * Applies body from the factory to the RootPanel. Applies styles by STYLES
	 * map.
	 * 
	 * @param factory
	 *            The style factory.
	 * @return Returns style factory
	 */
	public static StylesFactory getStyle(final StylesFactory factory) {
		if (factory != null) {
			return factory.run();
		} else {
			return null;
		}
	}

	public StylesFactory run() {
		final Widget body = getBody();
		RootPanel.get("slot").clear();
		RootPanel.get("slot").add(body);
		onInit();
		setupStyles(body);
		return this;
	}

	/**
	 * Applies styles by STYLES map.
	 * 
	 * @param root
	 *            The root widget.
	 */
	public final void setupStyles(final Widget root) {
		if (root == null) {
			return;
		}

		IStyleable styleable = STYLES.get(root.getClass());

		if (styleable != null) {
			styleable.setupStyle(root);
		}

		if (root instanceof HasWidgets) {
			Iterator<Widget> it = ((HasWidgets) root).iterator();
			while (it.hasNext()) {
				setupStyles(it.next());
			}
		}
	}

	/** Method called by getStyle(). */
	public void onInit() {
	}

	/**
	 * The body of the factory.
	 * 
	 * @return Returns root of the factory.
	 */
	public abstract Widget getBody();

	/**
	 * Sets content.
	 * 
	 * @param content
	 *            The content to set.
	 */
	public abstract void setContent(Widget content);
}
