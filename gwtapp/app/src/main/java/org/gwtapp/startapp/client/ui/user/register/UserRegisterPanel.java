package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.core.client.html.ui.form.HFieldPanel;
import org.gwtapp.core.client.ui.form.FieldPanel;
import org.gwtapp.core.client.ui.form.FormPanel;
import org.gwtapp.startapp.client.data.user.register.UserRegister;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModelImpl;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.TextBox;

public class UserRegisterPanel extends FormPanel<UserRegisterModel> {

	public static class Style {
		public final static String USER_REGISTER_PANEL = "userRegisterPanel";
	}

	private final FieldPanel<UserRegisterModel, String> login;
	private final FieldPanel<UserRegisterModel, String> email = new FieldPanel<UserRegisterModel, String>(
			this, UserRegister.EMAIL.name(), "Email:", new TextBox());
	private final FieldPanel<UserRegisterModel, String> password = new FieldPanel<UserRegisterModel, String>(
			this, UserRegister.PASSWORD.name(), "Password:", new TextBox());

	// hybrid example
	public UserRegisterPanel(final HUserRegisterPanel modelPanel) {
		super(modelPanel);
		
		HFieldPanel<String> loginField = modelPanel.getField(UserRegister.LOGIN
				.name());
		login = new FieldPanel<UserRegisterModel, String>(this, loginField,
				TextBox.wrap(DOM.getElementById(loginField.getControllerId())));
		addFields();
		
		wrap();
	}

	public UserRegisterPanel() {
		super(new UserRegisterModelImpl());
		login = new FieldPanel<UserRegisterModel, String>(this,
				UserRegister.LOGIN.name(), "Login:", new TextBox());
		addFields();
	}

	private void addFields() {
		addStyleName(Style.USER_REGISTER_PANEL);
		addField(login);
		addField(email);
		addField(password);
	}
}