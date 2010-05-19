package org.gwtapp.template.client.handlers;

import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.ui.FormWrapper;

public class FormHandler extends MessageHandler<FormWrapper> {

	private final boolean createFrame;

	public FormHandler() {
		this(false);
	}

	public FormHandler(boolean createFrame) {
		this.createFrame = createFrame;
	}

	@Override
	protected FormWrapper onWidget() {
		return new FormWrapper(getId(), createFrame);
	}

	@Override
	protected FormWrapper getDefaultWidget() {
		return new FormWrapper();
	}
}
