package org.gwtapp.extension.user.client;

import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.handler.TextBoxHandler;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class UserPanel extends TemplateModelPanel<User> {

	public static final String BIND_NAME = "UserPanel";

	@Inject
	public UserPanel(@Named(BIND_NAME) TemplateCallback callback) {
		super(callback);
		add(User.LOGIN, new TextBoxHandler());
		add(User.EMAIL, new TextBoxHandler());
		add(User.NAME, new TextBoxHandler());
	}
}
