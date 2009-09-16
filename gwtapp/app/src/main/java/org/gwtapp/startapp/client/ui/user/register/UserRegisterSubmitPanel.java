package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.core.client.ui.form.SubmitFormPanel;
import org.gwtapp.startapp.client.data.UserRegisterModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;

public class UserRegisterSubmitPanel extends SubmitFormPanel<UserRegisterModel> {

	public static class Style {
		public final static String USER_REGISTER_SUBMIT_PANEL = "userRegisterSubmitPanel";
	}
	
	public UserRegisterSubmitPanel() {
		super((UserRegisterModel) GWT.create(UserRegisterModel.class),
				new UserRegisterPanel());
		Button submit = new Button("Submit");
		addStyleName(Style.USER_REGISTER_SUBMIT_PANEL);
		add(getForm());
		add(submit);
	}
}
