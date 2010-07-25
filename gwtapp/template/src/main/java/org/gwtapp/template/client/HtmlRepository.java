package org.gwtapp.template.client;

import java.util.Map;

import org.gwtapp.template.client.callbacks.TFieldCallback;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class HtmlRepository {

	public HtmlRepository() {
	}

	public TemplatePanel.ElementCallback load(String name) {
		return loadTField(name);
	}

	private TemplatePanel.ElementCallback loadTField(String name) {
		try {
			return load(getTemplate(name), new TFieldCallback());
		} catch (Throwable e) {
			return load(getDefaultTemplate(), new TFieldCallback());
		}
	}

	private TemplatePanel.ElementCallback load(final Element element,
			final TFieldCallback callback) {
		return new TemplatePanel.ElementCallback() {
			@Override
			public Element getElement() {
				return element;
			}

			@Override
			public void template(TemplatePanel<?> owner,
					Map<String, TemplateHandler> widgetHandlers) {
				callback.template(owner, widgetHandlers);
			}
		};
	}

	private Element getTemplate(String name) {
		Element template = DOM.getElementById(name);
		if (template == null) {
			template = getDefaultTemplate();
		}
		return template;
	}

	private Element getDefaultTemplate() {
		return Document.get().createDivElement();
	}
}
