package org.gwtapp.core.server.html;

import org.gwtapp.core.client.html.ui.core.HWidget;
import org.gwtapp.core.client.html.ui.core.IsContainer;

public class HGenerator {

	private static int id = 0;

	private StringBuffer html = new StringBuffer();
	private String rpc = "";

	public HGenerator(HWidget widget) throws HGeneratorException {
		try {
			rpc = HSerializer.serialize(widget);
		} catch (HSerializerException e) {
			throw new HGeneratorException(e);
		}
		setId(widget);
		createRPC(html, rpc);
		createDOM(html, widget);
	}

	private void setId(HWidget widget) {
		widget.setId(getId());
		if (widget instanceof IsContainer) {
			IsContainer container = (IsContainer) widget;
			for (int i = 0; i < container.size(); i++) {
				setId(container.getWidget(i));
			}
		}
	}

	private void createRPC(StringBuffer html, String rpc) {
		html.append("<script>");
		html.append("HWidget[\"HWidget\"]=\"");
		html.append(rpc);
		html.append("\";");
		html.append("</script>");
	}

	private void createDOM(StringBuffer html, HWidget widget) {
		html.append("<");
		html.append(widget.getTag());
		html.append(" id=\"");
		html.append(widget.getId());
		html.append("\" id=\"");
		html.append(">");
		if (widget instanceof IsContainer) {
			IsContainer container = (IsContainer) widget;
			for (int i = 0; i < container.size(); i++) {
				createDOM(html, container.getWidget(i));
			}
		}
		html.append("</");
		html.append(widget.getTag());
		html.append(">");
	}

	public synchronized static String getId() {
		return "" + (++id);
	}

	public String getRPC() {
		return rpc;
	}

	public String getHTML() {
		return html.toString();
	}
}
