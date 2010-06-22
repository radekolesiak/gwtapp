package org.gwtapp.template.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class HtmlRepository {

	public HtmlRepository() {
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
		Template template = new Template();
		if (e != null) {
			template.setHtml(e.getInnerHTML());
			template.setTag(e.getTagName());
			template.setStyle(e.getAttribute("style"));
			template.setStyleClass(e.getClassName());
			template.setAttachTo(name);
		} else {
			template = getDefaultTemplate();
		}
		return template;
	}

	private Template getDefaultTemplate() {
		return new Template("div", "", "", "");
	}
}
