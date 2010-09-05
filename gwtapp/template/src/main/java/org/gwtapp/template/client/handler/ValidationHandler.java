package org.gwtapp.template.client.handler;

import java.util.Map;

import org.gwtapp.core.client.ui.HasValidation;
import org.gwtapp.core.rpc.exception.ValidationException;

public class ValidationHandler<T extends ValidationException> extends
		WidgetHandler implements HasValidation<T> {

	public String getStyleClass(String name, Enum<?> e) {
		String style = "";
		style += "validation-" + name;
		style += " ";
		style += "validation-" + name + "-" + e.name();
		return style.replaceAll("_", "-").toLowerCase();
	}

	public String getStyleClass(ValidationException validation) {
		String s = "";
		for (Map.Entry<String, Enum<?>> field : validation.getFields()
				.entrySet()) {
			if (field.getValue() != null) {
				s += getStyleClass(field.getKey(), field.getValue());
				s += " ";
			}
		}
		return s;
	}

	@Override
	public void setValidation(T validation) {
		clearValidation();
		getWidget().addStyleName(getStyleClass(validation));
	}

	@Override
	public void clearValidation() {
		getWidget().setStyleName("");
	}
}
