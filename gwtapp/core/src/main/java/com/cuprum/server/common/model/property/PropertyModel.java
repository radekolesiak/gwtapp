package com.cuprum.server.common.model.property;

import com.cuprum.server.common.entities.Property;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.property.xql.XqlGetProperty;

public class PropertyModel extends Model implements IPropertyModel {
	public Property get(String property) {
		return execute(new XqlGetProperty(property));
	}
}
