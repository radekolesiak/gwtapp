package org.gwtapp.template.client.handler;

import org.gwtapp.template.client.ui.ButtonWrapper;

public class ButtonHandler extends UiHandler<ButtonWrapper> {
	@Override
	protected ButtonWrapper onWidget() {
		return new ButtonWrapper(getId());
	}

	@Override
	protected ButtonWrapper getDefaultWidget() {
		return new ButtonWrapper();
	}
}
