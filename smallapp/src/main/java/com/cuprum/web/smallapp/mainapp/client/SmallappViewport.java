package com.cuprum.web.smallapp.mainapp.client;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.LayoutData;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class SmallappViewport extends Viewport {
	LayoutData headerData = new FlowData(0, 0, 0, 0);

	LayoutData centerData = new FlowData(0, 0, 0, 0);

	LayoutData footerData = new FlowData(0, 0, 0, 0);

	LayoutContainer headerPanel = new LayoutContainer();

	LayoutContainer centerPanel = new LayoutContainer();

	LayoutContainer footerPanel = new LayoutContainer();

	public void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		setScrollMode(Scroll.AUTO);
		setLayout(new FlowLayout());
		add(headerPanel, headerData);
		add(centerPanel, centerData);
		add(footerPanel, footerData);
		headerPanel.add(new LabelField("Header"));
		footerPanel.add(new LabelField("Footer"));
	}

	public void setContent(Widget content) {
		centerPanel.removeAll();
		centerPanel.add(content);
	}
}
