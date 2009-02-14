package com.cuprum.server.common.model.user;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.model.IModel;
import com.cuprum.web.common.client.data.TDualValue;

public interface IUserMailModel extends IModel {
	boolean existsMail(User user);

	boolean existsMail(String mail);

	void verifyNewMail(TDualValue<String> mail);
}
