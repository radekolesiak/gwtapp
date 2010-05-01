package org.gwtapp.core.client;

import java.util.Date;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Utils {

	public static ComplexPanel updateToRootPanel(Widget widget) {
		if (widget.getParent() instanceof ComplexPanel) {
			return (ComplexPanel) widget.getParent();
		}
		String id = widget.getElement().getParentElement().getAttribute("id");
		if (id == null || id.isEmpty()) {
			id = "R" + Math.random() + ":" + new Date().getTime();
			widget.getElement().getParentElement().setAttribute("id", id);
		}
		RootPanel rootPanel = RootPanel.get(id);
		rootPanel.add(widget);
		return rootPanel;
	}

	public static JavaScriptObject mapToJavaScriptArray(Map<String, ?> map) {
		JavaScriptObject array = createArray();
		for (Map.Entry<String, ?> entry : map.entrySet()) {
			addToArray(array, entry.getKey(), entry.getValue());
		}
		return array;
	}

	public native static JavaScriptObject createArray() /*-{
														var array = new Array();
														return array;
														}-*/;

	public native static void addToArray(JavaScriptObject array, String key,
			Object o) /*-{
						array[key] = o;
						}-*/;
}
