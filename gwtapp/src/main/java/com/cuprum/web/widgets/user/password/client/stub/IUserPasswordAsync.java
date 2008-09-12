package com.cuprum.web.widgets.user.password.client.stub;

import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordGetTokenValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUserPasswordAsync {
	void processChangePasswordByUser(TChangePasswordByUser passwords,
			AsyncCallback<TChangePasswordByUser> callback);

	void ChangePasswordGetToken(TChangePasswordGetTokenValue login,
			AsyncCallback<TChangePasswordGetTokenValue> callback);
}
