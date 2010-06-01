package org.gwtapp.startapp.server;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;

public class Utils {

	private final static Logger log = Logger.getLogger(Utils.class);

	public final static String CONTENT_TYPE = "text/plain";
	public final static String EXTENSION = "xcc";
	public final static String DEFAULT_NAME = "UserRegister";

	public static String getFilename() {
		return DEFAULT_NAME + "." + EXTENSION;
	}

	public static UserRegisterModel decode(String str) {
		return decode(str.getBytes());
	}

	public static UserRegisterModel decode(byte[] array) {
		UserRegisterModel urm = new UserRegisterModelImpl();
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
					new ByteArrayInputStream(array)));
			urm = (UserRegisterModel) decoder.readObject();
			decoder.close();
		} catch (Exception e) {
			log.error("", e);
		}
		return urm;
	}

	public static byte[] encode(UserRegisterModel book) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(output));
		encoder.writeObject(book);
		encoder.close();
		return output.toByteArray();
	}
}
