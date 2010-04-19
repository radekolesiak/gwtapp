package org.gwtapp.template.client;


public interface TemplaterHandler {

	void onTemplate(Template template);

	void onFailure(Throwable e);
}
