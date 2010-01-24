package org.gwtapp.core.client.template;

public interface TemplateRepositoryHandler {
	
	void onTemplate(String template);

	void onFailure(Throwable e);
}
