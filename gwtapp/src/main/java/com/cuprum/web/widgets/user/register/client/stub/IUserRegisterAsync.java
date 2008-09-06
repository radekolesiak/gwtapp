package com.cuprum.web.widgets.user.register.client.stub;

import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUserRegisterAsync {
	void processUserRegister(TUserRegisterValue userRegister, AsyncCallback<TUserRegisterValue> callback);
}
