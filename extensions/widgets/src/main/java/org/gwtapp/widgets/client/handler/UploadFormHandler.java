package org.gwtapp.widgets.client.handler;

import org.gwtapp.template.client.UiHandler;
import org.gwtapp.widgets.client.ui.UploadFormWrapper;


public class UploadFormHandler extends UiHandler<UploadFormWrapper> {

	public UploadFormHandler() {
		super(new UploadFormWrapper());
	}

	@Override
	protected UploadFormWrapper onWidget() {
		return new UploadFormWrapper(getId());
	}
}