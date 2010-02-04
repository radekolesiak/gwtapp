package org.gwtapp.core.client.template;

public interface TemplateRepositoryHandler {
	
	void onTemplate(Template template);

	void onFailure(Throwable e);
}
