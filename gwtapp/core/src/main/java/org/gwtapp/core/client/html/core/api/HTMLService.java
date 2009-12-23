package org.gwtapp.core.client.html.core.api;

import java.io.Serializable;

import org.gwtapp.core.client.exception.RpcException;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;

public interface HTMLService extends RemoteService {
	
	IsSerializable getResponseIsSerializable() throws RpcException;
	
	Serializable getResponseSerializable() throws RpcException;
}
