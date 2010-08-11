package org.gwtapp.template.client;

import java.util.Map;

import org.gwtapp.template.client.callback.TFieldCallback;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.user.client.ui.HTML;

public class TemplateRepository {

	public static enum Type {
		TFIELD, // by 't:field' attribute 
		ID;		// by 'id' attribute (not supported yet)
	}

	private final Type defaultType;

	private String repository;

	public TemplateRepository() {
		this("/");
	}

	public TemplateRepository(String repository) {
		this(repository, Type.TFIELD);
	}

	public TemplateRepository(String repository, Type defaultType) {
		setRepository(repository);
		this.defaultType = defaultType;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getRepository() {
		return repository;
	}

	public Type getDefaultType(){
		return defaultType;
	}
	
	public TemplatePanel.TemplateCallback load(String name) {
		return load(name, defaultType);
	}

	public TemplatePanel.TemplateCallback load(String name, Type type) {
		switch (type) {
		case TFIELD:
			return loadTField(name);
		default:
			return null;
		}
	}

	private TemplatePanel.TemplateCallback loadTField(String name) {
		try {
			return load(getTemplate(name), new TFieldCallback());
		} catch (Throwable e) {
			return load(getDefaultTemplate(), new TFieldCallback());
		}
	}

	// Delegate callback
	private TemplatePanel.TemplateCallback load(final Template template,
			final TemplatePanel.Callback callback) {
		return new TemplatePanel.TemplateCallback() {
			@Override
			public Template getTemplate() {
				return template;
			}

			@Override
			public void template(TemplatePanel<?> owner,
					Map<String, TemplateHandler> widgetHandlers) {
				callback.template(owner, widgetHandlers);
			}
		};
	}

	private Template getTemplate(String name) {
		Template template = new Template();
		template
				.setHtml(toEmpty(getTemplateItem(getRepository(), name, "body")));
		template.setTag(toEmpty(getTemplateItem(getRepository(), name, "tag")));
		template.setStyle(toEmpty(getTemplateItem(getRepository(), name,
				"style")));
		template.setStyleClass(toEmpty(getTemplateItem(getRepository(), name,
				"styleclass")));
		return template;
	}

	private Template getDefaultTemplate() {
		return new Template("div", "", "", "");
	}

	private static String toEmpty(String s) {
		if (s == null) {
			return "";
		} else {
			HTML html = new HTML(s);
			return html.getText();
		}
	}

	private static native String getTemplateItem(String repository,
			String name, String item)/*-{
										return $wnd.Templates[repository][name][item];
										}-*/;
}
