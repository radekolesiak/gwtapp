package com.cuprum.web.common.client;

public interface IFailureCallbackHandler {
	void onResponseFailure(Throwable caught);
}
