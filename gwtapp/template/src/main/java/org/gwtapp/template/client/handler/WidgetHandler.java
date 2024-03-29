package org.gwtapp.template.client.handler;

import org.gwtapp.template.client.ui.WidgetWrapper;

public class WidgetHandler extends UiHandler<WidgetWrapper> {

	@Override
	protected WidgetWrapper onWidget() {
		return new WidgetWrapper(getId());
	}

	@Override
	protected WidgetWrapper getDefaultWidget() {
		return new WidgetWrapper();
	}
}
