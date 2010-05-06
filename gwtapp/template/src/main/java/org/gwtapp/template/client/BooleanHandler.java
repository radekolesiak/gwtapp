package org.gwtapp.template.client;

import org.gwtapp.template.client.ui.BooleanWrapper;

public class BooleanHandler extends MessageHandler<BooleanWrapper> {
	@Override
	public BooleanWrapper onWidget() {
		return new BooleanWrapper(getId());
	}
}