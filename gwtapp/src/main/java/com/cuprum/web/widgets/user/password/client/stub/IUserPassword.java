package com.cuprum.web.widgets.user.password.client.stub;

import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.google.gwt.user.client.rpc.RemoteService;

public interface IUserPassword extends RemoteService {
	TChangePasswordByUser processChangePasswordByUser(
			TChangePasswordByUser passwords) throws SessionNotFoundException;
}
