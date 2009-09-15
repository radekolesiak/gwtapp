package org.gwtapp.core.server.html;

import org.gwtapp.core.client.html.ui.core.HWidget;
import org.gwtapp.core.client.html.ui.core.HasHTML;
import org.gwtapp.core.client.html.ui.core.IsContainer;

public class HGenerator {

	private static int id = 0;

	private StringBuffer html = new StringBuffer();
	private String rpc = "";

	public HGenerator(HWidget widget) throws HGeneratorException {
		setId(widget);
		try {
			rpc = HSerializer.serialize(widget);
		} catch (HSerializerException e) {
			throw new HGeneratorException(e);
		}
		createRPC(html, rpc);
		createDOM(html, widget);
	}

	private void setId(HWidget widget) {
		widget.setId(getId());
		if (widget instanceof IsContainer) {
			IsContainer container = (IsContainer) widget;
			for (HWidget child : container) {
				setId(child);
			}
		}
	}

	private void createRPC(StringBuffer html, String rpc) {
		html.append("<script>");
		html.append("HWidget = new Array();");
		html.append("HWidget[\"HWidget\"]=\"");
		byte[] bytes = rpc.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			html.append(bytes[i]);
			if (i < bytes.length - 1) {
				html.append(",");
			}
		}
		html.append("\";");
		html.append("</script>");
	}

	private static String o = "<";
	private static String c = ">";

	private void createDOM(StringBuffer html, HWidget widget) {
		html.append(o);
		html.append(widget.getTag());
		html.append(" id=\"");
		html.append(widget.getId());
		html.append("\"");
		html.append(c);
		if (widget instanceof IsContainer) {
			IsContainer container = (IsContainer) widget;
			for (HWidget child : container) {
				createDOM(html, child);
			}
		} else if (widget instanceof HasHTML) {
			HasHTML h = (HasHTML) widget;
			html.append(h.getHTML());
		}
		html.append(o + "/");
		html.append(widget.getTag());
		html.append(c);
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
