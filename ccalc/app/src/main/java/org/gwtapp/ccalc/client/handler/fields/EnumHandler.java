package org.gwtapp.ccalc.client.handler.fields;

import org.gwtapp.ccalc.client.ui.fields.EnumField;
import org.gwtapp.core.rpc.data.HasLabelModel;
import org.gwtapp.template.client.handler.UiHandler;


public class EnumHandler<T extends Enum<T> & HasLabelModel> extends
		UiHandler<EnumField<T>> {
	public EnumHandler(T[] values) {
		super(new EnumField<T>(values));
	}
}
