package com.cuprum.web.templates.simple.client.page;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.Widget;

/**
 * Top level container of the page.
 * 
 * @author Radek Olesiak
 */
public class Body extends Viewport {
	final ContentPanel north = new ContentPanel();
	final ContentPanel west = new ContentPanel();
	final ContentPanel center = new ContentPanel();
	final ContentPanel east = new ContentPanel();
	final ContentPanel south = new ContentPanel();


	/** Adds the container. */
	public Body() {
		setLayout(new BorderLayout());

		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,
				100);
		northData.setCollapsible(true);
		northData.setFloatable(true);
		northData.setSplit(true);
		northData.setMargins(new Margins(5, 5, 0, 5));

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200);
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins(5));

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setMargins(new Margins(5, 0, 5, 0));

		BorderLayoutData eastData = new BorderLayoutData(LayoutRegion.EAST, 200);
		eastData.setSplit(true);
		eastData.setCollapsible(true);
		eastData.setMargins(new Margins(5));

		BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH,
				100);
		southData.setSplit(true);
		southData.setCollapsible(true);
		southData.setFloatable(true);
		southData.setMargins(new Margins(0, 5, 5, 5));

		center.setScrollMode(Scroll.AUTO);

		add(north, northData);
		add(west, westData);
		add(center, centerData);
		add(east, eastData);
		add(south, southData);
	}

	private Widget content = null;

	public void setContent(Widget content) {
		if (this.content != null) {
			center.remove(this.content);
		}
		center.add(content);
		this.content = content;
	}
}
