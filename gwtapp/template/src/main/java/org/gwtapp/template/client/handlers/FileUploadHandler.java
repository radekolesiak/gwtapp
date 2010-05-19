package org.gwtapp.template.client.handlers;

import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.ui.FileUploadWrapper;

public class FileUploadHandler extends MessageHandler<FileUploadWrapper> {
	@Override
	protected FileUploadWrapper onWidget() {
		return new FileUploadWrapper(getId());
	}

	@Override
	protected FileUploadWrapper getDefaultWidget() {
		return new FileUploadWrapper();
	}
}
