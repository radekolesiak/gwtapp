package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.core.client.ui.FieldPanel;
import org.gwtapp.core.client.ui.FormPanel;
import org.gwtapp.startapp.client.data.UserRegister;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.TextBox;

public class UserRegisterPanel extends FormPanel<UserRegister> {

	private final FieldPanel<UserRegister, String> login = new FieldPanel<UserRegister, String>(
			this, UserRegister.LOGIN, "Login:", new TextBox());
	private final FieldPanel<UserRegister, String> email = new FieldPanel<UserRegister, String>(
			this, UserRegister.EMAIL, "Email:", new TextBox());
	private final FieldPanel<UserRegister, String> password = new FieldPanel<UserRegister, String>(
			this, UserRegister.PASSWORD, "Password:", new TextBox());

	public UserRegisterPanel() {
		addField(login);
		addField(email);
		addField(password);
	}

	@Override
	protected UserRegister createInstance() {
		return GWT.create(UserRegister.class);
	}
}
