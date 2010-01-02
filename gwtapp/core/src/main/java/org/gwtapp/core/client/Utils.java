package org.gwtapp.core.client;

import java.util.Date;

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
			id = Math.random() + ":" + new Date().getTime();
			widget.getElement().getParentElement().setAttribute("id", id);
		}
		RootPanel rootPanel = RootPanel.get(id);
		rootPanel.add(widget);
		return rootPanel;
	}
}
