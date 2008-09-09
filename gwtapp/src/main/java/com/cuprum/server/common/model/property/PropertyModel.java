package com.cuprum.server.common.model.property;

import com.cuprum.server.common.entities.Property;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.property.xql.CrGetProperty;

public class PropertyModel extends Model {
	public Property get(String property) {
		return execute(new CrGetProperty(property));
	}
}
