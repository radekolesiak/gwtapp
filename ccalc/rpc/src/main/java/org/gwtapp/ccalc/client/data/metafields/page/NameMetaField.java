package org.gwtapp.ccalc.client.data.metafields.page;

import org.gwtapp.ccalc.client.data.Page;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class NameMetaField extends MetaFieldAdapter<Page, String> {

	public NameMetaField() {
		this("name");
	}

	public NameMetaField(String name) {
		super(name);
	}

	@Override
	public String get(Page model) {
		return model.getName();
	}

	@Override
	public void set(Page model, String value) {
		model.setName((String) value);
	}
	
	@Override
	public String def() {
		return "";
	}

}
