package com.cuprum.web.common.client.data;

import java.util.ArrayList;

import com.cuprum.web.common.client.data.IValueCollection;
import com.cuprum.web.widgets.common.client.exception.MultipleException;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 
 * @author radek
 * 
 *         TODO: move to the gwtapp project.
 */
@SuppressWarnings("unchecked")
public class TValueModelDataList<T extends TValueModelData> extends
		ArrayList<T> implements IsSerializable, IValueCollection {

	/**
	 * UID.ListStore<TServiceValueModelData> store
	 */
	private static final long serialVersionUID = -6972919903420607753L;

	public void clearErrors() {
		for (T value : this) {
			value.clearErrors();
		}
	}

	public boolean hasErrors() {
		for (T value : this) {
			if (value.hasErrors()) {
				return true;
			}
		}
		return false;
	}

	public void evalErrors() throws MultipleException {
		if (hasErrors()) {
			throw new MultipleException();
		}
	}

	// TODO: to extend at the gwtapp-app project and implement with the extjs's ModelData
	/*-
	public <X extends TValueModelDataList<T>> X copyFrom(
			final ListStore<T> store) {
		clear();
		addAll(store.getModels());
		return (X) this;
	}

	public void copyTo(final ListStore<T> store) {
		store.removeAll();
		store.add(this);
		store.commitChanges();
	}
	-*/
}
