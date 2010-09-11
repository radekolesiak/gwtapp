package org.gwtapp.ccalc.server.test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.gwtapp.extension.user.client.data.UserImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;

@SuppressWarnings("serial")
public abstract class RemoteServiceDBServlet extends RemoteServiceServlet {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	static {
		emf = Persistence.createEntityManagerFactory("derbyPU");
		em = emf.createEntityManager();
		initDB();
	}

	private GuiceFilter filter;

	@Override
	public void init() throws ServletException {
		super.init();
		filter = new GuiceFilter();
		filter.init(new FilterConfig() {
			@Override
			public String getFilterName() {
				return "GuiceFilter";
			}

			@Override
			public ServletContext getServletContext() {
				return RemoteServiceDBServlet.this.getServletContext();
			}

			@Override
			public String getInitParameter(String s) {
				return null;
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Enumeration<?> getInitParameterNames() {
				return new Vector(0).elements();
			}
		});
	}

	@Override
	public void destroy() {
		super.destroy();
		filter.destroy();
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		FilterChain chain = null;
		Injector injector = getInjector();
		if (injector != null) {
			chain = injector.getInstance(FilterChain.class);
		}
		if (chain == null) {
			chain = new GuiceFilterChain();
		}
		filter.doFilter(request, response, chain);
	}

	public class GuiceFilterChain implements FilterChain {
		@Override
		public void doFilter(ServletRequest request, ServletResponse response)
				throws IOException, ServletException {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			RemoteServiceDBServlet.super.service(request, response);
			tx.commit();
			tx = null;
		}
	}

	public static EntityManager getEntityManager() {
		return em;
	}

	public Injector getInjector() {
		return null;
	}

	private static void initDB() {
		getEntityManager().persist(
				new UserImpl("012", "012@012.com", "Zero One Two"));
	}
}
