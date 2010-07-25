package org.gwtapp.template.client.handler;

import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.TextAreaWrapper;

public class TextAreaHandler extends UiHandler<TextAreaWrapper> {
	@Override
	protected TextAreaWrapper onWidget() {
		return new TextAreaWrapper(getId());
	}

	@Override
	protected TextAreaWrapper getDefaultWidget() {
		return new TextAreaWrapper();
	}
}
