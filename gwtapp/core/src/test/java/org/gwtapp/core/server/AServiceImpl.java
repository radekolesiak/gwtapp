package org.gwtapp.core.server;

import org.gwtapp.core.client.api.AService;
import org.gwtapp.core.client.data.A;
import org.gwtapp.core.client.data.B;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class AServiceImpl extends RemoteServiceServlet implements AService {
	@Override
	public B getB() {
		A a1 = new A();
		A a2 = new A();
		B b = new B();
		a1.setValue("abc");
		a2.setValue("xyz");
		b.setA1(a1);
		b.setA2(a1);
		b.setA3(a2);
		return b;
	}
}
