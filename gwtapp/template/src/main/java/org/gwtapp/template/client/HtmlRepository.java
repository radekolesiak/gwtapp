package org.gwtapp.template.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

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

	public <T extends Widget> T attach(String id, T widget) {
		Element e = DOM.getElementById(id);
		if (e != null) {
			e.setInnerHTML("");
			RootPanel.get(id).add(widget);
		}
		return widget;
	}

	public Template loadAttach(String id, Widget widget) {
		Template template = getTemplate(id);
		attach(id, widget);
		return template;
	}
}
