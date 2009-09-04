package org.gwtapp.core.client.html.ui.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HPanel extends HWidget implements IsContainer {

	public final static String TAG = "div";

	private List<HWidget> widgets = new ArrayList<HWidget>();

	public HPanel() {
		super(TAG);
	}
	
	private List<HWidget> getWidgets() {
		return widgets;
	}

	@Override
	public void addWidget(HWidget... widgets) {
		getWidgets().addAll(Arrays.asList(widgets));
	}

	@Override
	public void clear() {
		getWidgets().clear();
	}

	@Override
	public HWidget getWidget(int index) {
		return getWidgets().get(index);
	}

	@Override
	public void removeWidget(HWidget... widgets) {
		getWidgets().removeAll(Arrays.asList(widgets));
	}

	@Override
	public int size() {
		return getWidgets().size();
	}

	@Override
	public Iterator<HWidget> iterator() {
		return getWidgets().iterator();
	}

}
