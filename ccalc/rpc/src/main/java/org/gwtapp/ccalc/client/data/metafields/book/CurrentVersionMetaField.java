package org.gwtapp.ccalc.client.data.metafields.book;

import org.gwtapp.ccalc.client.data.Book;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;

public class CurrentVersionMetaField extends MetaFieldAdapter<Book, String> {

	public CurrentVersionMetaField() {
		this("currentVersion");
	}

	public CurrentVersionMetaField(String name) {
		super(name);
	}

	@Override
	public String get(Book model) {
		return model.getCurrentVersion();
	}

	@Override
	public void set(Book model, String value) {
		model.setCurrentVersion((String) value);
	}

	@Override
	public String def() {
		return "v2.0";
	}
}
