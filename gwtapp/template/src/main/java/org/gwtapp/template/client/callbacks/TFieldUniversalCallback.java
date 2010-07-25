package org.gwtapp.template.client.callbacks;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.TemplateUtils;
import org.gwtapp.template.client.ui.HTMLPanel;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class TFieldUniversalCallback implements TemplatePanelCallback {

	private final Template template;

	public TFieldUniversalCallback() {
		this(null);
	}

	public TFieldUniversalCallback(Template template) {
		this.template = template;
	}

	@Override
	public void template(TemplatePanel<?> owner,
			Map<String, TemplateHandler> widgetHandlers) {
		Map<String, String> ids = new HashMap<String, String>();
		for (String template : widgetHandlers.keySet()) {
			ids.put(template, HTMLPanel.createUniqueId());
		}
		if (template != null) {
			DOM.setInnerHTML(owner.getElement(), TemplateUtils.replaceTemplate(
					owner.getPattern(), template.getHtml(), ids));
		} else {
			DOM.setInnerHTML(owner.getElement(), TemplateUtils.replaceTemplate(
					owner.getPattern(), DOM.getInnerHTML(owner.getElement()),
					ids));
		}
		for (Map.Entry<String, String> entry : ids.entrySet()) {
			String field = entry.getKey();
			String id = entry.getValue();
			Element element = DOM.getElementById(id);
			if (element != null) {
				TemplateHandler handler = widgetHandlers.get(field);
				if (handler != null) {
					Widget widget = handler.onWidget(id);
					if (widget != null) {
						String styleClass = element.getAttribute("class");
						String style = element.getAttribute("style");
						if (styleClass != null && !styleClass.isEmpty()) {
							widget.addStyleName(styleClass);
						}
						if (style != null && !style.isEmpty()) {
							widget.getElement().setAttribute("style", style);
						}
						owner.addAndReplaceElement(widget, id);
					}
				}
			}
		}
	}
}
