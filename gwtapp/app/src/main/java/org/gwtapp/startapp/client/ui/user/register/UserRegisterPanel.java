package org.gwtapp.startapp.client.ui.user.register;

import java.util.ArrayList;

import org.gwtapp.core.client.ui.FieldPanel;
import org.gwtapp.core.client.ui.FormPanel;
import org.gwtapp.startapp.client.data.UserRegister;
import org.gwtapp.startapp.client.data.UserRegisterModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.TextBox;

public class UserRegisterPanel extends FormPanel<UserRegisterModel> {

	private final FieldPanel<UserRegisterModel, String> login = new FieldPanel<UserRegisterModel, String>(
			this, UserRegister.LOGIN, "Login:", new TextBox());
	private final FieldPanel<UserRegisterModel, String> email = new FieldPanel<UserRegisterModel, String>(
			this, UserRegister.EMAIL, "Email:", new TextBox());
	private final FieldPanel<UserRegisterModel, String> password = new FieldPanel<UserRegisterModel, String>(
			this, UserRegister.PASSWORD, "Password:", new TextBox());

	int i = 1;

	public UserRegisterPanel() {
		super((UserRegisterModel) GWT.create(UserRegisterModel.class));
		addField(login);
		addField(email);
		addField(password);
		getValue().setTockens(new ArrayList());
	}
}
