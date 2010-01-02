package org.gwtapp.startapp.client.ui;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;

public class UploadPanel extends FormPanel {

	public static class Style {
		public final static String UPLOAD = "upload";
		public final static String FILE = "file";
	}

	public final static String ACTION = "/gwt.startappentry/upload.rpc";

	private final Button upload = new Button("Upload");
	private final FlowPanel panel = new FlowPanel();
	private final FileUpload fileUpload = new FileUpload();
	private final FlowPanel file = new FlowPanel();

	public UploadPanel() {
		addStyleName(Style.UPLOAD);
		file.addStyleName(Style.FILE);
		setMethod(FormPanel.METHOD_POST);
		setEncoding(FormPanel.ENCODING_MULTIPART);
		setAction(ACTION);
		fileUpload.setName("GwtAppFile");
		upload.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
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
		});
		panel.add(fileUpload);
		panel.add(upload);
		setWidget(panel);
	}
}