package org.gwtapp.template.client.handlers;

import org.gwtapp.template.client.UiHandler;
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
