package com.cuprum.web.smallapp.mainapp.client.stub;

import java.util.Date;

import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;
import com.google.gwt.user.client.rpc.RemoteService;

public interface ISmallApp extends RemoteService {
	Date getLastSessionActivity() throws SessionNotFoundException;
	void forceRemoveSession() throws SessionNotFoundException;
}
