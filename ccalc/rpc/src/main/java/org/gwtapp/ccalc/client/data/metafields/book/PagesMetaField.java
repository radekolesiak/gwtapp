package org.gwtapp.ccalc.client.data.metafields.book;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.ccalc.client.data.Book;
import org.gwtapp.ccalc.client.data.Page;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class PagesMetaField extends MetaFieldAdapter<Book, List<Page>> {

	public PagesMetaField() {
		this("pages");
	}

	public PagesMetaField(String name) {
		super(name);
	}

	@Override
	public List<Page> get(Book model) {
		return model.getPages();
	}

	@Override
	public void set(Book model, List<Page> value) {
		model.setPages((List<Page>) value);
	}
	
	@Override
	public List<Page> def() {
		return new ArrayList<Page>();
	}

}
