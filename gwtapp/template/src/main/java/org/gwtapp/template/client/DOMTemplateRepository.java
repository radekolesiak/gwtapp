package org.gwtapp.template.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class DOMTemplateRepository {

	public DOMTemplateRepository() {
	}

	public Template load(String name) {
		try {
			return getTemplate(name);
		} catch (Throwable e) {
			return getDefaultTemplate();
		}
	}

	private Template getTemplate(String name) {
		Element e = DOM.getElementById(name);
		if (e != null) {
			Template template = new Template();
			template.setHtml(e.getInnerHTML());
			template.setTag(e.getTagName());
			template.setStyle(e.getAttribute("style"));
			template.setStyleClass(e.getClassName());
			return template;
		} else {
			return getDefaultTemplate();
		}
	}

	private Template getDefaultTemplate() {
		return new Template("div", "", "", "");
	}
}
