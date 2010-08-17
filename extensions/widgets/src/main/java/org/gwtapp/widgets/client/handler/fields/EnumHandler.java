package org.gwtapp.widgets.client.handler.fields;

import org.gwtapp.core.rpc.data.HasLabelModel;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.widgets.client.ui.fields.EnumField;


public class EnumHandler<T extends Enum<T> & HasLabelModel> extends
		UiHandler<EnumField<T>> {
	public EnumHandler(T[] values) {
		super(new EnumField<T>(values));
	}
}
