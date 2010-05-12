package org.gwtapp.startapp.client.ui;

import org.gwtapp.startapp.client.StartApp;
import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.TextBox;

public class LoginTemplatePanel extends TemplatePanel<String> {

	private final TextBox login = new TextBox();
	private final MessageHandler<TextBox> loginHandler = new MessageHandler<TextBox>(
			login);

	public LoginTemplatePanel() {
		super(StartApp.syncTemplateService.load("login.jsp"));
		addWidgetHandler("login", loginHandler);
		login.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				ValueChangeEvent
						.fire(LoginTemplatePanel.this, event.getValue());
			}
		});
	}

	@Override
	public String getValue() {
		return login.getValue();
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		login.setValue(value);
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}
}
