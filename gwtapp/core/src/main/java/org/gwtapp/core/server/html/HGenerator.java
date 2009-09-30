package org.gwtapp.core.server.html;

import java.util.Arrays;
import java.util.List;

import org.gwtapp.core.client.html.ui.core.HWidget;
import org.gwtapp.core.client.html.ui.core.IContainer;
import org.gwtapp.core.client.html.ui.core.IElementValue;
import org.gwtapp.core.client.html.ui.core.ILeaf;

public class HGenerator {

	private static int id = 0;

	private final String dictionary;
	private StringBuffer html = new StringBuffer();
	private String rpc = "";

	public HGenerator(String dictionary, HWidget widget)
			throws HGeneratorException {
		this(dictionary, new HWidget[] { widget });
	}

	public HGenerator(String dictionary, HWidget[] widgets)
			throws HGeneratorException {
		this(dictionary, Arrays.asList(widgets));
	}

	public HGenerator(String dictionary, List<HWidget> widgets)
			throws HGeneratorException {
		this.dictionary = dictionary;
		createDictionary(html);
		for (HWidget widget : widgets) {
			setId(widget);
			try {
				rpc = HSerializer.serialize(widget);
			} catch (HSerializerException e) {
				throw new HGeneratorException(e);
			}
			createRPC(html, widget.getName(), rpc);
			createDOM(html, widget);
		}
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

	private void createDictionary(StringBuffer html) {
		html.append("<script>");
		html.append(dictionary);
		html.append(" = new Array();");
		html.append("</script>");
	}

	private void createRPC(StringBuffer html, String name, String rpc) {
		html.append("<script>");
		html.append(dictionary);
		html.append("[\"");
		html.append(name);
		html.append("\"]=\"");
		html.append(HSerializer.encode(rpc));
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
