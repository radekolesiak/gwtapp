package org.gwtapp.ccalc.client.ui.calculations;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.template.client.handler.WidgetHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class CalculationsHeaderFormPanel extends
		TemplatePanel<CalculationsHeaderFormPanel.State> {

	public static enum State {
		NONE, ADD;
	}

	private final static String template = "calculations/CalculationsHeaderFormPanel.jsp";
	private final WidgetHandler add = add("add", new WidgetHandler());

	public CalculationsHeaderFormPanel() {
		super(CCalc.templateService.load(template), State.NONE);
	}

	@Override
	public void onWidgets() {
		add.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setValue(State.ADD, true);
			}
		});
	}
}
