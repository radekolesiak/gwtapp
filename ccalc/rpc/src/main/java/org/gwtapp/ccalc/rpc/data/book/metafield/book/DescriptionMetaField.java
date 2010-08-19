package org.gwtapp.ccalc.rpc.data.book.metafield.book;

import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class DescriptionMetaField extends MetaFieldAdapter<Book, String> {

	public DescriptionMetaField() {
		this("description");
	}

	public DescriptionMetaField(String name) {
		super(name);
	}

	@Override
	public String get(Book model) {
		return model.getDescription();
	}

	@Override
	public void set(Book model, String value) {
		model.setDescription((String) value);
	}
	
	@Override
	public String def() {
		return "";
	}

}
