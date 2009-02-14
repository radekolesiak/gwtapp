package com.cuprum.web.widgets.common.client;

import com.extjs.gxt.ui.client.widget.form.TextField;

public class TextBox extends TextField<String> {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected boolean validateValue(String value) {
		if (getAllowBlank() && getValidator() != null) {
			String msg = getValidator().validate(this, value);
			if (msg != null) {
				markInvalid(msg);
				return false;
			}
		}
		return super.validateValue(value);
	}

	@Override
	public String getValue() {
		String value = super.getValue();
		if (value == null) {
			return "";
		} else {
			return value;
		}
	}
}
