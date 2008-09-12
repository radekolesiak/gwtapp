package com.cuprum.web.common.client;

import com.cuprum.web.common.client.data.TValue;
import com.cuprum.web.common.client.data.TValuesMap;
import com.cuprum.web.widgets.common.client.SubmitListener;
import com.cuprum.web.widgets.common.client.SubmitListenerCollection;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public abstract class ProcessFormPanel<T> extends FormPanel {
	private final Button submit = new Button();

	private final SessionCallback<T> callback = new SessionCallback<T>() {
		@Override
		public void onBefore() {
			setReadOnly(false);
			submit.setEnabled(true);
		}

		@Override
		public void onSessionResponseSuccess(final T value) {
			validate(value);
			if (value instanceof TValuesMap) {
				if (!((TValuesMap) value).hasErrors()) {
					fireSubmitListener();
				}
			} else if (value instanceof TValue) {
				if (!((TValue) value).hasError()) {
					fireSubmitListener();
				}
			} else {
				fireSubmitListener();
			}
		}

		@Override
		public void onSessionNotFound() {
			fireSessionNotFoundListener();
		}
	};

	protected void beforeRender() {
		super.beforeRender();

		submit.setText(getSubmitMessage());

		submit.addSelectionListener(new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				submit();
			}
		});

		addButton(submit);

		onAddFields();

		setStyles();
	}

	public void setStyles() {
		setFrame(true);
		setWidth(400);
		setLabelWidth(125);
		setFieldWidth(210);
		setButtonAlign(HorizontalAlignment.CENTER);
	}

	public void submit() {
		setReadOnly(true);
		submit.setEnabled(false);
		onSubmit();
	}

	public void validate(T value) {
		Validate.init(this);
		onValidate(value);
		Validate.done(this);
	}

	protected SessionCallback<T> getCallback() {
		return callback;
	}

	private final SubmitListenerCollection submitListeners = new SubmitListenerCollection();

	public void addSubmitListener(SubmitListener listener) {
		submitListeners.add(listener);
	}

	public void removeSubmitListener(SubmitListener listener) {
		submitListeners.remove(listener);
	}

	protected void fireSubmitListener() {
		submitListeners.fireSubmitListener(this);
	}

	private final SessionNotFoundListenerCollection sessionListeners = new SessionNotFoundListenerCollection();

	public void addSessionNotFoundListener(SessionNotFoundListener listener) {
		sessionListeners.add(listener);
	}

	public void removeSessionNotFoundListener(SessionNotFoundListener listener) {
		sessionListeners.remove(listener);
	}

	protected void fireSessionNotFoundListener() {
		sessionListeners.fireSessionNotFoundListener(this);
	}

	public abstract String getSubmitMessage();

	public abstract T getValue();

	public abstract void setValue(T value);

	protected abstract void onAddFields();

	protected abstract void onValidate(T value);

	protected abstract void onSubmit();
}
