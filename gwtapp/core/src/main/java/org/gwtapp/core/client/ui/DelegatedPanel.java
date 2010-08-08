package org.gwtapp.core.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.ContainerPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;

public abstract class DelegatedPanel<X, Y> extends ContainerPanel implements
		HasValue<X> {

	private boolean embeddable = false;

	public DelegatedPanel() {
		this(DOM.createDiv());
	}

	public DelegatedPanel(Element element) {
		embeddable = true;
		setElement(element);
	}

	public void attach() {
		if (embeddable && !isAttached()) {
			onAttach();
			RootPanel.detachOnWindowClose(this);
		}
	}

	public abstract HasValue<Y> getDelegated();

	// cached value
	private X value = null;

	public abstract X convertToX(Y value);

	public abstract Y convertToY(X value);

	/**
	 * 
	 * @param oldValue
	 * @param newValue
	 * @return <b>true</b> if <i>oldValue</i> and <i>newValue</i> are different
	 *         on custom metric e.g. two different numbers, texts to update
	 *         delegated value only when needed (e.g. TextBox takes a lot of
	 *         milliseconds to update its DOM input value)
	 */
	public boolean isDelegatedToUpdate(X oldValue, X newValue) {
		return true;
	}

	@Override
	public X getValue() {
		return value;
	}

	@Override
	public void setValue(X value) {
		setValue(value, false);
	}

	@Override
	public void setValue(X value, boolean fireEvents) {
		setValue(value, fireEvents, isDelegatedToUpdate(getValue(), value));
	};

	public void setValue(X value, boolean fireEvents, boolean updateDelegatedPanel) {
		this.value = value;
		if (updateDelegatedPanel) {
			getDelegated().setValue(convertToY(value));
		}
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	};

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<X> handler) {
		return addValueChangeHandler(handler, getDelegated());
	}

	/**
	 * Delegated panel type is different than this panel type. It is also needed
	 * to create a custom data converter on X getValue() and setValue(X value,
	 * boolean fireEvents) methods.
	 */
	protected HandlerRegistration addValueChangeHandler(
			final ValueChangeHandler<X> handler, final HasValue<Y> delegated) {
		return delegated.addValueChangeHandler(new ValueChangeHandler<Y>() {
			@Override
			public void onValueChange(ValueChangeEvent<Y> event) {
				final HandlerRegistration hr = addHandler(handler,
						ValueChangeEvent.getType());
				DelegatedPanel.this.setValue(convertToX(event.getValue()),
						true, false);
				DeferredCommand.addCommand(new Command() {
					@Override
					public void execute() {
						hr.removeHandler();
					}
				});
			}
		});
	}
}
