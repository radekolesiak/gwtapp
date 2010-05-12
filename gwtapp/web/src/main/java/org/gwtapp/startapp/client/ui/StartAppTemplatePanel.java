package org.gwtapp.startapp.client.ui;

import org.gwtapp.form.client.ui.TemplateModelDataFormPanel;
import org.gwtapp.startapp.client.StartApp;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;
import org.gwtapp.template.client.MessageHandler;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;

public class StartAppTemplatePanel extends TemplateModelDataFormPanel<UserRegisterModel> {

	private final MessageHandler<LoginTemplatePanel> login = new MessageHandler<LoginTemplatePanel>(
			new LoginTemplatePanel());
	private final MessageHandler<TextBox> password = new MessageHandler<TextBox>(
			new TextBox());
	private final MessageHandler<TextBox> email = new MessageHandler<TextBox>(
			new TextBox());

	public StartAppTemplatePanel() {
		super(new UserRegisterModelImpl(), StartApp.syncTemplateService
				.load("startapp.jsp"));

		addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
			@Override
			public void onValueChange(ValueChangeEvent<UserRegisterModel> event) {
				Window.alert("New value is: " + event.getValue());
			}
		});

		addField(UserRegister.LOGIN.name(), login.getWidget());
		addField(UserRegister.PASSWORD.name(), password.getWidget());
		addField(UserRegister.EMAIL.name(), email.getWidget());

		addWidgetHandler(UserRegister.LOGIN.name(), login);
		addWidgetHandler(UserRegister.PASSWORD.name(), password);
	}

	@Override
	public void onAddWidgets() {
		add(email.getWidget());
	}

}
