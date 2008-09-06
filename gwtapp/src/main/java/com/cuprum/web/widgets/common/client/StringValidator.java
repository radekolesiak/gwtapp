package com.cuprum.web.widgets.common.client;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;

public class StringValidator implements Validator {
	private String msg = null;
	
	public StringValidator() {
		
	}
	
	public StringValidator(final String msg) {
		this.msg = msg;
	}
	
	public String validate(Field field, String value) {
		return msg;
	}
}
