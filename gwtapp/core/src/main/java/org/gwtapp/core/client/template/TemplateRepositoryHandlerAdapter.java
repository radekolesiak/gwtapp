package org.gwtapp.core.client.template;

import com.google.gwt.core.client.GWT;

public class TemplateRepositoryHandlerAdapter implements
		TemplateRepositoryHandler {

	@Override
	public void onFailure(Throwable e) {
		GWT.getUncaughtExceptionHandler().onUncaughtException(e);
	}

	@Override
	public void onTemplate(Template template) {

	}

}
