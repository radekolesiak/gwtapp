package org.gwtapp.startapp.client.ui;

import org.gwtapp.form.client.ui.TemplateModelDataFormPanel;
import org.gwtapp.startapp.client.StartApp;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;
import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.Param;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;

public class StartAppTemplatePanel extends
		TemplateModelDataFormPanel<UserRegisterModel> {

	private final MessageHandler<HTML> summary = new MessageHandler<HTML>(
			new HTML());
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
				updateMsg(event.getValue());
			}
		});

		addField(UserRegister.LOGIN.name(), login.getWidget());
		addField(UserRegister.PASSWORD.name(), password.getWidget());
		addField(UserRegister.EMAIL.name(), email.getWidget());

		addWidgetHandler("summary", summary);
		addWidgetHandler(UserRegister.LOGIN.name(), login);
		addWidgetHandler(UserRegister.PASSWORD.name(), password);
	}

	@Override
	public void onAddWidgets() {
		add(email.getWidget());
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
