package org.gwtapp.ccalc.client.data.metafields.summarypage;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.ccalc.client.data.Calculation;
import org.gwtapp.ccalc.client.data.SummaryPage;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class SummaryMetaField extends
		MetaFieldAdapter<SummaryPage, List<Calculation>> {

	public SummaryMetaField() {
		this("summary");
	}

	public SummaryMetaField(String name) {
		super(name);
	}

	@Override
	public List<Calculation> get(SummaryPage model) {
		return model.getSummary();
	}

	@Override
	public void set(SummaryPage model, List<Calculation> value) {
		model.setSummary((List<Calculation>) value);
	}

	@Override
	public List<Calculation> def() {
		return new ArrayList<Calculation>();
	}

}
