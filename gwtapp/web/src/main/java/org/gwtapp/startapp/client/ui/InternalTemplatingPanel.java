package org.gwtapp.startapp.client.ui;

import org.gwtapp.core.client.template.WidgetHandler;
import org.gwtapp.core.client.template.ui.TemplatePanel;
import org.gwtapp.startapp.client.StartApp;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class InternalTemplatingPanel extends TemplatePanel<Void> {

	private final TextBox t1 = new TextBox();
	private final TextBox t2 = new TextBox();

	public InternalTemplatingPanel() {
		this("startapp.jsp?type=internal");
	}

	public InternalTemplatingPanel(String template) {
		ValueChangeHandler<String> handler = new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Window.alert("New value is: " + event.getValue());
			}
		};
		t1.addValueChangeHandler(handler);
		t2.addValueChangeHandler(handler);
		addWidgetHandler("t1", new WidgetHandler() {
			@Override
			public Widget onWidget(String id) {
				return t1;
			}
		});
		addWidgetHandler("t2", new WidgetHandler() {
			@Override
			public Widget onWidget(String id) {
				return t2;
			}
		});
		StartApp.templateService.load(template, this);
	}
}
