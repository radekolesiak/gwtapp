package org.gwtapp.template.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class HtmlRepository {

	public HtmlRepository() {
	}

	public Element load(String name) {
		try {
			return getTemplate(name);
		} catch (Throwable e) {
			return getDefaultTemplate();
		}
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
