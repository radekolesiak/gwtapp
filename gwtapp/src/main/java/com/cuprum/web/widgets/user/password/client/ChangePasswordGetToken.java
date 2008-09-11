package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.widgets.common.client.TextBox;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class ChangePasswordGetToken extends FormPanel {
	private final TextBox login = new TextBox();

	private final Button submit = new Button("Send token to change password");

	public ChangePasswordGetToken() {
		setFrame(true);
		setWidth(400);
		setLabelWidth(125);
		setFieldWidth(210);
		setButtonAlign(HorizontalAlignment.CENTER);
		setHeading("Get token to change password form");

		login.setFieldLabel("Login");

		add(login);
		addButton(submit);
	}
}
