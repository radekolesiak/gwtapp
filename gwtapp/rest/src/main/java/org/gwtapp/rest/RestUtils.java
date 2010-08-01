package org.gwtapp.rest;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;

public class RestUtils {

	private final static Logger log = Logger.getLogger(RestUtils.class);

	public static Object decode(String str) {
		return decode(str.getBytes());
	}

	public static Object decode(byte[] array) {
		Object args = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
					new ByteArrayInputStream(array)));
			args = decoder.readObject();
			decoder.close();
		} catch (Exception e) {
			log.error("", e);
		}
		return args;
	}

	public static byte[] encode(Object object) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(output));
		encoder.writeObject(object);
		encoder.close();
		return output.toByteArray();
	}
}
