package org.gwtapp.ccalc.rpc.data.book.metafield.book;

import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class MailMetaField extends MetaFieldAdapter<Book, String> {

	public MailMetaField() {
		this("mail");
	}

	public MailMetaField(String name) {
		super(name);
	}

	@Override
	public String get(Book model) {
		return model.getMail();
	}

	@Override
	public void set(Book model, String value) {
		model.setMail((String) value);
	}
	
	@Override
	public String def() {
		return "";
	}

}
