package com.cuprum.web.common.client;

import java.io.Serializable;

public interface HasRegExp extends Serializable {
	boolean match(String value);
}
