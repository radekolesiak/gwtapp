package org.gwtapp.startapp.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.template.TemplaterHandler;
import org.gwtapp.core.client.template.WidgetHandler;
import org.gwtapp.startapp.client.StartApp;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ExternalTemplatingPanel extends FlowPanel {

	private final TextBox t1 = new TextBox();
	private final TextBox t2 = new TextBox();

	public ExternalTemplatingPanel() {
		Map<String, WidgetHandler> wh = new HashMap<String, WidgetHandler>();
		ValueChangeHandler<String> handler = new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Window.alert("New value is: " + event.getValue());
			}
		};
		t1.addValueChangeHandler(handler);
		t2.addValueChangeHandler(handler);
		wh.put("t1", new WidgetHandler() {
			@Override
			public Widget onWidget(String id) {
				return t1;
			}
		});
		wh.put("t2", new WidgetHandler() {
			@Override
			public Widget onWidget(String id) {
				return t2;
			}
		});
		StartApp.templater.template("startapp.jsp?type=external", wh, new TemplaterHandler() {
			@Override
			public void onTemplate(HTMLPanel panel) {
				add(panel);
			}

			@Override
			public void onFailure(Throwable e) {
				add(new Label("ERROR"));
				e.printStackTrace();
			}
		});
	}
}
