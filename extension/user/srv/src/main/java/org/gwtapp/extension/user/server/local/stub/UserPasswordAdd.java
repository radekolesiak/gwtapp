package org.gwtapp.extension.user.server.local.stub;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.data.UserPassword;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;

public interface UserPasswordAdd {

	long addUserPassword(UserPassword user) throws RpcException;

	void validateBeforeAddUserPassword(UserPassword user) throws UserValidationException;
}
