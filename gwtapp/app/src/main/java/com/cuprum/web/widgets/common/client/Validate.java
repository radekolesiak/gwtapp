package com.cuprum.web.widgets.common.client;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;

public class Validate {
	@SuppressWarnings("unchecked")
	public static void init(FormPanel panel) {
		for (Field field : panel.getFields()) {
			if (field instanceof TextField) {
				TextField textField = (TextField) field;
				textField.setValidator(null);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void done(FormPanel panel) {
		for (Field field : panel.getFields()) {
			if (field instanceof TextField) {
				TextField textField = (TextField) field;
				textField.validate();
			}
		}
	}
}
