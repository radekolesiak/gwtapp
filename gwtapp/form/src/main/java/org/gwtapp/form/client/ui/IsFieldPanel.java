package org.gwtapp.form.client.ui;

import org.gwtapp.core.rpc.data.ModelData;

public interface IsFieldPanel<T extends ModelData> {
	<X> void addField(FieldPanel<T, X> field);

	<X> void removeField(FieldPanel<T, X> field);
	
	<X> FieldPanel<T, X> getField(String fieldName);
}
