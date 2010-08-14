package org.gwtapp.widgets.client.ui;

import org.gwtapp.template.client.ui.FormWrapper;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;

public class UploadFormWrapper extends FormWrapper {

	private FileUpload fileUpload;

	public UploadFormWrapper() {
		super();
		init();
	}

	public UploadFormWrapper(String id) {
		super(id, true);
		init();
	}

	public UploadFormWrapper(Element e) {
		super(e, true);
		init();
	}

	private void init() {
		// Force form parameters:
		setMethod(FormPanel.METHOD_POST);
		setEncoding(FormPanel.ENCODING_MULTIPART);
	}

	// TODO make customizable messages
	public void upload() {
		FileUpload fileUpload = getFileUpload();
		if (fileUpload != null) {
			if (fileUpload.getFilename().isEmpty()) {
				Window.alert("Please choose file.");
			} else {
				if (Window.confirm("Load this form?")) {
					try {
						submit();
					} catch (JavaScriptException e) {
						Window.alert("Please choose existing file.");
					}
				}
			}
		}
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}
}