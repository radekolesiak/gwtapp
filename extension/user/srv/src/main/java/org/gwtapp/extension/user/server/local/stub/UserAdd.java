package org.gwtapp.extension.user.server.local.stub;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;

public interface UserAdd {

	long addUser(User user) throws RpcException;

	void validateBeforeAddUser(User user) throws UserValidationException;
}
