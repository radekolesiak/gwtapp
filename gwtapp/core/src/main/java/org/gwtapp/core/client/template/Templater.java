package org.gwtapp.core.client.template;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.Utils;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class Templater {

	// TODO important: cache templates in the main html page by means of
	// Dictionary !!!!

	TemplateRepository repository = new TemplateRepository();

	public Templater() {
		this("/");
	}

	public Templater(String path) {
		repository.setPath(path);
	}

	public void template(final String name, final TemplaterHandler handler,
			final Map<String, TemplateHandler> handlers) {
		final Map<String, String> ids = new HashMap<String, String>();
		for (String template : handlers.keySet()) {
			ids.put(template, HTMLPanel.createUniqueId());
		}
		repository.load(name, new TemplateRepositoryHandler() {
			@Override
			public void onTemplate(String template) {
				try {
					class TemplateHTMLPanel extends HTMLPanel {
						public TemplateHTMLPanel(String html) {
							super(html);
							addStyleName("templater");
							addStyleName("templater-" + name);
						}

						@Override
						protected void onAttach() {
							super.onAttach();
							for (Map.Entry<String, String> entry : ids
									.entrySet()) {
								String field = entry.getKey();
								String id = entry.getValue();
								Element element = DOM.getElementById(id);
								if (element != null) {
									String style = element
											.getAttribute("class");
									Widget widget = handlers.get(field)
											.onTemplate(id);
									if (style != null && !style.isEmpty()) {
										widget.addStyleName(style);
									}
									addAndReplaceElement(widget, id);
								}
							}
						};
					}
					handler.onTemplate(new TemplateHTMLPanel(replaceTemplate(
							template, ids)));
				} catch (Throwable e) {
					handler.onFailure(e);
				}
			}

			@Override
			public void onFailure(Throwable e) {
				handler.onFailure(e);
			}
		});
	}

	private native String replaceTemplate(String template, String regexp,
			JavaScriptObject array)/*-{
									return template.replace(
									new RegExp(regexp, "gi"),  
									function($1){
										if($1 && array[$1]){
											return("id=\""+array[$1]+"\"");
										} else {
											return("");
										}
									}
									)
									}-*/;

	private String replaceTemplate(String template, Map<String, String> ids) {
		String regexp = "";
		for (String name : ids.keySet()) {
			if (!regexp.isEmpty()) {
				regexp += "|";
			}
			regexp += "(template=\"" + name + "\")";
		}
		JavaScriptObject array = Utils.createArray();
		for (Map.Entry<String, ?> entry : ids.entrySet()) {
			Utils.addToArray(array, "template=\"" + entry.getKey() + "\"",
					entry.getValue() + "");
		}
		return replaceTemplate(template, regexp, array);
	}

	public void setPath(String path) {
		repository.setPath(path);
	}

	public String getPath() {
		return repository.getPath();
	}
}
