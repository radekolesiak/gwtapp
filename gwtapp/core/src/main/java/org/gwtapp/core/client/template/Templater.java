package org.gwtapp.core.client.template;

import java.util.Map;

public interface Templater {

	void template(String name, TemplaterHandler handler);

	void template(String name, Map<String, WidgetHandler> widgetHandlers,
			TemplaterHandler handler);
}
