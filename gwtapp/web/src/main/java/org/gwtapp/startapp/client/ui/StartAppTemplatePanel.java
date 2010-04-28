package org.gwtapp.startapp.client.ui;

import org.gwtapp.startapp.client.StartApp;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;
import org.gwtapp.template.client.WidgetHandler;
import org.gwtapp.template.client.ui.TemplateFormPanel;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StartAppTemplatePanel extends TemplateFormPanel<UserRegisterModel> {

	private final LoginTemplatePanel login = new LoginTemplatePanel();
	private final TextBox password = new TextBox();
	private final TextBox email = new TextBox();

	public StartAppTemplatePanel() {
		super(new UserRegisterModelImpl(), StartApp.syncTemplateService
				.load("startapp.jsp"));

		addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
			@Override
			public void onValueChange(ValueChangeEvent<UserRegisterModel> event) {
				Window.alert("New value is: " + event.getValue());
			}
		});

		addField(UserRegister.LOGIN.name(), login);
		addField(UserRegister.PASSWORD.name(), password);
		addField(UserRegister.EMAIL.name(), email);

		addWidgetHandler(UserRegister.LOGIN.name(), new WidgetHandler() {
			@Override
			public Widget onWidget(String id) {
				//LoginTemplatePanel login = new LoginTemplatePanel();
				//addField(UserRegister.LOGIN.name(), login);
				return login;
			}
		});
		addWidgetHandler(UserRegister.PASSWORD.name(), new WidgetHandler() {
			@Override
			public Widget onWidget(String id) {
				return password;
			}
		});
	}

	@Override
	public void onAddWidgets() {
		add(email);
	}

}
