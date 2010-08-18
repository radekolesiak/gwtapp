package org.gwtapp.extension.user.server.test;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.gwtapp.extension.user.client.data.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public abstract class RemoteServiceDBServlet extends RemoteServiceServlet {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	static {
		emf = Persistence.createEntityManagerFactory("derbyPU");
		em = emf.createEntityManager();
		initDB();
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		super.service(request, response);
		tx.commit();
		tx = null;
	}

	public static EntityManager getEntityManager() {
		return em;
	}

	private static void initDB() {
		getEntityManager().persist(
				new User("012", "012@012.com", "Zero One Two"));
	}
}
