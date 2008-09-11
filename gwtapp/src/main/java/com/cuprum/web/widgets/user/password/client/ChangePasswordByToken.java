package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.widgets.common.client.PasswordTextBoxes;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class ChangePasswordByToken extends FormPanel {
	private final PasswordTextBoxes password = new PasswordTextBoxes();

	private final Button submit = new Button("Change password");

	public ChangePasswordByToken() {
		setFrame(true);
		setWidth(400);
		setLabelWidth(125);
		setFieldWidth(210);
		setButtonAlign(HorizontalAlignment.CENTER);
		setHeading("Change password by token form");

		password.setFieldLabel("Password", "Confirm password");

		password.attachTo(this);
		addButton(submit);
	}
}
