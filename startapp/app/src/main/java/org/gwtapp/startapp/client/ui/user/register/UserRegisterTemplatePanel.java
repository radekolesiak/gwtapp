package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.form.client.ui.TemplateModelDataFormPanel;
import org.gwtapp.startapp.client.StartAppEntryPoint;
import org.gwtapp.startapp.client.handlers.user.register.LoginHandler;
import org.gwtapp.startapp.shared.data.user.register.UserRegister;
import org.gwtapp.startapp.shared.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.shared.data.user.register.UserRegisterModelImpl;
import org.gwtapp.template.client.Param;
import org.gwtapp.template.client.handlers.TextBoxHandler;
import org.gwtapp.template.client.handlers.WidgetHandler;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class UserRegisterTemplatePanel extends
		TemplateModelDataFormPanel<UserRegisterModel> {

	private final WidgetHandler summary = add("summary", new WidgetHandler());

	public UserRegisterTemplatePanel() {
		super(new UserRegisterModelImpl(), StartAppEntryPoint.templates
				.load("startapp.jsp"));
		add(UserRegister.LOGIN, new LoginHandler());
		add(UserRegister.EMAIL, new TextBoxHandler());
		add(UserRegister.PASSWORD, new TextBoxHandler());
	}

	@Override
	public void onAddFormWidgets() {
		addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
			@Override
			public void onValueChange(ValueChangeEvent<UserRegisterModel> event) {
				updateMsg(event.getValue());
			}
		});
	}

	@Override
	public void setValue(UserRegisterModel value, boolean fireEvents) {
		updateMsg(value);
		super.setValue(value, fireEvents);
	}

	private void updateMsg(UserRegisterModel value) {
		if (isTemplated()) {
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
	}

	private boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}

	private String getFieldMsg(String name, String value) {
		return isEmpty(value) ? "" : summary.getMessage(name, value);
	}
}