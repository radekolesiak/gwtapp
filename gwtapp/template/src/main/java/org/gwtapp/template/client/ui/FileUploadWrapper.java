package org.gwtapp.template.client.ui;

import org.gwtapp.core.client.ui.FileUpload;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class FileUploadWrapper extends FileUpload {

	public FileUploadWrapper() {
	}

	public FileUploadWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public FileUploadWrapper(Element e) {
		super(e);
	}
}
