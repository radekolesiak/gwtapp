package org.gwtapp.extension.widget.client;

import com.google.gwt.junit.client.GWTTestCase;

public abstract class WidgetTestCase extends GWTTestCase {
	@Override
	public String getModuleName() {
		return "org.gwtapp.extension.widget.WidgetTest";
	}
}
