package org.gwtapp.core.client.template;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

;
public class TemplateRepository {

	private static final int STATUS_CODE_OK = 200;

	private String path;
	private Map<String, String> templates = new HashMap<String, String>();

	public TemplateRepository() {
		this("/");
	}

	public TemplateRepository(String path) {
		setPath(path);
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void load(String name, TemplateRepositoryHandler handler) {
		load(name, handler, true);
	}

	public void load(String name, final TemplateRepositoryHandler handler,
			boolean cache) {
		if (cache && templates.containsKey(name)) {
			handler.onTemplate(templates.get(name));
		} else {
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
					getPath() + name);
			try {
				builder.sendRequest(null, new RequestCallback() {
					public void onError(Request request, Throwable e) {
						handler.onFailure(e);
					}

					public void onResponseReceived(Request request,
							Response response) {
						if (STATUS_CODE_OK == response.getStatusCode()) {
							handler.onTemplate(response.getText());
						} else {
							// TODO support RPC exceptions
							handler.onFailure(new IllegalStateException(
									"Response Status Code = "
											+ response.getStatusCode()));
						}
					}
				});
			} catch (Throwable e) {
				handler.onFailure(e);
			}

		}
	}
}
