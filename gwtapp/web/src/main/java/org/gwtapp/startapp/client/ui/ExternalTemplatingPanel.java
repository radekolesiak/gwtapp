package org.gwtapp.startapp.client.ui;

import org.gwtapp.core.client.template.Template;
import org.gwtapp.core.client.template.WidgetHandler;
import org.gwtapp.core.client.template.ui.TemplateFormPanel;
import org.gwtapp.startapp.client.data.user.register.UserRegister;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModelImpl;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ExternalTemplatingPanel extends
		TemplateFormPanel<UserRegisterModel> {

	private final TextBox login = new TextBox();
	private final TextBox password = new TextBox();
	private final TextBox email = new TextBox();

	public ExternalTemplatingPanel(Template template) {
		super(new UserRegisterModelImpl(), template);

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
