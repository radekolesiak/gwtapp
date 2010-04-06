package org.gwtapp.startapp.rpc.ui.user.register;

import org.gwtapp.core.rpc.html.ui.core.HTextBox;
import org.gwtapp.core.rpc.html.ui.form.HFieldPanel;
import org.gwtapp.core.rpc.html.ui.form.HFormPanel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;

public class HUserRegisterPanel extends HFormPanel<UserRegisterModel> {

	private HFieldPanel<String> login = new HFieldPanel<String>(
			UserRegister.LOGIN.name(), "Login:", new HTextBox());

	/*-
	private HFieldPanel email = new HFieldPanel(UserRegister.EMAIL, "Email:", new HTextBox());
	private HFieldPanel password = new HFieldPanel(UserRegister.PASSWORD, "Password:", new HTextBox());
	-*/

	public HUserRegisterPanel() {
		this(new UserRegisterModelImpl());
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
