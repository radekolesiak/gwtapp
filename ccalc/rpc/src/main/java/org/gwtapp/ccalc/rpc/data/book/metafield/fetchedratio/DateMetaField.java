package org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio;

import java.util.Date;

import org.gwtapp.ccalc.rpc.data.book.FetchedRatio;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;

public class DateMetaField extends MetaFieldAdapter<FetchedRatio, Date> {

	public DateMetaField() {
		super("date");
	}

	@Override
	public void set(FetchedRatio model, Date value) {
		model.setDate(value);
	}

	@Override
	public Date get(FetchedRatio model) {
		return model.getDate();
	}
}
