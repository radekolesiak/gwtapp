package org.gwtapp.core.client.data;

import com.google.gwt.user.client.rpc.IsSerializable;

public class B implements IsSerializable {

	private A a1;
	private A a2;
	private A a3;

	public void setA1(A a1) {
		this.a1 = a1;
	}

	public A getA1() {
		return a1;
	}

	public void setA2(A a2) {
		this.a2 = a2;
	}

	public A getA2() {
		return a2;
	}

	public void setA3(A a3) {
		this.a3 = a3;
	}

	public A getA3() {
		return a3;
	}
}
