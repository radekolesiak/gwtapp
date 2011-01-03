package org.gwtapp.template.client.handler;

import org.gwtapp.template.client.ui.BooleanWrapper;

public class BooleanHandler extends UiHandler<BooleanWrapper> {

	private final boolean checkbox;

	public BooleanHandler() {
		this(true);
	}

	public BooleanHandler(boolean checkbox) {
		this.checkbox = checkbox;
	}

	@Override
	public BooleanWrapper onWidget() {
		return new BooleanWrapper(getId());
	}

	@Override
	protected BooleanWrapper getDefaultWidget() {
		return new BooleanWrapper(checkbox);
	}
}