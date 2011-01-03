package org.gwtapp.template.client.handler;

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
