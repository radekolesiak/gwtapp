package com.cuprum.web.common.client;

import com.cuprum.web.common.client.data.TValue;

public interface HasValue {
	void setValue(TValue value);
	TValue getValue();
}
