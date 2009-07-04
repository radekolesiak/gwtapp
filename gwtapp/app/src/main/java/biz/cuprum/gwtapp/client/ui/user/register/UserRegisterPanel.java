package biz.cuprum.gwtapp.client.ui.user.register;

import biz.cuprum.gwtapp.client.data.UserRegister;
import biz.cuprum.gwtapp.core.client.ui.FieldPanel;
import biz.cuprum.gwtapp.core.client.ui.FormPanel;

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
		return new UserRegister();
	}
}
