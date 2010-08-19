package org.gwtapp.ccalc.rpc.data.book;

import java.io.Serializable;
import java.util.List;

import org.gwtapp.ccalc.rpc.data.book.metafield.summarypage.CalculationsMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.summarypage.IdMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.summarypage.SummaryMetaField;
import org.gwtapp.core.rpc.data.ModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface SummaryPage extends ModelData, IsSerializable, Serializable {

	public final static IdMetaField ID = new IdMetaField();
	public final static CalculationsMetaField CALCULATIONS = new CalculationsMetaField();
	public final static SummaryMetaField SUMMARY = new SummaryMetaField();

	Integer getId();

	void setId(Integer id);

	void setCalculations(List<Calculation> calculations);

	List<Calculation> getCalculations();

	List<Calculation> getSummary();

	void setSummary(List<Calculation> summaries);

}
