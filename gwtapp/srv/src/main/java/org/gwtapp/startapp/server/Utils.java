package org.gwtapp.startapp.server;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.gwtapp.startapp.client.data.user.register.UserRegister;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModelImpl;

public class Utils {

	public final static String CONTENT_TYPE = "text/plain";
	public final static String EXTENSION = "xcc";
	public final static String DEFAULT_NAME = "UserRegister";

	public static String getFilename() {
		return DEFAULT_NAME + "." + EXTENSION;
	}

	public static UserRegister decode(String str) {
		return decode(str.getBytes());
	}

	public static UserRegister decode(byte[] array) {
		UserRegister book = new UserRegisterModelImpl();
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
					new ByteArrayInputStream(array)));
			book = (UserRegister) decoder.readObject();
			decoder.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public static byte[] encode(UserRegister book) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(output));
		encoder.writeObject(book);
		encoder.close();
		return output.toByteArray();
	}
}
