package org.gwtapp.template.client.handler;

import java.util.List;
import java.util.Map;

import org.gwtapp.validation.client.HasValidation;
import org.gwtapp.validation.rpc.exception.ValidationException;

public class ValidationHandler<T extends ValidationException> extends
		WidgetHandler implements HasValidation<T> {

	public String getStyleName(ValidationException validation) {
		return getStyleName("", validation);
	}

	public String getStyleName(String child, ValidationException validation) {
		String s = "";
		for (Map.Entry<String, List<Enum<?>>> list : validation.getFields()
				.entrySet()) {
			if (list != null && list.getValue() != null) {
				for (Enum<?> field : list.getValue()) {
					if (field != null) {
						s += getStyleClass(child + list.getKey(), field);
						s += " ";
					}
				}
			}
		}

		for (Map.Entry<String, ValidationException> v : validation
				.getChildren().entrySet()) {
			s += getStyleName(child + v.getKey() + "-", v.getValue());
		}
		return s;
	}

	public String getStyleClass(String name, Enum<?> e) {
		String style = "";
		style += "validation-" + name;
		style += " ";
		style += "validation-" + name + "-" + e.name();
		return style.replaceAll("_", "-").toLowerCase();
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
