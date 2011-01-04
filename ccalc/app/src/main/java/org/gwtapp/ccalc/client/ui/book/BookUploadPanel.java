package org.gwtapp.ccalc.client.ui.book;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.extension.widget.client.handler.UploadFormHandler;
import org.gwtapp.io.client.IOSubmitCompleteHandler;
import org.gwtapp.template.client.handler.FileUploadHandler;
import org.gwtapp.template.client.handler.WidgetHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

public class BookUploadPanel extends TemplatePanel<Book> {

	private final WidgetHandler uploadButton = add("uploadButton", new WidgetHandler());
	private final UploadFormHandler uploadForm = add("formPanel", new UploadFormHandler());
	private final FileUploadHandler fileUpload = add("fileUpload", new FileUploadHandler());

	public BookUploadPanel() {
		super(CCalc.templateService.load("book/BookUploadPanel.jsp"));
	}

	@Override
	public void onWidgets() {
		uploadForm.getWidget().setFileUpload(fileUpload.getWidget());
		uploadButton.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				upload();
			}
		});
		fileUpload.getWidget().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				upload();
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
	
	private void upload(){
		uploadForm.getWidget().upload();
	}
}
