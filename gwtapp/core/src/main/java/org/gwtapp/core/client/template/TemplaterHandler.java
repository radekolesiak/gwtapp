package org.gwtapp.core.client.template;

import com.google.gwt.user.client.ui.HTMLPanel;

public interface TemplaterHandler {

	void onTemplate(HTMLPanel panel);

	void onFailure(Throwable e);
}
