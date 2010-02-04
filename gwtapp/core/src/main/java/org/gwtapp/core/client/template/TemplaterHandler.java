package org.gwtapp.core.client.template;


public interface TemplaterHandler {

	void onTemplate(Template template);

	void onFailure(Throwable e);
}
