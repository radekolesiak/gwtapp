package org.gwtapp.template.client.handler;

import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.FormWrapper;

public class FormHandler extends UiHandler<FormWrapper> {

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
