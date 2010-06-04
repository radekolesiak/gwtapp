package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.core.client.io.IOSubmitCompleteHandler;
import org.gwtapp.form.client.ui.TemplateModelDataFormPanel;
import org.gwtapp.startapp.client.StartAppEntryPoint;
import org.gwtapp.startapp.client.handlers.user.register.UploadFormHandler;
import org.gwtapp.startapp.shared.data.user.register.UserRegister;
import org.gwtapp.startapp.shared.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.shared.data.user.register.UserRegisterModelImpl;
import org.gwtapp.template.client.handlers.TextBoxHandler;
import org.gwtapp.template.client.handlers.WidgetHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

public class UploadDownloadTemplatePanel extends
		TemplateModelDataFormPanel<UserRegisterModel> {

	private final UploadFormHandler//
	uploadForm = add("upload-form", new UploadFormHandler());
	
	private final WidgetHandler//
	uploadBtn = add("upload-btn", new WidgetHandler());
	
	private final WidgetHandler//
	downloadBtn = add("download-btn", new WidgetHandler());
	
	private final WidgetHandler//
	clearBtn = add("clear-btn", new WidgetHandler());

	public UploadDownloadTemplatePanel(String id) {
		super(new UserRegisterModelImpl(), StartAppEntryPoint.domTemplates
				.load(id));
		add(UserRegister.LOGIN, new TextBoxHandler());
		add(UserRegister.PASSWORD, new TextBoxHandler());
		add(UserRegister.EMAIL, new TextBoxHandler());
	}

	@Override
	public void onAddFormWidgets() {
		clearBtn.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setValue(new UserRegisterModelImpl(), true);
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
		uploadForm.getWidget().addSubmitCompleteHandler(
				new IOSubmitCompleteHandler<UserRegisterModel>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert("There was a problem while uploading");
					}

					@Override
					public void onSuccessful(UserRegisterModel result) {
						setValue(result, true);
					}
				});
	}
}
