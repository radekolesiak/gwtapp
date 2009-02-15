package com.cuprum.web.widgets.user.password.client.stub;

import com.cuprum.web.common.client.exceptions.usersession.SessionNotFoundException;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByToken;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordGetToken;
import com.google.gwt.user.client.rpc.RemoteService;

public interface IUserPassword extends RemoteService {
	TChangePasswordByUser processChangePasswordByUser(
			TChangePasswordByUser passwords) throws SessionNotFoundException;

	TChangePasswordGetToken changePasswordGetToken(
			TChangePasswordGetToken login);
	
	TChangePasswordByToken changePasswordByToken(TChangePasswordByToken password);
}
