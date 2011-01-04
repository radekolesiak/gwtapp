package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.startapp.client.StartAppEntryPoint;
import org.gwtapp.startapp.client.handlers.user.register.LoginHandler;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;
import org.gwtapp.template.client.Param;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.handler.WidgetHandler;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class UserRegisterTemplatePanel extends TemplateModelPanel<UserRegisterModel> {

	private final WidgetHandler summary = add("summary", new WidgetHandler());

	public UserRegisterTemplatePanel() {
		super(StartAppEntryPoint.templates.load("startapp.jsp"), new UserRegisterModelImpl());
		add(UserRegister.LOGIN, new LoginHandler());
		add(UserRegister.EMAIL, new TextBoxHandler());
		add(UserRegister.PASSWORD, new TextBoxHandler());
	}

	@Override
	public void onFormWidgets() {
		addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
			@Override
			public void onValueChange(ValueChangeEvent<UserRegisterModel> event) {
				setValue(event.getValue());
			}
		});
	}

	@Override
	public void setTemplateValue(UserRegisterModel value) {
		String login = value.getLogin();
		String password = value.getPassword();
		String email = value.getEmail();
		if (isEmpty(login) && isEmpty(password) && isEmpty(email)) {
			summary.updateWidgetMessage("empty");
		} else {
			String loginMsg = getFieldMsg("login", login);
			String passwordMsg = getFieldMsg("password", password);
			String emailMsg = getFieldMsg("email", email);
			summary.updateWidgetParamMessage("any",//
					new Param("login", loginMsg),//
					new Param("password", passwordMsg),//
					new Param("email", emailMsg)//
			);
		}
	}

	private boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}

	private String getFieldMsg(String name, String value) {
		return isEmpty(value) ? "" : summary.getMessage(name, value);
	}
}
