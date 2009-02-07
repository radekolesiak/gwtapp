package com.cuprum.web.common.client.stub;

import com.cuprum.web.common.client.data.TConnectionSession;
import com.google.gwt.user.client.rpc.RemoteService;

public interface IConnectionSession extends RemoteService {
	TConnectionSession getNewConnectionSession();
}
