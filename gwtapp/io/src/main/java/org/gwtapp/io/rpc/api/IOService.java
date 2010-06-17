package org.gwtapp.io.rpc.api;

import java.io.Serializable;

import org.gwtapp.core.rpc.exception.RpcException;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;

public interface IOService extends RemoteService {
	IsSerializable getResponseIsSerializable() throws RpcException;
	Serializable getResponseSerializable() throws RpcException;
}
