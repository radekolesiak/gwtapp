package com.cuprum.web.widgets.user.register.client.stub;

import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.google.gwt.user.client.rpc.RemoteService;

public interface IUserRegister extends RemoteService {

	public final static String CONFIRM_REQUEST = "confirm";

	TUserRegisterValue processUserRegister(TUserRegisterValue userRegister);

	Boolean confirm(String uid);
}
