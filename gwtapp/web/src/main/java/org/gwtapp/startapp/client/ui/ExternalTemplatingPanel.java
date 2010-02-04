package org.gwtapp.startapp.client.ui;

import org.gwtapp.core.client.template.Template;
import org.gwtapp.core.client.template.WidgetHandler;
import org.gwtapp.core.client.template.ui.TemplatePanel;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ExternalTemplatingPanel extends TemplatePanel<Void> {

	private final TextBox t1 = new TextBox();
	private final TextBox t2 = new TextBox();

	public ExternalTemplatingPanel(Template template) {
		super(template);
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
	}
}
