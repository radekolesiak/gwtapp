package org.gwtapp.startapp.client.ui;

import org.gwtapp.core.client.template.Template;
import org.gwtapp.core.client.template.TemplaterHandler;
import org.gwtapp.core.client.template.WidgetHandler;
import org.gwtapp.core.client.template.ui.TemplatePanel;
import org.gwtapp.startapp.client.StartApp;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ExternalTemplatingPanel extends FlowPanel {

	private final TextBox t1 = new TextBox();
	private final TextBox t2 = new TextBox();

	public ExternalTemplatingPanel() {
		ValueChangeHandler<String> handler = new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Window.alert("New value is: " + event.getValue());
			}
		};
		t1.addValueChangeHandler(handler);
		t2.addValueChangeHandler(handler);
		StartApp.templater.template("startapp.jsp?type=external",
				new TemplaterHandler() {
					@Override
					public void onTemplate(Template template) {
						template.setTag("p");
						TemplatePanel<Void> panel = new TemplatePanel<Void>(
								template);
						panel.addWidgetHandler("t1", new WidgetHandler() {
							@Override
							public Widget onWidget(String id) {
								return t1;
							}
						});
						panel.addWidgetHandler("t2", new WidgetHandler() {
							@Override
							public Widget onWidget(String id) {
								return t2;
							}
						});
						panel.template();
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
