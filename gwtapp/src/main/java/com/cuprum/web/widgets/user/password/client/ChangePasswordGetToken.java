package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.widgets.common.client.TextBox;
import com.cuprum.web.widgets.user.password.client.i18n.ChangePasswordGetTokenMessages;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.core.client.GWT;

public class ChangePasswordGetToken extends FormPanel {
	private final ChangePasswordGetTokenMessages messages = GWT.create(ChangePasswordGetTokenMessages.class);
	
	private final TextBox login = new TextBox();

	private final Button submit = new Button(messages.msgSubmit());

	public ChangePasswordGetToken() {
		setFrame(true);
		setWidth(400);
		setLabelWidth(125);
		setFieldWidth(210);
		setButtonAlign(HorizontalAlignment.CENTER);
		setHeading(messages.msgHeading());

		login.setFieldLabel(messages.msgLoginLabel());
		login.setEmptyText(messages.msgLoginEmptyText());

		add(login);
		addButton(submit);
	}
}
