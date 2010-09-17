package org.gwtapp.ccalc.rpc.data.book.metafield.operation;

import org.gwtapp.ccalc.rpc.data.book.FetchedRatio;
import org.gwtapp.ccalc.rpc.data.book.Operation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;

public class FetchedRatioMetaField extends
		MetaFieldAdapter<Operation, FetchedRatio> {
	public FetchedRatioMetaField() {
		super("fetchedratio");
	}

	@Override
	public void set(Operation model, FetchedRatio value) {
		model.setFetchedRatio(value);
	}

	@Override
	public FetchedRatio get(Operation model) {
		return model.getFetchedRatio();
	}
}
