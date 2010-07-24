package org.gwtapp.template.client.handlers;

import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.TextBoxWrapper;

public class TextBoxHandler extends UiHandler<TextBoxWrapper> {
	@Override
	protected TextBoxWrapper onWidget() {
		return new TextBoxWrapper(getId());
	}

	@Override
	protected TextBoxWrapper getDefaultWidget() {
		return new TextBoxWrapper();
	}
}
