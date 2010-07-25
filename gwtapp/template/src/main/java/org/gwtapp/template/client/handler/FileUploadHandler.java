package org.gwtapp.template.client.handler;

import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.FileUploadWrapper;

public class FileUploadHandler extends UiHandler<FileUploadWrapper> {
	@Override
	protected FileUploadWrapper onWidget() {
		return new FileUploadWrapper(getId());
	}

	@Override
	protected FileUploadWrapper getDefaultWidget() {
		return new FileUploadWrapper();
	}
}
