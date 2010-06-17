package org.gwtapp.html.server;

import org.apache.log4j.Logger;

public class HtmlRpcBean {

	private final static Logger log = Logger.getLogger(HtmlRpcBean.class);

	private Object value = null;

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		/*-
		try {
			if (value instanceof IsSerializable) {
				return IOServer.encode(IOServer.success((IsSerializable) value));
			} else if (value instanceof Serializable) {
				return IOServer.encode(IOServer.success((Serializable) value));
			}
		} catch (IOServerException e) {
			log.error("", e);
		}*/
		return null;
	}
}
