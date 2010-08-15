package org.gwtapp.ccalc.client.data.book;

import java.io.Serializable;

import org.gwtapp.core.rpc.data.HasLabelModel;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum Currency implements HasLabelModel, IsSerializable, Serializable {
	
	USD("USD"), EUR("EUR"), GBP("GBP"), PLN("PLN");

	private final String label;

	private Currency(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

}
