package org.gwtapp.ccalc.client.ui;

import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public class DisclosurePanel extends TemplatePanel<Boolean> {

	private final Widget content;

	public <E extends Widget & HasClickHandlers> DisclosurePanel(
			TemplatePanel.TemplateCallback template, E label, Widget content) {
		super(template, false);
		this.content = content;
		add("label", new UiHandler<E>(label));
		add("content", new UiHandler<Widget>(content));
		content.setVisible(false);
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		if (isTemplated()) {
			content.setVisible(value);
		}
		super.setValue(value, fireEvents);
	}
}
