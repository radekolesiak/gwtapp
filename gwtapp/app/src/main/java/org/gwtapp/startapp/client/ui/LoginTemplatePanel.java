package org.gwtapp.startapp.client.ui;

import org.gwtapp.startapp.client.StartAppEntryPoint;
import org.gwtapp.template.client.handlers.TextBoxHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class LoginTemplatePanel extends TemplatePanel<String> {

	private final TextBoxHandler login = new TextBoxHandler();

	public LoginTemplatePanel() {
		super(StartAppEntryPoint.templates.load("login.jsp"));
		addWidgetHandler("login", login);
	}

	@Override
	public void onAddWidgets() {
		login.getWidget().addValueChangeHandler(
				new ValueChangeHandler<String>() {
					@Override
					public void onValueChange(ValueChangeEvent<String> event) {
						ValueChangeEvent.fire(LoginTemplatePanel.this, event
								.getValue());
					}
				});
	}

	@Override
	public String getValue() {
		return login.getWidget().getValue();
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		if (isTemplated()) {
			login.getWidget().setValue(value);
		}
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}
}
