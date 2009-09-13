package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.core.client.html.ui.HFieldPanel;
import org.gwtapp.core.client.html.ui.HFormPanel;
import org.gwtapp.core.client.html.ui.core.HTextBox;
import org.gwtapp.startapp.client.data.UserRegister;
import org.gwtapp.startapp.client.data.UserRegisterModel;

public class HUserRegisterPanel extends HFormPanel<UserRegisterModel> {

	private HFieldPanel login = new HFieldPanel(UserRegister.LOGIN, "Login:",
			new HTextBox());

	/*-
	private HFieldPanel email = new HFieldPanel(UserRegister.EMAIL, "Email:", new HTextBox());
	private HFieldPanel password = new HFieldPanel(UserRegister.PASSWORD, "Password:", new HTextBox());
	-*/

	public HUserRegisterPanel(UserRegisterModel value) {
		super(value);
		addField(login);
		// addField(email);
		// addField(password);
	}

	public HFieldPanel getLogin() {
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
