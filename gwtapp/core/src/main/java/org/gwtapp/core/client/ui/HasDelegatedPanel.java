package org.gwtapp.core.client.ui;

import com.google.gwt.user.client.ui.HasValue;

public interface HasDelegatedPanel<X, Y> extends HasValue<X> {

	HasValue<Y> getDelegated();

	X convertToX(Y value);

	Y convertToY(X value);

	boolean isDelegatedToUpdate(X oldValue, X newValue);

	void setValue(X value, boolean fireEvents, boolean updateDelegatedPanel);
}
