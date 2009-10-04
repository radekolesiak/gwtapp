package org.gwtapp.core.client.ui.form;

import org.gwtapp.core.client.data.ModelData;

public interface IsFieldPanel<T extends ModelData> {
	<X> void addField(FieldPanel<T, X> field);

	<X> void removeField(FieldPanel<T, X> field);
}
