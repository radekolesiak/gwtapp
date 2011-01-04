package org.gwtapp.startapp.client.ui.user.register;

import org.gwtapp.startapp.client.StartAppEntryPoint;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class LoginTemplatePanel extends TemplatePanel<String> {

	private final TextBoxHandler login = add("login", new TextBoxHandler());

	public LoginTemplatePanel() {
		super(StartAppEntryPoint.templates.load("login.jsp"));
	}

	@Override
	public void onWidgets() {
		login.getWidget().addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				ValueChangeEvent.fire(LoginTemplatePanel.this, event.getValue());
			}
		});
	}

	@Override
	public String getTemplateValue() {
		return login.getWidget().getValue();
	}

	@Override
	public void setTemplateValue(String value) {
		if (isTemplated()) {
			login.getWidget().setValue(value);
		}
	}
}
