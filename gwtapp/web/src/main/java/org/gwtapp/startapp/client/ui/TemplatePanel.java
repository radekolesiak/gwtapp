package org.gwtapp.startapp.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.template.TemplateHandler;
import org.gwtapp.core.client.template.TemplaterHandler;
import org.gwtapp.startapp.client.StartApp;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TemplatePanel extends FlowPanel {

	public TemplatePanel() {
		Map<String, TemplateHandler> th = new HashMap<String, TemplateHandler>();
		th.put("t1", new TemplateHandler() {
			@Override
			public Widget onTemplate(String id) {
				return new TextBox();
			}
		});
		th.put("t2", new TemplateHandler() {
			@Override
			public Widget onTemplate(String id) {
				return new TextBox();
			}
		});
		StartApp.templater.template("startapp.jsp", new TemplaterHandler() {
			@Override
			public void onTemplate(HTMLPanel panel) {
				add(panel);
			}

			@Override
			public void onFailure(Throwable e) {
				add(new Label("ERROR"));
				e.printStackTrace();
			}
		}, th);

	}
}
