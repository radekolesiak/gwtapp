package org.gwtapp.template.client;

public interface TemplateRepositoryHandler {
	
	void onTemplate(Template template);

	void onFailure(Throwable e);
}
