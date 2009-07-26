package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.core.client.ui.SubmitFormPanel;
import org.gwtapp.startapp.client.data.UserRegisterModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;

public class UserRegisterSubmitPanel extends SubmitFormPanel<UserRegisterModel> {

	public UserRegisterSubmitPanel() {
		super((UserRegisterModel) GWT.create(UserRegisterModel.class),
				new UserRegisterPanel());
		Button submit = new Button("Submit");
		add(getForm());
		add(submit);
	}
}
