package com.cuprum.web.common.client;

import com.cuprum.web.common.client.data.TValue;
import com.google.gwt.user.client.ui.Widget;

public interface IValueRenderer {
	@SuppressWarnings("unchecked")
	Widget getWidget(TValue value);
}
