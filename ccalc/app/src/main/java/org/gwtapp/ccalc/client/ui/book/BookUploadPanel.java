package org.gwtapp.ccalc.client.ui.book;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.extension.widget.client.handler.UploadFormHandler;
import org.gwtapp.io.client.IOSubmitCompleteHandler;
import org.gwtapp.template.client.handler.FileUploadHandler;
import org.gwtapp.template.client.handler.WidgetHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

public class BookUploadPanel extends TemplatePanel<Book> {

	private final WidgetHandler uploadButton = new WidgetHandler();
	private final UploadFormHandler uploadForm = new UploadFormHandler();
	private final FileUploadHandler fileUpload = new FileUploadHandler();

	public BookUploadPanel() {
		super(CCalc.templateService.load("book/BookUploadPanel.jsp"));
		add("formPanel", uploadForm);
		add("fileUpload", fileUpload);
		add("uploadButton", uploadButton);
	}

	@Override
	public void onAddWidgets() {
		uploadForm.getWidget().setFileUpload(fileUpload.getWidget());
		uploadButton.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				uploadForm.getWidget().upload();
			}
		});
		uploadForm.getWidget().addSubmitCompleteHandler(
				new IOSubmitCompleteHandler<Book>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert("There was a problem while uploading");
					}

					@Override
					public void onSuccess(Book result) {
						setValue(result, true);
					}
				});
	}
}
