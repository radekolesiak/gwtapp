package org.gwtapp.template.client.handler;

import java.util.List;
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
		getWidget().addStyleName(getStyleClass(validation));
	}

	@Override
	public void clearValidation() {
		getWidget().setStyleName("");
	}
}
