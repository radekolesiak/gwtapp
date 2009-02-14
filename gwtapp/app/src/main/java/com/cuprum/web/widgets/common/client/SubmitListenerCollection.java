package com.cuprum.web.widgets.common.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Widget;

public class SubmitListenerCollection extends ArrayList<SubmitListener> {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = -2036034618683437896L;

	public void fireSubmitListener(Widget sender) {
		for (SubmitListener listener : this) {
			listener.onSubmit(sender);
		}
	}
}
