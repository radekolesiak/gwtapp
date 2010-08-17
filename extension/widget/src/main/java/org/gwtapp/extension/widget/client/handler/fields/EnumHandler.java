package org.gwtapp.extension.widget.client.handler.fields;

import org.gwtapp.core.rpc.data.HasLabelModel;
import org.gwtapp.extension.widget.client.ui.fields.EnumField;
import org.gwtapp.template.client.UiHandler;


public class EnumHandler<T extends Enum<T> & HasLabelModel> extends
		UiHandler<EnumField<T>> {
	public EnumHandler(T[] values) {
		super(new EnumField<T>(values));
	}
}
