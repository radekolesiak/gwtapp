package org.gwtapp.template.client.handlers;

import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.ui.FormWrapper;

public class FormHandler extends MessageHandler<FormWrapper> {
	@Override
	protected FormWrapper onWidget() {
		return new FormWrapper(getId());
	}

	@Override
	protected FormWrapper getDefaultWidget() {
		return new FormWrapper();
	}
}
