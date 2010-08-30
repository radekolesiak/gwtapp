package org.gwtapp.ccalc.client.handler.fields;

import org.gwtapp.ccalc.client.ui.fields.LabelField;
import org.gwtapp.template.client.UiHandler;


public class LabelHandler<T> extends UiHandler<LabelField<T>> {
	public LabelHandler() {
		super(new LabelField<T>());
	}
}
