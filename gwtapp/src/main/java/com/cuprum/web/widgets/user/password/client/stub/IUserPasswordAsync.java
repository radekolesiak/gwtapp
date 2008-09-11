package com.cuprum.web.widgets.user.password.client.stub;

import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUserPasswordAsync {
	void processChangePasswordByUser(TChangePasswordByUser passwords,
			AsyncCallback<TChangePasswordByUser> callback);
}
