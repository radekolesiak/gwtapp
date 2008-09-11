package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.widgets.common.client.PasswordTextBoxes;
import com.cuprum.web.widgets.user.password.client.i18n.ChangePasswordByTokenMessages;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.core.client.GWT;

public class ChangePasswordByToken extends FormPanel {
	private final ChangePasswordByTokenMessages messages = GWT.create(ChangePasswordByTokenMessages.class);
	
	private final PasswordTextBoxes password = new PasswordTextBoxes();

	private final Button submit = new Button(messages.msgSubmit());

	public ChangePasswordByToken() {
		setFrame(true);
		setWidth(400);
		setLabelWidth(125);
		setFieldWidth(210);
		setButtonAlign(HorizontalAlignment.CENTER);
		setHeading(messages.msgHeading());

		password.setFieldLabel(messages.msgPasswordLabel(), messages.msgPasswordConfirmLabel());

		password.attachTo(this);
		addButton(submit);
	}
}
