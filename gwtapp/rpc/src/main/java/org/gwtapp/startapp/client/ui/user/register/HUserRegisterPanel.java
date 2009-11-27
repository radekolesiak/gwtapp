package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.core.client.html.ui.core.HTextBox;
import org.gwtapp.core.client.html.ui.form.HFieldPanel;
import org.gwtapp.core.client.html.ui.form.HFormPanel;
import org.gwtapp.startapp.client.data.UserRegister;
import org.gwtapp.startapp.client.data.UserRegisterModel;

public class HUserRegisterPanel extends HFormPanel<UserRegisterModel> {

	private HFieldPanel<String> login = new HFieldPanel<String>(
			UserRegister.LOGIN.name(), "Login:", new HTextBox());

	/*-
	private HFieldPanel email = new HFieldPanel(UserRegister.EMAIL, "Email:", new HTextBox());
	private HFieldPanel password = new HFieldPanel(UserRegister.PASSWORD, "Password:", new HTextBox());
	-*/

	public HUserRegisterPanel() {
	}

	public HUserRegisterPanel(UserRegisterModel value) {
		super(value);
		addField(login);
		// addField(email);
		// addField(password);
		setValue(value);
	}

	public HFieldPanel<String> getLogin() {
		return login;
	}

	/*-
	 public HFieldPanel getEmail() {
	 return email;
	 }

	 public HFieldPanel getPassword() {
	 return password;
	 }
	 */
}
