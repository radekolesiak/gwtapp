package org.gwtapp.html.client.io;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.RootPanel;

public class HRequestBuilder extends RequestBuilder {

	private final static FormPanel form = new FormPanel();
	private final static Hidden data = new Hidden("data");

	static {
		form.add(data);
		RootPanel.get().add(form);
	}

	public HRequestBuilder(Method httpMethod, String url) {
		super(httpMethod, url);
	}

	public HRequestBuilder(String httpMethod, String url) {
		super(httpMethod, url);
	}

	@Override
	public Request send() throws RequestException {
		// form.setMethod(getHTTPMethod());
		form.setMethod("POST");
		form.setAction(getUrl());
		data.setValue(getRequestData());
		form.submit();
		return null;
	}

}
