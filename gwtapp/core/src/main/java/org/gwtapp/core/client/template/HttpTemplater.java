package org.gwtapp.core.client.template;

import java.util.Map;

public class HttpTemplater implements Templater {

	// TODO important: cache templates in the main html page by means of
	// Dictionary !!!!

	private final HttpTemplateRepository repository = new HttpTemplateRepository();

	public HttpTemplater() {
		this("/");
	}

	public HttpTemplater(String path) {
		repository.setPath(path);
	}

	@Override
	public void template(String name,
			Map<String, WidgetHandler> widgetHandlers, TemplaterHandler handler) {
		repository.load(name, new TemplateRepositoryHandlerAdapter(name,
				widgetHandlers, handler));
	}

	public void setPath(String path) {
		repository.setPath(path);
	}

	public String getPath() {
		return repository.getPath();
	}
}
