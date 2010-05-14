package org.gwtapp.template.client.handlers;

import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.ui.TextBoxWrapper;

public class TextBoxHandler extends MessageHandler<TextBoxWrapper> {
	@Override
	protected TextBoxWrapper onWidget() {
		return new TextBoxWrapper(getId());
	}

	@Override
	protected TextBoxWrapper getDefaultWidget() {
		return new TextBoxWrapper();
	}
}
