package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.core.client.ui.FieldPanel;
import org.gwtapp.core.client.ui.FormPanel;
import org.gwtapp.startapp.client.data.UserRegister;
import org.gwtapp.startapp.client.data.UserRegisterModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.TextBox;

public class UserRegisterPanel extends FormPanel<UserRegisterModel> {

	public static class Style {
		public final static String USER_REGISTER_PANEL = "userRegisterPanel";
	}

	private final FieldPanel<UserRegisterModel, String> email = new FieldPanel<UserRegisterModel, String>(
			this, UserRegister.EMAIL, "Email:", new TextBox());
	private final FieldPanel<UserRegisterModel, String> password = new FieldPanel<UserRegisterModel, String>(
			this, UserRegister.PASSWORD, "Password:", new TextBox());

	// hybrid example
	public UserRegisterPanel(HUserRegisterPanel modelPanel) {
		super(modelPanel);
		addStyleName(Style.USER_REGISTER_PANEL);
		addField(email);
		addField(password);
	}

	public UserRegisterPanel() {
		super((UserRegisterModel) GWT.create(UserRegisterModel.class));
		addStyleName(Style.USER_REGISTER_PANEL);
		FieldPanel<UserRegisterModel, String> login = new FieldPanel<UserRegisterModel, String>(
				this, UserRegister.LOGIN, "Login:", new TextBox());
		addField(login);
		addField(email);
		addField(password);
	}
}
