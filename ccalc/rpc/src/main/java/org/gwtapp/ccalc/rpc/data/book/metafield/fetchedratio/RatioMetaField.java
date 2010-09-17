package org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio;

import org.gwtapp.ccalc.rpc.data.book.FetchedRatio;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;

public class RatioMetaField extends MetaFieldAdapter<FetchedRatio, Double> {
	public RatioMetaField() {
		super("ratio");
	}

	@Override
	public void set(FetchedRatio model, Double value) {
		model.setRatio(value);
	}

	@Override
	public Double get(FetchedRatio model) {
		return model.getRatio();
	}
}
