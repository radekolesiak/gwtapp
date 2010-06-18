package org.gwtapp.io.server;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IOHtmlRpcSerializer {

	private final static Logger log = Logger.getLogger(IOHtmlRpcSerializer.class);

	private Object value = null;

	public void setValue(Object value) {
		this.value = value;
	}

	public String getValue() {
		try {
			if (value instanceof IsSerializable) {
				return IOServer.encode(IOServer.success((IsSerializable) value));
			} else if (value instanceof Serializable) {
				return IOServer.encode(IOServer.success((Serializable) value));
			}
		} catch (IOServerException e) {
			log.error("", e);
		}
		return null;
	}
}
