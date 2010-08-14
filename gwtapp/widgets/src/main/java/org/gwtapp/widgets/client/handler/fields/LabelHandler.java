package org.gwtapp.widgets.client.handler.fields;

import org.gwtapp.template.client.UiHandler;
import org.gwtapp.widgets.client.ui.fields.LabelField;


public class LabelHandler<T> extends UiHandler<LabelField<T>> {
	public LabelHandler() {
		super(new LabelField<T>());
	}
}
