package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.form.client.ui.TemplateModelDataFormPanel;
import org.gwtapp.startapp.client.StartAppEntryPoint;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;
import org.gwtapp.template.client.handlers.TextBoxHandler;
import org.gwtapp.template.client.handlers.WidgetHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class UploadDownloadTemplatePanel extends
		TemplateModelDataFormPanel<UserRegisterModel> {

	private final WidgetHandler clear = add("clear-btn", new WidgetHandler());

	public UploadDownloadTemplatePanel(String id) {
		super(new UserRegisterModelImpl(), StartAppEntryPoint.domTemplates
				.load(id));
		add(UserRegister.LOGIN, new TextBoxHandler());
		add(UserRegister.PASSWORD, new TextBoxHandler());
		add(UserRegister.EMAIL, new TextBoxHandler());
	}

	@Override
	public void onAddFormWidgets() {
		clear.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setValue(new UserRegisterModelImpl(), true);
			}
		});
	}
}
