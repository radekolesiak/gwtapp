package org.gwtapp.core.rpc.html.ui.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class HWidget implements IsSerializable {

	private String id;	
	private String tag;
	private String name;
	private List<String> styles = new ArrayList<String>();

	public HWidget() {
	}
	
	public HWidget(String tag) {
		setTag(tag);
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void addStyle(String... style) {
		styles.addAll(Arrays.asList(style));
	}

	public void setStyles(List<String> styles) {
		this.styles = styles;
	}

	public List<String> getStyles() {
		return styles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
