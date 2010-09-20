package org.gwtapp.template.client.handler;

import java.util.List;
import java.util.Map;

import org.gwtapp.validation.client.HasValidation;
import org.gwtapp.validation.rpc.exception.ValidationException;

public class ValidationHandler<T extends ValidationException> extends
		WidgetHandler implements HasValidation<T> {

	public String getStyleClass(String name, Enum<?> e) {
		String style = "";
		style += "validation-" + name;
		style += " ";
		style += "validation-" + name + "-" + e.name();
		return style.replaceAll("_", "-").toLowerCase();
	}

	public String getStyleName(ValidationException validation) {
		String s = "";
		for (Map.Entry<String, List<Enum<?>>> list : validation.getFields()
				.entrySet()) {
			if (list != null && list.getValue() != null) {
				for (Enum<?> field : list.getValue()) {
					if (field != null) {
						s += getStyleClass(list.getKey(), field);
						s += " ";
					}
				}
			}
		}
		return s;
	}

	@Override
	public void setValidation(T validation) {
		clearValidation();
		String styleName = getStyleName(validation);
		if (!styleName.isEmpty()) {
			getWidget().addStyleName(styleName);
		}
	}

	@Override
	public void clearValidation() {
		getWidget().setStyleName("");
	}
}
