package org.gwtapp.template.client.handlers;

import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.ui.WrapperWidget;

public class WrapperHandler extends MessageHandler<WrapperWidget> {

	@Override
	protected WrapperWidget onWidget() {
		return new WrapperWidget(getId());
	}

	@Override
	protected WrapperWidget getDefaultWidget() {
		return new WrapperWidget();
	}
}
