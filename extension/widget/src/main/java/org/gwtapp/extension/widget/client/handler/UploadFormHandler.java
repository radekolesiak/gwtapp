package org.gwtapp.extension.widget.client.handler;

import org.gwtapp.extension.widget.client.ui.UploadFormWrapper;
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