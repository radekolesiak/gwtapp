package org.gwtapp.template.client;

import com.google.gwt.user.client.ui.HTML;

public class SyncTemplateRepository {

	private String repository;

	public SyncTemplateRepository() {
		this("/");
	}

	public SyncTemplateRepository(String repository) {
		setRepository(repository);
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getRepository() {
		return repository;
	}

	public Template load(String name) {
		try {
			return getTemplate(name);
		} catch (Throwable e) {
			return new Template("div", "", "", "");
		}
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
