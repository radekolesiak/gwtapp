package org.gwtapp.ccalc.client;

import java.util.Map;

import org.gwtapp.template.client.handler.TemplateHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.dom.client.Element;

public class ElementCallbackAdapter implements TemplatePanel.ElementCallback {

	private final Element element;

	public ElementCallbackAdapter(Element element) {
		this.element = element;
	}

	@Override
	public Element getElement() {
		return element;
	}

	@Override
	public void template(TemplatePanel<?> owner,
			Map<String, TemplateHandler> widgetHandlers) {

	}
}
