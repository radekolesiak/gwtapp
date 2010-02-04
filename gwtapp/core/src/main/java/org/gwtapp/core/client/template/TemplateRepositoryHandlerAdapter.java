package org.gwtapp.core.client.template;


public class TemplateRepositoryHandlerAdapter implements
		TemplateRepositoryHandler {

	final String name;
	final TemplaterHandler handler;

	public TemplateRepositoryHandlerAdapter(String name, TemplaterHandler handler) {
		this.name = name;
		this.handler = handler;
	}

	@Override
	public void onTemplate(String template) {
		try {
			if (template == null) {
				throw new IllegalStateException("Couldn't parse template");
			}
			handler.onTemplate(new Template("div", "template", template));
		} catch (Throwable e) {
			handler.onFailure(e);
		}
	}

	@Override
	public void onFailure(Throwable e) {
		handler.onFailure(e);
	}
}
