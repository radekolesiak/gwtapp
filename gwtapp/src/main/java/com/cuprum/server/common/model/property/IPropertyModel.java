package com.cuprum.server.common.model.property;

import com.cuprum.server.common.entities.Property;
import com.cuprum.server.common.model.IModel;

public interface IPropertyModel extends IModel {
	Property get(String property);
}
