package org.gwtapp.extension.user.server.stub;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.data.UserPassword;

public interface UserAddStub {
	long addUser(UserPassword user) throws RpcException;
}
