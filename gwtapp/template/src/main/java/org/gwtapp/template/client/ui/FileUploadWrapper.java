package org.gwtapp.template.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FileUpload;

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
