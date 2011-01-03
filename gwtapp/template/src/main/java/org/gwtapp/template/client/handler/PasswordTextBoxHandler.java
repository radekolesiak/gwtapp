package org.gwtapp.template.client.handler;

import org.gwtapp.template.client.ui.PasswordTextBoxWrapper;

public class PasswordTextBoxHandler extends UiHandler<PasswordTextBoxWrapper> {
	@Override
	protected PasswordTextBoxWrapper onWidget() {
		return new PasswordTextBoxWrapper(getId());
	}

	@Override
	protected PasswordTextBoxWrapper getDefaultWidget() {
		return new PasswordTextBoxWrapper();
	}
}
