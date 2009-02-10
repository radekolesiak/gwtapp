package com.cuprum.web.widgets.user.password.client.stub;

import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByToken;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordGetToken;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUserPasswordAsync {
	void processChangePasswordByUser(TChangePasswordByUser passwords,
			AsyncCallback<TChangePasswordByUser> callback);

	void changePasswordGetToken(TChangePasswordGetToken login,
			AsyncCallback<TChangePasswordGetToken> callback);

	void changePasswordByToken(TChangePasswordByToken password,
			AsyncCallback<TChangePasswordByToken> callback);
}
