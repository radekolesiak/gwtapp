package org.gwtapp.ccalc.rpc.data.book.metafield.book;

import org.gwtapp.ccalc.rpc.data.book.Book;
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
