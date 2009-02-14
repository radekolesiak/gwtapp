package com.cuprum.web.widgets.common.client;

public class PasswordTextBoxes extends DualTextBoxes {
	@Override
	protected TextBox createTextBox() {
		TextBox field = new TextBox();
		field.setPassword(true);
		return field;
	}
}
