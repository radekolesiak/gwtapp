package org.gwtapp.template.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class TemplateMessage {

	private final Map<String, String> messages;

	public TemplateMessage(Widget widget) {
		this(widget.getElement());
	}

	public TemplateMessage(String id) {
		this(DOM.getElementById(id));
	}

	public TemplateMessage(Element e) {
		String msg = e.getAttribute("t:msg");
		if (msg == null) {
			messages = null;
		} else {			
			messages = new HashMap<String, String>();
			TemplateUtils.parseMessages(msg, messages);
		}
	}

	public String getMsg(String name) {
		if (messages != null) {
			return messages.get(name);
		} else {
			return null;
		}
	}
}
