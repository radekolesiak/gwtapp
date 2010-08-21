package org.gwtapp.extension.user.server.stub;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.data.User;

public interface UserAddStub {
	long addUser(User user) throws RpcException;
}
