package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.io.client.IOSubmitCompleteHandler;
import org.gwtapp.startapp.client.StartAppEntryPoint;
import org.gwtapp.startapp.client.handlers.user.register.UploadFormHandler;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;
import org.gwtapp.template.client.handler.FileUploadHandler;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.handler.WidgetHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

public class UploadDownloadTemplatePanel extends
		TemplateModelPanel<UserRegisterModel> {

	private final UploadFormHandler//
	uploadForm = add("upload-form", new UploadFormHandler());

	private final FileUploadHandler//
	fileUpload = add("upload-file", new FileUploadHandler());

	private final WidgetHandler//
	uploadBtn = add("upload-btn", new WidgetHandler());

	private final WidgetHandler//
	downloadBtn = add("download-btn", new WidgetHandler());

	private final WidgetHandler//
	clearBtn = add("clear-btn", new WidgetHandler());

	public UploadDownloadTemplatePanel(String id) {
		this(new UserRegisterModelImpl(), id);
	}

	public UploadDownloadTemplatePanel(UserRegisterModel model, String id) {
		super(StartAppEntryPoint.html.load(id), model);
		add(UserRegister.LOGIN, new TextBoxHandler());
		add(UserRegister.PASSWORD, new TextBoxHandler());
		add(UserRegister.EMAIL, new TextBoxHandler());
	}

	@Override
	public void onAddFormWidgets() {
		clearBtn.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setValue(getEmptyModel(), true);
			}
		});
		downloadBtn.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				StartAppEntryPoint.downloader.download(getValue(),
						StartAppEntryPoint.callback);
			}
		});
		uploadBtn.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				uploadForm.getWidget().upload();
			}
		});
		uploadForm.getWidget().setFileUpload(fileUpload.getWidget());
		uploadForm.getWidget().addSubmitCompleteHandler(
				new IOSubmitCompleteHandler<UserRegisterModel>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert("There was a problem while uploading");
					}

					@Override
					public void onSuccess(UserRegisterModel result) {
						setValue(result, true);
					}
				});
	}

	private UserRegisterModel getEmptyModel() {
		UserRegisterModelImpl model = new UserRegisterModelImpl();
		for (String property : model.getPropertyNames()) {
			model.set(property, null);
		}
		return model;
	}
}
