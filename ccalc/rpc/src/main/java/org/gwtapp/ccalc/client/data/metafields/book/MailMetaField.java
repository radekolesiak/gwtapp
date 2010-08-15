package org.gwtapp.ccalc.client.data.metafields.book;

import org.gwtapp.ccalc.client.data.Book;
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
