package org.gwtapp.ccalc.client.data.book.metafields.summarypage;

import org.gwtapp.ccalc.client.data.book.SummaryPage;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class IdMetaField extends MetaFieldAdapter<SummaryPage, Integer> {

	public IdMetaField() {
		this("id");
	}

	public IdMetaField(String name) {
		super(name);
	}

	@Override
	public Integer get(SummaryPage model) {
		return model.getId();
	}

	@Override
	public void set(SummaryPage model, Integer value) {
		model.setId((Integer) value);
	}

}
