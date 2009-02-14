package com.cuprum.web.common.client;

import com.cuprum.web.common.client.data.TValue;

public interface HasValue {
	@SuppressWarnings("unchecked")
	void setValue(TValue value);

	@SuppressWarnings("unchecked")
	TValue getValue();
}
