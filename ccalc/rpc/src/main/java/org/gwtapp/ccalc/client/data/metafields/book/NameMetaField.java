package org.gwtapp.ccalc.client.data.metafields.book;

import org.gwtapp.ccalc.client.data.Book;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class NameMetaField extends MetaFieldAdapter<Book, String> {

	public NameMetaField() {
		this("name");
	}

	public NameMetaField(String name) {
		super(name);
	}

	@Override
	public String get(Book model) {
		return model.getName();
	}

	@Override
	public void set(Book model, String value) {
		model.setName((String) value);
	}
	
	@Override
	public String def() {
		return "";
	}

}
