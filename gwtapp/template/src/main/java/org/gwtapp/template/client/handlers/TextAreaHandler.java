package org.gwtapp.template.client.handlers;

import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.ui.TextAreaWrapper;

public class TextAreaHandler extends MessageHandler<TextAreaWrapper> {
	@Override
	protected TextAreaWrapper onWidget() {
		return new TextAreaWrapper(getId());
	}

	@Override
	protected TextAreaWrapper getDefaultWidget() {
		return new TextAreaWrapper();
	}
}
