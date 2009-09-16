package org.gwtapp.core.server.html;

import org.gwtapp.core.client.html.ui.core.HWidget;
import org.gwtapp.core.client.html.ui.core.IContainer;
import org.gwtapp.core.client.html.ui.core.IElementValue;
import org.gwtapp.core.client.html.ui.core.ILeaf;

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
		if (widget instanceof IContainer) {
			IContainer container = (IContainer) widget;
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

	private void createAttr(StringBuffer html, String attr, String value) {
		html.append(" ");
		html.append(attr);
		html.append("=\"");
		html.append(value); // TODO: replace "
		html.append("\"");
	}

	private void createDOM(StringBuffer html, HWidget widget) {
		html.append(o);
		html.append(widget.getTag());
		createAttr(html, "id", widget.getId());
		html.append("\"");
		if (widget instanceof IElementValue) {
			IElementValue elementValue = (IElementValue) widget;
			createAttr(html, "value", elementValue.getElementValue());
		}
		html.append(c);
		if (widget instanceof ILeaf) {
			ILeaf h = (ILeaf) widget;
			html.append(h.getLeaf());
		} else if (widget instanceof IContainer) {
			IContainer container = (IContainer) widget;
			for (HWidget child : container) {
				createDOM(html, child);
			}
		}
		html.append(o + "/");
		html.append(widget.getTag());
		html.append(c);
	}

	private synchronized static String getId() {
		return "" + (++id);
	}

	public String getRPC() {
		return rpc;
	}

	public String getHTML() {
		return html.toString();
	}
}
