package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.widgets.common.client.PasswordTextBoxes;
import com.cuprum.web.widgets.common.client.TextBox;
import com.cuprum.web.widgets.user.password.client.i18n.ChangePasswordByUserMessages;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.core.client.GWT;

public class ChangePasswordByUser extends FormPanel {
	ChangePasswordByUserMessages messages = GWT.create(ChangePasswordByUserMessages.class);
	
	private final TextBox oldPassword = new TextBox();

	private final PasswordTextBoxes newPassword = new PasswordTextBoxes();

	private final Button submit = new Button(messages.msgSubmit());

	public ChangePasswordByUser() {
		setFrame(true);
		setWidth(400);
		setLabelWidth(125);
		setFieldWidth(210);
		setButtonAlign(HorizontalAlignment.CENTER);
		setHeading(messages.msgHeading());

		oldPassword.setFieldLabel(messages.msgOldPasswordLabel());
		oldPassword.setPassword(true);

		newPassword.setFieldLabel(messages.msgNewPasswordLabel(), messages.msgNewPasswordConfirmLabel());

		add(oldPassword);
		newPassword.attachTo(this);
		addButton(submit);
	}
}
