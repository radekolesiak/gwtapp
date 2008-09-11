package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.widgets.common.client.PasswordTextBoxes;
import com.cuprum.web.widgets.common.client.TextBox;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class ChangePasswordByUser extends FormPanel {
	private final TextBox oldPassword = new TextBox();

	private final PasswordTextBoxes newPassword = new PasswordTextBoxes();

	private final Button submit = new Button("Change password");

	public ChangePasswordByUser() {
		setFrame(true);
		setWidth(400);
		setLabelWidth(125);
		setFieldWidth(210);
		setButtonAlign(HorizontalAlignment.CENTER);
		setHeading("Change password by user form");

		oldPassword.setFieldLabel("Old password");
		oldPassword.setPassword(true);

		newPassword.setFieldLabel("New password", "Confirm new password");

		add(oldPassword);
		newPassword.attachTo(this);
		addButton(submit);
	}
}
