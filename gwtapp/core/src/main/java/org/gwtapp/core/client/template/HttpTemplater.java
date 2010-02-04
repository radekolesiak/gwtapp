package org.gwtapp.core.client.template;

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
	public void template(String name, TemplaterHandler handler) {
		repository.load(name, new TemplateRepositoryHandlerAdapter(name,
				handler));
	}

	public void setPath(String path) {
		repository.setPath(path);
	}

	public String getPath() {
		return repository.getPath();
	}

}
