package org.gwtapp.startapp.client.handlers.user.register;

import org.gwtapp.startapp.client.ui.user.register.UploadFormWrapper;
import org.gwtapp.template.client.handler.UiHandler;

public class UploadFormHandler extends UiHandler<UploadFormWrapper> {

	public UploadFormHandler() {
		super(new UploadFormWrapper());
	}

	@Override
	protected UploadFormWrapper onWidget() {
		return new UploadFormWrapper(getId());
	}
}
