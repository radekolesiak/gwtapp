package org.gwtapp.html.rpc.api;

import java.io.Serializable;

import org.gwtapp.core.shared.exception.RpcException;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;

public interface HTMLService extends RemoteService {

	IsSerializable getResponseIsSerializable() throws RpcException;

	Serializable getResponseSerializable() throws RpcException;
}
