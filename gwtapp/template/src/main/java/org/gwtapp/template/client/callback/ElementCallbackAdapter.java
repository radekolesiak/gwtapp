package org.gwtapp.template.client.callback;

import java.util.Map;

import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.gwtapp.template.client.ui.TemplatePanel.ElementCallback;

import com.google.gwt.dom.client.Element;

public class ElementCallbackAdapter implements ElementCallback {

	private final ElementCallback callback;

	public ElementCallbackAdapter(ElementCallback callback) {
		this.callback = callback;
	}

	@Override
	public Element getElement() {
		return callback.getElement();
	}

	@Override
	public void template(TemplatePanel<?> owner,
			Map<String, TemplateHandler> widgetHandlers) {
		callback.template(owner, widgetHandlers);
	}
}
