package org.gwtapp.extension.user.server.stub;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.data.UserImpl;

public interface UserAddStub {
	long addUser(UserImpl user) throws RpcException;
}
