package org.gwtapp.core.client.template;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.HTMLPanel;

;
public class TemplateRepository {

	private static final int STATUS_CODE_OK = 200;

	private String path;
	private Map<String, String> templates = new HashMap<String, String>();
	private String uid = HTMLPanel.createUniqueId();

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

	protected String getUID() {
		return uid;
	}

	public void load(String name, TemplateRepositoryHandler handler) {
		load(name, handler, true);
	}

	public void load(String name, final TemplateRepositoryHandler handler,
			boolean cache) {
		final String uid = getUID();
		if (cache && templates.containsKey(name)) {
			handler.onTemplate(templates.get(name), uid);
		} else {
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
					getPath() + name + "?uid=" + uid);
			try {
				builder.sendRequest(null, new RequestCallback() {
					public void onError(Request request, Throwable e) {
						handler.onFailure(e);
					}

					public void onResponseReceived(Request request,
							Response response) {
						if (STATUS_CODE_OK == response.getStatusCode()) {
							handler.onTemplate(response.getText(), uid);
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
