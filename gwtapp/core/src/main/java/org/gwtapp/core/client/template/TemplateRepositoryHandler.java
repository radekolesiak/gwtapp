package org.gwtapp.core.client.template;

public interface TemplateRepositoryHandler {
	
	void onTemplate(String template, String uid);

	void onFailure(Throwable e);
}
