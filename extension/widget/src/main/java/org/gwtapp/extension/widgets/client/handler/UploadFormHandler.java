package org.gwtapp.extension.widgets.client.handler;

import org.gwtapp.extension.widgets.client.ui.UploadFormWrapper;
import org.gwtapp.template.client.UiHandler;


public class UploadFormHandler extends UiHandler<UploadFormWrapper> {

	public UploadFormHandler() {
		super(new UploadFormWrapper());
	}

	@Override
	protected UploadFormWrapper onWidget() {
		return new UploadFormWrapper(getId());
	}
}