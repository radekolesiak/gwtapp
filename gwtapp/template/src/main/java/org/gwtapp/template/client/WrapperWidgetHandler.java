package org.gwtapp.template.client;

import org.gwtapp.template.client.ui.WrapperWidget;

public class WrapperWidgetHandler extends MessageWidgetHandler<WrapperWidget> {
	@Override
	protected WrapperWidget onWidget() {
		return new WrapperWidget(getId());
	}
}
