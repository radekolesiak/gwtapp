package org.gwtapp.extension.user.server.stub;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.data.UserPassword;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;

public interface UserAdd {

	long addUser(UserPassword user) throws RpcException;

	void addUserValidate(UserPassword user) throws UserValidationException;
}
