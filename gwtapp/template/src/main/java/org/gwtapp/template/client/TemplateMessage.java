package org.gwtapp.template.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class TemplateMessage {

	private final static Map<String, Map<String, String>> cache = new HashMap<String, Map<String, String>>();
	private final Map<String, String> patterns;

	public TemplateMessage(Widget widget) {
		this(widget.getElement());
	}

	public TemplateMessage(String id) {
		this(DOM.getElementById(id));
	}

	public TemplateMessage(Element e) {
		Map<String, String> patterns = null;
		String msg = e.getAttribute("t:msg");
		if (msg != null) {
			if (cache.containsKey(msg)) {
				patterns = cache.get(msg);
			} else {
				patterns = new HashMap<String, String>();
				TemplateUtils.parseMessages(msg, patterns);
				cache.put(msg, patterns);
			}
		}
		this.patterns = patterns;
	}

	public String getPattern(String name) {
		if (patterns != null) {
			return patterns.get(name);
		} else {
			return null;
		}
	}

	public String getMessage(String name, String... params) {
		String pattern = getPattern(name);
		if (pattern != null) {
			return TemplateUtils.replaceParameters(pattern, params);
		} else {
			return null;
		}
	}

	public static void clearCache() {
		cache.clear();
	}
}
