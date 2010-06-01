package org.gwtapp.html.server;

import java.util.Arrays;
import java.util.List;

import org.gwtapp.core.server.HServer;
import org.gwtapp.core.server.HServerException;
import org.gwtapp.html.rpc.ui.HWidget;
import org.gwtapp.html.rpc.ui.IContainer;
import org.gwtapp.html.rpc.ui.IElementValue;
import org.gwtapp.html.rpc.ui.ILeaf;

public class HWidgetGenerator {

	private static volatile int id = 0;

	private final String dictionary;
	private StringBuffer html = new StringBuffer();
	private String rpc = "";

	public HWidgetGenerator(String dictionary, HWidget widget)
			throws HWidgetGeneratorException {
		this(dictionary, new HWidget[] { widget });
	}

	public HWidgetGenerator(String dictionary, HWidget[] widgets)
			throws HWidgetGeneratorException {
		this(dictionary, Arrays.asList(widgets));
	}

	public HWidgetGenerator(String dictionary, List<HWidget> widgets)
			throws HWidgetGeneratorException {
		this.dictionary = dictionary;
		createDictionary(html);
		for (HWidget widget : widgets) {
			setId(widget);
			try {
				rpc = HServer.success(widget);
			} catch (HServerException e) {
				throw new HWidgetGeneratorException(e);
			}
			createRPC(html, widget.getName(), rpc);
			html.append("\n");
			createDOM(html, widget);
			html.append("\n");
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
		html.append("<script type=\"text/javascript\">");
		html.append("var ");
		html.append(dictionary);
		html.append("; if(!");
		html.append(dictionary);
		html.append("){");
		html.append(dictionary);
		html.append(" = new Array();");
		html.append("}");
		html.append("</script>");
	}

	private void createRPC(StringBuffer html, String name, String rpc) {
		html.append("<script type=\"text/javascript\">");
		html.append(dictionary);
		html.append("[\"");
		html.append(name);
		html.append("\"]=\"");
		html.append(HServer.encode(rpc));
		html.append("\";");
		html.append("</script>");
	}

	private static String o = "<";
	private static String c = ">";

	private void createAttr(StringBuffer html, String attr, String value) {
		if (attr != null) {
			html.append(" ");
			html.append(attr);
			html.append("=\"");
			if (value != null) {
				html.append(value);
			}
			html.append("\"");
		}
	}

	private void createDOM(StringBuffer html, HWidget widget) {
		html.append(o);
		html.append(widget.getTag());
		createAttr(html, "id", widget.getId());
		if (widget instanceof IElementValue) {
			IElementValue elementValue = (IElementValue) widget;
			createAttr(html, "value", elementValue.getElementValue());
		}
		if (!"input".equalsIgnoreCase(widget.getTag())) {
			html.append(c);
		}
		if (widget instanceof ILeaf) {
			ILeaf h = (ILeaf) widget;
			html.append(h.getLeaf());
		} else if (widget instanceof IContainer) {
			IContainer container = (IContainer) widget;
			for (HWidget child : container) {
				createDOM(html, child);
			}
		}
		if (!"input".equalsIgnoreCase(widget.getTag())) {
			html.append(o);
		}
		html.append("/");
		if (!"input".equalsIgnoreCase(widget.getTag())) {
			html.append(widget.getTag());
		}
		html.append(c);
	}

	private static String getId() {
		return "hwidget-" + (++id);
	}

	public String getRPC() {
		return rpc;
	}

	public String getHTML() {
		return html.toString();
	}
}
