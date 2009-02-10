package com.cuprum.web.widgets.common.client;

import com.cuprum.web.common.client.Util;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;

public class StringValidator implements Validator {
	private String msg = null;

	public StringValidator() {

	}

	public StringValidator(final String msg) {
		this.msg = msg;
	}

	public StringValidator(final Throwable e) {
		if (Util.isDebug()) {
			this.msg = e.getClass().getName().replace('.', ' ');
		}
	}

	public String validate(Field field, String value) {
		return msg;
	}
}
