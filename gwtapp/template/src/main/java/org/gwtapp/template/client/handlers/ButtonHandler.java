package org.gwtapp.template.client.handlers;

import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.ui.ButtonWrapper;

public class ButtonHandler extends MessageHandler<ButtonWrapper> {
	@Override
	protected ButtonWrapper onWidget() {
		return new ButtonWrapper(getId());
	}

	@Override
	protected ButtonWrapper getDefaultWidget() {
		return new ButtonWrapper();
	}
}
